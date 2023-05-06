package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author MECHREVO
 */
@Data
@TableName(value = "sys_class")
public class SysClass {

    @TableId(type = IdType.AUTO)
    private Integer classId;

    private Integer classBuilder;

    private String className;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date buildTime;

}
