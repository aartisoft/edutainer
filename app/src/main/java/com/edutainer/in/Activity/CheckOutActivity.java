package com.edutainer.in.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.BaseActivity;
import com.edutainer.in.Utility.CustomEditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class CheckOutActivity extends BaseActivity implements ApiResponse, PaymentResultListener, View.OnClickListener {

    private LinearLayout provide_shipping_address;
    private CustomEditText street_address;
    private CustomEditText town_city;
    private CustomEditText pincode;
    private Spinner state;
    private TextView product;
    private TextView amount;
    private TextView gst;
    private TextView total;
    private String kit;
    private TextView apply_coupon;
    private Button checkout;
    private String coupon_code = "";

    private String course_cost = "";
    private String course_id = "";

    private String states[] = {"Andhra Pradesh",
            "Arunachal Pradesh",
            "Assam",
            "Bihar",
            "Chhattisgarh",
            "Goa",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jammu and Kashmir",
            "Jharkhand",
            "Karnataka",
            "Kerala",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Meghalaya",
            "Mizoram",
            "Nagaland",
            "Orissa",
            "Punjab",
            "Rajasthan",
            "Sikkim",
            "Tamil Nadu",
            "Telangana",
            "Tripura",
            "Uttarakhand",
            "Uttar Pradesh",
            "West Bengal",
            "Andaman and Nicobar Islands",
            "Chandigarh",
            "Dadraand Nagar Haveli",
            "Daman and Diu",
            "Delhi",
            "Lakshadeep",
            "Pondicherry"};

    @Override
    public void initialize(Bundle save) {


        save = getIntent().getExtras();
        kit = save.getString("check");
        course_id = save.getString("course_id");

        setTitle(save.getString("product"));
        provide_shipping_address = (LinearLayout) findViewById(R.id.provide_shipping_address);

        street_address = (CustomEditText) findViewById(R.id.street_address);
        town_city = (CustomEditText) findViewById(R.id.town_city);
        pincode = (CustomEditText) findViewById(R.id.pincode);
        product = (TextView) findViewById(R.id.product);
        amount = (TextView) findViewById(R.id.amount);
        gst = (TextView) findViewById(R.id.gst);
        total = (TextView) findViewById(R.id.total);
        checkout = (Button) findViewById(R.id.checkout);
        apply_coupon = (TextView) findViewById(R.id.apply_coupon);

        state = (Spinner) findViewById(R.id.state);

        state.setAdapter(new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, states));


        if (kit.equalsIgnoreCase("true")) {
            provide_shipping_address.setVisibility(View.VISIBLE);

            String kit_cost = save.getString("kit_cost");
            String price = save.getString("price");
            int cost = Integer.parseInt(kit_cost);
            int cost1 = Integer.parseInt(price);

            cost1 = cost1 + cost;
            amount.setText(cost1 + " INR");

            int percent = (cost1 * 18) / 100;
            cost1 = cost1 + percent;
            course_cost = cost1 + "";
            total.setText(cost1 + " INR");

        } else {
            provide_shipping_address.setVisibility(View.GONE);

            String price = save.getString("price");
            int cost1 = Integer.parseInt(price);

            amount.setText(cost1 + " INR");

            int percent = (cost1 * 18) / 100;
            cost1 = cost1 + percent;
            course_cost = cost1 + "";
            total.setText(cost1 + " INR");
        }

        product.setText(save.getString("product"));
        gst.setText("18%");

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout();
            }
        });
        apply_coupon.setOnClickListener(this);

        apply_coupon.setVisibility(View.GONE);
    }


    private void checkout() {
        if (kit.equalsIgnoreCase("true")) {
            if (street_address.getValue().equalsIgnoreCase("")) {
                street_address.setError("Please Provide Street Address");
                return;
            } else if (town_city.getValue().equalsIgnoreCase("")) {
                town_city.setError("Please Enter Town/City");
                return;
            } else if (pincode.getValue().equalsIgnoreCase("")) {
                pincode.setError("Please Enter Pincode");
                return;
            }


            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("street=").append(street_address.getValue().toString());
            stringBuilder.append("&town=").append(town_city.getValue().toString());
            stringBuilder.append("&state=").append(state.getSelectedItem().toString());
            stringBuilder.append("&pincode=").append(pincode.getValue().toString());
            stringBuilder.append("&user_id=").append(AppPref.getInstance().getUserId());

            String content = stringBuilder.toString();

            Log.w("content", content);

            ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.USER_ADDRESS, 0, content, true, "loading ...", this);
            apiConsumer.execute();
        } else {
            paynow();
        }
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_checkout;
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            if (responseData.equalsIgnoreCase("address added")) {
                paynow();
            } else {
                toastMessage("Some Error Occurred");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {


        String time = "";

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int DATE = calendar.get(Calendar.DATE);
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        time = year + "-" + month + "-" + DATE + " " + HOUR + ":" + MINUTE + ":" + second;


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getUserId());
        stringBuilder.append("&course_id=").append(course_id);
        stringBuilder.append("&status=").append("paid");
        stringBuilder.append("&orderid=").append(s);
        stringBuilder.append("&date=").append(time);
        stringBuilder.append("&coupon_code=").append(coupon_code);

        String content = stringBuilder.toString();

        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.BUY_COURSE, 0, content, true, "loading ...", new ApiResponse() {
            @Override
            public void getApiResponse(String responseData, int serviceCounter) {
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String status = jsonObject.getString("status");
                    String msg = jsonObject.getString("msg");

                    if (status.equalsIgnoreCase("1")) {
                        Intent intent = new Intent(CheckOutActivity.this, ReceiptActivity.class);
                        intent.putExtra("status", "success");
                        intent.putExtra("amount", course_cost);
                        intent.putExtra("course", getTitle());
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        apiConsumer.execute();


    }

    @Override
    public void onPaymentError(int i, String s) {

        String time = "";

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int DATE = calendar.get(Calendar.DATE);
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        time = year + "-" + month + "-" + DATE + " " + HOUR + ":" + MINUTE + ":" + second;


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getUserId());
        stringBuilder.append("&course_id=").append(course_id);
        stringBuilder.append("&status=").append("Failed");
        stringBuilder.append("&orderid=").append(s);
        stringBuilder.append("&date=").append(time);
        stringBuilder.append("&coupon_code=").append(coupon_code);

        String content = stringBuilder.toString();

        ApiConsumer apiConsumer = new ApiConsumer(this, AppUrl.BUY_COURSE, 0, content, true, "loading ...", new ApiResponse() {
            @Override
            public void getApiResponse(String responseData, int serviceCounter) {
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    String status = jsonObject.getString("status");
                    String msg = jsonObject.getString("msg");


                    Intent intent = new Intent(CheckOutActivity.this, ReceiptActivity.class);
                    intent.putExtra("status", "failure");
                    intent.putExtra("amount", course_cost);
                    intent.putExtra("course", getTitle());
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        apiConsumer.execute();

    }


    private void paynow() {
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: Rentomojo || HasGeek etc.
             */
            options.put("name", "Merchant Name");


            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Order #123456");

            options.put("currency", "INR");

            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            long cost_c = Integer.parseInt(course_cost) * 100;
            options.put("amount", cost_c + "");

            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("Error", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.apply_coupon:
                if (apply_coupon.getText().toString().equalsIgnoreCase("Apply Coupon Code"))
                    applycoupon();
                else
                    apply_coupon.setText("Apply Coupon Code");
                break;
        }
    }

    private void applycoupon() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inf = LayoutInflater.from(this);
        View v1 = inf.inflate(R.layout.layout_apply_coupon, null);
        final CustomEditText enter_coupon_code = (CustomEditText) v1.findViewById(R.id.enter_coupon_code);


        final Button btn_apply_coupon = (Button) v1.findViewById(R.id.apply_coupon);
        ImageView back = (ImageView) v1.findViewById(R.id.back);


        dialog.setView(v1);

        final Dialog dialog1 = dialog.create();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });

        btn_apply_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coupon_code = enter_coupon_code.getValue().toString();

                if (coupon_code.equalsIgnoreCase("")) {
                    enter_coupon_code.setError("Please Enter Coupon Code");
                    return;
                }


                try {
                    dialog1.dismiss();

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("coupon_code=").append(coupon_code);

                    String content = stringBuilder.toString();

                    ApiConsumer apiconsumer = new ApiConsumer(CheckOutActivity.this, AppUrl.APPLY_COUPON, 0, content, true, "", new ApiResponse() {
                        @Override
                        public void getApiResponse(String responseData, int serviceCounter) {
                            try {
                                Log.d("responseData", responseData);
                                JSONObject jsonObject = new JSONObject(responseData);
                                JSONArray jsonArray = jsonObject.getJSONArray("login");
                                if (jsonArray.length() > 0) {
                                    apply_coupon.setText(coupon_code + " coupon applied X");
                                } else {
                                    coupon_code = "";
                                    toastMessage("Coupon Code Not Valid");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    apiconsumer.execute();

                } catch (Exception js) {
                    js.printStackTrace();
                }
            }
        });
        dialog1.show();
    }
}
