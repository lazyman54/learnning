package com.ek.study.letcode;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.apache.storm.nimbus.ILeaderElector;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

public class StrToInt {

    private static final List<Character> NUM_CHAR = Lists
        .newArrayList('0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9');

    private static final List<Character> SIGN_CHAR = Lists.newArrayList('+', '-');


    public static void main(String[] args){
        System.out.println(strToInt("32424214235124235421352134"));
    }

    private static int strToInt(String str){
        if( isStrEmpty(str) ){
            throw new IllegalArgumentException("str can't be null or empty.");
        }

        if( isStrOutOfRange(str) ){
            throw new IllegalArgumentException("str is out of Integer range.");
        }
        int sum = 0;
        boolean numSing = true;
        for( int i = 0; i < str.length(); i++ ){
            char ch = str.charAt(i);
            if( NUM_CHAR.contains(ch) ){
                sum = sum * 10 + (ch - '0');
            } else if( SIGN_CHAR.contains(ch) && i == 0 ){
                numSing = '+' == ch;
            } else{
                throw new IllegalArgumentException("str has non number char.");
            }


        }
        if( numSing ){
            return sum;
        } else{
            return 0 - sum;
        }


    }

    private static boolean isStrOutOfRange(String str){

        if( str.startsWith("-") ){
            int minInt = Integer.MIN_VALUE;
        } else if( str.startsWith("+") ){
            int maxInt = Integer.MAX_VALUE;

        }

        return false;
    }

    private static boolean isStrEmpty(String str){
        if( null == str || "".equals(str.trim()) ){
            return true;
        }
        return false;

    }

}

