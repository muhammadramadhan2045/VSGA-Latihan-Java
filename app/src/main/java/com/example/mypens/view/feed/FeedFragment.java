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

import com.example.mypens.databinding.FragmentFeedBinding;
import com.example.mypens.db.feed.FeedReaderDbHelper;
import com.google.android.material.textfield.TextInputEditText;

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

        TextInputEditText nrpEditText = binding.nrpEditText;
        TextInputEditText nameEditText = binding.nameEditText;
        TextInputEditText birthEditText = binding.birthEditText;
        TextInputEditText genderEditText = binding.genderEditText;
        TextInputEditText addressEditText = binding.addressEditText;

        binding.buttonSave.setOnClickListener(v -> {
            String nrp = Objects.requireNonNull(nrpEditText.getText()).toString();
            String name = Objects.requireNonNull(nameEditText.getText()).toString();
            String birth = Objects.requireNonNull(birthEditText.getText()).toString();
            String gender = Objects.requireNonNull(genderEditText.getText()).toString();
            String address = Objects.requireNonNull(addressEditText.getText()).toString();

            //insdert to database try catch
            try {
                dbHelper = new FeedReaderDbHelper(getContext());
                dbHelper.insertData(
                        nrp,
                        name,
                        birth,
                        gender,
                        address
                );
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();
            } catch (Exception e) {
                Log.d("TAG", "Error: " + e.getMessage());
            }


        });


    }
}