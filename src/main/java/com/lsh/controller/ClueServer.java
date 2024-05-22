package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsh.domain.LogEntity;
import com.lsh.service.LogService;
import com.lsh.service.UserService;
import com.lsh.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2024/05/22
 */
@Component
@ServerEndpoint("/webSocket/clue/{id}")
public class ClueServer {
    private static Map<Long, Session> onlineSessionClientMap = new ConcurrentHashMap<>();



    private static LogService logService;

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    /**
     * 连接创建成功
     *
     * @param id
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("id") Long id, Session session) {
        //保存到在线客户端集合
        onlineSessionClientMap.put(id, session);
        //如果当前用户存在堆积数据 进行推送
        List<LogEntity> list = logService.list(new QueryWrapper<LogEntity>().eq("receive_user_id", id).eq("is_accumulation", 1));

        for (LogEntity log : list) {
            sendAccumulationToOne(id, log.getId(), log.getMessage());
        }
    }
    /**
     * 连接关闭回调
     *
     * @param id
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("id") String id, Session session) {
        //从map集合中移除
        onlineSessionClientMap.remove(id);
    }

    /**
     * 收到消息后的回调
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
    }

    /**
     * 发生错误时的回调
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
    }

    /**
     * 向指定的id发送消息
     *
     * @param id
     * @param message
     */
    public void sendToOne(String id, String message) {
        Session session = onlineSessionClientMap.get(id);
        if (session == null) {
            //如果该id不在线，记录消息 等待用户访问时推送
            LogEntity log = new LogEntity();
            log.setReceiveUserId(Long.parseLong(id));
            log.setIsAccumulation(1);
            log.setMessage(message);
            logService.save(log);
            return;
        }
        if (session != null) session.getAsyncRemote().sendText(message);
    }

    /**
     * 向指定用户发送堆积消息
     *
     * @param id      用户id
     * @param dataId  日志记录id
     * @param message
     */
    public void sendAccumulationToOne(Long id, Long dataId, String message) {
        Session session = onlineSessionClientMap.get(id);
        LogEntity log = new LogEntity();
        log.setId(dataId);
        log.setIsAccumulation(0);
        logService.updateById(log);
        if (session != null){
            RemoteEndpoint.Async asyncRemote = session.getAsyncRemote();
            asyncRemote.sendText(message);
        }
    }

    /**
     * 获取websocket地址
     *
     * @param request
     * @return
     */
    public static String getWebSocketUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        int i = 0, j = 0;
        for (int k = 0; k < url.length(); k++) {
            if (url.charAt(k) == '/') {
                i = k;
                j++;
            }
            if (j == 3) {
                break;
            }
        }
        String ipPort = j < 3 ? url.substring(7, url.length()) : url.substring(7, i);
        String value = ClueServer.class.getAnnotation(ServerEndpoint.class).value();
        return "ws://" + "localhost:8888/dormitory/" + value.replace("{id}", "10");
    }
}
