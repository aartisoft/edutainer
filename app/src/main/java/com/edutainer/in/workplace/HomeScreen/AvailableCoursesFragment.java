package com.edutainer.in.workplace.HomeScreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edutainer.in.R;
import com.edutainer.in.workplace.CourseDetails.DetailActivity;
import com.edutainer.in.workplace.Model.CourseModel;
import com.edutainer.in.workplace.Splash.SplashActivity;

import java.util.ArrayList;

public class AvailableCoursesFragment extends Fragment implements View.OnClickListener {

    LinearLayout ll_home;

    CardView cv_product_a;
    CardView cv_product_b;
    CardView cv_product_c;
    CardView cv_product_d;
    CardView cv_product_e;
    CardView cv_product_f;

    TextView tv_product_a;
    TextView tv_product_b;
    TextView tv_product_c;
    TextView tv_product_d;
    TextView tv_product_e;
    TextView tv_product_f;

    ImageView iv_product_a;
    ImageView iv_product_b;
    ImageView iv_product_c;
    ImageView iv_product_d;
    ImageView iv_product_e;
    ImageView iv_product_f;

    private ArrayList<CourseModel> listCourses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_available_courses, container, false);
        listCourses = new ArrayList<>(SplashActivity.enrolledCourses);

        System.out.println("EnrolledCourses: " + SplashActivity.enrolledCourses);

        initializeViews(view);
        return view;
    }

    private void initializeViews(View view){
        Typeface Nunito_SemiBold = Typeface.createFromAsset(getContext().getAssets(),  "fonts/Nunito-SemiBold.ttf");

        ll_home = view.findViewById(R.id.ll_home);
        cv_product_a = view.findViewById(R.id.cv_product_a);
        cv_product_a.setOnClickListener(this);
        cv_product_b = view.findViewById(R.id.cv_product_b);
        cv_product_b.setOnClickListener(this);
        cv_product_c = view.findViewById(R.id.cv_product_c);
        cv_product_c.setOnClickListener(this);
        cv_product_d = view.findViewById(R.id.cv_product_d);
        cv_product_d.setOnClickListener(this);
        cv_product_e = view.findViewById(R.id.cv_product_e);
        cv_product_e.setOnClickListener(this);
        cv_product_f = view.findViewById(R.id.cv_product_f);
        cv_product_f.setOnClickListener(this);

        tv_product_a = view.findViewById(R.id.tv_product_a);
        tv_product_a.setTypeface(Nunito_SemiBold);
        tv_product_b = view.findViewById(R.id.tv_product_b);
        tv_product_b.setTypeface(Nunito_SemiBold);
        tv_product_c = view.findViewById(R.id.tv_product_c);
        tv_product_c.setTypeface(Nunito_SemiBold);
        tv_product_d = view.findViewById(R.id.tv_product_d);
        tv_product_d.setTypeface(Nunito_SemiBold);
        tv_product_e = view.findViewById(R.id.tv_product_e);
        tv_product_e.setTypeface(Nunito_SemiBold);
        tv_product_f = view.findViewById(R.id.tv_product_f);
        tv_product_f.setTypeface(Nunito_SemiBold);


        iv_product_a = view.findViewById(R.id.iv_product_a);
        iv_product_b = view.findViewById(R.id.iv_product_b);
        iv_product_c = view.findViewById(R.id.iv_product_c);
        iv_product_d = view.findViewById(R.id.iv_product_d);
        iv_product_e = view.findViewById(R.id.iv_product_e);
        iv_product_f = view.findViewById(R.id.iv_product_f);

        for (int i= 0; i<listCourses.size(); i++ ){
            switch (listCourses.get(i).getCourse_name()){
                case "Understanding of IoT":
                    cv_product_a.setVisibility(View.VISIBLE);
                    break;
                case "Understanding of Robotics":
                    cv_product_b.setVisibility(View.VISIBLE);
                    break;
                case "IOT Basics":
                    cv_product_c.setVisibility(View.VISIBLE);
                    break;
                case "IOT using Arduino":
                    cv_product_d.setVisibility(View.VISIBLE);
                    break;
                case "IOT using Raspberry PI":
                    cv_product_e.setVisibility(View.VISIBLE);
                    break;
                case "Basic Robotics":
                    cv_product_f.setVisibility(View.VISIBLE);
                    break;
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_product_a:
                startActivity(0, R.drawable.ic_understanding_iot);

                break;
            case R.id.cv_product_b:
                startActivity(1, R.drawable.ic_understanding_robotics);

                break;
            case R.id.cv_product_c:
                startActivity(2, R.drawable.ic_iot_basics);

                break;
            case R.id.cv_product_d:
                startActivity(3, R.drawable.ic_iot_arduino);

                break;
            case R.id.cv_product_e:
                startActivity(4, R.drawable.ic_iot_raspberry);

                break;
            case R.id.cv_product_f:
                startActivity(5, R.drawable.ic_robotics_basics);

                break;
        }
    }

    private void startActivity(int position, int drawable){
        startActivity(new Intent(getContext(), DetailActivity.class)
                .putExtra("COURSE", listCourses.get(position))
                .putExtra("IMAGE", drawable)
                .putExtra("MODE", "LOCKED")
        );
        getActivity().overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);


    }
}
