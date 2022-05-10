package com.twitter.tweetservice.gateway.repository.impl;


import com.twitter.tweetservice.domain.entity.FavoriteTweet;
import com.twitter.tweetservice.gateway.repository.FavoriteTweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Repository
public class FavoriteTweetRepositoryImpl implements FavoriteTweetRepository {

    @Autowired
    private DynamoDbEnhancedClient client;

    private DynamoDbTable<FavoriteTweet> table;

    @PostConstruct
    public void init() {
        table = client.table("tb_favorite_tweets", TableSchema.fromBean(FavoriteTweet.class));
    }

    @Override
    public List<FavoriteTweet> findTweetFavoriteByTweetId(String tweetId) {
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder()
                        .partitionValue(tweetId)
                        .build());

        PageIterable<FavoriteTweet> query = table.query(r -> r.queryConditional(queryConditional));
        return query.items().stream().toList();
    }

    @Override
    public List<FavoriteTweet> findTweetFavoriteByUserId(String userId) {
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder()
                        .sortValue(userId)
                        .build());

        PageIterable<FavoriteTweet> query = table.query(r -> r.queryConditional(queryConditional));
        return query.items().stream().toList();
    }

    @Override
    public FavoriteTweet favoriteTweet(FavoriteTweet favoriteTweet) {
        try {
            table.putItem(favoriteTweet);
            return favoriteTweet;
        } catch (DynamoDbException exception) {
            log.error("Error to favorite tweet {} - {}", favoriteTweet.getTweet_id(), exception.getMessage());
            throw null;
        }
    }
}
