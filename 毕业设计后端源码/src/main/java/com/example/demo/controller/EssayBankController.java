package com.example.demo.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.EssayBankDTO;
import com.example.demo.entity.EssayBank;
import com.example.demo.entity.SelfTest;
import com.example.demo.mapper.EssayBankMapper;
import com.example.demo.service.impl.SelfTestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

import static com.example.demo.common.Constants.CODE_400;

/**
 * @author MECHREVO
 */
@RestController
@RequestMapping("/essaybank")
public class EssayBankController {

    @Resource
    private EssayBankMapper essayBankMapper;

    @Resource
    private SelfTestService selfTestService;

    @GetMapping("/all")
    public Result getAll(@RequestParam Integer pageNum,
                         @RequestParam Integer pageSize){
        IPage<EssayBank> page = new Page<>(pageNum,pageSize);
        QueryWrapper<EssayBank> queryWrapper = new QueryWrapper<>();
        IPage<EssayBank> iPage = essayBankMapper.selectPage(page, queryWrapper);

        return Result.success(iPage);
    }

    @PostMapping("/submit")
    public Result submitEssay(@RequestBody SelfTest selfTest){

        /**
         * 评分
         */
        int c = RandomUtil.randomInt(0, 15);
        selfTest.setTestScore(c);

        //获取时间
        selfTest.setSubmitTime(new Date());

        //判断该自测用户是否写作过

        QueryWrapper<SelfTest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",selfTest.getStudentId());
        queryWrapper.eq("test_id",selfTest.getTestId());
        SelfTest one = selfTestService.getOne(queryWrapper);

        if(one == null){
            //如果是第一次写
            boolean save = selfTestService.save(selfTest);
            if(save){
                return Result.success();
            }
        }else {
            //不是第一次写
            UpdateWrapper<SelfTest> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("student_id",selfTest.getStudentId());
            updateWrapper.eq("test_id",selfTest.getTestId());

            boolean update = selfTestService.update(selfTest,updateWrapper);
            if(update){
                return Result.success();
            }
        }


        return Result.error(CODE_400,"提交失败");
    }

}
