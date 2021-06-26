package com.strategy.service;

import com.strategy.dto.OrderDto;
import com.strategy.handler.AbstractHandler;
import com.strategy.handler.HandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV2 implements IOrderService {

  @Autowired
  private HandlerContext handlerContext;

  @Override
  public String handle(OrderDto dto) {
    AbstractHandler handler = handlerContext.getInstance(dto.getType());
    return handler.handle(dto);
  }
}
