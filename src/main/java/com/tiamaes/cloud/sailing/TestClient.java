package com.tiamaes.cloud.sailing;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangqigong
 * @version 1.0
 * @ClassName Test
 * @date 2021年06月10日 10:34
 * @description
 */
public class TestClient {

    // 测试
    private static final String URL = "https://payout-gamma.cashfree.com";
    private static final String AUTHORIZE = "/payout/v1/authorize";
    private static final String CLIENT_ID = "CF65905B5AN4Y8WMOMUI2U";
    private static final String CLIENT_SECRET = "c3c2e7bd05cd62b1196100cfd01047c5ec1afdb3";

    // 正式
    /*private static final String URL = "https://payout-api.cashfree.com";
    private static final String AUTHORIZE = "/payout/v1/authorize";
    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";*/


    // 测试
    /*private static final String ORDER_URL = "https://test.cashfree.com/api/v2/cftoken/order";
    private static final String APP_ID = "659054cfad84e4fba71053cce50956";
    private static final String SECRET_KEY = "8ba04dc3cfeef5695375b2f3546125a710c3cccc";*/
    // 生产
    private static final String ORDER_URL = "https://api.cashfree.com/api/v2/cftoken/order";
    private static final String APP_ID = "11178573323e44f7e74f179b37587111";
    private static final String SECRET_KEY = "1ac176986e678fdc37346282fbd826745a0a9bc4";
    /**
     * 三方接口超时时间设置
     */
    public static final int TIMEOUT = 30000;
    public static void main(String[] args) {
        /*String result = HttpRequest.post(URL + AUTHORIZE).header("X-Client-Id", CLIENT_ID)
                .header("X-Client-Secret", CLIENT_SECRET).timeout(TIMEOUT).execute().body();
        System.out.println(result);*/
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", "12321312");
        map.put("orderAmount", "500");
        map.put("orderCurrency", "INR");

        String res = HttpRequest.post(ORDER_URL).body(JSONUtil.toJsonStr(map))
                .header("x-client-id", APP_ID).header("x-client-secret", SECRET_KEY).timeout(TIMEOUT).execute().body();
        System.out.println(res);
    }
}
