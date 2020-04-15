package com.ek.study.serialize.json;

import com.alibaba.fastjson.JSON;

public class FastJsonStudy {

    public static void main(String[] args) {
        Data data = new Data();
        data.setName("eric");
        String abc = "{\"name\":\"eric\"}";
        JSON.parseObject(abc, Data.class);
        System.out.println(JSON.toJSONString(data));
    }

}


@lombok.Data
class Data {
    private String name;
    private Integer age;
    private Config config;

    public Config getAbc() {
        return config;
    }
}

@lombok.Data
class Config {
    private String configData;
}