package com.twitter.gateway.consumer.backpressure

interface Backpressure {
    fun shouldWait(): Boolean
}