package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

/**
 * 套餐服务接口
 * 该接口定义了与套餐管理相关的所有业务方法，如新增套餐、修改套餐、删除套餐、分页查询套餐等。
 */
public interface SetmealService {
    /**
     * 新增套餐
     *
     * @param setmealDTO 包含新增套餐详细信息的 DTO 对象
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 修改套餐信息
     *
     * @param setmealDTO 包含修改后的套餐详细信息的 DTO 对象
     */
    void updateSetmeal(SetmealDTO setmealDTO);

    /**
     * 分页查询套餐列表
     *
     * @param setmealPageQueryDTO 分页查询条件 DTO 对象
     * @return PageResult 分页查询结果，包含套餐列表
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据 ID 查询套餐详细信息
     *
     * @param id 套餐的唯一标识 ID
     * @return Setmeal 套餐的详细信息
     */
    SetmealVO getSetmealById(Long id);

    /**
     * 批量删除套餐
     *
     * @param ids 套餐 ID 列表
     */
    void deleteSetmeals(List<Long> ids);

    /**
     * 设置套餐的起售或停售状态
     *
     * @param id     套餐的唯一标识 ID
     * @param status 套餐状态（1：起售，0：停售）
     */
    void enableOrDisableSetmeal(Integer status, Long id);

    /**
     * 条件查询
     *
     * @param setmeal ? 套餐对象，包含查询条件
     * @return List<Setmeal> 符合条件的套餐列表
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     *
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
