package com.tiamaes.cloud.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/4/14 15:05
 */
@Component
@RefreshScope
public class TestScheuled {

    @Value("${testTime}")
    private String testTime;

    @Scheduled(cron = "${testTime}")
    public void testsch(){
        System.out.println(testTime);
    }

    public static void main(String[] args) {
        String property = System.getProperties().getProperty("os.name");
        System.out.println(property);


    }

}
