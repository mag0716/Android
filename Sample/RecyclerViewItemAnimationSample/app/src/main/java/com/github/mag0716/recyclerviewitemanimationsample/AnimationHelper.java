package com.github.mag0716.recyclerviewitemanimationsample;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by mag0716 on 2018/03/11.
 */

public class AnimationHelper {

    public static final long DURATION = 5000;

    public static ObjectAnimator createAnimator(@NonNull View view, boolean isLoopAnimation) {
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("scaleX", 2f),
                PropertyValuesHolder.ofFloat("scaleY", 2f));
        animator.setDuration(DURATION);
        if (isLoopAnimation) {
            animator.setRepeatCount(ValueAnimator.INFINITE);
        }
        return animator;
    }

    public static void clearAnimation(@NonNull View view) {
        view.setScaleX(1);
        view.setScaleY(1);
    }
}
