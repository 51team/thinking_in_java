package com.example.demo.utils;


public class MD5Util {
	public static boolean vlidateMD5sign(String text, String sign) {
        String vsign=MD5sign(text);
        System.out.println("MD5验证签名生成的签名："+vsign);
        System.out.println("MD5验证签名生成的签名与原签名是否一致：sign=vsign true?false:\":"+(vsign.equals(sign)));
        return vsign.equals(sign)?true:false;
    }
    /**
     * MD5生成签名字符串
     *
     *
     *            需签名参数
     *
     *            MD5key
     * @return
     */
    public static String MD5sign(String signSrc) {
        String genSign = "";
        try {
            genSign = Md5Encrypt.md5(signSrc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genSign;
    }    
}
