package m.k.android.librarysample.daggersample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * Created by kishimotomasashi on 2015/03/07.
 */
public class ToastDebugger implements IDebugger {

    @NonNull
    private final Context mContext;

    public ToastDebugger(@NonNull Context context) {
        mContext = context;
    }

    @Override
    public void showDebugMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
