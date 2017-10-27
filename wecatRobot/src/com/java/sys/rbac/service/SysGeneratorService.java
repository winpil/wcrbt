package com.java.sys.rbac.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.springframework.stereotype.Service;

import com.java.sys.rbac.entity.SysColumn;
import com.java.sys.rbac.entity.SysTable;
import com.java.sys.utils.SysConfigUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class SysGeneratorService {
	protected static final int TYPE_MAPPER = 1;
	protected static final int TYPE_ENTITY = 2;
	protected static final int TYPE_DAO = 3;
	protected static final int TYPE_SERVICE = 4;
	protected static final int TYPE_WEB_CONTROLLER = 5;
	protected static final int TYPE_JSP_LIST = 6;
	protected static final int TYPE_JSP_FORM = 7;
	protected static final int TYPE_RETURN = 8;
	
	protected static final String TPL_MAPPER = "/mapper.ftl";
	protected static final String TPL_ENTITY = "/entity.ftl";
	protected static final String TPL_DAO = "/dao.ftl";
	protected static final String TPL_SERVICE = "/service.ftl";
	protected static final String TPL_WEB_CONTROLLER = "/webController.ftl";
	protected static final String TPL_JSP_LIST = "/jspList.ftl";
	protected static final String TPL_JSP_FORM = "/jspForm.ftl";
	protected static final String TPL_RETURN= "/return.ftl";
	
	protected static final String PATH_MAPPER ="\\src\\com\\java\\dao\\mappers";
	protected static final String PATH_ENTITY ="\\src\\com\\java\\entity";
	protected static final String PATH_DAO ="\\src\\com\\java\\dao";
	protected static final String PATH_SERVICE ="\\src\\com\\java\\service";
	protected static final String PATH_WEB_CONTROLLER ="\\src\\com\\java\\controller\\web";
	protected static final String PATH_JSP = "\\WebRoot\\WEB-INF\\views\\project";
	protected static final String PATH_RETURN = "\\src\\com\\java\\controller\\response";
	
	protected static final String SUFFIX_MAPPER = "Dao.xml";
	protected static final String SUFFIX_ENTITY = ".java";
	protected static final String SUFFIX_DAO = "Dao.java";
	protected static final String SUFFIX_SERVICE = "Service.java";
	protected static final String SUFFIX_WEB_CONTROLLER = "WebController.java";
	protected static final String SUFFIX_JSP_LIST = "List.jsp";
	protected static final String SUFFIX_JSP_FORM = "Form.jsp";
	protected static final String SUFFIX_RETURN = ".java";
	
	public static final String PERM_TYPE_VIEW = "view";
	public static final String PERM_TYPE_EDIT = "edit";
	public static final String PERM_TYPE_DELETE = "delete";
	
	
	@Resource
	private SysColumnService columnService;
	
	/*
	 * 全部生成
	 */
	public void makeAll(String tableName){
		makeMapper(tableName);
		makeEntity(tableName);
		makeDao(tableName);
		makeReturn(tableName);
		makeService(tableName);
		makeWebController(tableName);
		makeJspList(tableName);
		makeJspForm(tableName);
	}
	
	
	/*
	 * 生成mapper
	 */
	public void makeMapper(String tableName){
		generate(tableName, TYPE_MAPPER);
	}
	
	/*
	 * 生成实体类
	 */
	public void makeEntity(String tableName){
		generate(tableName, TYPE_ENTITY);
	}
	
	/*
	 * 生成dao
	 */
	public void makeDao(String tableName){
		generate(tableName, TYPE_DAO);
	}
	
	/*
	 * 生成return
	 */
	public void makeReturn(String tableName){
		generate(tableName, TYPE_RETURN);
	}
	
	/*
	 * 生成service
	 */
	public void makeService(String tableName){
		generate(tableName, TYPE_SERVICE);
	}
	
	/*
	 * 生成controller
	 */
	public void makeWebController(String tableName){
		generate(tableName, TYPE_WEB_CONTROLLER);
	}
	
	/*
	 * 生成list.jsp
	 */
	public void makeJspList(String tableName){
		generate(tableName, TYPE_JSP_LIST);
	}
	
	/*
	 * 生成form.jsp
	 */
	public void makeJspForm(String tableName){
		generate(tableName, TYPE_JSP_FORM);
	}
	
	public void generate(String tableName,int type){
		String tplFile = null;
		String suffix = null;
		String genPath = null;
		
		switch(type){
			case TYPE_MAPPER:
				tplFile = TPL_MAPPER;
				genPath = PATH_MAPPER;
				suffix = SUFFIX_MAPPER;
				break;
			case TYPE_ENTITY:
				tplFile = TPL_ENTITY;
				genPath = PATH_ENTITY;
				suffix = SUFFIX_ENTITY;
				break;
			case TYPE_DAO:
				tplFile = TPL_DAO;
				genPath = PATH_DAO;
				suffix = SUFFIX_DAO;
				break;
			case TYPE_SERVICE:
				tplFile = TPL_SERVICE;
				genPath = PATH_SERVICE;
				suffix = SUFFIX_SERVICE;
				break;
			case TYPE_WEB_CONTROLLER:
				tplFile = TPL_WEB_CONTROLLER;
				genPath = PATH_WEB_CONTROLLER;
				suffix = SUFFIX_WEB_CONTROLLER;
				break;
			case TYPE_JSP_LIST:
				tplFile = TPL_JSP_LIST;
				genPath = PATH_JSP;
				suffix = SUFFIX_JSP_LIST;
				break;
			case TYPE_JSP_FORM:
				tplFile = TPL_JSP_FORM;
				genPath = PATH_JSP;
				suffix = SUFFIX_JSP_FORM;
				break;
			case TYPE_RETURN:
				tplFile = TPL_RETURN;
				genPath = PATH_RETURN;
				suffix = SUFFIX_RETURN;
				break;
		}
		
		List<SysColumn> columnList = columnService.findColumnList(tableName);
		if(columnList != null){
			String tableNameCH = "";
			for(SysColumn column : columnList){
				column = columnService.convert(column);
				if("id".equals(column.getColumnName())){
					tableNameCH = column.getColumnComment();
				}
			}
			String className = columnService.toClassName(tableName);
			SysTable sysTable = new SysTable(tableName,tableNameCH,className,columnService.firstLower(className), columnList);
			sysTable.setPermView(columnService.toPermStr(tableName, PERM_TYPE_VIEW));
			sysTable.setPermEdit(columnService.toPermStr(tableName, PERM_TYPE_EDIT));
			sysTable.setPermDelete(columnService.toPermStr(tableName, PERM_TYPE_DELETE));
			sysTable.setShortName(columnService.toShortName(tableName));
			
			Map<String,Object> table = new HashMap<String, Object>();
			table.put("table", sysTable);
			Configuration cfg = new Configuration();
	        String path = this.getClass().getResource("/").getPath()+"template";
	        String fileName = sysTable.getClassName()+suffix;
	        if(type == TYPE_JSP_LIST || type == TYPE_JSP_FORM){
	        	fileName = columnService.firstLower(fileName);
	        }
	        if(type == TYPE_RETURN){
	        	fileName = "Return"+sysTable.getShortName()+suffix;
	        }
			try{
				cfg.setDirectoryForTemplateLoading(new File(path));
				Template template = cfg.getTemplate(tplFile,"UTF-8");
				StringWriter out = new StringWriter();
				template.process(table, out);
				System.out.println("--------- freemarker making ---------");
				System.out.println(out.toString());
				
				String workplace = SysConfigUtil.getConfig("workplace");
				File targetDir = new File(workplace+genPath);
				File targetFile = new File(targetDir,fileName);
				targetFile.createNewFile();
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(targetFile),"UTF-8");
				osw.write(out.toString());
				osw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
