package com.github.mag0716.library;

import android.view.View;

/**
 * Non-constant resource ID in a switch statement
 * <p>
 * Created by mag0716 on 2017/10/27.
 */
public class Util {

    public static boolean isButton1(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                return true;
        }
        return false;
    }
}
