package com.ek.study;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/27
 */
public class SingletonEnumModel {

    private SingletonEnumModel() {
    }

    public static SingletonEnumModel instance() {
        return SingletonEnum.MySingleTon.getInstance();
    }

    private enum SingletonEnum {
        MySingleTon;
        private final SingletonEnumModel singletonEnumModel;

        SingletonEnum() {
            this.singletonEnumModel = new SingletonEnumModel();
        }

        private SingletonEnumModel getInstance() {
            return this.singletonEnumModel;
        }
    }
}
