package com.tiamaes.cloud.sailing;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangqigong
 * @version 1.0
 * @ClassName Test
 * @date 2021年06月10日 10:34
 * @description cashfree支付方式参数验证
 */
public class Test {

    // test
    private static final String URL = "https://payout-gamma.cashfree.com";
    private static final String AUTHORIZE = "/payout/v1/authorize";
    // private static final String CLIENT_ID = "CF65905B5AN4Y8WMOMUI2U";
    // private static final String CLIENT_SECRET = "c3c2e7bd05cd62b1196100cfd01047c5ec1afdb3";
    private static final String CLIENT_ID = "CF65905BVZBWYD4C9AUYYE";
    private static final String CLIENT_SECRET = "ddc415374e46c2f35ef4b7d99c3fca14e438b20c";
    // prod
    /*private static final String URL = "https://payout-api.cashfree.com";
    private static final String AUTHORIZE = "/payout/v1/authorize";
    private static final String CLIENT_ID = "CF111785C30SDF7GCDSES91CHO80";
    private static final String CLIENT_SECRET = "461433dd081806165c606df300ded5a482bdde6d";*/


    // test
    private static final String ORDER_URL = "https://test.cashfree.com/api/v2/cftoken/order";
    private static final String APP_ID = "659054cfad84e4fba71053cce50956";
    private static final String SECRET_KEY = "8ba04dc3cfeef5695375b2f3546125a710c3cccc";
    // prod
    /*private static final String ORDER_URL = "https://api.cashfree.com/api/v2/cftoken/order";
    private static final String APP_ID = "11178573323e44f7e74f179b37587111";
    private static final String SECRET_KEY = "1ac176986e678fdc37346282fbd826745a0a9bc4";*/
    /**
     * 三方接口超时时间设置
     */
    public static final int TIMEOUT = 30000;
    public static void main(String[] args) {
        String result = HttpRequest.post(URL + AUTHORIZE).header("X-Client-Secret", CLIENT_SECRET)
                .header("X-Client-Id", CLIENT_ID).timeout(TIMEOUT).execute().body();
        System.out.println(result);
        /*Map<String, Object> map = new HashMap<>();
        map.put("orderId", "12321312");
        map.put("orderAmount", "500");
        map.put("orderCurrency", "INR");

        String res = HttpRequest.post(ORDER_URL).body(JSONUtil.toJsonStr(map))
                .header("x-client-id", APP_ID).header("x-client-secret", SECRET_KEY).timeout(TIMEOUT).execute().body();
        System.out.println(res);*/
    }

    private static String generateEncryptedSignature(String clientIdWithEpochTimestamp) {
        // clientIdWithEpochTimeStamp = clientId+"."+Instant.now().getEpochSecond();
        String encrytedSignature = "";
        try {
            byte[] keyBytes = Files
                    .readAllBytes(new File("/Users/sameera/Downloads/payout_test_public_key.pem").toPath()); // Absolute Path to be replaced
            String publicKeyContent = new String(keyBytes);
            System.out.println(publicKeyContent);
            publicKeyContent = publicKeyContent.replaceAll("[\\t\\n\\r]", "")
                    .replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            System.out.println(publicKeyContent);
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(
                    Base64.getDecoder().decode(publicKeyContent));
            RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
            final Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            encrytedSignature = Base64.getEncoder().encodeToString(cipher.doFinal(clientIdWithEpochTimestamp.getBytes()));
            System.out.println(encrytedSignature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrytedSignature;
    }

}
