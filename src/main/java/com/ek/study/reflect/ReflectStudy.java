package com.ek.study.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/13
 */
public class ReflectStudy {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        abstractReflect();

    }

    private static void reflect()throws NoSuchMethodException, NoSuchFieldException {
        Class<TargetSource> cls = TargetSource.class;

        Field[] fields = cls.getDeclaredFields();
        System.out.println("***************field*****************");
        for (Field field : fields) {
            System.out.println("--------------");
            System.out.println(field);
            System.out.println(field.getName());
            System.out.println(field.getGenericType());
            System.out.println(field.getDeclaringClass());
            System.out.println(field.getType());
        }

        System.out.println("***************method*****************");
        Method method1 = cls.getMethod("noArgMethod");
        System.out.println(method1);

        Method method2 = cls.getMethod("baseArgMethod", int.class);
        System.out.println(method2);
        Method method3 = cls.getMethod("objArgMethod", ArgObj.class);
        System.out.println(method3);
        Method method4 = cls.getMethod("listBaseArgMethod", List.class);
        System.out.println(method4);
        Method method5 = cls.getMethod("listObjArgMethod", List.class);
        System.out.println(method5);
        Method method6 = cls.getMethod("listObjArgMethod", cls.getDeclaredField("argObjs").getType());
        System.out.println(method6);
    }

    private static void abstractReflect() throws NoSuchFieldException, IllegalAccessException {

        Class<AbstractTargetSource> cls = AbstractTargetSource.class;

        Field key = cls.getDeclaredField("key");

        key.setAccessible(true);

        key.set(null, "abc1212");

        System.out.println(AbstractTargetSource.getKey());
        System.out.println(new TargetSource().getKey());


    }

}
