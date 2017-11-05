package com.github.mag0716.androidlint;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mag0716.library.Util;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isButton1 = Util.isButton1(findViewById(R.id.text1));
        Log.d(TAG, "Non-constant resource ID in a switch statement : " + isButton1);
    }

    private void initNotificationChannel() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            final List<NotificationChannel> channelList = manager.getNotificationChannels();
            Log.d(TAG, "Calling new methods on older versions : " + channelList.size());
        }
    }

    // Incorrect support annotation usage
    private void sampleIncorrectSupportAnnotationUsage(@IntRange(from = 1, to = 0) long ngAnnotation) {
        Log.d(TAG, "Incorrect support annotation usage : " + ngAnnotation);
    }

    // Incorrect ObjectAnimator Property のサンプル例だったが、propertyName に x,y 以外を指定しても指摘されない
//    private void loadNgAnimator() {
//        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.ng_animator);
//        animator.setTarget(findViewById(R.id.text1));
//        animator.start();
//    }
}
