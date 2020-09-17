package com.ek.study.serialize.json;

import com.alibaba.fastjson.JSON;
import lombok.Data;

public class FastJsonStudy {

    public static void main(String[] args) {
        JsonData data = new JsonData();
        data.setName("eric");
        JsonData.InnerData innerData = new JsonData.InnerData();
        innerData.setMsg("msg");
        data.setConfig(innerData);
        System.out.println(JSON.toJSONString(data));
    }

}

@Data
class JsonData {
    private String name;
    private InnerData config;

    @Data
    public static class InnerData {
        String msg;
    }
}
