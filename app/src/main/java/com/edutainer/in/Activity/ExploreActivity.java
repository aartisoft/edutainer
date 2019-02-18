package com.edutainer.in.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.edutainer.in.Adapter.SingleAdapter;
import com.edutainer.in.Model.LessonModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends BaseActivity implements SingleAdapter.ReturnView, View.OnClickListener, ApiResponse {

    private ListView gridview;
    private ArrayList<LessonModel> arrayList = new ArrayList<>();
    private Button buy_now;
    private final String TAG = "EXPLORE_ACTIVITY";
    private String course_id = "";
    private String kit_cost = "";
    private String course_preview = "";
    private String course_cost = "";
    private String course_duration = "";
    private String course_term = "";
    private TextView price;
    private TextView duration;
    private TextView requirements;

    private TextView course_includes;
    private TextView lessons;
    private CheckBox hardware_kit;

    public void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(getActivityLayout());
        initialize(save);
    }


    public void initialize(Bundle save) {
        setTitle("IOT");
        gridview = (ListView) findViewById(R.id.gridview);
        buy_now = (Button) findViewById(R.id.buy_now);
        course_includes = (TextView) findViewById(R.id.course_includes);
        lessons = (TextView) findViewById(R.id.lessons);
        price = (TextView) findViewById(R.id.price);
        duration = (TextView) findViewById(R.id.duration);
        requirements = (TextView) findViewById(R.id.requirements);
        hardware_kit = (CheckBox) findViewById(R.id.hardware_kit);

        save = getIntent().getExtras();
        if (save != null) {
            course_id = save.getString("id");
            setTitle(save.getString("name"));
            course_preview = save.getString("preview");
            course_term = save.getString("course_term");
            course_cost = save.getString("course_cost");
            course_duration = save.getString("course_duration");
            kit_cost = save.getString("kit_cost");

            price.setText(course_cost + " INR");
            duration.setText(course_duration);
            requirements.setText(course_term);
            course_includes.setText(save.getString("course_desc"));

            hardware_kit.setText("Include Hardware kit (Cost : " + kit_cost + " INR)");
//            setDummyData(course_id);
        }

        // course_includes.setText("1. 10 Video Lectures\n2. 11 Quizzes\n3. Certificate of Completion");

        WebView videoplayer = (WebView) findViewById(R.id.videoplayer);
        videoplayer.getSettings().setJavaScriptEnabled(true);
        videoplayer.loadUrl(course_preview);


        buy_now.setOnClickListener(this);
        lessons.setOnClickListener(this);

        Checkout.preload(getApplicationContext());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getUserId());
        stringBuilder.append("&course_id=").append(course_id);

        String content = stringBuilder.toString();

        Log.d("content", content);


        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.COURSES_ASSIGNED, 0, content, true, "loading ...", new ApiResponse() {
            @Override
            public void getApiResponse(String responseData, int serviceCounter) {
                try {
                    Log.d("responseData", responseData);
                    JSONArray jsonArray = new JSONArray(responseData);
                    if (jsonArray.length() != 0) {
                        buy_now.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        apiConsumer.execute();

    }


    public int getActivityLayout() {
        return R.layout.activity_explore;
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        final TextView category_name = (TextView) view.findViewById(R.id.category_name);
        TextView nofquestion = (TextView) view.findViewById(R.id.nofquestion);
        ImageView forward = (ImageView) view.findViewById(R.id.forward);

        forward.setVisibility(View.GONE);

        final LessonModel lessonModel = arrayList.get(position);
        category_name.setText(lessonModel.getName());
        nofquestion.setText(lessonModel.getDetails());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_now:
                Intent intent = new Intent(this, CheckOutActivity.class);
                intent.putExtra("check", hardware_kit.isChecked() + "");
                intent.putExtra("product", getTitle());
                intent.putExtra("price", course_cost);
                intent.putExtra("kit_cost", kit_cost);
                intent.putExtra("course_id", course_id);
                startActivity(intent);
                break;
            case R.id.lessons:
                seelessons();
                break;
        }
    }


    private void seelessons() {
        Intent intent = new Intent(this, SubCategoryActivity.class);
        intent.putExtra("c_id", course_id);
        intent.putExtra("name", getTitle());
        intent.putExtra("mode", "locked");
        startActivity(intent);
    }


    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String lesson_details = jsonObject.getString("lesson_details");
                arrayList.add(new LessonModel(id, name, lesson_details));
            }

            gridview.setAdapter(new SingleAdapter(this, R.layout.layout_subcateogry_single_item, arrayList, this, 0));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
