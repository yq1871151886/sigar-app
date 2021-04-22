package com.tiamaes.cloud.xjjsjks;

class Animal {
    public Animal() {
        System.out.println("Animal");
    }
}
class Dog extends Animal{
    public Dog() {
        System.out.println("Dog");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
    }

}
