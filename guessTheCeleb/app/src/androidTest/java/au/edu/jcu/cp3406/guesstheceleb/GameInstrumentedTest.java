package au.edu.jcu.cp3406.guesstheceleb;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import au.edu.jcu.cp3406.guesstheceleb.game.CelebrityManager;
import au.edu.jcu.cp3406.guesstheceleb.game.Game;
import au.edu.jcu.cp3406.guesstheceleb.game.GameBuilder;
import au.edu.jcu.cp3406.guesstheceleb.game.Question;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class GameInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("au.edu.jcu.cp3406.guesstheceleb", appContext.getPackageName());
    }

    @Test
    public void testGameBuilder() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AssetManager assetManager = context.getAssets();
        CelebrityManager celebrityManager = new CelebrityManager(assetManager, "celebs");

        GameBuilder gameBuilder = new GameBuilder(celebrityManager);
        Game game = gameBuilder.create(Difficulty.EXPERT);

        int correctlyAnswered = 0;
        loop:
        while (!game.isGameOver()) {
            Question question = game.next();
            for (int i = 0; i < celebrityManager.count(); ++i) {
                Log.i("GameInstrumentedTest", celebrityManager.getName(i));
                if (question.check(celebrityManager.getName(i))) {
                    ++correctlyAnswered;
                    continue loop;
                }
            }
            fail("didn't answer question correctly");
        }
        assertEquals(game.count(), correctlyAnswered);
    }
}
