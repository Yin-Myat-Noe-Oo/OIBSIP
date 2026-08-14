# Stopwatch Application

## 📱 Project Overview

The **Stopwatch Application** is an Android application developed using **Java and XML in Android Studio**.

It provides an accurate stopwatch with Start, Stop/Pause, and Reset controls. The application updates the elapsed time continuously while the stopwatch is running.

## 🎯 Objective

The objective is to build a functional stopwatch that can:

* Start timing
* Pause timing
* Resume timing
* Reset the timer
* Display elapsed time clearly
* Handle Android activity lifecycle events
* Prevent multiple timers from running simultaneously

## 🛠️ Technologies Used

* Android Studio
* Java
* XML
* Android SDK
* `Handler`
* `Runnable`
* `SystemClock`
* Activity Lifecycle
* `TextView`
* `Button`

## ✨ Features

### ⏱️ Time Display

The stopwatch displays elapsed time in:

```text
HH:MM:SS
```

Example:

```text
00:05:27
```

### ▶️ Start

Pressing **Start** begins the stopwatch.

If the stopwatch was paused, pressing Start resumes it from the previous elapsed time.

### ⏸️ Stop/Pause

Pressing **Stop/Pause** freezes the current elapsed time.

The recorded time is not lost.

### 🔄 Reset

The Reset button:

* Stops the stopwatch.
* Clears the elapsed time.
* Returns the display to:

```text
00:00:00
```

### 🔘 Button States

The application changes button states according to the stopwatch status.

For example:

```text
Stopped:
Start → Active
Stop → Disabled

Running:
Start → Disabled
Stop → Active
```

### 📱 Lifecycle Handling

The stopwatch is designed to handle Android activity lifecycle events.

When the user navigates away and returns to the application, the elapsed time is calculated using a stored timing reference rather than relying only on repeated UI updates.

This helps prevent the timer from becoming inaccurate when the Activity is paused.

### 🏁 Lap Functionality

As a bonus feature, the application can include a **Lap** button.

Each lap records the current elapsed time and displays it in a scrollable list.

Example:

```text
Lap 1     00:00:12
Lap 2     00:00:25
Lap 3     00:00:41
```

## 📂 Suggested Project Structure

```text
Stopwatch/
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
│   ├── stopwatch-start.png
│   ├── stopwatch-running.png
│   ├── stopwatch-paused.png
│   └── stopwatch-laps.png
└── README.md
```

## ▶️ How to Run

1. Open the project in Android Studio.
2. Allow Gradle synchronization to finish.
3. Start an Android emulator or connect an Android device.
4. Click **Run ▶**.
5. Press **Start**.
6. Press **Stop/Pause** to freeze the timer.
7. Press **Start** again to resume.
8. Press **Reset** to return to zero.
9. If implemented, press **Lap** to record lap times.

## 🧪 Testing

Test the following scenarios:

* Start from zero
* Pause the stopwatch
* Resume after pause
* Reset while running
* Reset while paused
* Rapidly press Start
* Rapidly press Stop
* Navigate away and return
* Verify that elapsed time remains accurate
* Test Lap functionality if implemented

## 📸 Screenshots

Recommended screenshots:

* Initial stopwatch screen
* Running stopwatch
* Paused stopwatch
* Reset stopwatch
* Lap list

## 📚 Learning Concepts

This project demonstrates:

* `Handler`
* `Runnable`
* UI thread updates
* `SystemClock`
* Android Activity Lifecycle
* `onPause()`
* `onResume()`
* Button event handling
* Time calculations
* Scrollable lists

## 👩‍💻 Author

**Yin Myat Noe Oo**

Android Development — Oasis Infobyte Internship
