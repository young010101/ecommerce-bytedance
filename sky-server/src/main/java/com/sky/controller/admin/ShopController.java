package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@Slf4j
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
public class ShopController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "SHOP_STATUS";

    /**
     * 店铺状态
     *
     * @param status 0 打烊中 1 营业中
     * @return Result<?>
     */
    @PutMapping("/{status}")
    public Result<?> status(@PathVariable Integer status) {
        redisTemplate.opsForValue().set(KEY, status);
        log.info("店铺状态修改为：{}", status == 1 ? "营业中" : "打烊中");
        return Result.success();
    }

    /**
     * get status
     */
    @GetMapping("/status")
    public Result<Integer> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        return Result.success(status);
    }
}