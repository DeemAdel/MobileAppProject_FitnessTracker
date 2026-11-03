package com.example.mobileappproject_fitnesstracker;
import android.os.Bundle; import android.view.View;
import android.widget.Button; import android.widget.EditText; import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class GoalActivity extends AppCompatActivity {
    EditText exerciseGoal;
    EditText waterGoal;
    EditText calorieGoal;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        exerciseGoal = findViewById(R.id.etExerciseGoal);
        waterGoal = findViewById(R.id.etWaterGoal);
        calorieGoal = findViewById(R.id.etCalorieGoal);
        saveButton = findViewById(R.id.btnSaveGoal);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String exercise = exerciseGoal.getText().toString();
                String water = waterGoal.getText().toString();
                String calories = calorieGoal.getText().toString();

                if (exercise.isEmpty() || water.isEmpty() || calories.isEmpty()) {
                    Toast.makeText(
                            GoalActivity.this,
                            "Please enter all goals",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    Toast.makeText(
                            GoalActivity.this,
                            "Goal information entered successfully",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}