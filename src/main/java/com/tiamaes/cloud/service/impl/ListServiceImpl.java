package com.tiamaes.cloud.service.impl;

import com.google.gson.Gson;
import com.tiamaes.cloud.beans.FzBigPOJO;
import com.tiamaes.cloud.mapper.ListMapper;
import com.tiamaes.cloud.service.ListService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/1/11 11:11
 */
@Service
public class ListServiceImpl  implements ListService {


    @Autowired
    private ListMapper listMapper;


    //@Override
    public List<FzBigPOJO> getZpTrend1() {
        List<FzBigPOJO> trendsList = new ArrayList<>();
        String[] formats = getBeforeSevenDays();
        //FzBigPOJO list = null;
        for (int i = 0; i < formats.length; i++) {
            // 电信 查询近七天每天的数据
            FzBigPOJO list = listMapper.getDxTrends(formats[i]);
            StringBuffer sb = new StringBuffer();
            StringBuffer insert = sb.append(formats[i]).insert(4, "-").insert(7, "-");
            // 互联网 查询近七天每天的数据
            FzBigPOJO list1 = listMapper.getInternetTrends(String.valueOf(insert));
            if (list == null) list = new FzBigPOJO();
            if (StringUtils.isBlank(list.getDay())) {
                list.setDay(formats[i]);
                list.setWarnCount(0);
                //list.setWarnCountObject(UnitUtil.digital((long) list.getWarnCount()));
            } else {
                //list.setWarnCountObject(UnitUtil.digital((long) list.getWarnCount()));
            }
            if (list1 == null) list1 = new FzBigPOJO();
            if (StringUtils.isBlank(list1.getCreateTime())) {
                list1.setCreateTime(formats[i]);
                list1.setCount(0);
                //list1.setCountObject(UnitUtil.digital((long) list1.getCount()));
            } else {
                //list1.setCountObject(UnitUtil.digital((long) list1.getCount()));
            }
            trendsList.add(list);
            trendsList.add(list1);
        }
        //if (trendsList.size() > 0) {
            return trendsList;
            //return JSON.toJSONString(new JsonResult<>(TrendsList));
        //}
        //return JSON.toJSONString(new JsonResult<>(CodeMsgEnum.DATA_MISSING_ERROR));
    }

    @Override
    public List<FzBigPOJO> getZpTrend() {
        List<FzBigPOJO> trendsList = new ArrayList<>();
        String[] formats = getBeforeSevenDays();

        for (int i = 0; i < formats.length; i++) {
            // 电信 查询近七天每天的数据
            FzBigPOJO list = listMapper.getDxTrends(formats[i]);
            StringBuffer sb = new StringBuffer();
            StringBuffer insert = sb.append(formats[i]).insert(4, "-").insert(7, "-");
            // 互联网 查询近七天每天的数据
            FzBigPOJO list1 = listMapper.getInternetTrends(String.valueOf(insert));
            if (list == null){
                list = new FzBigPOJO();
            }
            if (StringUtils.isBlank(list.getDay())) {
                list.setDay(formats[i]);
                list.setWarnCount(0);
                //list.setWarnCountObject(UnitUtil.digital((long) list.getWarnCount()));
            } else {
                //list.setWarnCountObject(UnitUtil.digital((long) list.getWarnCount()));
            }
            if (list1 == null) list1 = new FzBigPOJO();
            if (StringUtils.isBlank(list1.getCreateTime())) {
                list1.setCreateTime(formats[i]);
                list1.setCount(0);
                //list1.setCountObject(UnitUtil.digital((long) list1.getCount()));
            } else {
                //list1.setCountObject(UnitUtil.digital((long) list1.getCount()));
            }
            list.setCount(list1.getCount());
            list.setCreateTime(list1.getCreateTime());
            trendsList.add(list);
        }
        return trendsList;
    }

    public static String[] getBeforeSevenDays() {
        String[] arr = new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = null;
        for (int i = 0; i < 7; i++) {
            c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -i);
            arr[6 - i] = sdf.format(c.getTime());
        }
        return arr;
    }

    public static String[] getBeforeSevenDay() {
        String[] arr = new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = null;
        for (int i = 0; i < 7; i++) {
            c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -i);
            arr[6 - i] = sdf.format(c.getTime());
        }
        return arr;
    }

    public static void main(String[] args) {
        List<FzBigPOJO> list1 = new ArrayList<FzBigPOJO>();
        FzBigPOJO pojo1 = new FzBigPOJO();
        pojo1.setDay("2021-01-11");
        pojo1.setCount(50);
        list1.add(pojo1);
        FzBigPOJO pojo2 = new FzBigPOJO();
        pojo2.setDay("2021-01-12");
        pojo2.setCount(30);
        list1.add(pojo2);
        List<FzBigPOJO> list2 = new ArrayList<FzBigPOJO>();
        FzBigPOJO pojo3 = new FzBigPOJO();
        pojo3.setCreateTime("2021-01-11");
        pojo3.setWarnCount(70);
        FzBigPOJO pojo4 = new FzBigPOJO();
        pojo4.setCreateTime("2021-01-12");
        pojo4.setWarnCount(80);
        list2.add(pojo3);
        list2.add(pojo4);
        // .map(z -> new FzBigPOJO(x.getDay(), x.getCount(), z.getCreateTime(), z.getWarnCount()))
        List<FzBigPOJO> collect = list1.stream().flatMap(x -> list2.stream().filter(y -> x.getDay().equals(y.getCreateTime()))
                .map(z -> new FzBigPOJO(x.getDay(), x.getCount(), z.getCreateTime(), z.getWarnCount())))
                .collect(Collectors.toList());
        //list2.stream().filter(x-> x.getDay().);
        Gson gson = new Gson();
        System.out.println(gson.toJson(collect));
        //collect.stream().forEach(System.out::println);

    }









}
