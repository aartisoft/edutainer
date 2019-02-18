package com.edutainer.in.workplace.Login;

import android.content.Context;

public class LoginContract {
    interface LoginPresenter{
        void login(String email_mobile, String password, Context context);

        void forgot_password(String email_mobile, Context context);

        void onDestroy();

    }

    interface LoginView{
        void showProgress();

        void hideProgress();

        void handleLogin(String string);

        void handleForgotPassword(String string);

        void showForgotDialog();
    }

    interface LoginInteraction{
        interface OnLoginFinishedListener {
            void onLoginFinished(String string);
        }
        void login(String email_mobile, String password, Context context, OnLoginFinishedListener onLoginFinishedListener);

        interface OnForgetFinishedListener {
            void onForgetFinished(String string);
        }
        void forgotPassword(String email_mobile, Context context, OnForgetFinishedListener onForgetFinishedListener);
    }
}
