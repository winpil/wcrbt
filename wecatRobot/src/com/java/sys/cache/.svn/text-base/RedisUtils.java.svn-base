package com.java.sys.cache;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.java.sys.rbac.entity.SysMenu;
import com.java.sys.rbac.service.SysMenuService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisUtils {
	//@Resource
	//private SysMenuService sysMenuService;
	
	public static Jedis jedis;						//非切片额客户端连接
	public static JedisPool jedisPool;				//非切片连接池
	
	public static void initPool(){
		if(jedisPool == null){
			JedisPoolConfig config = new JedisPoolConfig(); 
	        config.setMaxActive(20); 
	        config.setMaxIdle(5); 
	        config.setMaxWait(1000l); 
	        config.setTestOnBorrow(false);
	        jedisPool = new JedisPool(config,"127.0.0.1",6379);
		}
	}
	
	
	
}
