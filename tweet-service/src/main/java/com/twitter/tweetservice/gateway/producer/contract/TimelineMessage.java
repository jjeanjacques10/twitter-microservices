package com.twitter.tweetservice.gateway.producer.contract;

import com.twitter.tweetservice.domain.entity.Tweet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimelineMessage {
    Tweet tweet;
    String userId;
}
