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
import java.util.Objects;

/**
 * Implementation of DishService for managing dish-related operations.
 *
 * @author GPT4o
 */
@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * Save a dish along with its associated flavors.
     * @param dishDTO Data Transfer Object containing dish and flavor details.
     */
    @Transactional
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        // Map DTO to Dish entity
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        // Save dish to the database
        dishMapper.insert(dish);
        Long dishId = dish.getId();

        // Map and save associated flavors, if any
        saveDishFlavors(dishId, dishDTO.getFlavors());
    }

    /**
     * Helper method to save dish flavors.
     * @param dishId The ID of the dish.
     * @param flavors List of flavors to associate with the dish.
     */
    private void saveDishFlavors(Long dishId, List<DishFlavor> flavors) {
        if (Objects.nonNull(flavors) && !flavors.isEmpty()) {
            flavors.forEach(flavor -> flavor.setDishId(dishId));
            dishFlavorMapper.insertBatch(flavors);
        } else {
            log.warn("No flavors provided for dish ID: {}", dishId);
        }
    }
}