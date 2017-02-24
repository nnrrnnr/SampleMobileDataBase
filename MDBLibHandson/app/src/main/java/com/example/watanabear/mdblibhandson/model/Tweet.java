package com.example.watanabear.mdblibhandson.model;

import twitter4j.Status;

/**
 * Created by ryo on 2017/02/24.
 */

public class Tweet {

    private String screenName;
    private String text;
    private String iconUrl;

    public Tweet(Status status) {
        setScreenName(status.getUser().getScreenName());
        setText(status.getText());
        setIconUrl(status.getUser().getProfileImageURLHttps());
    }

    public Tweet(TweetRealm tweetRealm) {
        setScreenName(tweetRealm.getScreenName());
        setText(tweetRealm.getText());
        setIconUrl(tweetRealm.getIconUrl());
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
