package com.example.mobileappproject_fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnWorkout, btnIntake, btnGoal, btnProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

        btnWorkout = findViewById(R.id.btnWorkout);
        btnIntake = findViewById(R.id.btnIntake);
        btnGoal = findViewById(R.id.btnGoal);
        btnProgress = findViewById(R.id.btnProgress);

        btnWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, WorkoutActivity.class);
            startActivity(intent);
        });

        btnIntake.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, IntakeActivity.class);
            startActivity(intent);
        });

        btnGoal.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, GoalActivity.class);
            startActivity(intent);
        });

        btnProgress.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, ProgressActivity.class);
            startActivity(intent);
        });
    }
}
