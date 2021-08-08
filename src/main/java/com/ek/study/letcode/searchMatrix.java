package com.ek.study.letcode;

/**
 * <p>please add class desc</p>
 *
 * @author lazyman
 * @version 1.0.0
 * @date 2021/8/8
 */
public class searchMatrix {

    public static void main(String[] args){
        int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 20;
        System.out.println(new SearchMatrixSolution().searchMatrix(matrix, target));
    }
}

class SearchMatrixSolution{

    public boolean searchMatrix(int[][] matrix, int target){

        int m = matrix.length;
        int n = matrix[0].length;
        int x=0,y=n-1;
        while(x < m && y >= 0){
            if( matrix[x][y] == target ){
                return true;
            } else if( matrix[x][y] > target ){
                y--;
            } else{
                x++;
            }
        }
        return false;
    }




}