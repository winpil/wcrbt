package com.java.entity;


import com.java.sys.basic.entity.BaseEntity;

public class SyncProjectMapped extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer projectId;	//jira项目标识符
	private String projectName;	//项目名称
	private String mappedValue;	//映射值
	
	public SyncProjectMapped() {
		super();
	}
	
    
    public Integer getProjectId() {
    	return projectId;
    }
    public void setProjectId(Integer projectId) {
    	this.projectId = projectId;
    }
    
    public String getProjectName() {
    	return projectName;
    }
    public void setProjectName(String projectName) {
    	this.projectName = projectName;
    }
    
    public String getMappedValue() {
    	return mappedValue;
    }
    public void setMappedValue(String mappedValue) {
    	this.mappedValue = mappedValue;
    }
	
}