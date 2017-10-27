package com.java.sys.utils;

import java.io.InputStream;
import java.util.Properties;

public class SysConfigUtil {
	
	/**
	 * 获取配置文件config.properties的值
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		Properties p = new Properties();
		InputStream is = SysConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
		try{
			p.load(is);
			is.close();
			if(p.getProperty(key)==null){
				return "";
			}
			return p.getProperty(key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
