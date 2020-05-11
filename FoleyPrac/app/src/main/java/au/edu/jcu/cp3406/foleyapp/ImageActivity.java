package au.edu.jcu.cp3406.foleyapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ImageActivity extends AppCompatActivity {

    public static final int REQUEST = 1234;
    String animal;
    Sound sound;
    int image;
    ImageView display;
    AudioManager audioManager;
    ImageManager imageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        audioManager = new AudioManager(this);
        imageManager = new ImageManager(this.getAssets(), "animals");

        Intent intent = getIntent();
        animal = intent.getStringExtra("animal");

        //BIRD, CAT, CHICKS, COW, DOG, FISH, HORSE, HUMAN, LION, MONKEY, PIG, ROOSTER
        if (animal != null) {
            switch (animal) {
                case "bird":
                    sound = Sound.BIRD;
                    image = 0;
                    break;
                case "cat":
                    sound = Sound.CAT;
                    image = 1;
                    break;
                case "chicks":
                    sound = Sound.CHICKS;
                    image = 2;
                    break;
                case "cow":
                    sound = Sound.COW;
                    image = 3;
                    break;
                case "dog":
                    sound = Sound.DOG;
                    image = 4;
                    break;
                case "fish":
                    sound = Sound.FISH;
                    image = 5;
                    break;
                case "horse":
                    sound = Sound.HORSE;
                    image = 6;
                    break;
                case "human":
                    sound = Sound.HUMAN;
                    image = 7;
                    break;
                case "lion":
                    sound = Sound.LION;
                    image = 8;
                    break;
                case "monkey":
                    sound = Sound.MONKEY;
                    image = 9;
                    break;
                case "pig":
                    sound = Sound.PIG;
                    image = 10;
                    break;
                case "rooster":
                    sound = Sound.ROOSTER;
                    image = 11;
                    break;
            }

            display = findViewById(R.id.display);
            display.setImageBitmap(imageManager.get(image));

        } else {
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Return", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        String action = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "started";
                if (audioManager.isReady()) {
                    audioManager.play(sound);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                action = "moved";
                break;
            case MotionEvent.ACTION_UP:
                action = "ended";
                break;
        }
        Log.i("TouchableActivity", String.format(Locale.getDefault(), "%.2f %.2f %s", x, y, action));
        return true;
    }
}
