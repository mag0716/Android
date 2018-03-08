package com.github.mag0716.recyclerviewitemanimationsample;

import android.animation.ObjectAnimator;
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
    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new Adapter(this, 100));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
//        animator = ObjectAnimator.ofPropertyValuesHolder(icon,
//                PropertyValuesHolder.ofFloat("scaleX", 3f),
//                PropertyValuesHolder.ofFloat("scaleY", 3f));
//        animator.setDuration(5000);
//        animator.setRepeatCount(ObjectAnimator.INFINITE);
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
        }

        @Override
        public int getItemCount() {
            return size;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView icon;
            TextView text;

            public ViewHolder(View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.icon);
                text = itemView.findViewById(R.id.text);
            }
        }
    }
}
