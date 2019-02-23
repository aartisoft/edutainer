package com.edutainer.in.workplace.CourseDetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.edutainer.in.Activity.VideoActivity;
import com.edutainer.in.R;
import com.edutainer.in.workplace.CourseTopics.TopicsActivity;
import com.edutainer.in.workplace.Model.CourseModel;
import com.edutainer.in.workplace.Model.LessonModel;

import java.util.ArrayList;

public class RecyclerAdapterTopics extends RecyclerView.Adapter<RecyclerAdapterTopics.ViewHolder> {

    private Context context;
    private ArrayList<LessonModel> listTopics;
    private CourseModel courseModel;
    private String mode;

    public RecyclerAdapterTopics(Context context, ArrayList<LessonModel> listTopics, CourseModel courseModel, String mode) {
        this.context = context;
        this.listTopics = listTopics;
        this.mode = mode;
        this.courseModel = courseModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_topic, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_topic.setText(listTopics.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mode.equalsIgnoreCase("UNLOCKED"))
                    context.startActivity(new Intent(context, TopicsActivity.class)
                        .putExtra("COURSE_ID", courseModel.getId()+"")
                        .putExtra("TITLE", courseModel.getCourse_name())
//                        .putExtra("id", courseModel.getId()+"")
//                        .putExtra("title", courseModel.getCourse_name())
//                        .putExtra("course_id", courseModel.getId()+"")
                    );
                else
                    Toast.makeText(context, "Please Buy the course First!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTopics.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_topic;
        Typeface OpenSans_SemiBold = Typeface.createFromAsset(context.getAssets(),  "fonts/OpenSans-Semibold.ttf");

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_topic = itemView.findViewById(R.id.tv_topic);
            tv_topic.setTypeface(OpenSans_SemiBold);
        }
    }
}
