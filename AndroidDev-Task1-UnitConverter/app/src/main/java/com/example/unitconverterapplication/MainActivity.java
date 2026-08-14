package com.example.unitconverterapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText inputValue;
    private Spinner categorySpinner;
    private Spinner sourceSpinner;
    private Spinner targetSpinner;
    private Button convertButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        categorySpinner = findViewById(R.id.categorySpinner);
        sourceSpinner = findViewById(R.id.sourceSpinner);
        targetSpinner = findViewById(R.id.targetSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        setupSpinners();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        convertButton.setOnClickListener(v -> {
            performConversion();
        });
    }

    private void setupSpinners() {
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUnitSpinners(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void updateUnitSpinners(String category) {
        int arrayResId;
        switch (category) {
            case "Weight": arrayResId = R.array.weight_units; break;
            case "Temperature": arrayResId = R.array.temp_units; break;
            default: arrayResId = R.array.length_units; break;
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        sourceSpinner.setAdapter(adapter);
        targetSpinner.setAdapter(adapter);
    }

    private void performConversion() {
        String input = inputValue.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double value;
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (categorySpinner.getSelectedItem() == null || 
            sourceSpinner.getSelectedItem() == null || 
            targetSpinner.getSelectedItem() == null) {
            Toast.makeText(this, "Please select units", Toast.LENGTH_SHORT).show();
            return;
        }

        String category = categorySpinner.getSelectedItem().toString();
        String from = sourceSpinner.getSelectedItem().toString();
        String to = targetSpinner.getSelectedItem().toString();

        double result = 0;

        if (category.equals("Length")) {
            result = convertLength(value, from, to);
        } else if (category.equals("Weight")) {
            result = convertWeight(value, from, to);
        } else if (category.equals("Temperature")) {
            result = convertTemperature(value, from, to);
        }

        resultText.setText(String.format("%.2f %s", result, to));
    }

    private double convertLength(double value, String from, String to) {
        double meters;
        switch (from) {
            case "Centimeter": meters = value / 100; break;
            case "Meter": meters = value; break;
            case "Kilometer": meters = value * 1000; break;
            default: meters = value;
        }

        switch (to) {
            case "Centimeter": return meters * 100;
            case "Meter": return meters;
            case "Kilometer": return meters / 1000;
            default: return meters;
        }
    }

    private double convertWeight(double value, String from, String to) {
        double grams;
        switch (from) {
            case "Gram": grams = value; break;
            case "Kilogram": grams = value * 1000; break;
            case "Pound": grams = value * 453.592; break;
            default: grams = value;
        }

        switch (to) {
            case "Gram": return grams;
            case "Kilogram": return grams / 1000;
            case "Pound": return grams / 453.592;
            default: return grams;
        }
    }

    private double convertTemperature(double value, String from, String to) {
        double celsius;
        switch (from) {
            case "Celsius": celsius = value; break;
            case "Fahrenheit": celsius = (value - 32) * 5 / 9; break;
            case "Kelvin": celsius = value - 273.15; break;
            default: celsius = value;
        }

        switch (to) {
            case "Celsius": return celsius;
            case "Fahrenheit": return (celsius * 9 / 5) + 32;
            case "Kelvin": return celsius + 273.15;
            default: return celsius;
        }
    }
}