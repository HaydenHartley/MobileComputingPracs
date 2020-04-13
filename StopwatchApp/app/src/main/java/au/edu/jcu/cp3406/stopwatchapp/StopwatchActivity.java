package au.edu.jcu.cp3406.stopwatchapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.os.Handler;
import android.os.Bundle;

public class StopwatchActivity extends AppCompatActivity {

    private Handler handler;
    private boolean isRunning;
    private TextView display;
    private Button toggle;
    private int speed = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        toggle = findViewById(R.id.toggle);

        isRunning = false;
        if (savedInstanceState == null) {
            stopwatch = new Stopwatch();
        } else {
            int currentTime = savedInstanceState.getInt("currentTime");
            stopwatch = new Stopwatch(currentTime);
            boolean isRunning = savedInstanceState.getBoolean("running");
            if (isRunning) {
                onClickStart();
            }
        }
        display.setText(stopwatch.toString());
        checkToggleButton();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("timer", stopwatch.toString());
        outState.putBoolean("running", isRunning);
        outState.putInt("currentTime", stopwatch.getSeconds());
    }

    public void onClickSettings(View view) {
        Intent intent = new Intent(this, settingsActivity.class);
        startActivityForResult(intent, settingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == settingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    speed = data.getIntExtra("speed", 1000);
                }
            }
        }
    }

    Stopwatch stopwatch = new Stopwatch();

    private void onClickStart() {
        isRunning = true;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    stopwatch.tick();
                    display.setText(stopwatch.toString());
                    handler.postDelayed(this, speed);
                }
            }
        });
    }

    private void onClickStop() {
        isRunning = false;
    }

    public void onClickToggle(View view) {
        if (isRunning) {
            onClickStop();
            toggle.setText("START");
        } else {
            onClickStart();
            toggle.setText("STOP");
        }
    }

    public void onClickReset(View view) {
        isRunning = false;
        stopwatch.reset();
        display.setText(stopwatch.toString());
        checkToggleButton();
    }

    public void checkToggleButton() {
        if (isRunning) {
            toggle.setText("STOP");
        } else {
            toggle.setText("START");
        }
    }

}
