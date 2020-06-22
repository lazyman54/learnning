package com.ek.study.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author 80213134
 */
public class EventConsumer1 {

    @Subscribe
    public void consume(Integer eventData) {
        System.out.println(String.format("consumer1 consume %s", eventData));
    }


}
