package com.edutainer.in.workplace.CourseDetails;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.edutainer.in.R;
import com.edutainer.in.workplace.Checkout.CheckoutActivity;
import com.edutainer.in.workplace.Model.CourseModel;
import com.edutainer.in.workplace.Model.LessonModel;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity
        implements DetailContract.DetailView,
        View.OnClickListener {

    ImageView iv_title;
    WebView wv_title;
    TextView tv_title;
    TextView tv_description;
    TextView tv_more;
    TextView tv_topic;
    TextView tv_details;
    TextView tv_name;
    TextView tv_name_value;
    TextView tv_duration;
    TextView tv_duration_value;
    TextView tv_category;
    TextView tv_category_value;
    TextView tv_kit_cost;
    TextView tv_kit_cost_value;
    TextView tv_course_cost;
    TextView tv_course_cost_value;
    Button btn_buy;
    CheckBox hardware_kit;
    RecyclerView rv_topics;
    Dialog dialog;

    CourseModel courseModel;
    DetailContract.DetailPresenter presenter;
    ArrayList<LessonModel> listLessons;
    RecyclerAdapterTopics adapterTopics;

    boolean isTitleExpand = false;
    String mode = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface OpenSans_Regular = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Regular.ttf");
        Typeface OpenSans_Bold = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Bold.ttf");
        Typeface OpenSans_SemiBold = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Semibold.ttf");

        presenter = new DetailPresenterImpl(this, new GetDetailInteractionImpl());

        courseModel = getIntent().getParcelableExtra("COURSE");
        mode = getIntent().getStringExtra("MODE");
        presenter.lessons(DetailActivity.this, courseModel.getId()+"");
        setTitle(courseModel.getCourse_name());

        wv_title = findViewById(R.id.wv_title);
        wv_title.getSettings().setJavaScriptEnabled(true);
        wv_title.loadUrl(courseModel.getCourse_preview());

        tv_title = findViewById(R.id.tv_title);
        tv_title.setTypeface(OpenSans_Bold);
        tv_title.setText(courseModel.getCourse_name());

        tv_description = findViewById(R.id.tv_description);
        tv_description.setTypeface(OpenSans_SemiBold);
        tv_description.setText(courseModel.getCourse_desc());

        tv_more = findViewById(R.id.tv_more);
        tv_more.setTypeface(OpenSans_SemiBold);
        tv_more.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isTitleExpand) {
                    isTitleExpand = true;
                    ObjectAnimator animation = ObjectAnimator.ofInt(tv_description, "maxLines", 40);
                    animation.setDuration(100).start();
                    tv_more.setText("read less");
                } else {
                    isTitleExpand = false;
                    ObjectAnimator animation = ObjectAnimator.ofInt(tv_description, "maxLines", 3);
                    animation.setDuration(100).start();
                    tv_more.setText("read more");
                }

            }
        });

        tv_topic = findViewById(R.id.tv_topic);
        tv_topic.setTypeface(OpenSans_Bold);

        tv_details = findViewById(R.id.tv_details);
        tv_details.setTypeface(OpenSans_Bold);

        tv_name = findViewById(R.id.tv_name);
        tv_name.setTypeface(OpenSans_SemiBold);

        tv_name_value = findViewById(R.id.tv_name_value);
        tv_name_value.setText(courseModel.getCourse_name());
        tv_name_value.setTypeface(OpenSans_SemiBold);

        tv_duration = findViewById(R.id.tv_duration);
        tv_duration.setTypeface(OpenSans_SemiBold);

        tv_duration_value = findViewById(R.id.tv_duration_value);
        tv_duration_value.setText(courseModel.getCourse_duration());
        tv_duration_value.setTypeface(OpenSans_SemiBold);

        tv_category = findViewById(R.id.tv_category);
        tv_category.setTypeface(OpenSans_SemiBold);

        tv_category_value = findViewById(R.id.tv_category_value);
        tv_category_value.setText(courseModel.getCat_name());
        tv_category_value.setTypeface(OpenSans_SemiBold);

        tv_kit_cost = findViewById(R.id.tv_kit_cost);
        tv_kit_cost.setTypeface(OpenSans_SemiBold);

        tv_kit_cost_value = findViewById(R.id.tv_kit_cost_value);
        tv_kit_cost_value.setText("₹ " +courseModel.getKit_cost());
        tv_kit_cost_value.setTypeface(OpenSans_SemiBold);

        tv_course_cost = findViewById(R.id.tv_course_cost);
        tv_course_cost.setTypeface(OpenSans_SemiBold);

        tv_course_cost_value = findViewById(R.id.tv_course_cost_value);
        tv_course_cost_value.setText("₹ " +courseModel.getCourse_cost());
        tv_course_cost_value.setTypeface(OpenSans_SemiBold);

        btn_buy = findViewById(R.id.btn_buy);
        btn_buy.setTypeface(OpenSans_Bold);
        btn_buy.setOnClickListener(this);

        hardware_kit = findViewById(R.id.hardware_kit);
        hardware_kit.setTypeface(OpenSans_SemiBold);

        rv_topics = findViewById(R.id.rv_topics);
        listLessons = new ArrayList<>();
        rv_topics.setLayoutManager(new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.VERTICAL, false));
        adapterTopics = new RecyclerAdapterTopics(DetailActivity.this, listLessons, courseModel, mode);
        rv_topics.setAdapter(adapterTopics);
    }

    @Override
    public void showProgress() {
        dialog  = new Dialog(DetailActivity.this);
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
    public void handleLesson(String string) {
        System.out.println("DetailActivity: handleLesson: " + string);
        if (string.equalsIgnoreCase("No network")){
            //show no network available
        }else{
            try {
                JSONArray jsonArray = new JSONArray(string);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String course_id = jsonObject.getString("course_id");
                    String name = jsonObject.getString("name");
                    String details=jsonObject.getString("lesson_details");
                    listLessons.add(new LessonModel(id, course_id, name, details));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterTopics.notifyDataSetChanged();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void buy(){
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra("CHECK", hardware_kit.isChecked());
        intent.putExtra("COURSE", courseModel);
        intent.putExtra("IMAGE", getIntent().getIntExtra("IMAGE", R.drawable.ic_iot_basics));
        startActivity(intent);
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_buy:
               buy();
        }
    }
}
