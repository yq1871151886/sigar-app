package com.tiamaes.cloud.controller;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.google.gson.Gson;
import com.tiamaes.cloud.sigar.SigarUtil;
import com.tiamaes.cloud.yjj.PageDomain;
import com.tiamaes.cloud.yjj.PoliceStrength;
import lombok.extern.slf4j.Slf4j;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/11/20 15:21
 */
@RestController
@Slf4j
@RefreshScope
public class ServersController {


    @GetMapping
    public String getCpu(){
        SigarUtil.setSystemVariable();
        Sigar sigar = new Sigar();
        CpuPerc cpu = null;
        try {
            cpu = sigar.getCpuPerc();
        } catch (SigarException e) {
            log.info("实例化内存失败");
        }
        if (null != cpu){
            String s = String.valueOf(cpu.getCombined());
            System.out.println(s);
            log.info("CPU使用率：{}",s);
            return s;
        }
       return null;
    }
    @Autowired
    private Gson gson;
    //produces = {"application/json; charset=UTF-8"}
    @PostMapping(value = "GJSYSERVICE_NEW/g/MoneyService/saveMultiNotes",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces ={"application/json; charset=UTF-8"} )
    public String saveMutiRecords(@RequestBody String params, HttpServletRequest request) throws IOException {
        /*BufferedReader reader = request.getReader();
        StringBuilder bodyStr = new StringBuilder();
        String s = reader.readLine();
        while (s != null){
            bodyStr.append(s);
            s = reader.readLine();
        }
        System.out.println("接收到的值："+bodyStr.toString());*/
        System.out.println(new String(params.getBytes("UTF-8")));

        return params;
    }

    public String saveMutiRecords1(@RequestBody String params, HttpServletRequest request) throws IOException {
        /*BufferedReader reader = request.getReader();
        StringBuilder bodyStr = new StringBuilder();
        String s = reader.readLine(false);
        while (s != null){
            bodyStr.append(s);
            s = reader.readLine();
        }
        System.out.println("接收到的值："+bodyStr.toString());*/
        return params;
    }


    @GetMapping(value = "list",produces = {"application/json; charset=UTF-8"})
    public String list(PageDomain<PoliceStrength> page){
        PoliceStrength queryParams = page.getQueryParams();
        String regionId = queryParams.getRegionId();
        System.out.println(regionId);
        String pNum = queryParams.getPnum();
        System.out.println(pNum);
        String s = gson.toJson(page);
        System.out.println(s);
        return s;
    }

    @NacosValue(value = "${config}",autoRefreshed = true)
    private String config;

    @GetMapping(value = "getConfig")
    public String getConfig(){
        System.out.println(config);
        return config;
    }





}
