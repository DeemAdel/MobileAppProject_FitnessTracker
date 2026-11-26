
package com.example.mobileappproject_fitnesstracker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class WorkoutActivity extends AppCompatActivity {

    private Spinner spinnerType;
    private EditText etDuration, etDate;
    private Button btnSave;
    private ImageView btnBack;

    private DatabaseHelper db;

    private final String[] months = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // Connect to SQLite database
        db = new DatabaseHelper(this);

        // Connect XML elements
        spinnerType = findViewById(R.id.spinnerType);
        etDuration = findViewById(R.id.etDuration);
        etDate = findViewById(R.id.etDate);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        // Workout types
        String[] types = {
                "Running", "Walking", "Cycling",
                "Swimming", "Gym", "Yoga"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                types
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerType.setAdapter(adapter);

        // Set today's date
        Calendar today = Calendar.getInstance();

        etDate.setText(
                months[today.get(Calendar.MONTH)] + " "
                        + today.get(Calendar.DAY_OF_MONTH) + ", "
                        + today.get(Calendar.YEAR)
        );

        // Back button
        btnBack.setOnClickListener(v -> finish());

        // Date picker
        etDate.setOnClickListener(v -> {

            Calendar c = Calendar.getInstance();

            DatePickerDialog dialog = new DatePickerDialog(
                    WorkoutActivity.this,

                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(
                                DatePicker view,
                                int year,
                                int month,
                                int day
                        ) {
                            etDate.setText(
                                    months[month] + " " + day + ", " + year
                            );
                        }
                    },

                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            );

            dialog.show();
        });

        // Save workout
        btnSave.setOnClickListener(v -> saveWorkout());
    }

    private void saveWorkout() {

        String type = spinnerType.getSelectedItem().toString();

        String durationStr = etDuration.getText().toString().trim();

        String date = etDate.getText().toString().trim();

        if (durationStr.isEmpty() || date.isEmpty()) {

            Toast.makeText(this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT).show();

            return;
        }

        try {

            int duration = Integer.parseInt(durationStr);

            if (duration <= 0) {

                Toast.makeText(this,
                        "Please enter a valid duration",
                        Toast.LENGTH_SHORT).show();

                return;
            }

            // Save workout to SQLite
            db.addWorkout(type, duration, date);

            Toast.makeText(this,
                    "Workout saved successfully!",
                    Toast.LENGTH_SHORT).show();

            etDuration.setText("");

            finish();

        } catch (NumberFormatException e) {

            Toast.makeText(this,
                    "Please enter a valid number",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
