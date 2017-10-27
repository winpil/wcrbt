package com.java.sys.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.java.sys.utils.SysConfigUtil;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.mangofactory.swagger.models.dto.ApiInfo;

@Configuration
@EnableSwagger
@EnableWebMvc
public class SwaggerConfig {
	
	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}
	
	/*
	 * Spring在启动的时候检测到@Bean的时候默认会在容器中注入一个以方法名（你的代码中是user）命名的Bean，而这个Bean用的是和该方法的返回类型一样的类（你的代码中是User）来初始化的。
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation(){
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*?");
	}
	
	
	private ApiInfo apiInfo(){
		return new ApiInfo(SysConfigUtil.getConfig("project.name"),"接口文档",null,"iamjava@qq.com",null,null);
	}
	
	
	
}
