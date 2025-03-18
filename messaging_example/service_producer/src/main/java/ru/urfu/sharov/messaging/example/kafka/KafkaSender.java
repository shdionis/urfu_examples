package ru.urfu.sharov.messaging.example.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaSender {
    @Autowired
    private final String topicName;
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String data) {
        log.info(data);
        kafkaTemplate.send(topicName, data);
    }
}
