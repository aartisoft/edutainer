package com.edutainer.in.workplace.Drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.edutainer.in.R;
import com.edutainer.in.workplace.Drawer.Share.FragmentShare;
import com.edutainer.in.workplace.Drawer.Webview.FragmentWebView;
import com.edutainer.in.workplace.HomeScreen.HomeActivity;

public class NavDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        checkFragment();
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
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_courses) {
            startActivity(new Intent(NavDrawerActivity.this, HomeActivity.class)
                    .putExtra("POSITION", 0)
            );
        } else if (id == R.id.menu_enrolled) {
            startActivity(new Intent(NavDrawerActivity.this, HomeActivity.class)
                    .putExtra("POSITION", 1)
            );
        } else if (id == R.id.menu_privacy_policy) {
            startActivity(new Intent(NavDrawerActivity.this, NavDrawerActivity.class)
                    .putExtra("FRAGMENT", "PRIVACY_POLICY")
            );
        } else if (id == R.id.menu_terms_condition) {
            startActivity(new Intent(NavDrawerActivity.this, NavDrawerActivity.class)
                    .putExtra("FRAGMENT", "TERMS")
            );
        } else if (id == R.id.menu_about_us) {
            startActivity(new Intent(NavDrawerActivity.this, NavDrawerActivity.class)
                    .putExtra("FRAGMENT", "ABOUT")
            );
        } else if (id == R.id.menu_refer_n_learn) {
            startActivity(new Intent(NavDrawerActivity.this, NavDrawerActivity.class)
                    .putExtra("FRAGMENT", "REFER")
            );
        }else if (id == R.id.menu_logout){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void beginFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_nav_drawer, fragment)
                .addToBackStack(null)
                .commit();

    }

    private void checkFragment(){
        if (getIntent().getExtras() != null){
            Bundle bundle = new Bundle();
            Fragment fragment;
            if (getIntent().getStringExtra("FRAGMENT").equalsIgnoreCase("PRIVACY_POLICY")){
                fragment = new FragmentWebView();
                bundle.putString("URL", "https://www.edutainer.in/edutainer_api/privacypolicy.php");
                fragment.setArguments(bundle);
                beginFragment(fragment);
                getSupportActionBar().setTitle("Privacy policy");
            }else if (getIntent().getStringExtra("FRAGMENT").equalsIgnoreCase("TERMS")){
                fragment = new FragmentWebView();
                bundle.putString("URL", "https://www.edutainer.in/edutainer_api/terms_conditions.php");
                fragment.setArguments(bundle);
                beginFragment(fragment);
                getSupportActionBar().setTitle("Terms & Conditions");

            }else if (getIntent().getStringExtra("FRAGMENT").equalsIgnoreCase("ABOUT")){
                fragment = new FragmentWebView();
                bundle.putString("URL", "https://www.edutainer.in/edutainer_api/Aboutus.php");
                fragment.setArguments(bundle);
                beginFragment(fragment);
                getSupportActionBar().setTitle("About us");

            }else if (getIntent().getStringExtra("FRAGMENT").equalsIgnoreCase("REFER")){
                beginFragment(new FragmentShare());
                getSupportActionBar().setTitle("Refer & learn");
            }
        }
    }


}
