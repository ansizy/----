package com.example.demo.controller.dto;

import lombok.Data;

/**
 *
 * @author MECHREVO
 */
@Data
public class EssayBankDTO {
    private Integer studentId;
    private Integer testId;
    private String testEssay;
    private Integer testScore;
    private String essayTitle;
}
