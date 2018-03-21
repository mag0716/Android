package com.github.mag0716.recyclerviewitemanimationsample;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mag0716 on 2018/03/21.
 */

public class SecondActivity extends AppCompatActivity implements ValueAnimator.AnimatorUpdateListener {

    private static final int COUNT = 100;

    private LinearLayout container;
    private List<ObjectAnimator> animators = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAnimation();
    }

    private void init() {
        container = findViewById(R.id.container);
        for (int i = 0; i < COUNT; i++) {
            final ImageView imageView = new ImageView(this);
            imageView.setTag(i);
            imageView.setImageResource(R.drawable.sample);
            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            container.addView(imageView, params);
            animators.add(AnimationHelper.createAnimator(imageView, true));
        }
    }

    private void startAnimation() {
        for (ObjectAnimator animator : animators) {
            animator.addUpdateListener(this);
            animator.start();
        }
    }

    private void stopAnimation() {
        for (ObjectAnimator animator : animators) {
            animator.removeUpdateListener(this);
            animator.cancel();
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        Log.d("Sample", "onAnimationUpdate : " + animation.getAnimatedValue());
    }
}
