package com.example.androidappdevelopment_task4_quizapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questionCounterText, questionText;
    private MaterialButton[] optionButtons = new MaterialButton[4];
    private Button nextButton;
    private ProgressBar progressBar;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int correctCount = 0;
    private int incorrectCount = 0;
    private boolean isAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionCounterText = findViewById(R.id.questionCounter);
        questionText = findViewById(R.id.questionText);
        progressBar = findViewById(R.id.quizProgressBar);
        
        optionButtons[0] = findViewById(R.id.option1);
        optionButtons[1] = findViewById(R.id.option2);
        optionButtons[2] = findViewById(R.id.option3);
        optionButtons[3] = findViewById(R.id.option4);
        nextButton = findViewById(R.id.nextButton);

        loadQuestions();
        Collections.shuffle(questionList);
        progressBar.setMax(questionList.size());
        showQuestion();

        for (int i = 0; i < 4; i++) {
            final int index = i;
            optionButtons[i].setOnClickListener(v -> {
                if (!isAnswered) {
                    checkAnswer(index);
                }
            });
        }

        nextButton.setOnClickListener(v -> {
            currentQuestionIndex++;
            if (currentQuestionIndex < questionList.size()) {
                showQuestion();
            } else {
                finishQuiz();
            }
        });
    }

    private void loadQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("What is the capital of France?", new String[]{"London", "Berlin", "Paris", "Madrid"}, 2));
        questionList.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1));
        questionList.add(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"}, 1));
        questionList.add(new Question("What is the largest ocean on Earth?", new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 3));
        questionList.add(new Question("Which element has the chemical symbol 'O'?", new String[]{"Gold", "Oxygen", "Silver", "Iron"}, 1));
        questionList.add(new Question("What is the smallest prime number?", new String[]{"0", "1", "2", "3"}, 2));
        questionList.add(new Question("In which year did World War II end?", new String[]{"1941", "1943", "1945", "1947"}, 2));
        questionList.add(new Question("What is the largest mammal in the world?", new String[]{"Elephant", "Blue Whale", "Giraffe", "Hippopotamus"}, 1));
        questionList.add(new Question("How many continents are there?", new String[]{"5", "6", "7", "8"}, 2));
        questionList.add(new Question("What is the square root of 64?", new String[]{"6", "7", "8", "9"}, 2));
    }

    private void showQuestion() {
        isAnswered = false;
        nextButton.setVisibility(View.GONE);

        Question currentQuestion = questionList.get(currentQuestionIndex);
        questionCounterText.setText("Question " + (currentQuestionIndex + 1) + "/" + questionList.size());
        progressBar.setProgress(currentQuestionIndex + 1);
        questionText.setText(currentQuestion.getQuestion());
        
        String[] options = currentQuestion.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setEnabled(true);
            // Reset to aesthetic theme colors
            optionButtons[i].setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            optionButtons[i].setTextColor(ContextCompat.getColor(this, R.color.text_medium));
            optionButtons[i].setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.girly_purple_light)));
        }
    }

    private void checkAnswer(int selectedIndex) {
        isAnswered = true;
        int correctIndex = questionList.get(currentQuestionIndex).getCorrectAnswerIndex();

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setEnabled(false);
            if (i == correctIndex) {
                // Highlight correct answer in soft green
                optionButtons[i].setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.soft_green)));
                optionButtons[i].setTextColor(Color.WHITE);
                optionButtons[i].setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.soft_green)));
            } else if (i == selectedIndex) {
                // Highlight selected wrong answer in soft red
                optionButtons[i].setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.soft_red)));
                optionButtons[i].setTextColor(Color.WHITE);
                optionButtons[i].setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.soft_red)));
            }
        }

        if (selectedIndex == correctIndex) {
            score++;
            correctCount++;
        } else {
            incorrectCount++;
        }

        nextButton.setVisibility(View.VISIBLE);
    }

    private void finishQuiz() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", questionList.size());
        intent.putExtra("CORRECT", correctCount);
        intent.putExtra("INCORRECT", incorrectCount);
        startActivity(intent);
        finish();
    }
}
