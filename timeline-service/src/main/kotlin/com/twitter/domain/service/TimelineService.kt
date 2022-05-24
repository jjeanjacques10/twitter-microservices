package com.twitter.domain.service

import com.twitter.domain.entity.Timeline
import com.twitter.gateway.consumer.contract.TimelineMessage
import java.util.*

interface TimelineService {
    fun findTimelineByUser(userId: UUID): Timeline
    fun createTimeline(timelineMessage: TimelineMessage)
}