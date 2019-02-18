package com.edutainer.in.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.edutainer.in.R;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.BaseActivity;


public class RefernLearnActivity extends BaseActivity implements View.OnClickListener {


    private ImageButton imgBtnWhatsApp;
    private ImageButton imgBtnFacebook;
    private ImageButton imgBtnMessage;
    private Button btnInviteFriend;
    private TextView tvReferralCode;
    private TextView tc;
    private String referralCode;
    private String shareText = "Technology is evolving, why don't you.Download the Edutainer App and get Technical Education, available on google play store :https://play.google.com/store/apps/details?id=com.vgapps.edutainer";

    private static final String TAG = "ReferNLearn";


    @Override
    public void initialize(Bundle save) {
        imgBtnWhatsApp = (ImageButton) findViewById(R.id.free_ride_imgbtn_whatsapp);
        imgBtnWhatsApp.setOnClickListener(this);
        imgBtnFacebook = (ImageButton) findViewById(R.id.free_ride_imgbtn_facebook);
        imgBtnFacebook.setOnClickListener(this);
        imgBtnMessage = (ImageButton) findViewById(R.id.free_ride_imgbtn_message);
        imgBtnMessage.setOnClickListener(this);
        btnInviteFriend = (Button) findViewById(R.id.free_ride_btn_invite_friend);
        btnInviteFriend.setOnClickListener(this);

        tvReferralCode = (TextView) findViewById(R.id.free_ride_tv_referralCode);
        tc = (TextView) findViewById(R.id.tc);

        tvReferralCode.setText(AppPref.getInstance().getGenCode());
        tc.setOnClickListener(this);
    }

//    private void getContacts() {
//        final ArrayList<ContactModel> contactList = new ArrayList<>();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    contactList.clear();
//                    Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
//                    String _ID = ContactsContract.Contacts._ID;
//                    String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
//                    String PHOTO = ContactsContract.Contacts.PHOTO_THUMBNAIL_URI;
//                    String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
//                    Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//                    String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
//                    String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
//                    String TYPE = ContactsContract.CommonDataKinds.Phone.TYPE;
//                    ContentResolver contentResolver = getContentResolver();
//                    Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
//                    if (cursor != null && cursor.getCount() > 0) {
//                        while (cursor.moveToNext()) {
//                            String phoneNumber = "";
//                            String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
//                            String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
//                            String photo = cursor.getString(cursor.getColumnIndex(PHOTO));
//                            ArrayList<String> checkPhone = new ArrayList<>();
//                            int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
//                            if (hasPhoneNumber > 0) {
//                                Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
//                                while (phoneCursor.moveToNext()) {
//                                    if (phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER)) == null) {
//                                        continue;
//                                    }
//                                    int type = phoneCursor.getInt(phoneCursor.getColumnIndex(TYPE));
//                                    phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
//                                    ContactModel contactModel = new ContactModel(name, phoneNumber);
//                                    checkPhone.add(phoneNumber);
//                                    contactList.add(contactModel);
//                                }
//                                phoneCursor.close();
//                            }
//                            checkPhone.clear();
//                        }
//                    }
//                    JSONArray jsonArray = new JSONArray();
//                    for (int i = 0; i < contactList.size(); i++) {
//                        ContactModel contactModel = contactList.get(i);
//                        JSONObject jsonObject = new JSONObject();
//                        jsonObject.put("name", contactModel.getName());
//                        jsonObject.put("number", contactModel.getNumber());
//                        jsonArray.put(jsonObject);
//                    }
//
//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append("user_id=").append(AppPref.getInstance().getUserId());
//                    stringBuilder.append("&data=").append(jsonArray.toString());
//
//                    String content = stringBuilder.toString();
//
//                    ApiConsumer apiConsumer = new ApiConsumer(RefernLearnActivity.this, AppUrl.CONTACT_URL, 0, content, false, "loading ..", new ApiResponse() {
//                        @Override
//                        public void getApiResponse(String responseData, int serviceCounter) {
//                            AppPref.getInstance().setUSERCONTACT("yes");
//                        }
//                    });
//                    apiConsumer.execute();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//
//        Thread thread = new Thread(runnable);
//        thread.start();
//    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_refer_n_learn;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.free_ride_imgbtn_whatsapp:
                try {
                    sendMessage(shareText, "com.whatsapp");
                } catch (Exception e) {
                    Log.w(TAG, e.toString());
                    toastMessage("Some Error Occurred");
                }
                break;

            case R.id.free_ride_imgbtn_facebook:
                try {
                    sendMessage(shareText, "com.facebook.katana");
                } catch (Exception e) {
                    Log.w(TAG, e.toString());
                    toastMessage("Some Error Occurred");
                }
                break;
            case R.id.tc:
                sendToActivity(AccountDetailsActivity.class);
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
