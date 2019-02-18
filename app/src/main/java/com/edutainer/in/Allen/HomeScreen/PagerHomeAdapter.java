package com.edutainer.in.Allen.HomeScreen;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerHomeAdapter extends FragmentPagerAdapter {

    public PagerHomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FirstFragment();
                break;
            case 1:
                fragment = new SecondFragment();
                break;
            case 2:
                fragment = new ThirdFragment();
                break;
            case 3:
                fragment = new FourthFragment();
                break;
            case 4:
                fragment = new FifthFragment();
                break;
            case 5:
                fragment = new SixthFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab " + (position + 1);
    }
}
