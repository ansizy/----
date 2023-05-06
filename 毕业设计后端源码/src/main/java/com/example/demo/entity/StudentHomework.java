package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.util.Date;

/**
 *
 *
 * @author MECHREVO
 */
@Data
@TableName(value = "student_homework")
public class StudentHomework {

    private Integer studentId;

    private Integer homeworkId;

    private String homeworkEssay;

    private Date submitTime;

    private Integer homeworkScore;

    private Integer teacherRate;

}
