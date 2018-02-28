package com.example.youssef.testtwtt;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

public class MainActivity extends AppCompatActivity {
    Parcours parcours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TwitterUtil twitterUtil = new TwitterUtil(this);
        parcours = new Parcours(twitterUtil);
        Parcours.Item item1 = parcours.new Item("first","");

        Parcours.Item item2 = parcours.new Item("second","");

        parcours.add(item1);
        parcours.add(item2);

    }


    public void tweet(View v){
        parcours.publish();
    }
}
