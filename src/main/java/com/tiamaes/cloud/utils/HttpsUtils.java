
package com.tiamaes.cloud.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ansh
 *
 */
@Slf4j
public class HttpsUtils {
    // private static log log = logFactory.getlog(HttpsUtils.class);
    private static PoolingHttpClientConnectionManager connMgr;

    private static RequestConfig requestConfig;

    private static final int MAX_TIMEOUT = 180000;
    private static final String HTTPS = "https";
    private static final String UTF_8 = "UTF-8";

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        // Validate connections after 1 sec of inactivity
        connMgr.setValidateAfterInactivity(1000);
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        // configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static JSONObject doGet(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpClient httpClient = null;
        if (apiUrl.startsWith(HTTPS)) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSslConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (StringUtils.isEmpty(result) == true) ? new JSONObject() : JSONObject.parseObject(result);
    }

    /**
     * 发送 POST 请求，K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static JSONObject doPost(String apiUrl, Map<String, Object> params) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith(HTTPS)) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSslConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }

        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(),
                        entry.getValue() == null ? null : entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(UTF_8)));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, UTF_8);
        } catch (IOException e) {
            log.error("请求异常：" + e.getMessage());
            // e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSON.parseObject(httpStr);
    }

    /**
     * 发送 POST 请求，K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static JSONArray doPostForJsonArray(String apiUrl, Map<String, Object> params) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith(HTTPS)) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSslConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }

        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(),
                        entry.getValue() == null ? null : entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(UTF_8)));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, UTF_8);
        } catch (IOException e) {
            log.error("请求异常：" + e.getMessage());
            // e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSON.parseArray(httpStr);
    }

    // /**
    // * 发送 POST 请求，JSON形式。实测参数传递有问题。先注释掉了。
    // *
    // * @param apiUrl
    // * @param json
    // * json对象
    // * @return
    // */
    // public static JSONObject doPost(String apiUrl, JSONObject json) {
    // CloseableHttpClient httpClient = null;
    // if (apiUrl.startsWith("https")) {
    // httpClient =
    // HttpClients.custom().setSSLSocketFactory(createSslConnSocketFactory())
    // .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
    // } else {
    // httpClient = HttpClients.createDefault();
    // }
    // String httpStr = null;
    // HttpPost httpPost = new HttpPost(apiUrl);
    // CloseableHttpResponse response = null;
    //
    // try {
    // httpPost.setConfig(requestConfig);
    // // 解决中文乱码问题
    // StringEntity stringEntity = new StringEntity(json.toString(), UTF_8);
    // stringEntity.setContentEncoding(UTF_8);
    // stringEntity.setContentType("application/json");
    // httpPost.setEntity(stringEntity);
    // response = httpClient.execute(httpPost);
    // HttpEntity entity = response.getEntity();
    // httpStr = EntityUtils.toString(entity, UTF_8);
    // } catch (IOException e) {
    // e.printStackTrace();
    // } finally {
    // if (response != null) {
    // try {
    // EntityUtils.consume(response.getEntity());
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }
    // return JSON.parseObject(httpStr);
    // }

    /**
     * 发送 POST 请求，JSON形式
     *
     * @param apiUrl json字符串
     * @return
     */
    public static JSONObject doPost(String apiUrl, String jsonStr) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith(HTTPS)) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSslConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            // 解决中文乱码问题
            StringEntity stringEntity = new StringEntity(jsonStr, UTF_8);
            stringEntity.setContentEncoding(UTF_8);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSON.parseObject(httpStr);
    }

    /**
     * 发送 POST 请求，JSON形式
     *
     * @param apiUrl json字符串
     * @return
     */
    public String doXmlPost(String apiUrl, String xmlStr) {
        CloseableHttpClient httpClient = null;
        if (apiUrl.startsWith(HTTPS)) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSslConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            // 解决中文乱码问题
            StringEntity stringEntity = new StringEntity(xmlStr, UTF_8);
            stringEntity.setContentEncoding(UTF_8);
            stringEntity.setContentType("application/xml");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSslConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

    public static String getOrderNo() {
        // 生成订单号
        String orderNo = "TM" + new SimpleDateFormat("yyyyMMddhhmmsss").format(new Date()) + RandomUtils.nextInt(10000);
        return orderNo;
    }

    public static String doGetForString(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpClient httpClient = null;
        if (apiUrl.startsWith(HTTPS)) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSslConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送 长沙银行POST 请求，K-V形式
     *
     * API接口URL
     * 
     * @param params 参数map
     * @return
     */
    /*public static JSONObject sendBankPost(Map<String, Object> params, BankKeys bankKeys) {
        CloseableHttpClient httpClient = null;
        // 判断是是什么https还是http
        if (bankKeys.getBaseBrl().startsWith(HTTPS)) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSslConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        //// 第一步：先将报文数据放到一个map里，再将map转为json字符串
        Map<String, Object> data = new HashMap<String, Object>(1);
        data.put("Data", params);
        JSONObject json = new JSONObject(data);
        String jsonStr = json.toJSONString();
        // 第二步：调用加密方法对json字符串加密并且加密的结果作为bizContent字段的值，生成一个以bizContent为key值的json字符串，该值放在http体中。
        String biz = sm4encrypt(jsonStr, bankKeys.getEncryptKey(), bankKeys.getEncryptIV());
        // 签名源JSON串:{"bizContent":"79C5BB2340F72DAA29674FA826F77F997BA6E4E2F45321BB6F07EEE6439375FE1C49347546CB1439D98F25BC61F150D55E36F13366F4D41687B6D5F843CF92A183AA01F322321C7857D1D824176F762AA156B356C84F29F30D15DF045597E070CBEB2E685718FD0465861D43D91611AC5DD8C5C7B0454100793085A069B99C7025211A95D9BEA24BEAB697D8A091CD92B4326DB7C6DD5D73F5D79B902D5CB892A8C5B33D87D546FBAACB7E99B4688DD5DA1869CA53BC617972B86FFED31113F2697ADE343A7433731B65CCEEF00F5BA03120BEE31EAF2E35474AD5B15080D7D4DB6AFE3E3245972452A2C0ABF8358AF83FE84D80D40857A0F435D37B2316277AF83F0F085398C7FB58DACB8BB5EDB72214290917DDE377B2793033A2AC4F2513E64FF426D4CA2646BE5534972DDFACD1"}
        // 第三步：调用加签方法对第二步中得到的json字符串加签，并将签名数据放到http头中的”x-aob-signature”字段中，该字段是http头中的唯一字段。
        String signData = sm2sign(biz, bankKeys.getSignKey());
        JSONObject jsonObject = JSON.parseObject(biz);
        String resStr = null;
        HttpPost httpPost = new HttpPost(bankKeys.getBaseBrl());
        CloseableHttpResponse response = null;
        JSONObject res = null;
        try {
            httpPost.setConfig(requestConfig);
            StringEntity requestEntity = new StringEntity(biz, UTF_8);
            // 将加签信息放入头部信息
            httpPost.setHeader("x-aob-signature", signData);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            httpPost.setEntity(requestEntity);
            String s = httpPost.toString();
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String httpStr = EntityUtils.toString(entity, UTF_8);
            // 第二步：调用验签方法验签
            Header resHeader = response.getFirstHeader("x-aob-signature");
            String rreHeader = resHeader.getValue();
            boolean verifyResult = sm2verify(httpStr, rreHeader, bankKeys.getVerifyKey());
            // 调用方法解密
            if (verifyResult == true) {
                // 第三步：调用解密方法，将bizContent中的值解密，获取到解密后的数据
                resStr = sm4decrypt(httpStr, bankKeys.getEncryptKey(), bankKeys.getEncryptIV());
                // 解密后:{"Data":{"SerialNo":"202004126700088888888","BussinessCode":"01","TransactionCode":"ZZGJM0001","UserInformation":"{\"idNo\":\"34643344712471273\",\"idType\":\"01\",\"name\":\"lyyy\",\"userId\":\"00001\",\"cellphoneNo\":\"13973136765\"}","ChannelId":"ZZGJ","BackUrl":"http://162.16.7.151:8080"}}
                System.out.println("解密后:" + resStr);
                JSONObject result = JSON.parseObject(resStr);
                res = (JSONObject) result.get("Data");
            }
        } catch (IOException e) {
            log.error("请求异常：" + e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }*/

    /**
     * 发送 银联消费POST 请求，K-V形式
     *
     * API接口URL
     * 
     * @param maps 参数map
     * @return
     */
    /*public static JSONObject sendUnionPayPost(List<Map<String, Object>> maps, UnionPayKeys keys) {
        CloseableHttpClient httpClient = null;
        // 判断是是什么https还是http
        if (keys.getUnionPayUrl().startsWith(HTTPS)) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSslConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }

        HttpPost httpPost = new HttpPost(keys.getUnionPayUrl());
        CloseableHttpResponse response = null;
        JSONObject res = null;
        try {
            httpPost.setConfig(requestConfig);
            // 将参数转换为json
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> params = maps.get(0);
            String data = objectMapper.writeValueAsString(params);
            log.info("请求体信息：{}", data);
            // 将加签信息放入头部信息
            Date date = new Date();
            String version = keys.getConsumeVersion();
            String appid = keys.getAppid();
            String sequence = Tools.getSerialIdOfNumber(date, 10);
            Map<String, Object> randomMap = maps.get(1);
            String random = randomMap.get("random").toString();
            random = Base64Utils.encode(RsaUtils.encryptByPublicKey(random.getBytes(), keys.getPubKey()));
            String timestamp = Tools.getSerialId(date);
            String nonce = Tools.getSerialIdOfNumber(null, 18);
            String requestSignData = getUnionPaySignature(appid, data, nonce, timestamp, version, sequence, random);
            String signature = RsaUtils.signSha1(requestSignData.getBytes(UTF_8), keys.getPriKey());
            log.info("签名体:{},生成签名：{}", requestSignData, signature);
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            httpPost.setHeader("version", version);
            httpPost.setHeader("appId", appid);
            httpPost.setHeader("sequence", sequence);
            httpPost.setHeader("random", random);
            httpPost.setHeader("timestamp", timestamp);
            httpPost.setHeader("nonce", nonce);
            httpPost.setHeader("signType", "RSA");
            httpPost.setHeader("signature", signature);
            Header[] allHeaders = httpPost.getAllHeaders();
            log.info("HTTP请求头长度：{}", allHeaders.length);
            for (int i = 0; i < allHeaders.length; i++) {
                log.info("HTTP请求头下标:{}，内容：{}", i, allHeaders[i]);
            }
            // 请求报文体
            StringEntity requestEntity = new StringEntity(data, UTF_8);
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String resStr = EntityUtils.toString(entity, UTF_8);
            // 响应码
            JSONObject resJson = JSONObject.parseObject(resStr);

            log.info("应答公共参数：{}", resJson);
            if (resJson == null || !"0000".equals(resJson.getString("errcode"))) {
                log.info("银联消费失败");
                return resJson;
            } // 第二步：取出请求头中信息调用验签方法验签
            *//*
             * String resAppId = response.getFirstHeader("appId").getValue(); String
             * resSequence = response.getFirstHeader("sequence").getValue(); String
             * resTimestamp = response.getFirstHeader("timestamp").getValue(); String
             * resNonce = response.getFirstHeader("nonce").getValue(); String resSignature =
             * response.getFirstHeader("signature").getValue(); String resSignData =
             * getUnionPaySignature(resAppId, resStr, resNonce, resTimestamp, version,
             * resSequence, random); boolean verify =
             * RsaUtils.verify(resSignData.getBytes(UTF_8), keys.getPubKey(), resSignature);
             *//*
            res = JSON.parseObject(resStr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }*/

    /*public static void main(String[] args) throws Exception {
        String s = "123";
        // String pri =
        // "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK0O5c6ttVAeXzQULjEyynVeHr1gXrZ5XgZWBIuJJQ1O4XzRhBAajf2SacXxu7dwUV7nrMMi6FQI7HvuIKz09/DydJTkN2J95210RZ2ZfqwRdlpPLx9UQUObhOinpGF9d2wpfLc3wPQRD0UjP2hjZYopQWuRH0keslTkl4B8rY/lAgMBAAECgYA2/7ERSiuBVuWyy2cSw62MN2lVjjcLju7O3K99GQzpedQ3su5hn87Yc5uiCP07gzqZCs/hBEsJyfw+NqOz0/an2lZOi5nboW+7KGPQl4tb5uRmK+KzNfl/Q7ZlRHjn4j0mERsB7IutlhYXNEVdcn8n39XyBqxSYdSMX9iYbx/KAQJBAOX5U3bO+bYh4ZQ7B81czx8u4EUQRBmtoGMVkipvNg3vOuLcDXENsXHd1q60PgdFcqt3Cv9M29iWXoCeSax1ugUCQQDApKQs8rf+QP3Ju7aMg49WRrnmnOzrdlH8VdvJx4c8iR9zWqosy56i4mh6MMejmm7rHDaf50J5ZqpgkbgeogRhAkAqiltpH9JLYasTu3OvBlr9/rLXs7GVOmvvyD64gMfz/evbqS8HWawYTxv2RzsuJyeWFjg/j50fTHmAu0cPktttAkEAupl/8Y32DVD0w4iRLZBtsjt17NzKXNXSWmLsL4qXA1srXLhQqG1frxUx8FH5vw5jLFe6za7bY+6/UCU2PG3hYQJAVKr26BA+bRlVSnxktzNQ77W2Wo8gzQZY1EPxTWbzNfFg0I83057OT8swybdQAe7733fUdQTk31S33kevqRkBtQ==";
        String pri = "MIICXAIBAAKBgQCtDuXOrbVQHl80FC4xMsp1Xh69YF62eV4GVgSLiSUNTuF80YQQGo39kmnF8bu3cFFe56zDIuhUCOx77iCs9Pfw8nSU5DdifedtdEWdmX6sEXZaTy8fVEFDm4Top6RhfXdsKXy3N8D0EQ9FIz9oY2WKKUFrkR9JHrJU5JeAfK2P5QIDAQABAoGANv+xEUorgVblsstnEsOtjDdpVY43C47uztyvfRkM6XnUN7LuYZ/O2HObogj9O4M6mQrP4QRLCcn8Pjajs9P2p9pWTouZ26Fvuyhj0JeLW+bkZiviszX5f0O2ZUR45+I9JhEbAeyLrZYWFzRFXXJ/J9/V8gasUmHUjF/YmG8fygECQQDl+VN2zvm2IeGUOwfNXM8fLuBFEEQZraBjFZIqbzYN7zri3A1xDbFx3dautD4HRXKrdwr/TNvYll6AnkmsdboFAkEAwKSkLPK3/kD9ybu2jIOPVka55pzs63ZR/FXbyceHPIkfc1qqLMueouJoejDHo5pu6xw2n+dCeWaqYJG4HqIEYQJAKopbaR/SS2GrE7tzrwZa/f6y17OxlTpr78g+uIDH8/3r26kvB1msGE8b9kc7LicnlhY4P4+dH0x5gLtHD5LbbQJBALqZf/GN9g1Q9MOIkS2QbbI7dezcylzV0lpi7C+KlwNbK1y4UKhtX68VMfBR+b8OYyxXus2u22Puv1AlNjxt4WECQFSq9ugQPm0ZVUp8ZLczUO+1tlqPIM0GWNRD8U1m8zXxYNCPN9Oezk/LMMm3UAHu+9931HUE5N9Ut95Hr6kZAbU=";
        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtDuXOrbVQHl80FC4xMsp1Xh69YF62eV4GVgSLiSUNTuF80YQQGo39kmnF8bu3cFFe56zDIuhUCOx77iCs9Pfw8nSU5DdifedtdEWdmX6sEXZaTy8fVEFDm4Top6RhfXdsKXy3N8D0EQ9FIz9oY2WKKUFrkR9JHrJU5JeAfK2P5QIDAQAB";
        String s1 = RsaUtils.signSha1(s.getBytes(UTF_8), pri);
        boolean verifySha1 = RsaUtils.verifySha1(s.getBytes(), pub, s1);

        System.out.println(verifySha1);
    }*/

    private static final String VERSION_ONE = "0100";
    private static final String VERSION_TWO = "0200";

    /**
     * 银联消费请求头随机码
     * 
     * @return
     * @throws Exception
     */
   /* public static String getUnionPayRandom() throws Exception {
        String serialId = Tools.getSerialIdOfLetterAndNumber(null, 8);
        byte[] encode = Base64.encode(RsaUtils.encryptByPublicKey(serialId.getBytes(),
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmZqn6zJQxREZzCTIJeTYj9ilNQ4iAan5719/35/Uqj0kJKB2pZFXgkQIMsnronsvc6+Q+rhDDhHICWk6i+ak9WN15k2dz2NsKgjXimP6JAu74akMjkhVV8N15w0iR4hS+kmi0tRDiRb8x4VlRuJvz68y/tSiJmuLEsMoVEC3uXw4wTQazsrEyRvkzKAA8DnHRb11qizvLbUsmuqmDT4FeqkwKCaJdvRbGDb2bAQ4a6LpJKSJawW4VGc+zvEt4DX2sbwGGFu93FUM8UhqbP8dxd/iB2md9i/lY5iHIlQ098r+JRoPNUxk64lgtRTmdIIvGhA3cNxD4/oK+AV7Nnz3rwIDAQAB"));
        return new String(encode);
    }*/

    /**
     * 银联支付获取签名
     * 
     * @param appId
     * @param data
     * @param nonce
     * @param timestamp
     * @param version
     * @param sequence
     * @param random
     * @return
     * @throws Exception
     */
    public static String getUnionPaySignature(String appId, String data, String nonce, String timestamp, String version,
            String sequence, String random) throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("appid=").append(appId).append("&message=").append(data).append("&nonce=").append(nonce);
        if (VERSION_ONE.equals(version)) {
            result.append("&timestamp=").append(timestamp);
        } else if (VERSION_TWO.equals(version)) {
            result.append("&random=").append(random).append("&sequence=").append(sequence).append("&timestamp=")
                    .append(timestamp).append("&version=").append(version);
        }
        return result.toString();
    }

    /**
     * 加密方法
     * 
     * @param origjson 报文源数据
     * @param sm4Key   SM4加解密密钥
     * @param sm4Iv    SM4加解密向量
     * @return signorigData 签名源JSON串 先将报文源数据加密，再将解密后的数据放到bizContent字段下，组成签名源JSON串
     */

    /*public static String sm4encrypt(String origjson, String sm4Key, String sm4Iv) {
        String signorigData = null;
        try {
            // 调用加密方法
            String encryptData = SMHelper.encrypt(origjson, sm4Key, sm4Iv);
            // 加密数据：79C5BB2340F72DAA29674FA826F77F997BA6E4E2F45321BB6F07EEE6439375FE1C49347546CB1439D98F25BC61F150D55E36F13366F4D41687B6D5F843CF92A183AA01F322321C7857D1D824176F762AA156B356C84F29F30D15DF045597E070CBEB2E685718FD0465861D43D91611AC5DD8C5C7B0454100793085A069B99C7025211A95D9BEA24BEAB697D8A091CD92B4326DB7C6DD5D73F5D79B902D5CB892A8C5B33D87D546FBAACB7E99B4688DD5DA1869CA53BC617972B86FFED31113F2697ADE343A7433731B65CCEEF00F5BA03120BEE31EAF2E35474AD5B15080D7D4DB6AFE3E3245972452A2C0ABF8358AF83FE84D80D40857A0F435D37B2316277AF83F0F085398C7FB58DACB8BB5EDB72214290917DDE377B2793033A2AC4F2513E64FF426D4CA2646BE5534972DDFACD1
            System.out.println("加密数据: " + encryptData);
            // 加密后的数据放到bizContent里面，再把map转json
            HashMap data = new HashMap(1);
            data.put("bizContent", encryptData);
            // map转json，签名源JSON串
            signorigData = JSON.toJSONString(data);
        } catch (OpenException e) {
            e.printStackTrace();
        }
        return signorigData;
    }*/

    /**
     * 加签方法
     * 
     * @param signorigData 签名源JSON串
     * @param privateKey   SM2加签私钥
     * @return signData 签名
     */

    /*public static String sm2sign(String signorigData, String privateKey) {
        try {
            String signData = SMHelper.sign(signorigData, privateKey);
            return signData;
        } catch (OpenException e) {
            e.printStackTrace();
            return "签名异常";
        }
    }*/

    /**
     * 解密方法
     * 
     * @param signorigData 签名源JSON串
     * @param sm4Key       SM4加解密密钥
     * @param sm4Iv        SM4加解密向量
     * @return decryptData 解密后的数据
     */
    /*public static String sm4decrypt(String signorigData, String sm4Key, String sm4Iv) {
        // 解析报文
        Map<String, Object> jsonToMap = JSONObject.parseObject(signorigData);
        // 获取到加密数据
        String encryptData = jsonToMap.get("bizContent").toString();
        String decryptData = null;
        // SM4解密
        try {
            decryptData = SMHelper.decrypt(encryptData, sm4Key, sm4Iv);
        } catch (OpenException e) {
            e.printStackTrace();
        }
        return decryptData;
    }*/

    /**
     * 验签方法
     * 
     * @param signorigData 签名源JSON串
     * @param signData     签名
     * @param publicKey    SM2验签公钥
     * @return verifyResult 验签结果
     */

    /*public static boolean sm2verify(String signorigData, String signData, String publicKey) {
        SMHelper smHelper = new SMHelper();
        try {
            boolean verifyResult = SMHelper.verify(signorigData, signData, publicKey);
            return verifyResult;
        } catch (OpenException e) {
            return false;
        }
    }*/

}
