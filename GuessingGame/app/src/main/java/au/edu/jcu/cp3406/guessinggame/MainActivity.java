package au.edu.jcu.cp3406.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkGuess(View view){
        textGuess = findViewById(R.id.guess);
        Game game = new Game();
        try {
            Integer.parseInt(textGuess.getText().toString());
        } catch (NumberFormatException e) {
            textGuess.setText("0");
        }
        int guess = Integer.parseInt(textGuess.getText().toString());
        if(game.check(guess)){
            Toast.makeText(getApplicationContext(), "Correct Guess!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong Guess!", Toast.LENGTH_SHORT).show();
        }

    }

    public void clearText(View view){
        textGuess = findViewById(R.id.guess);
        textGuess.setText("");
    }



}
