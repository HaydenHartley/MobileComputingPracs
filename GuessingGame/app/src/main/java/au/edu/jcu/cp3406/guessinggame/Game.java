package au.edu.jcu.cp3406.guessinggame;

import java.util.Random;

public class Game {

    Random rand = new Random();
    int luckyNum = rand.nextInt(10) + 1;

    public void newNum() {
        int luckyNum = rand.nextInt(10) + 1;
    }

    public boolean check(int i){
        return i == luckyNum;
    }

}
