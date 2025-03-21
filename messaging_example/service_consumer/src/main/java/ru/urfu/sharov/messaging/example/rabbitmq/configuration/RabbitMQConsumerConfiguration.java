package ru.urfu.sharov.messaging.example.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConsumerConfiguration {
    @Value("${spring.rabbitmq.queue.name}")
    private String queueName;
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    public Queue myQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange(exchangeName, false, false);
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
    }

    @Bean
    public String queueName() {
        return queueName;
    }
}
