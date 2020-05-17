package au.edu.jcu.cp3406.spiritlevel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SpiritLevelView extends View {
    int x_position, y_position;

    public SpiritLevelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SpiritLevelViewAttrs);
        x_position = attributes.getDimensionPixelSize(R.styleable.SpiritLevelViewAttrs_x_position, 0);
        y_position = attributes.getDimensionPixelSize(R.styleable.SpiritLevelViewAttrs_y_position, 0);
        attributes.recycle();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(x_position, y_position, 1, new Paint());
    }
}
