package com.twitter.gateway.consumer

import com.twitter.domain.service.TimelineService
import com.twitter.gateway.consumer.backpressure.Backpressure
import com.twitter.gateway.consumer.contract.TimelineMessage
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy
import io.awspring.cloud.messaging.listener.annotation.SqsListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import java.lang.Exception
import java.lang.Thread.sleep

@Component
class TimelineConsumer(
    val timelineService: TimelineService,
    val backpressure: Backpressure
) {

    private val MESSAGE_GROUP_ID = "timeline-group"

    @SqsListener(value = ["timeline-queue"], deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun receiveMessage(
        timelineMessage: TimelineMessage,
        @Header("timeline-group") senderId: String?
    ) {
        while (backpressure.shouldWait()) {
            println("Waiting to process the message - sorry, there is a problem with the database")
            sleep(5000)
        }
        log.info("message received {} {}", senderId, timelineMessage)
        try {
            timelineService.createTimeline(timelineMessage)
        } catch (e: Exception) {
            log.error("error to update timelines - {}", e.message)
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}