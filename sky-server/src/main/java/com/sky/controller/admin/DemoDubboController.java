package com.sky.controller.admin;

import com.sky.protos.*;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.service.DemoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dubbo")
@Slf4j
@Tag(name = "Dubbo测试")
public class DemoDubboController {
  @DubboReference
  private DemoService demoService;

  @DubboReference
  private Greeter greeterService;

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

  @GetMapping("/greeter/{name}")
  public Result<String> greeter(@PathVariable String name) {
    log.info("greeter: {}", name);
    GreeterReply reply = greeterService.greet(GreeterRequest.newBuilder().setName(name).build());
    return Result.success(reply.getMessage());
  }
}
