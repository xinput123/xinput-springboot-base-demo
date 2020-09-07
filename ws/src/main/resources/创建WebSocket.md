#### 1、创建一个maven项目，在pom文件中引入
```$xslt
<parent>
    <groupId>com.github.xinput123</groupId>
    <artifactId>xinput-springboot-parent</artifactId>
    <version>1.0.4</version>
    <relativePath/>
</parent>

<dependency>
    <groupId>com.github.xinput123</groupId>
    <artifactId>xinput-springboot-base</artifactId>
    <version>${xinput-springboot-base.version}</version>
</dependency>
```

#### 2、创建配置文件
- application.yml 公用部分抽取
- application-dev.yml 不同环境不同配置，如数据库，资源
- system.properties 自定义的一些配置文件
- 这里只是一个demo，所以只有dev环境，如果其他环境，则需要添加对应的配置文件

#### 3、创建AppWebSocketConfig类
```$xslt
import com.example.handler.WsMessageHandler;
import com.xinput.bootbase.interceptor.BaseWebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

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
```

#### 4、创建消息处理类 WsMessageHandler
```$xslt
import com.xinput.bleach.util.JsonUtils;
import com.xinput.bootbase.consts.HeaderConsts;
import com.xinput.bootbase.handler.AbstractTextWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
public class WsMessageHandler extends AbstractTextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(WsMessageHandler.class);

    /**
     * 接收消息事件
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("requestId", session.getAttributes().get(HeaderConsts.REQUEST_ID_KEY));
        map.put("message", message.getPayload());

        session.sendMessage(new TextMessage("服务端收到消息: " + JsonUtils.toJsonString(map)));
    }

}
```