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

public class NewRemoteViewsWidgetProvider extends AppWidgetProvider {

	
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
			
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_remoteviews_widget);
			
			// set click listener
			
			appWidgetManager.updateAppWidget(appWidgetId, views);
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
	}
}
