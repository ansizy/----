package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.controller.dto.StudentHomeworkDTO;
import com.example.demo.controller.dto.TeacherHomeworkDTO;
import com.example.demo.entity.ClassHomework;

import com.example.demo.entity.StudentHomework;
import com.example.demo.mapper.HomeworkMapper;
import com.example.demo.mapper.StudentHomeworkMapper;
import com.example.demo.service.HomeworkImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author MECHREVO
 */
@Service
public class HomeworkService extends ServiceImpl<HomeworkMapper, ClassHomework> implements HomeworkImpl {


    @Resource
    private StudentHomeworkMapper studentHomeworkMapper;

    @Resource
    private HomeworkMapper homeworkMapper;

    @Override
    public List<StudentHomeworkDTO> getHomeworkById(Integer studentId) {
        List<StudentHomeworkDTO> homeworks = studentHomeworkMapper.selectHomeworkById(studentId);
        return homeworks;
    }

    @Override
    public StudentHomeworkDTO MyGetOneByHomeworkId(Integer homeworkId) {
        return studentHomeworkMapper.MySelectOneByHomeworkId(homeworkId);
    }

    @Override
    public List<TeacherHomeworkDTO> getPage(Integer userId, Integer pageNum, Integer pageSize) {

        List<TeacherHomeworkDTO> page = homeworkMapper.MySelectPage(userId, pageNum, pageSize);
        return page;
    }

    @Override
    public Integer getTotal(Integer userId) {
        Integer total = homeworkMapper.selectTotal(userId);
        return total;
    }

}
