package com.github.mag0716.recyclerviewitemanimationsample;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by mag0716 on 2018/03/10.
 */

public class AnimationImageView extends AppCompatImageView {

    private ObjectAnimator animator;

    public AnimationImageView(Context context) {
        super(context);
        initAnimator();
    }

    public AnimationImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAnimator();
    }

    public AnimationImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAnimator();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }


    private void initAnimator() {
        animator = ObjectAnimator.ofPropertyValuesHolder(this,
                PropertyValuesHolder.ofFloat("scaleX", 2f),
                PropertyValuesHolder.ofFloat("scaleY", 2f));
        animator.setDuration(5000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
    }

    private void startAnimation() {
        stopAnimation();
        if (animator != null && !animator.isRunning()) {
            animator.start();
        }
    }

    private void stopAnimation() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
        // reset size
        setScaleX(1);
        setScaleY(1);
    }
}
