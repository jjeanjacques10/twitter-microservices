package com.twitter.tweetservice.domain.service;

import com.twitter.tweetservice.domain.entity.Tweet;
import com.twitter.tweetservice.gateway.rest.datacontract.TweetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface TweetService {

    Page<Tweet> listTweetsByUser(String userId, Pageable pageable);

    Tweet getTweet(UUID tweetId);

    Tweet createTweet(TweetDto tweetDto);

    void deleteTweet(UUID tweetId);
}
