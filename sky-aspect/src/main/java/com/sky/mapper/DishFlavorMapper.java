package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * Insert dish flavors.
     *
     * @param flavors The dish flavors to insert.
     */
    void insertBatch(@Param("flavors") List<DishFlavor> flavors);

    /**
     * Get flavors by dish id.
     *
     * @param dishId The dish id.
     * @return List<DishFlavor> The list of flavors.
     */
    @Select("SELECT * FROM dish_flavor WHERE dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);

    /**
     * Delete flavors by dish ids.
     *
     * @param dishId The dish ids.
     */
    @Delete("DELETE FROM dish_flavor WHERE dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 批量删除菜品口味关系.
     *
     * @param dishIds 菜品ID列表
     */
    void deleteBatchByDishIds(List<Long> dishIds);
}
