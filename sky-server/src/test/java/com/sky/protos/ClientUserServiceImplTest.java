package com.sky.protos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.sky.constant.PasswordConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.entity.Employee;
import com.sky.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientUserServiceImplTest {

  private static final String TEST_EMAIL = "test@example.com";
  private static final String TEST_CREDENTIAL = PasswordConstant.DEFAULT_PASSWORD;
  private static final Long TEST_USER_ID = 123L;

  @Mock private EmployeeService employeeService;

  @InjectMocks private ClientUserServiceImpl clientUserService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void register_ShouldReturnUserId() {
    // Arrange
    RegisterReq request =
        RegisterReq.newBuilder().setEmail(TEST_EMAIL).setPassword(TEST_CREDENTIAL).build();

    EmployeeDTO expectedEmployeeDTO = new EmployeeDTO();
    expectedEmployeeDTO.setUsername(TEST_EMAIL);

    when(employeeService.addEmployee(any(EmployeeDTO.class))).thenReturn(TEST_USER_ID);

    // Act
    RegisterResp response = clientUserService.register(request);

    // Assert
    assertEquals(TEST_USER_ID.intValue(), response.getUserId());
    verify(employeeService).addEmployee(any(EmployeeDTO.class));
  }

  @Test
  void login_ShouldReturnUserId() {
    // Arrange
    LoginReq request =
        LoginReq.newBuilder().setEmail(TEST_EMAIL).setPassword(TEST_CREDENTIAL).build();

    Employee mockEmployee = new Employee();
    mockEmployee.setId(TEST_USER_ID);

    when(employeeService.login(any(EmployeeLoginDTO.class))).thenReturn(mockEmployee);

    // Act
    LoginResp response = clientUserService.login(request);

    // Assert
    assertEquals(TEST_USER_ID.intValue(), response.getUserId());
    verify(employeeService).login(any(EmployeeLoginDTO.class));
  }

  @Test
  void login_ShouldThrowExceptionWhenCredentialsAreInvalid() {
    // Arrange
    LoginReq request =
        LoginReq.newBuilder()
            .setEmail(TEST_EMAIL)
            .setPassword(java.util.UUID.randomUUID().toString())
            .build();

    when(employeeService.login(any(EmployeeLoginDTO.class)))
        .thenThrow(new RuntimeException("Invalid credentials"));

    // Act & Assert
    assertThrows(RuntimeException.class, () -> clientUserService.login(request));
    verify(employeeService, times(1)).login(any(EmployeeLoginDTO.class));
  }
}
