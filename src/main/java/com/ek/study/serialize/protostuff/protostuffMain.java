package com.ek.study.serialize.protostuff;

import com.alibaba.fastjson.JSON;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class protostuffMain {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Entity entity = new Entity();
        entity.setName("eric");
        entity.setJob("it");

        Schema<Entity> schema = RuntimeSchema.getSchema(Entity.class);


        byte[] bytes = ProtostuffIOUtil.toByteArray(entity, schema, LinkedBuffer.allocate(256));
        System.out.println(bytes.length);

        Entity newEntity = Entity.class.newInstance();

        ProtostuffIOUtil.mergeFrom(bytes, newEntity, schema);

        System.out.println(newEntity);

        System.out.println(JSON.toJSONString(entity).getBytes().length).;

    }


}
