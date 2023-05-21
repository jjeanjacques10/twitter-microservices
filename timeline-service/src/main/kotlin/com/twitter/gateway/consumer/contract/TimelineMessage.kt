package com.twitter.gateway.consumer.contract

import lombok.ToString

@ToString
data class TimelineMessage(var tweet: Tweet, var userId: String)