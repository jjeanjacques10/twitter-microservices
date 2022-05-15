package com.twitter.gateway.repository

import com.twitter.domain.entity.Timeline
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TimelineRepository : CrudRepository<Timeline, String>