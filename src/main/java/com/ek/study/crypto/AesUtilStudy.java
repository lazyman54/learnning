package com.ek.study.crypto;


import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AesUtilStudy {

    public static final String aesKey = "sfohofj2w0464316";
    public static final String encodeModel_CBC = "AES/CBC/PKCS5Padding";
    public static final String encodeModel_ECB = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed("OK".getBytes());
        System.out.println(secureRandom.nextInt());
    }

    private static String aesEncode(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException {

        String key = "";

        Cipher cipher = Cipher.getInstance(encodeModel_CBC);
        //cipher.init(1, key);
        return "";


    }


}
