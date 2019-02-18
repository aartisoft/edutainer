package com.edutainer.in.Allen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.edutainer.in.Activity.DrawerActivity;
import com.edutainer.in.Activity.TermsConditionsActivity;
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

public class RegisterActivityTest extends AppCompatActivity implements ApiResponse, View.OnClickListener {
    TextInputEditText et_name;
    TextInputEditText et_email;
    TextInputEditText et_password;
    CheckBox cb_terms;
    TextView tv_terms;
    Button btn_create_account;
    TextView tv_login;

    private String gen_code = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_test);

        Toolbar toolbar = findViewById(R.id.toolbar_register);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("Create Account");

        Typeface OpenSans_Regular = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Regular.ttf");
        Typeface OpenSans_Bold = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Bold.ttf");

/*
        et_name = findViewById(R.id.et_name);
        et_name.setTypeface(OpenSans_Regular);

        et_email = findViewById(R.id.et_email);
        et_email.setTypeface(OpenSans_Regular);

        et_password = findViewById(R.id.et_password);
        et_password.setTypeface(OpenSans_Regular);

*/
        cb_terms = findViewById(R.id.cb_terms);
        cb_terms.setTypeface(OpenSans_Regular);

        tv_terms = findViewById(R.id.tv_terms);
        tv_terms.setTypeface(OpenSans_Regular);

        btn_create_account = findViewById(R.id.btn_create_account);
        btn_create_account.setTypeface(OpenSans_Bold);

        tv_login = findViewById(R.id.tv_login);
        tv_login.setTypeface(OpenSans_Regular);

    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        super.onOptionsItemSelected(menuItem);
        switch (menuItem.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    public void sendToActivity(Class className) {
        Intent intent = new Intent(this, className);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }

    public void sendToActivity(Class className, int flags) {
        Intent intent = new Intent(this, className);
        intent.setFlags(flags);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create_account:
                register();
                break;
            case R.id.tv_terms:
                sendToActivity(TermsConditionsActivity.class);
                break;
            case R.id.tv_login:
                sendToActivity(LoginActivityTest.class);
                break;
        }
    }


    private void register() {
        String string_name = et_name.getText().toString();
        String string_email = et_email.getText().toString();
        String string_password = et_password.getText().toString();

        if (string_name.equalsIgnoreCase("")) {
            et_name.setError("Please Enter Name");
            return;
        } else if (string_email.equalsIgnoreCase("") || !Utility.isValidEmail(string_email)) {
            et_email.setError("Please Provide Valid Email Id");
            return;
        } else if (string_password.equalsIgnoreCase("")) {
            et_password.setError("Please Enter Password");
            return;
        } else if (!cb_terms.isChecked()) {
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
        stringBuilder.append("&password=").append(string_password);

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
                AppPref.getInstance().setUserId(user_id);
                AppPref.getInstance().setUSERNAME(et_name.getText().toString());
                AppPref.getInstance().setUSEREMAIL(et_email.getText().toString());
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
