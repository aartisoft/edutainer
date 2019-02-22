package com.edutainer.in.workplace.Checkout;

import android.content.Context;
import com.edutainer.in.workplace.Helper.CommonFunctions;
import com.edutainer.in.workplace.Helper.OkHttpAsyncResponse;

public class GetCheckoutInteractionImpl implements CheckoutContract.CheckoutInteraction{

    @Override
    public void coupon(Context context, String coupon, final OnCouponFinishedListener onCouponFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.coupon_code(coupon, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onCouponFinishedListener.onCouponFinished(output);
                }
            });
        }else {
            onCouponFinishedListener.onCouponFinished("No network");
        }
    }

    @Override
    public void address(Context context, String street, String town, String state, String pinCode, String userId, final OnAddressFinishedListener onAddressFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.address(street, town, state, pinCode, userId, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onAddressFinishedListener.onAddressFinished(output);
                }
            });
        }else {
            onAddressFinishedListener.onAddressFinished("No network");
        }
    }

    @Override
    public void payment(Context context, String user_id, String course_id, String status, String order_id, String date, String coupon_code, final OnPaymentFinishedListener onPaymentFinishedListener) {
        if (CommonFunctions.isNetworkAvailable(context)){
            CommonFunctions.payment(user_id, course_id, status, order_id, date, coupon_code, new OkHttpAsyncResponse() {
                @Override
                public void processFinish(String output) {
                    onPaymentFinishedListener.onPaymentFinished(output);
                }
            });
        }else {
            onPaymentFinishedListener.onPaymentFinished("No network");
        }
    }
}
