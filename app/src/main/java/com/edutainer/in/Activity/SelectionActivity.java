package com.edutainer.in.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.edutainer.in.Model.QuestionModel;
import com.edutainer.in.Model.VideoModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by TalkCharge on 27-03-2018.
 */

public class SelectionActivity extends BaseActivity implements View.OnClickListener, ApiResponse {
    private Button test;
    private Button videos;
    private String c_id;
    private String c_name;
    public static ArrayList<VideoModel> videoModelArrayList = new ArrayList<>();
    public static ArrayList<QuestionModel> questionModelArrayList = new ArrayList<>();

    @Override
    public void initialize(Bundle save) {
        save = getIntent().getExtras();
        if (save != null) {
            c_id = save.getString("c_id");
            c_name = save.getString("cat_name");
        }
        test = (Button) findViewById(R.id.test);
        videos = (Button) findViewById(R.id.videos);
        TextView category_name = (TextView) findViewById(R.id.category_name);
        TextView sub_category_name = (TextView) findViewById(R.id.sub_category_name);

        category_name.setText(c_name);
        sub_category_name.setText("Explore " + c_name);

        test.setOnClickListener(this);
        videos.setOnClickListener(this);

//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("cat_id=").append(c_id);
//
//        String content = stringBuilder.toString();
//
//        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.URL_OPTION_SELECTION, 0, content, true, "loading ...", this);
//        apiConsumer.execute();

    }


    @Override
    public void onBackPressed() {
        if (videoModelArrayList != null) {
            videoModelArrayList.clear();
        }
        if (questionModelArrayList != null) {
            questionModelArrayList.clear();
        }
        super.onBackPressed();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_selection;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test:
                sendToActivity(QuestionActivity.class);
                break;
            case R.id.videos:
                sendToActivity(VideoActivity.class);
                break;
        }
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            JSONArray jsonArray_study = jsonObject.getJSONArray("study");
            JSONArray jsonArray_video = jsonObject.getJSONArray("video");
            JSONArray jsonArray_question = jsonObject.getJSONArray("question");

            videoModelArrayList = new ArrayList<>(jsonArray_video.length());
            questionModelArrayList = new ArrayList<>(jsonArray_question.length());


            for (int i = 0; i < jsonArray_video.length(); i++) {
                JSONObject vjsonObject = jsonArray_video.getJSONObject(i);
                String index_id = vjsonObject.getString("index_id");
                String video_name = vjsonObject.getString("video_name");
                String url = vjsonObject.getString("url");
                videoModelArrayList.add(new VideoModel(index_id, video_name, url));
            }

            for (int i = 0; i < jsonArray_question.length(); i++) {
                JSONObject qjsonObject = jsonArray_question.getJSONObject(i);
                String q_name = qjsonObject.getString("q_name");
                String q_type = qjsonObject.getString("q_type");
                String option1 = qjsonObject.getString("option1");
                String option2 = qjsonObject.getString("option2");
                String option3 = qjsonObject.getString("option3");
                String option4 = qjsonObject.getString("option4");
                String correct_answer = qjsonObject.getString("correct_answer");
                String time = qjsonObject.getString("q_time");
                String alternate_question = qjsonObject.getString("alternate_question");

                QuestionModel questionModel = new QuestionModel();
                questionModel.setQuestion(q_name);
                questionModel.setQ_type(q_type);
                questionModel.setOption1(option1);
                questionModel.setOption2(option2);
                questionModel.setOption3(option3);
                questionModel.setOption4(option4);
                questionModel.setCorrect_answer(correct_answer);
                questionModel.setTime(time);
                questionModel.setAlternate_question(alternate_question);

                questionModelArrayList.add(questionModel);
            }

            if (questionModelArrayList.size() > 0) {
                test.setVisibility(View.VISIBLE);
            } else
                test.setVisibility(View.GONE);
            if (videoModelArrayList.size() > 0) {
                videos.setVisibility(View.VISIBLE);
            } else
                videos.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
