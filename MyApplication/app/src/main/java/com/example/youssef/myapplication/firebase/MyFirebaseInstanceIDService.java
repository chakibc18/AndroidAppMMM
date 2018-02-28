package com.example.youssef.myapplication.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by NgocTri on 8/9/2016.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseInsIDService";

    @Override
    public void onTokenRefresh() {
        //Get updated token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //inscription sur le topic events
        FirebaseMessaging.getInstance().subscribeToTopic("events");
        //Log.d(TAG, "New Token: " + refreshedToken);

        //You can save the token into third party server to do anything you want
    }
}
