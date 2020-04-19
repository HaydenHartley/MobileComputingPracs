package au.edu.jcu.cp3406.guesstheceleb;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class ImageManager {
    private String assetPath;
    private String[] imageNames;
    private AssetManager assetManager;

    ImageManager(AssetManager asset_manager, String asset_path) {
        assetManager = asset_manager;
        assetPath = asset_path;
        try {
            imageNames = assetManager.list(assetPath);
            System.out.println(Arrays.toString(imageNames));
        } catch (IOException e) {
            System.out.println("failed to get image names");
        }
    }

    Bitmap get(int i) {
        try {
            InputStream stream = assetManager.open(assetPath + "/" + imageNames[i]);
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            System.out.println("failed to get images");
            return null;
        }
    }
}
