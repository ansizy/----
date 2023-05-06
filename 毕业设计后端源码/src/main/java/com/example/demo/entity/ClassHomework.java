package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 记录班级作业，实体类
 * @author MECHREVO
 */
@Data
@TableName(value = "class_homework")

public class ClassHomework {

    @TableId(type = IdType.AUTO)
    private Integer homeworkId;
    
    private String homeworkName;
    
    private Integer homeworkBuilder;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date homeworkTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private Integer classId;

    private String homeworkDemand;

}
