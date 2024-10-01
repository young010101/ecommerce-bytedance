package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动填充注解，用于自动填充公共字段，如创建时间、更新时间、创建人、更新人等
 * 默认操作类型为更新操作
 * 如果是插入操作，需要在注解中指定操作类型为插入操作
 * @see OperationType
 * @see com.sky.constant.AutoFillConstant
 * @see com.sky.constant.StatusConstant
 * @see com.sky.constant.PasswordConstant
 * @see com.sky.enumeration.OperationType
 * @see com.sky.aspect.AutoFillAspect
 * @see com.sky.aspect.AutoFillAspect#fill(Object, OperationType)
 * @see com.sky.aspect.AutoFillAspect#fillCreateTime(Object)
 * @see com.sky.aspect.AutoFillAspect#fillUpdateTime(Object)
 * @see com.sky.aspect.AutoFillAspect#fillCreateUser(Object)
 * @see com.sky.aspect.AutoFillAspect#fillUpdateUser(Object)
 * @see com.sky.aspect.AutoFillAspect#fillStatus(Object)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    OperationType value() default OperationType.UPDATE;
}
