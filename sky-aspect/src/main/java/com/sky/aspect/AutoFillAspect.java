package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import com.sky.exception.AutoFillException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import static com.sky.constant.AutoFillConstant.SET_CREATE_TIME;
import static com.sky.constant.AutoFillConstant.SET_UPDATE_TIME;
import static com.sky.constant.AutoFillConstant.SET_CREATE_USER;
import static com.sky.constant.AutoFillConstant.SET_UPDATE_USER;


/**
 * 自动填充切面.
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 定义切入点，拦截标记了AutoFill注解的mapper方法.
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && "
            + "@annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointcut() {
    }

    /**
     * 前置通知，在执行mapper方法前自动填充公共字段.
     *
     * @param joinPoint 连接点
     */
    @Before("autoFillPointcut()")
    public void autoFill(final JoinPoint joinPoint) {
        log.info("Before auto fill, 开始填充公共字段...");

        // 获取当前被拦截的方法上的数据库操作类型
        // 注意是aspectj.lang.reflect.MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();


        // 获取当前被拦截的方法的参数列表--实体类对象, 约定第一个参数为实体类对象
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            throw new AutoFillException("自动填充失败：方法必须至少包含一个实体参数");
        }
        Object entity = args[0];

        // 准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long userId = BaseContext.getCurrentId();

        // 根据操作类型，为对应的属性通过反射赋值
        if (operationType == OperationType.INSERT) {
            // 将实体对象附上创建时间、更新时间、创建人、更新人
            try {
                entity.getClass()
                        .getDeclaredMethod(SET_CREATE_TIME, LocalDateTime.class)
                        .invoke(entity, now);
                entity.getClass()
                        .getDeclaredMethod(SET_UPDATE_TIME, LocalDateTime.class)
                        .invoke(entity, now);
                entity.getClass()
                        .getDeclaredMethod(SET_CREATE_USER, Long.class)
                        .invoke(entity, userId);
                entity.getClass()
                        .getDeclaredMethod(SET_UPDATE_USER, Long.class)
                        .invoke(entity, userId);
            } catch (IllegalAccessException | InvocationTargetException
                     | NoSuchMethodException e) {
                log.error("自动填充字段时发生错误: ", e);
                throw new AutoFillException("自动填充字段失败", e);
            }
        } else if (operationType == OperationType.UPDATE) {
            try {
                entity.getClass()
                        .getDeclaredMethod(SET_UPDATE_TIME, LocalDateTime.class)
                        .invoke(entity, now);
                entity.getClass()
                        .getDeclaredMethod(SET_UPDATE_USER, Long.class)
                        .invoke(entity, userId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("不支持的操作类型");
        }
    }
}
