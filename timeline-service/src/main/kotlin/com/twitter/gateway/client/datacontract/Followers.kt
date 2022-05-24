package com.twitter.gateway.client.datacontract

import java.time.LocalDateTime
import java.util.*

data class Followers(
    val followerId: UUID,
    val followedId: UUID,
    val createdAt: LocalDateTime? = null
)
