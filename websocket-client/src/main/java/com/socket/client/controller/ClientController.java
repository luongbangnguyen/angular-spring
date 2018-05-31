package com.socket.client.controller;

import com.socket.client.model.Greeting;
import com.socket.client.model.Message;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    @GetMapping("getMessage")
    public Message getMessage() {
        WebSocketClient webSocketClient = new StandardWebSocketClient();

        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(webSocketClient));

        SockJsClient sockJsClient = new SockJsClient(transports);

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        stompClient.connect("ws://localhost:8080/gs-guide-websocket", new StompSessionHandlerAdapter(){
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                Greeting greeting = (Greeting) payload;
                System.out.println(greeting.getContent());
            }

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Greeting.class;
            }

            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                session.subscribe("/queue/greetings/1234", this);
                session.send("/app/hello/1234", null);
            }
        });

       return new Message();
    }
}
