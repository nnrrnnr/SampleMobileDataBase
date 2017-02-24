package com.example.watanabear.mdblibhandson.repository;

import com.example.watanabear.mdblibhandson.model.Tweet;

import java.util.Collection;
import java.util.List;

import rx.Single;

/**
 * Created by ryo on 2017/02/24.
 */

public interface TweetRepository {
    List<Tweet> all();
    Single<Result> loadTimeLine();

    public class Result {}
}
