package com.strategy.handler.biz;

import com.strategy.dto.OrderDto;
import com.strategy.handler.AbstractHandler;
import com.strategy.handler.HandlerType;
import org.springframework.stereotype.Component;

/**
 * 促销订单处理
 */
@Component
@HandlerType("2")
public class PromotionHandler extends AbstractHandler {
  @Override
  public String handler(OrderDto dto) {
    return "处理促销订单";
  }
}
