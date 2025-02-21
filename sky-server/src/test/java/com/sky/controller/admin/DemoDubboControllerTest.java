// package com.sky.controller.admin;

// import static org.mockito.Mockito.*;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import com.sky.interceptor.JwtTokenAdminInterceptor;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;

// import com.sky.service.DemoService;

// @WebMvcTest(DemoDubboController.class)
// class DemoDubboControllerTest {

// @Autowired
// private MockMvc mockMvc;

// @MockBean
// private DemoService demoService;

// @MockBean private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

// @Test
// void hello_ShouldReturnExpectedMessage() throws Exception {
// when(jwtTokenAdminInterceptor.preHandle(
// any(HttpServletRequest.class), any(HttpServletResponse.class),
// any(Object.class)))
// .thenReturn(true);
// // Given
// when(demoService.sayHello("World")).thenReturn("Hello World");

// // When/Then
// mockMvc.perform(get("/dubbo/hello"))
// .andExpect(status().isOk())
// .andExpect(content().string("Hello World"));

// verify(demoService).sayHello("World");
// }

// @Test
// void protoc_ShouldReturnProtobufMessage() throws Exception {
// // When/Then
// mockMvc.perform(get("/dubbo/protoc"))
// .andExpect(status().isOk())
// .andExpect(content().string("hello"));
// }
// }
