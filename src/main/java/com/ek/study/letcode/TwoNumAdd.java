package com.ek.study.letcode;

/**
 * <p>please add class desc</p>
 *
 * @author lazyman
 * @version 1.0.0
 * @date 2021/8/8
 */
public class TwoNumAdd {

    public static void main(String[] args){

        int[] numbers = new int[]{2,7,11,15};
        int target = 9;

        int[] ints = new Solution().twoSum(numbers, target);
        System.out.println(ints[0]+"," + ints[1]);
    }

}
class Solution {
    public int[] twoSum(int[] numbers, int target) {

        if( numbers == null || numbers.length<2 ){
            return new int[0];
        }

        int i=0, j=numbers.length-1;
        while(i < j){
            int sum = numbers[i] + numbers[j];
            if( sum == target ){
                return new int[]{i + 1, j + 1};
            } else if( sum > target ){
                j--;
            }else{
                i++;
            }

        }
        return new int[0];
    }
}