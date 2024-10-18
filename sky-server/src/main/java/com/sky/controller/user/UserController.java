package com.sky.controller.user;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user/user")
@Api(tags = "C端-用户接口")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserLoginVO> login(
            @ApiParam(value = "用户授权码", required = true) @RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录请求：{}", userLoginDTO);
        User user = userService.login(userLoginDTO);

        // 登录成功后，生成jwt令牌
        Map<String, Object> claim = new HashMap<>();
        claim.put(JwtClaimsConstant.USER_ID, user.getId());

        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claim);

        UserLoginVO userLoginVO = UserLoginVO.builder()
               .id(user.getId())
               .openid(user.getOpenid())
               .token(token)
               .build();

        return Result.success(userLoginVO);
    }

    @PostMapping("/logout")
    @ApiOperation("用户退出")
    public Result<String> logout() {
        log.info("用户退出");
        userService.logout();
        return Result.success("退出成功");
    }
}
