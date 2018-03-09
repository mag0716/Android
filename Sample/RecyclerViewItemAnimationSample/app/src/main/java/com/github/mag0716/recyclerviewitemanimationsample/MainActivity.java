package com.github.mag0716.recyclerviewitemanimationsample;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new Adapter(this, 100));
        recyclerView.setLayoutManager(new PreCacheLayoutManager(this));

    }

    private class PreCacheLayoutManager extends LinearLayoutManager {

        public PreCacheLayoutManager(Context context) {
            super(context);
        }

        @Override
        protected int getExtraLayoutSpace(RecyclerView.State state) {
            return 1000;
        }
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private final LayoutInflater inflater;
        private final int size;

        public Adapter(@NonNull Context context, int size) {
            inflater = LayoutInflater.from(context);
            this.size = size;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.item_cell, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText("Text" + position);

            holder.initAnimator();
            holder.startAnimation();
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            holder.startAnimation();
        }

        @Override
        public void onViewDetachedFromWindow(ViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
            holder.stopAnimation();
        }

        @Override
        public int getItemCount() {
            return size;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView icon;
            TextView text;
            private ObjectAnimator animator;

            public ViewHolder(View itemView) {
                super(itemView);

                icon = itemView.findViewById(R.id.icon);
                text = itemView.findViewById(R.id.text);
            }

            public void initAnimator() {
                animator = ObjectAnimator.ofPropertyValuesHolder(icon,
                        PropertyValuesHolder.ofFloat("scaleX", 2f),
                        PropertyValuesHolder.ofFloat("scaleY", 2f));
                animator.setDuration(5000);
                animator.setRepeatCount(ObjectAnimator.INFINITE);
            }

            public void startAnimation() {
                stopAnimation();
                if (animator != null && !animator.isRunning()) {
                    animator.start();
                }
            }

            public void stopAnimation() {
                if (animator != null && animator.isRunning()) {
                    animator.cancel();
                    icon.setAnimation(null);
                }
                // reset size
                icon.setScaleX(1);
                icon.setScaleY(1);
            }

        }
    }
}
