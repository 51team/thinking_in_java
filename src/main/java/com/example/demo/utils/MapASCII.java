package com.example.demo.utils;


import org.thymeleaf.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapASCII  {

	public static String getASCII(List<String> list, String key) throws UnsupportedEncodingException {
		//升序
		Collections.sort(list);
        String content = StringUtils.join(list.toArray(), ";");
//      Collections.sort(arl);//根据元素的自然顺序 对指定列表按升序进行排序。
		String sign = content + ";private_key=" + key;
//		System.out.println(MD5Util.MD5sign( URLEncoder.encode(sign.trim(),"UTF-8")));
		return URLEncoder.encode(sign.trim(),"UTF-8");
	}
	/**
     * 根据算法,加密当前的密码,加密方式: MD5(明文密码+时间戳)
     *
     * @param password    明文密码
     * @param currentTime client传输的加密时间戳
     *
     * @return
     */
    public String encodePassword(String password, String currentTime) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            String str = password + currentTime;
            //加密后的字符串
            String encode = base64en.encode(md5.digest(str.getBytes("utf-8")));
            System.out.println(encode);
            return encode;
        } catch (Exception ex) {
        	//logger.error("encodePassword has exception", ex);
        }
        return null;
    }
	public static void main(String[] args) throws UnsupportedEncodingException {
		String signature="a=0f829ac4e6689efbd338abda4c090f90bab60725";
		String timestamp="b=1455868453";
		String nonce="aa=167786695011  ";
		String token="d=aaafasdf23rfdsagfdsaf";

		List<String> list=new ArrayList<String>();
		list.add(nonce);
		list.add(timestamp);
		list.add(token);
		list.add(signature);
		System.out.println(getASCII(list,"11"));
	}
}
