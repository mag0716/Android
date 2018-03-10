package com.github.mag0716.recyclerviewitemanimationsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by mag0716 on 2018/03/11.
 */

public class AnimationCanvasView extends View {

    private Paint paint = new Paint();
    private Bitmap bitmap;
    private float offset;

    public AnimationCanvasView(Context context) {
        super(context);
        init(context);
    }

    public AnimationCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimationCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, offset, offset, paint);
    }

    private void init(@NonNull Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        offset = 24 * metrics.density;
    }
}
