package com.ek.study.instance;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/14
 */
public class InstanceOfStudy {
    public static void main(String[] args) {
        ITop realClass = new RealClass();
        ITop topClass = new TopClass();

        System.out.println(topClass instanceof ITop);
        System.out.println(topClass instanceof TopClass);
        System.out.println(topClass instanceof RealClass);

        System.out.println(RealClass.class.isInstance(topClass));
        System.out.println(TopClass.class.isInstance(topClass));
        System.out.println(ITop.class.isInstance(topClass));
        System.out.println(InstanceOfStudy.class.isInstance(topClass));


    }
}


interface ITop {
}

class TopClass implements ITop {
}

class RealClass extends TopClass {
}
