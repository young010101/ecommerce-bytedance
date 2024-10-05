package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishFlavorMapper {
    /**
     * Insert a dish flavor.
     *
     * @param dishFlavor The dish flavor to insert.
     */
    @Insert("INSERT INTO dish_flavor (dish_id, name, value) VALUES (#{dishId}, #{name}, #{value})")
    void insert(DishFlavor dishFlavor);
}
