package com.wss.config;

import com.wss.handler.WsMessageHandler;
import com.xinput.bootbase.interceptor.BaseWebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * @Author: wangdongpeng
 * @Date: 2020-08-11 15:07
 * @Description
 * @Version 1.0
 */
@Configuration
@EnableWebSocket
public class AppWebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WsMessageHandler wsMessageHandler;

    @Autowired
    private BaseWebSocketInterceptor baseWebSocketInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(wsMessageHandler, "/ws")
                .addInterceptors(baseWebSocketInterceptor)
                .setAllowedOrigins("*");
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        // ws 传输数据的时候，数据过大有时候会接收不到，所以在此处设置bufferSize
        container.setMaxTextMessageBufferSize(1024 * 30);
        container.setMaxBinaryMessageBufferSize(1024 * 30);
        container.setMaxSessionIdleTimeout(1000L * 60 * 10);
        return container;
    }

}