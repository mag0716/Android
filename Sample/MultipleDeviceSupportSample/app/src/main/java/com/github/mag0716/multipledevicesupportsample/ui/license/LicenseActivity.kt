package com.github.mag0716.multipledevicesupportsample.ui.license

import android.os.Bundle
import android.webkit.WebView
import com.github.mag0716.multipledevicesupportsample.R
import com.github.mag0716.multipledevicesupportsample.ui.base.LoggingActivity

class LicenseActivity : LoggingActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)

        webView = findViewById(R.id.web_view)

        if (savedInstanceState == null) {
            webView.loadUrl("file:///android_asset/licenses.html")
        } else {
            webView.restoreState(savedInstanceState)
        }
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }
}