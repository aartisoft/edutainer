package com.edutainer.in.workplace.Login;

import android.content.Context;

public class LoginPresenterImpl implements LoginContract.LoginPresenter,
        LoginContract.LoginInteraction.OnForgetFinishedListener,
        LoginContract.LoginInteraction.OnLoginFinishedListener {

    private LoginContract.LoginView loginView;
    private LoginContract.LoginInteraction loginInteraction;

    public LoginPresenterImpl(LoginContract.LoginView loginView, LoginContract.LoginInteraction loginInteraction) {
        this.loginView = loginView;
        this.loginInteraction = loginInteraction;
    }

    @Override
    public void onLoginFinished(String string) {
        loginView.handleLogin(string);
    }

    @Override
    public void onForgetFinished(String string) {
        loginView.handleForgotPassword(string);
    }

    @Override
    public void login(String email_mobile, String password, Context context) {
        loginInteraction.login(email_mobile, password, context, this);
    }

    @Override
    public void forgot_password(String email_mobile, Context context) {
        loginInteraction.forgotPassword(email_mobile, context, this);
    }

    @Override
    public void onDestroy() {
        loginView =null;
    }
}
