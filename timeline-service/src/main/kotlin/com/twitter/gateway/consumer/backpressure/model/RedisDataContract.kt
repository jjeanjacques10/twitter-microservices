package com.twitter.gateway.consumer.backpressure.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class RedisDataContract(
    val status: String? = null
)
