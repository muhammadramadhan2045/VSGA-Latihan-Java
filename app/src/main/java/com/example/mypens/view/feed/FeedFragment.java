package com.example.mypens.view.feed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mypens.R;
import com.example.mypens.databinding.FragmentFeedBinding;
import com.example.mypens.db.FeedReaderDbHelper;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.Objects;

public class FeedFragment extends Fragment {


    private FragmentFeedBinding binding;

    FeedReaderDbHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText titleEditText = binding.titleEditText;
        TextInputEditText subEditText = binding.subtitleEditText;

        binding.buttonSave.setOnClickListener(v -> {
            String title = Objects.requireNonNull(titleEditText.getText()).toString();
            String subtitle = Objects.requireNonNull(subEditText.getText()).toString();

            //insdert to database try catch
            try {
                dbHelper = new FeedReaderDbHelper(getContext());
                dbHelper.insertData(title, subtitle);
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();
            } catch (Exception e) {
                Log.d("TAG", "Error: " + e.getMessage());
            }


        });


    }
}