package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.controller.dto.ClassDTO;
import com.example.demo.entity.SysClass;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MECHREVO
 */
public interface SysClassMapper extends BaseMapper<SysClass> {

    /**
     * select sc.class_id,class_name,user_name,build_time from class_student
     *     left join sys_class sc on class_student.class_id = sc.class_id
     *     left join sys_user su on sc.class_builder = su.user_id
     *     where student_id = 1
     */

    /**
     *由学生id 返回学生参加班级信息
     * @param studentId
     * @return 学生参加班级信息
     */
    @Select("select sc.class_id,class_name,user_name,build_time from class_student\n" +
            "    left join sys_class sc on class_student.class_id = sc.class_id\n" +
            "    left join sys_user su on sc.class_builder = su.user_id\n" +
            "where student_id = #{studentId}")
    List<ClassDTO> getClassById(Integer studentId);

    /**
     * 由班级Id 返回班级信息
     * @param classId
     * @return 班级信息
     */
    @Select("select class_id,class_name,user_name,build_time from sys_class\n" +
            "    left join sys_user su on sys_class.class_builder = su.user_id\n" +
            "where class_id = #{classId}")
    List<ClassDTO> getClassInfo(Integer classId);

}
