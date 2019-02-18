package com.edutainer.in.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.edutainer.in.Adapter.SingleAdapter;
import com.edutainer.in.Model.ConceptModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends BaseActivity implements ApiResponse, SingleAdapter.ReturnView {

    //  private JCVideoPlayer videoplayer;
    private ListView listview;
    private ArrayList<ConceptModel> conceptModelArrayList = new ArrayList<>();
    private String id = "";
    private String course_id="";


    @Override
    public void initialize(Bundle savedInstanceState) {
        savedInstanceState = getIntent().getExtras();
//        videoplayer = (JCVideoPlayer) findViewById(R.id.videoplayer);
        listview = (ListView) findViewById(R.id.listview);

        if (savedInstanceState != null) {
            id = savedInstanceState.getString("id");
            course_id=savedInstanceState.getString("course_id");
            setTitle(savedInstanceState.getString("title"));
            getData(id);
        }


    }

    private void getData(String id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id=").append(id);


        String content = stringBuilder.toString();


        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.CONCEPT_URL, 0, content, true, "loading ...", this);
        apiConsumer.execute();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_video;
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String content = jsonObject.getString("content");
                String id = jsonObject.getString("id");
                conceptModelArrayList.add(new ConceptModel(id, name, content));

            }
            conceptModelArrayList.add(new ConceptModel("0", "Solve Problems", ""));
            conceptModelArrayList.add(new ConceptModel("0", "Clear Doubts", ""));
            listview.setAdapter(new SingleAdapter(this, R.layout.layout_subcateogry_single_item, conceptModelArrayList, this, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        final TextView category_name = (TextView) view.findViewById(R.id.category_name);
        TextView nofquestion = (TextView) view.findViewById(R.id.nofquestion);


        final ConceptModel categoryModel = conceptModelArrayList.get(position);
        category_name.setText(categoryModel.getName());

        if (categoryModel.getName().equalsIgnoreCase("Solve Problems") || categoryModel.getName().equalsIgnoreCase("Clear Doubts")) {
            nofquestion.setVisibility(View.GONE);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (categoryModel.getName().equalsIgnoreCase("Solve Problems"))
                    sendToActivity(QuestionActivity.class, new String[]{"id;" + id});
                else if (categoryModel.getName().equalsIgnoreCase("Clear Doubts"))
                    sendtoForum();
                else {
                    Intent intent = new Intent(VideoActivity.this, WebViewActivity.class);
                    intent.putExtra("course_id", course_id);
                    intent.putExtra("video_id", categoryModel.getId());
                    startActivity(intent);
                }
                //sendToActivity(WebViewActivity.class);
//                    videoplayer.setUp(categoryModel.getContent()
//                            , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, categoryModel.getName());

            }
        });

    }

    private void sendtoForum() {
        Intent intent = new Intent(this, AskaQuestionActivity.class);
        intent.putExtra("lesson_id", id);
        startActivity(intent);
    }
}