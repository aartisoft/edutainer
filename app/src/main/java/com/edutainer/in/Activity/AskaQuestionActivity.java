package com.edutainer.in.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;

import org.json.JSONObject;

public class AskaQuestionActivity extends BaseActivity implements View.OnClickListener,ApiResponse
{
    private Button send;
    private EditText edit_query;
    private String id="";

    @Override
    public void initialize(Bundle save) {
         send=(Button) findViewById(R.id.send);
         edit_query=(EditText) findViewById(R.id.edit_query);

         save=getIntent().getExtras();
         if(save!=null)
         {
             id=save.getString("lesson_id");
         }
         send.setOnClickListener(this);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_ask_a_question;
    }

    @Override
    public void onClick(View view) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getUserId());
        stringBuilder.append("&lesson_id").append(id);
        stringBuilder.append("&question=").append(edit_query.getText().toString());


        String content=stringBuilder.toString();

        ApiConsumer apiConsumer=new ApiConsumer(this, AppUrl.ASK_QUESTION,0,content,true,"loading ...",this);
        apiConsumer.execute();
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try
        {
            Log.d("responseData",responseData);
            JSONObject jsonObject=new JSONObject(responseData);
            String status=jsonObject.getString("status");
            if(status.equalsIgnoreCase("1"))
            {
                finish();
            }
            toastMessage(jsonObject.getString("msg"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
