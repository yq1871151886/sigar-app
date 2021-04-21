package com.tiamaes.cloud.controller;

import com.tiamaes.cloud.beans.FzBigPOJO;
import com.tiamaes.cloud.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/1/11 11:10
 */
@RestController
@RequestMapping("yd")
public class ListController {


    @Autowired
    private ListService listService;


    @GetMapping("getZpTrend")
    public List<FzBigPOJO> getZpTrend(){
        List<FzBigPOJO> zpTrend = listService.getZpTrend();
        return zpTrend;
    }

}
