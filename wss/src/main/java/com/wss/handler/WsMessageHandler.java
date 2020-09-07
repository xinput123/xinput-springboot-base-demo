package com.wss.handler;

import com.xinput.bleach.util.JsonUtils;
import com.xinput.bootbase.consts.HeaderConsts;
import com.xinput.bootbase.domain.WssManager;
import com.xinput.bootbase.handler.AbstractTextWebSocketHandler;
import com.xinput.bootbase.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangdongpeng
 * @Date: 2020-08-14 15:17
 * @Description
 * @Version 1.0
 */
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
        String userId = WssManager.getUserId(session);
        Object platform = session.getAttributes().get(JwtUtils.PLATFORM);
        logger.info("assistant server receive ws message:{} from user:{}", message.getPayload(), userId);

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("platform", platform);
        map.put("requestId", session.getAttributes().get(HeaderConsts.REQUEST_ID_KEY));
        map.put("message", message.getPayload());

        session.sendMessage(new TextMessage("服务端收到消息: " + JsonUtils.toJsonString(map)));
    }

}
