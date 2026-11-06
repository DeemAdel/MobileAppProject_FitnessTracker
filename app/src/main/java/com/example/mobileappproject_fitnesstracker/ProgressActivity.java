package com.example.mobileappproject_fitnesstracker;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressActivity extends AppCompatActivity {

    ProgressBar workoutBar, waterBar, caloriesBar;
    TextView workoutText, waterText, caloriesText;
    TextView workoutPercent, waterPercent, caloriesPercent;
    TextView messageTitle, messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        workoutBar = findViewById(R.id.workoutBar);
        waterBar = findViewById(R.id.waterBar);
        caloriesBar = findViewById(R.id.caloriesBar);

        workoutText = findViewById(R.id.workoutText);
        waterText = findViewById(R.id.waterText);
        caloriesText = findViewById(R.id.caloriesText);

        workoutPercent = findViewById(R.id.workoutPercent);
        waterPercent = findViewById(R.id.waterPercent);
        caloriesPercent = findViewById(R.id.caloriesPercent);

        messageTitle = findViewById(R.id.messageTitle);
        messageText = findViewById(R.id.messageText);

        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProgress();
    }

    private void loadProgress() {
        int workoutDone = 30,    workoutGoal = 30;
        int waterDone = 1500,    waterGoal = 2000;
        int caloriesDone = 1800, caloriesGoal = 1800;

        int goalsReached = 0;
        if (showRow(workoutBar, workoutText, workoutPercent,
                workoutDone, workoutGoal, "min") >= 100) goalsReached++;
        if (showRow(waterBar, waterText, waterPercent,
                waterDone, waterGoal, "ml") >= 100) goalsReached++;
        if (showRow(caloriesBar, caloriesText, caloriesPercent,
                caloriesDone, caloriesGoal, "kcal") >= 100) goalsReached++;

        if (goalsReached == 3) {
            messageTitle.setText("Great Job!");
            messageText.setText("You reached all your daily goals.");
        } else {
            messageTitle.setText("Keep Going!");
            messageText.setText(goalsReached + " of 3 goals reached today.");
        }
    }

    private int showRow(ProgressBar bar, TextView text, TextView percentText,
                        int done, int goal, String unit) {
        int percent = (goal > 0) ? Math.min(100, done * 100 / goal) : 0;
        bar.setProgress(percent);
        text.setText(done + " / " + goal + " " + unit);
        percentText.setText(percent + "%");
        return percent;
    }
}