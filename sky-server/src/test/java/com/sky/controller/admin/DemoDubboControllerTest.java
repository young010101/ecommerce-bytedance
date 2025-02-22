package com.sky.controller.admin;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.interceptor.JwtTokenAdminInterceptor;
import com.sky.properties.JwtProperties;
import com.sky.service.DemoService;
import com.sky.service.EmployeeService;
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

  @MockitoBean private EmployeeService employeeService;

  @MockitoBean private JwtProperties jwtProperties;

  @MockitoBean private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

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
}
