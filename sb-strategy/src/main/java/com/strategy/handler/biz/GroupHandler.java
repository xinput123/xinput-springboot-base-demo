package com.strategy.handler.biz;

import com.strategy.dto.OrderDto;
import com.strategy.handler.AbstractHandler;
import com.strategy.handler.HandlerType;
import org.springframework.stereotype.Component;

/**
 * 普通订单处理器
 */
@Component
@HandlerType("3")
public class GroupHandler extends AbstractHandler {
  @Override
  public String handler(OrderDto dto) {
    return "处理团购订单";
  }
}
