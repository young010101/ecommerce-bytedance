package com.sky.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.protos.ResponseOuterClass;
import com.sky.service.DemoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dubbo")
@Slf4j
@Tag(name = "Dubbo测试")
public class DemoDubboController {
  @DubboReference
  private DemoService demoService;

  @GetMapping("/hello")
  public String hello() {
    log.info("hello");
    return demoService.sayHello("World");
  }

  ResponseOuterClass.Response person = ResponseOuterClass.Response.newBuilder().setData("hello").build();

  @GetMapping("/protoc")
  public String protoc() {
    log.info("protoc");
    return person.getData();
  }
}
