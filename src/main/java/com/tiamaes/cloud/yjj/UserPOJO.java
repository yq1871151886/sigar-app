package com.tiamaes.cloud.yjj;

import lombok.Data;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/2/24 14:13
 */
@Data
public class UserPOJO {

    private String name;
    private Integer num;

    public UserPOJO(String name, Integer num) {
        this.name = name;
        this.num = num;
    }
}
