package com.edutainer.in.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edutainer.in.Activity.TransactionsActivity;
import com.edutainer.in.Activity.YourQuestionsActivity;
import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;
import com.edutainer.in.Utility.AppPref;
import com.edutainer.in.Utility.AppUrl;
import com.edutainer.in.Utility.CustomEditText;
import com.edutainer.in.Utility.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProfileFragment extends BaseFragment implements View.OnClickListener, ApiResponse {
    private TextView name;
    private TextView email;
    private TextView edit_profile;
    private TextView change_password;
    private TextView transactions;
    private TextView questions;

    private static final int EDIT_PROFILE = 0;
    private static final int CHANGE_PASSWORD = 1;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    public void initializeView(View view) {
        name = (TextView) view.findViewById(R.id.name);
        email = (TextView) view.findViewById(R.id.email);
        edit_profile = (TextView) view.findViewById(R.id.edit_profile);
        change_password = (TextView) view.findViewById(R.id.change_password);
        transactions = (TextView) view.findViewById(R.id.transactions);
        questions=(TextView) view.findViewById(R.id.questions);


        edit_profile.setOnClickListener(this);
        change_password.setOnClickListener(this);
        transactions.setOnClickListener(this);
        questions.setOnClickListener(this);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user_id=").append(AppPref.getInstance().getUserId());

        String content = stringBuilder.toString();

        ApiConsumer apiConsumer = new ApiConsumer(getActivity(), AppUrl.URL_PROFILE, 0, content, true, "loading ...", new ApiResponse() {
            @Override
            public void getApiResponse(String responseData, int serviceCounter) {
                try {
                    JSONArray jsonArray = new JSONArray(responseData);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String name = jsonObject.getString("name");
                    String email = jsonObject.getString("email");
                    String mobile = jsonObject.getString("mobile");
                    AppPref.getInstance().setUSERNAME(name);
                    AppPref.getInstance().setUSEREMAIL(email);
                    AppPref.getInstance().setUSERMOBILE(mobile);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                name.setText(AppPref.getInstance().getUSERNAME());
                email.setText(AppPref.getInstance().getUSEREMAIL());
            }
        });
        apiConsumer.execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_profile:
                editProfile();
                break;
            case R.id.change_password:
                changePassword();
                break;
            case R.id.transactions:
                sendToActivity(TransactionsActivity.class);
                break;
            case R.id.questions:
                sendToActivity(YourQuestionsActivity.class);
                break;
        }
    }


    private void editProfile() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = LayoutInflater.from(getActivity());
        View v1 = inf.inflate(R.layout.dialog_editprofile, null);
        dialog.setView(v1);
        final Dialog dialog1 = dialog.create();

        final CustomEditText edt_name = (CustomEditText) v1.findViewById(R.id.edt_name);
        final CustomEditText edt_mobile = (CustomEditText) v1.findViewById(R.id.edt_mobile);
        final CustomEditText edt_email = (CustomEditText) v1.findViewById(R.id.edt_email);

        edt_name.setValue(AppPref.getInstance().getUSERNAME());
        edt_email.setValue(AppPref.getInstance().getUSEREMAIL());
        edt_mobile.setValue(AppPref.getInstance().getUSERMOBILE());
        edt_mobile.setEnabled(false);
        edt_email.setEnabled(false);

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetter(source.charAt(i)) && !Character.toString(source.charAt(i)).equals("_") && !Character.toString(source.charAt(i)).equals("-") && !Character.toString(source.charAt(i)).equals(" ")) {
                        return "";
                    }
                }
                return null;
            }
        };

        EditText edt_name_edit = (EditText) edt_name.findViewById(R.id.value);
        edt_name_edit.setFilters(new InputFilter[]{filter});


        Button btn_update_profile = (Button) v1.findViewById(R.id.btn_update_profile);
        ImageView back = (ImageView) v1.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });


        btn_update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt_name.getValue().equalsIgnoreCase("")) {
                    edt_name.setError("Please Enter Name");
                    return;
                }
                if (edt_mobile.getValue().equalsIgnoreCase("")) {
                    edt_mobile.setError("Please Enter Mobile No");
                    return;
                }
                if (edt_mobile.getValue().length() != 10) {
                    edt_mobile.setError("Please Enter Valid Mobile No");
                    return;
                }
                if (edt_email.getValue().equalsIgnoreCase("")) {
                    edt_email.setError("Please Enter Email");
                    return;
                }
                if (!Utility.isValidEmail(edt_email.getValue())) {
                    edt_email.setError("Please Enter Valid Email Id");
                    return;
                }
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("name=").append(edt_name.getValue().toString());
                    stringBuilder.append("&user_id=").append(AppPref.getInstance().getUserId());

                    String content = stringBuilder.toString();

                    ApiConsumer apiConsumer = new ApiConsumer(getActivity(), AppUrl.EDIT_PROFILE, EDIT_PROFILE, content, true, "loading ...", new ApiResponse() {
                        @Override
                        public void getApiResponse(String responseData, int serviceCounter) {
                            try {
                                Log.d("responseData", responseData);
                                JSONObject jsonObject = new JSONObject(responseData);
                                String status = jsonObject.getString("status");
                                String msg = jsonObject.getString("msg");
                                if (status.equalsIgnoreCase("1")) {
                                    AppPref.getInstance().setUSERNAME(edt_name.getValue().toString());
                                    name.setText(AppPref.getInstance().getUSERNAME());
                                }
                                toastMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    apiConsumer.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog1.dismiss();
            }
        });

        dialog1.show();
    }

    private void changePassword() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = LayoutInflater.from(getActivity());
        View v1 = inf.inflate(R.layout.dialog_change_password, null);
        final CustomEditText old_password = (CustomEditText) v1.findViewById(R.id.old_password);
        old_password.setVisibility(View.VISIBLE);
        final CustomEditText new_password = (CustomEditText) v1.findViewById(R.id.new_password);
        final CustomEditText re_new_password = (CustomEditText) v1.findViewById(R.id.re_new_password);

        Button btn_update_password = (Button) v1.findViewById(R.id.btn_update_password);
        ImageView back = (ImageView) v1.findViewById(R.id.back);


        dialog.setView(v1);

        final Dialog dialog1 = dialog.create();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });

        btn_update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string_old_password = old_password.getValue().toString();
                String string_new_password = new_password.getValue().toString();
                String string_re_new_password = re_new_password.getValue().toString();

                if (string_old_password.equalsIgnoreCase("")) {
                    old_password.setError("Please Enter Old Password");
                    return;
                }
                if (string_new_password.equalsIgnoreCase("")) {
                    new_password.setError("Please Enter New Password");
                    return;
                }
                if (string_re_new_password.equalsIgnoreCase("")) {
                    re_new_password.setError("Please Re-Enter Password");
                    return;
                }

                if (!string_new_password.equalsIgnoreCase(string_re_new_password)) {
                    re_new_password.setError("Password does not match");
                    return;
                }

                if (string_new_password.equalsIgnoreCase(string_re_new_password)) {
                    try {
                        dialog1.dismiss();

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("old_password=").append(string_old_password);
                        stringBuilder.append("&new_password=").append(string_new_password);
                        stringBuilder.append("&user_id=").append(AppPref.getInstance().getUserId());

                        String content = stringBuilder.toString();

                        ApiConsumer apiconsumer = new ApiConsumer(getActivity(), AppUrl.CHANGE_PASSWORD, CHANGE_PASSWORD, content, true, "", ProfileFragment.this);
                        apiconsumer.execute();

                    } catch (Exception js) {
                        js.printStackTrace();
                    }

                }
            }
        });
        dialog1.show();
    }


    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        switch (serviceCounter) {
            case EDIT_PROFILE:
//                parseEditProfileResponse(responseData);
                break;
            case CHANGE_PASSWORD:
                parseChangePasswordResponse(responseData);
                break;
        }
    }

    private void parseChangePasswordResponse(String responseData) {
        try {
            Log.d("responseData", responseData);
            JSONObject jsonObject = new JSONObject(responseData);
            String status = jsonObject.getString("status");
            String msg = jsonObject.getString("msg");
            toastMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
