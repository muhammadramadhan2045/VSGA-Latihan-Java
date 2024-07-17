package com.example.mypens.view.detailfeed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mypens.R;
import com.example.mypens.databinding.FragmentDetailFeedBinding;
import com.example.mypens.db.FeedReaderDbHelper;

import java.util.Objects;


public class DetailFeedFragment extends Fragment {

    private FragmentDetailFeedBinding binding;
    FeedReaderDbHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailFeedBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //get data from bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("title");
            String subtitle = bundle.getString("subtitle");

            binding.titleEditText.setText(title);
            binding.subtitleEditText.setText(subtitle);
        }else{
            binding.titleEditText.setText(R.string.no_title);
            binding.subtitleEditText.setText(R.string.no_subtitle);
        }

        binding.btnUpdate.setOnClickListener(v -> {
            String title = Objects.requireNonNull(binding.titleEditText.getText()).toString();
            String subtitle = Objects.requireNonNull(binding.subtitleEditText.getText()).toString();

            dbHelper = new FeedReaderDbHelper(getContext());
            dbHelper.updateData(title, subtitle);

            NavController navController = Navigation.findNavController(view);
            navController.navigateUp();
        });

        //disable edit text for title
        binding.titleEditText.setEnabled(false);
    }
}