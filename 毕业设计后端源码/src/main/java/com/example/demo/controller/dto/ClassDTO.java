package com.example.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author MECHREVO
 */
@Data
public class ClassDTO {

    private Integer classId;

    private String userName;

    private String className;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date buildTime;
}
