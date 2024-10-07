package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
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
     *
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
     *
     * @param dishId  The ID of the dish.
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

    /**
     * 分页查询菜品
     *
     * @param dishPageQueryDTO 分页查询条件
     * @return PageResult 分页查询结果
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        // Set up the pagination parameters (page number and page size)
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());  // TODO warning unsolved.

        // Query the database for dishes that match the criteria in dishPageQueryDTO
        Page<DishVO> dishes = dishMapper.pageQuery(dishPageQueryDTO);  // 这里用`DishVO`是因为`DishVO`是`Dish`的子类，`DishVO`是`Dish`的视图对象，包含了`Dish`的所有属性，还包含了其他属性，比如`categoryName`，`flavors`等。

        // Use PageInfo to get the details of the page result
        PageInfo<DishVO> pageInfo = new PageInfo<>(dishes);  // Why GPT4o 要在此多此一举？

        // Wrap the result in a PageResult object
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据id查询菜品
     * Get dish by id.
     *
     * @param id 菜品id
     * @return DishVO 菜品信息
     */
    @Override
    public DishVO getByIdWithFlavor(Long id) {
        DishVO byId = dishMapper.getById(id);
        byId.setFlavors(dishFlavorMapper.getByDishId(id));
        return byId;
    }

    @Override
    public List<DishFlavor> getFlavorsByDishId(Long dishId) {
        return dishFlavorMapper.getByDishId(dishId);
    }
}