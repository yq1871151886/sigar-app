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
        Callable<String> callable1 = new ThreadBean("线程1执行,7000");
        Callable<String> callable2 = new ThreadBean("线程2执行,5000");
        Callable<String> callable3 = new ThreadBean("线程3执行,3000");
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit1 = executorService.submit(callable1);
        Future<String> submit2 = executorService.submit(callable2);
        Future<String> submit3 = executorService.submit(callable3);
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
