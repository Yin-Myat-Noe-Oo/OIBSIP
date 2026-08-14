# Calculator Application

## 📱 Project Overview

The **Calculator Application** is a simple Android calculator developed using **Java and XML in Android Studio**.

The application provides a clean button-grid interface for performing basic arithmetic calculations.

## 🎯 Objective

The objective of this project is to build a functional Android calculator that supports:

* Addition
* Subtraction
* Multiplication
* Division
* Decimal numbers
* Clear functionality
* Backspace functionality
* Division-by-zero error handling

## 🛠️ Technologies Used

* Android Studio
* Java
* XML
* Android SDK
* TextView
* Button
* GridLayout
* OnClickListener
* StringBuilder

## ✨ Features

### 🔢 Number Input

The calculator provides buttons for:

```text
0 1 2 3 4 5 6 7 8 9
```

It also supports decimal values.

### ➕ Arithmetic Operators

Supported operators:

```text
+
−
×
÷
```

### 🟰 Equals

The `=` button evaluates the current expression and displays the result.

### 🧹 Clear

The `C` button clears the current expression and resets the calculator.

### ⌫ Backspace

The Backspace button removes the last character from the current input.

### ⚠️ Division by Zero

The calculator handles invalid division operations safely.

Example:

```text
10 ÷ 0
```

Result:

```text
Error
```

The application does not crash.

### 📱 Responsive Button Grid

The calculator interface uses an XML button grid to organize the controls.

## 📂 Suggested Project Structure

```text
Calculator/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── MainActivity.java
│           └── res/
│               ├── layout/
│               │   └── activity_main.xml
│               ├── drawable/
│               └── values/
├── screenshots/
│   ├── calculator.png
│   ├── addition.png
│   └── error.png
└── README.md
```

## ▶️ How to Run

1. Open the project in Android Studio.
2. Wait for Gradle synchronization.
3. Start an Android emulator or connect a physical device.
4. Click **Run ▶**.
5. Enter numbers and operators.
6. Press `=` to calculate the result.

## 🧪 Example

```text
Input:
25 + 15

Result:
40
```

Another example:

```text
Input:
10 ÷ 0

Result:
Error
```

## 🧪 Testing

Test the application with:

* Addition
* Subtraction
* Multiplication
* Division
* Decimal numbers
* Multiple operators
* Clear
* Backspace
* Division by zero
* Rapid/repeated button taps
* Empty expression

## 📸 Screenshots

Recommended screenshots:

* Calculator home screen
* Arithmetic calculation
* Decimal calculation
* Division-by-zero error
* Backspace functionality

## 📚 Learning Concepts

This project demonstrates:

* XML layouts
* `GridLayout`
* `TextView`
* `Button`
* `OnClickListener`
* `StringBuilder`
* Expression handling
* Input validation
* Error handling

## 👩‍💻 Author

**Yin Myat Noe Oo**

Android Development — Oasis Infobyte Internship
