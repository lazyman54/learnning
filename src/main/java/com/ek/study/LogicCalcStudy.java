package com.ek.study;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/22
 */
public class LogicCalcStudy {
    public static void main(String[] args) throws InterruptedException {

        int negative = -131072;
        int easy = -128;

        int anInt = Integer.parseInt("01111111111111110000000000000000", 2);
        System.out.println(anInt);

       /* System.out.println(toBinaryString(negative));
        //System.out.println(toBinaryString(easy));
        Thread.sleep(100);
        //System.out.println(toBinaryString(1073741792));


        System.out.println(easy >> 2);
        System.out.println(easy >>> 2);*/

    }

    public static String toBinaryString(Integer number) {
        String binaryString = Integer.toBinaryString(number);
        System.out.println(binaryString);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i += 8) {
            System.out.println(i);
            sb.append(binaryString.substring(i, i + 8)).append(" ");
        }
        return sb.toString();
    }
}
