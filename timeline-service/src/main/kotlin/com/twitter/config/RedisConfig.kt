package com.twitter.config

import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration.LettuceClientConfigurationBuilder
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

    @Bean
    fun lettuceClientConfigurationBuilderCustomizer(): LettuceClientConfigurationBuilderCustomizer? {
        return LettuceClientConfigurationBuilderCustomizer { clientConfigurationBuilder: LettuceClientConfigurationBuilder ->
            if (clientConfigurationBuilder.build().isUseSsl) {
                clientConfigurationBuilder.useSsl().disablePeerVerification()
            }
        }
    }

}