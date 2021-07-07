package com.ek.study.serialize.json;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

public class JsonStudy {


    public static void main(String[] args){
        jsonSerialize();
    }

    public static void jsonSerialize(){

        JsonObj jsonObj = new JsonObj();
        jsonObj.setMsg("msg");

        System.out.println(JSON.toJSONString(jsonObj));
        System.out.println(new Gson().toJson(jsonObj));
    }



}
