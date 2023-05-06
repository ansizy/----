package com.example.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author MECHREVO
 */
@Data
public class StudentHomeworkDTO {
    /**
     * select sh.homework_id,homework_name,homework_time,end_time,homework_demand, class_name,homework_score,submit_time
     * from class_homework
     * left join sys_class sc on class_homework.class_id = sc.class_id
     * left join class_student cs on class_homework.class_id = cs.class_id
     * left join student_homework sh on class_homework.homework_id = sh.homework_id
     * where cs.student_id = 1;
     */

    //class_homework表的内容 , sys_class表的内容 , class_student表的内容

    private Integer homeworkId;

    private String homeworkName;

    private String homeworkDemand;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    private Integer classId;

    private String className;

    private Integer homeworkScore;



}
