package com.twitter.tweetservice.service.impl;

import com.twitter.tweetservice.domain.entity.Tweet;
import com.twitter.tweetservice.gateway.repository.TweetRepository;
import com.twitter.tweetservice.gateway.rest.datacontract.TweetDto;
import com.twitter.tweetservice.service.TweetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<TweetDto> listTweetsByUser(String userId) {
        List<Tweet> tweets = tweetRepository.findByUserId(userId);
        return tweets.stream()
                .map(t -> mapper.map(t, TweetDto.class)).toList();
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
