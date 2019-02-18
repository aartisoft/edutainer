package com.edutainer.in.workplace.FirstScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.edutainer.in.Allen.LoginActivityTest;
import com.edutainer.in.Allen.RegisterActivityTest;
import com.edutainer.in.R;
import com.edutainer.in.workplace.FirstScreen.Fragment.ViewPagerAdapter;
import com.edutainer.in.workplace.Login.LoginActivity;
import com.edutainer.in.workplace.Register.RegisterActivity;
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_login;
    Button btn_create_account;
    ViewPager vp_first_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_test);

        Toolbar toolbar = findViewById(R.id.toolbar_first_screen);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btn_login = findViewById(R.id.btn_login);
        btn_create_account = findViewById(R.id.btn_create_account);
        vp_first_activity = findViewById(R.id.vp_first_activity);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        vp_first_activity.setAdapter(adapter);

        btn_login.setOnClickListener(this);
        btn_create_account.setOnClickListener(this);

        CardFlipPageTransformer cardFlipPageTransformer = new CardFlipPageTransformer();

// Enable / Disable scaling while flipping. If false, then card will only flip as in Poker card example.
// Otherwise card will also scale like in Gallery demo. By default, its true.
        cardFlipPageTransformer.setScalable(false);

// Set orientation. Either horizontal or vertical. By default, its vertical.
        cardFlipPageTransformer.setFlipOrientation(CardFlipPageTransformer.VERTICAL);

// Assign the page transformer to the ViewPager.
        vp_first_activity.setPageTransformer(true, cardFlipPageTransformer);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
                break;
            case R.id.btn_create_account:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
                break;
        }
    }
}
