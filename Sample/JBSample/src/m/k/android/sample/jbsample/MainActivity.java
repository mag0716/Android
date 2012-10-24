package m.k.android.sample.jbsample;

import m.k.android.sample.jbsample.activityoptions.ActivityOptionsActivity;
import m.k.android.sample.jbsample.notification.NotificationActivity;
import m.k.android.sample.jbsample.webview.WebViewActivity;
import m.k.android.sample.jbsample.widget.WidgetManagerActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClick(View view) {
    	switch(view.getId()) {
    		case R.id.btn1:
    			moveNotificationActivity();
    			break;
    		case R.id.btn2:
    			moveWidgetManagerActivity();
    			break;
    		case R.id.btn3:
    			moveWebViewActivity();
    			break;
    		case R.id.btn4:
    			moveActivityOptionsActivity();
    			break;
    	}
    }
    
    private void moveNotificationActivity() {
    	Intent intent = new Intent(this, NotificationActivity.class);
    	startActivity(intent);
    }
    
    private void moveWidgetManagerActivity() {
    	Intent intent = new Intent(this, WidgetManagerActivity.class);
    	startActivity(intent);
    }
    
    private void moveWebViewActivity() {
    	Intent intent = new Intent(this, WebViewActivity.class);
    	startActivity(intent);
    }

    private void moveActivityOptionsActivity() {
    	Intent intent = new Intent(this, ActivityOptionsActivity.class);
    	startActivity(intent);
    }
}
