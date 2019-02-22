package com.edutainer.in.workplace.HomeScreen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.edutainer.in.Allen.HomeScreen.FirstFragment;
import com.edutainer.in.Allen.HomeScreen.SingleModulePagerAdapter;
import com.edutainer.in.R;
import com.edutainer.in.workplace.Model.CourseModel;
import com.edutainer.in.workplace.Splash.SplashActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HomeContract.HomeView,
        NavigationView.OnNavigationItemSelectedListener {

    ViewPager vp_home;
    TabLayout sliding_tabs;
    Dialog dialog;

    SingleModulePagerAdapter myPagerAdapter;
    HomeContract.HomePresenter presenter;
    private ArrayList<CourseModel> listCourses;
    private ArrayList<Fragment> mFragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initializeViews();
    }

    private void initializeViews(){
       /* PagerHomeAdapter adapter = new PagerHomeAdapter(getSupportFragmentManager());
        vp_home.setAdapter(adapter);*/
        listCourses = new ArrayList<>(SplashActivity.courseModels);
        mFragmentList = new ArrayList<>();

        vp_home = findViewById(R.id.vp_home);
        myPagerAdapter = new SingleModulePagerAdapter(getSupportFragmentManager(), mFragmentList);
        vp_home.setAdapter(myPagerAdapter);
        myPagerAdapter.addFrag(new AvailableCoursesFragment());
        myPagerAdapter.addFrag(new AvailableCoursesFragment());
        myPagerAdapter.addFrag(new AvailableCoursesFragment());
        myPagerAdapter.notifyDataSetChanged();

        sliding_tabs = findViewById(R.id.sliding_tabs);
        sliding_tabs.setupWithViewPager(vp_home);

        sliding_tabs.getTabAt(0).setIcon(R.drawable.ic_android);
        sliding_tabs.getTabAt(1).setIcon(R.drawable.ic_android);
        sliding_tabs.getTabAt(2).setIcon(R.drawable.ic_android);

        /*for (int i = 0; i < myPagerAdapter.getCount(); i++) {
            TabLayout.Tab tab = sliding_tabs.getTabAt(i);
            if (tab != null) {
                View v = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_tab_text, null, false);
                tab.setCustomView(v);
                ImageView iv_tab = v.findViewById(R.id.iv_tab);
                TextView tv_tab = v.findViewById(R.id.tv_tab);

                switch (i){
                    case 0:
                        iv_tab.setImageResource(R.drawable.ic_android);
                        tv_tab.setText("COURSES");
                        break;
                    case 1:
                        iv_tab.setImageResource(R.drawable.ic_android);
                        tv_tab.setText("ENROLLED");

                        break;
                    case 2:
                        iv_tab.setImageResource(R.drawable.ic_android);
                        tv_tab.setText("PROFILE");

                        break;
                }

            }
        }

        sliding_tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View v = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_tab_text, null, false);
                tab.setCustomView(v);
                ImageView iv_tab = v.findViewById(R.id.iv_tab);
                TextView tv_tab = v.findViewById(R.id.tv_tab);
                tv_tab.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.textColor));

                switch (tab.getPosition()){
                    case 0:
                        iv_tab.setImageResource(R.drawable.ic_arrow);
                        break;
                    case 1:
                        iv_tab.setImageResource(R.drawable.ic_arrow);
                        break;
                    case 2:
                        iv_tab.setImageResource(R.drawable.ic_arrow);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View v = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_tab_text, null, false);
                tab.setCustomView(v);
                ImageView iv_tab = v.findViewById(R.id.iv_tab);
                TextView tv_tab = v.findViewById(R.id.tv_tab);
                tv_tab.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.textColor));

                switch (tab.getPosition()){
                    case 0:
                        iv_tab.setImageResource(R.drawable.ic_android);
                        break;
                    case 1:
                        iv_tab.setImageResource(R.drawable.ic_android);
                        break;
                    case 2:
                        iv_tab.setImageResource(R.drawable.ic_android);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_search) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showProgress() {
        dialog  = new Dialog(HomeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(false);
        //...that's the layout i told you will inflate later
        dialog.setContentView(R.layout.progress_dialog);
        dialog.show();
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        });
    }


}
