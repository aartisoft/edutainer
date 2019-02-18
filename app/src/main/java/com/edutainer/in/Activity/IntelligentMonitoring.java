package com.edutainer.in.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.edutainer.in.R;
import com.edutainer.in.Utility.ApiConsumer;
import com.edutainer.in.Utility.ApiResponse;

public class IntelligentMonitoring extends AppCompatActivity implements ApiResponse {
    Switch first, second, third;

    public void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.activity_intelligent_monitoring);
        setTitle("Home Monitoring");

        first = (Switch) findViewById(R.id.first);
        second = (Switch) findViewById(R.id.second);
        third = (Switch) findViewById(R.id.third);

        String api = "http://blog.edutainer.in/projects/gethome.php";
        ApiConsumer apiConsumer = new ApiConsumer(this, api, 0, "", true, "loading ...", new ApiResponse() {
            @Override
            public void getApiResponse(String responseData, int serviceCounter) {
                try {
                    Log.d("responseData", responseData);
                    String a[] = responseData.split(":");
                    if (a[1].equalsIgnoreCase("ONN")) {
                        first.setChecked(true);
                    } else
                        first.setChecked(false);


                    if (a[2].equalsIgnoreCase("ONN")) {
                        second.setChecked(true);
                    } else
                        second.setChecked(false);

                    if (a[3].equalsIgnoreCase("ONN")) {
                        third.setChecked(true);
                    } else
                        third.setChecked(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        apiConsumer.execute();


        first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    sendtoserver("FIRST", "ONN");
                else
                    sendtoserver("FIRST", "OFF");
            }
        });

        second.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    sendtoserver("SECOND", "ONN");
                else
                    sendtoserver("SECOND", "OFF");
            }
        });

        third.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    sendtoserver("THIRD", "ONN");
                else
                    sendtoserver("THIRD", "OFF");
            }
        });
    }

    private void sendtoserver(String name, String status) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("names=").append(name);
        stringBuilder.append("&status=").append(status);

        String content = stringBuilder.toString();

        Log.d("content", content);

        String api = "http://blog.edutainer.in/projects/sethome.php";

        ApiConsumer apiConsumer = new ApiConsumer(this, api, 0, content, true, "loading ...", this);
        apiConsumer.execute();
    }

    @Override
    public void getApiResponse(String responseData, int serviceCounter) {
        try {
            Log.d("responseData", responseData);
            Toast.makeText(this, responseData, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
