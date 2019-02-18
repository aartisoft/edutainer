package com.edutainer.in.Activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.edutainer.in.R;
import com.edutainer.in.Utility.BaseActivity;

public class AboutUsActivity extends BaseActivity {
    private WebView webView;

    @Override
    public void initialize(Bundle save) {
        setTitle("About us");
        webView = (WebView) findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.edutainer.in/edutainer_api/Aboutus.php");
    }

    @Override
    public int getActivityLayout() {
        return R.layout.layout_webpage;
    }
}
