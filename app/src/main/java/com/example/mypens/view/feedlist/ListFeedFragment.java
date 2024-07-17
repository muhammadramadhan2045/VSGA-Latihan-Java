package com.example.mypens.view.feedlist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.mypens.R;
import com.example.mypens.adapter.FeedAdapter;
import com.example.mypens.databinding.FragmentListFeedBinding;
import com.example.mypens.db.FeedReaderContract;
import com.example.mypens.db.FeedReaderDbHelper;
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


    }

    private void loadEntriesFromDatabase() {
        feedList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE
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
            String title = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
            String subtitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));
            feedList.add(new Feed((int) itemId, title, subtitle));
        }
        cursor.close();
    }



}