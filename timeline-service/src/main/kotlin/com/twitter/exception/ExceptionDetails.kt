package com.twitter.exception

data class ExceptionDetails(
    val title: String,
    val status: Int,
    val details: String,
    val timestamp: String,
    val developerMethod: String
)

