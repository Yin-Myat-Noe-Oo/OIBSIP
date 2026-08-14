# Quiz Application

## 📱 Project Overview

The **Quiz Application** is an interactive Android multiple-choice quiz developed using **Java and XML** in Android Studio.

Users answer a series of multiple-choice questions, receive immediate feedback, and see their final score at the end of the quiz.

## 🎯 Objective

The application is designed to provide an interactive quiz experience with:

* A welcome screen
* Multiple-choice questions
* Four answer options
* Immediate answer feedback
* Score tracking
* Question navigation
* Final results
* Quiz restart functionality
* Shuffled questions

## 🛠️ Technologies Used

* Android Studio
* Java
* XML
* Android SDK
* RadioGroup
* RadioButton
* Intent
* Bundle
* ArrayList
* Collections/Randomization

## ✨ Features

### 🏠 Welcome Screen

The application starts with a welcome screen containing a **Start Quiz** button.

### ❓ Questions

The quiz contains at least **10 questions**.

Each question includes:

* Question text
* Four answer choices
* Question counter

Example:

```text
Question 3 of 10
```

### ✅ Immediate Feedback

After selecting an answer:

* Correct answer → highlighted green
* Incorrect answer → highlighted red

The user can immediately see whether the selected answer was correct.

### ➡️ Next Button

The Next button moves the user to the following question.

### 🏆 Score Tracking

The application tracks the number of correct answers throughout the quiz.

### 📊 Results Screen

At the end of the quiz, the application displays:

```text
Total Questions: 10
Correct: 7
Incorrect: 3
Score: 70%
```

A **Restart Quiz** button allows the user to play again.

### 🔀 Question Shuffling

Questions are shuffled so that the quiz can appear in a different order each time.

## 📂 Suggested Project Structure

```text
QuizApp/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   ├── MainActivity.java
│           │   ├── QuizActivity.java
│           │   └── ResultActivity.java
│           └── res/
│               ├── layout/
│               │   ├── activity_main.xml
│               │   ├── activity_quiz.xml
│               │   └── activity_result.xml
│               ├── drawable/
│               └── values/
├── screenshots/
│   ├── welcome.png
│   ├── question.png
│   ├── feedback.png
│   └── results.png
└── README.md
```

## ▶️ How to Run

1. Open the project in Android Studio.
2. Synchronize the Gradle files.
3. Start an Android emulator or connect an Android device.
4. Run the application.
5. Press **Start Quiz**.
6. Select an answer.
7. Press **Next**.
8. Continue until all questions are answered.
9. View the final score.
10. Press **Restart Quiz** to play again.

## 🧪 Example Question

```text
What is the largest planet in our Solar System?

A. Earth
B. Mars
C. Jupiter
D. Venus
```

Correct answer:

```text
C. Jupiter
```

## 🧪 Testing

Test the following:

* Start button
* Question display
* Four answer options
* Correct answer feedback
* Incorrect answer feedback
* Next button
* Score calculation
* Question counter
* Final result
* Restart functionality
* Question shuffling

## 📸 Screenshots

Recommended screenshots:

* Welcome screen
* Question screen
* Correct answer feedback
* Wrong answer feedback
* Final results screen

## 📚 Learning Concepts

This project demonstrates:

* `RadioGroup`
* `RadioButton`
* `Intent`
* `Bundle`
* Java arrays/ArrayLists
* Question randomization
* Event listeners
* Activity navigation
* Score calculation

## 👩‍💻 Author

**Yin Myat Noe Oo**

Android Development — Oasis Infobyte Internship
