package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.controller.dto.SelfTestDTO;
import com.example.demo.entity.SelfTest;

import java.util.List;

/**
 * @author MECHREVO
 */
public interface SelfTestImpl extends IService<SelfTest> {
    /**
     * 获取自测列表
     * @param studentId
     * @return
     */
    List<SelfTest> getListById(Integer studentId);

}
