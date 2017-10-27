package com.github.mag0716.androidlint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mag0716.library.Util;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isButton1 = Util.isButton1(findViewById(R.id.text1));
        Log.d(TAG, "Non-constant resource ID in a switch statement : " + isButton1);
    }
}
