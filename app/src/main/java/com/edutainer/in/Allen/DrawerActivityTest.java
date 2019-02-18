package com.edutainer.in.Allen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.edutainer.in.Activity.AboutUsActivity;
import com.edutainer.in.Activity.AccountActivity;
import com.edutainer.in.Activity.AskaQuestionActivity;
import com.edutainer.in.Activity.DonateActivity;
import com.edutainer.in.Activity.LoginActivity;
import com.edutainer.in.Activity.PrivacyPolicyActivity;
import com.edutainer.in.Activity.RefernLearnActivity;
import com.edutainer.in.Activity.SubscribeActivity;
import com.edutainer.in.Activity.TermsConditionsActivity;
import com.edutainer.in.Fragment.EnrollmentFragment;
import com.edutainer.in.Fragment.ProfileFragment;
import com.edutainer.in.R;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.BottomNavigationViewHelper;
import com.edutainer.in.Utility.Utility;


public class DrawerActivityTest extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getHeaderView(navigationView.getHeaderView(0));

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics1 = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics1);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics1);
            iconView.setLayoutParams(layoutParams);
        }
        Utility.menuWork(this, bottomNavigationView, R.id.bottom_home, false, 0);

        savedInstanceState = getIntent().getExtras();
        if (savedInstanceState != null) {
            String pos = savedInstanceState.getString("pos");
            switch (pos) {
                case "1":
                    setTitle("Home");
                    setFragment(new MainFragmentTest(), R.id.frame);
                    break;
                case "3":
                    setTitle("Enrollment");
                    setFragment(new EnrollmentFragment(), R.id.frame);
                    break;
                case "4":
                    setTitle("Account");
                    setFragment(new ProfileFragment(), R.id.frame);
                    break;
            }
        } else
            setFragment(new MainFragmentTest(), R.id.frame);


        //setFragment(new MainFragment(),R.id.frame);
    }

    private void getHeaderView(View view) {
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(AppPref.getInstance().getUSERNAME());
        TextView email = (TextView) view.findViewById(R.id.email);
        email.setText(AppPref.getInstance().getUSEREMAIL());
    }

    public void setFragment(Fragment fragment, int id) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(id, fragment);
        t.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;
        switch (id) {
            case R.id.menu_home:
                setTitle("Explore");
                setFragment(new MainFragmentTest(), R.id.frame);
                break;
            case R.id.menu_subscripe:
                intent = new Intent(this, SubscribeActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_terms_condition:
                intent = new Intent(this, TermsConditionsActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_privac_policy:
                intent = new Intent(this, PrivacyPolicyActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_about_us:
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_refer_n_learn:
                intent = new Intent(this, RefernLearnActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_ask_a_question:
                intent = new Intent(this, AskaQuestionActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_account:
                intent = new Intent(this, AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_donate_now:
                intent = new Intent(this, DonateActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_logout:
                logout();
                break;
        }
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void logout() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Logout");
        adb.setMessage("Are you sure you want to Logout?");
        adb.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clearDataFromSharedPreference();
                Intent intent = new Intent(DrawerActivityTest.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
        adb.setNegativeButton("NO", null);
        adb.show();
    }

    private void clearDataFromSharedPreference() {
        AppPref.getInstance().setUSEREMAIL("");
        AppPref.getInstance().setUserId("");
        AppPref.getInstance().setUSERMOBILE("");
        AppPref.getInstance().setUSERNAME("");
        AppPref.getInstance().setUSERCONTACT("");
        AppPref.getInstance().setUSERIMEI("");
        AppPref.getInstance().setUSERSTREAM("0");
        AppPref.getInstance().setReferCode("");
        AppPref.getInstance().setGenCode("");
    }
}
