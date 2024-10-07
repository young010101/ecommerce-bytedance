package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

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
}
