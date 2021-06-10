package com.ek.study.strategy;

public enum TestStrategy implements IStrategy {



    STRATEGY_ONE(KeyEnum.Key_1){
        protected void abc(){
            System.out.println("abc");
        }
    };

    private final KeyEnum keyEnum;

    TestStrategy(KeyEnum keyEnum) {
        this.keyEnum = keyEnum;
    }

    protected abstract void abc();
}
