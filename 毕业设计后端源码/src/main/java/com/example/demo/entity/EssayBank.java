package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author MECHREVO
 */
@Data
@TableName(value = "essay_bank")
public class EssayBank {

    private Integer testId;
    private String essayTitle;
    private String essayRequire;
}
