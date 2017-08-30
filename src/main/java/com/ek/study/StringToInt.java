package com.ek.study;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/27
 */
public class StringToInt {

    private static final int ASCLL_BASE_NUM = 48;

    public static int StrToInt(String str) throws IllegalArgumentException {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("str can not be none, please check");
        }
        long result = 0;
        for (char aChar : str.toCharArray()) {
            int realValue = (int) aChar - ASCLL_BASE_NUM;
            if (realValue < 0 || realValue > 9) {
                throw new IllegalArgumentException("str can only be num");
            }
            result = result * 10 + realValue;
            if (result > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("str must less than Max Int");
            }
        }
        return (int) result;

    }
}
