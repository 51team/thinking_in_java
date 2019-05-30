package com.example.demo.utils;

public class AESUtilTest {

	public static void main(String[] args) throws Exception {
		AESUtil.getA221();

		String aeskey = "E0E9B474CF7DE5D50D553D9ADE7CC47D";
		byte[]  key = AESUtil.parseHexStr2Byte(aeskey);
        System.out.println("key====" + AESUtil.parseByte2HexStr(key));
//        String content=getDecrypt("飒飒多");
        String content = "PN_ONLINE_AP_VENDOR_000001";
        // ===========aes_ecb加解密===========
//        String encodeStr = AESUtil.AES_ecb_encrypt(content, key);
//        System.out.println(encodeStr);
		String ase=getEncrypt(content);
		System.out.println(getDecrypt(ase));
	}

	public static String getEncrypt(String content) throws Exception {
//		String password = "9484276F6BEF41F6D3204DBCFFB98D4223CE43C038617459FCD0FA593520C766";
		String password = "E0E9B474CF7DE5D50D553D9ADE7CC47D";
		byte[] key = AESUtil.parseHexStr2Byte(password);
		System.out.println("key====" + AESUtil.parseByte2HexStr(key));
		// ===========aes_ecb加密=========== 当前使用
		String encodeStr = AESUtil.AES_ecb_encrypt(content, key);
		System.out.println("加密："+encodeStr);
		return encodeStr;
	}
	
	public static String getDecrypt(String content) throws Exception {
		if(null==content){
			return null;
		}
//		String password = "9484276F6BEF41F6D3204DBCFFB98D4223CE43C038617459FCD0FA593520C766";
		String password = "E0E9B474CF7DE5D50D553D9ADE7CC47D";
		// byte[] key = generateKey(password);

		byte[] key = AESUtil.parseHexStr2Byte(password);
		System.out.println("key====" + AESUtil.parseByte2HexStr(key));
		// ===========aes_ecb解密=========== 当前使用
		String decodeStr = AESUtil.AES_ecb_decrypt(content, key);
		System.out.println("解密"+decodeStr);
		return decodeStr;
	}

}
