package com.sky.controller.client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.sky.constant.PasswordConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.protos.LoginReq;
import com.sky.protos.LoginResp;
import com.sky.protos.RegisterReq;
import com.sky.protos.RegisterResp;
import com.sky.protos.UserService;
import com.sky.result.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientUserControllerTest {

  @Mock private UserService userService;

  @InjectMocks private ClientUserController clientUserController;

  @Test
  void register_ShouldReturnUserId_WhenValidRequest() {
    // Arrange
    EmployeeDTO employeeDTO = new EmployeeDTO();
    employeeDTO.setUsername("test@example.com");

    RegisterReq expectedRequest =
        RegisterReq.newBuilder()
            .setEmail("test@example.com")
            .setPassword(PasswordConstant.DEFAULT_PASSWORD)
            .build();

    RegisterResp mockResponse = RegisterResp.newBuilder().setUserId(123).build();

    when(userService.register(expectedRequest)).thenReturn(mockResponse);

    // Act
    Result<Integer> result = clientUserController.register(employeeDTO);

    // Assert
    assertNotNull(result);
    assertEquals(123, result.getData());
    verify(userService).register(expectedRequest);
  }

  @Test
  void login_ShouldReturnUserId_WhenValidCredentials() {
    // Arrange
    EmployeeLoginDTO loginDTO = new EmployeeLoginDTO();
    loginDTO.setUsername("test@example.com");
    loginDTO.setPassword("password123");

    LoginReq expectedRequest =
        LoginReq.newBuilder().setEmail("test@example.com").setPassword("password123").build();

    LoginResp mockResponse = LoginResp.newBuilder().setUserId(123).build();

    when(userService.login(expectedRequest)).thenReturn(mockResponse);

    // Act
    Result<Integer> result = clientUserController.login(loginDTO);

    // Assert
    assertNotNull(result);
    assertEquals(123, result.getData());
    verify(userService).login(expectedRequest);
  }
}
