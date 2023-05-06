package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.demo.entity.SelfTest;

import com.example.demo.mapper.SelfTestMapper;

import com.example.demo.service.SelfTestImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MECHREVO
 */
@Service
public class SelfTestService extends ServiceImpl<SelfTestMapper, SelfTest> implements SelfTestImpl {

    @Resource
    private SelfTestMapper selfTestMapper;

    @Override
    public List<SelfTest> getListById(Integer studentId) {

        List<SelfTest> testList = selfTestMapper.selectListById(studentId);

        return testList;
    }
}
