package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * get 查询
 * edit 更新
 * remove 删除
 * add 新增
 *
 *
 * @author MECHREVO
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;

//    public boolean saveUser(User user){
//
//        if(user.getUserId() == null){
//            userMapper.insert(user);
//        }
//        else {
//            userMapper.update(user);
//        }
//
//        return false;
//    }

//    public List<User> getAllUser(){
//        List<User> users = userMapper.findAll();
//        return users;
//    }
//
//    public List<User> getPage(Integer pageNum, Integer pageSize) {
//        List<User> users = userMapper.selectPage(pageNum, pageSize);
//        return users;
//    }
//
//    public Integer getTotal() {
//        return userMapper.selectTotal();
//    }


    public List<User> MyGetListByClassId(Integer classId){
        return userMapper.MyListByClassId(classId);
    }
}
