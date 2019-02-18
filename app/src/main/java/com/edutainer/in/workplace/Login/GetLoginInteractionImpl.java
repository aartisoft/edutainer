package com.edutainer.in.workplace.Login;

import android.content.Context;
import com.edutainer.in.workplace.Helper.CommonFunctions;
import com.edutainer.in.workplace.Helper.OkHttpAsyncResponse;

public class GetLoginInteractionImpl implements LoginContract.LoginInteraction{

    @Override
    public void login(String email_mobile, String password, Context context, final OnLoginFinishedListener onLoginFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.login(email_mobile, password, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onLoginFinishedListener.onLoginFinished(output);
                }
            });
        }else {
            onLoginFinishedListener.onLoginFinished("No network");
        }
    }

    @Override
    public void forgotPassword(String email_mobile, Context context, final OnForgetFinishedListener onForgetFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.forgotPassword(email_mobile, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onForgetFinishedListener.onForgetFinished(output);
                }
            });
        }else {
            onForgetFinishedListener.onForgetFinished("No network");
        }
    }
}
