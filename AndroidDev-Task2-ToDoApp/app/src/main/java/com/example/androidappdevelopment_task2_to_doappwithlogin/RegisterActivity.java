package com.example.androidappdevelopment_task2_to_doappwithlogin;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtEmail;
    EditText edtPassword;

    Button btnRegister;
    TextView txtLogin;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnRegister = findViewById(R.id.btnRegister);
        txtLogin = findViewById(R.id.txtLogin);

        btnRegister.setOnClickListener(v -> register());

        txtLogin.setOnClickListener(v -> finish());
    }

    private void register() {

        String name = edtName.getText()
                .toString()
                .trim();

        String email = edtEmail.getText()
                .toString()
                .trim();

        String password = edtPassword.getText()
                .toString();

        if (name.isEmpty()
                || email.isEmpty()
                || password.isEmpty()) {

            Toast.makeText(
                    this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        if (password.length() < 6) {

            Toast.makeText(
                    this,
                    "Password must be at least 6 characters",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        boolean registered =
                databaseHelper.registerUser(
                        name,
                        email,
                        password
                );

        if (registered) {

            Toast.makeText(
                    this,
                    "Registration successful",
                    Toast.LENGTH_SHORT
            ).show();

            finish();

        } else {

            Toast.makeText(
                    this,
                    "Email already exists",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}