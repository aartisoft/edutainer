package com.edutainer.in.Allen.HomeScreen;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.edutainer.in.R;

public class FourthFragment extends Fragment {
    TextView tv_title;
    TextView tv_description;
    Button btn_learn_more;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_fourth, container, false);
        Typeface OpenSans_Regular = Typeface.createFromAsset(getContext().getAssets(),  "fonts/OpenSans-Regular.ttf");
        Typeface OpenSans_Bold = Typeface.createFromAsset(getContext().getAssets(),  "fonts/OpenSans-Bold.ttf");
        Typeface OpenSans_SemiBold = Typeface.createFromAsset(getContext().getAssets(),  "fonts/OpenSans-Semibold.ttf");

        tv_title = view.findViewById(R.id.tv_title);
        tv_title.setTypeface(OpenSans_Bold);

        tv_description = view.findViewById(R.id.tv_description);
        tv_description.setTypeface(OpenSans_SemiBold);

        btn_learn_more = view.findViewById(R.id.btn_learn_more);
        btn_learn_more.setTypeface(OpenSans_Bold);

        return view;
    }
}
