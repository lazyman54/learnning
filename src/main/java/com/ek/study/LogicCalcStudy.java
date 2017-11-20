package com.ek.study;

import java.math.BigInteger;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/22
 */
public class LogicCalcStudy {
    public static void main(String[] args) throws InterruptedException {

        int negative = -131072;
        int easy = -128;

        BigInteger bigInteger = new BigInteger("0000000000110011001001000101010100110110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000001", 2);
        BigInteger bigInteger2 = new BigInteger("110011001001000101010100110110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000001", 2);
        System.out.println(bigInteger2);
        System.out.println(bigInteger);

        long anInt = Long.parseLong("1101001111000011101111001000001000000", 2);
        System.out.println(anInt);

       /* System.out.println(toBinaryString(negative));
        //System.out.println(toBinaryString(easy));
        Thread.sleep(100);
        //System.out.println(toBinaryString(1073741792));


        System.out.println(easy >> 2);
        System.out.println(easy >>> 2);*/
        //System.out.println(toBinaryString(1510145737000L));
        //System.out.println(toBinaryString(86400000L));
        System.out.println(toBinaryString(1999999999999L));

    }

    public static String toBinaryString(Long number) {
        String binaryString = Long.toBinaryString(number);
        System.out.println(binaryString);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i += 8) {
            System.out.println(i);
            sb.append(binaryString.substring(i, i + 8)).append(" ");
        }
        return sb.toString();
    }
}
