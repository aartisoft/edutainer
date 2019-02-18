package com.edutainer.in.Activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by TalkCharge on 08-03-2018.
 */

public class DashBoardActivity extends BaseActivity {

    private float correct_percent;
    private float incorrect_percent;


    @Override
    public void initialize(Bundle save) {

        save = getIntent().getExtras();
        if (save != null) {
            int x = save.getInt("correct");
            int y = save.getInt("incorrect");
            String online = save.getString("online");
            if (online != null && !online.equalsIgnoreCase("") && online.equalsIgnoreCase("true")) {
                String c_id = save.getString("c_id");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("quiz_id=").append(c_id);
                stringBuilder.append("&user_id=").append(AppPref.getInstance().getUserId());
                stringBuilder.append("&number=").append(x + "");
                stringBuilder.append("&date=").append(returnDateTime());

                String content = stringBuilder.toString();

                ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.EXAM_GIVEN, 0, content, true, "loadding ...", new ApiResponse() {
                    @Override
                    public void getApiResponse(String responseData, int serviceCounter) {
                        try {
                            Log.d("responseData", responseData);
                            JSONObject jsonObject = new JSONObject(responseData);
                            String status = jsonObject.getString("status");
                            if (status.equalsIgnoreCase("1")) {
                                toastMessage("Exam Over.");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                apiConsumer.execute();
            }
            correct_percent = (x * 100) / (x + y);
            incorrect_percent = (y * 100) / (x + y);
        }

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
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_dashbaord;
    }

    public void backtohome(View view) {
        sendToActivity(DrawerActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    private String returnDateTime() {
        String datetime = "";
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        datetime = year + ":" + month + ":" + date + " " + hour + ":" + minute + ":" + second;
        return datetime;
    }
}
