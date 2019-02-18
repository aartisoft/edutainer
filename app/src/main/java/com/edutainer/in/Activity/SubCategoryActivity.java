package com.edutainer.in.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.edutainer.in.Adapter.SingleAdapter;
import com.edutainer.in.Model.LessonModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TalkCharge on 03-03-2018.
 */

public class SubCategoryActivity extends BaseActivity implements SingleAdapter.ReturnView, ApiResponse {

    private ListView gridview;
    private String c_id = "";
    private String c_name = "";
    private ArrayList<LessonModel> arrayList = new ArrayList<>();
    private String mode="locked";

    @Override
    public void initialize(Bundle save) {
        setTitle("Select Category");
        gridview = (ListView) findViewById(R.id.gridview);


        save = getIntent().getExtras();
        if (save != null) {
            c_id = save.getString("c_id");
            setTitle(save.getString("name"));
            mode=save.getString("mode");
        }


        getData(c_id);
    }

    private void getData(String c_id) {
        this.c_id = c_id;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id=").append(c_id);


        String content = stringBuilder.toString();


        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.LESSON_URL, 0, content, true, "loading ...", this);
        apiConsumer.execute();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_subcategory;
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        final TextView category_name = (TextView) view.findViewById(R.id.category_name);
        TextView nofquestion = (TextView) view.findViewById(R.id.nofquestion);

        final LessonModel categoryModel = arrayList.get(position);
        category_name.setText(categoryModel.getName());
        nofquestion.setText(categoryModel.getDetails());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode.equalsIgnoreCase("unlocked"))
                sendToActivity(VideoActivity.class,new String[]{"id;"+categoryModel.getId(),"title;"+categoryModel.getName(),"course_id;"+c_id});
                else
                    toastMessage("Please Buy the course First!");
            }
        });

    }

    private void decision(String isend, String id, String name) {
        switch (isend) {
            case "0":
                toastMessage("Coming Soon");
                break;
            case "1":
                sendToActivity(SelectionActivity.class, new String[]{"c_id;" + id, "cat_name;" + name});
                break;
            case "2":
                getData(id);
//                sendToActivity(SubCategoryActivity.class, new String[]{"c_id;" + id, "cat_name;" + name});
                break;
            case "3":
                sendToActivity(SubCategoryGridActivity.class, new String[]{"c_id;" + id, "cat_name;" + name});
                break;
        }
    }


    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String details=jsonObject.getString("lesson_details");
                arrayList.add(new LessonModel(id, name,details));
            }

            gridview.setAdapter(new SingleAdapter(this, R.layout.layout_subcateogry_single_item, arrayList, this, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
