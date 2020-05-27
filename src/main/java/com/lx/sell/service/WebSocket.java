package com.lx.sell.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Werdio丶
 * @since 2020-05-26 15:24:26
 */
@Slf4j
@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    private Session session;

    private static final CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】建立连接，当前连接数 = {}", webSocketSet.size());
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        log.info("【websocket消息】断开连接，当前连接数 = {}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String msg){
        log.info("【websocket消息】接收到消息，msg = {}", msg);
    }

    public void sendMessage(String msg){

        for (WebSocket webSocket : webSocketSet) {
            try {
                log.info("【websocket消息】广播消息，msg = {}", msg);
                webSocket.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
