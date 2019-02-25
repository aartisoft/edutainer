package com.edutainer.in.workplace.HomeScreen;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edutainer.in.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    ImageView iv_profile;
    TextView tv_sign_out;
    EditText et_name;
    TextView iv_edit_name;
    EditText et_email;
    TextView iv_edit_email;
    TextView tv_mobile;
    TextView tv_transaction;
    TextView tv_question;
    TextView tv_password;

    boolean isEditName = false;
    boolean isEditEmail = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_test, container, false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view){
        Typeface Nunito_SemiBold = Typeface.createFromAsset(getContext().getAssets(),  "fonts/Nunito-SemiBold.ttf");
        iv_profile = view.findViewById(R.id.iv_profile);

        tv_sign_out = view.findViewById(R.id.tv_sign_out);
        tv_sign_out.setTypeface(Nunito_SemiBold);

        et_name = view.findViewById(R.id.et_name);
        et_name.setTypeface(Nunito_SemiBold);

        iv_edit_name = view.findViewById(R.id.iv_edit_name);
        iv_edit_name.setTypeface(Nunito_SemiBold);
        iv_edit_name.setOnClickListener(this);

        et_email = view.findViewById(R.id.et_email);
        et_email.setTypeface(Nunito_SemiBold);

        iv_edit_email = view.findViewById(R.id.iv_edit_email);
        iv_edit_email.setTypeface(Nunito_SemiBold);

        tv_mobile = view.findViewById(R.id.tv_mobile);
        tv_mobile.setTypeface(Nunito_SemiBold);

        tv_transaction = view.findViewById(R.id.tv_transaction);
        tv_transaction.setTypeface(Nunito_SemiBold);

        tv_question = view.findViewById(R.id.tv_question);
        tv_question.setTypeface(Nunito_SemiBold);

        tv_password = view.findViewById(R.id.tv_password);
        tv_password.setTypeface(Nunito_SemiBold);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_edit_name:
                showEditDialog("enter name");
                break;
            case R.id.iv_edit_email:
                break;
        }
    }

    private void showEditDialog(String edit){
        Dialog dialog  = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(true);
        //...that's the layout i told you will inflate later

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_profile, null, false);
        dialog.setContentView(view);

        final EditText et_email = view.findViewById(R.id.et_email);
        et_email.setHint(edit);
        Button btn_submit = view.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), ""+et_email.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private void startActivity(int position, int drawable){

    }
}
