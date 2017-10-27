package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import com.java.sys.basic.entity.BaseEntity;
import java.util.Date;

public class SyncLog extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer code;	//
	private String receive;	//参数值
	private String requestData;	//请求内容
	private String result;	//返回内容
	
	public SyncLog() {
		super();
	}
	
    
    public Integer getCode() {
    	return code;
    }
    public void setCode(Integer code) {
    	this.code = code;
    }
    
    public String getReceive() {
    	return receive;
    }
    public void setReceive(String receive) {
    	this.receive = receive;
    }
    
    public String getRequestData() {
    	return requestData;
    }
    public void setRequestData(String requestData) {
    	this.requestData = requestData;
    }
    
    public String getResult() {
    	return result;
    }
    public void setResult(String result) {
    	this.result = result;
    }
	
}