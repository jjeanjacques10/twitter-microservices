package com.twitter.config

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import io.awspring.cloud.messaging.listener.QueueMessageHandler
import io.awspring.cloud.messaging.listener.SimpleMessageListenerContainer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order


@Configuration
class SQSConfig {

    @Value("\${cloud.aws.sqs.url}")
    private var sqsUrl: String? = null

    @Value("\${cloud.aws.region.static}")
    private var sqsRegion: String? = null

    @Bean
    @Primary
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun amazonSQSAsync(): AmazonSQSAsync? {
        log.info("Config AWS SQS - URL: {} Region: {}", sqsUrl, sqsRegion)
        return AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(sqsUrl, sqsRegion))
            .build()
    }

    @Bean
    fun simpleMessageListenerContainer(
        amazonSQSAsync: AmazonSQSAsync?,
        queueMessageHandler: QueueMessageHandler?
    ): SimpleMessageListenerContainer? {
        val simpleMessageListenerContainer = SimpleMessageListenerContainer()
        simpleMessageListenerContainer.setAmazonSqs(amazonSQSAsync)
        simpleMessageListenerContainer.setMessageHandler(queueMessageHandler)
        simpleMessageListenerContainer.setMaxNumberOfMessages(10)
        return simpleMessageListenerContainer
    }

    @Bean
    fun queueMessageHandler(amazonSQSAsync: AmazonSQSAsync?): QueueMessageHandler? {
        val queueMessageHandlerFactory = QueueMessageHandlerFactory()
        queueMessageHandlerFactory.setAmazonSqs(amazonSQSAsync)
        return queueMessageHandlerFactory.createQueueMessageHandler()
    }

    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate? {
        return QueueMessagingTemplate(amazonSQSAsync())
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}