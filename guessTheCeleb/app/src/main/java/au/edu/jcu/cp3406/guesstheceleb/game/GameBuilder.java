package au.edu.jcu.cp3406.guesstheceleb.game;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import au.edu.jcu.cp3406.guesstheceleb.Difficulty;


public class GameBuilder {
    private CelebrityManager celebrityManager;
    private Random random = new Random();

    public GameBuilder(CelebrityManager celebrityManager) {
        this.celebrityManager = celebrityManager;
    }

    public Game create(Difficulty level) {
        int numberOfQuestions;
        switch (level) {
            case EASY:
                numberOfQuestions = 3;
                break;
            case MEDIUM:
                numberOfQuestions = 6;
                break;
            case HARD:
                numberOfQuestions = 12;
                break;
            case EXPERT:
                numberOfQuestions = 24;
                break;
            default:
                numberOfQuestions = 3;
        }
        List<Question> questionsList = new ArrayList<>();
        List<String> celebrityNames = new ArrayList<>();
        List<Bitmap> celebrityImages = new ArrayList<>();
        for (int i = 0; i < celebrityManager.count(); ++i) {
            celebrityNames.add(celebrityManager.getName(i));
            celebrityImages.add(celebrityManager.get(i));
        }
        String[] possibleNames = new String[celebrityNames.size()];
        celebrityNames.toArray(possibleNames);
        for (int i = 0; i < numberOfQuestions; ++i) {
            int randomNum = random.nextInt(celebrityNames.size());
            Question question = new Question(celebrityNames.get(randomNum), celebrityImages.get(randomNum), possibleNames);
            questionsList.add(question);
            celebrityNames.remove(randomNum);
            celebrityImages.remove(randomNum);
        }
        Question[] questions = new Question[questionsList.size()];
        questionsList.toArray(questions);
        return new Game(questions);
    }
}
