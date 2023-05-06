package com.example.demo.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MECHREVO
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> index(){
        List<User> userList = userService.list();
        return userList;
    }

    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping
    public boolean save(@RequestBody User user){
        if(user.getUserPassword() == null){
            user.setUserPassword("123");
        }
        user.setUserRole(1);
        boolean res = userService.save(user);

        return res;
    }

    @GetMapping("/page")
    public  IPage<User> findPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize){
//        pageNum = (pageNum - 1) * pageSize;
//        List<User> userList = userService.getPage(pageNum, pageSize);
//
//        Integer total = userService.getTotal();
//
//        Map<String, Object> res = new HashMap<>();
//        res.put("data",userList);
//        res.put("total",total);
//
//        return res;

        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        return userService.page(page,queryWrapper);
    }

    /**
     * 导出用户名单Excel
     * @param response
     * @throws Exception
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        //查出所有数据
        List<User> list = userService.list();

        ExcelWriter writer = ExcelUtil.getWriter(true);

        //自定义标题别名
        writer.addHeaderAlias("userId","用户ID");
        writer.addHeaderAlias("userName","用户名");
        writer.addHeaderAlias("userSchool","学校");
        writer.addHeaderAlias("userEmile","邮箱");
        writer.addHeaderAlias("userPhone","电话");

        //将list写出到Excel
        writer.write(list,true);

        //设置浏览器响应模式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");

        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();

    }

    /**
     * 文件导入用户
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public void imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> list = reader.readAll(User.class);
        System.out.print(list);
    }





}
