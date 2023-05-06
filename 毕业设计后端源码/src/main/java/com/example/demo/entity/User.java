package com.example.demo.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

/**
 * @author ansizy
 */
@Data
@TableName(value = "sys_user")
@ToString
public class User {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPassword;

    private Integer userRole;

    private String userEmile;

    private String userSchool;

    private String userPhone;

}
