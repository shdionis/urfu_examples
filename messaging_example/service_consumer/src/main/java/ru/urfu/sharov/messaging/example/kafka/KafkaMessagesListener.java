package ru.urfu.sharov.messaging.example.kafka;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessagesListener {
    @PostConstruct
    public void onPostConstruct() {
        log.info("init consumer");
    }

    @KafkaListener(topics = "#{@topicName}", groupId = "#{@serverGroupId}")
    public void onMessage(String data) {
        log.info("onMessage {}", data);
    }
}
