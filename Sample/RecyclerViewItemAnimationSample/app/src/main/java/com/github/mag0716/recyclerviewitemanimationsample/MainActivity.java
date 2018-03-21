package com.github.mag0716.recyclerviewitemanimationsample;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
    private Adapter adapter;
    private long startTime = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(this, 100);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.startAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.stopAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private final long baseTime = System.currentTimeMillis();

        private final LayoutInflater inflater;
        private final int size;
        private final ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 2f).setDuration(AnimationHelper.DURATION);
        private PublishSubject<Long> animationTimer = PublishSubject.create();
        private Disposable disposable;

        public Adapter(@NonNull Context context, int size) {
            inflater = LayoutInflater.from(context);
            this.size = size;

            disposable = Observable.interval(0, AnimationHelper.DURATION, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> animationTimer.onNext(aLong));
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
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
            valueAnimator.cancel();
            valueAnimator.removeAllUpdateListeners();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.d("Sample", "onBindViewHolder : " + position);
            holder.text.setText("Text" + position);
            holder.icon.setTag(position);
            holder.canvasIcon.setTag(position);
            holder.canvasIcon.setStartTime(startTime);
            holder.shareValueAnimatorIcon.setTag(position);
            holder.shareValueAnimatorIcon.attachValueAnimator(valueAnimator);

            holder.startAnimation();
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            Log.d("Sample", "onViewAttachedToWindow : " + holder.text.getText());
            holder.startAnimation();
        }

        @Override
        public void onViewDetachedFromWindow(ViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
            Log.d("Sample", "onViewDetachedFromWindow : " + holder.text.getText());
            holder.stopAnimation();
        }

        @Override
        public int getItemCount() {
            return size;
        }

        public void startAnimation() {
            valueAnimator.start();
        }

        public void stopAnimation() {
            valueAnimator.cancel();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements ValueAnimator.AnimatorUpdateListener {

            ImageView icon;
            ImageView valueAnimatorWithCurrentPlayTimeIcon;
            AnimationCanvasView canvasIcon;
            ShareValueAnimatorImageView shareValueAnimatorIcon;
            TextView text;
            private ObjectAnimator animator;
            private ObjectAnimator animatorWithCurrentPlayztime;

            public ViewHolder(View itemView) {
                super(itemView);

                icon = itemView.findViewById(R.id.icon);
                valueAnimatorWithCurrentPlayTimeIcon = itemView.findViewById(R.id.icon1);
                canvasIcon = itemView.findViewById(R.id.icon2);
                shareValueAnimatorIcon = itemView.findViewById(R.id.icon3);
                text = itemView.findViewById(R.id.text);
                animator = AnimationHelper.createAnimator(icon, true);
                animatorWithCurrentPlayztime = AnimationHelper.createAnimator(valueAnimatorWithCurrentPlayTimeIcon, true);
            }

            public void startAnimation() {
                Log.d("Sample-ValueAnimator#" + valueAnimatorWithCurrentPlayTimeIcon.getTag(), "startAnimation");
                stopAnimation();
                if (animator != null && !animator.isRunning()) {
                    animator.start();
                }
                if (animatorWithCurrentPlayztime != null && !animatorWithCurrentPlayztime.isRunning()) {
                    long diff = System.currentTimeMillis() - baseTime;
                    long startTime = diff % animatorWithCurrentPlayztime.getDuration();
                    animatorWithCurrentPlayztime.addUpdateListener(this);
                    animatorWithCurrentPlayztime.start();
                    animatorWithCurrentPlayztime.setCurrentPlayTime(startTime);
                }
            }

            public void stopAnimation() {
                Log.d("Sample-ValueAnimator#" + valueAnimatorWithCurrentPlayTimeIcon.getTag(), "stopAnimation");
                if (animator != null && animator.isRunning()) {
                    animator.cancel();
                }
                AnimationHelper.clearAnimation(icon);
                if (animatorWithCurrentPlayztime != null && animatorWithCurrentPlayztime.isRunning()) {
                    animatorWithCurrentPlayztime.removeUpdateListener(this);
                    animatorWithCurrentPlayztime.cancel();
                    valueAnimatorWithCurrentPlayTimeIcon.setAnimation(null);
                }
                AnimationHelper.clearAnimation(valueAnimatorWithCurrentPlayTimeIcon);
            }

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 調査用のログ
                // 画面を終了しても、removeUpdateListener がされてない
                Log.d("Sample-ValueAnimator#" + valueAnimatorWithCurrentPlayTimeIcon.getTag(), "value = " + (float) animation.getAnimatedValue());
            }
        }
    }
}
