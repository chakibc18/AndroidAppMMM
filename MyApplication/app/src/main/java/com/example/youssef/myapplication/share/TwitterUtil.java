package com.example.youssef.myapplication.share;

import android.content.Context;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

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
            TweetComposer.Builder builder = new TweetComposer.Builder(this.context)
                    .text(s);
            //    .image(image);
            builder.show();
    }
}
