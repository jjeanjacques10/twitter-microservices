package com.twitter.tweetservice.domain.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.LocalDateTime;

@Data
@Builder
@DynamoDbBean
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteTweet {

    private String tweet_id;

    private String user_id;

    @DynamoDBAttribute(attributeName = "like_at")
    private LocalDateTime likedAt;

    @DynamoDbPartitionKey()
    public String getTweet_id() {
        return tweet_id;
    }

    @DynamoDbSortKey()
    public String getUser_id() {
        return user_id;
    }

}
