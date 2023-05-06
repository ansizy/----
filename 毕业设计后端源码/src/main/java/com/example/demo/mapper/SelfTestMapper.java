package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SelfTest;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MECHREVO
 */
@Repository
public interface SelfTestMapper extends BaseMapper<SelfTest> {

    /**
     * 返回学生自测列表
     * @param studentId
     * @return
     */
    @Select("select * from self_test where student_id = #{studentId}")
    List<SelfTest> selectListById(Integer studentId);
}
