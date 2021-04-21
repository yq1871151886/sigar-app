package com.tiamaes.cloud.yjj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/2/1 11:15
 */
public class Test {


    public static void main(String[] args) {
        AreaBean areaBean = new AreaBean();
        areaBean.setCount(671L);
        areaBean.setCounts(1799L);
        areaBean.setRegionAreaName("人民公园");
        areaBean.setWarningTimes(357L);
        DoorPeopleNum doorPeopleNum1 = new DoorPeopleNum();
        doorPeopleNum1.setName("人民公园南门");
        doorPeopleNum1.setNum(1799L);
        DoorPeopleNum doorPeopleNum2 = new DoorPeopleNum();
        doorPeopleNum2.setName("人民公园南门");
        doorPeopleNum2.setNum(1799L);


        List<DoorPeopleNum> list = new ArrayList<>();
        list.add(doorPeopleNum1);
        list.add(doorPeopleNum2);
        areaBean.setDoorPeopleNum(list);

        List<AreaBean> areaBeans = new ArrayList<>();
        areaBeans.add(areaBean);
        String s = JSON.toJSONString(areaBeans);
        System.out.println(s);


    }
}
