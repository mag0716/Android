package m.k.android.librarysample.daggersample;

import android.util.Log;

/**
 * Created by kishimotomasashi on 2015/03/07.
 */
public class LogcatDebugger implements IDebugger {
    @Override
    public void showDebugMessage(String msg) {
        Log.d("debug", msg);
    }
}
