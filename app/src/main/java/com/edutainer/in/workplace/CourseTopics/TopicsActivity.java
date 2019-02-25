package com.edutainer.in.workplace.CourseTopics;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.edutainer.in.Activity.WebViewActivity;
import com.edutainer.in.R;
import com.edutainer.in.workplace.Model.TopicModel;
import com.edutainer.in.workplace.TopicDoubts.DoubtActivity;
import com.edutainer.in.workplace.TopicQuestions.QuestionsActivity;
import com.edutainer.in.workplace.TopicVideo.VideoActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopicsActivity extends AppCompatActivity implements TopicContract.TopicView, ListAdapterTopics.ReturnView {
    private ListView listview;
    Dialog dialog;

    private ArrayList<TopicModel> listTopics = new ArrayList<>();
    private String topic_id = "";
    private String course_id = "";
    private String title = "";

    TopicContract.TopicPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        Toolbar toolbar = findViewById(R.id.toolbar_topic);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        topic_id = getIntent().getStringExtra("TOPIC_ID");
        course_id = getIntent().getStringExtra("COURSE_ID");
        title = getIntent().getStringExtra("TITLE");

        setTitle(title);
        listview = findViewById(R.id.listview);

        presenter = new TopicPresenterImpl(this, new GetTopicInteractionImpl());
        presenter.topic(TopicsActivity.this, course_id);
        showProgress();

    }

    @Override
    public void showProgress() {
        dialog  = new Dialog(TopicsActivity.this);
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
    public void handleTopic(String string) {
        hideProgress();
        System.out.println("TopicActivity: handleTopic: " + string);
        if (string.equalsIgnoreCase("No network")){
            //show no network available
        }else {
            try {
                JSONArray jsonArray = new JSONArray(string);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String content = jsonObject.getString("content");
                    String id = jsonObject.getString("id");
                    listTopics.add(new TopicModel(id, name, content));

                }
                listTopics.add(new TopicModel("0", "Solve Problems", ""));
                listTopics.add(new TopicModel("0", "Clear Doubts", ""));
                listview.setAdapter(new ListAdapterTopics(TopicsActivity.this, R.layout.layout_subcateogry_single_item, listTopics, this, 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        final TextView category_name = (TextView) view.findViewById(R.id.category_name);
        TextView nofquestion = (TextView) view.findViewById(R.id.nofquestion);
        nofquestion.setVisibility(View.GONE);


        final TopicModel topicModel = listTopics.get(position);
        category_name.setText(topicModel.getName());

       /* if (topicModel.getName().equalsIgnoreCase("Solve Problems") || topicModel.getName().equalsIgnoreCase("Clear Doubts")) {
            nofquestion.setVisibility(View.GONE);
        }*/

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("TopicsActivity: solve problem: " + topicModel.getName());
                System.out.println("TopicsActivity: solve problem: " + topicModel.getContent());
                System.out.println("TopicsActivity: solve problem: " + topicModel.getId());

                if (topicModel.getName().equalsIgnoreCase("Solve Problems")){
                    startActivity(new Intent(TopicsActivity.this, QuestionsActivity.class)
                        .putExtra("TOPIC_ID", topic_id)
                        .putExtra("COURSE_ID", course_id)
                    );
                }else if (topicModel.getName().equalsIgnoreCase("Clear Doubts")){
                    startActivity(new Intent(TopicsActivity.this, DoubtActivity.class)
                            .putExtra("TOPIC_ID", topic_id)
                    );
                }else {
                    Intent intent = new Intent(TopicsActivity.this, VideoActivity.class);
                    intent.putExtra("course_id", course_id);
                    intent.putExtra("video_id", topicModel.getId());
                    startActivity(intent);
                }
            }
        });
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
