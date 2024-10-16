package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
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

    /**
     * Update dish.
     *
     * @param dishDTO The dish information.
     *                菜品信息
     */
    @PutMapping
    @ApiOperation("更新菜品")
    public Result<?> update(@RequestBody DishDTO dishDTO) {
        log.info("更新菜品：{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * Updates the status of a dish to either enable or disable its sale.
     * <p>
     * This method handles a POST request to update the status of a dish identified by its ID.
     * The status parameter indicates whether the dish should be enabled (1) or disabled (0).
     *
     * @param id The ID of the dish to update.
     * @param status The new status for the dish (1 for enabled, 0 for disabled).
     * @return A Result object indicating the success of the operation.
     */
    @PostMapping("/status/{status}")  // Post instead of Put
    @ApiOperation("菜品起售、停售")
    public Result<?> updateStatus(@RequestParam Long id, @PathVariable Integer status) {
        // Log the operation with the dish ID and the new status
        log.info("菜品起售、停售：id={}, status={}", id, status);
        // Call the dishService to enable or disable the status of the dish
        dishService.enableOrDisableStatus(id, status);
        // Return a success result
        return Result.success();
    }

    /// 根据分类id查询菜品
    /// @param categoryId 分类id
    /// @return Result
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId) {
        log.info("根据分类id查询菜品：{}", categoryId);
        List<Dish> dishVOList = dishService.listByCategoryId(categoryId);
        return Result.success(dishVOList);
    }
}
