package com.ek.study.letcode;

import static java.util.Arrays.*;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>please add class desc</p>
 *
 * @author lazyman
 * @version 1.0.0
 * @date 2021/8/8
 */
public class ThreeNumAdd {

    public static void main(String[] args){
//        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        int[] nums = new int[]{0, 0, 0, 0};

        List<List<Integer>> resultList = new ThreeNumAdd().threeSum(nums);
        System.out.println(resultList);
    }


    public List<List<Integer>> threeSum(int[] nums){

        if( null == nums || nums.length < 3 ){
            return new ArrayList<>(0);
        }
        sort(nums);

        List<List<Integer>> resultList = new ArrayList<>();
        for( int i = 0; i < nums.length - 2; i++ ){

            if( nums[i] > 0 ){
                break;
            }
            if( i > 0 && nums[i] == nums[i - 1] ){
                continue;
            }
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while(left < right){
                int sum = nums[left] + nums[right];
                if( sum < target ){
                    left++;
                } else if( sum > target ){
                    right--;
                } else{

                    if( left == i + 1 || nums[left] != nums[left - 1] ){
                        resultList.add(Lists.newArrayList(nums[i], nums[left], nums[right]));
                    }
                    left++;

                }
            }


        }
        return resultList;

    }

}
