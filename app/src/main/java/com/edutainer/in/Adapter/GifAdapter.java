package com.edutainer.in.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import com.edutainer.in.Fragment.GifFragment1;
import com.edutainer.in.Fragment.GifFragment2;
import com.edutainer.in.Fragment.GifFragment3;
import com.edutainer.in.Fragment.GifFragment4;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TalkCharge on 18-12-2017.
 */

public class GifAdapter extends FragmentPagerAdapter {
    private Map<Integer, String> mFragmentTag;
    private FragmentManager mFragmentManager;
    private Context mContext;

    public GifAdapter(FragmentManager fm, Context context) {
        super(fm);
        mFragmentManager = fm;
        mFragmentTag = new HashMap<Integer, String>();
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GifFragment1();
            case 1:
                return new GifFragment2();
            case 2:
                return new GifFragment3();
            case 3:
                return new GifFragment4();
        }
        return null;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTag.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTag.get(position);
        if (tag == null) {
            return null;
        }
        return mFragmentManager.findFragmentByTag(tag);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
