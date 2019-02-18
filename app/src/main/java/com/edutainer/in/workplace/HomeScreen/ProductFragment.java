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
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edutainer.in.R;
import com.edutainer.in.workplace.CourseDetails.DetailActivity;
import com.edutainer.in.workplace.Model.CourseModel;

public class ProductFragment extends Fragment {

    LinearLayout ll_product;
    ImageView iv_product;
    TextView tv_title;
    TextView tv_description;
    Button btn_learn_more;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_first, container, false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view){
        Typeface OpenSans_Regular = Typeface.createFromAsset(getContext().getAssets(),  "fonts/OpenSans-Regular.ttf");
        Typeface OpenSans_Bold = Typeface.createFromAsset(getContext().getAssets(),  "fonts/OpenSans-Bold.ttf");
        Typeface OpenSans_SemiBold = Typeface.createFromAsset(getContext().getAssets(),  "fonts/OpenSans-Semibold.ttf");

        ll_product = view.findViewById(R.id.ll_product);

        iv_product = view.findViewById(R.id.iv_product);

        tv_title = view.findViewById(R.id.tv_title);
        tv_title.setTypeface(OpenSans_Bold);

        tv_description = view.findViewById(R.id.tv_description);
        tv_description.setTypeface(OpenSans_SemiBold);

        btn_learn_more = view.findViewById(R.id.btn_learn_more);
        btn_learn_more.setTypeface(OpenSans_Bold);

        btn_learn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DetailActivity.class)
                    .putExtra("COURSE", getArguments().getParcelable("COURSE"))
                    .putExtra("IMAGE", getArguments().getInt("IMAGE"))
                );

            }
        });

        initializeValues();

    }

    private void initializeValues(){
        if (getArguments() != null){
            CourseModel courseModel = getArguments().getParcelable("COURSE");
            tv_title.setText(courseModel.getCourse_name());
            tv_description.setText(courseModel.getCourse_short_desc());

           /* Glide
                    .with(getContext())
                    .load(getArguments().getInt("IMAGE"))
                    .into(iv_product);
*/
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getArguments().getInt("IMAGE"));//Get bitmap from drawable resources
            createPaletteAsync(bitmap);

        }
    }

    private void createPaletteAsync(Bitmap bitmap) {

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // Use generated instance
                //work with the palette here
                int defaultValue = 0x000000;
                int vibrant = palette.getVibrantColor(defaultValue);
                int vibrantLight = palette.getLightVibrantColor(defaultValue);
                int vibrantDark = palette.getDarkVibrantColor(defaultValue);
                int muted = palette.getMutedColor(defaultValue);
                int mutedLight = palette.getLightMutedColor(defaultValue);
                int mutedDark = palette.getDarkMutedColor(defaultValue);

                GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {0xFFFFFF, 0xFFFFFF, vibrantLight});
                gd.setCornerRadius(0f);

                ll_product.setBackground(gd);


                btn_learn_more.setBackgroundResource(getArguments().getInt("BUTTON"));

            }
        });
    }
}
