package com.tiamaes.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/2/24 11:33
 */
@RestController
@RequestMapping("atomic")
public class AtomicController {


    private static AtomicInteger serial = new AtomicInteger();

    @GetMapping("getSerial")
    public String getSerial(){
        int i = serial.addAndGet(1);
        System.out.println(""+i);
        return ""+i;
    }
    @GetMapping("newSerial")
    public void newSerial(){
        serial = new AtomicInteger(1);
        int i = serial.get();
        System.out.println(i);
    }



}
