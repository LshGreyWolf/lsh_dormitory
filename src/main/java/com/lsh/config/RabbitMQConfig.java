package com.lsh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@Slf4j
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "topic.exchange";
    public static final String EXCHANGE_NAME1 = "amq.direct";
    public static final String QUEUE_NAME = "test";
    public static final String QUEUE_NAME2 = "test2";
    public static final String ROUTING_KEY = "my_routing_key1";
    public static final String ROUTING_KEY2 = "my_routing_key2";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public DirectExchange exchange2() {
        return new DirectExchange(EXCHANGE_NAME1);
    }
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }
    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_NAME2);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        log.error("TopicExchange{}",exchange);
        log.error("queue{}",queue);
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
    @Bean
    public Binding binding2(Queue queue2, DirectExchange exchange) {
        log.error("DirectExchange{}",exchange);
        log.error("queue2{}",queue2);
        return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY2);
    }
}
