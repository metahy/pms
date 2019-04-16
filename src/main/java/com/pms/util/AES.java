package com.pms.util;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class AES {
    public static void main(String[] args) {
        String s = desEncryption("xiehuaxin1124", "ttt");
        System.out.println(s);
        String s1 = desDecryption(s, "ttt");
        System.out.println(s1);
    }

    public static String desEncryption(String password, String key) {
        try {
            //1.生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(new SecureRandom(key.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();

            //2.转换KEY
            Key keyIn = new SecretKeySpec(byteKey,"AES");

            //3.加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keyIn);
            return Hex.encodeHexString(cipher.doFinal(password.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String desDecryption(String cipherText, String key) {
        try {
            //1.生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(new SecureRandom(key.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();

            //2.转换KEY
            Key keyIn = new SecretKeySpec(byteKey,"AES");

            //3.解密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keyIn);
            return new String(cipher.doFinal(Hex.decodeHex(cipherText)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
