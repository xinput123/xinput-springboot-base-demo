package com.strategy.handler;

import com.strategy.utils.BeanTool;

import java.util.Map;

/**
 * 处理器上下文，根据类型获取相应的处理器
 */
@SuppressWarnings("unchecked")
public class HandlerContext {

  private Map<String, Class> handlerMap;

  public HandlerContext(Map<String, Class> handlerMap) {
    this.handlerMap = handlerMap;
  }

  public AbstractHandler getInstance(String type) {
    Class clazz = handlerMap.get(type);
    if (clazz == null) {
      throw new IllegalArgumentException("not found handler for type: " + type);
    }
    return (AbstractHandler) BeanTool.getBean(clazz);
  }

}
