package com.strategy.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取bean工具类
 */
public class BeanTool implements ApplicationContextAware {

  private static ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (context == null) {
      context = applicationContext;
    }
  }

  public static Object getBean(String name) {
    return context.getBean(name);
  }

  public static <T> T getBean(Class<T> clazz) {
    return context.getBean(clazz);
  }
}
