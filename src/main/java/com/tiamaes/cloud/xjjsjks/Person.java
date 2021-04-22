package com.tiamaes.cloud.xjjsjks;

public class Person {

    String name;
    int age;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Person person = new Person("Peter",17);
        System.out.println(person.name + "is" + person.age + "years old!");
    }
}
