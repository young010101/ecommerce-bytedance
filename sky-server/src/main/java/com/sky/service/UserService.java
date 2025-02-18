package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

public interface UserService {
    /**
     * Processes WeChat user login.
     *
     * @param userLoginDTO the login data transfer object containing user
     *                     information
     * @return user login response containing session information
     */
    User wxLogin(UserLoginDTO userLoginDTO);

    /**
     * Logs out the user.
     */
    void logout();
}
