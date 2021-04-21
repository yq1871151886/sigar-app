package com.tiamaes.cloud.beans;


import lombok.Data;

import java.util.List;

/***********************************************
 * @Author: yandi
 * @Date: Created in 2021/1/5 14:39
 * @Description:
 * @Modified:
 * @Version: $
 * @Copyright 2020-2021 Comleader - Powered By 凌鉴项目组
 **********************************************/
@Data
public class FzBigPOJO {


    //途径
    private String anaType;
    //类型
    private String zptypeDetail;
    //个数
    private Integer anaTypeSize;

    //预警数量
    private Integer warnCount;
    //private Unit warnCountObject;
    //推送数量
    private Integer pushCount;
    //private Unit pushCountObject;
    //拦截量
    private Integer interceptCount;
    //诈骗类型排名count
    private Integer zptypeCount;
    //private Unit zptypeCountObject;
    //异常号码发现 个数
    private Integer operatorCount;
    //private Unit operatorCountObject;
    //异常号码发现 移动，联通，电信
    private String operator;
    //被叫手机号码个数 转换单位
    private String callednmCount;
    //private Unit callednmCountObject;
    //被叫手机号码个数
    private Long callednmCounts;
    //被叫地市
    private String calledCityName;
    //调取数据数量
    private Integer retrievalDataSum;
    //private Unit retrievalDataSumObject;
    //反馈数据数量
    private Integer feedbackDataSum;
    //private Unit feedbackDataSumObject;
    //推送数据数量
    private Integer gapushCount;
    //互联网类型
    private String type;
    //互联网访问域名
    private String host;
    //互联网域名个数
    private Integer hostCount;
    //private Unit hostCountObject;
    //互联网个数
    private Integer zpCount;
    //日期
    private String day;
    //互联网 count
    private Integer count;
    //private Unit countObject;
    //互联网 日期
    private String createTime;
    //发现goip设备数量
    private Integer goipCount;
    //private Unit goipCountObject;
    //发现团伙设备数量
    private Integer teamid;
    //private Unit teamidObject;
    //goip设备数量 趋势七天
    private Integer goipTrends;
    //private Unit goipTrendsObject;
    //goip设备数量 时间 趋势七天
    private String openTime;
    //团伙设备数量 趋势七天
    private Integer teamidTrends;
    //private Unit teamidTrendsObject;
    //团伙设备数量 时间 趋势七天
    private String starttalktime;

    public FzBigPOJO(){

    }
    public FzBigPOJO(String day,Integer count, String createTime,Integer warnCount) {
        this.warnCount = warnCount;
        this.day = day;
        this.count = count;
        this.createTime = createTime;
    }
}
