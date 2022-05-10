package com.twitter.tweetservice.domain.service;

import com.twitter.tweetservice.gateway.rest.datacontract.TweetDto;

import java.util.List;

public interface TweetService {

    List<TweetDto> listTweetsByUser(String userId);

    void createTweet(TweetDto tweetDto);

}
