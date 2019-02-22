package com.edutainer.in.workplace.Register;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edutainer.in.R;
import com.edutainer.in.workplace.Helper.AppPref;
import com.edutainer.in.workplace.HomeScreen.HomeActivity;
import com.edutainer.in.workplace.Login.LoginActivity;

import org.json.JSONObject;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.RegisterView,
        View.OnClickListener {
    EditText et_name;
    EditText et_mobile;
    EditText et_email;
    EditText et_password;
    CheckBox cb_terms;
    TextView tv_terms;
    Button btn_create_account;
    TextView tv_login;
    TextView tv_refer_code;
    Dialog dialog;

    private String gen_code = "";
    private String ref_code = "";
    private RegisterContract.RegisterPresenter presenter;

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

        presenter = new RegisterPresenterImpl(this, new GetRegisterInteractionImpl());

        et_name = findViewById(R.id.et_name);
        et_name.setTypeface(OpenSans_Regular);

        et_mobile = findViewById(R.id.et_mobile);
        et_mobile.setTypeface(OpenSans_Regular);

        et_email = findViewById(R.id.et_email);
        et_email.setTypeface(OpenSans_Regular);

        et_password = findViewById(R.id.et_password);
        et_password.setTypeface(OpenSans_Regular);

        cb_terms = findViewById(R.id.cb_terms);
        cb_terms.setTypeface(OpenSans_Regular);

        tv_terms = findViewById(R.id.tv_terms);
        tv_terms.setTypeface(OpenSans_Regular);
        tv_terms.setOnClickListener(this);

        btn_create_account = findViewById(R.id.btn_create_account);
        btn_create_account.setTypeface(OpenSans_Bold);
        btn_create_account.setOnClickListener(this);

        tv_login = findViewById(R.id.tv_login);
        tv_login.setTypeface(OpenSans_Regular);
        tv_login.setOnClickListener(this);

        tv_refer_code = findViewById(R.id.tv_refer_code);
        tv_refer_code.setTypeface(OpenSans_Regular);
        tv_refer_code.setOnClickListener(this);

    }

    @Override
    public void showProgress() {
        dialog  = new Dialog(RegisterActivity.this);
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
    public void handleRegister(String string) {
        System.out.println("RegisterActivity: handleRegister: " + string);
        if (string.equalsIgnoreCase("No network")){
            hideProgress();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RegisterActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
                }
            });
            //show no network available
        }else{
            try {
                JSONObject jsonObject = new JSONObject(string);
                String status = jsonObject.getString("status");
                final String message = jsonObject.getString("msg");
                if (status.equalsIgnoreCase("1")) {
                    String user_id = jsonObject.getString("user_id");

                    AppPref.getInstance().setUSERNAME(et_name.getText().toString());
                    AppPref.getInstance().setUSEREMAIL(et_email.getText().toString());
                    AppPref.getInstance().setUSERMOBILE(et_mobile.getText().toString());
                    AppPref.getInstance().setReferCode(ref_code);
                    AppPref.getInstance().setGenCode(gen_code);
                    AppPref.getInstance().setUserId(user_id);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgress();
                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            );
                        }
                    });

                } else {
                    if (message.contains("Duplicate entry") && message.contains("email")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "Email already exits", Toast.LENGTH_LONG).show();
                                hideProgress();
                            }
                        });
                    } else if (message.contains("Duplicate entry") && message.contains("mobile")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "Mobile No already exits", Toast.LENGTH_LONG).show();
                                hideProgress();
                            }
                        });
                    } else
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, ""+message, Toast.LENGTH_LONG).show();
                                hideProgress();
                            }
                        });
                }
            } catch (Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                        Toast.makeText(RegisterActivity.this, "Network error!! Try again", Toast.LENGTH_LONG).show();
                    }
                });

                e.printStackTrace();
            }
        }
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


    private void register() {
        String string_name = et_name.getText().toString();
        String string_mobile = et_mobile.getText().toString();
        String string_email = et_email.getText().toString();
        String string_password = et_password.getText().toString();


        if (string_name.equalsIgnoreCase("")) {
            et_name.setError("Please Enter Name");
            return;
        }
        else if (string_mobile.equalsIgnoreCase("") || string_mobile.length() != 10) {
            et_mobile.setError("Please Provide Correct Mobile Number");
            return;
        }
        else if (string_email.equalsIgnoreCase("") || !isValidEmail(string_email)) {
            et_email.setError("Please Provide Valid Email Id");
            return;
        } else if (string_password.equalsIgnoreCase("")) {
            et_password.setError("Please Enter Password");
            return;
        } else if (!cb_terms.isChecked()) {
            Toast.makeText(RegisterActivity.this, "To register, You must be agree with Terms & Conditions", Toast.LENGTH_LONG).show();
            return;
        }

        gen_code = string_name.substring(0, 2);
        Random r = new Random();
        int low = 10;
        int high = 100;
        int result = r.nextInt(high - low) + low;

        gen_code = gen_code + result;

        presenter.register(string_name, string_email, string_mobile, string_password, gen_code, ref_code, RegisterActivity.this);
        showProgress();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_create_account:
                register();
                break;
            case R.id.tv_terms:
//                sendToActivity(TermsConditionsActivity.class);
                break;
            case R.id.tv_login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
                break;
            case R.id.tv_refer_code:
                showReferDialog();
                break;

        }
    }

    @Override
    public void showReferDialog() {
        final Dialog dialog  = new Dialog(RegisterActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(true);
        //...that's the layout i told you will inflate later

        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_refer, null, false);
        dialog.setContentView(view);

        final EditText et_refer_code = view.findViewById(R.id.et_refer_code);
        Button btn_submit = view.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref_code = et_refer_code.getText().toString().trim();
                tv_refer_code.setText("Referral: " + ref_code);
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
