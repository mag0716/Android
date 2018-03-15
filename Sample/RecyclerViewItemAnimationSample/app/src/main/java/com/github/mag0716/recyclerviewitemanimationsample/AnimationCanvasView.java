package com.github.mag0716.recyclerviewitemanimationsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by mag0716 on 2018/03/11.
 */

public class AnimationCanvasView extends View {

    private static final int FPS = 60;

    private Paint paint = new Paint();
    private RectF rect = new RectF();
    private Bitmap bitmap;
    private int bitmapSize;

    private long startTime;
    private long duration;

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

        long diff = System.currentTimeMillis() - startTime;
        float scale = 1f + ((float) (diff % duration) / (float) duration);
        calculateRect(scale);
        Log.d("Sample-Canvas#" + getTag(), "onDraw : scale = " + scale);

        canvas.drawBitmap(bitmap, null, rect, paint);

        postInvalidateDelayed(1000 / FPS);
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    private void init(@NonNull Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sample);
        bitmapSize = bitmap.getWidth();

        duration = AnimationHelper.DURATION;
    }

    private void calculateRect(float scale) {
        float size = bitmapSize * scale;
        rect.left = (2 * bitmapSize - size) / 2;
        rect.top = (2 * bitmapSize - size) / 2;
        rect.right = rect.left + size;
        rect.bottom = rect.top + size;
    }
}
