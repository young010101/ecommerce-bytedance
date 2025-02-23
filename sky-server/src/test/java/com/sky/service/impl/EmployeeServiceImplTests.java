package com.sky.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.sky.constant.StatusConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
// import com.sky.exception.DuplicateUsernameException;
import com.sky.mapper.EmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.DigestUtils;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeServiceImplTest {

  @Mock private EmployeeMapper employeeMapper;

  @InjectMocks private EmployeeServiceImpl employeeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Value("${test.password}")
  private String testPassword;

  @Test
  void login_success() {
    // Arrange
    EmployeeLoginDTO loginDTO = new EmployeeLoginDTO();
    loginDTO.setUsername("test");
    loginDTO.setPassword(testPassword);

    Employee mockEmployee = new Employee();
    mockEmployee.setStatus(StatusConstant.ENABLE);
    mockEmployee.setPassword(DigestUtils.md5DigestAsHex(testPassword.getBytes()));

    when(employeeMapper.getByUsername(anyString())).thenReturn(mockEmployee);

    // Act
    Employee result = employeeService.login(loginDTO);

    // Assert
    assertNotNull(result);
  }

  @Test
  void login_accountNotFound() {
    // Arrange
    EmployeeLoginDTO loginDTO = new EmployeeLoginDTO();
    loginDTO.setUsername("nonexistent");

    when(employeeMapper.getByUsername(anyString())).thenReturn(null);

    // Act & Assert
    assertThrows(AccountNotFoundException.class, () -> employeeService.login(loginDTO));
  }

  @Test
  void login_accountLocked() {
    // Arrange
    EmployeeLoginDTO loginDTO = new EmployeeLoginDTO();
    loginDTO.setUsername("locked");
    loginDTO.setPassword(testPassword);

    Employee mockEmployee = new Employee();
    mockEmployee.setStatus(StatusConstant.DISABLE);
    mockEmployee.setPassword(DigestUtils.md5DigestAsHex(testPassword.getBytes()));

    when(employeeMapper.getByUsername(anyString())).thenReturn(mockEmployee);

    // Act & Assert
    assertThrows(AccountLockedException.class, () -> employeeService.login(loginDTO));
  }

  @Test
  void addEmployee_success() {
    // Arrange
    EmployeeDTO employee = new EmployeeDTO();
    employee.setUsername("newEmployee");
    employee.setName("Test Employee");
    employee.setPhone("1234567890");
    employee.setIdNumber("123456789012345678");

    doNothing().when(employeeMapper);

    // Act
    employeeService.addEmployee(employee);

    // Assert
    assertNull(employee.getId());
    //    assertNotNull(employee.getPassword());
    //    assertEquals(StatusConstant.ENABLE, employee.getStatus());
    //    assertEquals(DigestUtils.md5DigestAsHex("123456".getBytes()), employee.getPassword());
  }

  // @Test
  // void addEmployee_duplicateUsername() {
  // // Arrange
  // Employee employee = new Employee();
  // employee.setUsername("existingEmployee");
  // employee.setName("Test Employee");
  // employee.setPhone("1234567890");
  // employee.setIdNumber("123456789012345678");
  //
  // when(employeeMapper.getByUsername(employee.getUsername()))
  // .thenReturn(new Employee()); // Simulate existing employee
  //
  // // Act & Assert
  // assertThrows(
  // DuplicateUsernameException.class,
  // () -> employeeService.addEmployee(employee));
  // }
}
