package au.edu.jcu.cp3406.guesstheceleb;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.Locale;

import au.edu.jcu.cp3406.guesstheceleb.game.CelebrityManager;
import au.edu.jcu.cp3406.guesstheceleb.game.Game;
import au.edu.jcu.cp3406.guesstheceleb.game.GameBuilder;

public class MainActivity extends AppCompatActivity implements StateListener {
    GameBuilder gameBuilder;
    private GameFragment gameFragment;
    private StatusFragment statusFragment;
    private QuestionFragment questionFragment;
    private boolean isLargeScreen;
    private StateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CelebrityManager celebrityManager = new CelebrityManager(this.getAssets(), "celebs");
        gameBuilder = new GameBuilder(celebrityManager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        gameFragment = (GameFragment) fragmentManager.findFragmentById(R.id.game);
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.status);
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.question);
        isLargeScreen = statusFragment != null;
    }

    @Override
    public void onUpdate(State state) {
        Difficulty level = gameFragment.getLevel();
        String text = String.format(Locale.getDefault(), "state: %s level: %s", state, level);
        Log.i("MainActivity", text);

        if (isLargeScreen) {
            switch (state) {
                case START_GAME:
                    Game game = gameBuilder.create(level);
                    questionFragment.setGame(game);
                    break;
                case CONTINUE_GAME:
                    statusFragment.setScore(questionFragment.getScore());
                    break;
                case GAME_OVER:
                    statusFragment.setScore(questionFragment.getScore());
                    statusFragment.setMessage("Game over!");
                    break;
            }
        } else {

        }
    }

}
