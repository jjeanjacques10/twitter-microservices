package com.twitter.tweetservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
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
import software.amazon.awssdk.regions.Region;

@Slf4j
@Configuration
@Profile("!local")
public class SQSConfig {

    @Value("${cloud.aws.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.secretKey}")
    private String secretKey;

    @Bean
    @Primary
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public AmazonSQSAsync amazonSQSAsync() {
        log.info("Config AWS SQS - URL: {} Region: {}", accessKey, secretKey); // TODO: Remove it
        return AmazonSQSAsyncClientBuilder.standard()
                .withRegion(Region.US_WEST_1.toString())
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }


}
