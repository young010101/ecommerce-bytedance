package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {
    /**
     * Count the number of dishes in the category.
     * @param categoryId The category id.
     * @return The number of dishes.
     */
    @Select("SELECT COUNT(id) FROM dish WHERE category_id = #{categoryId}")
    int countByCategoryId(long categoryId);

    /**
     * Insert a dish.
     * @param dish The dish to insert.
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);
}
