package com.example.demo.controller.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author MECHREVO
 */
@Data
public class TeacherHomeworkDTO {

    private Integer homeworkId;

    private String homeworkName;

    private Integer homeworkBuilder;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date homeworkTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private Integer classId;

    private String className;

    private String homeworkDemand;
}
