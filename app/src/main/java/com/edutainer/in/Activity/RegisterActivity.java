package com.edutainer.in.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.edutainer.in.Model.ContactModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;
import com.edutainer.in.Utility.CustomEditText;
import com.edutainer.in.Utility.Utility;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class RegisterActivity extends BaseActivity implements ApiResponse, View.OnClickListener {

    private Button login;
    private CustomEditText edt_name;
    private CustomEditText edt_email;
    private CustomEditText edt_mobile;
    private CustomEditText edt_password;
    private CheckBox terms_condition;
    private TextView terms_text;
    private CustomEditText edt_refer_code;
    private String gen_code = "";


    private static ArrayList<ContactModel> contactList = new ArrayList();


    @Override
    public void initialize(Bundle save) {
        setTitle("Register");

        edt_name = (CustomEditText) findViewById(R.id.edt_name);
        edt_email = (CustomEditText) findViewById(R.id.edt_email);
        edt_mobile = (CustomEditText) findViewById(R.id.edt_mobile);
        edt_password = (CustomEditText) findViewById(R.id.edt_password);
        edt_refer_code = (CustomEditText) findViewById(R.id.edt_refer_code);

        edt_mobile.setFilters(10);

        terms_condition = (CheckBox) findViewById(R.id.terms_condition);
        terms_text = (TextView) findViewById(R.id.terms_text);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

        terms_text.setOnClickListener(this);


    }


    @Override
    public int getActivityLayout() {
        return R.layout.activity_register;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.terms_text:
                sendToActivity(TermsConditionsActivity.class);
                break;
        }
    }


    private void login() {
        String string_name = edt_name.getValue().toString();
        String string_email = edt_email.getValue().toString();
        String string_mobile = edt_mobile.getValue().toString();
        String string_password = edt_password.getValue().toString();

        if (string_name.equalsIgnoreCase("")) {
            edt_name.setError("Please Enter Name");
            return;
        } else if (string_email.equalsIgnoreCase("") || !Utility.isValidEmail(string_email)) {
            edt_email.setError("Please Provide Valid Email Id");
            return;
        } else if (string_mobile.equalsIgnoreCase("") || string_mobile.length() != 10) {
            edt_mobile.setError("Please Provide Correct Mobile Number");
            return;
        } else if (string_password.equalsIgnoreCase("")) {
            edt_password.setError("Please Enter Password");
            return;
        } else if (!terms_condition.isChecked()) {
            toastMessage("To register, You must be agree with Terms & Conditions");
            return;
        }

        gen_code = string_name.substring(0, 2);
        Random r = new Random();
        int low = 10;
        int high = 100;
        int result = r.nextInt(high - low) + low;

        gen_code = gen_code + result;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name=").append(string_name);
        stringBuilder.append("&email=").append(string_email);
        stringBuilder.append("&mobile=").append(string_mobile);
        stringBuilder.append("&imei=").append(AppPref.getInstance().getUSERIMEI());
        stringBuilder.append("&date=").append(new Date().toString());
        stringBuilder.append("&password=").append(string_password);
        stringBuilder.append("&gen_code=").append(gen_code);
        stringBuilder.append("&ref_code=").append(edt_refer_code.getValue().toString());

        String content = stringBuilder.toString();


        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.REGISTER_URL, 0, content, true, "loading ...", this);
        apiConsumer.execute();
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            Log.d("responseData", responseData);
            JSONObject jsonObject = new JSONObject(responseData);
            String status = jsonObject.getString("status");
            String message = jsonObject.getString("msg");
            if (status.equalsIgnoreCase("1")) {
                String user_id = jsonObject.getString("user_id");
                AppPref.getInstance().setUSERNAME(edt_name.getValue().toString());
                AppPref.getInstance().setUSEREMAIL(edt_email.getValue().toString());
                AppPref.getInstance().setUSERMOBILE(edt_mobile.getValue().toString());
                AppPref.getInstance().setReferCode(edt_refer_code.getValue().toString());
                AppPref.getInstance().setGenCode(gen_code);
                AppPref.getInstance().setUserId(user_id);
                sendToActivity(DrawerActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            } else {
                if (message.contains("Duplicate entry") && message.contains("email")) {
                    toastMessage("Email already exits");
                } else if (message.contains("Duplicate entry") && message.contains("mobile")) {
                    toastMessage("Mobile No already exits");
                } else
                    toastMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
