package com.edutainer.in.workplace.HomeScreen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edutainer.in.R;
import com.edutainer.in.workplace.CourseDetails.DetailActivity;
import com.edutainer.in.workplace.Model.CourseModel;
import com.edutainer.in.workplace.Splash.SplashActivity;

import java.util.ArrayList;

public class EnrolledCoursesFragment extends Fragment{
    RecyclerView rv_enrolled;
    private ArrayList<CourseModel> listCourses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enrolled_courses, container, false);
        listCourses = new ArrayList<>(SplashActivity.enrolledCourses);
        rv_enrolled = view.findViewById(R.id.rv_enrolled);
        rv_enrolled.setLayoutManager(new GridLayoutManager(getContext(), 2));
        RecyclerAdapterCourses adapterCourses = new RecyclerAdapterCourses(getContext(), listCourses, EnrolledCoursesFragment.this);
        rv_enrolled.setAdapter(adapterCourses);
        return view;
    }
}
