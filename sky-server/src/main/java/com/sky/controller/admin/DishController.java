package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @since 1.0
     * @see DishDTO
     * @see Result
     * @see com.sky.entity.DishFlavor
     */
    @PostMapping
    @ApiOperation("添加菜品")
    public Result<?> addDish(@RequestBody DishDTO dishDTO) {  // @RequestBody 注解用于接收前端传递的json数据
        log.info("添加菜品：{}", dishDTO);

        dishService.saveWithFlavor(dishDTO);

        return Result.success();
    }
}
