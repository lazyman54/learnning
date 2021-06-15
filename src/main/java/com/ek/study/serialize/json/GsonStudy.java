package com.ek.study.serialize.json;

import com.ek.study.domain.BaseDomain;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class GsonStudy {

    private static Gson gson = new Gson();
    public static void main(String[] args){
        List<BaseDomain> domainList = new ArrayList<>();
        BaseDomain domain1 = new BaseDomain();
        domain1.setKey("key1");
        domain1.setValue("value1");
        BaseDomain domain2 = new BaseDomain();
        domain2.setKey("key2");
        domain2.setValue("value2");

        domainList.add(domain1);
        domainList.add(domain2);
        String str = gson.toJson(domainList);
        System.out.println(str);

        List<Object> objectList = gson.<List<Object>>fromJson(str, List.class);
        System.out.println(objectList.size());
        for( Object o : objectList ){
            System.out.println(o);
        }

    }

}
