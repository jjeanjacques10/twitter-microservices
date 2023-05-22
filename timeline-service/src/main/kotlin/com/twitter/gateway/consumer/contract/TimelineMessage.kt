package com.twitter.gateway.consumer.contract

import lombok.ToString


@ToString
class TimelineMessage {
    lateinit var tweet: Tweet
    lateinit var userId: String
}