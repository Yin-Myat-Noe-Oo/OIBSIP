package com.example.androidappdevelopment_task2_to_doappwithlogin;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listTasks;
    TextView txtEmpty;
    TextView txtWelcome;
    Button btnAddTask;
    Button btnLogout;

    DatabaseHelper databaseHelper;

    ArrayList<Task> taskList;
    TaskAdapter taskAdapter;

    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        listTasks = findViewById(R.id.listTasks);
        txtEmpty = findViewById(R.id.txtEmpty);
        txtWelcome = findViewById(R.id.txtWelcome);

        btnAddTask = findViewById(R.id.btnAddTask);
        btnLogout = findViewById(R.id.btnLogout);

        SharedPreferences preferences =
                getSharedPreferences(
                        "TodoSession",
                        MODE_PRIVATE
                );

        boolean loggedIn =
                preferences.getBoolean(
                        "logged_in",
                        false
                );

        userId = preferences.getInt(
                "user_id",
                -1
        );

        if (!loggedIn || userId == -1) {

            goToLogin();

            return;
        }

        String userName = databaseHelper.getUserName(userId);
        txtWelcome.setText(getString(R.string.welcome_user, userName));

        btnAddTask.setOnClickListener(
                v -> showAddTaskDialog()
        );

        btnLogout.setOnClickListener(
                v -> logout()
        );

        loadTasks();
    }

    // =========================
    // LOAD TASKS
    // =========================

    private void loadTasks() {

        taskList = new ArrayList<>();

        try (Cursor cursor = databaseHelper.getTasks(userId)) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(
                            cursor.getColumnIndexOrThrow("id")
                    );

                    String taskName = cursor.getString(
                            cursor.getColumnIndexOrThrow("task_name")
                    );

                    String notes = cursor.getString(
                            cursor.getColumnIndexOrThrow("notes")
                    );

                    boolean completed =
                            cursor.getInt(
                                    cursor.getColumnIndexOrThrow("completed")
                            ) == 1;

                    taskList.add(
                            new Task(
                                    id,
                                    taskName,
                                    notes,
                                    completed
                            )
                    );
                } while (cursor.moveToNext());
            }
        }

        taskAdapter = new TaskAdapter(
                this,
                taskList,
                databaseHelper
        );

        listTasks.setAdapter(taskAdapter);

        updateEmptyState();
    }

    private void updateEmptyState() {

        if (taskList.isEmpty()) {

            txtEmpty.setVisibility(View.VISIBLE);
            listTasks.setVisibility(View.GONE);

        } else {

            txtEmpty.setVisibility(View.GONE);
            listTasks.setVisibility(View.VISIBLE);
        }
    }

    // =========================
    // ADD TASK DIALOG
    // =========================

    private void showAddTaskDialog() {

        View view = getLayoutInflater()
                .inflate(
                        R.layout.dialog_add_task,
                        null
                );

        EditText edtTaskName =
                view.findViewById(R.id.edtTaskName);

        EditText edtNotes =
                view.findViewById(R.id.edtNotes);

        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle(R.string.add_task_plus)
                        .setView(view)
                        .setPositiveButton(
                                R.string.add,
                                null
                        )
                        .setNegativeButton(
                                R.string.cancel,
                                null
                        )
                        .create();

        dialog.setOnShowListener(
                dialogInterface -> {

                    Button addButton =
                            dialog.getButton(
                                    AlertDialog.BUTTON_POSITIVE
                            );

                    addButton.setOnClickListener(
                            v -> {

                                String taskName =
                                        edtTaskName
                                                .getText()
                                                .toString()
                                                .trim();

                                String notes =
                                        edtNotes
                                                .getText()
                                                .toString()
                                                .trim();

                                if (taskName.isEmpty()) {
                                    edtTaskName.setError(
                                            getString(R.string.enter_task_name)
                                    );
                                    return;
                                }

                                boolean added =
                                        databaseHelper.addTask(
                                                userId,
                                                taskName,
                                                notes
                                        );

                                if (added) {

                                    Toast.makeText(
                                            MainActivity.this,
                                            R.string.task_added,
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    dialog.dismiss();

                                    loadTasks();
                                }
                            }
                    );
                }
        );

        dialog.show();
    }

    // =========================
    // LOGOUT
    // =========================

    private void logout() {

        SharedPreferences preferences =
                getSharedPreferences(
                        "TodoSession",
                        MODE_PRIVATE
                );

        preferences.edit().clear().apply();

        goToLogin();
    }

    private void goToLogin() {

        Intent intent = new Intent(
                MainActivity.this,
                LoginActivity.class
        );

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
        );

        startActivity(intent);

        finish();
    }
}
