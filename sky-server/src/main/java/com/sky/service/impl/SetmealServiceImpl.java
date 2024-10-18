package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of SetmealService for managing setmeal-related operations.
 */
@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 新增套餐
     *
     * @param setmealDTO 包含新增套餐详细信息的 DTO 对象
     */
    @Transactional
    @Override
    public void saveWithDish(SetmealDTO setmealDTO) {
        // Map DTO to Setmeal entity
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        // Save setmeal to the database
        setmealMapper.insert(setmeal);

        // TODO 这里又忘记添加后要返回id
        Long setmealId = setmeal.getId();

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();

        setmealDishes.forEach(setmealDish -> setmealDish.setSetmealId(setmealId));

        setmealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * 修改套餐信息
     *
     * @param setmealDTO 包含修改后的套餐详细信息的 DTO 对象
     */
    @Transactional
    @Override
    public void updateSetmeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.update(setmeal);

        setmealDishMapper.deleteBySetmealId(setmealDTO.getId());
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> setmealDish.setSetmealId(setmealDTO.getId()));
        setmealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * 分页查询套餐列表
     *
     * @param setmealPageQueryDTO 分页查询条件 DTO 对象
     * @return PageResult 分页查询结果，包含套餐列表
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        // Set up pagination parameters
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());

        // Query the database for setmeals that match the criteria
        Page<SetmealVO> setmeals = setmealMapper.pageQuery(setmealPageQueryDTO);

        // Use PageInfo to get the details of the page result
        PageInfo<SetmealVO> pageInfo = new PageInfo<>(setmeals);

        // Wrap the result in a PageResult object
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据 ID 查询套餐详细信息
     *
     * @param id 套餐的唯一标识 ID
     * @return Setmeal 套餐的详细信息
     */
    @Override
    public SetmealVO getSetmealById(Long id) {
        Setmeal byId = setmealMapper.getById(id);
        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(byId, setmealVO);

        setmealVO.setSetmealDishes(setmealDishMapper.getBySetmealId(id));

        return setmealVO;
    }

    /**
     * 批量删除套餐
     *
     * @param ids 套餐 ID 列表
     */
    @Transactional
    @Override
    public void deleteSetmeals(List<Long> ids) {
        for (Long setmealId : ids) {
            Setmeal setmeal = setmealMapper.getById(setmealId);
            if (setmeal.getStatus().equals(StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        }

        // delete setmeal dishes
        setmealDishMapper.deleteBatchBySetmealIds(ids);

        // Delete setmeal records in batch
        setmealMapper.deleteBatchIds(ids);
    }

    /**
     * 设置套餐的起售或停售状态
     *
     * @param id     套餐的唯一标识 ID
     * @param status 套餐状态（1：起售，0：停售）
     */
    @Override
    public void enableOrDisableSetmeal(Integer status, Long id) {
        Setmeal setmeal = Setmeal.builder().id(id).status(status).build();
        setmealMapper.update(setmeal);
    }

    /**
     * 条件查询
     *
     * @param setmeal
     * @return
     */
    public List<Setmeal> list(Setmeal setmeal) {
        return setmealMapper.list(setmeal);
    }

    @Override
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }
}
