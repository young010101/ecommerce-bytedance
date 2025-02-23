package com.sky.controller.client;

import com.sky.constant.PasswordConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.protos.LoginReq;
import com.sky.protos.LoginResp;
import com.sky.protos.RegisterResp;
import com.sky.protos.RegisterReq;
import com.sky.protos.UserService;
import com.sky.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@Slf4j
@Tag(name = "gRPC user login")
public class ClientUserController {

  // @DubboReference
  @Autowired
  private UserService userService;

  /**
   * 注册
   *
   * @param employeeDTO 注册
   * @return 用户id
   */
  @PostMapping("/register")
  public Result<Integer> register(@RequestBody EmployeeDTO employeeDTO) {
    RegisterReq request = RegisterReq.newBuilder().setEmail(employeeDTO.getUsername())
        .setPassword(PasswordConstant.DEFAULT_PASSWORD).build();
    RegisterResp resp = userService.register(request);
    return Result.success(resp.getUserId());
  }

  /**
   * 登录
   *
   * @param employeeLoginDTO 登录请求
   * @return 用户id
   */
  @PostMapping("/login")
  public Result<Integer> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
    LoginReq request = LoginReq.newBuilder().setEmail(employeeLoginDTO.getUsername())
        .setPassword(employeeLoginDTO.getPassword()).build();
    LoginResp resp = userService.login(request);
    return Result.success(resp.getUserId());
  }
}
