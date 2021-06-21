package com.tiamaes.cloud.proxy.dongtai;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: yangqigong
 * @createDate: 2021/6/21
 * @version: 1.0
 */
public class PyyProxy {
    private Object object;

    public PyyProxy(Object object) {
        this.object = object;
    }

    public Object getObjectProxy(){
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理对象执行");
                        Object invoke = method.invoke(object, args);
                        return invoke;
                    }
                }
        );
    }




}
