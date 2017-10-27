package com.java.sys.utils;

import org.apache.shiro.crypto.hash.Sha1Hash;


  
   
  
public class EncryptUtils {
	
	public static void main(String[] args) {
		System.out.println(new Sha1Hash("123").toString());
		
		//40bd001563085fc35165329ea1ff5c5ecbdbbeef
		//40bd001563085fc35165329ea1ff5c5ecbdbbeef
	}

	public static String sha1(String str){
		if(Tool.isBlank(str)){
			return "";
		}
		return new Sha1Hash(str).toString();
	}
    
}