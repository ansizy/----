package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.ClassDTO;
import com.example.demo.entity.ClassHomework;
import com.example.demo.entity.SysClass;
import com.example.demo.entity.User;
import com.example.demo.mapper.SysClassMapper;
import com.example.demo.mapper.UtileMapper;
import com.example.demo.service.SysClassService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.HomeworkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MECHREVO
 */

@RestController
@RequestMapping("/class")
public class ClassController {

    @Resource
    private SysClassMapper classMapper;

    @Resource
    private SysClassService classService;

    @Resource
    private HomeworkService homeworkService;

    @Resource
    private UtileMapper utileMapper;

    @Resource
    private UserService userService;

    @GetMapping("/page")
    public Result getClassInfo(@RequestParam Integer studentId){
        List<ClassDTO> dtos = classMapper.getClassById(studentId);
        return Result.success(dtos);

    }

    @GetMapping("/search")
    public Result searchClass(@RequestParam Integer classId){

        List<ClassDTO> info = classMapper.getClassInfo(classId);
        if(info.size() == 0){
            return Result.error("400","未找到此班级");
        }else {
            return Result.success(info);
        }

    }

    @GetMapping("/homework/info")
    public Result getHomeworkInfo(@RequestParam Integer classId){
        QueryWrapper<ClassHomework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_id",classId);
        List<ClassHomework> homeworks = homeworkService.list(queryWrapper);
        return Result.success(homeworks);
    }

    @GetMapping("/join")
    public Result joinClass(@RequestParam Integer classId,
                            @RequestParam Integer studentId){

        Integer res = utileMapper.getById(classId, studentId);
        if(res == 1){
            return Result.error("400","已在此班级中");
        }
        if(res == 0){
            int i = utileMapper.insertById(classId, studentId);
            if(i == 1){
                return Result.success();
            }else {
                return Result.error("400","加入班级失败");
            }
        }
        return Result.error("400","系统错误");
    }

    @GetMapping("/list")
    public Result getClassList(@RequestParam Integer userId,
                               @RequestParam Integer pageNum,
                               @RequestParam Integer pageSize){
        QueryWrapper<SysClass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_builder",userId);

        IPage<SysClass> page = new Page<>(pageNum,pageSize);
        IPage<SysClass> iPage = classService.page(page, queryWrapper);

        return Result.success(iPage);
    }

    @GetMapping("/studentlist")
    public Result getStudentListByClassId(@RequestParam Integer classId){
        List<User> userList = userService.MyGetListByClassId(classId);
        return Result.success(userList);
    }

    @GetMapping("/teacher/addclass")
    public Result addNewClass(@RequestParam Integer userId,
                              @RequestParam String className){
        SysClass sysClass = new SysClass();
        sysClass.setClassBuilder(userId);
        sysClass.setClassName(className);
        sysClass.setBuildTime(new Date());

        boolean save = classService.save(sysClass);
        if(save){
            return Result.success();
        }
        else {
            return Result.error("401","新建班级失败");
        }

    }

    @GetMapping("/teacher/search")
    public Result searchClass(@RequestParam Integer userId,
                              @RequestParam Integer classId){
        QueryWrapper<SysClass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_builder",userId);
        queryWrapper.eq("class_id",classId);
        List<SysClass> list = classService.list(queryWrapper);

        if(list.size() == 0)
        {
            return Result.error("401","您不是此班级的创建者,无法查看");
        }
        return Result.success(list);
    }

}
