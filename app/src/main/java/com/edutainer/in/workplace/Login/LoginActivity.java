package com.edutainer.in.workplace.Login;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edutainer.in.Allen.HomeActivityTest;
import com.edutainer.in.Allen.LoginActivityTest;
import com.edutainer.in.R;
import com.edutainer.in.workplace.Helper.AppPref;
import com.edutainer.in.workplace.Helper.Preferences;
import com.edutainer.in.workplace.HomeScreen.HomeActivity;
import com.edutainer.in.workplace.Register.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView,
        View.OnClickListener {

    EditText et_email;
    EditText et_password;
    TextView tv_forgot_password;
    Button btn_login;
    TextView tv_create_account;
    ImageView iv_visibility;

    Dialog dialog;
    private LoginContract.LoginPresenter presenter;
    boolean isVisible = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_test);
        Toolbar toolbar = findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);

        presenter = new LoginPresenterImpl(this, new GetLoginInteractionImpl());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("Login");

        Typeface OpenSans_Regular = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Regular.ttf");
        Typeface OpenSans_Bold = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Bold.ttf");

        et_email = findViewById(R.id.et_email);
        et_email.setTypeface(OpenSans_Regular);

        et_password = findViewById(R.id.et_password);
        et_password.setTypeface(OpenSans_Regular);

        iv_visibility = findViewById(R.id.iv_visibility);
        iv_visibility.setOnClickListener(this);

        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        tv_forgot_password.setTypeface(OpenSans_Regular);
        tv_forgot_password.setOnClickListener(this);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setTypeface(OpenSans_Bold);
        btn_login.setOnClickListener(this);

        tv_create_account = findViewById(R.id.tv_create_account);
        tv_create_account.setTypeface(OpenSans_Regular);
        tv_create_account.setOnClickListener(this);

    }

    @Override
    public void showProgress() {
        dialog  = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(false);
        //...that's the layout i told you will inflate later
        dialog.setContentView(R.layout.progress_dialog);
        dialog.show();
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void handleLogin(String string) {
        System.out.println("LoginActivity: handleLogin: " + string);
        if (string.equalsIgnoreCase("No network")){
            hideProgress();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
                }
            });
            //show no network available
        }else{
            try {
                JSONObject jsonObject1 = new JSONObject(string);
                JSONArray jsonArray = jsonObject1.getJSONArray("login");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String user_id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String mobile = jsonObject.getString("mobile");
                String email = jsonObject.getString("email");
                String gen_code = jsonObject.getString("gen_code");

                AppPref.getInstance().setUserId(user_id);
                AppPref.getInstance().setUSERNAME(name);
                AppPref.getInstance().setUSERMOBILE(mobile);
                AppPref.getInstance().setUSEREMAIL(email);
                AppPref.getInstance().setGenCode(gen_code);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();

                        startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        );
                        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
                    }
                });

            } catch (Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                        Toast.makeText(LoginActivity.this, "Network error!! Try again", Toast.LENGTH_LONG).show();
                    }
                });
                e.printStackTrace();
            }
        }

    }

    @Override
    public void handleForgotPassword(String string) {
        System.out.println("LoginActivity: handleLogin: " + string);
        if (string.equalsIgnoreCase("No network")){
            hideProgress();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
                }
            });
            //show no network available
        }else{
            try {
                JSONObject jsonObject = new JSONObject(string);
                String status = jsonObject.getString("status");
                final String message = jsonObject.getString("msg");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "Network error!! Try again.", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }
        }

    }

    @Override
    public void showForgotDialog() {
        Dialog dialog  = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(true);
        //...that's the layout i told you will inflate later

        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_forgot, null, false);
        dialog.setContentView(view);

        final EditText et_email = view.findViewById(R.id.et_email);
        Button btn_submit = view.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, ""+et_email.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        super.onOptionsItemSelected(menuItem);
        switch (menuItem.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    public boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void login() {
        String string_email = et_email.getText().toString().trim();
        String string_password = et_password.getText().toString().trim();

        if (string_email.equalsIgnoreCase("") || !isValidEmail(string_email)) {
            et_email.setError("Please Provide Valid Email Id");
            return;
        } else if (string_password.equalsIgnoreCase("")) {
            et_password.setError("Please Enter Password");
            return;
        }

        presenter.login(string_email, string_password, LoginActivity.this);
        showProgress();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_forgot_password:
                showForgotDialog();
                break;
            case R.id.tv_create_account:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
                break;
            case R.id.iv_visibility:
                if(!isVisible){
                    et_password.setTransformationMethod(new HideReturnsTransformationMethod());
                } else{
                    et_password.setTransformationMethod(new PasswordTransformationMethod());
                }
                isVisible = !isVisible;
                break;
        }
    }

}
