package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealDishMapper {
    /**
     * Get set meal by id.
     *
     * @param dishId The set meal id.
     * @return The set meal.
     */
    // TODO change to 黑马 version
    @Select("SELECT COUNT(id) FROM setmeal_dish WHERE dish_id = #{dishId}")
    int getByDishId(Long dishId);
}
