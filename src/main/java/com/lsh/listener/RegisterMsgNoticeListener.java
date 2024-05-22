package com.lsh.listener;

import com.lsh.event.RegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2024/04/10
 */
@Slf4j
@Component
public class RegisterMsgNoticeListener implements ApplicationListener<RegisterEvent> {

    @Override
    @Async
    public void onApplicationEvent(RegisterEvent registerEvent) {
        log.info("异步处理监听事件");
    }
}
