package com.ek.study.size;

import java.util.Arrays;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/9/18
 */
public class JavaByteSizeStudy {
    public static void main(String[] args) {
        char[] chars = new char[25];
        for (int i = 0; i < 25; i++) {
            chars[i] = 'a';
        }
        byte[] bytes = new byte[50];
        StringBuilder sbf = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            sbf.append("a").append(i % 10);
        }
        int i = 0;
        System.out.println(sbf.toString().toCharArray().length);
        for (char aChar : sbf.toString().toCharArray()) {
            bytes[i++] = (byte) aChar;
        }
        System.out.println("char: " + Arrays.toString(chars));
        System.out.println("bytes: " + Arrays.toString(bytes));
        System.out.println("str: " + sbf.toString());
        System.out.println((char) bytes[1]);
    }
}
