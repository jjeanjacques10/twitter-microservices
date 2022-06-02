package com.twitter.tweetservice.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Profile("local")
@Slf4j
@Configuration
public class SQSLocalConfig {

    @Value("${cloud.aws.sqs.url}")
    private String sqsUrl;

    @Value("${cloud.aws.region.static}")
    private String sqsRegion;

    @Bean
    @Primary
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public AmazonSQSAsync amazonSQSAsync() {
        log.info("Config AWS SQS - URL: {} Region: {}", sqsUrl, sqsRegion);
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsUrl, sqsRegion))
                .build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }


}
