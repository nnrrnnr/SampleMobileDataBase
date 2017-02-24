package com.example.watanabear.mdblibhandson;

import android.app.Application;

import twitter4j.TwitterFactory;

/**
 * Created by ryo on 2017/02/24.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TwitterAuthUtil.init(this);

        TwitterFactory.getSingleton().setOAuthConsumer(
                "[consumerKey]",
                "[consumerSecret]"
        );
    }
}
