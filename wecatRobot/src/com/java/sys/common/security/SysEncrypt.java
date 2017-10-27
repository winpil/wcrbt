package com.java.sys.common.security;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;


public class SysEncrypt {
	
	/**
	 * 方法名：RSAEncode
	 * 详述：加密
	 * @param str
	 * @return String
	 */
	public static String RSAEncode(String str){
		String encryptStr = null;
		try {
			encryptStr = Base64.encodeBase64String((RSA.encryptByPublicKey(str.getBytes("utf-8"))));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return encryptStr;
	}
	
	/**
	 * 方法名：RSADecode
	 * 详述：解密
	 * @param str
	 * @return String
	 */
	public static String RSADecode(String str){
		String decodeStr = null;
		try {
			byte[] decryptByPrivateKey = RSA.decryptByPrivateKey(Base64.decodeBase64(str));
			decodeStr = new String(decryptByPrivateKey, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
        return decodeStr;
	}
	
	/**
	 * 方法名：getParam
	 * 详述：从加密字符串中获取参数
	 * @param key 参数名
	 * @param sign 加密字符串
	 * @return String
	 */
	public static String getParam(String key,String sign){
		try {
			Map<String,String> map = RSA.signToMap(sign);
			return map.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String str = "phone=18122101240&password=qwertyuiop&tokenName=xiaozhunihaoma&timeStamp=";
		String encode = RSAEncode(str);
		String decode = RSADecode(encode);
		System.out.println(encode);
		System.out.println("-----------------------------------");
		System.out.println(decode);
	}
	
	
	
}
