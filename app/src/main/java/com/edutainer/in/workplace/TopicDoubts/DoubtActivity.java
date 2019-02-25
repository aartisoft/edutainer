package com.edutainer.in.workplace.TopicDoubts;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edutainer.in.R;
import com.edutainer.in.workplace.Helper.AppPref;

import org.json.JSONObject;

public class DoubtActivity extends AppCompatActivity implements DoubtContract.DoubtView {

    EditText et_query;
    Button btn_send;
    Dialog dialog;

    DoubtContract.DoubtPresenter presenter;
    String topic_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doubt);

        Toolbar toolbar = findViewById(R.id.toolbar_doubt);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Ask Question");

        topic_id = getIntent().getStringExtra("TOPIC_ID");
        presenter = new DoubtPresenterImpl(this, new GetDoubtInteractionImpl());

        et_query = findViewById(R.id.et_query);
        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = et_query.getText().toString().trim();
                presenter.Doubt(DoubtActivity.this, question, topic_id, AppPref.getInstance().getUserId());
            }
        });

    }

    @Override
    public void showProgress() {
        dialog  = new Dialog(DoubtActivity.this);
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
    public void handleDoubt(String string) {
        if (string.equalsIgnoreCase("No network")){
            //show no network available
        }else {
            try
            {
                JSONObject jsonObject = new JSONObject(string);
                String status = jsonObject.getString("status");
                String msg = "";
                if (jsonObject.has("msg"))
                    msg = jsonObject.getString("msg");
                if(status.equalsIgnoreCase("1")) {
                    final String finalMsg = msg;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DoubtActivity.this, "" + finalMsg, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
