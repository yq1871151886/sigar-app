package com.tiamaes.cloud.test;

import com.google.common.collect.Maps;
import com.tiamaes.cloud.yjj.ThreadBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/4/21 10:37
 */
public class RunableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit1 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String a = "线程1执行,7000";
                String[] split = a.split(",");
                String s = split[1];
                try {
                    Thread.sleep(Long.valueOf(s));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return a;
            }
        });
        Future<String> submit2 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String b = "线程1执行,5000";
                String[] split = b.split(",");
                String s = split[1];
                try {
                    Thread.sleep(Long.valueOf(s));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return b;
            }
        });
        Future<String> submit3 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String c = "线程1执行,3000";
                String[] split = c.split(",");
                String s = split[1];
                try {
                    Thread.sleep(Long.valueOf(s));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return c;
            }
        });

        boolean flag = true;
        while (flag){
            if (submit1.isDone() && submit2.isDone() && submit3.isDone()){
                System.out.println(submit1.get()+submit2.get()+submit3.get());
                flag = false;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }















}
