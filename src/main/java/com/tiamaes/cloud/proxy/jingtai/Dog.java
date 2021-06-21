package com.tiamaes.cloud.proxy.jingtai;

/**
 * @description:
 * @author: yangqigong
 * @createDate: 2021/6/21
 * @version: 1.0
 */
public class Dog implements Animal {

    @Override
    public void skill() {
        System.out.println("被代理对象Dog执行！！！");
    }
}
