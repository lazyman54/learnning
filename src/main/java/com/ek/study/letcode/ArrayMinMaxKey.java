package com.ek.study.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.Max;

/**
 * <p>please add class desc</p>
 *
 * @author lazyman
 * @version 1.0.0
 * @date 2021/8/8
 */
public class ArrayMinMaxKey {

    public static void main(String[] args){

        int[] res = new int[]{2, 3, 1, 8, 9, 20, 12};

        List<Integer> result = MaxThenLeftAndMinThanRight.find(res);
        System.out.println(result);
    }

}

class MaxThenLeftAndMinThanRight{


    public static List<Integer> find(int[] res){

        if(null == res || res.length <=2){
            return new ArrayList<>(0);
        }

        List<Integer> resultList = new ArrayList<>();

        int[] minRight = new int[res.length];
        int min = Integer.MAX_VALUE;
        for( int i = res.length-1; i >= 0; i-- ){
            minRight[i] = Math.min(res[i], min);
            min = minRight[i];
        }
        int maxLeft = res[0];
        for( int i = 1; i < res.length-2; i++ ){
            if( res[i] > maxLeft ){
                maxLeft = res[i];
                if( res[i] < minRight[i+1] ){
                    resultList.add(i);
                }
            }
        }
        return resultList;

    }

}