package com.example.androidappdevelopment_task2_to_doappwithlogin;


public class Task {

    private final int id;
    private final String taskName;
    private final String notes;
    private boolean completed;

    public Task(
            int id,
            String taskName,
            String notes,
            boolean completed
    ) {
        this.id = id;
        this.taskName = taskName;
        this.notes = notes;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
