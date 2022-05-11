package com.twitter.tweetservice.domain.entity;


import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;

@Data
@Builder
@DynamoDbBean
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteTweet {

    @Getter(onMethod_ = {@DynamoDbPartitionKey, @DynamoDbAttribute("tweet_id")})
    private String tweetId;

    @Getter(onMethod_ = {@DynamoDbSortKey, @DynamoDbAttribute("user_id"), @DynamoDbSecondaryPartitionKey(indexNames = "user_index")})
    private String userId;

    @Getter(onMethod_ = {@DynamoDbAttribute("like_at")})
    private LocalDateTime likedAt;

}
