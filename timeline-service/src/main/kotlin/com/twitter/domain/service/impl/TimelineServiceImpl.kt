package com.twitter.domain.service.impl

import com.twitter.domain.entity.Timeline
import com.twitter.domain.service.TimelineService
import com.twitter.exception.TimelineNotFound
import com.twitter.gateway.repository.TimelineRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TimelineServiceImpl(val timelineRepository: TimelineRepository) : TimelineService {

    override fun findTimelineByUser(userId: UUID): Timeline {
        return timelineRepository.findById(userId.toString())
            .orElseThrow { TimelineNotFound("Timeline not found - user [${userId}]") }
    }

}