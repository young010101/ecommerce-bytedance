package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品管理")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 添加菜品.
     * 黑马程序员使用的方法名是save，这里使用addDish.
     *
     * @param dishDTO 菜品信息
     * @return Result
     * @see DishDTO
     * @see Result
     * @see com.sky.entity.DishFlavor
     * @since 1.0
     */
    @PostMapping
    @ApiOperation("添加菜品")
    public Result<?> addDish(@RequestBody DishDTO dishDTO) {  // @RequestBody 注解用于接收前端传递的json数据
        log.info("添加菜品：{}", dishDTO);

        dishService.saveWithFlavor(dishDTO);

        return Result.success();
    }

    /**
     * page query
     * 分页查询菜品
     *
     * @param dishPageQueryDTO 分页查询条件
     * @return Result
     * @see DishPageQueryDTO
     * @see PageResult
     * @see Result
     * @see com.sky.entity.Dish
     * @since 1.0
     */
    @GetMapping("/page")
    @ApiOperation("分页查询菜品")
    public Result<PageResult> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询菜品：{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Get dish by id.
     *
     * @param id The dish id.
     * @return Result
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * Delete dishes by their IDs. Supports batch deletion by providing multiple IDs.
     *
     * @param ids List of dish IDs to delete.
     * @return Response message indicating success or failure.
     */
    @DeleteMapping
    @ApiOperation("根据id删除菜品")
    public Result<?> deleteByIds(@RequestParam List<Long> ids) {
        log.info("根据id删除菜品：{}", ids);
        dishService.deleteByIds(ids);
        return Result.success();
    }
}
