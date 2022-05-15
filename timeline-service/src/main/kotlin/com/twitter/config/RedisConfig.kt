package com.twitter.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import java.awt.print.Book


@Configuration
class RedisConfig {
    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory?): RedisTemplate<Long, Book>? {
        val template = RedisTemplate<Long, Book>()
        template.setConnectionFactory(connectionFactory!!)
        return template
    }
}