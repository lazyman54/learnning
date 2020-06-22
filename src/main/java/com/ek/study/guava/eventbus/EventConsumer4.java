package com.ek.study.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author 80213134
 */
public class EventConsumer4 {

    @Subscribe
    public void consume(String eventData) {
        System.out.println(String.format("consumer4 consume %s", eventData));
    }

}
