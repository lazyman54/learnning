package com.ek.study.jmx;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/8/14
 */
public class JmxStudy  {

    public static void main(String[] args) {
        ClassLoadingMXBean loadingMXBean = ManagementFactory.getClassLoadingMXBean();
        System.out.println(loadingMXBean.getLoadedClassCount());
        System.out.println(loadingMXBean.getTotalLoadedClassCount());
        System.out.println(loadingMXBean.getUnloadedClassCount());

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println(threadMXBean.getObjectName());

    }

}
