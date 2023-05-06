package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.LoginImpl;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录服务实现
 * @author MECHREVO
 */
@Service
public class ILoginService implements LoginImpl {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Override
    public UserDTO login(UserDTO userDTO) {

        //输入电话
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone",userDTO.getUsername());
        queryWrapper.eq("user_password",userDTO.getPassword());

        User one = userMapper.selectOne(queryWrapper);

        //电话不匹配，判断邮箱
        if(one == null){
            queryWrapper.clear();
            queryWrapper.eq("user_emile",userDTO.getUsername());
            queryWrapper.eq("user_password",userDTO.getPassword());
            one = userMapper.selectOne(queryWrapper);
        }


        if(one != null){
            BeanUtil.copyProperties(one,userDTO,true);
            //设置Token
            String token = TokenUtils.genToken(one.getUserId().toString(), one.getUserPassword());
            userDTO.setToken(token);
            return userDTO;
        }
        else {
            return null;
        }


    }

    @Override
    public Integer register(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //判断邮箱是否被注册
        queryWrapper.eq("user_emile",user.getUserEmile());

        User one = userMapper.selectOne(queryWrapper);
        //邮箱未被注册
        if(one == null){
            queryWrapper.clear();
            queryWrapper.eq("user_phone",user.getUserPhone());

            one = userMapper.selectOne(queryWrapper);

            //电话未被注册
            if(one == null){
                //插入数据
                userMapper.insert(user);
                //注册成功
                return 1;
            }else{
                //电话被注册
                return 3;
            }

        }else {
            //邮箱以及被注册
            return 2;
        }

    }
}



















