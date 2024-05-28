package com.lsh.mq;

import com.lsh.config.RabbitMQConfig;
import com.lsh.utils.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageSender {  
  
    @Autowired  
    private RabbitTemplate rabbitTemplate;  
    @GetMapping("/mqTest")
    public Result sendMessage(String EXCHANGE_NAME ,String routingKey ,String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, message);
        return Result.ok();
    }  
}