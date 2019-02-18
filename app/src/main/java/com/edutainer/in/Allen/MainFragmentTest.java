package com.edutainer.in.Allen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.edutainer.in.Activity.ExploreActivity;
import com.edutainer.in.Activity.SplashActivity;
import com.edutainer.in.Fragment.BaseFragment;
import com.edutainer.in.Model.CourseCategoryModel;
import com.edutainer.in.Model.CourseModel;
import com.edutainer.in.R;
import com.ramotion.expandingcollection.ECBackgroundSwitcherView;
import com.ramotion.expandingcollection.ECCardData;
import com.ramotion.expandingcollection.ECPagerView;
import com.ramotion.expandingcollection.ECPagerViewAdapter;

import java.util.ArrayList;

public class MainFragmentTest extends BaseFragment {

    private ECPagerView ecPagerView;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_main_test;
    }

    private ArrayList<CourseCategoryModel> arrayList() {
        ArrayList<CourseCategoryModel> arrayList = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < SplashActivity.courseModels.size(); i++) {
            int id = SplashActivity.courseModels.get(i).getCat_id();
            String name = SplashActivity.courseModels.get(i).getCat_name();
            if (x != id) {
                x = id;
                CourseCategoryModel courseCategoryModel = new CourseCategoryModel(id, name);
                arrayList.add(courseCategoryModel);
            }

        }
        return arrayList;
    }

    private ArrayList<CourseModel> courseModelArrayList(int id) {
        ArrayList<CourseModel> arrayList = new ArrayList<>();

        for (int i = 0; i < SplashActivity.courseModels.size(); i++) {
            if (SplashActivity.courseModels.get(i).getCat_id() == id) {
                arrayList.add(SplashActivity.courseModels.get(i));
            }

        }
        return arrayList;
    }

    @Override
    public void initializeView(View view) {
        // Create adapter for pager

    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }


    private void decision(String id, String name, String preview, String course_cost, String course_duration, String course_term, String course_desc, String course_kit) {
        try {
            //sendToActivity(ExploreActivity.class, new String[]{"id;" + id, "name;" + name, "preview;" + preview});
            Intent intent = new Intent(getActivity(), ExploreActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("name", name);
            intent.putExtra("preview", preview);
            intent.putExtra("course_cost", course_cost);
            intent.putExtra("course_duration", course_duration);
            intent.putExtra("course_term", course_term);
            intent.putExtra("course_desc", course_desc);
            intent.putExtra("kit_cost", course_kit);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void image(final TextView textView, String name) {

//        Target target = new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
//                textView.setBackground(drawable);
//                textView.invalidate();
//            }
//
//            @Override
//            public void onBitmapFailed(Drawable errorDrawable) {
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//            }
//        };
//
//        String path = AppUrl.IMAGE_URL + name;
//        Log.d("responsepath", path);
//
//        Picasso.with(getActivity()).load(path).into(target);


        if (name.equalsIgnoreCase("Understanding Of IoT"))
            textView.setBackgroundResource(R.drawable.iot);

        if (name.equalsIgnoreCase("Robotics"))
            textView.setBackgroundResource(R.drawable.robotics);
        if (name.equalsIgnoreCase("Drone"))
            textView.setBackgroundResource(R.drawable.drone);
        if (name.equalsIgnoreCase("AI"))
            textView.setBackgroundResource(R.drawable.ai);
        if (name.equalsIgnoreCase("ML"))
            textView.setBackgroundResource(R.drawable.ml);
        if (name.equalsIgnoreCase("AR"))
            textView.setBackgroundResource(R.drawable.drone);
    }
}
