package com.edutainer.in.workplace.HomeScreen;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
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
import com.edutainer.in.workplace.Drawer.NavDrawerActivity;
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
        listCourses = new ArrayList<>(SplashActivity.courseModels);
        mFragmentList = new ArrayList<>();

        vp_home = findViewById(R.id.vp_home);
        myPagerAdapter = new SingleModulePagerAdapter(getSupportFragmentManager(), mFragmentList);
        vp_home.setAdapter(myPagerAdapter);
        myPagerAdapter.addFrag(new AvailableCoursesFragment());
        myPagerAdapter.addFrag(new EnrolledCoursesFragment());
        myPagerAdapter.addFrag(new ProfileFragment());
        myPagerAdapter.notifyDataSetChanged();

        sliding_tabs = findViewById(R.id.sliding_tabs);
        sliding_tabs.setupWithViewPager(vp_home);

        sliding_tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(HomeActivity.this, R.color.textColor);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(HomeActivity.this, R.color.tabColor);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(HomeActivity.this, R.color.textColor);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }
        });

        sliding_tabs.getTabAt(0).setIcon(R.drawable.ic_courses);
        sliding_tabs.getTabAt(1).setIcon(R.drawable.ic_enrolled);
        sliding_tabs.getTabAt(2).setIcon(R.drawable.ic_profile);

        if (getIntent().getIntExtra("POSITION", 0) == 0){
            vp_home.setCurrentItem(0);
        }else if (getIntent().getIntExtra("POSITION", 0) == 1){
            vp_home.setCurrentItem(1);
        }
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

        if (id == R.id.menu_courses) {
            vp_home.setCurrentItem(0);
            // Handle the camera action
        } else if (id == R.id.menu_enrolled) {
            vp_home.setCurrentItem(1);

        } else if (id == R.id.menu_privacy_policy) {
            startActivity(new Intent(HomeActivity.this, NavDrawerActivity.class)
                    .putExtra("FRAGMENT", "PRIVACY_POLICY")
            );

        } else if (id == R.id.menu_terms_condition) {
            startActivity(new Intent(HomeActivity.this, NavDrawerActivity.class)
                    .putExtra("FRAGMENT", "TERMS")
            );

        } else if (id == R.id.menu_about_us) {
            startActivity(new Intent(HomeActivity.this, NavDrawerActivity.class)
                    .putExtra("FRAGMENT", "ABOUT")
            );

        } else if (id == R.id.menu_refer_n_learn) {
            startActivity(new Intent(HomeActivity.this, NavDrawerActivity.class)
                    .putExtra("FRAGMENT", "REFER")
            );

        }else if (id == R.id.menu_logout){

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
