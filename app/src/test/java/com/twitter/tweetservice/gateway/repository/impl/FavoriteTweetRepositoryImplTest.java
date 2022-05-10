package com.twitter.tweetservice.gateway.repository.impl;

import com.twitter.tweetservice.domain.entity.FavoriteTweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(value = SpringExtension.class)
class FavoriteTweetRepositoryImplTest {

    @Mock
    DynamoDbEnhancedClient client;

    @Mock
    DynamoDbTable<FavoriteTweet> table;

    @InjectMocks
    FavoriteTweetRepositoryImpl favoriteTweetRepository;

    @Test
    void findTweetLikes() {
        List<FavoriteTweet> tweetLikes = favoriteTweetRepository.findTweetFavoriteByTweetId("99b9c235-0ca1-4da5-b88f-5b653baa7139");
        assert !tweetLikes.isEmpty();
    }

    @Test
    void likeTweet() {
        FavoriteTweet expectedFavoriteTweet = FavoriteTweet.builder()
                .tweet_id(UUID.randomUUID().toString())
                .user_id(UUID.randomUUID().toString())
                .likedAt(LocalDateTime.now())
                .build();

        FavoriteTweet favoriteTweetResult = favoriteTweetRepository.favoriteTweet(expectedFavoriteTweet);

        assert favoriteTweetResult.getTweet_id() == expectedFavoriteTweet.getTweet_id();
    }
}