package com.edutainer.in.workplace.Register;

import android.content.Context;

public class RegisterContract {
    interface RegisterPresenter{
        void register(String name, String email, String mobile, String password, String gen_code, String coupon_code, Context context);

        void onDestroy();

    }

    interface RegisterView{
        void showProgress();

        void hideProgress();

        void handleRegister(String string);

        void showReferDialog();
    }

    interface RegisterInteraction{
        interface OnRegisterFinishedListener {
            void onRegisterFinished(String string);
        }
        void register(String name, String email, String mobile, String password, String gen_code, String coupon_code, Context context, OnRegisterFinishedListener onRegisterFinishedListener);
    }
}
