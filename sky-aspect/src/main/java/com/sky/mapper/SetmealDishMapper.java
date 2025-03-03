package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * Get setmeal ids by dish ids.
     *
     * @param dishIds The dish ids.
     * @return List<Long> The list of setmeal ids.
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    /**
     * 批量插入套餐菜品关系.
     *
     * @param setmealDishes 套餐菜品关系列表
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐ID查询套餐菜品关系.
     *
     * @param id 套餐ID
     * @return 套餐菜品关系列表
     */
    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getBySetmealId(Long id);

    /**
     * 根据套餐ID删除套餐菜品关系.
     *
     * @param setmealId 套餐ID
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    /**
     * 批量删除套餐菜品关系.
     *
     * @param setmealIds 套餐ID列表
     */
    void deleteBatchBySetmealIds(List<Long> setmealIds);
}
