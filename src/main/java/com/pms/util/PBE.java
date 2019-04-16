package com.pms.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Hex;

public class PBE {

    private static byte[] salt = {-107, 127, -121, 96, 27, -17, -100, 56};

    public static void main(String[] args) {
        String s = desEncryption("xiehuaxin", "ttt");
        System.out.println(s);
        String s1 = desDecryption(s, "ttt1");
        System.out.println(s1);
    }

    public static String desEncryption(String password, String keyString) {
        try {
            //1. 口令与秘钥
            PBEKeySpec pbeKeySpec = new PBEKeySpec(keyString.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = factory.generateSecret(pbeKeySpec);

            //2. 加密
            PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);//100是你选择迭代的次数
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
            return Hex.encodeHexString(cipher.doFinal(password.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String desDecryption(String cipherText, String keyString) {
        try {
            //1. 口令与秘钥
            PBEKeySpec pbeKeySpec = new PBEKeySpec(keyString.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = factory.generateSecret(pbeKeySpec);

            //2.解密
            PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);//100是你选择迭代的次数
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
            return new String(cipher.doFinal(Hex.decodeHex(cipherText)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
