package com.ek.study.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author 80213134
 */
public class EventConsumer3 {
    @Subscribe
    public void consume(String eventData) {
        System.out.println(String.format("consumer3 consume %s", eventData));
    }
}
