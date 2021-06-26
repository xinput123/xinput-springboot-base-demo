package com.strategy.service;

import com.strategy.dto.OrderDto;

public interface IOrderService {

  /**
   * 根据订单的不同类型作出不同的处理
   */
  String handle(OrderDto orderDto);
}
