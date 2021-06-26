package com.strategy.service;

import com.strategy.dto.OrderDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV1 implements IOrderService {

  @Override
  public String handle(OrderDto orderDto) {
    String type = orderDto.getType();
    if (StringUtils.equalsIgnoreCase(type, "1")) {
      return "普通订单";
    } else if (StringUtils.equalsIgnoreCase(type, "2")) {
      return "团购订单";
    } else if (StringUtils.equalsIgnoreCase(type, "3")) {
      return "促销订单";
    }

    return null;
  }
}
