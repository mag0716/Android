package com.github.mag0716.testsample;

import android.content.Context;
import android.support.annotation.NonNull;

public class Sample {

    public static String getApplicationName(@NonNull Context context) {
        return context.getString(R.string.app_name);
    }
}
