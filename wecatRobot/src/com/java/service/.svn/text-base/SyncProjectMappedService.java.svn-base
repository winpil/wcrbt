package com.java.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.IssueType;
import com.java.entity.Project;
import com.java.entity.SyncProjectMapped;
import com.java.entity.issue.Assignee;
import com.java.entity.issue.FieldNames;
import com.java.entity.issue.FieldTypes;
import com.java.entity.issue.MyIssueType;
import com.java.entity.issue.MyProject;
import com.java.entity.issue.Reporter;
import com.java.entity.issue.Value;
import com.java.dao.SyncProjectMappedDao;
import com.java.sys.basic.service.BaseService;
import com.java.sys.utils.SysConfigUtil;
import com.java.sys.utils.Tool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class SyncProjectMappedService extends BaseService<SyncProjectMappedDao, SyncProjectMapped> {
	
	private static final Logger log = Logger.getLogger(SyncProjectMappedService.class);
	
	@Resource
	private SyncFieldMappedService syncFieldMappedService;
	
	private static final String getPorjectUrl = SysConfigUtil.getConfig("jiraBaseUser")+"/rest/api/2/project";
	private static final String getUserUrl = SysConfigUtil.getConfig("jiraBaseUser")+"/rest/api/2/user?username=";
	private static final String issueUrl = SysConfigUtil.getConfig("jiraBaseUser")+"/rest/api/2/issue";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	public SyncProjectMapped getSyncProjectMapped(SyncProjectMapped entity,HttpServletRequest request){
		SyncProjectMapped projectmapped = new SyncProjectMapped();
		if(entity != null){
			try{
				BeanUtils.copyProperties(projectmapped, entity);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return projectmapped;
	}
	
	
	public List<SyncProjectMapped> getSyncProjectMappedList(List<SyncProjectMapped> entityList,HttpServletRequest request){
		List<SyncProjectMapped> list = new ArrayList<SyncProjectMapped>();
		if(entityList != null && entityList.size()>0){
			for(SyncProjectMapped entity : entityList){
				list.add(getSyncProjectMapped(entity, request));
			}
		}
		return list;
	}


	public List<Project> getProjcetList() {
		List<Project> list  = new ArrayList<>();
		String userName = SysConfigUtil.getConfig("jiraUserName");
		String password = SysConfigUtil.getConfig("jiraPassword");
		String result = Tool.get2(getPorjectUrl, Base64.encodeToString((userName+":"+password).getBytes()));
		if("".equals(result)){
			log.error("————————————————————获取项目失败——————————————————————");
			return list;
		}
		JSONArray jsonArray = JSONArray.fromObject(result);
		int size = jsonArray.size();
		for(int i = 0;i<size;i++){
			JSONObject json = jsonArray.getJSONObject(i);
			Project p = new Project();
			p.setId((String)json.getString("id"));
			p.setKey((String)json.getString("key"));
			p.setName((String)json.getString("name"));
			list.add(p);
		}
		return list;
	}


	public List<SyncProjectMapped> getAll() {
		return dao.getAll();
	}

	public List<IssueType> getIssueType(Integer projectId) {
		List<IssueType> list  = new ArrayList<>();
		String userName = SysConfigUtil.getConfig("jiraUserName");
		String password = SysConfigUtil.getConfig("jiraPassword");
		String result = Tool.get2(getPorjectUrl+"/"+projectId, Base64.encodeToString((userName+":"+password).getBytes()));
		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("issueTypes");
		int size = jsonArray.size();
		for(int i = 0;i<size;i++){
			JSONObject json = jsonArray.getJSONObject(i);
			IssueType type = new IssueType();
			type.setId(json.getString("id"));
			type.setSubtask(json.getBoolean("subtask"));
			type.setDescription(json.getString("description"));
			type.setName(json.getString("name"));
			list.add(type);
		}
		return list;
	}
	public Map<String,String> getMapped() {
		Map<String,String> map = new HashMap<>();
		List<SyncProjectMapped> projects = dao.getAll();
		for(SyncProjectMapped p: projects){
			map.put(p.getMappedValue(), p.getProjectId().toString());
		}
		return map;
	}

	public Map<String,Object> createIssue(String data, Map<String, String> projectMapped, Map<String, String> typeMapped, Map<String, String> fieldMapped, Map<String, String> fieldTypeMapped) {
		
		Map<String,Object> map = new HashMap<>();
		//jira登陆账户
		String userName = SysConfigUtil.getConfig("jiraUserName");
		String password = SysConfigUtil.getConfig("jiraPassword");
		
		JSONObject jsonObject = JSONObject.fromObject(data);
		String projectId = jsonObject.getString(FieldNames.PROJECT);
		String issueType = jsonObject.getString(FieldNames.ISSUETYPE);
		if(Tool.isBlank(projectId,issueType)){
			map.put("code", -1);
			return map;
		}
		Map<String,Object> allData = new HashMap<>();
		Map<String,Object> fields = new HashMap<>();
		MyProject project = new MyProject(projectMapped.get(projectId));
		MyIssueType type = new MyIssueType(typeMapped.get(issueType));
		fields.put(FieldNames.PROJECT, project);
		fields.put(FieldNames.ISSUETYPE, type);
		Iterator<String> keys = jsonObject.keys();
		String key = "";
		String value = "";
		String field = "";
		String fieldType = "";
		while(keys.hasNext()){
			key = keys.next();
			if(FieldNames.PROJECT.equals(key)||FieldNames.ISSUETYPE.equals(key)){
				continue;
			}
			field = fieldMapped.get(key);
			if(Tool.isBlank(field)){
				continue;
			}
			value = jsonObject.getString(key);
			fieldType = fieldTypeMapped.get(field);
			if(!Tool.isBlank(value)){
				/**
				 * 特殊字段特殊处理
				 */
				if(FieldNames.ASSIGNEE.equals(field)){//经办人
					String result = Tool.get2(getUserUrl+value, Base64.encodeToString((userName+":"+password).getBytes()));
					if(!Tool.isBlank(result)){
						Assignee a = new Assignee(value);
						fields.put(field, a);
					}
				}else if(FieldNames.REPORTER.equals(field)){//委托人
					String result = Tool.get2(getUserUrl+value, Base64.encodeToString((userName+":"+password).getBytes()));
					if(!Tool.isBlank(result)){
						Reporter r = new Reporter(value);
						fields.put(field, r);
					}else{
						Reporter r = new Reporter(userName);
						fields.put(field, r);
					}
				}else{
					/**
					 * 特殊类型特殊处理
					 */
					if(FieldTypes.OPTION.equals(fieldType)){//单项
						Value v = new Value(value);
						fields.put(field, v);
					}else if(FieldTypes.ARRAY.equals(fieldType)){//多项
						String[] s = value.split(",");
						List<Value> vs = new ArrayList<>();
						for(int i = 0;i<s.length;i++){
							Value v = new Value(s[i]);
							vs.add(v);
						}
						fields.put(field, vs);
					}else if(FieldTypes.DATETIME.equals(fieldType)){//日期
						fields.put(field, sdf.format(new Date(Long.parseLong(value))));
					}else{
						fields.put(field, value);
					}
				}
			}
		}
		allData.put(FieldNames.FIELDS, fields);
		map = Tool.httpPost( issueUrl,JSONObject.fromObject(allData),Base64.encodeToString((userName+":"+password).getBytes()));
		map.put("requestData", JSONObject.fromObject(allData).toString());
		return map;
	}


	
	
}