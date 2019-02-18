package com.edutainer.in.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;

import com.edutainer.in.Model.CourseModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplashActivity extends RuntimePermissionsActivity implements ApiResponse {

    public static ArrayList<CourseModel> courseModels = new ArrayList<>();


    @Override
    public void initialize(Bundle save) {
        getSupportActionBar().hide();
        loadCategories();
        //decision();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_splash;
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
                if (AppPref.getInstance().getUSEREMAIL().equalsIgnoreCase("")) {
                    sendToActivity(FirstActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                } else {
                    sendToActivity(DrawerActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                }
            }
        }, 1000);
    }


    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            Log.d("responseData", responseData);
            JSONArray jsonArray = new JSONArray(responseData);
            courseModels = new ArrayList<>(jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String cat_name = jsonObject.getString("name");
                int cat_id = jsonObject.getInt("category_id");
                int id = jsonObject.getInt("id");
                String web_img = jsonObject.getString("web_image");
                String course_name = jsonObject.getString("course_name");
                String course_preview = jsonObject.getString("course_preview");
                String course_cost = jsonObject.getString("course_cost");
                String course_term = jsonObject.getString("course_term");
                String course_short_desc = jsonObject.getString("course_short_desc");
                String course_kit = jsonObject.getString("kit_cost");
                String course_duration = jsonObject.getString("course_duration");
                String status = jsonObject.getString("status");


                CourseModel courseModel = new CourseModel();
                courseModel.setCat_id(cat_id);
                courseModel.setCat_name(cat_name);
                courseModel.setId(id);
                courseModel.setCourse_name(course_name);
                courseModel.setCourse_preview(course_preview);
                courseModel.setWeb_image(web_img);
                courseModel.setCourse_term(course_term);
                courseModel.setCourse_cost(course_cost);
                courseModel.setCourse_duration(course_duration);
                courseModel.setStatus(status);
                courseModel.setCourse_short_desc(course_short_desc);
                courseModel.setCourse_kit(course_kit);
                courseModels.add(courseModel);

            }


            decision();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
