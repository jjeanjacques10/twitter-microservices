package com.twitter.domain.service

import com.twitter.domain.entity.Timeline
import java.util.*

interface TimelineService {
    fun findTimelineByUser(userId: UUID): Timeline
}