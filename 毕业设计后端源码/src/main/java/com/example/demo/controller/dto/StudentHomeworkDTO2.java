package com.example.demo.controller.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author MECHREVO
 */
@Data
public class StudentHomeworkDTO2 {

    private Integer studentId;

    private String userName;

    private Integer homeworkId;

    private String homeworkName;

    private String homeworkEssay;

    private Date submitTime;

    private Integer homeworkScore;

    private Integer teacherRate;


}
