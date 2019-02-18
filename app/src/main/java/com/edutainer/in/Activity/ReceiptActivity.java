package com.edutainer.in.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.edutainer.in.R;
import com.edutainer.in.Utility.BaseActivity;

import java.util.Date;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class ReceiptActivity extends BaseActivity implements View.OnClickListener {

    private GifImageView gifview;
    private GifDrawable gifFromAssets = null;
    private TextView emotions;
    private TextView txv_status;
    private TextView txv_operator;
    private TextView txv_amount;
    private TextView txv_date;
    private LinearLayout contact_us;
    private LinearLayout recharge_again;


    @Override
    public void initialize(Bundle save) {
        setTitle("Receipt");
        gifview = (GifImageView) findViewById(R.id.gifview);
        emotions=(TextView) findViewById(R.id.emotions);
        txv_status=(TextView) findViewById(R.id.txv_status);
        txv_operator=(TextView) findViewById(R.id.txv_operator);
        txv_amount=(TextView) findViewById(R.id.txv_amount);
        txv_date=(TextView) findViewById(R.id.txv_date);
        contact_us=(LinearLayout) findViewById(R.id.contact_us);
        recharge_again=(LinearLayout) findViewById(R.id.recharge_again);

        try {
            save = getIntent().getExtras();
            if (save != null) {
                String status = save.getString("status");
                String amount=save.getString("amount");
                String course=save.getString("course");

                if (status.equalsIgnoreCase("success")) {
                    gifFromAssets = new GifDrawable(getAssets(), "success_gif.gif");
                    emotions.setText("Hurray! ");
                    txv_status.setText("Your order is Successfull");
                } else {
                    gifFromAssets = new GifDrawable(getAssets(), "failed_gif.gif");
                    emotions.setText("Sorry! ");
                    txv_status.setText("Your order is Unsuccessfull");
                }

                txv_amount.setText(amount);
                txv_operator.setText(course);

                txv_date.setText(new Date().toString());


                gifFromAssets.addAnimationListener(new AnimationListener() {
                    @Override
                    public void onAnimationCompleted(int loopNumber) {
                        gifFromAssets.stop();
                    }
                });

                gifview.setImageDrawable(gifFromAssets);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        contact_us.setOnClickListener(this);
        recharge_again.setOnClickListener(this);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_receipt;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.recharge_again:
                onBackPressed();
                break;
            case R.id.contact_us:
                sendToActivity(FaqActivity.class);
                break;
        }
    }
}
