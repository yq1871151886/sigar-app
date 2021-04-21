package com.tiamaes.cloud.dmd;

import lombok.Data;

import java.util.List;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/2/4 11:12
 */
@Data
public class Method {

    private String name;

    private String returnType;

    private List<String> parameterTypes;

}
