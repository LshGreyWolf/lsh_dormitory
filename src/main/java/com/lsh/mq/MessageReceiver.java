package com.lsh.mq;

import com.lsh.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;  
  
@Component
public class MessageReceiver {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME2)
    public void processMessage(String message) {  
        System.out.println("Received: " + message);  
    }  
}