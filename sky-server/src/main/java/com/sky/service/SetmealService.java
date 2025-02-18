package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

/**
 * The interface for the setmeal service.
 * This interface defines all the business methods related to setmeal
 * management,
 * such as adding a new setmeal, updating a setmeal, deleting a setmeal,
 * and querying setmeals with pagination.
 */
public interface SetmealService {
    /**
     * Saves a new setmeal with its dishes.
     *
     * @param setmealDTO the setmeal data transfer object
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * Retrieves setmeal information by page.
     *
     * @param setmealDTO 包含修改后的套餐详细信息的 DTO 对象
     */
    void updateSetmeal(SetmealDTO setmealDTO);

    /**
     * Retrieves setmeal information by id.
     *
     * @param setmealPageQueryDTO 分页查询条件 DTO 对象
     * @return PageResult 分页查询结果，包含套餐列表
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * Retrieves the setmeal information by id.
     *
     * @param id the setmeal id
     * @return the setmeal data transfer object
     */
    SetmealVO getSetmealById(Long id);

    /**
     * Deletes the setmeals by ids.
     *
     * @param ids the setmeal ids
     */
    void deleteSetmeals(List<Long> ids);

    /**
     * Enables or disables the status of a setmeal.
     *
     * @param id     the setmeal id
     * @param status the setmeal status (1: enabled, 0: disabled)
     */
    void enableOrDisableSetmeal(Integer status, Long id);

    /**
     * Retrieves a list of setmeals by condition.
     *
     * @param setmeal the setmeal object
     * @return the list of setmeals
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * Retrieves a list of dish items by id.
     *
     * @param id the dish id
     * @return the list of dish items
     */
    List<DishItemVO> getDishItemById(Long id);
}
