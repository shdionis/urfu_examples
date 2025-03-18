package ru.urfu.sharov.messaging.example.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.urfu.sharov.messaging.example.kafka.KafkaSender;
import ru.urfu.sharov.messaging.example.rabbitmq.RabbitMQSender;

@Component
@RestController("/")
@Slf4j
public class MessagingRestController {
    private final KafkaSender kafkaSender;
    private final RabbitMQSender rabbitMQSender;

    public MessagingRestController(KafkaSender kafkaSender, RabbitMQSender rabbitMQSender) {
        this.kafkaSender = kafkaSender;
        this.rabbitMQSender = rabbitMQSender;
    }

    @GetMapping("kafka")
    public void sendMessageToKafka(@RequestParam("data") String data) {
        log.info("sendMessage to Kafka{}", data);
        kafkaSender.sendMessage(data);
    }

    @GetMapping("rabbit")
    public void sendMessageToRabbit(@RequestParam("data") String data) {
        log.info("sendMessage to RabbitMQ {}", data);
        rabbitMQSender.sendMessage(data);
    }
}
