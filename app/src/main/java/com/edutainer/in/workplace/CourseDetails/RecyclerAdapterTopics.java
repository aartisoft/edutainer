package com.edutainer.in.workplace.CourseDetails;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edutainer.in.R;
import com.edutainer.in.workplace.Model.LessonModel;

import java.util.ArrayList;

public class RecyclerAdapterTopics extends RecyclerView.Adapter<RecyclerAdapterTopics.ViewHolder> {

    private Context context;
    private ArrayList<LessonModel> listTopics;

    public RecyclerAdapterTopics(Context context, ArrayList<LessonModel> listTopics) {
        this.context = context;
        this.listTopics = listTopics;
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
