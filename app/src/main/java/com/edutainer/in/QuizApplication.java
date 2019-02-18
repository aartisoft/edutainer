package com.edutainer.in;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import com.edutainer.in.Model.CategoryModel;
import com.edutainer.in.Model.HomeCategoryModel;
import com.edutainer.in.Utility.AnalyticsTrackers;
import com.edutainer.in.Utility.TypefaceUtil;

import java.util.ArrayList;

/**
 * Created by TalkCharge on 23-02-2018.
 */

public class QuizApplication extends Application {
    private static QuizApplication instance;
    public static ArrayList<HomeCategoryModel> categoryModelArrayList;
    public static ArrayList<CategoryModel> subcategoryModelArrayList;
    public static final String FACEBOOK_AID_CODE = "1597812857005158_1597813120338465";
    public static final String YOUTUBE_API_KEY = "AIzaSyD0O7wZcmyCgZNGlqm_p6GlRtVn8Xvd9VY";

    public QuizApplication() {
        super();
    }

    public void onCreate() {
        super.onCreate();
        instance = this;

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "AvenirLTStd-Book.otf");

        AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);
    }


    public static QuizApplication getInstance() {
        if (instance == null) {
            instance = new QuizApplication();
        }
        return instance;
    }

    public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsTrackers analyticsTrackers = AnalyticsTrackers.getInstance();
        return analyticsTrackers.get(AnalyticsTrackers.Target.APP);
    }

    public void trackScreenView(String screenName) {
        Tracker t = getGoogleAnalyticsTracker();
        t.setScreenName(screenName);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    public void trackException(Exception e) {
        if (e != null) {
            Tracker t = getGoogleAnalyticsTracker();

            t.send(new HitBuilders.ExceptionBuilder()
                    .setDescription(
                            new StandardExceptionParser(this, null)
                                    .getDescription(Thread.currentThread().getName(), e))
                    .setFatal(false)
                    .build()
            );
        }
    }

    public void trackEvent(String category, String action, String label) {
        Tracker t = getGoogleAnalyticsTracker();

        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
    }
}
