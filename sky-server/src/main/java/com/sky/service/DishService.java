package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 添加菜品和口味
     *
     * @param dishDTO 菜品信息
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 分页查询菜品
     *
     * @param dishPageQueryDTO 分页查询条件
     * @return PageResult 分页查询结果
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品
     *
     * @param id 菜品id
     * @return DishVO 菜品信息
     */
    DishVO getByIdWithFlavor(Long id);

    /// Delete dish by ids.
    ///
    /// @param ids The dish ids.
    void deleteByIds(List<Long> ids);

    /// Update dish with flavor.
    ///
    /// @param dishDTO The dish dto.
    void updateWithFlavor(DishDTO dishDTO);

    /// Enable or disable dish.
    /// @param id The dish id.
    void enableOrDisableStatus(Long id, Integer status);
}
