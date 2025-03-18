package ru.urfu.sharov.messaging.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public RabbitMQSender(
            RabbitTemplate rabbitTemplate,
            @Value("${spring.rabbitmq.exchange.name}") String exchangeName,
            @Value("${spring.rabbitmq.routing.key}")String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void sendMessage(String message) {
        log.info(message);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
