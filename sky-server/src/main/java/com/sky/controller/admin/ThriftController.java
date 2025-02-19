package com.sky.controller.admin;

import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/thrift")
public class ThriftController {
    @GetMapping("/hello/{name}")
    Result<String> thrift(@PathVariable String name) {
        log.info("thrift hello: {}", name);
        return Result.success("hello " + name);
    }
}
