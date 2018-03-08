package com.github.mag0716.recyclerviewitemanimationsample;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewGroup container;
    private View icon;
    private TextView text;
    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        icon = findViewById(R.id.icon);
        text = findViewById(R.id.text);
        text.setText("Text");

        initAnimator();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animator != null) {
            animator.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animator != null) {
            animator.cancel();
        }
    }

    private void initAnimator() {
        animator = ObjectAnimator.ofPropertyValuesHolder(icon,
                PropertyValuesHolder.ofFloat("scaleX", 3f),
                PropertyValuesHolder.ofFloat("scaleY", 3f));
        animator.setDuration(5000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
    }
}
