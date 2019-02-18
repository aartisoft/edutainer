package com.edutainer.in.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.edutainer.in.Adapter.SingleAdapter;
import com.edutainer.in.Model.TransactionalModel;
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


public class TransactionsActivity extends BaseActivity implements SingleAdapter.ReturnView, ApiResponse {
    private ListView listview;
    private ArrayList<TransactionalModel> transactionalModelArrayList = new ArrayList<>();
    private LinearLayout no_data;

    @Override
    public void initialize(Bundle save) {
        setTitle("Transactions");
        listview = (ListView) findViewById(R.id.listview);
        no_data=(LinearLayout) findViewById(R.id.no_data);
        dummydata();

    }


    private void dummydata() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id=").append(AppPref.getInstance().getUserId());

        String content = stringBuilder.toString();

        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.TRANSACTION_URL, 0, content, true, "loading ...", this);
        apiConsumer.execute();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_transactions;
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        TransactionalModel transactionalModel = transactionalModelArrayList.get(position);
        TextView course_name = (TextView) view.findViewById(R.id.course_name);
        TextView txn_id = (TextView) view.findViewById(R.id.txn_id);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView details = (TextView) view.findViewById(R.id.details);
        View colorview = (View) view.findViewById(R.id.colorview);

        course_name.setText(transactionalModel.getCourse_name());
        txn_id.setText("Txn ID : "+transactionalModel.getTxn_id());
        price.setText("\u20A8 "+transactionalModel.getPrice());
        date.setText(transactionalModel.getTxn_date());
        if (transactionalModel.getTxn_status().equalsIgnoreCase("paid")) {
            details.setText("SUCCESS");
            colorview.setBackgroundColor(getResources().getColor(R.color.green));
        }
        else {
            details.setText("FAILURE");
            colorview.setBackgroundColor(Color.RED);
        }

    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            JSONArray jsonArray = new JSONArray(responseData);
            if(jsonArray.length()>0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String course_name = jsonObject.getString("course_name");
                    String course_cost = jsonObject.getString("course_cost");
                    String date_purchased=jsonObject.getString("date_purchased");
                    String t_status = jsonObject.getString("t_status");

                    transactionalModelArrayList.add(new TransactionalModel(id, course_name, date_purchased, course_cost, t_status));

                }

                listview.setAdapter(new SingleAdapter(this, R.layout.layout_transaction_single_item, transactionalModelArrayList, this, 0));
                listview.setVisibility(View.VISIBLE);
                no_data.setVisibility(View.GONE);
            }
            else
            {
                listview.setVisibility(View.GONE);
                no_data.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
