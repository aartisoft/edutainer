package com.edutainer.in.workplace.Drawer.Share;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.edutainer.in.R;
import com.edutainer.in.workplace.Drawer.NavDrawerActivity;
import com.edutainer.in.workplace.Helper.AppPref;

public class FragmentShare extends Fragment implements View.OnClickListener {
    private ImageButton imgBtnWhatsApp;
    private ImageButton imgBtnFacebook;
    private ImageButton imgBtnMessage;
    private Button btnInviteFriend;
    private TextView tvReferralCode;
    private TextView tc;
    private String referralCode;
    private String shareText = "Technology is evolving, why don't you.Download the Edutainer App and get Technical Education, available on google play store :https://play.google.com/store/apps/details?id=com.vgapps.edutainer";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_refer_n_learn, container, false);
        imgBtnWhatsApp = (ImageButton) view.findViewById(R.id.free_ride_imgbtn_whatsapp);
        imgBtnWhatsApp.setOnClickListener(this);
        imgBtnFacebook = (ImageButton) view.findViewById(R.id.free_ride_imgbtn_facebook);
        imgBtnFacebook.setOnClickListener(this);
        imgBtnMessage = (ImageButton) view.findViewById(R.id.free_ride_imgbtn_message);
        imgBtnMessage.setOnClickListener(this);
        btnInviteFriend = (Button) view.findViewById(R.id.free_ride_btn_invite_friend);
        btnInviteFriend.setOnClickListener(this);

        tvReferralCode = (TextView) view.findViewById(R.id.free_ride_tv_referralCode);
        tc = (TextView) view.findViewById(R.id.tc);

        tvReferralCode.setText(AppPref.getInstance().getGenCode());
        tc.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.free_ride_imgbtn_whatsapp:
                try {
                    sendMessage(shareText, "com.whatsapp");
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.free_ride_imgbtn_facebook:
                try {
                    sendMessage(shareText, "com.facebook.katana");
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tc:
                startActivity(new Intent(getContext(), NavDrawerActivity.class)
                        .putExtra("FRAGMENT", "TERMS")
                );
                break;
            case R.id.free_ride_imgbtn_message:
                Uri uri = Uri.parse("smsto:");
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
                smsIntent.putExtra("sms_body", shareText);
                startActivity(smsIntent);
                break;

            case R.id.free_ride_btn_invite_friend:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Edutainer");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(sharingIntent, "share"));
                break;
        }
    }

    private void sendMessage(String message, String packageName) throws Exception {
        Intent sendIntent = new Intent();
        sendIntent.setPackage(packageName);
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
