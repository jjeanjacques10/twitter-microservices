package com.twitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@SpringBootApplication
@EnableRedisRepositories
class TimelineServiceApplication

fun main(args: Array<String>) {
	runApplication<TimelineServiceApplication>(*args)
}
