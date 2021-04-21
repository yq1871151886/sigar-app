package com.tiamaes.cloud.utils;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/3/5 14:43
 */
public class ByteUtils {


    /**
     * 字符串转16进制
     * @param str
     * @param encoding
     * @return
     */
    public byte[] strToHexByte(String str,String encoding){
        byte[] bytes = str.getBytes();
        StringBuilder sha256StrBuff = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if (Integer.toHexString(0xFF & bytes[i]).length() == 1) {
                sha256StrBuff.append("0").append(Integer.toHexString(0xFF & bytes[i]));
            } else {
                sha256StrBuff.append(Integer.toHexString(0xFF & bytes[i]));
            }
        }
        try {
            return sha256StrBuff.toString().getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Map sortMap(Map<String, Long> map) {
        //获取entrySet
        Set<Map.Entry<String, Long>> mapEntries = map.entrySet();
        //使用链表来对集合进行排序，使用LinkedList，利于插入元素
        List<Map.Entry<String, Long>> result = new LinkedList<>(mapEntries);
        //自定义比较器来比较链表中的元素
        Collections.sort(result, new Comparator<Map.Entry<String, Long>>() {
            //基于entry的值（Entry.getValue()），来排序链表
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        //将排好序的存入到LinkedHashMap(可保持顺序)中，需要存储键和值信息对到新的映射中。
        Integer sort = 1;
        Map<String, Long> linkMap = new LinkedHashMap<>();
        for (Map.Entry<String, Long> newEntry : result) {
            // 取出排名前5的值
            if (sort <= 5) {
                linkMap.put(newEntry.getKey(), newEntry.getValue());
                ++sort;
            }
        }
        return linkMap;
    }
}
