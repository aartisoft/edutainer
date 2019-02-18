package com.edutainer.in.Activity;

import android.os.Bundle;
import android.util.Log;

import com.edutainer.in.Fragment.ExamFragment;
import com.edutainer.in.Model.CategoryModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubCategoryGridActivity extends BaseActivity implements ApiResponse
{
    public static ArrayList<CategoryModel> arrayList;
    @Override
    public void initialize(Bundle save) {
        save=getIntent().getExtras();
        if(save!=null)
        {
            String id=save.getString("c_id");
            String name=save.getString("name");
            setTitle(name);
            getData(id);
        }
    }

    private void getData(String c_id) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("c_id=").append(c_id);

        String content = stringBuilder.toString();

        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.CATEGORY_URL, 0, content, true, "loading ...", this);
        apiConsumer.execute();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_subcategorygrid;
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            Log.d("responseData", responseData);
            if (responseData != null && !responseData.equalsIgnoreCase("null")) {
                JSONArray jsonArray = new JSONArray(responseData);
                arrayList = new ArrayList<>(jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String cat_id = jsonObject.getString("c_id");
                    String cat_name = jsonObject.getString("category_name");
                    String instructions = jsonObject.getString("date_added");
                    String active = jsonObject.getString("status");
                    String image = jsonObject.getString("icon");
                    String isend=jsonObject.getString("isend");
                    arrayList.add(new CategoryModel(cat_id, cat_name, active, instructions, image,isend));
                }
            }
            setFragment(new ExamFragment(),R.id.frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
