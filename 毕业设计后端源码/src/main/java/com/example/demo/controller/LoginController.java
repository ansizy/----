package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录注册接口
 * @author MECHREVO
 */
@RestController
public class LoginController {

    @Resource
    private ILoginService loginService;

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){

        /**
         * username : 电话号码、邮箱。
         * 真正的userName不可以用来登录 userId 也不可以
         */
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"账号或者密码为空");
        }

        UserDTO user = loginService.login(userDTO);

        if(user == null){
            return Result.error(Constants.CODE_400,"账号或者密码错误");
        }
        else {

            UserDTO dto = new UserDTO();
            //成功，只返回数据，一个参数就够了
            return Result.success(user);

        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){

        String username= user.getUserName();
        String password = user.getUserPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"用户名或密码不能为空！");
        }
        Integer userRole = user.getUserRole();
        if(userRole == null){
            return Result.error(Constants.CODE_400,"用户角色不能为空！");
        }


        Integer res = loginService.register(user);

        if(res == 1){
            return Result.success();
        }
        else if(res == 2){
            return Result.error(Constants.CODE_400,"邮箱已被注册");
        }
        else if(res == 3){
            return Result.error(Constants.CODE_400,"电话已被注册");
        }
        else {
            return Result.error(Constants.CODE_400,"未知错误");
        }

    }

    /**
     * 返回个人信息
     * @param userId
     * @return user
     */
    @GetMapping("/person")
    public Result getPersonInfo(@RequestParam Integer userId){

        User user = userService.getById(userId);
        return Result.success(user);
    }

    @PostMapping("/person/edit")
    public Result editPersonInfo(@RequestBody User user){

        boolean res = userService.updateById(user);
        if(res){
            return Result.success();
        }
        else {
            return Result.error();
        }

    }
}
