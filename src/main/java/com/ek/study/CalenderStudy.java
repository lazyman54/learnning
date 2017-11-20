package com.ek.study;

import java.util.Calendar;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/13
 */
public class CalenderStudy {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DATE, 13);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTime());
        System.out.println(cal.getTimeInMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() - cal.getTimeInMillis());
    }
}
