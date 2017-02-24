package com.example.watanabear.mdblibhandson.model;

import io.realm.RealmObject;
import twitter4j.Status;

/**
 * Created by ryo on 2017/02/24.
 */

public class TweetRealm extends RealmObject {

    private String screenName;
    private String text;
    private String iconUrl;

    public TweetRealm(Status status) {
        setScreenName(status.getUser().getScreenName());
        setText(status.getText());
        setIconUrl(status.getUser().getProfileImageURLHttps());
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
