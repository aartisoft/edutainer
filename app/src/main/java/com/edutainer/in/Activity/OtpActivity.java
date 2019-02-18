package com.edutainer.in.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.edutainer.in.R;
import com.edutainer.in.Utility.BaseActivity;

public class OtpActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_get_otp;

    @Override
    public void initialize(Bundle save) {
        btn_get_otp = (Button) findViewById(R.id.btn_get_otp);
        btn_get_otp.setOnClickListener(this);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_otp;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_otp:
                sendToActivity(DrawerActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
        }
    }
}
