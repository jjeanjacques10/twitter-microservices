package com.twitter.tweetservice.domain.service.impl;

import com.twitter.tweetservice.domain.entity.FavoriteTweet;
import com.twitter.tweetservice.domain.enums.FavoriteAction;
import com.twitter.tweetservice.domain.service.FavoriteTweetService;
import com.twitter.tweetservice.exception.FavoritedTweetNotFound;
import com.twitter.tweetservice.gateway.repository.FavoriteTweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class FavoriteTweetServiceImpl implements FavoriteTweetService {

    @Autowired
    FavoriteTweetRepository favoriteTweetRepository;

    @Override
    public FavoriteTweet favoriteTweet(String userId, String tweeId, FavoriteAction action) {
        FavoriteTweet favoriteTweet = FavoriteTweet.builder()
                .tweetId(tweeId)
                .userId(userId)
                .likedAt(LocalDateTime.now())
                .build();
        FavoriteTweet favoritedTweet = favoriteTweetRepository.favoriteTweet(favoriteTweet);
        if (Objects.isNull(favoritedTweet)) {
            log.error("Tweet didn't like - [{}] [{}] ", tweeId, userId);
            throw new FavoritedTweetNotFound("Item didn't create");
        }
        log.info("Tweet liked - [{}] [{}] ", tweeId, userId);
        return favoritedTweet;
    }

    @Override
    public List<FavoriteTweet> listFavoritesByTweet(String tweeId) {
        log.info("find favorite items from [{}]", tweeId);
        return favoriteTweetRepository.findTweetFavoriteByTweetId(tweeId);
    }

    @Override
    public List<FavoriteTweet> listFavoritesByUser(String userId) {
        log.info("find favorite items by [{}] user", userId);
        return favoriteTweetRepository.findTweetFavoriteByUserId(userId);
    }

    @Override
    public void deleteTweet(String tweetId, String userId) {
        if (userId != null)
            favoriteTweetRepository.deleteTweet(tweetId, userId);
        else
            favoriteTweetRepository.deleteTweet(tweetId);
    }

}
