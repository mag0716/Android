package m.k.android.sample.jbsample.widget;

import m.k.android.sample.utils.LogUtils;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.os.Bundle;

public class SizableWidgetConfigure extends Activity {

	int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.outputLog("onCreate", getClass());
		
		setResult(RESULT_CANCELED);
	}
}
