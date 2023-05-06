package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.controller.dto.StudentHomeworkDTO;
import com.example.demo.controller.dto.StudentHomeworkDTO2;
import com.example.demo.entity.StudentHomework;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *   select 查询
 *   insert 插入
 *   update 更新
 *   delete 删除
 * @author MECHREVO
 */

@Repository
public interface StudentHomeworkMapper extends BaseMapper<StudentHomework> {

    /**
     * select homework_id,homework_name,homework_demand,class_name,end_time from class_homework\n" +
     *             "left join sys_class sc on class_homework.class_id = sc.class_id\n" +
     *             "left join class_student cs on class_homework.class_id = cs.class_id\n" +
     *             "where student_id=
     *
     * 由studentId 查询其所做过的作业列表
     * @param studentId
     * @return
     */
    @Select("select class_homework.homework_id,homework_name,homework_time,end_time,homework_demand, class_name,homework_score,submit_time from class_homework\n" +
            "left join sys_class sc on class_homework.class_id = sc.class_id\n" +
            "left join class_student cs on class_homework.class_id = cs.class_id\n" +
            "left join student_homework sh on class_homework.homework_id = sh.homework_id\n" +
            "where cs.student_id=#{studentId}")
    List<StudentHomeworkDTO> selectHomeworkById(@Param("studentId") Integer studentId);

    /**
     * fad
     * @param homeworkId
     * @return sf
     */
    @Select("select homework_id,homework_name,homework_demand,end_time,class_homework.class_id,class_name from class_homework\n" +
            "left join sys_class sc on class_homework.class_id = sc.class_id\n" +
            "where homework_id = #{homeworkId}")
    StudentHomeworkDTO MySelectOneByHomeworkId(@Param("homeworkId") Integer homeworkId);

    /**
     * 返回作业列表，by homeworkId
     * @param homeworkId
     * @return
     */
    @Select("select * from student_homework\n" +
            "left join sys_user su on student_id = user_id\n" +
            "where homework_id = #{homeworkId}")
    List<StudentHomeworkDTO2> mySelectByHomeworkId(Integer homeworkId);


}
