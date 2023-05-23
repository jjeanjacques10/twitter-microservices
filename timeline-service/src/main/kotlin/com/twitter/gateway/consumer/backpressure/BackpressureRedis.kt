package com.twitter.gateway.consumer.backpressure

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.twitter.gateway.consumer.backpressure.model.StatusDataContract
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class BackpressureRedis : Backpressure {

    private val URL_ACTUATOR_REDIS = "http://localhost:8083/actuator/health"

    override fun shouldWait(): Boolean {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(URL_ACTUATOR_REDIS)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val status =
                        jacksonObjectMapper().readValue(response.body?.string(), StatusDataContract::class.java)
                    if (status.components?.redis?.status == "DOWN") return true
                }
            }
        } catch (e: Exception) {
            log.warn("Failed to connect to: {}", URL_ACTUATOR_REDIS)
            return false
        }
        log.warn("Failed to connect to: {}", URL_ACTUATOR_REDIS)
        return false
    }

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
    }
}
