package com.example.youssef.testones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }


    public void send(View v){
        JSONObject tags = new JSONObject();
        try {
            tags.put("v1","c1");
            tags.put("v2","c2");

        }catch(JSONException e) {
            e.printStackTrace();
        }
        OneSignal.sendTags(tags);
    }
}
