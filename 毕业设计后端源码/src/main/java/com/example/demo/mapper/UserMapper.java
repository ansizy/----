package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * select 查询
 * insert 插入
 * update 更新
 * delete 删除
 *
 *
 * @author MECHREVO
 */
//@Mapper

@Repository
public interface UserMapper extends BaseMapper<User> {

//    /**
//     * 查询所有用户
//     * @return 所用用户列表
//     */
//    @Select("select * from sys_user")
//    List<User> findAll();
//
//    /**
//     * 插入一个用户
//     * @param user
//     * @return
//     */
//    Integer insert(User user);
//
//    /**
//     * 更新
//     * @param user
//     * @return
//     */
//    int update(User user);
//
//    /***
//     * 分页查询
//     * @param pageNum
//     * @param pageSize
//     * @param userName
//     * @return
//     */
//    @Select("select * from sys_user limit #{pageNum},#{pageSize}")
//    List<User> selectPage(Integer pageNum, Integer pageSize);
//
//    /**
//     *
//     * 查询用户总数
//     * @return 用户总数
//     */
//    @Select("select count(*) from sys_user")
//    Integer selectTotal();

    @Select("select * from class_student\n" +
            "left join sys_user su on class_student.student_id = su.user_id\n" +
            "where class_id = #{classId};")
    List<User> MyListByClassId(Integer classId);
}
