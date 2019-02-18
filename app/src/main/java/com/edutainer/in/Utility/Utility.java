package com.edutainer.in.Utility;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.edutainer.in.Activity.DrawerActivity;
import com.edutainer.in.Fragment.EnrollmentFragment;
import com.edutainer.in.Fragment.MainFragment;
import com.edutainer.in.Fragment.ProfileFragment;
import com.edutainer.in.R;

import java.util.HashMap;

public class Utility {

    public static boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void getListViewSize(ListView myListView) {
        ListAdapter myListAdapter = myListView.getAdapter();
        if (myListAdapter == null) {
            //do nothing return null
            return;
        }
        //set listAdapter in loop for getting final size
        int totalHeight = 0;
        for (int size = 0; size < myListAdapter.getCount(); size++) {
            View listItem = myListAdapter.getView(size, null, myListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //setting listview item in adapter
        ViewGroup.LayoutParams params = myListView.getLayoutParams();
        params.height = totalHeight + (myListView.getDividerHeight() * (myListAdapter.getCount() - 1));
        myListView.setLayoutParams(params);
        // print height of adapter on log
        Log.i("height of listItem:", String.valueOf(totalHeight));
    }





    public static Bitmap retriveVideoFrameFromVideo(String videoPath)
             {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);

            bitmap = mediaMetadataRetriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }


    public static void menuWork(final AppCompatActivity context, final BottomNavigationView bottomNavigationView, final int id, final boolean dismiss, final int frompos) {
//        bottomNavigationView.setItemIconTintList(null);
//        bottomNavigationView.getMenu().findItem(R.id.bottom_home).getIcon().setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_OVER);
//        bottomNavigationView.getMenu().findItem(R.id.bottom_profile).getIcon().setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_OVER);
//        bottomNavigationView.getMenu().findItem(R.id.bottom_exam).getIcon().setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_OVER);
//        bottomNavigationView.getMenu().findItem(R.id.bottom_result).getIcon().setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_OVER);
        bottomNavigationView.setSelectedItemId(id);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        context.setTitle("Home");
                        setFragment(context, new MainFragment(), R.id.frame);
                        break;
                    case R.id.bottom_exam:
                        context.setTitle("Enrollment");
                        setFragment(context, new EnrollmentFragment(), R.id.frame);
                        break;
                    case R.id.bottom_profile:
                        context.setTitle("Profile");
                        setFragment(context, new ProfileFragment(), R.id.frame);
                        break;
                }
                return true;
            }
        });

    }

    public static void menuOtherWork(final AppCompatActivity context, final BottomNavigationView bottomNavigationView, final int id, final boolean dismiss, final int frompos) {
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(id);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        Utility.sendToActivityClear(context, DrawerActivity.class, new String[]{"pos;1"});
                        break;
                    case R.id.bottom_exam:
                        Utility.sendToActivityClear(context, DrawerActivity.class, new String[]{"pos;2"});
                        break;
                    case R.id.bottom_profile:
                        Utility.sendToActivityClear(context, DrawerActivity.class, new String[]{"pos;4"});
                        break;
                }
                return true;
            }
        });

    }

    public static void setFragment(AppCompatActivity activity, Fragment fragment, int id) {
        FragmentTransaction t = activity.getSupportFragmentManager().beginTransaction();
        t.replace(id, fragment);
        t.commit();
    }

    public static void sendToActivity(AppCompatActivity activity, Class className) {
        Intent intent = new Intent(activity, className);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }

    public static void sendToActivity(AppCompatActivity activity, Class className, int flags) {
        Intent intent = new Intent(activity, className);
        intent.setFlags(flags);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }

    public static void sendToActivity(AppCompatActivity activity, Class className, String[] strings) {
        Intent intent = new Intent(activity, className);
        for (int i = 0; i < strings.length; i++) {
            String s1 = strings[i];
            String s2[] = s1.split(";");
            intent.putExtra(s2[0], s2[1]);
        }
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }

    public static void sendToActivityClear(AppCompatActivity activity, Class className, String[] strings) {
        Intent intent = new Intent(activity, className);
        for (int i = 0; i < strings.length; i++) {
            String s1 = strings[i];
            String s2[] = s1.split(";");
            intent.putExtra(s2[0], s2[1]);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }

}
