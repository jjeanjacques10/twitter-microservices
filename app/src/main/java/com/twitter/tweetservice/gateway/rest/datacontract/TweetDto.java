package com.twitter.tweetservice.gateway.rest.datacontract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetDto {
    private String id;
    private String userId;
    private LocalDateTime createdAt;
    private String content;
}
