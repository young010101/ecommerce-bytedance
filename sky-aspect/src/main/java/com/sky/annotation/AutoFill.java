package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动填充注解，用于自动填充公共字段，如创建时间、更新时间、创建人、更新人等.
 * The default operation type is update operation.
 * If the operation type is insert operation, it needs to be specified in the
 * annotation.
 *
 * @see OperationType
 * @see com.sky.constant.AutoFillConstant
 * @see com.sky.constant.StatusConstant
 * @see com.sky.constant.PasswordConstant
 * @see com.sky.enumeration.OperationType
 * @see com.sky.aspect.AutoFillAspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    /**
     * Returns the operation type for auto-filling.
     *
     * @return the OperationType enum value
     */
    OperationType value();
}
