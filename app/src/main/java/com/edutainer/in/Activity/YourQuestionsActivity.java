package com.edutainer.in.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.edutainer.in.Adapter.CustomExpandableListAdapter;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class YourQuestionsActivity extends BaseActivity {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();
    private LinearLayout no_data;

    @Override
    public void initialize(Bundle save) {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        no_data = (LinearLayout) findViewById(R.id.no_data);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getUserId());


        String content = stringBuilder.toString();

        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.YOUR_QUESTIONS, 0, content, true, "loading ...", new ApiResponse() {
            @Override
            public void getApiResponse(String responseData, int serviceCounter) {

                try {
                    Log.d("responseData", responseData);
                    JSONArray jsonArray = new JSONArray(responseData);

                    if (jsonArray.length() > 0) {
                        expandableListView.setVisibility(View.VISIBLE);
                        no_data.setVisibility(View.GONE);
                    } else {
                        no_data.setVisibility(View.VISIBLE);
                        expandableListView.setVisibility(View.GONE);
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("forum_id");
                        String question = jsonObject.getString("question");
                        String answer = jsonObject.getString("answer");
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(answer);
                        expandableListDetail.put(question, arrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                expandableListAdapter = new CustomExpandableListAdapter(YourQuestionsActivity.this, expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(expandableListAdapter);
            }
        });

        apiConsumer.execute();

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            int previousGroup = 1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });

    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_your_questions;
    }
}
