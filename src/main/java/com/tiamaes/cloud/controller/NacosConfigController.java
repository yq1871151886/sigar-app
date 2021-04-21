package com.tiamaes.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/12/23 11:58
 */
@RestController
@RefreshScope
public class NacosConfigController {

    @Value("${testName}")
    private String testName;


    @GetMapping("test")
    public String getTest(){
        return testName;
    }


}
