package com.sky.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeChatProperties weChatProperties;

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {

        String openid = getOpenid(userLoginDTO.getCode());

        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        // check if user exists. if not, insert into database
        User user = userMapper.getByOpenid(openid);
        if (user == null) {
            user = User.builder().openid(openid).createTime(LocalDateTime.now()).build();
            userMapper.insert(user);
        }

//            BeanUtils.copyProperties(user, userLoginVO);

        return user;
    }

    private String getOpenid(String code) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appid", weChatProperties.getAppid());
        paramMap.put("secret", weChatProperties.getSecret());
        paramMap.put("js_code", code);
        paramMap.put("grant_type", "authorization_code");

        // Call the doGet method
        String result = HttpClientUtil.doGet(WX_LOGIN_URL, paramMap);
        // Parse the result json
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getString("openid");
    }

    @Override
    public void logout() {
//        // Get the current user
//        User user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        // Invalidate the user's session
//        SecurityContextHolder.getContext().setAuthentication(null);
//        // Log the user out
//        log.info("User {} has logged out", user.getUsername())
    }

}
