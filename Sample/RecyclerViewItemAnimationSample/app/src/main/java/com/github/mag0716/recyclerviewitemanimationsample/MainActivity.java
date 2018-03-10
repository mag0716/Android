package com.github.mag0716.recyclerviewitemanimationsample;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

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
        private PublishSubject<Long> animationTimer = PublishSubject.create();
        private Disposable disposable;

        public Adapter(@NonNull Context context, int size) {
            inflater = LayoutInflater.from(context);
            this.size = size;

            disposable = Observable.interval(0, AnimationHelper.DURATION, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> animationTimer.onNext(aLong));
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.item_cell, parent, false));
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
            if (disposable != null) {
                disposable.dispose();
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.d("xxx", "onBindViewHolder : " + position);
            holder.text.setText("Text" + position);
            holder.icon.setTag(position);
            holder.animationIcon.setAnimationTimer(animationTimer);

            holder.startAnimation();
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            Log.d("xxx", "onViewAttachedToWindow : " + holder.icon.getTag());
            holder.startAnimation();
        }

        @Override
        public void onViewDetachedFromWindow(ViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
            Log.d("xxx", "onViewDetachedFromWindow : " + holder.icon.getTag());
            holder.stopAnimation();
        }

        @Override
        public int getItemCount() {
            return size;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView icon;
            AnimationImageView animationIcon;
            TextView text;
            private ObjectAnimator animator;

            public ViewHolder(View itemView) {
                super(itemView);

                icon = itemView.findViewById(R.id.icon);
                animationIcon = itemView.findViewById(R.id.icon2);
                text = itemView.findViewById(R.id.text);
                animator = AnimationHelper.createAnimator(icon);
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
                AnimationHelper.clearAnimation(icon);
            }

        }
    }
}
