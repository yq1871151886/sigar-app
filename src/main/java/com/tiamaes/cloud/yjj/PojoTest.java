package com.tiamaes.cloud.yjj;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/2/24 14:13
 */
public class PojoTest {


    public static void main(String[] args) {
        ArrayList<UserPOJO> list = new ArrayList<>();
        list.add(new UserPOJO("管理人员", 20));
        list.add(new UserPOJO("市场人员", 18));
        list.add(new UserPOJO("开发人员", 22));
        list.add(new UserPOJO("其他人员", 22));
        gett(list);
        /*for (int i=0;i<list.size();i++) {
            Double aDouble = Double.valueOf(list.get(i).getNum());
            System.out.println(list.get(i).getName()+"--"+aDouble);
        }*/
       /* Class<UserPOJO> userPOJOClass = UserPOJO.class;
        Field[] declaredFields = userPOJOClass.getDeclaredFields();
        for (int i = 0;i<declaredFields.length;i++){
            String name = declaredFields[i].getName();
            System.out.println(name);
        }*/
        /*String nowymd = "20210224";
        String s2 = String.valueOf(13);
        String cardOfferData = nowymd + leftAddZero(s2, 8 - s2.length());
        String s = new BigInteger(cardOfferData.getBytes()).toString(16);
        System.out.println(s);
        String s1 = new BigInteger(s, 16).toString(10);
        System.out.println(s1);*/


        String s1 = new BigInteger("194").toString(16);
        System.out.println(s1);
    }
    public static String leftAddZero(String str, int num) {
        if (null == str){
            str = "";
        }
        StringBuilder data = new StringBuilder(str);
        for (int i = 0;i <num;i++){
            data.insert(0,"0");
        }
        return data.toString();
    }

    public static <T> void gett(List<T> ts){
        Class<?> aClass = ts.get(0).getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i=0;i<declaredFields.length;i++){
            System.out.println(declaredFields[i].getName());
        }

    }
}
