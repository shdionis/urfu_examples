package ru.urfu.sharov.messaging.example.rabbitmq;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQListener {
    @PostConstruct
    public void onPostConstruct() {
        log.info("init consumer");
    }

    @RabbitListener(queues = "#{@queueName}")
    public void onMessage(String message) {
        log.info("onMessage {}", message);
    }
}
