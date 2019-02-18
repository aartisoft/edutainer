package com.edutainer.in.Fragment;

import android.view.View;
import android.widget.ImageView;


import com.edutainer.in.R;


/**
 * Created by TalkCharge on 18-12-2017.
 */

public class GifFragment1 extends BaseFragment {


    private View view;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_intro;
    }


    @Override
    public void initializeView(View view) {
        this.view = view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ImageView gifview = (ImageView) view.findViewById(R.id.gif_decoder_view);
        gifview.setBackgroundResource(R.drawable.intro_1);
    }
}
