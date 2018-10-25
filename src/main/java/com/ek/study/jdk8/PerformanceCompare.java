package com.ek.study.jdk8;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PerformanceCompare {

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        ArrayList<Object> arrayList = Lists.newArrayListWithCapacity(100000);


        /*for (Object o : arrayList) {

        }*/

        int a = 128;
        int b = 120;
        System.out.println(Math.round(-11.5));

        arrayList.forEach(obj -> {
        });

        System.out.println("jdk7 cost: " + stopwatch.elapsed(TimeUnit.MICROSECONDS) + "micros");

    }

}

