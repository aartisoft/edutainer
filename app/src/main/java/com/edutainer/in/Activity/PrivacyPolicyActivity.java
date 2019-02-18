package com.edutainer.in.Activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.edutainer.in.R;
import com.edutainer.in.Utility.BaseActivity;

public class PrivacyPolicyActivity extends BaseActivity {
    private WebView webview;

    @Override
    public void initialize(Bundle save) {
        setTitle("Privacy Policy");
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);

        webview.loadUrl("https://www.edutainer.in/edutainer_api/privacypolicy.php");
    }

    @Override
    public int getActivityLayout() {
        return R.layout.layout_webpage;
    }
}
