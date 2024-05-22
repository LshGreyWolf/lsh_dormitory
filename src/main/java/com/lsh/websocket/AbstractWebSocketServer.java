package com.lsh.websocket;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lsh.domain.LogEntity;
import com.lsh.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Slf4j
public abstract class AbstractWebSocketServer {

    private static LogService logService;

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    //存放所有在线的客户端 key为类名称 区分不同服务端
    private static Map<String, Map<String, List<Session>>> onlineSessionClientMap = new ConcurrentHashMap<>();

    /**
     * 创建连接成功
     *
     * @param id
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) {
        String serverName = getClass().getSimpleName();
        List<Session> list = new ArrayList<>();
        list.add(session);

        synchronized (onlineSessionClientMap) {
            if (onlineSessionClientMap.containsKey(serverName)) {
                Map<String, List<Session>> map = onlineSessionClientMap.get(serverName);
                if (map.containsKey(id)) {
                    list.addAll(map.get(id));
                }
                map.put(id, list);
                onlineSessionClientMap.put(serverName, map);
            } else {
                Map<String, List<Session>> map = new ConcurrentHashMap();
                map.put(id, list);
                onlineSessionClientMap.put(serverName, map);
            }
        }

        //查询堆积消息 并发送
        List<LogEntity> msgList = logService.list(new QueryWrapper<LogEntity>().eq("receive_user_id", id).eq("is_accumulation", 1));
        if (msgList.size() > 0) {
            for (LogEntity entity : msgList) {
                sendAccumulationToOne(Long.valueOf(id), entity.getId(), entity.getMessage());
            }
        }
    }

    /**
     * 关闭连接
     *
     * @param id
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("id") String id, Session session) {
        String serverName = getClass().getSimpleName();

        synchronized (onlineSessionClientMap) {
            if (onlineSessionClientMap.containsKey(serverName)) {
                Map<String, List<Session>> map = onlineSessionClientMap.get(serverName);
                List<Session> sessions = map.get(id);
                sessions.remove(session);
                if (sessions.size() == 0) {
                    map.remove(id);
                } else {
                    map.put(id, sessions);
                }
                onlineSessionClientMap.put(serverName, map);
            }
        }
    }

    /**
     * 收到消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
    }

    /**
     * 发生错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("WebSocket发生错误：" + error.getMessage());
    }

    /**
     * 指定id发送消息
     *
     * @param id
     * @param message
     */
    public void sendToOne(String id, Object message) {
        sendToOne(id, message, getStringMessage());
    }

    /**
     * 指定id发送消息
     *
     * @param id       连接id
     * @param message  具体的消息：此处封装为Object 实际可能是一个字符串 也可能是一个对象
     * @param function lambda表达式：用于获取需要保存到数据的预警明细信息。如果传null 默认将message.toString进行保存
     */
    public void sendToOne(String id, Object message, Function function) {
        String serverName = getClass().getSimpleName();

        Map<String, List<Session>> map = onlineSessionClientMap.get(serverName);
        List<Session> sessions = map.get(id);

        //保存消息
        LogEntity entity = new LogEntity();
        entity.setReceiveUserId(Long.valueOf(id));
        entity.setMessage(function.apply(message).toString());
        entity.setIsAccumulation(sessions != null ? 0 : 1);
        logService.save(entity);
        //用户在线，直接推送消息
        if (sessions != null) {
            for (Session session : sessions) {
                if (session != null) session.getAsyncRemote().sendText(JSONObject.toJSONString(message));
            }
        }
    }

    /**
     * 推送堆积消息
     *
     * @param id
     * @param dataId
     * @param message
     */
    private void sendAccumulationToOne(Long id, Long dataId, Object message) {
        String serverName = getClass().getSimpleName();

        Map<String, List<Session>> map = onlineSessionClientMap.get(serverName);
        List<Session> sessions = map.get(id);

        //修改状态
        logService.update(new UpdateWrapper<LogEntity>().eq("id", dataId).set("is_accumulation", 0));
        //用户在线，直接推送消息
        for (Session session : sessions) {
            if (session != null) session.getAsyncRemote().sendText(JSONObject.toJSONString(message));
        }
    }

    /**
     * 提供一个函数式接口，用来将message对象转换为能够保存到数据库的字符串 默认返回null
     *
     * @return
     */
    public Function getStringMessage() {
        return o -> o.toString();
    }

    /**
     * 获取webSocket连接地址
     *
     * @return
     */
    public String getWebSocketUrl() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
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
        String value = getClass().getAnnotation(ServerEndpoint.class).value();
        return "ws://" + "localhost:8888/dormitory/" + value.replace("{id}", "10");
    }
}
