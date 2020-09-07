package com.ws.handler;

import com.xinput.bleach.util.JsonUtils;
import com.xinput.bootbase.consts.HeaderConsts;
import com.xinput.bootbase.handler.AbstractTextWebSocketHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
public class WsMessageHandler extends AbstractTextWebSocketHandler {

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
