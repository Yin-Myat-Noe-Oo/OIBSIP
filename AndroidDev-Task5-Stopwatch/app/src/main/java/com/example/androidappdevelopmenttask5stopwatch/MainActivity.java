package com.example.androidappdevelopmenttask5stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer, tvMilliseconds;
    private Button btnStart, btnPause, btnReset, btnLap;
    private ListView lvLaps;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updateTime = 0L;
    private boolean isRunning = false;

    private ArrayList<String> lapList;
    private ArrayAdapter<String> adapter;

    private final Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            updateDisplay(updateTime);
            handler.postDelayed(this, 10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTimer = findViewById(R.id.tvTimer);
        tvMilliseconds = findViewById(R.id.tvMilliseconds);
        btnStart = findViewById(R.id.btnStart);
        btnPause = findViewById(R.id.btnPause);
        btnReset = findViewById(R.id.btnReset);
        btnLap = findViewById(R.id.btnLap);
        lvLaps = findViewById(R.id.lvLaps);

        lapList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lapList);
        lvLaps.setAdapter(adapter);

        if (savedInstanceState != null) {
            timeSwapBuff = savedInstanceState.getLong("timeSwapBuff");
            isRunning = savedInstanceState.getBoolean("isRunning");
            if (isRunning) {
                startTime = savedInstanceState.getLong("startTime");
                handler.postDelayed(updateTimerThread, 0);
                setRunningUI(true);
            } else {
                updateDisplay(timeSwapBuff);
                setRunningUI(false);
            }
        }

        btnStart.setOnClickListener(v -> {
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(updateTimerThread, 0);
            isRunning = true;
            setRunningUI(true);
        });

        btnPause.setOnClickListener(v -> {
            timeSwapBuff += timeInMilliseconds;
            handler.removeCallbacks(updateTimerThread);
            isRunning = false;
            setRunningUI(false);
        });

        btnReset.setOnClickListener(v -> {
            startTime = 0L;
            timeInMilliseconds = 0L;
            timeSwapBuff = 0L;
            updateTime = 0L;
            isRunning = false;
            handler.removeCallbacks(updateTimerThread);
            tvTimer.setText("00:00:00");
            tvMilliseconds.setText(".000");
            setRunningUI(false);
            lapList.clear();
            adapter.notifyDataSetChanged();
            btnLap.setEnabled(false);
        });

        btnLap.setOnClickListener(v -> {
            String currentTime = tvTimer.getText().toString() + tvMilliseconds.getText().toString();
            lapList.add(0, "Lap " + (lapList.size() + 1) + " : " + currentTime);
            adapter.notifyDataSetChanged();
        });
    }

    private void updateDisplay(long time) {
        int secs = (int) (time / 1000);
        int mins = secs / 60;
        int hrs = mins / 60;
        secs = secs % 60;
        mins = mins % 60;
        int milliseconds = (int) (time % 1000);
        
        tvTimer.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", hrs, mins, secs));
        tvMilliseconds.setText(String.format(Locale.getDefault(), ".%03d", milliseconds));
    }

    private void setRunningUI(boolean running) {
        if (running) {
            btnStart.setVisibility(View.GONE);
            btnPause.setVisibility(View.VISIBLE);
            btnPause.setEnabled(true);
            btnLap.setEnabled(true);
        } else {
            btnStart.setVisibility(View.VISIBLE);
            btnPause.setVisibility(View.GONE);
            btnLap.setEnabled(false);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("timeSwapBuff", timeSwapBuff);
        outState.putLong("startTime", startTime);
        outState.putBoolean("isRunning", isRunning);
    }
}
