package com.tiamaes.cloud.dmd;

import lombok.Data;

import java.util.Map;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/2/4 11:15
 */
@Data
public class Result {

    private String parameterType;

    private Map<String,Map<String,Object>> properties;
}
