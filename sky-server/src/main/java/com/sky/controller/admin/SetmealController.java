package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐管理控制器
 * 该控制器提供与套餐管理相关的所有操作接口，如新增套餐、修改套餐、删除套餐、分页查询套餐等。
 */
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐管理")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    /**
     * 修改套餐信息
     * <p>
     * 依赖根据套餐 ID 查询套餐信息
     *
     * @param setmealDTO 包含套餐详细信息的 DTO 对象
     * @return 操作结果
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public Result<?> updateSetmeal(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改套餐：{}", setmealDTO);
        setmealService.updateSetmeal(setmealDTO);
        return Result.success();
    }

    /**
     * 分页查询套餐列表
     *
     * @param setmealPageQueryDTO 分页查询条件 DTO 对象
     * @return 分页查询结果，包含套餐列表
     */
    @GetMapping("/page")
    @ApiOperation("分页查询套餐")
    public Result<PageResult> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("分页查询套餐：{}", setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 设置套餐的起售或停售状态
     *
     * @param status 套餐状态（1：起售，0：停售）
     * @param id     套餐的唯一标识 ID
     * @return 操作结果
     */
    @PostMapping("/status/{status}")
    @ApiOperation("起售/停售套餐")
    public Result<?> enableOrDisableSetmeal(@PathVariable Integer status, @RequestParam Long id) {
        log.info("起售/停售套餐：status={}, id={}", status, id);
        setmealService.enableOrDisableSetmeal(status, id);
        return Result.success();
    }

    /**
     * 批量删除套餐
     *
     * @param ids 套餐 ID 列表
     * @return 操作结果
     */
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public Result<?> deleteSetmeals(@RequestParam List<Long> ids) {
        log.info("批量删除套餐：ids={}", ids);
        setmealService.deleteSetmeals(ids);
        return Result.success();
    }

    /**
     * 新增套餐
     *
     * @param setmealDTO 包含新增套餐详细信息的 DTO 对象
     * @return 操作结果
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result<?> save(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐：{}", setmealDTO);
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    /**
     * 根据 ID 查询套餐详细信息
     *
     * @param id 套餐的唯一标识 ID
     * @return 套餐的详细信息
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询套餐")
    public Result<SetmealVO> getSetmealById(@PathVariable Long id) {
        log.info("根据ID查询套餐：id={}", id);
        SetmealVO setmealInfo = setmealService.getSetmealById(id);
        return Result.success(setmealInfo);
    }
}
