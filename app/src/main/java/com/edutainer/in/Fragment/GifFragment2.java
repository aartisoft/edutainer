package com.edutainer.in.Fragment;

import android.view.View;
import android.widget.ImageView;


import com.edutainer.in.R;
import com.edutainer.in.Utility.PlayGifView;


/**
 * Created by TalkCharge on 18-12-2017.
 */

public class GifFragment2 extends BaseFragment {

    private PlayGifView image;
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
        gifview.setBackgroundResource(R.drawable.intro_2);
    }
}