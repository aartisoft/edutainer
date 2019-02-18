package com.edutainer.in.Allen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.edutainer.in.Activity.DrawerActivity;
import com.edutainer.in.Activity.RuntimePermissionsActivity;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.workplace.Model.CourseModel;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class SplashActivityTest extends RuntimePermissionsActivity implements ApiResponse {
    ImageView iv_splash;
    public static ArrayList<CourseModel> courseModels = new ArrayList<>();

    @Override
    public void initialize(Bundle save) {
        getSupportActionBar().hide();
        loadCategories();

        iv_splash = findViewById(R.id.iv_splash);
        Glide.with(SplashActivityTest.this)
                .asGif()
                .load(R.drawable.logo_gif)
                .into(iv_splash);

        //decision();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_splash_test;
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    private void loadCategories() {
        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.CATEGORY_URL, 0, "", false, "loading ...", this);
        apiConsumer.execute();
    }

    private void decision() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("list size " + courseModels.size());
                if (AppPref.getInstance().getUSEREMAIL().equalsIgnoreCase("")) {
                    sendToActivity(FirstActivityTest.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                } else {
                    sendToActivity(DrawerActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                }
            }
        }, 5000);
    }


    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            Log.d("responseData", responseData);
            JSONArray jsonArray = new JSONArray(responseData);
            courseModels = new ArrayList<>(jsonArray.length());
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
        }
    }
}
