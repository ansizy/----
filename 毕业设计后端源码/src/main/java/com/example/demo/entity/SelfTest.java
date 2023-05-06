package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author MECHREVO
 */
@Data
@TableName(value = "self_test")
public class SelfTest {

    private Integer studentId;

    private Integer testId;

    private String testEssay;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date submitTime;

    private Integer testScore;

    private String essayTitle;
}
