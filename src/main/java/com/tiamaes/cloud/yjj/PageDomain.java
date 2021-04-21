package com.tiamaes.cloud.yjj;

import lombok.Data;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/3/25 17:32
 */
@Data
public class PageDomain<T> {

    private Integer pageNumber;

    private Integer pageSize;

    private T queryParams;
}
