package com.edutainer.in.workplace.Checkout;

import android.content.Context;

public class CheckoutPresenterImpl implements CheckoutContract.CheckoutPresenter,
        CheckoutContract.CheckoutInteraction.OnCouponFinishedListener,
        CheckoutContract.CheckoutInteraction.OnAddressFinishedListener,
        CheckoutContract.CheckoutInteraction.OnPaymentFinishedListener
{

    private CheckoutContract.CheckoutView checkoutView;
    private CheckoutContract.CheckoutInteraction checkoutInteraction;

    public CheckoutPresenterImpl(CheckoutContract.CheckoutView checkoutView, CheckoutContract.CheckoutInteraction checkoutInteraction) {
        this.checkoutView = checkoutView;
        this.checkoutInteraction = checkoutInteraction;
    }

    @Override
    public void coupon(Context context, String coupon) {
        checkoutInteraction.coupon(context, coupon, this);
    }

    @Override
    public void address(Context context, String street, String town, String state, String pinCode, String userId) {
        checkoutInteraction.address(context, street, town, state, pinCode, userId, this);
    }

    @Override
    public void payment(Context context, String user_id, String course_id, String status, String order_id, String date, String coupon_code) {
        checkoutInteraction.payment(context, user_id, course_id, status, order_id, date, coupon_code, this);
    }

    @Override
    public void onDestroy() {
        checkoutView = null;
    }

    @Override
    public void onCouponFinished(String string) {
        checkoutView.handleCoupon(string);
    }

    @Override
    public void onAddressFinished(String string) {
        checkoutView.handleAddress(string);
    }

    @Override
    public void onPaymentFinished(String string) {
        checkoutView.handlePayment(string);
    }
}
