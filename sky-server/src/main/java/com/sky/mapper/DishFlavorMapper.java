package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * Insert dish flavors.
     *
     * @param flavors The dish flavors to insert.
     */
    void insertBatch(List<DishFlavor> flavors);
}
