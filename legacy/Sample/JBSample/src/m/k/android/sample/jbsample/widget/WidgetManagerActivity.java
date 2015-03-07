package m.k.android.sample.jbsample.widget;

import java.util.Set;

import m.k.android.sample.jbsample.R;
import m.k.android.sample.utils.LogUtils;
import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class WidgetManagerActivity extends Activity {

	private final static int APPWIDGET_HOST_ID = 1024;
	
	private final class RequestCode {
		public final static int ALLOW_BIND_REQUEST_CODE = 0;
	}
	
	private AppWidgetManager mAppWidgetManager;
	private AppWidgetHost mAppWidgetHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_manager);

		mAppWidgetManager = AppWidgetManager.getInstance(this);
		mAppWidgetHost = new AppWidgetHost(this, APPWIDGET_HOST_ID);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		LogUtils.outputLog(String.format("onActivityResult : requestCode = %d, resultCode = %d", requestCode, resultCode), getClass());
		
		switch(requestCode) {
			case RequestCode.ALLOW_BIND_REQUEST_CODE:
				if(resultCode == RESULT_OK) {
					int widgetId = data.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
					LogUtils.outputLog(String.format("widgetId = %d", widgetId), getClass());
					if(widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
						AppWidgetProviderInfo info = mAppWidgetManager.getAppWidgetInfo(widgetId);
						LogUtils.outputLog(String.format("widget label = %s", info.label), getClass());
					}
				} else if(requestCode == RESULT_CANCELED) {
					
				}
				break;
		}
	}
	
	public void onClick(View view) {
		switch(view.getId()) {
			case R.id.btn1:
				outputWidgetInfo();
				break;
			case R.id.btn2:
				getAppWidgetOptions();
				break;
			case R.id.btn3:
				updateAppWidgetOptions();
				break;
			case R.id.btn4:
				bindAppWidget();
				break;
		}
	}
	
	private void outputWidgetInfo() {
		LogUtils.outputLog("outputWidgetInfo", getClass());
		mAppWidgetManager = AppWidgetManager.getInstance(this);          
        int[] widgetIds = mAppWidgetManager.getAppWidgetIds(new ComponentName(this, SizableWidgetProvider.class));
        
        if(widgetIds.length <= 0) {
        	LogUtils.outputLog("widget isn't exisits.", getClass());
        	return;
        }
        
        AppWidgetProviderInfo info;
        for(int widgetId : widgetIds) {
        	info = mAppWidgetManager.getAppWidgetInfo(widgetId);

        	if(info.configure != null) {  
        		LogUtils.outputLog("configure" + info.configure.flattenToString(), getClass());
        	}

        	LogUtils.outputLog("icon = " + info.icon, getClass());  
        	LogUtils.outputLog("initialLayout = " + info.initialLayout, getClass());  
        	LogUtils.outputLog("label = " + info.label, getClass());  
        	LogUtils.outputLog("minHeight = " + info.minHeight, getClass());  
        	LogUtils.outputLog("minWidth = " + info.minWidth, getClass());  
        	LogUtils.outputLog("updatePeriodMilis = " + info.updatePeriodMillis, getClass());  
        	if(info.provider != null) {  
        		LogUtils.outputLog("provider = " + info.provider.flattenToString(), getClass());
        	}

        	if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {  
        		LogUtils.outputLog("autoAdvanceViewId = " + info.autoAdvanceViewId, getClass());
        		LogUtils.outputLog("previewImage = " + info.previewImage, getClass());
        	}

        	if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {  
        		LogUtils.outputLog("resizeMode = " + info.resizeMode, getClass());
        	}
        }
	}
	
	private void getAppWidgetOptions() {
		LogUtils.outputLog("getAppWidgetOptions", getClass());
		mAppWidgetManager = AppWidgetManager.getInstance(this);          
        int[] widgetIds = mAppWidgetManager.getAppWidgetIds(new ComponentName(this, SizableWidgetProvider.class));
        
        if(widgetIds.length <= 0) {
        	LogUtils.outputLog("widget isn't exisits.", getClass());
        	return;
        }

        Bundle options;
        for(int widgetId : widgetIds) {
        	options = mAppWidgetManager.getAppWidgetOptions(widgetId);
        	outputAppWidgetOptions(options);
        }
	}
	
	private void updateAppWidgetOptions() {
		LogUtils.outputLog("updateAppWidgetOptions", getClass());
		mAppWidgetManager = AppWidgetManager.getInstance(this);          
        int[] widgetIds = mAppWidgetManager.getAppWidgetIds(new ComponentName(this, SizableWidgetProvider.class));
        
        if(widgetIds.length <= 0) {
        	LogUtils.outputLog("widget isn't exisits.", getClass());
        	return;
        }

        Bundle options;
        for(int widgetId : widgetIds) {
        	options = mAppWidgetManager.getAppWidgetOptions(widgetId);
        	LogUtils.outputLog("before updateAppWidgetOptions.", getClass());
        	outputAppWidgetOptions(options);
        	options.putInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, 500);
        	options.putInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH, 672);
        	mAppWidgetManager.updateAppWidgetOptions(widgetId, options);
        	LogUtils.outputLog("after updateAppWidgetOptions", getClass());
        	options = mAppWidgetManager.getAppWidgetOptions(widgetId);
        	outputAppWidgetOptions(options);
        }
    	new SizableWidgetProvider().onUpdate(this, mAppWidgetManager, widgetIds);
	}
	
	private void bindAppWidget() {
		LogUtils.outputLog("bindAppWidget", getClass());
		
		mAppWidgetHost = new AppWidgetHost(this, APPWIDGET_HOST_ID);
		ComponentName componentName = new ComponentName(this, SizableWidgetProvider.class);
		int widgetId = mAppWidgetHost.allocateAppWidgetId();
		boolean isAllowed = AppWidgetManager.getInstance(this).bindAppWidgetIdIfAllowed(widgetId, componentName);
		if(isAllowed) {
			LogUtils.outputLog(String.format("widgetId = %d", widgetId), getClass());
			if(widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
				AppWidgetProviderInfo info = mAppWidgetManager.getAppWidgetInfo(widgetId);
				LogUtils.outputLog(String.format("widget label = %s", info.label), getClass());
			}
		} else {
			Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_BIND);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER, componentName);
			startActivityForResult(intent, RequestCode.ALLOW_BIND_REQUEST_CODE);
		}
	}
	
	private void outputAppWidgetOptions(Bundle options) {
		LogUtils.outputLog("outputAppWidgetOptions", getClass());
		Set<String> keys = options.keySet();
		for(String key : keys) {
			LogUtils.outputLog(String.format("%s = %s", key, options.get(key)), getClass());
		}
	}
}
