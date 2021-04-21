package com.tiamaes.cloud.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/1/6 11:37
 */
public class MapTest {

    private  static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) throws JsonProcessingException {
        /*String s = "{\"code\":200,\"data\":{\"pushcount\":{\"feedbackDataSum\":74017,\"pushCount\":179712,\"retrievalDataSum\":132133},\"phoneTop3\":[{\"operator\":\"电信\",\"operatorCount\":246},{\"operator\":\"移动\",\"operatorCount\":546},{\"operator\":\"联通\",\"operatorCount\":1530}],\"areaList\":[{\"calledCityName\":\"三门峡\",\"callednmCount\":\"21_\",\"callednmCounts\":21},{\"calledCityName\":\"信阳\",\"callednmCount\":\"108_\",\"callednmCounts\":108},{\"calledCityName\":\"南阳\",\"callednmCount\":\"150_\",\"callednmCounts\":150},{\"calledCityName\":\"周口\",\"callednmCount\":\"126_\",\"callednmCounts\":126},{\"calledCityName\":\"商丘\",\"callednmCount\":\"195_\",\"callednmCounts\":195},{\"calledCityName\":\"安阳\",\"callednmCount\":\"141_\",\"callednmCounts\":141},{\"calledCityName\":\"平顶山\",\"callednmCount\":\"69_\",\"callednmCounts\":69},{\"calledCityName\":\"开封\",\"callednmCount\":\"72_\",\"callednmCounts\":72},{\"calledCityName\":\"新乡\",\"callednmCount\":\"99_\",\"callednmCounts\":99},{\"calledCityName\":\"洛阳\",\"callednmCount\":\"525_\",\"callednmCounts\":525},{\"calledCityName\":\"漯河\",\"callednmCount\":\"21_\",\"callednmCounts\":21},{\"calledCityName\":\"濮阳\",\"callednmCount\":\"123_\",\"callednmCounts\":123},{\"calledCityName\":\"焦作\",\"callednmCount\":\"150_\",\"callednmCounts\":150},{\"calledCityName\":\"许昌\",\"callednmCount\":\"108_\",\"callednmCounts\":108},{\"calledCityName\":\"郑州\",\"callednmCount\":\"132_\",\"callednmCounts\":132},{\"calledCityName\":\"驻马店\",\"callednmCount\":\"90_\",\"callednmCounts\":90},{\"calledCityName\":\"鹤壁\",\"callednmCount\":\"6_\",\"callednmCounts\":6}]},\"msg\":\"请求成功\"}";
        //存进去
        Map<String,Object> result = JSON.parseObject(s, HashMap.class);
        Map<String,Object> data = (Map<String, Object>) result.get("data");
        ObjectMapper objectMapper = new ObjectMapper();
        String s1 = objectMapper.writeValueAsString(data);
        System.out.println("要存进去的data字符串"+s1);
        //转换回来
        Map<String,Object> hashMap = objectMapper.readValue(s1, HashMap.class);
        System.out.println("转换回来的对象："+objectMapper.writeValueAsString(hashMap));*/

        /*Map<String,Object> pushcount = (Map<String, Object>) data.get("pushcount");
        Integer feedbackDataSum = (Integer) pushcount.get("feedbackDataSum");
        System.out.println(feedbackDataSum);*/
        /*int n = 7 - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int a = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        System.out.println(a);
        List<Object> ll = new ArrayList<>();*/
        /*Map<String,Object> maps = new HashMap<>();
        maps.put("y","yqg");
        List<Integer> list = new ArrayList<Integer>();
        List<String> a = new ArrayList<>();*/
        /*int reverse = longReverse(534236469);
        System.out.println(reverse);*/
        String s1 = "5";
        String s2 = "12";
        System.out.println(s1.compareTo(s2));

    }
    private static int longReverse(int x) {
        int res = 0;
        while(x!=0) {
            //每次取末尾数字
            int tmp = x%10;
            //判断是否 大于 最大32位整数
            if (res>214748364 || (res==214748364 && tmp>7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res<-214748364 || (res==-214748364 && tmp<-8)) {
                return 0;
            }
            res = res*10 + tmp;
            x /= 10;
        }
        return res;
    }
    private static int reverse(int x) {

        String g = "-";
        String s1 = String.valueOf(x);
        boolean contains = s1.contains(g);
        if (contains){
            s1 = s1.replaceAll("-","").trim();
        }
        StringBuilder s = new StringBuilder(s1);
        s.reverse();
        if (contains){
            s.insert(0,g);
        }
        Long result = Long.valueOf(s.toString());
        if(result > -1L << 31 && result < (1L << 31)-1 ){
            return Integer.valueOf(s.toString());
        }else{
            return 0;
        }

    }
    /*public Vector<Integer> twoSum(Vector<Integer> nums, int target) {
        for(int i = 0; i<nums.length;i++){
            for(int j = 0; j< nums.length;j++){
                if(i != j){
                    if(i + j == target){
                        System.out.println(i+j);
                    }
                }
            }
        }
        return nums;
    }*/

}
