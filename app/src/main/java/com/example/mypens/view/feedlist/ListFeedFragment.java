package com.example.mypens.view.feedlist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mypens.R;
import com.example.mypens.adapter.FeedAdapter;
import com.example.mypens.databinding.FragmentListFeedBinding;
import com.example.mypens.db.feed.FeedReaderContract;
import com.example.mypens.db.feed.FeedReaderDbHelper;
import com.example.mypens.model.Feed;

import java.util.ArrayList;
import java.util.List;


public class ListFeedFragment extends Fragment {


    private FragmentListFeedBinding binding;
    private FeedReaderDbHelper dbHelper;
    private FeedAdapter feedAdapter;
    private List<Feed> feedList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListFeedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fabAddFeed.setOnClickListener(v -> {
            //navigate to feed fragment
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_listFeedFragment_to_feedFragment);
        });

        dbHelper = new FeedReaderDbHelper(getContext());


        binding.rvFeed.setLayoutManager(new LinearLayoutManager(getContext()));

        loadEntriesFromDatabase();

        //set adapter
        feedAdapter = new FeedAdapter(feedList);
        binding.rvFeed.setAdapter(feedAdapter);


        //set when rv empty show visibilty to lotti
        if (feedList.isEmpty()) {
            binding.viewEmpty.animationView.setVisibility(View.VISIBLE);
        } else {
            binding.viewEmpty.animationView.setVisibility(View.GONE);
        }

        feedAdapter.setOnItemClickCallback(feed -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", feed.getName());
            bundle.putString("address", feed.getAddress());
            bundle.putString("gender", feed.getGender());
            bundle.putString("birth", feed.getDate());
            bundle.putString("nrp", feed.getNrp());
            NavController navController = NavHostFragment.findNavController(ListFeedFragment.this);
            navController.navigate(R.id.action_listFeedFragment_to_detailFeedFragment, bundle);
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Feed feed = feedList.get(position);

                //delete from database
                try {
                    dbHelper.deleteData(feed.getName());
                    feedList.remove(position);
                    feedAdapter.notifyItemRemoved(position);
                    feedAdapter.notifyItemRangeChanged(position, feedList.size());
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        itemTouchHelper.attachToRecyclerView(binding.rvFeed);


    }

    private void loadEntriesFromDatabase() {
        feedList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_NRP,
                FeedReaderContract.FeedEntry.COLUMN_NAME_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_DATE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_GENDER,
                FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS
        };

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME));
            String nrp = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_NRP));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_DATE));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_GENDER));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS));



            feedList.add(new Feed((int) itemId, nrp,name, date, gender, address));
        }
        cursor.close();
    }


}