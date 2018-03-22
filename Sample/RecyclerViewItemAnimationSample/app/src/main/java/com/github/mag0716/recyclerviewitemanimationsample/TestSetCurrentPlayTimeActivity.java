package com.github.mag0716.recyclerviewitemanimationsample;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 *
 */
public class TestSetCurrentPlayTimeActivity extends AppCompatActivity {

    private ImageView icon1, icon2;
    private ObjectAnimator animator1, animator2;
    private LoggingAnimationListener listener1, listener2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_setcurrentplaytime);

        icon1 = findViewById(R.id.icon1);
        icon2 = findViewById(R.id.icon2);
        animator1 = AnimationHelper.createAnimator(icon1, false);
        animator2 = AnimationHelper.createAnimator(icon2, false);
        listener1 = new LoggingAnimationListener(icon1);
        listener2 = new LoggingAnimationListener(icon2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        animator1.addListener(listener1);
        animator1.addUpdateListener(listener1);
        animator2.addListener(listener2);
        animator2.addUpdateListener(listener2);
        animator1.start();
        new Handler().postDelayed(() -> {
            animator2.start();
            animator2.setCurrentPlayTime(1000);
        }, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        animator1.cancel();
        animator1.removeUpdateListener(listener1);
        animator1.removeListener(listener1);
        animator2.cancel();
        animator2.removeUpdateListener(listener2);
        animator2.removeListener(listener2);
    }

    private class LoggingAnimationListener implements ValueAnimator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {

        private View view;

        public LoggingAnimationListener(final View view) {
            this.view = view;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            Log.d("Sample#" + view.getTag(), "onAnimatorStart");
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Log.d("Sample#" + view.getTag(), "onAnimationEnd");
            AnimationHelper.clearAnimation(view);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            Log.d("Sample#" + view.getTag(), "onAnimationCancel");
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            Log.d("Sample#" + view.getTag(), "onAnimationRepeat");
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            Log.d("Sample#" + view.getTag(), "onAnimationUpdate : " + animation.getAnimatedValue());
        }
    }
}
