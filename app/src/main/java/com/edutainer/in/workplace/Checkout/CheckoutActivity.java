package com.edutainer.in.workplace.Checkout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.edutainer.in.Activity.CheckOutActivity;
import com.edutainer.in.Activity.ReceiptActivity;
import com.edutainer.in.R;
import com.edutainer.in.workplace.Helper.AppPref;
import com.edutainer.in.workplace.Model.CourseModel;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class CheckoutActivity extends AppCompatActivity implements CheckoutContract.CheckoutView,
        PaymentResultListener,
        View.OnClickListener {

    ImageView iv_product;
    TextView tv_title;
    TextView tv_category;
    TextView tv_duration;
    TextView tv_duration_value;
    TextView tv_course_cost;
    TextView tv_course_cost_value;
    TextView tv_kit_cost;
    TextView tv_kit_cost_value;
    TextView tv_address;
    TextView tv_amount;
    TextView tv_amount_value;
    TextView tv_gst;
    TextView tv_gst_value;
    TextView tv_total;
    TextView tv_total_value;
    TextView tv_coupon;
    CardView cv_login;
    Button btn_pay;
    LinearLayout ll_address;
    EditText et_street_address;
    EditText et_town_city;
    Spinner spinner_state;
    EditText et_pincode;

    CheckoutContract.CheckoutPresenter presenter;
    CourseModel courseModel;
    Dialog dialog;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_test);

        Toolbar toolbar = findViewById(R.id.toolbar_checkout);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Checkout");

        Typeface OpenSans_Regular = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Regular.ttf");
        Typeface OpenSans_Bold = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Bold.ttf");
        Typeface OpenSans_SemiBold = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Semibold.ttf");

        courseModel = getIntent().getParcelableExtra("COURSE");

        presenter = new CheckoutPresenterImpl(this, new GetCheckoutInteractionImpl());

        iv_product = findViewById(R.id.iv_product);
        Glide
                .with(getBaseContext())
                .load(getIntent().getIntExtra("IMAGE", R.drawable.ic_iot_basics))
                .into(iv_product);

        tv_title = findViewById(R.id.tv_title);
        tv_title.setTypeface(OpenSans_Bold);
        tv_title.setText(courseModel.getCourse_name());

        tv_category = findViewById(R.id.tv_category);
        tv_category.setTypeface(OpenSans_SemiBold);
        tv_category.setText(courseModel.getCat_name());

        tv_duration = findViewById(R.id.tv_duration);
        tv_duration.setTypeface(OpenSans_SemiBold);

        tv_duration_value = findViewById(R.id.tv_duration_value);
        tv_duration_value.setTypeface(OpenSans_SemiBold);
        tv_duration_value.setText(courseModel.getCourse_duration());


        tv_course_cost = findViewById(R.id.tv_course_cost);
        tv_course_cost.setTypeface(OpenSans_SemiBold);

        tv_course_cost_value = findViewById(R.id.tv_course_cost_value);
        tv_course_cost_value.setTypeface(OpenSans_SemiBold);
        tv_course_cost_value.setText("₹ " +courseModel.getCourse_cost());

        tv_kit_cost = findViewById(R.id.tv_kit_cost);
        tv_kit_cost.setTypeface(OpenSans_SemiBold);

        tv_kit_cost_value = findViewById(R.id.tv_kit_cost_value);
        tv_kit_cost_value.setTypeface(OpenSans_SemiBold);
        tv_kit_cost_value.setText("₹ " +courseModel.getKit_cost());

        tv_address = findViewById(R.id.tv_address);
        tv_address.setTypeface(OpenSans_Bold);


        tv_amount = findViewById(R.id.tv_amount);
        tv_amount.setTypeface(OpenSans_SemiBold);

        tv_amount_value = findViewById(R.id.tv_amount_value);
        tv_amount_value.setTypeface(OpenSans_SemiBold);

        tv_gst = findViewById(R.id.tv_gst);
        tv_gst.setTypeface(OpenSans_SemiBold);

        tv_gst_value = findViewById(R.id.tv_gst_value);
        tv_gst_value.setTypeface(OpenSans_SemiBold);

        tv_total = findViewById(R.id.tv_total);
        tv_total.setTypeface(OpenSans_Bold);

        tv_total_value = findViewById(R.id.tv_total_value);
        tv_total_value.setTypeface(OpenSans_Bold);

        tv_coupon = findViewById(R.id.tv_coupon);
        tv_coupon.setTypeface(OpenSans_SemiBold);
        tv_coupon.setOnClickListener(this);

        ll_address = findViewById(R.id.ll_address);

        et_street_address = findViewById(R.id.et_street_address);
        et_street_address.setTypeface(OpenSans_SemiBold);

        et_town_city = findViewById(R.id.et_town_city);
        et_town_city.setTypeface(OpenSans_SemiBold);

        spinner_state = findViewById(R.id.spinner_state);
        spinner_state.setAdapter(new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, states));

        et_pincode = findViewById(R.id.et_pincode);
        et_pincode.setTypeface(OpenSans_SemiBold);

        cv_login = findViewById(R.id.cv_login);

        btn_pay = findViewById(R.id.btn_pay);
        btn_pay.setTypeface(OpenSans_SemiBold);
        btn_pay.setOnClickListener(this);

        if (getIntent().getBooleanExtra("CHECK", false)){
            ll_address.setVisibility(View.VISIBLE);
            tv_kit_cost.setVisibility(View.VISIBLE);
            tv_kit_cost_value.setVisibility(View.VISIBLE);

            long cost1 = Integer.parseInt(courseModel.getCourse_cost());
            long cost2 = Integer.parseInt(courseModel.getKit_cost());

            long amount = cost1 + cost2;
            long gst =  (amount *18) / 100;
            long total =  amount + gst;

            course_cost = total+"";
            tv_amount_value.setText("₹ " + amount);
            tv_gst_value.setText("₹ " + gst);
            tv_total_value.setText("₹ " + total);

        }else {
            ll_address.setVisibility(View.GONE);
            tv_kit_cost.setVisibility(View.GONE);
            tv_kit_cost_value.setVisibility(View.GONE);

            long cost1 = Integer.parseInt(courseModel.getCourse_cost());

            long amount = cost1 ;
            long gst =  (amount *18) / 100;
            long total =  amount + gst;

            course_cost = total+"";
            tv_amount_value.setText("₹ " + amount);
            tv_gst_value.setText("₹ " + gst);
            tv_total_value.setText("₹ " + total);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        super.onOptionsItemSelected(menuItem);
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_coupon:
                applyCoupon();
                break;
            case R.id.btn_pay:
                checkout();
                break;
        }
    }

    private void checkout() {
        if (getIntent().getBooleanExtra("CHECK", false)) {

            String street = et_street_address.getText().toString();
            String town = et_town_city.getText().toString();
            String state = spinner_state.getSelectedItem().toString();
            String pincode = et_pincode.getText().toString();

            if (et_street_address.getText().toString().equalsIgnoreCase("")) {
                et_street_address.setError("Please Provide Street Address");
                return;
            } else if (et_town_city.getText().toString().equalsIgnoreCase("")) {
                et_town_city.setError("Please Enter Town/City");
                return;
            } else if (et_pincode.getText().toString().equalsIgnoreCase("")) {
                et_pincode.setError("Please Enter Pincode");
                return;
            }

            presenter.address(CheckoutActivity.this, street, town, state, pincode, AppPref.getInstance().getUserId());

        } else {
            paynow();
        }
    }

    private void applyCoupon() {

        Dialog dialog  = new Dialog(CheckoutActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(true);
        //...that's the layout i told you will inflate later

        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_coupon, null, false);
        dialog.setContentView(view);

        final EditText et_coupon_code = view.findViewById(R.id.et_coupon_code);
        Button btn_apply_coupon = view.findViewById(R.id.btn_apply_coupon);

        btn_apply_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckoutActivity.this, ""+et_coupon_code.getText().toString(), Toast.LENGTH_SHORT).show();
                coupon_code = et_coupon_code.getText().toString().trim();

                if (coupon_code.equalsIgnoreCase("")) {
                    et_coupon_code.setError("Please Enter Coupon Code");
                    return;
                }

                try {
                    presenter.coupon(CheckoutActivity.this, coupon_code);
                } catch (Exception js) {
                    js.printStackTrace();
                }
            }
        });

        dialog.show();
    }

    @Override
    public void showProgress() {
        dialog  = new Dialog(CheckoutActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(false);
        //...that's the layout i told you will inflate later
        dialog.setContentView(R.layout.progress_dialog);
        dialog.show();
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void handleCoupon(String string) {
        if (string.equalsIgnoreCase("No network")){
            hideProgress();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(CheckoutActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
                }
            });
            //show no network available
        }else{
            try {
                Log.d("responseData", string);
                JSONObject jsonObject = new JSONObject(string);
                JSONArray jsonArray = jsonObject.getJSONArray("login");
                if (jsonArray.length() > 0) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CheckoutActivity.this, "Coupon applied successful", Toast.LENGTH_LONG).show();
                            tv_coupon.setText(coupon_code);
                        }
                    });
                } else {
                    coupon_code = "";
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CheckoutActivity.this, "Coupon Code Not Valid", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CheckoutActivity.this, "Network error.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    @Override
    public void handleAddress(String string) {
        if (string.equalsIgnoreCase("No network")){
            hideProgress();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(CheckoutActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
                }
            });
            //show no network available
        }else{
            try {
                if (string.equalsIgnoreCase("address added")) {
                    paynow();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CheckoutActivity.this, "Some Error Occurred", Toast.LENGTH_LONG).show();
                        }
                    });                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CheckoutActivity.this, "Network error.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    @Override
    public void handlePayment(String string) {
        System.out.println("CheckoutActivity: handlePayment: " + string);
        if (string.equalsIgnoreCase("No network")){
            hideProgress();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(CheckoutActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
                }
            });
            //show no network available
        }else {
            try {
                JSONObject jsonObject = new JSONObject(string);
                String status = jsonObject.getString("status");
                String msg = jsonObject.getString("msg");

                if (status.equalsIgnoreCase("1")){
                    Intent intent = new Intent(CheckoutActivity.this, ReceiptActivity.class);
                    intent.putExtra("status", "success");
                    intent.putExtra("amount", course_cost);
                    intent.putExtra("course", getTitle());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(CheckoutActivity.this, ReceiptActivity.class);
                    intent.putExtra("status", "failure");
                    intent.putExtra("amount", course_cost);
                    intent.putExtra("course", getTitle());
                    startActivity(intent);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

        presenter.payment(CheckoutActivity.this, AppPref.getInstance().getUserId(), courseModel.getId()+"","paid", s, time, coupon_code);
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
        presenter.payment(CheckoutActivity.this, AppPref.getInstance().getUserId(), courseModel.getId()+"","failed", s, time, coupon_code);
    }
}
