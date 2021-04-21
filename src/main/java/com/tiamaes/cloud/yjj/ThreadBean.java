package com.tiamaes.cloud.yjj;

import lombok.Data;

import java.util.concurrent.Callable;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/4/21 11:31
 */
@Data
public class ThreadBean implements Callable<String> {
    private String a;
    @Override
    public String call(){
        String[] split = a.split(",");
        String s = split[1];
        try {
            Thread.sleep(Long.valueOf(s));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a;
    }

    public ThreadBean(String a) {
        this.a = a;
    }
}
