package com.edutainer.in.Activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;
import com.edutainer.in.Utility.CustomEditText;
import com.edutainer.in.Utility.Utility;

import org.json.JSONArray;
import org.json.JSONObject;


public class LoginActivity extends BaseActivity implements View.OnClickListener, ApiResponse {
    private Button login;
    private CustomEditText edt_email;
    private CustomEditText edt_password;

    private TextView forgot_password;
    private TextView create_account;

    @Override
    public void initialize(Bundle save) {
        setTitle("Login");

        edt_email = (CustomEditText) findViewById(R.id.edt_email);
        edt_password = (CustomEditText) findViewById(R.id.edt_password);

        forgot_password = (TextView) findViewById(R.id.forgot_password);
        create_account = (TextView) findViewById(R.id.create_account);

        login = (Button) findViewById(R.id.login);

        forgot_password.setPaintFlags(forgot_password.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        create_account.setPaintFlags(create_account.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        login.setOnClickListener(this);
        create_account.setOnClickListener(this);
        forgot_password.setOnClickListener(this);

    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.create_account:
                sendToActivity(RegisterActivity.class);
                break;
            case R.id.forgot_password:
                sendToActivity(ForgetPasswordActivity.class);
                break;
        }
    }


    private void login() {
        String string_email = edt_email.getValue().toString();
        String string_password = edt_password.getValue().toString();

        if (string_email.equalsIgnoreCase("") || !Utility.isValidEmail(string_email)) {
            edt_email.setError("Please Provide Valid Email Id");
            return;
        } else if (string_password.equalsIgnoreCase("")) {
            edt_password.setError("Please Enter Password");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("email=").append(string_email);
        stringBuilder.append("&password=").append(string_password);

        String content = stringBuilder.toString();


        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.LOGIN_URL, 0, content, true, "loading ...", this);
        apiConsumer.execute();
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            Log.d("responseData", responseData);
            JSONObject jsonObject1 = new JSONObject(responseData);
            JSONArray jsonArray = jsonObject1.getJSONArray("login");
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String user_id = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            String mobile = jsonObject.getString("mobile");
            String email = jsonObject.getString("email");
            String gen_code = jsonObject.getString("gen_code");
            //String stream = jsonObject.getString("user_stream");

            AppPref.getInstance().setUserId(user_id);
            AppPref.getInstance().setUSERNAME(name);
            AppPref.getInstance().setUSERMOBILE(mobile);
            AppPref.getInstance().setUSEREMAIL(email);
            AppPref.getInstance().setGenCode(gen_code);
            //AppPref.getInstance().setUSERSTREAM(stream);
            sendToActivity(DrawerActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        } catch (Exception e) {
            toastMessage("User Id or Password Not Correct");
            e.printStackTrace();
        }
    }
}
