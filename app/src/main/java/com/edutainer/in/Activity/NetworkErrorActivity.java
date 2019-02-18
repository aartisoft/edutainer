package com.edutainer.in.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.edutainer.in.R;

/**
 * Created by Gokul on 7/21/2016.
 */
public class NetworkErrorActivity extends Activity {

    private Button network_btn_tryagain;

    public void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.fragment_network_error);

        network_btn_tryagain = (Button) findViewById(R.id.network_btn_tryagain);
        network_btn_tryagain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isNetworkAvailable(NetworkErrorActivity.this)) {
                    Intent intent = new Intent(NetworkErrorActivity.this, SplashActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
