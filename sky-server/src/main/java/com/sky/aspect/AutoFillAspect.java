package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import static com.sky.constant.AutoFillConstant.*;

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
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();  // 注意是aspectj.lang.reflect.MethodSignature
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();


        // 获取当前被拦截的方法的参数列表--实体类对象, 约定第一个参数为实体类对象
        Object[] args = joinPoint.getArgs();

        // TODO: 如果参数为空，直接返回. 有必要么？
        if (args == null || args.length == 0) {
            return;
        }

        Object entity = args[0];


        // 准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long userId = BaseContext.getCurrentId();


        // 根据不同的操作类型，为对应的属性通过反射赋值
        if (operationType == OperationType.INSERT) {
            // 将实体对象附上创建时间、更新时间、创建人、更新人
            try {
                entity.getClass().getDeclaredMethod(SET_CREATE_TIME, LocalDateTime.class).invoke(entity, now);
                entity.getClass().getDeclaredMethod(SET_UPDATE_TIME, LocalDateTime.class).invoke(entity, now);
                entity.getClass().getDeclaredMethod(SET_CREATE_USER, Long.class).invoke(entity, userId);
                entity.getClass().getDeclaredMethod(SET_UPDATE_USER, Long.class).invoke(entity, userId);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
                // 课程用 e.printStackTrace(); 但是 copilot 建议 throw new RuntimeException(e); why?
                // 因为这里是一个切面，如果出现异常，应该抛出异常，而不是打印异常信息
                // TODO: 使用我们定义的异常，而不是直接抛出 Exception
            }
        } else if (operationType == OperationType.UPDATE) {
            // 将实体对象附上更新时间、更新人
            try {
                entity.getClass().getDeclaredMethod(SET_UPDATE_TIME, LocalDateTime.class).invoke(entity, now);
                entity.getClass().getDeclaredMethod(SET_UPDATE_USER, Long.class).invoke(entity, userId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("不支持的操作类型");
        }
    }
}
