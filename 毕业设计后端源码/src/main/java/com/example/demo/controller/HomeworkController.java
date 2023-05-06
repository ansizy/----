package com.example.demo.controller;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.StudentHomeworkDTO;
import com.example.demo.controller.dto.StudentHomeworkDTO2;
import com.example.demo.controller.dto.TeacherHomeworkDTO;
import com.example.demo.entity.ClassHomework;
import com.example.demo.entity.StudentHomework;
import com.example.demo.entity.User;
import com.example.demo.mapper.StudentHomeworkMapper;
import com.example.demo.mapper.UtileMapper;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.HomeworkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 *
 * @author MECHREVO
 */
@RestController
public class HomeworkController {
    @Resource
    private HomeworkService homeworkService;

    @Resource
    private StudentHomeworkMapper studentHomeworkMapper;

    @Resource
    private UtileMapper utileMapper;

    @Resource
    private UserService userService;

    /**
     * 通过userId查询用户的所有作业列表
     * @param userId
     * @return
     */
    @GetMapping("/student/homeworklist")
    public Result getHomeworkList(@RequestParam Integer userId){
        List<StudentHomeworkDTO> homeworks = homeworkService.getHomeworkById(userId);
        return Result.success(homeworks);
    }

    @PostMapping("/homework/submit")
    public Result submitHomework(@RequestBody StudentHomework studentHomework){

        /**
         * 对象判空
         * 评分
         * 完善对象
         * 插入数据库
         */

        if(studentHomework.getHomeworkId() == null){
            return Result.error("401","作业ID为空");
        }
        if(studentHomework.getStudentId() == null){
            return Result.error("401","学生ID为空");
        }
        QueryWrapper<StudentHomework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentHomework.getStudentId());
        queryWrapper.eq("homework_id",studentHomework.getHomeworkId());
        StudentHomework one = studentHomeworkMapper.selectOne(queryWrapper);
        if(one != null){
            return Result.error("401","已经提交过该作业");
        }

        /**
         * 评分
         */
        int c = RandomUtil.randomInt(0, 15);
        studentHomework.setHomeworkScore(c);

        //获取时间
        studentHomework.setSubmitTime(new Date());

        studentHomeworkMapper.insert(studentHomework);

        return Result.error();
    }

    @GetMapping("/student/homework/search")
    public Result search(@RequestParam Integer studentId,@RequestParam Integer homeworkId){

        if(homeworkId == null){
            return Result.error("400","搜索ID为空");
        }

        //先判断作业是否存在
        ClassHomework homework = homeworkService.getById(homeworkId);
        if (homework == null) {
            return Result.error("400","该作业不存在");
        }
        Integer classId = homework.getClassId();
        Integer res = utileMapper.getById(classId, studentId);
        if(res == 0){
            return Result.error("400","您目前不在作业班级中，请先加入相关班级");
        }
        else {
            StudentHomeworkDTO data = homeworkService.MyGetOneByHomeworkId(homeworkId);
            QueryWrapper<StudentHomework> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("homework_id",homeworkId);
            queryWrapper.eq("student_id", studentId);
            StudentHomework one = studentHomeworkMapper.selectOne(queryWrapper);
            data.setHomeworkScore(one.getHomeworkScore());
            List<StudentHomeworkDTO> list = new ArrayList<>();
            list.add(data);
            return Result.success(list);
        }

    }

    /**
     * 添加新作业
     */
    @PostMapping("/teacher/addhomework")
    public Result AddNewHomework(@RequestBody ClassHomework classHomework){

        //数据校验
        User user = userService.getById(classHomework.getHomeworkBuilder());
        if(user.getUserRole() != 2){
            return Result.error("401","用户角色错误");
        }
        Date date = classHomework.getHomeworkTime();
        boolean after = date.after(classHomework.getEndTime());
        if(after){
            return Result.error("401","时间设置有问题，请仔细检查");
        }
        //插入数据
        boolean save = homeworkService.save(classHomework);
        if(save){
            return Result.success();
        }else {
            return Result.error("401","添加作业失败。");
        }

    }

    @GetMapping("/teacher/homework/list")
    public Result getHomeworkList(@RequestParam Integer userId,
                                  @RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize){
        pageNum = (pageNum - 1) * pageSize;

        List<TeacherHomeworkDTO> page = homeworkService.getPage(userId, pageNum, pageSize);

        Integer total = homeworkService.getTotal(userId);
        Map<String, Object> res = new HashMap<>();
        res.put("data",page);
        res.put("total",total);

        return Result.success(res);

    }

    @GetMapping("/teacher/homework/view")
    public Result viewHomework(@RequestParam Integer homeworkId){
//        QueryWrapper<StudentHomework> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("homework_id",homeworkId);
//        List<StudentHomework> list = studentHomeworkMapper.selectList(queryWrapper);

        List<StudentHomeworkDTO2> list = studentHomeworkMapper.mySelectByHomeworkId(homeworkId);
        return Result.success(list);
    }

    @GetMapping("/teacher/pigai")
    public Result pigaiHomework(@RequestParam Integer studentId,
                                @RequestParam Integer homeworkId,
                                @RequestParam Integer teacherRate){
        UpdateWrapper<StudentHomework> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("student_id",studentId).eq("homework_id",homeworkId)
                        .set("teacher_rate",teacherRate);
        studentHomeworkMapper.update(null, updateWrapper);

        return Result.success();
    }

    @GetMapping("/teacher/search")
    public Result searchTeacher(@RequestParam Integer userId,
                                @RequestParam Integer homeworkId){
        //TeacherHomeworkDTO
        TeacherHomeworkDTO dto = utileMapper.mySelectOneHomework(userId, homeworkId);
        if(dto == null){
            return Result.error();
        }else {
            return Result.success(dto);
        }
    }
}
