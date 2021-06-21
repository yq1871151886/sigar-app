package com.tiamaes.cloud.proxy.jingtai;

/**
 * @description:
 * @author: yangqigong
 * @createDate: 2021/6/21
 * @version: 1.0
 */
public class DogProxy implements Animal{
    private Animal animal;

    public DogProxy(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void skill() {
        animal.skill();
    }
}
