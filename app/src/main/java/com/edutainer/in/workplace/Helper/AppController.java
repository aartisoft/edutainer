package com.edutainer.in.workplace.Helper;

import android.support.multidex.MultiDexApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class AppController extends MultiDexApplication {
    private OkHttpClient okHttpClient;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        okHttpClient = new OkHttpClient.Builder().
                connectTimeout(100, TimeUnit.SECONDS).
                writeTimeout(10, TimeUnit.SECONDS).
                readTimeout(30, TimeUnit.SECONDS).
                build();
        mInstance = this;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static synchronized AppController getInstance()
    {
        return mInstance;
    }
}