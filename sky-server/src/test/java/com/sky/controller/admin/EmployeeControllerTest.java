package com.sky.controller.admin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.interceptor.JwtTokenAdminInterceptor;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Slf4j
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private EmployeeService employeeService;

  @MockitoBean private JwtProperties jwtProperties;

  @MockitoBean private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

  @Autowired private ObjectMapper objectMapper;

  private Employee testEmployee;
  private EmployeeDTO testEmployeeDTO;
  private EmployeeLoginDTO testLoginDTO;

  @BeforeEach
  void setUp() {
    testEmployee = new Employee();
    testEmployee.setId(1L);
    testEmployee.setUsername("test");
    testEmployee.setName("Test User");

    testEmployeeDTO = new EmployeeDTO();
    testEmployeeDTO.setUsername("test");
    testEmployeeDTO.setName("Test User");

    testLoginDTO = new EmployeeLoginDTO();
    testLoginDTO.setUsername("test");
    testLoginDTO.setPassword("password");

    when(jwtProperties.getAdminSecretKey()).thenReturn("your-test-secret-key-your-test-secret-key");
    when(jwtProperties.getAdminTtl()).thenReturn(7200L);
  }

  @Test
  void login_ShouldReturnLoginVO() throws Exception {
    when(employeeService.login(any(EmployeeLoginDTO.class))).thenReturn(testEmployee);

    String url = "/admin/employee/login";
    MockHttpServletResponse response =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(testLoginDTO)))
            .andReturn() // 获取结果
            .getResponse(); // 获取响应

    log.info("Response Status: " + response.getStatus());
    log.info("Response Content: " + response.getContentAsString());

    mockMvc
        .perform(
            post("/admin/employee/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testLoginDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(1))
        .andExpect(jsonPath("$.data.userName").value("test"));
  }

  @Test
  void logout_ShouldReturnSuccess() throws Exception {
    when(jwtTokenAdminInterceptor.preHandle(
            any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class)))
        .thenReturn(true);

    String url = "/admin/employee/logout";
    MockHttpServletResponse response =
        mockMvc
            .perform(MockMvcRequestBuilders.post(url)) // 添加有效Token)
            .andReturn() // 获取结果
            .getResponse(); // 获取响应

    log.info("Response Status: " + response.getStatus());
    log.info("Response Content: " + response.getContentAsString());

    mockMvc.perform(post(url)).andExpect(status().isOk()).andExpect(jsonPath("$.code").value(1));
  }

  @Test
  void addEmployee_ShouldReturnSuccess() throws Exception {
    when(jwtTokenAdminInterceptor.preHandle(
            any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class)))
        .thenReturn(true);
    doNothing().when(employeeService).addEmployee(any(EmployeeDTO.class));

    mockMvc
        .perform(
            post("/admin/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testEmployeeDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(1));

    verify(employeeService).addEmployee(any(EmployeeDTO.class));
  }

  @Test
  void pageQuery_ShouldReturnPageResult() throws Exception {
    when(jwtTokenAdminInterceptor.preHandle(
            any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class)))
        .thenReturn(true);
    PageResult pageResult = new PageResult();
    pageResult.setTotal(1L);
    when(employeeService.pageQuery(any(EmployeePageQueryDTO.class))).thenReturn(pageResult);

    mockMvc
        .perform(get("/admin/employee/page").param("page", "1").param("pageSize", "10"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(1))
        .andExpect(jsonPath("$.data.total").value(1));
  }

  @Test
  void enableOrDisableEmployee_ShouldReturnSuccess() throws Exception {
    when(jwtTokenAdminInterceptor.preHandle(
            any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class)))
        .thenReturn(true);
    doNothing().when(employeeService).enableOrDisableEmployee(anyInt(), anyLong());

    mockMvc
        .perform(post("/admin/employee/status/1/id/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(1));

    verify(employeeService).enableOrDisableEmployee(1, 1L);
  }

  @Test
  void getEmployeeById_ShouldReturnEmployee() throws Exception {
    when(jwtTokenAdminInterceptor.preHandle(
            any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class)))
        .thenReturn(true);
    when(employeeService.getEmployeeById(anyLong())).thenReturn(testEmployee);

    mockMvc
        .perform(get("/admin/employee/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(1))
        .andExpect(jsonPath("$.data.username").value("test"));
  }

  @Test
  void updateEmployee_ShouldReturnSuccess() throws Exception {
    when(jwtTokenAdminInterceptor.preHandle(
            any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class)))
        .thenReturn(true);
    doNothing().when(employeeService).updateEmployee(any(EmployeeDTO.class));

    mockMvc
        .perform(
            put("/admin/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testEmployeeDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(1));

    verify(employeeService).updateEmployee(any(EmployeeDTO.class));
  }
}
