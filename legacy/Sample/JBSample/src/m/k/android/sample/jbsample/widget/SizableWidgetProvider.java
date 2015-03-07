package m.k.android.sample.jbsample.widget;

import m.k.android.sample.jbsample.R;
import m.k.android.sample.utils.LogUtils;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class SizableWidgetProvider extends AppWidgetProvider {

	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		LogUtils.outputLog("onReceive", getClass());
	}
	
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		LogUtils.outputLog("onEnabled", getClass());
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		LogUtils.outputLog("onUpdate", getClass());
		
		final int n = appWidgetIds.length;
		
		for(int i=0; i<n; i++) {
			int appWidgetId = appWidgetIds[i];
			
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sizable_widget);
			
			// set click listener
			
			appWidgetManager.updateAppWidget(appWidgetId, views);
			
			Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
			LogUtils.outputLog(String.format("getAppWidgetOptions(%d)", appWidgetId), getClass());
			outputAppWidgetMinSize(options);
			outputAppWidgetMaxSize(options);
		}
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		LogUtils.outputLog("onDeleted", getClass());
	}
	
	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		LogUtils.outputLog("onDisabled", getClass());
	}

	@Override
	public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
		LogUtils.outputLog("onAppWidgetOptionsChanged", getClass());

		outputAppWidgetMinSize(newOptions);
		outputAppWidgetMaxSize(newOptions);
	}
	
	private void outputAppWidgetMinSize(Bundle options) {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			LogUtils.outputLog(String.format("appWidgetMinSize = (%d, %d)", 
								options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, 0),
								options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT, 0)), 
							getClass());
		} else {
			LogUtils.outputLog(Log.WARN, getClass(), String.format("not target(SDK is %d)", Build.VERSION.SDK_INT), null);
		}
	}

	private void outputAppWidgetMaxSize(Bundle options) {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			LogUtils.outputLog(String.format("appWidgetMaxSize = (%d, %d)", 
								options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH, 0),
								options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT, 0)), 
							getClass());
		} else {
			LogUtils.outputLog(Log.WARN, getClass(), String.format("not target(SDK is %d)", Build.VERSION.SDK_INT), null);
		}
	}
}
