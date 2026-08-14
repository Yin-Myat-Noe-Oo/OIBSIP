package com.example.androidappdevelopment_task2_to_doappwithlogin;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TodoApp.db";
    private static final int DATABASE_VERSION = 1;

    // Users table
    private static final String TABLE_USERS = "users";
    private static final String COL_USER_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";

    // Tasks table
    private static final String TABLE_TASKS = "tasks";
    private static final String COL_TASK_ID = "id";
    private static final String COL_TASK_USER_ID = "user_id";
    private static final String COL_TASK_NAME = "task_name";
    private static final String COL_TASK_NOTES = "notes";
    private static final String COL_COMPLETED = "completed";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_EMAIL + " TEXT UNIQUE NOT NULL, " +
                COL_PASSWORD + " TEXT NOT NULL)";

        String createTasksTable = "CREATE TABLE " + TABLE_TASKS + " (" +
                COL_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TASK_USER_ID + " INTEGER NOT NULL, " +
                COL_TASK_NAME + " TEXT NOT NULL, " +
                COL_TASK_NOTES + " TEXT, " +
                COL_COMPLETED + " INTEGER DEFAULT 0, " +
                "FOREIGN KEY (" + COL_TASK_USER_ID + ") REFERENCES " +
                TABLE_USERS + "(" + COL_USER_ID + "))";

        db.execSQL(createUsersTable);
        db.execSQL(createTasksTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // =========================
    // PASSWORD HASHING
    // =========================

    public static String hashPassword(String password) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // =========================
    // REGISTER USER
    // =========================

    public boolean registerUser(String name, String email, String password) {

        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();

            values.put(COL_NAME, name);
            values.put(COL_EMAIL, email);
            values.put(COL_PASSWORD, hashPassword(password));

            long result = db.insert(TABLE_USERS, null, values);

            return result != -1;
        }
    }

    // =========================
    // LOGIN USER
    // =========================

    public int loginUser(String email, String password) {

        String hashedPassword = hashPassword(password);

        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.query(
                     TABLE_USERS,
                     new String[]{COL_USER_ID},
                     COL_EMAIL + "=? AND " + COL_PASSWORD + "=?",
                     new String[]{email, hashedPassword},
                     null,
                     null,
                     null
             )) {
            if (cursor.moveToFirst()) {
                return cursor.getInt(
                        cursor.getColumnIndexOrThrow(COL_USER_ID)
                );
            }
        }

        return -1;
    }

    // =========================
    // GET USER NAME
    // =========================

    public String getUserName(int userId) {
        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.query(
                     TABLE_USERS,
                     new String[]{COL_NAME},
                     COL_USER_ID + "=?",
                     new String[]{userId + ""},
                     null,
                     null,
                     null
             )) {
            if (cursor.moveToFirst()) {
                return cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME)
                );
            }
        }
        return "User";
    }

    // =========================
    // ADD TASK
    // =========================

    public boolean addTask(
            int userId,
            String taskName,
            String notes
    ) {

        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();

            values.put(COL_TASK_USER_ID, userId);
            values.put(COL_TASK_NAME, taskName);
            values.put(COL_TASK_NOTES, notes);
            values.put(COL_COMPLETED, 0);

            long result = db.insert(
                    TABLE_TASKS,
                    null,
                    values
            );

            return result != -1;
        }
    }

    // =========================
    // GET USER TASKS
    // =========================

    public Cursor getTasks(int userId) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.query(
                TABLE_TASKS,
                null,
                COL_TASK_USER_ID + "=?",
                new String[]{userId + ""},
                null,
                null,
                COL_TASK_ID + " DESC"
        );
    }

    // =========================
    // UPDATE COMPLETED STATUS
    // =========================

    public void updateTaskStatus(
            int taskId,
            boolean completed
    ) {

        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();

            values.put(
                    COL_COMPLETED,
                    completed ? 1 : 0
            );

            db.update(
                    TABLE_TASKS,
                    values,
                    COL_TASK_ID + "=?",
                    new String[]{taskId + ""}
            );
        }
    }

    // =========================
    // DELETE TASK
    // =========================

    public void deleteTask(int taskId) {

        try (SQLiteDatabase db = this.getWritableDatabase()) {
            db.delete(
                    TABLE_TASKS,
                    COL_TASK_ID + "=?",
                    new String[]{taskId + ""}
            );
        }
    }
}
