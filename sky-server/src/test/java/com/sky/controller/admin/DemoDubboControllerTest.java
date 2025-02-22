package com.sky.controller.admin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.interceptor.JwtTokenAdminInterceptor;
import com.sky.properties.JwtProperties;
import com.sky.protos.Greeter;
import com.sky.protos.GreeterReply;
import com.sky.protos.GreeterRequest;
import com.sky.service.DemoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DemoDubboController.class)
class DemoDubboControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private DemoService demoService;

  @MockitoBean private JwtProperties jwtProperties;

  @MockitoBean private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

  @MockitoBean private Greeter greeterService;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    when(jwtProperties.getAdminSecretKey()).thenReturn("your-test-secret-key-your-test-secret-key");
    when(jwtProperties.getAdminTtl()).thenReturn(7200L);
  }

  @Test
  void hello_ShouldReturnExpectedMessage() throws Exception {
    when(jwtTokenAdminInterceptor.preHandle(
            any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class)))
        .thenReturn(true);
    // Given
    when(demoService.sayHello("World")).thenReturn("Hello World");

    // When/Then
    mockMvc
        .perform(get("/dubbo/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello World"));

    verify(demoService).sayHello("World");
  }

  @Test
  void protoc_ShouldReturnProtobufMessage() throws Exception {
    when(jwtTokenAdminInterceptor.preHandle(
            any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class)))
        .thenReturn(true);
    // When/Then
    mockMvc
        .perform(get("/dubbo/protoc"))
        .andExpect(status().isOk())
        .andExpect(content().string("hello"));
  }

  @Test
  void greeter_ShouldReturnExpectedMessage() throws Exception {
    when(jwtTokenAdminInterceptor.preHandle(
            any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class)))
        .thenReturn(true);
    // 预期:Hello World
    // 实际:{"code":1,"msg":null,"data":"Hello World"}
    // .andExpect(jsonPath("$.code").value(1))
    // .andExpect(jsonPath("$.data.username").value("test"));
    String reply = "Hello World";
    when(greeterService.greet(any(GreeterRequest.class)))
        .thenReturn(GreeterReply.newBuilder().setMessage(reply).build());

    mockMvc
        .perform(get("/dubbo/greeter/world"))
        .andExpect(status().isOk())
        // .andExpect(content().string(reply))
        .andExpect(jsonPath("$.code").value(1))
        .andExpect(jsonPath("$.data").value(reply));
  }
}
