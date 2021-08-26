package com.ek.study.tx.tmp;

import com.ek.study.concurrent.SynchronizedStudy;
import lombok.Synchronized;

/**
 * <p>please add class desc</p>
 *
 * @author lazyman
 * @version 1.0.0
 * @date 2021/8/25
 */
public class SignTon {


    private volatile static SignTon instance ;


    public static SignTon instance(){

        if( instance == null  ){
            synchronized(SignTon.class){
                if(instance == null  ){
                    instance = new SignTon();
                }
            }
        }
        return instance;

    }



}
