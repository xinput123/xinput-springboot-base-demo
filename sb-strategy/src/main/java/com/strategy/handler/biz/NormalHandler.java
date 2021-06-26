package com.strategy.handler.biz;

import com.strategy.dto.OrderDto;
import com.strategy.handler.AbstractHandler;
import com.strategy.handler.HandlerType;
import org.springframework.stereotype.Component;

/**
 * 普通订单处理器
 */
@Component
@HandlerType("1")
public class NormalHandler extends AbstractHandler {
  @Override
  public String handler(OrderDto dto) {
    return "处理普通订单";
  }
}
