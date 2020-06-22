package com.ek.study.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author 80213134
 */
public class EventConsumer2 {

    @Subscribe
    public void consume(Data eventData) {
        System.out.println(String.format("consumer2 consume %s", eventData));
    }
}
