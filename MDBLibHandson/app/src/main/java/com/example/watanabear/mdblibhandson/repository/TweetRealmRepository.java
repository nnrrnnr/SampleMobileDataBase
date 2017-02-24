package com.example.watanabear.mdblibhandson.repository;

import com.annimon.stream.Stream;
import com.example.watanabear.mdblibhandson.model.Tweet;
import com.example.watanabear.mdblibhandson.model.TweetRealm;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Created by ryo on 2017/02/24.
 */

public class TweetRealmRepository implements TweetRepository {
    @Override
    public List<Tweet> all() {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<TweetRealm> tweets = realm.where(TweetRealm.class).findAll();
        List<Tweet> c = new ArrayList<>();
        Stream.of(tweets).forEach(tweetRealm -> c.add(new Tweet(tweetRealm)));
        return c;
    }

    @Override
    public Single<Result> loadTimeLine() {
        return Single.create((Single.OnSubscribe<Result>)singleSubscriber -> {
            final Twitter twitter = TwitterFactory.getSingleton();
            final ResponseList<Status> homeTimeline;
            try {
                homeTimeline = twitter.getHomeTimeline();
                final Realm realm = Realm.getDefaultInstance();
                try {
                    realm.executeTransaction(realm1 -> {
                        for (Status status : homeTimeline) {
                            final TweetRealm tweet = new TweetRealm(status);
                            realm1.copyToRealmOrUpdate(tweet);
                        }
                    });
                } finally {
                    realm.close();
                }

            } catch (TwitterException e) {
                singleSubscriber.onError(new Throwable());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }
}
