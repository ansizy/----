package com.example.demo.service;

import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.User;

/**
 *
 * 登录服务接口
 * @author MECHREVO
 */
public interface LoginImpl {

    /**
     * 登录功能
     * @param user
     * @return
     */
    UserDTO login(UserDTO user);

    /**
     * 注册
     * @param user
     * @return
     */
    Integer register(User user);
}
