package au.edu.jcu.cp3406.reflexchecker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private Random random = new Random();
    private static final int[] drawables = {
            R.drawable.baseline_fastfood_black_48,
            R.drawable.baseline_local_cafe_black_48,
            R.drawable.baseline_local_bar_black_48,
            R.drawable.baseline_local_grocery_store_black_48
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setupDescription(R.id.task1, R.array.task1_descriptions);
        setupDescription(R.id.task2, R.array.task2_descriptions);

        for (int i = 0; i < 5; i++) {
            addImage();
            int arrayID = getRandomTask();
            addCheckboxes(arrayID);
        }
    }

    private void setupDescription(int taskID, int arrayID) {
        TextView task = findViewById(taskID);
        String[] descriptions = getResources().getStringArray(arrayID);

        int i = random.nextInt(descriptions.length);
        task.setText(descriptions[i]);
    }

    private void addImage() {
        ViewGroup gameRows = findViewById(R.id.game_rows);
        getLayoutInflater().inflate(R.layout.image, gameRows);

        View lastChild = gameRows.getChildAt(gameRows.getChildCount() - 1);
        ImageView image = lastChild.findViewById(R.id.image);

        int index = random.nextInt(drawables.length);
        image.setImageDrawable(getResources().getDrawable(drawables[index]));
    }

    private int getRandomTask() {
        int i = random.nextInt(2);
        if (i == 0) {
            return R.array.drinks;
        } else {
            return R.array.fruits;
        }
    }

    private boolean getRandomBool() {
        int i = random.nextInt(2);
        if (i == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void addCheckboxes(int arrayID) {
        ViewGroup gameRows = findViewById(R.id.game_rows);
        getLayoutInflater().inflate(R.layout.checkboxes, gameRows);

        View lastChild = gameRows.getChildAt(gameRows.getChildCount() - 1);
        TableRow checkboxes = lastChild.findViewById(R.id.checkboxes);

        String[] items = getResources().getStringArray(arrayID);
        List<String> itemsList = new ArrayList<>(Arrays.asList(items));
        for (int i = 0; i < checkboxes.getChildCount(); i++) {
            CheckBox box = (CheckBox) checkboxes.getChildAt(i);
            int r = random.nextInt(itemsList.size());
            box.setText(itemsList.get(r));
            try {
                itemsList.remove(r);
            } catch (Exception e) {
                continue;
            }

            box.setChecked(getRandomBool());

        }

    }

    public void endTest(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
