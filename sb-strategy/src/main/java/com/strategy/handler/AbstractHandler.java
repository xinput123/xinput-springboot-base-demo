package com.strategy.handler;

import com.strategy.dto.OrderDto;

public abstract class AbstractHandler {
  public abstract String handler(OrderDto dto);
}
