package au.edu.jcu.cp3406.guesstheceleb.game;

import java.util.Locale;

public class Game {
    private Question[] questions;
    private int questionNumber;
    private int score;

    public Game(Question[] questions) {
        this.questions = questions;
        this.questionNumber = -1;
        this.score = 0;
    }

    public String getScore() {
        return (String.format(Locale.getDefault(), "Score: %d/%d", score, questions.length));
    }

    public boolean isGameOver() {
        return questionNumber + 1 == questions.length;
    }

    public Question next() {
        questionNumber++;
        return questions[questionNumber];
    }

    public void updateScore(boolean correct) {
        if (correct) {
            score += 1;
        }
    }

    public int count() {
        return questions.length;
    }
}
