package com.twitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@SpringBootApplication
@EnableFeignClients
@EnableRedisRepositories
class TimelineServiceApplication

fun main(args: Array<String>) {
	runApplication<TimelineServiceApplication>(*args)
}
