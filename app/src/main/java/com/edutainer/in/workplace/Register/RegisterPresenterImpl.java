package com.edutainer.in.workplace.Register;

import android.content.Context;

public class RegisterPresenterImpl implements RegisterContract.RegisterPresenter,
        RegisterContract.RegisterInteraction.OnRegisterFinishedListener {

    private RegisterContract.RegisterView registerView;
    private RegisterContract.RegisterInteraction registerInteraction;

    public RegisterPresenterImpl(RegisterContract.RegisterView registerView, RegisterContract.RegisterInteraction registerInteraction) {
        this.registerView = registerView;
        this.registerInteraction = registerInteraction;
    }

    @Override
    public void onRegisterFinished(String string) {
        registerView.handleRegister(string);
    }

    @Override
    public void register(String name, String email, String mobile, String password, String gen_code, String ref_code, Context context) {
        registerInteraction.register(name, email, mobile, password, gen_code, ref_code, context, this);
    }

    @Override
    public void onDestroy() {
        registerView = null;
    }
}
