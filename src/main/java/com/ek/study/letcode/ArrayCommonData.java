package com.ek.study.letcode;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>please add class desc</p>
 *
 * @author lazyman
 * @version 1.0.0
 * @date 2021/8/13
 */
public class ArrayCommonData {

    public static void main(String[] args){
        int[][] array = new int[][]{{1, 2, 5, 7, 8}, {2, 5, 7, 8, 11}, {2, 5, 8, 9, 13}};

        Collection<Integer> solution = new ArrayCommonData().solution(array);
        System.out.println(solution);
    }


    private Collection<Integer> solution(int[][] arrays){

        int m = arrays.length;
        int n = arrays[0].length;
        Set<Integer> result = new HashSet<>();

        int[] indexArray = new int[m];//用来保存第m个数组，当前遍历到的下标

        for( int i = 0; i < n; i++ ){
            int data = arrays[0][i];
            for( int j = 1; j < m; j++ ){
                boolean match = false;
                for( int k = indexArray[j]; k < n; k++ ){
                    int temData = arrays[j][k];
                    if( temData == data ){
                        //无需移动，当前值匹配
                        match = true;
                        break;
                    } else if( temData < data ){
                        //当前行可以往后移动
                        indexArray[j] += 1;
                    } else{
                        //第一行可以往下移动了
                        break;
                    }
                }
                if( !match ){
                    continue;
                }
                if( j == m - 1 ){
                    result.add(data);
                }
            }
        }
        return result;
    }


}
