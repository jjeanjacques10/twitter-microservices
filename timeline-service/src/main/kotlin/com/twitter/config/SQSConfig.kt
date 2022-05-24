package com.twitter.config

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order


@Configuration
class SQSConfig(
    //@Value("\${cloud.aws.sqs.url}") var sqsUrl: String
    //@Value("\${cloud.aws.region}") var sqsRegion: String
) {

    @Bean
    @Primary
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun amazonSQSAsync(): AmazonSQSAsync? {
        return AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:4576", "us-east-1"))
            .build()
    }
}