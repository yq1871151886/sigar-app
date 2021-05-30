package com.tiamaes.cloud.dmd;

import java.util.ArrayList;
import java.util.List;

public class ListTest {


    private static List<String> s2 = new ArrayList<>();



    public static void main(String[] args) {
        List<String> s1 = new ArrayList<>();
        s1.add("0");
        s1.add("2");
        s1.add("7");
        s1.add("6");
        s1.add("3");
        s1.add("3");
        s1.add("3");
        s2 = s1.subList(1, 5);

        for (String string : s2) {
            System.out.println(string);
        }


    }


}
