package com.twitter.gateway.consumer.contract

data class TimelineMessage(
    var tweet: Tweet,
    var userId: String
) {}
