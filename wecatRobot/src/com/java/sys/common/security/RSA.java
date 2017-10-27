package com.java.sys.common.security;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * <p>RSA签名,加解密处理核心文件，注意：密钥长度1024</p>
 * @author leelun
 * @version $Id: RSA.java, v 0.1 2013-11-15 下午2:33:53 lilun Exp $
 */
public class RSA {

    /**
     * 加密算法RSA
     */
    public static final String  KEY_ALGORITHM       = "RSA";
    /**
     * RSA最大加密明文大小
     */
    private static final int    MAX_ENCRYPT_BLOCK   = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int    MAX_DECRYPT_BLOCK   = 128;

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY          = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY         = "RSAPrivateKey";
    
    /**
     * 公钥
     */
    private static final String PUBLIC_KEY1          = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHoE6zEDa1iY8w0XQMa" +
            		"oHRbBXx9LjecB7ageK1KWJyLKWBcUwnyyUJ+PN9CjaqZs4Vu3RMgV/r6MxFLtlLtBlY1MsKoLX3M+8glAIl79C" +
            		"E0kxtMMFajDs/gEuwfKoXPpxFMRgRRuNYkKdnngT9VluBJ3AxVIqqiZYQFobBxMBd5wIDAQAB";

    /**
     * 私钥
     */
    private static final String PRIVATE_KEY1         = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMegTrMQNrWJjzDRdAxqgdFsFfH0uN5wHtqB4rUpYnIspYFxTCfLJ" +
            "Qn4830KNqpmzhW7dEyBX+vozEUu2Uu0GVjUywqgtfcz7yCUAiXv0ITSTG0wwVqMOz+AS7B8qhc+nEUxGBFG41iQp2eeBP1WW4EncD" +
            "FUiqqJlhAWhsHEwF3nAgMBAAECgYBNeabxkfpZrGCZZsDuTKvDdnpx5DOXiNZAoaY1/zDI9RDq0ujmjM38N9VwEvWyXqHfB1iUlKW" +
            "ArmIQ+6Sz39rx0lbBGqTg2x2JmJFgWYr89pPrYdTYixwMja3nTA6g7GjzDAfGTdHMjDxw3TA5VYUS8Y/N1paw1Pidv1MtcQHlCQJBAO" +
            "pBdMxeTijKFvJrpm+lMvY9Y+lHrfmGlFBSkqI75zOBUhU1dl88n8Eufydl9LimsxKZp4bwMKRI21Ye8TxPKIMCQQDaJ/YtLNbec+ODt" +
            "J9/aTQcugnvmLsEQGw+kpiYQTAVT/TrugACtvPuXx7wLH17Wcs03fO7YXRVTxx0oLvZCc/NAkEA2nxEeJx4n5ilQGNhL35QAtSNTDXb" +
            "sQBxcl0hgIkIgcRbD4b2qar8gYxhchio4FQybuW7F6MMax283R0s+IgICwJBAMdvav2HlXNOTLsWAA4RlkOAzB6O6MGNR6e4iun+BkA" +
            "llYl+xA23Ra8wpG76rUh8IWpWUkCGajynEyOH4dMbxi0CQGBK5CpX6Gg4GppzHV8s1RyQT64aCgMQN6hOinMJN93jY1fK0jxk9WBXdB26bpOR1o7UePJrc/c7zMCVKMwrBLY=";

    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024,new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;

    }
    
    
    public static String isSign(HttpServletRequest httpServletRequest) throws Exception {
    	
        String str = "0";
        String string ="AA威武！";
        String signStr="";
        //String sign = httpServletRequest.getParameter("sign");
        String sign = "CgqRXVR6sY4X+NTDyd+BYn/HIPziEi9rcDbo7ItP2cc88PKhANn0zysONh7+ZtNs0gsDNxF2tYCMA3BT94GxCxrdvivXtrJ9qh4/DWuW/hhNAtuTWX0zKfnYqAz" +
        		"AAKiPansO48FiQnDdLLFgd7tfQa7zQzKPiEn16HY1qPQT1js=";
        try {
			byte[] decryptByPrivateKey = decryptByPrivateKey(Base64.decodeBase64(sign));
			signStr = new String(decryptByPrivateKey, "utf-8");
		} catch (Exception e) {
			str="0";
		}
        if(signStr.equals(string)){
        	str="1";
        }
        return str;

    }
    
    public static Map<String,String> signToMap(String sign) throws Exception {
    	Map<String,String> map = new HashMap<String,String>();
    	
    	byte[] decryptByPrivateKey = decryptByPrivateKey(Base64.decodeBase64(sign));
        String string = new String(decryptByPrivateKey, "utf-8");
        String[] str = string.split("&");
    	for (int i = 0; i < str.length; i++) {
    		String key = str[i].substring(0, str[i].indexOf("="));
    		String value = str[i].substring(str[i].indexOf("=")+1);
			map.put(key,value);
		}
        return map;
    }

    public static void main(String[] args) throws Exception {
//        Map<String, Object> genKeyPair = genKeyPair();
//
//        String base64publicKey = getPublicKey(genKeyPair);
//        System.out.println("公钥 \n" + base64publicKey);
//        String base64privateKey = getPrivateKey(genKeyPair);
//        System.out.println("私钥\n" + base64privateKey);

        String passwd = "phone=18122101240&password=qwertyuiop&tokeName=xiaozhunihaoma";
        String charsetName = "utf-8";

        String encryptByPublicKey = Base64.encodeBase64String((encryptByPublicKey(passwd.getBytes(charsetName))));
        System.out.println("加密\n" + encryptByPublicKey);
        System.out.println("加密\n" + encryptByPublicKey.length());
        byte[] decryptByPrivateKey = decryptByPrivateKey(Base64.decodeBase64(encryptByPublicKey));
        String string = new String(decryptByPrivateKey, "utf-8");
        System.out.println("解密后\n" + string);

    	
    	
    	
    	
    	signToMap("NcatkXvUGvl3BrKkmNSC90GfLvEdVk9cesmOgtcmVdecY/Ct8xsG2wBYNhYEsUoLUdQ/iFIT9cdjEeNylJbVz31HWl1IKb+4L5fkYG46+xBA5rhfTMxIFaBSRMHSbKsWoQP8dwV/qbtAvpJx5JLAFh23/i1GSNMda1zYiJQ42Yw=");
    	
    	
    }

    

    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData)
                                                                                     throws Exception {
        byte[] keyBytes = Base64.decodeBase64(PRIVATE_KEY1);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;

    }

    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
                                                                                   throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;

    }

    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(PUBLIC_KEY1);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;

    }

    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;

    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }
}
