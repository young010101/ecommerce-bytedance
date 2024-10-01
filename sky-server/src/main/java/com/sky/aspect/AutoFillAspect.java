package com.sky.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 自动填充切面
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /*
     * 切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointcut() {
    }

    /*
     * 前置通知, 在通知中填充公共字段
     */
    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("Before auto fill, 开始填充公共字段...");

        // 获取当前被拦截的方法上的数据库操作类型

        // 获取当前被拦截的方法的参数列表--实体类对象

        // 准备赋值的数据

        // 根据不同的操作类型，为对应的属性通过反射赋值
    }


}
