package com.edutainer.in.workplace.TopicQuestions;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.edutainer.in.R;
import com.edutainer.in.workplace.Model.QuestionModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity implements QuestionsContract.QuestionsView{
    RecyclerView rv_questions;
    Dialog dialog;

    String topic_id ="";
    String course_id ="";
    RecyclerAdapterQuestions adapter;
    private ArrayList<QuestionModel> listQuestions;
    QuestionsContract.QuestionsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Toolbar toolbar = findViewById(R.id.toolbar_question);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Question 1");

        topic_id = getIntent().getStringExtra("TOPIC_ID");
        course_id = getIntent().getStringExtra("COURSE_ID");

        rv_questions = findViewById(R.id.rv_questions);
        CustomLayoutManager llm = new CustomLayoutManager(getBaseContext());
        llm.setHorizontalScrollEnabled(false);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_questions.setLayoutManager(llm);
        listQuestions = new ArrayList<>();
        adapter = new RecyclerAdapterQuestions(QuestionsActivity.this, listQuestions, topic_id, course_id);
        rv_questions.setAdapter(adapter);

        presenter = new QuestionsPresenterImpl(this, new GetQuestionsInteractionImpl());
        presenter.Questions(QuestionsActivity.this, topic_id);
    }
    @Override
    public void showProgress() {
        dialog  = new Dialog(QuestionsActivity.this);
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
    public void handleQuestions(String string) {
        System.out.println("QuestionsActivity: handleQuestions: " + string);
        if (string.equalsIgnoreCase("No network")){
            //show no network available
        }else {
            try {
                JSONArray jsonArray = new JSONArray(string);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String q_name = jsonObject.getString("name");
                    String option1 = jsonObject.getString("option1");
                    String option2 = jsonObject.getString("option2");
                    String option3 = jsonObject.getString("option3");
                    String option4 = jsonObject.getString("option4");
                    String correct_answer = jsonObject.getString("correct_answer");

                    listQuestions.add(new QuestionModel(id, q_name, option1, option2, option3, option4, correct_answer, "false"));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
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

    public void nextQuestion(int position) {
        if (position < (listQuestions.size() - 1)) {
            rv_questions.scrollToPosition(position + 1);
            setTitle("Question " + (position + 2));
        }else if (position == listQuestions.size()-1){

        }
//        else {
//            result();
//        }
    }
}
