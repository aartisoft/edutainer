package com.edutainer.in.workplace.Splash;

import android.content.Context;

public class SplashContract {

    interface SplashPresenter{
        void loadModel(Context context);

        void loadEnrolled(Context context, String userId);

        void onDestroy();

    }

    interface SplashView{
        void showProgress();

        void hideProgress();

        void handleLoadModel(String string);

        void handleEnrolled(String string);
    }

    interface SplashInteraction{
        interface OnLoadModelFinishedListener {
            void onLoadModelFinished(String string);
        }
        void loadModel(Context context,OnLoadModelFinishedListener onLoadModelFinishedListener);

        interface OnEnrolledFinishedListener {
            void onEnrolledFinished(String string);
        }
        void enrolled(Context context, String userId, OnEnrolledFinishedListener onEnrolledFinishedListener);
    }
}
