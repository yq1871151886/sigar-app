package com.tiamaes.cloud.dmd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2021/2/4 11:23
 */
public class Test {

    public static void main(String[] args) {
        String mon = "[\n" +
                "            {\n" +
                "                \"name\": \"queryByCondition\",\n" +
                "                \"parameterTypes\": [\n" +
                "                    \"com.wdk.finance.bill.request.AccOutputMngRequest\"\n" +
                "                ],\n" +
                "                \"returnType\": \"com.wdk.finance.bill.common.result.FinBillResult\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"update\",\n" +
                "                \"parameterTypes\": [\n" +
                "                    \"com.wdk.finance.bill.model.AccOutputMngDTO\"\n" +
                "                ],\n" +
                "                \"returnType\": \"com.wdk.finance.bill.common.result.FinBillResult\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"delete\",\n" +
                "                \"parameterTypes\": [\n" +
                "                    \"java.lang.Long\"\n" +
                "                ],\n" +
                "                \"returnType\": \"com.wdk.finance.bill.common.result.FinBillResult\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"insert\",\n" +
                "                \"parameterTypes\": [\n" +
                "                    \"com.wdk.finance.bill.model.AccOutputMngDTO\"\n" +
                "                ],\n" +
                "                \"returnType\": \"com.wdk.finance.bill.common.result.FinBillResult\"\n" +
                "            }\n" +
                "        ]";


        String ty = "[\n" +
                "            {\n" +
                "                \"type\": \"com.wdk.finance.bill.request.AccOutputMngRequest\",\n" +
                "                \"properties\": {\n" +
                "                    \"gmtModified\": {\n" +
                "                        \"type\": \"java.util.Date\"\n" +
                "                    },\n" +
                "                    \"extendParam\": {\n" +
                "                        \"type\": \"java.util.Map<java.lang.String, java.lang.String>\"\n" +
                "                    },\n" +
                "                    \"name\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    },\n" +
                "                    \"recordFlag\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    },\n" +
                "                    \"arguments\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    },\n" +
                "                    \"id\": {\n" +
                "                        \"type\": \"java.lang.Long\"\n" +
                "                    },\n" +
                "                    \"gmtCreate\": {\n" +
                "                        \"type\": \"java.util.Date\"\n" +
                "                    },\n" +
                "                    \"type\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    },\n" +
                "                    \"script\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"type\": \"com.wdk.finance.bill.common.result.FinBillResult\",\n" +
                "                \"properties\": {\n" +
                "                    \"code\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    },\n" +
                "                    \"data\": {\n" +
                "                        \"type\": \"java.lang.Object\"\n" +
                "                    },\n" +
                "                    \"success\": {\n" +
                "                        \"type\": \"com.wdk.finance.bill.common.result.FinBillResult1\",\n" +
                "\t\t\t\t\t\t\"properties\": {\n" +
                "\t\t\t\t\t\t\t\"abc\":{\n" +
                "\t\t\t\t\t\t\t\t\"type\":\"java.lang.Object\"\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\n" +
                "                    },\n" +
                "                    \"message\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"type\": \"com.wdk.finance.bill.model.AccOutputMngDTO\",\n" +
                "                \"properties\": {\n" +
                "                    \"gmtModified\": {\n" +
                "                        \"type\": \"java.util.Date\"\n" +
                "                    },\n" +
                "                    \"name\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    },\n" +
                "                    \"recordFlag\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    },\n" +
                "                    \"arguments\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    },\n" +
                "                    \"id\": {\n" +
                "                        \"type\": \"java.lang.Long\"\n" +
                "                    },\n" +
                "                    \"gmtCreate\": {\n" +
                "                        \"type\": \"java.util.Date\"\n" +
                "                    },\n" +
                "                    \"type\": {\n" +
                "                        \"type\": \"java.lang.String\"\n" +
                "                    },\n" +
                "                    \"script\": {\n" +
                "                        \"type\": \"com.wdk.finance.bill.common.result.FinBillResult1\",\n" +
                "\t\t\t\t\t\t\"properties\": {\n" +
                "\t\t\t\t\t\t\t\"abc\":{\n" +
                "\t\t\t\t\t\t\t\t\"type\":\"java.lang.Object\"\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t}\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        ]";

        List<Method> monthods = JSONArray.parseArray(mon, Method.class);
        List<Type> types = JSONArray.parseArray(ty, Type.class);

        ArrayList<Result> results = new ArrayList<>();
        for (Type type:types){
            monthods.forEach(monthod->{
                monthod.getParameterTypes().forEach(parameter->{
                    if (parameter.equals(type.getType())){
                        Result  result= new Result();
                        get(type.getProperties(), result,parameter,type.getType(),false);
                        results.add(result);
                    }
                });
            });
        }
        System.out.println(JSON.toJSONString(results));
    }





    public static Result get(Map<String,Map<String,Object>> properties,Result result,String parameter,String type,Boolean sw){
        result.setParameterType(parameter);
        for (Map.Entry<String, Map<String, Object>> propertie:properties.entrySet()){
                Map<String, Object> value = propertie.getValue();
                if (!value.keySet().contains("properties")){
                    for (Map.Entry<String,Object> data:value.entrySet()){
                        Map<String,Object> p = new HashMap<>();
                        p.put(data.getKey(), data.getValue());
                        Map<String, Map<String, Object>> a = result.getProperties();
                        if ( a == null){
                            a  = new HashMap<>();
                        }
                        a.put(propertie.getKey(),p);
                        result.setProperties(a);
                    }
                }else {
                    get((Map<String, Map<String, Object>>) value.get("properties"),result,parameter,type,true);
                }
            }
        return result;
    }

}
