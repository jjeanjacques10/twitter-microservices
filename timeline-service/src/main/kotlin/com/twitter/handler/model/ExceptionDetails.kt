package com.twitter.handler.model

data class ExceptionDetails(
    val title: String,
    val status: Int,
    val details: String,
    val timestamp: String,
    val developerMethod: String
)

