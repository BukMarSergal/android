package com.example.bigprace;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity2 extends AppCompatActivity {

    private ProgressBar simpleProgressBar;
    private ProgressBar simpleProgressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progres);

        // Find ProgressBar views
        simpleProgressBar = findViewById(R.id.progressBar);
        simpleProgressBar2 = findViewById(R.id.progressBar2);

        // Hide progress bars initially
        simpleProgressBar.setVisibility(View.VISIBLE);
        simpleProgressBar2.setVisibility(View.INVISIBLE);

        // First CountDownTimer for simpleProgressBar
        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Do something on tick if needed
            }
            public void onFinish() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        simpleProgressBar.setVisibility(View.INVISIBLE);
                        startSecondCountDownTimer(); // Start the second countdown timer
                        simpleProgressBar2.setVisibility(View.VISIBLE);
                    }
                });
            }
        }.start();
    }

    private void startSecondCountDownTimer() {
        // Second CountDownTimer for simpleProgressBar2
        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Do something on tick if needed
            }
            public void onFinish() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startMainActivity3(); // Start MainActivity3 after both timers finish
                    }
                });
            }
        }.start();

    }

    private void startMainActivity3() {
        // Start MainActivity3 after both countdown timers finish
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(intent);
        finish(); // Finish MainActivity2 to prevent going back to it with Back button
    }

    public void svapovac(View view) {
        // Handle button click to switch to MainActivity
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish MainActivity2 to prevent going back to it with Back button
    }

    public void first(View view) {
        // Handle button click to show the first progress bar
        simpleProgressBar.setVisibility(View.VISIBLE);
    }

    public void second(View view) {
        // Handle button click to show the second progress bar
        simpleProgressBar2.setVisibility(View.VISIBLE);
    }
}
