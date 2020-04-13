package au.edu.jcu.cp3406.stopwatchapp;

import java.util.Locale;

public class Stopwatch {

    private int seconds;

    public Stopwatch() {
        seconds = 0;
    }

    public Stopwatch(int initialSeconds) {
        seconds = initialSeconds;
    }

    public String toString() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, secs);
        return time;
    }

    public int getSeconds(){
        return seconds;
    }

    public void tick() {
        seconds++;
    }

    public void reset() {
        seconds = 0;
    }
}
