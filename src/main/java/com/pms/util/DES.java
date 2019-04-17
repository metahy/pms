package com.pms.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;

public class DES {
    public static void main(String[] args) {
        String s = desEncryption("xiehuaxin1124", "ttt");
        System.out.println(s);
        String s1 = desDecryption(s, "ttt");
        System.out.println(s1);
    }

    public static String desEncryption(String password, String key) {
        try {
            //1.生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESEde");
            keyGenerator.init(new SecureRandom(key.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //2.转换KEY
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESEde");
            Key convertKey = factory.generateSecret(deSedeKeySpec);

            //3.加密
            Cipher cipher = Cipher.getInstance("DESEde");
            cipher.init(Cipher.ENCRYPT_MODE, convertKey);
            return Hex.encodeHexString(cipher.doFinal(password.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String desDecryption(String cipherText, String key) {
        try {
            //1.生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESEde");
            keyGenerator.init(new SecureRandom(key.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //2.转换KEY
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESEde");
            Key convertKey = factory.generateSecret(deSedeKeySpec);

            //3.解密
            Cipher cipher = Cipher.getInstance("DESEde");
            cipher.init(Cipher.DECRYPT_MODE, convertKey);
            return new String(cipher.doFinal(Hex.decodeHex(cipherText)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}