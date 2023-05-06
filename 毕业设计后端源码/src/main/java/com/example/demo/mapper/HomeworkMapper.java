package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.controller.dto.TeacherHomeworkDTO;
import com.example.demo.entity.ClassHomework;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MECHREVO
 */
@Repository
public interface HomeworkMapper extends BaseMapper<ClassHomework> {

    /**
     * 分页查询
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return 分页数据
     */
    @Select("select *\n" +
            "from class_homework\n" +
            "left join sys_class sc on class_homework.class_id = sc.class_id\n" +
            "where homework_builder = #{userId}\n" +
            "limit #{pageNum},#{pageSize}")
    List<TeacherHomeworkDTO> MySelectPage(Integer userId, Integer pageNum, Integer pageSize);

    @Select("select count(*) from class_homework where homework_builder = #{userId}")
    Integer selectTotal(Integer userId);

}
