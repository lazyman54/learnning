package com.ek.study.tmp;

import com.google.common.util.concurrent.RateLimiter;

/**
 * <p>please add class desc</p>
 *
 * @author lazyman
 * @version 1.0.0
 * @date 2021/8/13
 */
public class RateLimiterStudy {

    public static void main(String[] args){
        RateLimiter limiter = RateLimiter.create(10);
        long time = System.currentTimeMillis();
//        for( int i = 0; i < 2000; i++ ){
//            limiter.acquire();
//        }
        double result = limiter.acquire(20);
        System.out.println(result);
        result = limiter.acquire();
        System.out.println(result);
        System.out.println("cost: " + (System.currentTimeMillis() - time));
        if( result == 1 ){
            System.out.println("acquire ok");
        } else{
            System.out.println("acquire not ok");
        }

    }
}
