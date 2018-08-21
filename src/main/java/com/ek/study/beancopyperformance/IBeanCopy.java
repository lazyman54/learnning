package com.ek.study.beancopyperformance;

import java.lang.reflect.InvocationTargetException;

public interface IBeanCopy {

    void copyBean(Object source, Object target) throws InvocationTargetException, IllegalAccessException;

}
