package com.lsh.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/websocket/clue/{id}")
public class ClueServer extends AbstractWebSocketServer {



}