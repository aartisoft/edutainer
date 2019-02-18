package com.edutainer.in.Fragment;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edutainer.in.Activity.ExploreActivity;
import com.edutainer.in.Activity.SplashActivity;
import com.edutainer.in.Model.CourseCategoryModel;
import com.edutainer.in.Model.CourseModel;
import com.edutainer.in.R;

import java.util.ArrayList;

public class MainFragment extends BaseFragment {

    private CardView shareapp;

    private String shareText = "Technology is evolving, why don't you.Download the Edutainer App and get Technical Education, available on google play store :https://play.google.com/store/apps/details?id=com.vgapps.edutainer";

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_main;
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


        LinearLayout linearview = (LinearLayout) view.findViewById(R.id.linearview);

        linearview.removeAllViews();

        shareapp = (CardView) view.findViewById(R.id.shareapp);

        for (int j = 0; j < arrayList().size(); j++) {

            View view2 = (View) LayoutInflater.from(getActivity()).inflate(R.layout.layout_mainsingleitem, null);
            LinearLayout main_layout = (LinearLayout) view2.findViewById(R.id.main_layout);
            LinearLayout linearLayout = (LinearLayout) main_layout.findViewById(R.id.popular_subjects);
            TextView subject_group
                    = (TextView) main_layout.findViewById(R.id.subject_group);

            CourseCategoryModel courseCategoryModel = arrayList().get(j);
            subject_group.setText(courseCategoryModel.getCategory_name());

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            width = (int) (width / 2.1);
            height = (int) (height / 6.5);

            ArrayList<CourseModel> courseModels = courseModelArrayList(courseCategoryModel.getId());
            for (byte i = 0; i < courseModels.size(); i++) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view1 = (View) layoutInflater.inflate(R.layout.layout_mainfragment_singleitem, null);
                TextView textView = (TextView) view1.findViewById(R.id.subject);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

                params.setMargins(10, 10, 10, 10);
                textView.setLayoutParams(params);

                final CourseModel courseModel = courseModels.get(i);

                textView.setText(courseModel.getCourse_name());
                image(textView, courseModel.getCourse_name());
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (courseModel.getStatus().equalsIgnoreCase("1"))
                            decision(courseModel.getId() + "", courseModel.getCourse_name(), courseModel.getCourse_preview(), courseModel.getCourse_cost(), courseModel.getCourse_duration(), courseModel.getCourse_term(),courseModel.getCourse_short_desc(),courseModel.getCourse_kit());
                        else
                            toastMessage("Coming Soon");
                    }
                });
                linearLayout.addView(textView);
            }


            linearview.addView(main_layout);
        }


        shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Edutainer");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(sharingIntent, "share"));
            }
        });

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
