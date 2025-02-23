package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * Saves a new dish with its flavors.
     *
     * @param dishDTO the dish data transfer object
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * Retrieves dish information by page.
     *
     * @param dishPageQueryDTO the query parameters for pagination
     * @return page result containing dish information
     */
    PageResult<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * Retrieves dish information by id.
     *
     * @param id the dish id
     * @return the dish data transfer object
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * Deletes dishes by ids.
     *
     * @param ids the dish ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * Updates a dish with its flavors.
     *
     * @param dishDTO the dish data transfer object
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * Enables or disables the status of a dish.
     *
     * @param id     the dish id
     * @param status the status
     */
    void enableOrDisableStatus(Long id, Integer status);

    /**
     * Retrieves a list of dishes by category id.
     *
     * @param categoryId the category id
     * @return the list of dishes
     */
    List<Dish> listByCategoryId(Long categoryId);

    /**
     * Retrieves a list of dishes with flavors.
     *
     * @param dish the dish object
     * @return the list of dishes with flavor
     */
    List<DishVO> listWithFlavor(Dish dish);
}
