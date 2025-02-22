package com.sky.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.protos.ResponseOuterClass;

@RestController
@RequestMapping("/protoc")
public class ProtocController {

  ResponseOuterClass.Response person = ResponseOuterClass.Response.newBuilder().setData("hello").build();

  @GetMapping("/protoc")
  public String protoc() {
    return person.getData();
  }
}
