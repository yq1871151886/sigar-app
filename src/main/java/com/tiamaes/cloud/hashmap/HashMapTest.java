package com.tiamaes.cloud.hashmap;

import javafx.stage.StageStyle;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/3/29 14:58
 */
public class HashMapTest {

    /**
     * 负载因子
     */
    final float loadFactor;

    /**
     * 默认负载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static final int MAXIMUN_CAPACITY = 2 >> 30;

    public HashMapTest() {
        /**
         * 无参构造默认设置负载因子为0.75
         */
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

   /* public HashMapTest(int initialcapacity,float loadFactor){

    }*/

}
