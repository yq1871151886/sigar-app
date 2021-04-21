package com.tiamaes.cloud.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/12/24 9:21
 */
@Configuration
public class ThreadPoolConfig {


    @Bean("taskExecutor")
    public ExecutorService taskExecutor(){
        ThreadFactory threadName = new ThreadFactoryBuilder().setNameFormat("consume-pool-%d").build();
        return new ThreadPoolExecutor(8,24,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(100),threadName,new ThreadPoolExecutor.AbortPolicy());
    }

}
