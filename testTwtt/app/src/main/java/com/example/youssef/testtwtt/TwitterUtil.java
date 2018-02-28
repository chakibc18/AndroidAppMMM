package com.example.youssef.testtwtt;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by youssef on 28/12/17.
 */

public class TwitterUtil {
    private Context context;
    public Boolean initialized = false;

    public TwitterUtil(Context context) {
        this.context = context;
        initialize();
    }


    private void initialize(){
        if (!initialized) {
            TwitterConfig config = new TwitterConfig.Builder(this.context)
                    .logger(new DefaultLogger(Log.DEBUG))
                    .twitterAuthConfig(new TwitterAuthConfig("AoFVL2Q8dMRBybnHr8bJ0yVXF", "qkTfU27H2mD7XloNmMIYATY0ut9bp4QiEV3CwLX6pflpgiX6n6"))
                    .debug(true)
                    .build();
            Twitter.initialize(config);
            initialized = true;
        }
    }

    public void post(String s){
        String url1 = "http://www.kooora.com/";
        try {
            URL url = new URL("https://www.google.fr/search?client=ubuntu&channel=fs&q=TweetComposer&ie=utf-8&oe=utf-8&gfe_rd=cr&dcr=0&ei=McmJWp7cLqXL8geL45f4CA");
            //Uri image = Uri.parse("https://www.w3schools.com/w3css/img_fjords.jpg");
            TweetComposer.Builder builder = new TweetComposer.Builder(this.context)
                    .text(s+" "+url1);
            //    .image(image);
            builder.show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
