package com.example.demo.mapper;

import com.example.demo.controller.dto.SelfTestDTO;
import com.example.demo.controller.dto.TeacherHomeworkDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MECHREVO
 */
@Repository
public interface UtileMapper {

    /**
     * 查找数据是否有次记录
     * @param classId
     * @param studentId
     * @return
     */
    @Select("select count(*) from class_student where class_id = #{classId} and student_id = #{studentId}")
    Integer getById(Integer classId, Integer studentId);

    @Insert("insert into class_student(class_id,student_id) values (#{classId},#{studentId})")
    int insertById(@Param("classId") Integer classId,
                       @Param("studentId") Integer studentId);

    @Select("select * from class_homework\n" +
            "left join sys_class sc on class_homework.class_id = sc.class_id\n" +
            "where homework_id = #{homeworkId} and homework_builder = #{userId}")
    TeacherHomeworkDTO mySelectOneHomework(Integer userId,Integer homeworkId);

}
