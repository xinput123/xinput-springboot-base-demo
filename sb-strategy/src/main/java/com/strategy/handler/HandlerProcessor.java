package com.strategy.handler;

import com.google.common.collect.Maps;
import com.strategy.utils.ClassScaner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 扫描指定包中标有@HandlerType的类；
 * 将注解中的类型值作为key，对应的类作为value，保存在Map中；
 * 以上面的map作为构造函数参数，初始化HandlerContext，将其注册到spring容器中
 */
@Component
@SuppressWarnings("unchecked")
public class HandlerProcessor implements BeanFactoryPostProcessor {

  private static final String HANDLER_PACKAGE = "com.strategy.handler.biz";

  /**
   * 扫描@HandlerType，初始化HandlerContext，将其注册到spring容器
   */
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    Map<String, Class> handlerMap = Maps.newHashMapWithExpectedSize(3);
    ClassScaner.scan(HANDLER_PACKAGE, HandlerType.class).forEach(clazz -> {
      // 获取注解中的类型值
      String type = clazz.getAnnotation(HandlerType.class).value();
      // 将注解中的类型值作为key，对应的类作为value，保存在Map中
      handlerMap.put(type, clazz);
    });
    // 初始化HandlerContext，将其注册到spring容器中
    HandlerContext context = new HandlerContext(handlerMap);
    beanFactory.registerSingleton(HandlerContext.class.getName(), context);
  }
}
