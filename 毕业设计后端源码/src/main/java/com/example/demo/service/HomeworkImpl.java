package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.StudentHomeworkDTO;
import com.example.demo.controller.dto.TeacherHomeworkDTO;
import com.example.demo.entity.ClassHomework;
import com.example.demo.entity.StudentHomework;

import java.util.List;

/**
 * @author MECHREVO
 */
public interface HomeworkImpl extends IService<ClassHomework> {
    /**
     * sfa
     * @param studentId
     * @return
     */
    List<StudentHomeworkDTO> getHomeworkById(Integer studentId);

    StudentHomeworkDTO MyGetOneByHomeworkId(Integer homeworkId);

    List<TeacherHomeworkDTO> getPage(Integer userId, Integer pageNum, Integer pageSize);

    Integer getTotal(Integer userId);
}
