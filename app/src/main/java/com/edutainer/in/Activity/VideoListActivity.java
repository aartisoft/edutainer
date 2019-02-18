package com.edutainer.in.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.edutainer.in.Adapter.SingleAdapter;
import com.edutainer.in.Model.VideoModel;
import com.edutainer.in.R;
import com.edutainer.in.Utility.BaseActivity;
import java.util.List;

public class VideoListActivity extends BaseActivity implements SingleAdapter.ReturnView {
    private ListView listview;

    @Override
    public void initialize(Bundle save) {
        setTitle("Video Tutorials");
        listview = (ListView) findViewById(R.id.listview);

        listview.setAdapter(new SingleAdapter(this, R.layout.layout_videolist_single_item, SelectionActivity.videoModelArrayList, this, 0));
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_videolist;
    }

    @Override
    public void getAdapterView(View view, List objects, int position, int from) {
        final TextView category_name = (TextView) view.findViewById(R.id.category_name);
        final ImageView icon = (ImageView) view.findViewById(R.id.icon);


        final VideoModel categoryModel = SelectionActivity.videoModelArrayList.get(position);
        Thread thread = new Thread() {
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.with(VideoListActivity.this).load("https://img.youtube.com/vi/"+categoryModel.getUrl()+"/2.jpg").into(icon);
                    }
                });
            }
        };
        thread.start();
        category_name.setText(categoryModel.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToActivity(VideoActivity.class, new String[]{"url;" + categoryModel.getUrl()});
            }
        });
    }
}
