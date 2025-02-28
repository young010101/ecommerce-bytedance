package com.sky.controller.admin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.interceptor.JwtTokenAdminInterceptor;
import com.sky.properties.JwtProperties;
import com.sky.protos.*;
import com.sky.service.DemoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = DemoDubboController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({JwtProperties.class})
class DemoDubboControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private DemoService demoService;

  @MockitoBean private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

  @MockitoBean private Greeter greeterService;

  @MockitoBean private ProductCatalogService productCatalogService;
  @MockitoBean private UserService clientUserService;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() throws Exception {
    when(jwtTokenAdminInterceptor.preHandle(
            any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class)))
        .thenReturn(true);
  }

  @Test
  void hello_ShouldReturnExpectedMessage() throws Exception {
    when(demoService.sayHello("World")).thenReturn("Hello World");

    mockMvc
        .perform(get("/dubbo/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello World"));

    verify(demoService).sayHello("World");
  }

  @Test
  void protoc_ShouldReturnProtobufMessage() throws Exception {
    mockMvc
        .perform(get("/dubbo/protoc"))
        .andExpect(status().isOk())
        .andExpect(content().string("hello"));
  }

  @Test
  void greeter_ShouldReturnExpectedMessage() throws Exception {
    String reply = "Hello World";
    when(greeterService.greet(any(GreeterRequest.class)))
        .thenReturn(GreeterReply.newBuilder().setMessage(reply).build());

    mockMvc
        .perform(get("/dubbo/greeter/world"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(1))
        .andExpect(jsonPath("$.data").value(reply));
  }
}
