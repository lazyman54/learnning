package com.ek.study.serialize;

public class ByteToStr {
    public static void main(String[] args) {
        String hexStr = "7b226572726f72223a7b22636f6465223a2230393939313031222c226d7367223a22e794" +
                "a8e688b7e989b4e69d83e5a4b1e8b4a5222c22706167655469746c65223a6e756c6c2c226c696e6b4d7367223a6e756c6c2c226c696e6b55726c223a6e756c6c7d2c2273756363657373223a66616c73657d";


        System.out.println(hexStr.length());
        byte[] bytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            String subStr = hexStr.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        System.out.println(new String(bytes));
    }

    String originStr = "0000   7b 22 65 72 72 6f 72 22 3a 7b 22 63 6f 64 65 22   {\"error\":{\"code\"\n" +
            "0010   3a 22 30 39 39 39 31 30 31 22 2c 22 6d 73 67 22   :\"0999101\",\"msg\"\n" +
            "0020   3a 22 e7 94 a8 e6 88 b7 e9 89 b4 e6 9d 83 e5 a4   :\"..............\n" +
            "0030   b1 e8 b4 a5 22 2c 22 70 61 67 65 54 69 74 6c 65   ....\",\"pageTitle\n" +
            "0040   22 3a 6e 75 6c 6c 2c 22 6c 69 6e 6b 4d 73 67 22   \":null,\"linkMsg\"\n" +
            "0050   3a 6e 75 6c 6c 2c 22 6c 69 6e 6b 55 72 6c 22 3a   :null,\"linkUrl\":\n" +
            "0060   6e 75 6c 6c 7d 2c 22 73 75 63 63 65 73 73 22 3a   null},\"success\":\n" +
            "0070   66 61 6c 73 65 7d                                 false}\n";


}
