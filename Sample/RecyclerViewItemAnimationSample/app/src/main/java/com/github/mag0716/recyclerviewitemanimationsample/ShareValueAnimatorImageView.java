package com.github.mag0716.recyclerviewitemanimationsample;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by kishimoto on 2018/03/19.
 */
public class ShareValueAnimatorImageView extends AppCompatImageView implements ValueAnimator.AnimatorUpdateListener {

    public ShareValueAnimatorImageView(Context context) {
        super(context);
    }

    public ShareValueAnimatorImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShareValueAnimatorImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        final float scale = (float) valueAnimator.getAnimatedValue();
        Log.d("Sample-Share#" + getTag(), "onAnimationUpdate : scale = " + scale);
        setScaleX(scale);
        setScaleY(scale);
    }

    public void attachValueAnimator(ValueAnimator valueAnimator) {
        if (valueAnimator != null) {
            valueAnimator.addUpdateListener(this);
        }
    }
}
