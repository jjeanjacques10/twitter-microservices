package com.twitter.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("Timeline")
data class Timeline(
    @Indexed @Id val userId: String,
    val items: List<String>
)