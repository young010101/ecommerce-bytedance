package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * Insert dish flavors.
     *
     * @param flavors The dish flavors to insert.
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * Get flavors by dish id.
     *
     * @param dishId The dish id.
     * @return List<DishFlavor> The list of flavors.
     */
    @Select("SELECT * FROM dish_flavor WHERE dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
