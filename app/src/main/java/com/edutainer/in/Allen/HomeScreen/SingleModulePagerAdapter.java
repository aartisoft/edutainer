package com.edutainer.in.Allen.HomeScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SingleModulePagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mFragmentList;


    public SingleModulePagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragmentList) {
        super(fm);
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment) {
//        fragment.setArguments(bundle);
        mFragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "COURSES";
            case 1:
                return "ENROLLED";
            case 2:
                return "PROFILE";
        }
        return "";
    }



}
