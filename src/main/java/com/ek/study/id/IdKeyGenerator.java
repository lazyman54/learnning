package com.ek.study.id;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/9
 */
public interface IdKeyGenerator<T> {

    int setWorkId(int workId);

    T generateId();

    Class getIdType();


}
