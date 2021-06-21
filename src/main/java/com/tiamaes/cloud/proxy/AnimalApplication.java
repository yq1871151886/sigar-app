package com.tiamaes.cloud.proxy;

import com.tiamaes.cloud.proxy.jingtai.Dog;
import com.tiamaes.cloud.proxy.jingtai.DogProxy;

import java.sql.SQLOutput;

/**
 * @description:
 * @author: yangqigong
 * @createDate: 2021/6/21
 * @version: 1.0
 */
public class AnimalApplication {

    public static void main(String[] args) {
        DogProxy dogProxy = new DogProxy(new Dog());
        dogProxy.skill();
    }
}
