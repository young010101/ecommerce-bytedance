package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    /**
     * Count the number of dishes in the category.
     *
     * @param categoryId The category id.
     * @return The number of dishes.
     */
    @Select("SELECT COUNT(id) FROM dish WHERE category_id = #{categoryId}")
    int countByCategoryId(long categoryId);

    /**
     * Insert a dish.
     *
     * @param dish The dish to insert.
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * page query
     *
     * @param dishPageQueryDTO 分页查询条件
     * @return 菜品列表
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品
     *
     * @param id 菜品id
     * @return 菜品信息
     */
    @Select("SELECT * FROM dish WHERE id = #{id}")
    Dish getById(Long id);

    /**
     * Delete dish by ids.
     *
     * @param ids The dish ids.
     */
    // below are 黑马 version
    // @Delete("delete from dish where id = #{id}")
    // void deleteById(Long id);
    void deleteBatchIds(List<Long> ids);

    /// Update dish.
    /// 更新菜品
    /// @param dish The dish to update.
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    List<Dish> list(Dish categoryId);
}
