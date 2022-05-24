package com.twitter.domain.entity

import com.twitter.gateway.consumer.contract.Tweet
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("Timeline")
data class Timeline(
    @Indexed @Id var userId: String,
    var items: MutableList<Tweet>? = mutableListOf()
) {
}