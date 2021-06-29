package com.ek.study.gc;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/8/14
 */
public class GcStudy {

    //1M
    Byte[] bytes2 = new Byte[1024 * 1024];
    //4M
    Byte[] bytes3 = new Byte[4096 * 1024];
    Byte[] bytes4 = new Byte[4096*1024];
    Byte[] bytes5 = new Byte[4096*1024];
    //Byte[] bytes6 = new Byte[4096*1024];

    //16M
    public static void main(String[] args) {
        GcStudy gcStudy = new GcStudy();
        System.out.println(gcStudy);
        while (true) {

        }
    }
}
