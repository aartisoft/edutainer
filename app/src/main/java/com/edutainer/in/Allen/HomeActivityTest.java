package com.edutainer.in.Allen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.edutainer.in.Allen.HomeScreen.FirstFragment;
import com.edutainer.in.Allen.HomeScreen.SingleModulePagerAdapter;
import com.edutainer.in.R;
import com.edutainer.in.workplace.HomeScreen.ProductFragment;
import com.edutainer.in.workplace.Model.CourseModel;
import com.edutainer.in.workplace.Splash.SplashActivity;

import java.util.ArrayList;

public class HomeActivityTest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager vp_home;
    SingleModulePagerAdapter myPagerAdapter;
    private ArrayList<Fragment> mFragmentList;
    private ArrayList<CourseModel> listCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initializeViews();
    }

    private void initializeViews(){
       /* PagerHomeAdapter adapter = new PagerHomeAdapter(getSupportFragmentManager());
        vp_home.setAdapter(adapter);*/
       listCourses = new ArrayList<>(SplashActivity.courseModels);

       vp_home = findViewById(R.id.vp_home);
       mFragmentList = new ArrayList<>();
       vp_home.setOffscreenPageLimit(mFragmentList.size());
       myPagerAdapter = new SingleModulePagerAdapter(getSupportFragmentManager(), mFragmentList);
       vp_home.setAdapter(myPagerAdapter);

       createFragments();

    }

    private void createFragments() {
        System.out.println("list size" + listCourses.size());
        for (int i = 0; i < listCourses.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("COURSE", listCourses.get(i));
            switch (i){
                case 0:
                    bundle.putInt("BACKGROUND", R.drawable.gradient_home_a);
                    bundle.putInt("IMAGE", R.drawable.img_understanding_iot);
                    bundle.putInt("BUTTON", R.drawable.shape_button_a);
                    break;
                case 1:
                    bundle.putInt("BACKGROUND", R.drawable.gradient_home_b);
                    bundle.putInt("IMAGE", R.drawable.img_understanding_robotics);
                    bundle.putInt("BUTTON", R.drawable.shape_button_b);
                    break;
                case 2:
                    bundle.putInt("BACKGROUND", R.drawable.gradient_home_c);
                    bundle.putInt("IMAGE", R.drawable.img_iot_basics);
                    bundle.putInt("BUTTON", R.drawable.shape_button_c);
                    break;
                case 3:
                    bundle.putInt("BACKGROUND", R.drawable.gradient_home_d);
                    bundle.putInt("IMAGE", R.drawable.img_iot_arduino);
                    bundle.putInt("BUTTON", R.drawable.shape_button_d);
                    break;
                case 4:
                    bundle.putInt("BACKGROUND", R.drawable.gradient_home_e);
                    bundle.putInt("IMAGE", R.drawable.img_iot_raspberry_pi);
                    bundle.putInt("BUTTON", R.drawable.shape_button_e);
                    break;
                case 5:
                    bundle.putInt("BACKGROUND", R.drawable.gradient_home_f);
                    bundle.putInt("IMAGE", R.drawable.img_robotic_basics);
                    bundle.putInt("BUTTON", R.drawable.shape_button_f);
                    break;

            }
            myPagerAdapter.addFrag(new ProductFragment());
            myPagerAdapter.notifyDataSetChanged();
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
        getMenuInflater().inflate(R.menu.home_activity_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

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
}
