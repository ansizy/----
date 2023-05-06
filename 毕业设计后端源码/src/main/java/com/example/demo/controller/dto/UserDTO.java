package com.example.demo.controller.dto;

import lombok.Data;

/**
 * 接受前端请求的参数
 * @author MECHREVO
 */
@Data
public class UserDTO {
    private Integer userId;
    private String username;
    private String password;
    private String token;

}
