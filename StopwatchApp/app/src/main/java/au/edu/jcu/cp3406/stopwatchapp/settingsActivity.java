package au.edu.jcu.cp3406.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class settingsActivity extends AppCompatActivity {

    private EditText speedSetting;
    public static final int SETTINGS_REQUEST = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        speedSetting = findViewById(R.id.speed);

    }

    public void onClickDone(View view) {
        try {
            Integer.parseInt(speedSetting.getText().toString());
        } catch (NumberFormatException e) {
            speedSetting.setText("1");
        }

        int speedSec = Integer.parseInt(speedSetting.getText().toString());
        int speedMili = speedSec*1000;

        Intent intent = new Intent();
        intent.putExtra("speed", speedMili);
        setResult(RESULT_OK, intent);
        finish();
    }
}
