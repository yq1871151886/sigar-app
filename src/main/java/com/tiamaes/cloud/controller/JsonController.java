package com.tiamaes.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiamaes.cloud.beans.CommReq;
import com.tiamaes.cloud.beans.QrCodeToWeChatRequest;
import com.tiamaes.cloud.beans.QrCodeToWeChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/1/6 14:37
 */
@RestController
@RequestMapping("json")
public class JsonController {

    @Autowired
    private Gson gson;

    @PostMapping("wechat")
    public QrCodeToWeChatResponse weChatRequest(@RequestBody QrCodeToWeChatRequest request){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(request);
            System.out.println(s);
            Gson gson = new GsonBuilder().create();
            CommReq oReq = gson.fromJson(s, CommReq.class);
            System.out.println(gson.toJson(oReq));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String s = "{\"errcode\":0,\"errmsg\":\"OK\",\"encryptedData\":\"4sQUm9VVVXK3VHhrBHBEDw\\u003d\\u003d\",\"iv\":\"EYVG7dKhy17a9GUSzL9EnA\\u003d\\u003d\",\"nonceStr\":\"389480ec3f7dd73f\"}";
        //System.out.println(JSON.toJSONString(request));
        QrCodeToWeChatResponse response = JSONObject.parseObject(s, QrCodeToWeChatResponse.class);
        return response;
    }

}
