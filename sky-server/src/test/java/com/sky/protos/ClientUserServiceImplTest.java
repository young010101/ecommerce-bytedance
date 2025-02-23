package com.sky.protos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClientUserServiceImplTest {

  @InjectMocks private ClientUserServiceImpl clientUserService;

  @Mock private EmployeeService employeeService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void register_ShouldReturnUserId() {
    // Arrange
    RegisterReq request = RegisterReq.newBuilder().setEmail("test@example.com").build();

    when(employeeService.addEmployee(any(EmployeeDTO.class))).thenReturn(1L);

    // Act
    RegisterResp response = clientUserService.register(request);

    // Assert
    assertEquals(1, response.getUserId());
    verify(employeeService, times(1)).addEmployee(any(EmployeeDTO.class));
  }

  @Test
  void login_ShouldReturnUserIdWhenCredentialsAreValid() {
    // Arrange
    LoginReq request =
        LoginReq.newBuilder().setEmail("test@example.com").setPassword("password123").build();

    Employee employee = new Employee();
    employee.setId(1L);

    when(employeeService.login(any(EmployeeLoginDTO.class))).thenReturn(employee);

    // Act
    LoginResp response = clientUserService.login(request);

    // Assert
    assertEquals(1, response.getUserId());
    verify(employeeService, times(1)).login(any(EmployeeLoginDTO.class));
  }

  @Test
  void login_ShouldThrowExceptionWhenCredentialsAreInvalid() {
    // Arrange
    LoginReq request =
        LoginReq.newBuilder().setEmail("test@example.com").setPassword("wrongpassword").build();

    when(employeeService.login(any(EmployeeLoginDTO.class)))
        .thenThrow(new RuntimeException("Invalid credentials"));

    // Act & Assert
    assertThrows(RuntimeException.class, () -> clientUserService.login(request));
    verify(employeeService, times(1)).login(any(EmployeeLoginDTO.class));
  }
}
