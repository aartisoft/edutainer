package com.edutainer.in.workplace.Drawer.Webview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.edutainer.in.R;

public class FragmentWebView extends Fragment {
    private WebView webView;
    private String url = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        if (getArguments() != null){
            Bundle bundle = getArguments();
            url = bundle.getString("URL");
            webView.loadUrl(url);
        }
        return view;
    }
}
