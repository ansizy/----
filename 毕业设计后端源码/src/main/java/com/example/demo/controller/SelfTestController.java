package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.SelfTestDTO;
import com.example.demo.entity.SelfTest;
import com.example.demo.entity.User;
import com.example.demo.mapper.EssayBankMapper;
import com.example.demo.service.impl.SelfTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author MECHREVO
 */
@RestController
@RequestMapping("/student")
public class SelfTestController {

    @Resource
    private SelfTestService selfTestService;

    @Resource
    private EssayBankMapper essayBankMapper;

    /**
     * 获取自测列表
     * @param studentId
     * @return
     */
    @GetMapping("/selflist")
    public Result getSelfList(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam Integer studentId){


        IPage<SelfTest> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SelfTest> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("student_id", studentId);
        IPage<SelfTest> iPage = selfTestService.page(page, queryWrapper);

        return Result.success(iPage);
    }


    @GetMapping("/delete")
    public Result deleteOneTest(@RequestParam Integer testId,
                                @RequestParam Integer studentId){

        QueryWrapper<SelfTest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        queryWrapper.eq("test_id",testId);
        boolean res = selfTestService.remove(queryWrapper);
        if(res){
            return Result.success();
        }
        return Result.error();
    }
}
