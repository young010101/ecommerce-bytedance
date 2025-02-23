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
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClientUserControllerTest {

  private static final String TEST_EMAIL = "test@example.com";

  @Mock private UserService userService;

  @InjectMocks private ClientUserController clientUserController;

  @Test
  void register_ShouldReturnUserId_WhenValidRequest() {
    // Arrange
    EmployeeDTO employeeDTO = new EmployeeDTO();
    employeeDTO.setUsername(TEST_EMAIL);

    RegisterReq expectedRequest =
        RegisterReq.newBuilder()
            .setEmail(TEST_EMAIL)
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
    String randomPassword = UUID.randomUUID().toString();

    EmployeeLoginDTO loginDTO = new EmployeeLoginDTO();
    loginDTO.setUsername(TEST_EMAIL);
    loginDTO.setPassword(randomPassword);

    LoginReq expectedRequest =
        LoginReq.newBuilder().setEmail(TEST_EMAIL).setPassword(randomPassword).build();

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
