package com.lsh.listener;

import com.lsh.websocket.ClueServer;
import com.lsh.event.ClueEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ClueEventListener {

    @Autowired
    private ClueServer webSocketServer;

    /**
     * 监听器处理消息
     *
     * @param event
     */
    @Async
    @EventListener
    public void onEventListener(ClueEvent event) {
        //这里的id就写死成user2的id
        webSocketServer.sendToOne("10", event.getMessage());
    }
}
