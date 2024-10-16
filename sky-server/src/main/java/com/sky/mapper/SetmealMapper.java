package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for managing setmeal-related database operations.
 */
@Mapper
public interface SetmealMapper {

    /**
     * 根据分类 ID 统计套餐数量
     *
     * @param categoryId 分类 ID
     * @return 套餐数量
     */
    @Select("SELECT COUNT(id) FROM setmeal WHERE category_id = #{categoryId}")
    int countByCategoryId(long categoryId);

    /**
     * 新增套餐
     *
     * @param setmeal 新增的套餐对象
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 分页查询套餐列表
     *
     * @param setmealPageQueryDTO 分页查询条件
     * @return 套餐列表
     */
    Page<Setmeal> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据 ID 查询套餐详细信息
     *
     * @param id 套餐 ID
     * @return 套餐信息
     */
    @Select("SELECT * FROM setmeal WHERE id = #{id}")
    Setmeal getById(Long id);

    /**
     * 批量删除套餐
     *
     * @param ids 套餐 ID 列表
     */
    void deleteBatchIds(List<Long> ids);

    /**
     * 更新套餐信息
     *
     * @param setmeal 更新的套餐对象
     */
    void update(Setmeal setmeal);
}
