package com.java.sys.listener;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletContextEvent;

import net.sf.ehcache.CacheManager;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.web.context.ContextLoaderListener;

import com.java.sys.cache.EHCacheUtils;

public class SysContextLoaderListener extends ContextLoaderListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		// 注册apache的BeanUtils的Date类型、BigDecimal类型空值转化默认值
		ConvertUtils.register(new DateConverter(null), Date.class);
		ConvertUtils.register(new BigDecimalConverter(BigDecimal.ZERO), BigDecimal.class);
		//RedisUtils.initPool();
		
		// ehcache初始化
		EHCacheUtils.init();
		EHCacheUtils.printInfo();
		// 微信公众号参数初始化
		//WeixinTool.keyRefresh();
	}
	
}
