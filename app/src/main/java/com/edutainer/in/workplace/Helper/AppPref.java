package com.edutainer.in.workplace.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import com.edutainer.in.QuizApplication;

import static android.content.Context.MODE_PRIVATE;

public class AppPref {

    private static AppPref instance;
    private final String USERNAME = "user_name";
    private final String USEREMAIL = "user_email";
    private final String USERMOBILE = "user_mobile";
    private final String USERIMEI = "user_imei";
    private final String USERCONTACT = "user_contact";
    private final String USERID = "user_id";
    private final String USERNOTIFICATION = "user_notification";
    private final String USERSTREAM = "user_stream";
    private final String REFERCODE = "refer_code";
    private final String GENCODE = "gen_code";


    private SharedPreferences sPreferences;
    private SharedPreferences.Editor sEditor;
    private String SG_SHARED_PREFERENCES = "shared_preferences";


    private AppPref(Context context) {

        sPreferences = context.getSharedPreferences(SG_SHARED_PREFERENCES,
                MODE_PRIVATE);
        sEditor = sPreferences.edit();
    }


    public static AppPref getInstance() {
        if (instance == null) {
            synchronized (AppPref.class) {
                if (instance == null) {
                    instance = new AppPref(AppController.getInstance().getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void registerPre(OnSharedPreferenceChangeListener listener) {
        sPreferences.registerOnSharedPreferenceChangeListener(listener);
    }


    public void unRegister(OnSharedPreferenceChangeListener listener) {
        sPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }


    public String getUSERNAME() {
        return sPreferences.getString(USERNAME, "");
    }

    public void setUSERNAME(String user_name) {
        sEditor.putString(USERNAME, user_name);
        sEditor.commit();
    }


    public String getReferCode() {
        return sPreferences.getString(REFERCODE, "");
    }

    public void setReferCode(String refer_code) {
        sEditor.putString(REFERCODE, refer_code);
        sEditor.commit();
    }

    public String getGenCode() {
        return sPreferences.getString(GENCODE, "");
    }

    public void setGenCode(String gen_code) {
        sEditor.putString(GENCODE, gen_code);
        sEditor.commit();
    }


    public String getUSEREMAIL() {
        return sPreferences.getString(USEREMAIL, "");
    }

    public void setUSEREMAIL(String user_email) {
        sEditor.putString(USEREMAIL, user_email);
        sEditor.commit();
    }

    public void setUSERMOBILE(String user_mobile) {
        sEditor.putString(USERMOBILE, user_mobile);
        sEditor.commit();
    }

    public String getUSERMOBILE() {
        return sPreferences.getString(USERMOBILE, "");
    }

    public void setUSERIMEI(String userimei) {
        sEditor.putString(USERIMEI, userimei);
        sEditor.commit();
    }

    public String getUSERIMEI() {
        return sPreferences.getString(USERIMEI, "");
    }

    public void setUSERCONTACT(String usercontact) {
        sEditor.putString(USERCONTACT, usercontact);
        sEditor.commit();
    }

    public String getUSERCONTACT() {
        return sPreferences.getString(USERCONTACT, "");
    }

    public void setUserId(String userId) {
        sEditor.putString(USERID, userId);
        sEditor.commit();
    }

    public String getUserId() {
        return sPreferences.getString(USERID, "");
    }

    public void setUSERNOTIFICATION(String usernotification) {
        sEditor.putString(USERNOTIFICATION, usernotification);
        sEditor.commit();
    }

    public String getUSERNOTIFICATION() {
        return sPreferences.getString(USERNOTIFICATION, "true");
    }


    public void setUSERSTREAM(String userstream) {
        sEditor.putString(USERSTREAM, userstream);
        sEditor.commit();
    }

    public String getUSERSTREAM() {
        return sPreferences.getString(USERSTREAM, "0");
    }
}
