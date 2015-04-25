package m.k.android.sample.homeapplication;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
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

        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> applicationInfoList = getApplicationList(packageManager);
        outputApplicationInfoLog(applicationInfoList, packageManager);

        final AppWidgetManager widgetManager = AppWidgetManager.getInstance(this);
        List<AppWidgetProviderInfo> widgetInfoList = getWidgetList(widgetManager);
        outputWidgetInfoLog(widgetInfoList, packageManager);
    }

    @Override
    public void onBackPressed() {
        // disable back key.
    }

    private List<ResolveInfo> getApplicationList(@NonNull PackageManager manager) {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // see PackageManager.MATCH_DEFAULT_ONLY
        return manager.queryIntentActivities(intent, 0);
    }

    private List<AppWidgetProviderInfo> getWidgetList(@NonNull AppWidgetManager manager) {
        return manager.getInstalledProviders();
    }

    private void outputApplicationInfoLog(List<ResolveInfo> applicationInfoList, @NonNull PackageManager manager) {
        if(applicationInfoList != null) {
            for(ResolveInfo info : applicationInfoList) {
                outputDebugLog("Application : " + info.loadLabel(manager));
            }
        }
    }

    private void outputWidgetInfoLog(List<AppWidgetProviderInfo> widgetInfoList, @NonNull PackageManager manager) {
        if(widgetInfoList != null) {
            for(AppWidgetProviderInfo info : widgetInfoList) {
                outputDebugLog("Widget : " + info.label);
            }
        }
    }

    private void outputDebugLog(String msg) {
        Log.d(TAG, msg);
    }
}
