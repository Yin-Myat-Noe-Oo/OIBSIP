# To-Do App with Login

## 📱 Project Overview

The **To-Do App with Login** is an Android task management application developed using **Java, XML, and SQLite**.

Users must create an account and log in before accessing their personal task list. Tasks are stored locally in an SQLite database and are associated with the corresponding user account.

## 🎯 Objective

The main objective is to develop a secure local task management application that supports:

* User registration
* User login
* Password hashing
* User-specific tasks
* Adding tasks
* Completing tasks
* Deleting tasks
* Logout
* Local SQLite persistence

## 🛠️ Technologies Used

* Android Studio
* Java
* XML
* Android SDK
* SQLite
* SQLiteOpenHelper
* SharedPreferences/session management
* RecyclerView or ListView

## ✨ Features

### 1. Login

Users can enter:

* Email/username
* Password

Valid credentials allow the user to access the main task screen.

### 2. Sign Up

New users can register using:

* Name
* Email
* Password

The password is hashed before being stored in the SQLite database rather than storing it as plain text.

### 3. Personal Task List

After login, users can view their own tasks.

Tasks belonging to another user are not displayed.

### 4. Add Task

Users can create a task containing:

* Task name
* Optional notes

### 5. Complete Task

Users can mark tasks as completed.

Completed tasks are visually distinguished using a strikethrough or completed-state indicator.

### 6. Delete Task

Users can permanently remove tasks from the database.

### 7. Logout

The Logout button:

* Clears the current session.
* Returns the user to the Login screen.
* Prevents access to the task list without authentication.

### 8. Empty State

When a user has no tasks, the application displays a friendly message such as:

```text
No tasks yet.
Add your first task!
```

## 🗄️ Database Design

The SQLite database contains user and task information.

### Users Table

```text
users
--------------------------------
id
name
email
password_hash
```

### Tasks Table

```text
tasks
--------------------------------
id
user_id
task_name
notes
completed
```

The `user_id` field associates every task with its owner.

## 📂 Suggested Project Structure

```text
TodoApp/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   ├── LoginActivity.java
│           │   ├── SignupActivity.java
│           │   ├── MainActivity.java
│           │   ├── AddTaskActivity.java
│           │   ├── DatabaseHelper.java
│           │   └── TaskAdapter.java
│           └── res/
│               ├── layout/
│               ├── drawable/
│               └── values/
├── screenshots/
│   ├── login.png
│   ├── signup.png
│   ├── task-list.png
│   └── add-task.png
└── README.md
```

## ▶️ How to Run

1. Open the project in Android Studio.
2. Allow Gradle synchronization to complete.
3. Start an Android emulator or connect a physical Android device.
4. Run the application.
5. Create a new account.
6. Log in using the registered credentials.
7. Add and manage tasks.

## 🔐 Security

Passwords should **not be stored as plain text**.

A basic hashing mechanism is used before storing passwords in SQLite.

> Note: For production applications, modern password hashing algorithms such as Argon2, scrypt, or PBKDF2 with a unique salt are preferable to simple MD5 hashing.

## 🧪 Testing

The following scenarios should be tested:

* Valid registration
* Duplicate email registration
* Empty registration fields
* Invalid login
* Valid login
* Adding a task
* Completing a task
* Deleting a task
* Empty task list
* Logout
* Login after logout
* Persistence after restarting the application

## 📸 Screenshots

Include screenshots showing:

* Login screen
* Sign-up screen
* Main task list
* Add Task dialog/screen
* Completed task
* Empty task state
* Logout/login flow

## 📚 Learning Resources

The project demonstrates:

* `SQLiteOpenHelper`
* SQLite CRUD operations
* Android Activities
* `EditText`
* `Button`
* `RecyclerView`
* `Toast`
* Password hashing
* Session management

## 👩‍💻 Author

**Yin Myat Noe Oo**

Android Development — Oasis Infobyte Internship
