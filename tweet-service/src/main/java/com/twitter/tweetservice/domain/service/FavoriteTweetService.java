package com.twitter.tweetservice.domain.service;

import com.twitter.tweetservice.domain.entity.FavoriteTweet;
import com.twitter.tweetservice.domain.enums.FavoriteAction;

import java.util.List;

public interface FavoriteTweetService {

    FavoriteTweet favoriteTweet(String userId, String tweetId, FavoriteAction action);

    List<FavoriteTweet> listFavoritesByTweet(String tweetId);

    List<FavoriteTweet> listFavoritesByUser(String userId);

    void deleteTweet(String tweetId, String userId);
}
