package com.twitter.tweetservice.domain.service.impl;

import com.twitter.tweetservice.domain.entity.Tweet;
import com.twitter.tweetservice.domain.service.TweetService;
import com.twitter.tweetservice.exception.TweetNotFound;
import com.twitter.tweetservice.gateway.repository.TweetRepository;
import com.twitter.tweetservice.gateway.rest.datacontract.TweetDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<Tweet> listTweetsByUser(String userId, Pageable pageable) {
        Page<Tweet> tweets = tweetRepository.findByUserId(userId, pageable);
        if (tweets.isEmpty()) {
            throw new TweetNotFound("Tweets not found - user " + userId);
        }
        return tweets;
    }

    @Override
    public void createTweet(TweetDto tweetDto) {
        Tweet tweet = Tweet.builder()
                .id(UUID.randomUUID())
                .content(tweetDto.getContent())
                .userId(tweetDto.getUserId())
                .createdAt(LocalDateTime.now())
                .build();
        tweetRepository.save(tweet);
    }


}
