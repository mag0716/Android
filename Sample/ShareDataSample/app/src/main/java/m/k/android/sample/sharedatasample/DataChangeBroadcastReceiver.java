package m.k.android.sample.sharedatasample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class DataChangeBroadcastReceiver extends BroadcastReceiver {
    public DataChangeBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String value = intent.getStringExtra("value");

        pref(context, MainActivity.BROADCAST_TYPE).edit().putString("sample", value).commit();
    }

    private SharedPreferences pref(Context context, String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
}
