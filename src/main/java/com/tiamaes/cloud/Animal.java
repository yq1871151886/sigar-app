package com.tiamaes.cloud;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/4/12 9:04
 */
class Animal {
    Animal(){
        System.out.println("Animal");
    }
}
    class Dog extends Animal{
        Dog(){
            System.out.println("Dog");
        }


    public static void main(String[] args) {
        Dog dog = new Dog();
    }

    }
