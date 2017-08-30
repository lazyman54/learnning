package com.ek.study.finals;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/7/19
 */
public class FinalStudy {

    public static void main(String[] args) {
        Thread reader = new Thread(FinalClass::write);
        Thread writer = new Thread(FinalClass::read);

        writer.start();
        reader.start();

    }
}

class FinalClass {

    public int i;
    public final int j;
    static FinalClass finalClass;

    public FinalClass() {
        this.j = 2;
        this.i = 1;
    }

    public static void write() {
        finalClass = new FinalClass();
    }

    public static void read() {
        FinalClass finalClass1 = finalClass;
        System.out.println("i: " + finalClass1.i);
        System.out.println("j: " + finalClass1.j);
    }

}
