package com.edutainer.in.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.edutainer.in.Adapter.GifAdapter;
import com.edutainer.in.R;
import com.edutainer.in.Utility.BaseActivity;

public class IntroActivity extends BaseActivity implements View.OnClickListener
{
    private TextView next;
    private TextView skip;
    private ViewPager viewPager;

    @Override
    public void initialize(Bundle save) {

        getSupportActionBar().hide();

        skip = (TextView) findViewById(R.id.skip);
        next = (TextView) findViewById(R.id.next);

        next.setOnClickListener(this);
        skip.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(setUpViewPager());

       // AppPref.getInstance().setAppGif("true");

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = ((GifAdapter) viewPager.getAdapter()).getFragment(position);
                if (fragment != null) {
                    fragment.onResume();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private GifAdapter setUpViewPager() {
        GifAdapter pagerAdapter = new GifAdapter(getSupportFragmentManager(), this);
        return pagerAdapter;
    }


    @Override
    public int getActivityLayout() {
        return R.layout.activity_intro;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                if (viewPager.getCurrentItem() < 3)
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                else
                    sendToActivity(LoginActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
            case R.id.skip:
                sendToActivity(LoginActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
        }
    }
}
