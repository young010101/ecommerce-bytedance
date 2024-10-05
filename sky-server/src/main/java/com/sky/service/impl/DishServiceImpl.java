package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜品服务实现类
 */
@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 添加菜品和对应口味
     * @param dishDTO 菜品信息
     */
    @Transactional  // 事务注解, 保证数据的原子性
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();

        BeanUtils.copyProperties(dishDTO, dish);

        List<DishFlavor> flavors = dishDTO.getFlavors();

        // 判断是否有口味
        if (flavors != null && !flavors.isEmpty()) {
            // 向口味表中插入数据
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dish.getId());
                dishFlavorMapper.insert(flavor);
            }
        } else {

        }

        dishMapper.insert(dish);

    }
}
