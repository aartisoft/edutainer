package com.edutainer.in.Activity;

import android.os.Bundle;
import com.edutainer.in.Fragment.ProfileFragment;
import com.edutainer.in.R;
import com.edutainer.in.Utility.BaseActivity;

/**
 * Created by TalkCharge on 08-03-2018.
 */

public class AccountActivity extends BaseActivity {


    @Override
    public void initialize(Bundle save) {
        setFragment(new ProfileFragment(),R.id.frame);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_account;
    }

}
