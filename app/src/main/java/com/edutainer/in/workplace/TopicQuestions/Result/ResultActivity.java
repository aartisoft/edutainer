package com.edutainer.in.workplace.TopicQuestions.Result;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.edutainer.in.R;
import com.edutainer.in.workplace.Helper.AppPref;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.json.JSONObject;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements ResultContract.ResultView {
    Dialog dialog;

    ResultContract.ResultPresenter presenter;

    int correct = 0;
    int incorrect = 0;
    private float correct_percent;
    private float incorrect_percent;
    String topic_id ="";
    String course_id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar toolbar = findViewById(R.id.toolbar_result);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Result");

        correct = getIntent().getIntExtra("CORRECT", 0);
        incorrect = getIntent().getIntExtra("INCORRECT", 0);
        topic_id = getIntent().getStringExtra("TOPIC_ID");
        course_id = getIntent().getStringExtra("COURSE_ID");

        correct_percent = (float) (correct * 100) / (correct+ incorrect);
        incorrect_percent = (float) (incorrect * 100) / (correct + incorrect);

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(correct_percent, 0));
        yvalues.add(new Entry(incorrect_percent, 1));

        PieDataSet dataSet = new PieDataSet(yvalues, "");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Correct");
        xVals.add("InCorrect");

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.setDescription("Quiz DashBoard");
        pieChart.setDrawHoleEnabled(false);
        pieChart.setTransparentCircleRadius(58f);

        pieChart.setHoleRadius(0f);
        int[] COLORFUL_COLORS = {
                Color.rgb(21, 126, 251), Color.rgb(255, 64, 129), Color.rgb(255, 255, 255),
                Color.rgb(106, 150, 31), Color.rgb(179, 100, 53)
        };
        dataSet.setColors(COLORFUL_COLORS);

        data.setValueTextSize(15f);
        data.setValueTextColor(Color.DKGRAY);

        pieChart.animateXY(1400, 1400);

        presenter = new ResultPresenterImpl(this, new GetResultInteractionImpl());
        presenter.result(ResultActivity.this, topic_id, course_id, AppPref.getInstance().getUserId());
    }

    @Override
    public void showProgress() {
        dialog  = new Dialog(ResultActivity.this);
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
    public void handleResult(String string) {
        if (string.equalsIgnoreCase("No network")){
            //show no network available
        }else {
            try {
                JSONObject jsonObject = new JSONObject(string);
                String status = jsonObject.getString("status");
                if (status.equalsIgnoreCase("1")) {
                    Toast.makeText(ResultActivity.this, "Exam Over...", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
}
