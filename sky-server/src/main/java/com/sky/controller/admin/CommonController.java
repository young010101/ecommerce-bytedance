package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Common controller for admin.
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api(tags = "公共接口")
public class CommonController {
    /**
     * Upload file.
     * @return The result.
     * @param file The file to upload.
     */
     @PostMapping("/upload")
     @ApiOperation("上传文件")
     public Result<?> upload(@RequestParam("file") MultipartFile file) { //TODO 参数名和前端一致
         log.info("上传文件：{}", file);
         return Result.success();
     }
}
