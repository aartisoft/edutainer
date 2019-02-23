package com.edutainer.in.workplace.HomeScreen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edutainer.in.R;
import com.edutainer.in.workplace.CourseDetails.DetailActivity;
import com.edutainer.in.workplace.Model.CourseModel;

import java.util.ArrayList;

public class RecyclerAdapterCourses extends RecyclerView.Adapter<RecyclerAdapterCourses.ViewHolder> {

    private Context context;
    private ArrayList<CourseModel> listCourses;
    private Fragment fragment;
    Typeface Nunito_SemiBold;
    int drawable = R.drawable.ic_robotics_basics;

    public RecyclerAdapterCourses(Context context, ArrayList<CourseModel> listCourses, EnrolledCoursesFragment fragment) {
        this.fragment = fragment;
        this.context = context;
        this.listCourses = listCourses;
        Nunito_SemiBold = Typeface.createFromAsset(context.getAssets(),  "fonts/Nunito-SemiBold.ttf");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        CourseModel model = listCourses.get(i);
        viewHolder.tv_product.setTypeface(Nunito_SemiBold);
        switch (model.getCourse_name()){
            case "Understanding of IoT":
                drawable = R.drawable.ic_understanding_iot;
                Glide.with(context).load(R.drawable.ic_understanding_iot).into(viewHolder.iv_product);
                viewHolder.tv_product.setText("Understanding\n of IoT");
                break;
            case "Understanding of Robotics":
                drawable = R.drawable.ic_understanding_robotics;
                Glide.with(context).load(R.drawable.ic_understanding_robotics).into(viewHolder.iv_product);
                viewHolder.tv_product.setText("Understanding\n of Robotics");
                break;
            case "IOT Basics":
                drawable = R.drawable.ic_iot_basics;
                Glide.with(context).load(R.drawable.ic_iot_basics).into(viewHolder.iv_product);
                viewHolder.tv_product.setText("IOT\n Basics");
                break;
            case "IOT using Arduino":
                drawable = R.drawable.ic_iot_arduino;
                Glide.with(context).load(R.drawable.ic_iot_arduino).into(viewHolder.iv_product);
                viewHolder.tv_product.setText("IOT\n using Arduino");
                break;
            case "IOT using Raspberry PI":
                drawable = R.drawable.ic_iot_raspberry;
                Glide.with(context).load(R.drawable.ic_iot_raspberry).into(viewHolder.iv_product);
                viewHolder.tv_product.setText("IOT using\n Raspberry PI");
                break;
            case "Basic Robotics":
                drawable = R.drawable.ic_robotics_basics;
                Glide.with(context).load(R.drawable.ic_robotics_basics).into(viewHolder.iv_product);
                viewHolder.tv_product.setText("Basic\n Robotics");
                break;
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailActivity.class)
                        .putExtra("COURSE", listCourses.get(i))
                        .putExtra("IMAGE", drawable)
                        .putExtra("MODE", "UNLOCKED")
                );
                fragment.getActivity().overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);


            }
        });
    }

    @Override
    public int getItemCount() {
        return listCourses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_product;
        TextView tv_product;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_product = itemView.findViewById(R.id.iv_product);
            tv_product = itemView.findViewById(R.id.tv_product);
        }
    }
}
