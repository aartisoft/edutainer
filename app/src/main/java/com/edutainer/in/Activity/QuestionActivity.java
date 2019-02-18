package com.edutainer.in.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.edutainer.in.Model.QuestionModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by TalkCharge on 03-03-2018.
 */

public class QuestionActivity extends BaseActivity implements ApiResponse, View.OnClickListener {
    private ArrayList<QuestionModel> questionModelArrayList = new ArrayList();
    private TextView question;
    private TextView option1;
    private TextView option2;
    private TextView option3;
    private TextView option4;
    private TextView next;

    private int qno = 0;

    private String cat_id;



    @Override
    public void initialize(Bundle save) {


        setTitle("Question " + (qno + 1));

        question = (TextView) findViewById(R.id.question);
        option1 = (TextView) findViewById(R.id.option1);
        option2 = (TextView) findViewById(R.id.option2);
        option3 = (TextView) findViewById(R.id.option3);
        option4 = (TextView) findViewById(R.id.option4);
        next = (TextView) findViewById(R.id.next);

        save = getIntent().getExtras();
        if (save != null) {
            cat_id = save.getString("id");
        }

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        next.setOnClickListener(this);

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("id=").append(cat_id);

        String content=stringBuilder.toString();

        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.QUESTION_URL, 0, content, true, "loading ...", this);
        apiConsumer.execute();

    }


    @Override
    public int getActivityLayout() {
        return R.layout.activity_question;
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            Log.d("responseData", responseData);
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String q_name = jsonObject.getString("name");
                String option1 = jsonObject.getString("option1");
                String option2 = jsonObject.getString("option2");
                String option3 = jsonObject.getString("option3");
                String option4 = jsonObject.getString("option4");
                String correct_answer = jsonObject.getString("correct_answer");

                QuestionModel questionModel = new QuestionModel();
                questionModel.setQuestion(q_name);
                questionModel.setOption1(option1);
                questionModel.setOption2(option2);
                questionModel.setOption3(option3);
                questionModel.setOption4(option4);
                questionModel.setCorrect_answer(correct_answer);

                questionModelArrayList.add(questionModel);
            }
            showquestion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showquestion() {
        QuestionModel questionModel = questionModelArrayList.get(qno);
        question.setText(Html.fromHtml(questionModel.getQuestion()));

        option1.setBackground(getResources().getDrawable(R.drawable.background_rounded));
        option2.setBackground(getResources().getDrawable(R.drawable.background_rounded));
        option3.setBackground(getResources().getDrawable(R.drawable.background_rounded));
        option4.setBackground(getResources().getDrawable(R.drawable.background_rounded));
        option1.setText(Html.fromHtml(questionModel.getOption1()));
        option2.setText(Html.fromHtml(questionModel.getOption2()));
        option3.setText(Html.fromHtml(questionModel.getOption3()));
        option4.setText(Html.fromHtml(questionModel.getOption4()));
        next.setText("Submit");
        option2.setTextColor(getResources().getColor(R.color.background_color));
        option1.setTextColor(getResources().getColor(R.color.background_color));
        option3.setTextColor(getResources().getColor(R.color.background_color));
        option4.setTextColor(getResources().getColor(R.color.background_color));
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
    }

    private void decision() {
        switch (questionModelArrayList.get(qno).getCorrect_answer()) {
            case "1":
                option1.setTextColor(Color.WHITE);
                option3.setTextColor(getResources().getColor(R.color.white));
                option2.setTextColor(getResources().getColor(R.color.white));
                option4.setTextColor(getResources().getColor(R.color.white));
                option1.setBackground(getResources().getDrawable(R.drawable.background_round_green));
                option2.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                option3.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                option4.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                break;
            case "2":
                option2.setTextColor(Color.WHITE);
                option1.setTextColor(getResources().getColor(R.color.white));
                option3.setTextColor(getResources().getColor(R.color.white));
                option4.setTextColor(getResources().getColor(R.color.white));
                option1.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                option2.setBackground(getResources().getDrawable(R.drawable.background_round_green));
                option3.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                option4.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                break;
            case "3":
                option2.setTextColor(Color.WHITE);
                option1.setTextColor(getResources().getColor(R.color.white));
                option3.setTextColor(getResources().getColor(R.color.white));
                option4.setTextColor(getResources().getColor(R.color.white));
                option1.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                option2.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                option3.setBackground(getResources().getDrawable(R.drawable.background_round_green));
                option4.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                break;
            case "4":
                option2.setTextColor(Color.WHITE);
                option1.setTextColor(getResources().getColor(R.color.white));
                option3.setTextColor(getResources().getColor(R.color.white));
                option4.setTextColor(getResources().getColor(R.color.white));
                option1.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                option2.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                option3.setBackground(getResources().getDrawable(R.drawable.background_round_red));
                option4.setBackground(getResources().getDrawable(R.drawable.background_round_green));
                break;
        }

        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);
        next.setText("Next");
    }

    private void nextQuestion() {
        if (qno < (questionModelArrayList.size() - 1)) {
            qno += 1;
            setTitle("Question " + (qno + 1));
            showquestion();
        } else {
            result();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                if (next.getText().toString().equalsIgnoreCase("Submit"))
                    decision();
                else
                    nextQuestion();
                break;
            case R.id.option1:
                if (questionModelArrayList.get(qno).getCorrect_answer().equalsIgnoreCase("1")) {
                    questionModelArrayList.get(qno).setSelection("true");
                } else
                    questionModelArrayList.get(qno).setSelection("false");
                option1.setTextColor(Color.WHITE);
                option3.setTextColor(getResources().getColor(R.color.background_color));
                option2.setTextColor(getResources().getColor(R.color.background_color));
                option4.setTextColor(getResources().getColor(R.color.background_color));
                option1.setBackground(getResources().getDrawable(R.drawable.background_round_green));
                option2.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                option3.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                option4.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                break;
            case R.id.option2:
                if (questionModelArrayList.get(qno).getCorrect_answer().equalsIgnoreCase("2")) {
                    questionModelArrayList.get(qno).setSelection("true");
                } else
                    questionModelArrayList.get(qno).setSelection("false");
                option2.setTextColor(Color.WHITE);
                option1.setTextColor(getResources().getColor(R.color.background_color));
                option3.setTextColor(getResources().getColor(R.color.background_color));
                option4.setTextColor(getResources().getColor(R.color.background_color));
                option2.setBackground(getResources().getDrawable(R.drawable.background_round_green));
                option1.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                option3.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                option4.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                break;
            case R.id.option3:
                if (questionModelArrayList.get(qno).getCorrect_answer().equalsIgnoreCase("3")) {
                    questionModelArrayList.get(qno).setSelection("true");
                } else
                    questionModelArrayList.get(qno).setSelection("false");
                option3.setBackground(getResources().getDrawable(R.drawable.background_round_green));
                option3.setTextColor(Color.WHITE);
                option1.setTextColor(getResources().getColor(R.color.background_color));
                option2.setTextColor(getResources().getColor(R.color.background_color));
                option4.setTextColor(getResources().getColor(R.color.background_color));
                option2.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                option1.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                option4.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                break;
            case R.id.option4:
                if (questionModelArrayList.get(qno).getCorrect_answer().equalsIgnoreCase("4")) {
                    questionModelArrayList.get(qno).setSelection("true");
                } else
                    questionModelArrayList.get(qno).setSelection("false");
                option4.setTextColor(Color.WHITE);
                option1.setTextColor(getResources().getColor(R.color.background_color));
                option2.setTextColor(getResources().getColor(R.color.background_color));
                option3.setTextColor(getResources().getColor(R.color.background_color));
                option4.setBackground(getResources().getDrawable(R.drawable.background_round_green));
                option2.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                option3.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                option1.setBackground(getResources().getDrawable(R.drawable.background_rounded));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void discardQuiz() {
        AlertDialog.Builder adb = new AlertDialog.Builder(QuestionActivity.this);
        adb.setTitle("Leave Quiz");
        adb.setMessage("Are you sure you want to discard the quiz?");
        adb.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                result();
            }
        });
        adb.setNegativeButton("NO", null);
        adb.show();
    }

    private void result() {
        finish();
        Intent intent = new Intent(QuestionActivity.this, DashBoardActivity.class);
        int correct = 0;
        int incorrect = 0;
        for (int i = 0; i < questionModelArrayList.size(); i++) {
            if (questionModelArrayList.get(i).getSelection().equalsIgnoreCase("true")) {
                correct += 1;
            } else
                incorrect += 1;
        }
        intent.putExtra("correct", correct);
        intent.putExtra("incorrect", incorrect);
        startActivity(intent);
    }
}
