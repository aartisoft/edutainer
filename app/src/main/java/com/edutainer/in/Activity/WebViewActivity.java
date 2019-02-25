package com.edutainer.in.Activity;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.edutainer.in.R;

import java.net.URLEncoder;

public class WebViewActivity extends AppCompatActivity {
    WebView webview;
    Dialog cProgressDialog;

    public void initialize(Bundle save) {
        try {
            save = getIntent().getExtras();
            String course_id = save.getString("course_id");
            String video_id = save.getString("video_id");
            webview = (WebView) findViewById(R.id.webview);
            webview.getSettings().setJavaScriptEnabled(true);
            String postData = "user_id=" + URLEncoder.encode("1", "UTF-8") + "&course_id=" + URLEncoder.encode(course_id, "UTF-8") + "&video_id=" + URLEncoder.encode(video_id, "UTF-8");
            Log.d("postdata", postData);
            webview.postUrl("https://www.edutainer.in/jnjsndjs/index.php", postData.getBytes());

            cProgressDialog = new Dialog(this);
            cProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            cProgressDialog.setContentView(R.layout.loader);
            cProgressDialog.setCanceledOnTouchOutside(false);
            cProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

            Window window = cProgressDialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
            cProgressDialog.show();


            webview.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    cProgressDialog.hide();
                }
            });
        } catch (Exception e) {

        }
        //webview.loadDataWithBaseURL("", "" , "text/html",  "UTF-8", "");
    }


    public int getActivityLayout() {
        return R.layout.layout_webview;
    }

    public void onCreate(Bundle save) {
        super.onCreate(save);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(getActivityLayout());
        initialize(save);
    }
}
