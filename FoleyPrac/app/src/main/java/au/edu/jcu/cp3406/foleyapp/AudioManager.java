package au.edu.jcu.cp3406.foleyapp;

import android.content.Context;
import android.media.SoundPool;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class AudioManager implements SoundPool.OnLoadCompleteListener {
    private Map<Sound, Integer> soundsIds;
    private SoundPool soundPool;
    private int loadId;
    private boolean isReady;

    AudioManager(Context context) {
        soundsIds = new HashMap<>();
        soundPool = new SoundPool(5, android.media.AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(this);

        soundPool.load(context, R.raw.bird, 0);
        soundPool.load(context, R.raw.cat, 0);
        soundPool.load(context, R.raw.chicks, 0);
        soundPool.load(context, R.raw.cow, 0);
        soundPool.load(context, R.raw.dog, 0);
        soundPool.load(context, R.raw.fish, 0);
        soundPool.load(context, R.raw.horse, 0);
        soundPool.load(context, R.raw.human, 0);
        soundPool.load(context, R.raw.lion, 0);
        soundPool.load(context, R.raw.monkey, 0);
        soundPool.load(context, R.raw.pig, 0);
        soundPool.load(context, R.raw.rooster, 0);

    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        this.isReady = status == 0;

        Sound sound = Sound.values()[loadId++];
        Log.i("AudioManager", "loaded sound: " + sound);
        soundsIds.put(sound, sampleId);
    }

    boolean isReady() {
        return isReady;
    }

    void play(Sound sound) {
        Integer id = soundsIds.get(sound);
        assert id != null;
        soundPool.play(id, 1, 1, 1, 0, 1);

    }
}
