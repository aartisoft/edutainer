package com.edutainer.in.workplace.Splash;

import android.content.Context;

public class SplashContract {

    interface SplashPresenter{
        void loadModel(Context context);

        void onDestroy();

    }

    interface SplashView{
        void showProgress();

        void hideProgress();

        void handleLoadModel(String string);
    }

    interface SplashInteraction{
        interface OnLoadModelFinishedListener {
            void onLoadModelFinished(String string);
        }
        void loadModel(Context context,OnLoadModelFinishedListener onLoadModelFinishedListener);
    }
}
