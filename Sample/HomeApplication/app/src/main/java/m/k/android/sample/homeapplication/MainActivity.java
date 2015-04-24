package m.k.android.sample.homeapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "HomeApplication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PackageManager manager = getPackageManager();

        List<ResolveInfo> applicationInfoList = getApplicationList(manager);

        outputDebugLog(applicationInfoList, manager);
    }

    @Override
    public void onBackPressed() {
        // disable back key.
    }

    private List<ResolveInfo> getApplicationList(@NonNull PackageManager manager) {
        final Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // see PackageManager.MATCH_DEFAULT_ONLY
        return manager.queryIntentActivities(intent, 0);
    }

    private void outputDebugLog(List<ResolveInfo> applicationInfoList, @NonNull PackageManager manager) {
        if(applicationInfoList != null) {
            for(ResolveInfo info : applicationInfoList) {
                Log.d(TAG, (String)info.loadLabel(manager));
            }
        }
    }
}
