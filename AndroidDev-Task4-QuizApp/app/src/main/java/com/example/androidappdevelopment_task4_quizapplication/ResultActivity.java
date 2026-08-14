package com.example.androidappdevelopment_task4_quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreText = findViewById(R.id.scoreText);
        TextView correctText = findViewById(R.id.correctIncorrectText); // Reused ID for correct count
        TextView incorrectText = findViewById(R.id.incorrectText);
        Button restartButton = findViewById(R.id.restartButton);

        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);
        int correct = getIntent().getIntExtra("CORRECT", 0);
        int incorrect = getIntent().getIntExtra("INCORRECT", 0);

        scoreText.setText(score + "/" + total);
        correctText.setText(String.valueOf(correct));
        incorrectText.setText(String.valueOf(incorrect));

        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}
