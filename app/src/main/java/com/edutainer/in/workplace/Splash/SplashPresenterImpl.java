package com.edutainer.in.workplace.Splash;

import android.content.Context;

public class SplashPresenterImpl implements SplashContract.SplashPresenter,
    SplashContract.SplashInteraction.OnLoadModelFinishedListener,
    SplashContract.SplashInteraction.OnEnrolledFinishedListener
{

    private SplashContract.SplashView splashView;
    private SplashContract.SplashInteraction splashInteraction;

    public SplashPresenterImpl(SplashContract.SplashView splashView, SplashContract.SplashInteraction splashInteraction) {
        this.splashView = splashView;
        this.splashInteraction = splashInteraction;
    }

    @Override
    public void loadModel(Context context) {
        splashInteraction.loadModel(context, this);
    }

    @Override
    public void loadEnrolled(Context context, String userId) {
        splashInteraction.enrolled(context, userId, this);
    }

    @Override
    public void onLoadModelFinished(String string) {
        splashView.handleLoadModel(string);
    }

    @Override
    public void onDestroy() {
        splashView = null;
    }

    @Override
    public void onEnrolledFinished(String string) {
        splashView.handleEnrolled(string);
    }
}
