package com.edutainer.in.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;
import android.view.Window;
import android.view.WindowManager;

import com.edutainer.in.R;


public abstract class RuntimePermissionsActivity extends AppCompatActivity {
    private SparseIntArray mErrorString = new SparseIntArray();
    static final int REQUEST_PERMISSIONS = 20;

    public void onCreate(Bundle save) {
        super.onCreate(save);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getActivityLayout());

        initialize(save);
    }


    public abstract void initialize(Bundle save);

    public abstract int getActivityLayout();


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionsGranted(requestCode);
        }
        else {
//            showMessageOKCancel("You need to allow access to the permissions running the Application",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                requestAppPermissions(new
//                                                String[]{Manifest.permission.READ_CONTACTS,
//                                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                                                Manifest.permission.READ_SMS,
//                                                Manifest.permission.RECEIVE_SMS}, R.string
//                                                .runtime_permissions_txt
//                                        , REQUEST_PERMISSIONS);
//                            }
//                        }
//                    },
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    });
            onPermissionsGranted(requestCode);
        }
    }

    public void requestAppPermissions(final String[] requestedPermissions,
                                      final int stringId, final int requestCode) {
        mErrorString.put(requestCode, stringId);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                ActivityCompat.requestPermissions(RuntimePermissionsActivity.this, requestedPermissions, requestCode);
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        } else {
            onPermissionsGranted(requestCode);
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener,
                                     DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(RuntimePermissionsActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", cancelListener)
                .create()
                .show();
    }

    public abstract void onPermissionsGranted(int requestCode);


    public void sendToActivity(Class className) {
        Intent intent = new Intent(this, className);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }

    public void sendToActivity(Class className, int flags) {
        Intent intent = new Intent(this, className);
        intent.setFlags(flags);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }

    public void sendToActivity(Class className, String[] strings) {
        Intent intent = new Intent(this, className);
        for (int i = 0; i < strings.length; i++) {
            String s1 = strings[i];
            String s2[] = s1.split(";");
            intent.putExtra(s2[0], s2[1]);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
    }
}
