package com.example.mobileappproject_fitnesstracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IntakeActivity extends Activity {

    EditText etWater, etCalories;
    Button btnSaveIntake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intake);

        etWater = findViewById(R.id.etWater);
        etCalories = findViewById(R.id.etCalories);
        btnSaveIntake = findViewById(R.id.btnSaveIntake);

        btnSaveIntake.setOnClickListener(v -> {

            String waterText =
                    etWater.getText().toString().trim();

            String caloriesText =
                    etCalories.getText().toString().trim();

            if (waterText.isEmpty() ||
                    caloriesText.isEmpty()) {

                Toast.makeText(this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int water = Integer.parseInt(waterText);
                int calories = Integer.parseInt(caloriesText);

                if (water < 0 || calories < 0) {
                    Toast.makeText(this,
                            "Please enter valid values",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this,
                        "Intake entered successfully!",
                        Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException e) {
                Toast.makeText(this,
                        "Please enter valid numbers",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}