package com.ek.study.crypto;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;

public class RsaUtilStudy {

    public static final String RSA_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmCDfKrID2cLtCGJ9CtT+g8LBdnYD14VNYAPB5VjCppAbNHRCX3HvYd80Sq/DkqmGt6YvwAt8Jla6IJEPqlDLX08xSieLAwxavzvFW6v/b8AOGeSCvlCrbdNoq0VMY9V1bpps8yUvtmDWrpwlhPeiC111VyE96n0vJz43kUY4aU5EYixu9XVeAptVvrRP9tXY5vBYJgeOooP62ihF1Zohd+INUY68AobKMzxzxU+y2W/HwBoqB1Ptk4gtGt9zd6zbgrhC5Z/9pxMfHTV4a04/ejucIv5ezItCmD6tcS1KvSAPA9odVfmLe2ujB9GZoVPmEp28x7xlEQSlgVZeA28Y/QIDAQAB";
    public static final String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCYIN8qsgPZwu0IYn0K1P6DwsF2dgPXhU1gA8HlWMKmkBs0dEJfce9h3zRKr8OSqYa3pi/AC3wmVrogkQ+qUMtfTzFKJ4sDDFq/O8Vbq/9vwA4Z5IK+UKtt02irRUxj1XVummzzJS+2YNaunCWE96ILXXVXIT3qfS8nPjeRRjhpTkRiLG71dV4Cm1W+tE/21djm8FgmB46ig/raKEXVmiF34g1RjrwChsozPHPFT7LZb8fAGioHU+2TiC0a33N3rNuCuELln/2nEx8dNXhrTj96O5wi/l7Mi0KYPq1xLUq9IA8D2h1V+Yt7a6MH0ZmhU+YSnbzHvGURBKWBVl4Dbxj9AgMBAAECggEBAIEEl9/g240qWNacmInAPB5I5MRvsDBvOEDuztmQXnzeiIQC+Wty+F8b6oBQ8l+G8N79zU9lLiRhtzb5b7y1CtXJFvdZ8/Zg2vGGYomtpFQFILBq8UswpULeYc2nVvLwTMjBpRbM+dsF4+xwNw2+6qoS1/pOEfG1u3DfvkaxtBPx4Rw+VubELpu0FA2HF4zb0cYL6nm708ONOJ2YGOSJYNgDerOvxBwm02TZE8aZwEbkLKEz5IiOp+ADnYu6Uw0di8vleZfgkonqs7qMXFP+mUKTXKQKCzOmjfbfAZpYR1daoxC6MIViOI/BgPXh9WxRjFJdhJ9h9l4yILSedvMYbGECgYEA3GBDA0UiFI8Icg1yfPlJBVNeoZWHkmbshVN59BbqgH/gmzJDQ3cJ4BSoEOAF1ncwAQQ4EA3fdg+DM/RAAMz++38oGJJ1FKRSF00LMHLgP9+YBhZTJBUiGQ3XRx62SD/jO5YJEhGctAYh/0pqsZvKWML280tK7LSz0BpPNNvw1rkCgYEAsLhYLYb1nquQftf5PCguNsYs8D7h1ct9KvsJSI2eRaQw1kyykMIn0qKxXRuHmBzA3PqHtkqrdGBBzPg26LaypH6btvNG3bSlbwQkaMDmwlXwdRs3OWtDQe+jDGP3wnbkCnMCxvtpQTf9/mevzWclPfFddOwjU+irNMvU6sYscmUCgYAT5mVmwDFppdjfY7N48ZHUd0jOUpBhegXVrEoQ7pjSbbm8ifdk+jTcFUFv9KrSSQT+soDVgksxgAkbAe1dpSVJVJwsS91itcvr6rsd7uGtIuW5dv6e/YGMvaE4/N78+zfrw4qiJRD/eKZNRU0JKMZG6aQfKCdl5JNwMVABt/KxkQKBgGOQCIXl4l35tNfx4AZncNoFPu4/z0Z/dc6QToOk3gXIYodqALST3pHKfXjCeNtXX8VgFLO8T+qqDIhpU646uFz6w8oIN+XSz93DDKxD+f6FIosnwS5FY0gztb3WWJEkIIhOkcL/hCZtYFlDm4Vy1kWWxZuJKsIsdMCzEgsPiZ0NAoGBALmACJOF6GqDQ0K9NVBxqZ4P9xzelns6QM7Hw8aYAco6gcpX3JBvhFifPcskbS6BfJfAWeGLAsA4WuuWeXRg+B+JhBftg26h5cmcmB87MmAZnU41vDmdOIWE+2mW4/GQi/DvBSw5B+pTazT2ro1EsopyvL+OaH1ospORfJqSLhYC";


    public static void main(String[] args) throws Exception {
        KeyPair keyPair = rsaKeyGenerate();
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
    }

    private static KeyPair rsaKeyGenerate() throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(2048, SecureRandom.getInstance("SHA1PRNG"));

        return keyPairGenerator.generateKeyPair();

    }


    private static String encWithPublicKey(String data) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");

    }

}
