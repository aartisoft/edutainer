package com.edutainer.in.workplace.Checkout;

import android.content.Context;

public class CheckoutContract {

    interface CheckoutPresenter{
        void coupon(Context context, String coupon);

        void address(Context context, String street, String town, String state, String pinCode, String userId);

        void payment(Context context, String user_id, String course_id, String status, String order_id, String date, String coupon_code);

        void onDestroy();
    }

    interface CheckoutView{
        void showProgress();

        void hideProgress();

        void handleCoupon(String string);

        void handleAddress(String string);

        void handlePayment(String string);
    }

    interface CheckoutInteraction{
        interface OnCouponFinishedListener {
            void onCouponFinished(String string);
        }
        void coupon(Context context, String id, OnCouponFinishedListener onCouponFinishedListener);

        interface OnAddressFinishedListener {
            void onAddressFinished(String string);
        }
        void address(Context context, String street, String town, String state, String pinCode, String userId, OnAddressFinishedListener onAddressFinishedListener);

        interface OnPaymentFinishedListener {
            void onPaymentFinished(String string);
        }
        void payment(Context context, String user_id, String course_id, String status, String order_id, String date, String coupon_code, OnPaymentFinishedListener onPaymentFinishedListener);
    }
}
