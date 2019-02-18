package com.edutainer.in.workplace.Register;

import android.content.Context;
import com.edutainer.in.workplace.Helper.CommonFunctions;
import com.edutainer.in.workplace.Helper.OkHttpAsyncResponse;

public class GetRegisterInteractionImpl implements RegisterContract.RegisterInteraction {
    @Override
    public void register(String name, String email, String mobile, String password, String gen_code, String ref_code, Context context, final OnRegisterFinishedListener onRegisterFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.register(name, email, mobile, password, gen_code, ref_code, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onRegisterFinishedListener.onRegisterFinished(output);
                }
            });
        }else {
            onRegisterFinishedListener.onRegisterFinished("No network");
        }
    }
}
