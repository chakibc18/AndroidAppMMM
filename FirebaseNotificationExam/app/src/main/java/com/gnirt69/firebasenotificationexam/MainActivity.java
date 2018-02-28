package com.gnirt69.firebasenotificationexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    SendNotification sendNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnShowToken = (Button) findViewById(R.id.button_show_token);

        sendNotification = new SendNotification(this);

        btnShowToken.setOnClickListener(new View.OnClickListener() {
/*            @Override
            public void onClick(View v) {
                //Get the token
                FirebaseApp.initializeApp(v.getContext());
//                String registrationToken = FirebaseInstanceId.getInstance().getToken();
               // Log.d(TAG, "Token: " + registrationToken);
                FirebaseMessaging.getInstance().subscribeToTopic("all");

            }*/

            public void onClick(View view) {
                sendNotification.show();
            }
        });
    }
}

