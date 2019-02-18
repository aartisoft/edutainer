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
import android.widget.TextView;
import android.widget.Toast;
import com.edutainer.in.Activity.DrawerActivity;
import com.edutainer.in.Activity.ForgetPasswordActivity;
import com.edutainer.in.Activity.LoginActivity;
import com.edutainer.in.Activity.RegisterActivity;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.Utility;
import com.edutainer.in.workplace.HomeScreen.HomeActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivityTest extends AppCompatActivity implements View.OnClickListener, ApiResponse {
    TextInputEditText et_email;
    TextInputEditText et_password;
    TextView tv_forgot_password;
    Button btn_login;
    TextView tv_create_account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);
        Toolbar toolbar = findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("Login");

        Typeface OpenSans_Regular = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Regular.ttf");
        Typeface OpenSans_Bold = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Bold.ttf");

        /*et_email = findViewById(R.id.et_email);
        et_email.setTypeface(OpenSans_Regular);

        et_password = findViewById(R.id.et_password);
        et_password.setTypeface(OpenSans_Regular);

        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        tv_forgot_password.setTypeface(OpenSans_Regular);
        tv_forgot_password.setOnClickListener(this);
*/
        btn_login = findViewById(R.id.btn_login);
        btn_login.setTypeface(OpenSans_Bold);
        btn_login.setOnClickListener(this);

  /*      tv_create_account = findViewById(R.id.tv_create_account);
        tv_create_account.setTypeface(OpenSans_Regular);
        tv_create_account.setOnClickListener(this);
*/
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

    private void login() {
        String string_email = et_email.getText().toString();
        String string_password = et_password.getText().toString();

        if (string_email.equalsIgnoreCase("") || !Utility.isValidEmail(string_email)) {
            et_email.setError("Please Provide Valid Email Id");
            return;
        } else if (string_password.equalsIgnoreCase("")) {
            et_password.setError("Please Enter Password");
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(LoginActivityTest.this, HomeActivityTest.class));
                break;

  /*          case R.id.btn_login:
                login();
                break;
            case R.id.create_account:
                sendToActivity(RegisterActivity.class);
                break;
            case R.id.tv_forgot_password:
                sendToActivity(ForgetPasswordActivity.class);
                break;
  */      }
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
