package com.twitter.tweetservice.gateway.repository;

import com.twitter.tweetservice.domain.entity.FavoriteTweet;

import java.util.List;

public interface FavoriteTweetRepository {

    List<FavoriteTweet> findTweetFavoriteByTweetId(String tweetId);

    List<FavoriteTweet> findTweetFavoriteByUserId(String userId);

    FavoriteTweet favoriteTweet(FavoriteTweet favoriteTweet);

}
