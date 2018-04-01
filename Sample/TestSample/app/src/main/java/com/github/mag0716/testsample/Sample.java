package com.github.mag0716.testsample;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Sample {

    public static String getApplicationName(@NonNull Context context) {
        return context.getString(R.string.app_name);
    }

    public static String getAssetsFile(@NonNull Context context) throws IOException {
        final AssetManager assetManager = context.getResources().getAssets();
        return inputStreamToString(assetManager.open("sample.txt"));
    }

    public static String inputStreamToString(@NonNull InputStream is) throws IOException {
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String str = null;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } finally {
            is.close();
        }
    }
}
