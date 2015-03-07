package m.k.android.sample.jbsample.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends Activity {

	WebView mWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mWebView = new WebView(this);
		mWebView.getSettings().setJavaScriptEnabled(true);
		setContentView(mWebView);
		
		Intent intent = getIntent();
		mWebView.loadUrl(intent.getStringExtra("url"));
	}
}
