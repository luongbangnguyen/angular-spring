package com.example.angular.config.websocket.dev;

import com.example.angular.config.websocket.WebSocketConfigBase;
import com.example.angular.constants.PropertiesKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
@Profile("dev")
public class WebSocketConfig extends WebSocketConfigBase {

    @Value("${"+ PropertiesKey.ADDRESS_ORIGIN_ACCESS+"}")
    private String addressOriginAccess;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket")
                .setAllowedOrigins(addressOriginAccess)
                .withSockJS();
    }

}