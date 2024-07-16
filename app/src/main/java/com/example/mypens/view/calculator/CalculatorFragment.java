package com.example.mypens.view.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypens.R;
import com.example.mypens.databinding.FragmentCalculatorBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class CalculatorFragment extends Fragment {

    TextInputEditText firstNumber, secondNumber;
    MaterialButton addButton, subtractButton, multiplyButton, divideButton;
    TextView result;

    private FragmentCalculatorBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar myToolbar = binding.myToolbar;
        myToolbar.setTitle("Calculator");
        myToolbar.inflateMenu(R.menu.menu_main);
        binding.myToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_input_nama) {
                NavController navController = NavHostFragment.findNavController(this);
                navController.navigate(R.id.action_calculatorFragment_to_inputNamaFragment);
            }
            return true;
        });


        firstNumber = binding.firstNumberEditText;
        secondNumber = binding.secondNumberEditText;
        addButton = binding.btPlus;
        subtractButton = binding.btMinus;
        multiplyButton = binding.btMultiple;
        divideButton = binding.btDivide;
        result = binding.resultValue;


        addButton.setOnClickListener(v -> {
            if (Objects.requireNonNull(firstNumber.getText()).toString().isEmpty() || Objects.requireNonNull(secondNumber.getText()).toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter both numbers", Toast.LENGTH_SHORT).show();
            } else {
                double num1 = Double.parseDouble(firstNumber.getText().toString());
                double num2 = Double.parseDouble(secondNumber.getText().toString());
                double sum = num1 + num2;
                result.setText(String.format("%s", sum));
            }
        });

        // Subtract button
        subtractButton.setOnClickListener(v -> {
            if (Objects.requireNonNull(firstNumber.getText()).toString().isEmpty() || Objects.requireNonNull(secondNumber.getText()).toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter both numbers", Toast.LENGTH_SHORT).show();
            } else {
                double num1 = Double.parseDouble(firstNumber.getText().toString());
                double num2 = Double.parseDouble(secondNumber.getText().toString());
                double sum = num1 - num2;
                result.setText(String.format("%s", sum));
            }
        });

        multiplyButton.setOnClickListener(v -> {
            if (Objects.requireNonNull(firstNumber.getText()).toString().isEmpty() || Objects.requireNonNull(secondNumber.getText()).toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter both numbers", Toast.LENGTH_SHORT).show();
            } else {
                double num1 = Double.parseDouble(firstNumber.getText().toString());
                double num2 = Double.parseDouble(secondNumber.getText().toString());
                double sum = num1 * num2;
                result.setText(String.format("%s", sum));
            }
        });

        divideButton.setOnClickListener(v -> {
            if (Objects.requireNonNull(firstNumber.getText()).toString().isEmpty() || Objects.requireNonNull(secondNumber.getText()).toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please enter both numbers", Toast.LENGTH_SHORT).show();
            } else {
                double num1 = Double.parseDouble(firstNumber.getText().toString());
                double num2 = Double.parseDouble(secondNumber.getText().toString());
                double sum = num1 / num2;
                result.setText(String.format("%s", sum));
            }
        });

        // Clear button
        binding.btClear.setOnClickListener(v -> {
            firstNumber.setText("");
            secondNumber.setText("");
            result.setText("");
        });
    }
}
