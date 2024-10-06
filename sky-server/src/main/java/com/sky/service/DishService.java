package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

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
}
