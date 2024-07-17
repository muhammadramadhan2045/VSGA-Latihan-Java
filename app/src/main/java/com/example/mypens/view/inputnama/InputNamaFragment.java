package com.example.mypens.view.inputnama;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mypens.R;
import com.example.mypens.databinding.FragmentInputNamaBinding;
import com.example.mypens.db.FeedReaderContract;
import com.example.mypens.db.FeedReaderDbHelper;

import java.util.Objects;


public class InputNamaFragment extends Fragment {


    //binding
    private FragmentInputNamaBinding binding;
    FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getContext());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInputNamaBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btShow.setOnClickListener(v -> {
            String name = Objects.requireNonNull(binding.nameEditText.getText()).toString();
            if (name.isEmpty()) {
                binding.tvResult.setText(R.string.name_validation);
                return;
            }
            binding.tvResult.setVisibility(View.VISIBLE);
            binding.tvResult.setText(String.format("Halo, %s", name));

        });
    }
}