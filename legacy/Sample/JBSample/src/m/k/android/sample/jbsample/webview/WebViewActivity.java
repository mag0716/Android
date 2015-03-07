package m.k.android.sample.jbsample.webview;

import m.k.android.sample.jbsample.R;
import m.k.android.sample.utils.LogUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WebViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
	}
	
	public void onClick(View view) {
		switch(view.getId()) {
			case R.id.btn1:
				moveToWebView("file:///android_asset/kusamakura/04-content001.html");
				break;
			case R.id.btn2:
				moveToWebView("http://html5test.com/");
				break;
		}
	}
	
	private void moveToWebView(String url) {
		LogUtils.outputLog(String.format("moveToWebView : url = %s", url), getClass());
		final Intent intent = new Intent(this, WebActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);
	}
}
