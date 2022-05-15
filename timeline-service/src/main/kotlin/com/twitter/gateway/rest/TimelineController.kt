package com.twitter.gateway.rest

import com.twitter.domain.entity.Timeline
import com.twitter.domain.service.TimelineService
import com.twitter.gateway.rest.datacontract.ResponseDataContract
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/timeline")
class TimelineController {

    @Autowired
    lateinit var timelineService: TimelineService

    @GetMapping("/{userId}")
    fun getTimeline(@PathVariable("userId") userId: UUID): ResponseEntity<ResponseDataContract<Timeline>> {
        var timeline = timelineService.findTimelineByUser(userId)
        return ResponseEntity.ok(ResponseDataContract(data = timeline))
    }

}