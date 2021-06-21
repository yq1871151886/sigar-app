package com.tiamaes.cloud.proxy.dongtai;

/**
 * @description:
 * @author: yangqigong
 * @createDate: 2021/6/21
 * @version: 1.0
 */
public class ProxyTest {

    public static void main(String[] args) {
        Figure figure = new Pyy();
        Figure objectProxy = (Figure) new PyyProxy(figure).getObjectProxy();
        objectProxy.skill();
    }

}
