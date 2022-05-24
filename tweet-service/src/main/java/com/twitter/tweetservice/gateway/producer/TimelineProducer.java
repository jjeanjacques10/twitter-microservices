package com.twitter.tweetservice.gateway.producer;

import com.twitter.tweetservice.gateway.producer.contract.TimelineMessage;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class TimelineProducer {

    @Autowired
    private QueueMessagingTemplate messagingTemplate;

    private static final String MESSAGE_GROUP_ID = "timeline-group";

    public void send(TimelineMessage message) {
        messagingTemplate.convertAndSend("timeline-queue", message, Map.of(MESSAGE_GROUP_ID, "tweet-microservice"));
    }

}
