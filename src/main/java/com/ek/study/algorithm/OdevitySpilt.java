package com.ek.study.algorithm;

import java.util.Random;

/**
 * 奇偶数左右分开算法，给定任意一个数组，将奇数全部放在数组前半部分，偶数移动到数组后半部分
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/10/30
 */
public class OdevitySpilt {
    public static void main(String[] args) {

        int length = 102400;

        int[] source = new int[length];

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            source[i] = random.nextInt(length << 2);
        }


        OdevityAlgorithm odevityAlgorithm = new OdevityAlgorithm(source);
        for (int i : odevityAlgorithm.spilt()) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}

class OdevityAlgorithm {

    private int[] source;

    public OdevityAlgorithm(int[] source) {
        this.source = source;
    }

    public int[] spilt() {

        int endPos = source.length - 1;
        long current = System.currentTimeMillis();
        for (int index = 0; index < source.length; index++) {
            if ((endPos - 1 == index)) {
                break;
            }
            if (isOddNum(source[index])) {
                continue;
            }
            int tmp = source[endPos];
            source[endPos] = source[index];
            source[index] = tmp;
            endPos--;
            index--;
        }
        System.out.println("time: " + (System.currentTimeMillis() - current));
        return source;

    }

    private boolean isOddNum(int num) {
        return num % 2 == 1;
    }

}