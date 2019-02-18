package com.edutainer.in.workplace.Splash;

import android.content.Context;

import com.edutainer.in.workplace.Helper.CommonFunctions;
import com.edutainer.in.workplace.Helper.OkHttpAsyncResponse;

public class GetSplashInteractionImpl implements SplashContract.SplashInteraction {
    @Override
    public void loadModel(Context context, final OnLoadModelFinishedListener onLoadModelFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.loadCategory(new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onLoadModelFinishedListener.onLoadModelFinished(output);
                }
            });
        }else {
            onLoadModelFinishedListener.onLoadModelFinished("No network");
        }
    }
}
