package com.example.androidappdevelopment_task2_to_doappwithlogin;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView txtRegister;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);

        btnLogin.setOnClickListener(v -> login());

        txtRegister.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class
            );

            startActivity(intent);
        });
    }

    private void login() {

        String email = edtEmail.getText()
                .toString()
                .trim();

        String password = edtPassword.getText()
                .toString();

        if (email.isEmpty() || password.isEmpty()) {

            Toast.makeText(
                    this,
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        int userId = databaseHelper.loginUser(
                email,
                password
        );

        if (userId != -1) {

            SharedPreferences preferences =
                    getSharedPreferences(
                            "TodoSession",
                            MODE_PRIVATE
                    );

            preferences.edit()
                    .putInt("user_id", userId)
                    .putBoolean("logged_in", true)
                    .apply();

            Intent intent = new Intent(
                    LoginActivity.this,
                    MainActivity.class
            );

            startActivity(intent);

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Invalid email or password",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}