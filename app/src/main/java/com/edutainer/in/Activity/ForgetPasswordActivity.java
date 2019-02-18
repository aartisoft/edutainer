package com.edutainer.in.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;
import com.edutainer.in.Utility.CustomEditText;
import org.json.JSONObject;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener, ApiResponse {
    private Button btn_get_otp;
    private CustomEditText email_id;

    @Override
    public void initialize(Bundle save) {
        setTitle("Forgot Password");

        btn_get_otp = (Button) findViewById(R.id.btn_get_otp);
        email_id = (CustomEditText) findViewById(R.id.email_id);

        btn_get_otp.setOnClickListener(this);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_forgot;
    }

    @Override
    public void onClick(View view) {
        String string_email = email_id.getValue().toString();

        if (string_email.equalsIgnoreCase("")) {
            email_id.setError("Please Enter Email Id/Mobile");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("email=").append(string_email);

        String content = stringBuilder.toString();

        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.FORGOT_PASSWORD, 0, content, true, "loading ..", this);
        apiConsumer.execute();
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            Log.d("responseData", responseData);
            JSONObject jsonObject = new JSONObject(responseData);
            String status = jsonObject.getString("status");
            String message = jsonObject.getString("msg");
            toastMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
