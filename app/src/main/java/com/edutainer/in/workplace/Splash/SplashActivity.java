package com.edutainer.in.workplace.Splash;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.edutainer.in.Allen.HomeActivityTest;
import com.edutainer.in.R;
import com.edutainer.in.workplace.FirstScreen.FirstActivity;
import com.edutainer.in.workplace.Helper.AppPref;
import com.edutainer.in.workplace.HomeScreen.HomeActivity;
import com.edutainer.in.workplace.Model.CourseModel;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements SplashContract.SplashView {
    ImageView iv_splash;

    public static ArrayList<CourseModel> courseModels = new ArrayList<>();
    SplashContract.SplashPresenter presenter;
    Dialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_test);
        presenter = new SplashPresenterImpl(this, new GetSplashInteractionImpl());

        getSupportActionBar().hide();

        presenter.loadModel(SplashActivity.this);
        iv_splash = findViewById(R.id.iv_splash);
        Glide.with(SplashActivity.this)
                .asGif()
                .load(R.drawable.logo_gif)
                .into(iv_splash);
    }

    @Override
    public void showProgress() {
        dialog  = new Dialog(SplashActivity.this);
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
    public void handleLoadModel(String string) {
        System.out.println("Splash: handleLoadModel response" + string);
        if (string.equalsIgnoreCase("No network")){
            //show no network available
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SplashActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
                }
            });
        }
        else if (string.contains("Exception")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SplashActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            try {
                courseModels = new ArrayList<>();
                JSONArray jsonArray = new JSONArray(string);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int category_id = jsonObject.getInt("category_id");
                    String course_cost = jsonObject.getString("course_cost");
                    String course_desc = jsonObject.getString("course_desc");
                    String course_duration = jsonObject.getString("course_duration");
                    String course_name = jsonObject.getString("course_name");
                    String course_period = jsonObject.getString("course_period");
                    String course_preview = jsonObject.getString("course_preview");
                    String course_short_desc = jsonObject.getString("course_short_desc");
                    String course_term = jsonObject.getString("course_term");
                    int id = jsonObject.getInt("id");
                    String kit_cost = jsonObject.getString("kit_cost");
                    String cat_name = jsonObject.getString("name");
                    String status = jsonObject.getString("status");
                    String web_img = jsonObject.getString("web_image");

                    courseModels.add(new CourseModel(
                            category_id,
                            course_cost,
                            course_desc,
                            course_duration,
                            course_name,
                            course_period,
                            course_preview,
                            course_short_desc,
                            course_term,
                            id,
                            kit_cost,
                            cat_name,
                            status,
                            web_img));
                }

                decision();
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SplashActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    private void decision() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (AppPref.getInstance().getUSEREMAIL().equalsIgnoreCase("")) {
                    startActivity(new Intent(SplashActivity.this, FirstActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    );
                } else {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    );
                }
             /*   Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 4000);*/
            }
        });

    }

}
