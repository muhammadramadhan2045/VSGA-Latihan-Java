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
import com.example.mypens.db.feed.FeedReaderDbHelper;

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
            String nrp = bundle.getString("nrp");
            String name = bundle.getString("name");
            String birth = bundle.getString("birth");
            String gender = bundle.getString("gender");
            String address = bundle.getString("address");


            binding.nrpEditText.setText(nrp);
            binding.nameEditText.setText(name);
            binding.birthEditText.setText(birth);
            binding.genderEditText.setText(gender);
            binding.addressEditText.setText(address);

        }else{
            //if bundle is null
            binding.nrpEditText.setText("NRP");
            binding.nameEditText.setText("Name");
            binding.birthEditText.setText("Birth");
            binding.genderEditText.setText("Gender");
            binding.addressEditText.setText("Address");
        }

        binding.btnUpdate.setOnClickListener(v -> {
            String nrp= Objects.requireNonNull(binding.nrpEditText.getText()).toString();
            String name= Objects.requireNonNull(binding.nameEditText.getText()).toString();
            String birth= Objects.requireNonNull(binding.birthEditText.getText()).toString();
            String gender= Objects.requireNonNull(binding.genderEditText.getText()).toString();
            String address= Objects.requireNonNull(binding.addressEditText.getText()).toString();



            dbHelper = new FeedReaderDbHelper(getContext());
            dbHelper.updateData(
                    nrp,
                    name,
                    birth,
                    gender,
                    address
            );

            NavController navController = Navigation.findNavController(view);
            navController.navigateUp();
        });

        //disable edit text for title
        binding.nrpEditText.setEnabled(false);
    }
}