package au.edu.jcu.cp3406.guesstheceleb;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class GameFragment extends Fragment {
    private StateListener listener;
    private Difficulty level;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
    }

    public Difficulty getLevel() {
        return level;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_game, container, false);
        final Spinner spinner = view.findViewById(R.id.difficulty);

        //handle button click by triggering state listener update
        view.findViewById(R.id.play);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = spinner.getSelectedItem().toString();
                Log.i("GameFragment", "selection: " + selection);
                level = Difficulty.valueOf(selection.toUpperCase());
                listener.onUpdate(State.START_GAME);
            }
        });

        return view;
    }

}
