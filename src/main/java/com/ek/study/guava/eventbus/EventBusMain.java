package com.ek.study.guava.eventbus;

import com.google.common.eventbus.EventBus;

public class EventBusMain {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();

        eventBus.register(new EventConsumer1());
        eventBus.register(new EventConsumer2());
        eventBus.register(new EventConsumer3());
        eventBus.register(new EventConsumer4());
        Data data = new Data();
        data.setData("data obj");
        eventBus.post("HeHe");
        eventBus.post(1);
        eventBus.post(data);


    }

}
