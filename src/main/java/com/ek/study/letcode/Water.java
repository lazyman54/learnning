package com.ek.study.letcode;

/**
 * <p>please add class desc</p>
 *
 * @author lazyman
 * @version 1.0.0
 * @date 2021/8/8
 */
public class Water {

    public static void main(String[] args){

        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        //int[] height = new int[]{4,3,2,1,4};

        new Water().maxArea(height);
    }

    public int maxArea(int[] height){

        int i = 0;
        int j = height.length - 1;
        int maxWater = 0;
        while(true){

            if( i == j ){
                System.out.println(i);
                break;
            }

            int water = area(i, j, height[i], height[j]);
            if( water > maxWater ){
                maxWater = water;
            }
            System.out.println("i=" + i + ",j=" + j + ",water: " + water);

            if(height[i] > height[j] ){
                j--;
            }else{
                i++;
            }
//            if( height[i + 1] > height[i] ){
//                i++;
//            } else if( height[j - 1] > height[j] ){
//                j--;
//            } else{
//                i++;
//            }
        }
        System.out.println(maxWater);
        return maxWater;

    }

    private int area(int i, int j, int a, int b){
        int h = Math.min(a, b);
        int d = j - i;
        return h * d;
    }
}
