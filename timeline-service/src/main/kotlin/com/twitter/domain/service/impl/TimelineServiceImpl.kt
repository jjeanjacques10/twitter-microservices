package com.twitter.domain.service.impl

import com.twitter.domain.entity.Timeline
import com.twitter.domain.service.TimelineService
import com.twitter.exception.TimelineNotFound
import com.twitter.gateway.client.FollowersClient
import com.twitter.gateway.client.datacontract.Followers
import com.twitter.gateway.consumer.contract.TimelineMessage
import com.twitter.gateway.repository.TimelineRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class TimelineServiceImpl(
    private val timelineRepository: TimelineRepository,
    private val followersClient: FollowersClient
) : TimelineService {

    override fun findTimelineByUser(userId: UUID): Timeline {
        return timelineRepository.findById(userId.toString())
            .orElseThrow { TimelineNotFound("Timeline not found - user [${userId}]") }
    }

    override fun createTimeline(timelineMessage: TimelineMessage) {
        var followers: List<Followers> = followersClient.getFollowers(timelineMessage.userId)?.data ?: emptyList()

        if (followers.isEmpty()) {
            log.info("User not have followers")
            return
        }
        followers.forEach { it ->
            val timeline: Timeline

            val foundTimeline = timelineRepository.findById(it.followerId.toString())
            if (foundTimeline.isPresent) {
                timeline = foundTimeline.get()
                timeline.items?.add(timelineMessage.tweet)
            } else {
                timeline = Timeline(userId = it.followerId.toString(), items = mutableListOf(timelineMessage.tweet))
            }
            timeline.items?.sortByDescending { item -> item.createdAt }
            timelineRepository.save(timeline)
            log.info("user's timeline {} updated", it.followerId)
        }
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

}