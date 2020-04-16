package com.ek.study.crypto;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AesUtilStudy {

    public static final String aesKey = "sfohofj2w0464316";
    public static final String encodeModel_CBC = "AES/CBC/PKCS5Padding";
    public static final String encodeModel_ECB = "AES/ECB/PKCS5Padding";
    public static final String encodeModel_CTR = "AES/CTR/PKCS5Padding";
//    public static final String encodeModel_CBC = "AES/CBC/NOPadding";
//    public static final String encodeModel_ECB = "AES/ECB/NOPadding";
//    public static final String encodeModel_CTR = "AES/CTR/NOPadding";

    public static void main(String[] args) throws Exception {
        String originStr = "my name is eric,what's your name";
        ecb(originStr);
        cbc(originStr);
        ctr(originStr);
    }

    private static void ecb(String data) throws Exception {
        String enStr = aesEcbEncode(data.getBytes());
        System.out.println(enStr);
        String originStr = aesEcbDecode(Base64.getDecoder().decode(enStr));
        System.out.println(originStr);

    }

    private static void cbc(String data) throws Exception {
        String enStr = aesCbcEncode(data.getBytes());
        System.out.println(enStr);
        enStr = "OCKuuaJNogK7ylopQMsXeTffI/vBik94tSsqXEnnl5cFzBvNjPhHk+X0MX3mwNgA";
        String originStr = aesCbcDecode(Base64.getDecoder().decode(enStr));
        System.out.println(originStr);

    }

    private static void ctr(String data) throws Exception {
        String enStr = aesCtrEncode(data.getBytes());
        System.out.println(enStr);
        enStr = "mynameisericandyou";
        String originStr = aesCtrDecode(Base64.getDecoder().decode(enStr));
        System.out.println(originStr);

    }


    private static SecretKey aesKeyGenerate() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(aesKey.getBytes());
        keyGenerator.init(128, secureRandom);
        return keyGenerator.generateKey();
    }

    private static String aesEcbDecode(byte[] data) throws Exception {

        SecretKey secretKey = aesKeyGenerate();

        //IvParameterSpec iv = new IvParameterSpec(secretKey.getEncoded());

        Cipher cipher = Cipher.getInstance(encodeModel_ECB);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] result = cipher.doFinal(data);

        return new String(result);

    }

    private static String aesEcbEncode(byte[] data) throws Exception {

        SecretKey secretKey = aesKeyGenerate();

        Cipher cipher = Cipher.getInstance(encodeModel_ECB);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] result = cipher.doFinal(data);

        return Base64.getEncoder().encodeToString(result);


    }

    private static String aesCbcDecode(byte[] data) throws Exception {

        SecretKey secretKey = aesKeyGenerate();

        IvParameterSpec iv = new IvParameterSpec(secretKey.getEncoded());
        //IvParameterSpec iv = new IvParameterSpec("23sfasoo2fawf856".getBytes());
        Cipher cipher = Cipher.getInstance(encodeModel_CBC);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] result = cipher.doFinal(data);

        return new String(result);

    }

    private static String aesCbcEncode(byte[] data) throws Exception {

        SecretKey secretKey = aesKeyGenerate();

        //IvParameterSpec iv = new IvParameterSpec("23sfasoo2fawf856".getBytes());
        IvParameterSpec iv = new IvParameterSpec(secretKey.getEncoded());
        Cipher cipher = Cipher.getInstance(encodeModel_CBC);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] result = cipher.doFinal(data);

        return Base64.getEncoder().encodeToString(result);


    }

    private static String aesCtrDecode(byte[] data) throws Exception {

        SecretKey secretKey = aesKeyGenerate();

        IvParameterSpec iv = new IvParameterSpec("23sfasoo2fawf856".getBytes());
        Cipher cipher = Cipher.getInstance(encodeModel_CTR);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] result = cipher.doFinal(data);

        return new String(result);

    }

    private static String aesCtrEncode(byte[] data) throws Exception {

        SecretKey secretKey = aesKeyGenerate();

        IvParameterSpec iv = new IvParameterSpec("23sfasoo2fawf856".getBytes());
        //IvParameterSpec iv = new IvParameterSpec(secretKey.getEncoded());
        Cipher cipher = Cipher.getInstance(encodeModel_CTR);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] result = cipher.doFinal(data);

        return Base64.getEncoder().encodeToString(result);


    }


}
