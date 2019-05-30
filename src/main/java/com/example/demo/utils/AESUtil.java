package com.example.demo.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

/**
 * @version V1.0
 * @desc AES 加密工具类
 */
public class AESUtil {

    private static class BouncyCastleProviderInner {
        private static BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
    }

    public static BouncyCastleProvider getBouncyCastleProvider() {
        return BouncyCastleProviderInner.bouncyCastleProvider;
    }

    private static final String ALGORITHM_CBC = "AES/CBC/PKCS7Padding";
    private static final String ALGORITHM_EBC = "AES/ECB/PKCS7Padding";

    /**
     * aes_ecb加密
     *
     * @param srcData 待加密字符串
     * @param key     密钥
     *
     * @return 16进制字符串
     *
     * @throws Exception
     * @Title: AES_ecb_encrypt
     */
    public static String AES_ecb_encrypt(String srcData, byte[] key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Security.addProvider(getBouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(ALGORITHM_EBC, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return parseByte2HexStr(cipher.doFinal(srcData.getBytes()));
    }

    /**
     * aes_ecb解密
     *
     * @param encData 16进制的加密字符串
     * @param key     密钥
     *
     * @return String 字符串
     *
     * @throws Exception
     * @Title: AES_ecb_decrypt
     */
    public static String AES_ecb_decrypt(String encData, byte[] key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Security.addProvider(getBouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(ALGORITHM_EBC, "BC");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decbbdt = cipher.doFinal(parseHexStr2Byte(encData));
        return new String(decbbdt);
    }

    /**
     * aes_cbc加密
     *
     * @param srcData 待加密字符串
     * @param key     密钥
     * @param iv      向量
     *
     * @return String 16进制字符串
     *
     * @throws Exception
     * @Title: AES_cbc_encrypt
     */
    public static String AES_cbc_encrypt(String srcData, byte[] key, byte[] iv) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Security.addProvider(getBouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(ALGORITHM_CBC, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] encData = cipher.doFinal(srcData.getBytes());
        return parseByte2HexStr(encData);
    }

    /**
     * aes_cbc解密
     *
     * @param encData 16进制的加密字符串
     * @param key     密钥
     * @param iv      向量
     *
     * @return String 字符串
     *
     * @throws Exception
     * @Title: AES_cbc_decrypt
     */
    public static String AES_cbc_decrypt(String encData, byte[] key, byte[] iv) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Security.addProvider(getBouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(ALGORITHM_CBC, "BC");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] decbbdt = cipher.doFinal(parseHexStr2Byte(encData));
        return new String(decbbdt);
    }

    /**
     * 将byte[]转换成16进制字符串
     *
     * @param buf 字节数组
     *
     * @return String 16进制字符串
     *
     * @Title: parseByte2HexStr
     * @Description:
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str 16进制字符串
     *
     * @return byte[]
     *
     * @Title: parseHexStr2Byte
     */
    public static byte[] parseHexStr2Byte(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }

    /**
     * 将字符串转换成byte[]
     *
     * @param str 字符串
     *
     * @return byte[]
     *
     * @Title: parseStr2Byte
     */
    public static byte[] parseStr2Byte(String str) {
        return str.getBytes();
    }

    /**
     * 将byte[]转换成字符串
     *
     * @param buf 字节数组
     *
     * @return String
     *
     * @Title: parseByte2Str
     */
    public static String parseByte2Str(byte[] buf) {
        return new String(buf);
    }

    /**
     * 根据字符串生成随机密钥
     *
     * @param encodeRules 加密规则字符串
     *
     * @return byte[]
     *
     * @throws NoSuchAlgorithmException
     * @throws Exception
     * @Title: generateKey
     */
    public static byte[] generateKey(String encodeRules) throws NoSuchAlgorithmException {
        // 1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        // 2.根据ecnodeRules规则初始化密钥生成器，根据传入的字节数组生成随机源
        keygen.init(256, new SecureRandom(encodeRules.getBytes()));
        // 3.产生原始对称密钥
        SecretKey original_key = keygen.generateKey();
        // 4.获得原始对称密钥的字节数组
        byte[] bts = original_key.getEncoded();
        return bts;
    }

    /**
     * 根据指定字符串生成256位密钥
     *
     * @param datastr
     *
     * @return byte[]
     *
     * @Title: tohash256Key
     */
    public static byte[] tohash256Key(String datastr) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            digester.update(datastr.getBytes());
            byte[] hex = digester.digest();
            return hex;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 根据指定字符串生成128位密钥
     *
     * @param datastr
     *
     * @return byte[]
     *
     * @Title: tohash128Key
     */
    public static byte[] tohash128Key(String datastr) {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            digester.update(datastr.getBytes());
            byte[] hex = digester.digest();
            return hex;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            String password = "生成加密规则";
            // byte[] key = generateKey(password);
            byte[] key = tohash256Key(password);
            byte[] key2 = tohash128Key(password);
            System.out.println("key====" + parseByte2HexStr(key));
            System.out.println("key2====" + parseByte2HexStr(key2));
            String content = "jianglailailailailailai";
            // ===========aes_ecb加解密===========
            String encodeStr = AES_ecb_encrypt(content, key);
            System.out.println(encodeStr);
            String decodeStr = AES_ecb_decrypt(encodeStr, key);
            System.out.println(decodeStr);
            // ===========aes_cbc加解密===========
            String cbcEncodeStr = AES_cbc_encrypt(content, key, key2);
            System.out.println(cbcEncodeStr);
            String cbcDecodeStr = AES_cbc_decrypt(cbcEncodeStr, key, key2);
            System.out.println(cbcDecodeStr);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 自动生成AES128位密钥
     */
    public static void getA221(){
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String s = parseByte2HexStr(b);
            System.out.println(s);
            System.out.println("十六进制密钥长度为"+s.length());
            System.out.println("二进制密钥的长度为"+s.length()*4);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("没有此算法。");
        }
    }
}
