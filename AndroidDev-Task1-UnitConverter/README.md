# Unit Converter Application

## 📱 Project Overview

The **Unit Converter Application** is an Android mobile application developed using **Java and XML in Android Studio**. The application allows users to convert numerical values between different units of measurement.

The app provides multiple measurement categories, including **Length, Weight, and Volume**, with a simple and user-friendly interface.

## 🎯 Objective

The main objective of this project is to build an Android application that can:

* Accept a numerical value from the user.
* Allow the user to select a measurement category.
* Allow the user to select a source unit and target unit.
* Convert the value using the appropriate conversion formula.
* Display the converted result with the corresponding unit.
* Validate user input and display helpful error messages.

## 🛠️ Technologies Used

* Android Studio
* Java
* XML
* Android SDK
* Spinner
* EditText
* TextView
* Button
* Toast

## ✨ Features

### 1. Numeric Input

Users can enter the value they want to convert using an input field.

### 2. Measurement Categories

The application supports at least three categories:

* **Length**

  * Centimetres
  * Metres
  * Kilometres
  * Inches
  * Feet

* **Weight**

  * Grams
  * Kilograms
  * Pounds
  * Ounces

* **Volume**

  * Millilitres
  * Litres
  * Gallons

### 3. Source and Target Units

Two Spinner controls allow users to select:

* Source unit
* Target unit

The available units are automatically updated when the measurement category changes.

### 4. Conversion

The **Convert** button calculates the converted value and displays the result.

### 5. Input Validation

If the input field is:

* Empty
* Non-numeric
* Invalid

the application displays a Toast message asking the user to enter a valid number.

### 6. Result Display

The result is displayed with the target unit label.

Example:

```text
100 Centimetres
= 1 Metre
```

## 📂 Suggested Project Structure

```text
UnitConverter/
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
│   ├── home.png
│   ├── length.png
│   ├── weight.png
│   └── volume.png
└── README.md
```

## ▶️ How to Run

1. Open Android Studio.
2. Select **Open Project**.
3. Select the Unit Converter project.
4. Allow Gradle synchronization to finish.
5. Connect an Android device or start an emulator.
6. Click **Run ▶**.
7. Select a measurement category.
8. Enter a value.
9. Select source and target units.
10. Press **Convert**.

## 🧪 Example Conversions

```text
100 cm → 1 m
1 kg → 1000 g
1 litre → 1000 ml
```

## 📸 Screenshots

Add screenshots of the following:

* Main screen
* Length conversion
* Weight conversion
* Volume conversion
* Input validation message
* Conversion result

## 📚 Learning Resources

The project was developed using Android development concepts including:

* `Spinner`
* `EditText`
* `TextView`
* `Button`
* `Toast`
* XML layouts
* Java event handling

## 👩‍💻 Author

**Yin Myat Noe Oo**

Android Development — Oasis Infobyte Internship
