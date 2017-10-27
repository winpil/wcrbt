package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import com.java.sys.basic.entity.BaseEntity;
import java.util.Date;

public class SyncFieldMapped extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String fieldId;	//字段Id
	private String fieldName;	//字段名称
	private String fieldType;	//字段类型
	private String fieldTypeName;	//字段类型名称
	private String mappedValue;	//映射值
	private String customType;	//
	private Date createTime;	//
	private Date updateTime;	//
	
	public SyncFieldMapped() {
		super();
	}
	
    
    public String getFieldId() {
    	return fieldId;
    }
    public void setFieldId(String fieldId) {
    	this.fieldId = fieldId;
    }
    
    public String getFieldName() {
    	return fieldName;
    }
    public void setFieldName(String fieldName) {
    	this.fieldName = fieldName;
    }
    
    public String getFieldType() {
    	return fieldType;
    }
    public void setFieldType(String fieldType) {
    	this.fieldType = fieldType;
    }
    
    public String getFieldTypeName() {
    	return fieldTypeName;
    }
    public void setFieldTypeName(String fieldTypeName) {
    	this.fieldTypeName = fieldTypeName;
    }
    
    public String getMappedValue() {
    	return mappedValue;
    }
    public void setMappedValue(String mappedValue) {
    	this.mappedValue = mappedValue;
    }
    
    public String getCustomType() {
    	return customType;
    }
    public void setCustomType(String customType) {
    	this.customType = customType;
    }
    
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
    	return createTime;
    }
    public void setCreateTime(Date createTime) {
    	this.createTime = createTime;
    }
    
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
    	return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
    	this.updateTime = updateTime;
    }
	
}