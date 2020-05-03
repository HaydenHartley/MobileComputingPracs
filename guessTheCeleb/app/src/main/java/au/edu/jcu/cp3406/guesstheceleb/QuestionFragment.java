package au.edu.jcu.cp3406.guesstheceleb;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import au.edu.jcu.cp3406.guesstheceleb.game.Game;


public class QuestionFragment extends Fragment {
    private StateListener listener;
    private Game currentGame;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    public void setGame(Game game) {
        this.currentGame = game;


    }

    public String getScore() {
        return currentGame.getScore();
    }

    public void showNextQuestion() {
        currentGame.next();
    }

}
