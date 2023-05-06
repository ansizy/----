package com.example.demo.controller.dto;

import lombok.Data;

/**
 * @author MECHREVO
 */
@Data
public class SelfTestDTO {
    private Integer studentId;
    private Integer testId;

    private String testTitle;

    private String testEssay;
    private Integer testScore;
}
