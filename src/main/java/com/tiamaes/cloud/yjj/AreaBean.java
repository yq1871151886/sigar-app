package com.tiamaes.cloud.yjj;

import lombok.Data;

import java.util.List;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/2/1 11:12
 */
@Data
public class AreaBean {

    private Long count;

    private Long counts;

    private String regionAreaName;

    private Long warningTimes;

    private List<DoorPeopleNum> doorPeopleNum;
}
