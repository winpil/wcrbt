package com.java.sys.rbac.controller.web;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.sys.utils.Tool;
import com.mangofactory.swagger.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/sys/sysCommonWebController")
public class SysCommonWebController {

	protected String PATH_SYS_IMG = "/uploads/sys/images/";		//系统图片默认路径
	protected String PATH_SYS_FILE = "/uploads/sys/files/";		//系统文件默认路径

	/*
	 * 上传文件
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(HttpServletRequest request,
			@RequestParam(value="file",required=false) MultipartFile file,
			@RequestParam(value="inputName",required=false) String inputName){
		if(file != null){
			Tool.uploadFile(request, file, PATH_SYS_FILE,true);
		}
		if(Tool.isBlank(inputName)){
			inputName = "";
		}
		return "redirect:/sys/sysCommonWebController/sysFileList?inputName="+inputName;
	}
	
	/*
	 * 文件列表
	 */
	@RequestMapping(value = "/sysFileList")
	public String sysFileList(HttpServletRequest request,Model model){
		List<Map<String,Object>> fileList = null;
		String dir = request.getSession().getServletContext().getRealPath(PATH_SYS_FILE);
		File file = new File(dir);
		File[] fs = file.listFiles();
		if(fs != null && fs.length>0){
			fileList = new ArrayList<Map<String,Object>>();
			for(File f: fs){
				double size = f.length()/1024;
				String fileSize = size>1024?size/1024+"MB":size+"KB";
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("filePath", request.getContextPath()+PATH_SYS_IMG+f.getName());
				map.put("fileSize", fileSize);
				map.put("uploadTime", new Date(f.lastModified()));
				map.put("fileName", f.getName());
				fileList.add(map);
			}
		}
		model.addAttribute("fileList", fileList);
		return "/WEB-INF/views/sys/sysFileList.jsp";
	}
	
	/*
	 * 删除文件
	 */
	@RequestMapping(value = "/deleteFile")
	public String deleteFile(HttpServletRequest request,Model model,
			@RequestParam(value="filePath",required=false) String filePath,
			@RequestParam(value="inputName",required=false) String inputName){
		String dir = request.getSession().getServletContext().getRealPath(PATH_SYS_FILE);
		String realPath = dir+File.separator+filePath.replace(request.getContextPath()+PATH_SYS_FILE, "");
		File file = new File(realPath);
		file.delete();
		if(Tool.isBlank(inputName)){
			inputName = "";
		}
		return "redirect:/sys/sysCommonWebController/sysFileList?inputName="+inputName;
	}
	
	
	/*
	 * 上传图片
	 */
	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	public String uploadImg(HttpServletRequest request,
			@RequestParam(value="img",required=false) MultipartFile img,
			@RequestParam(value="inputName",required=false) String inputName,
			@RequestParam(value="multiSelect",required=false) String multiSelect,
			@RequestParam(value="inuse",required=false) String inuse,
			@RequestParam(value="x1",required=false) String x1,
			@RequestParam(value="y1",required=false) String y1,
			@RequestParam(value="x2",required=false) String x2,
			@RequestParam(value="y2",required=false) String y2,
			@RequestParam(value="width",required=false) String width,
			@RequestParam(value="height",required=false) String height,
			@RequestParam(value="maxWidth",required=false) String _maxWidth,
			@RequestParam(value="maxHeight",required=false) String _maxHeight){
		String uploadImg = "";
		if(img != null){
			if(Tool.isNotBlank(x1,y1,x2,y2,width,height,_maxWidth,_maxHeight)){
				Double dx1 = Double.parseDouble(x1);
				Double dy1 = Double.parseDouble(y1);
				Double dx2 = Double.parseDouble(x2);
				Double dy2 = Double.parseDouble(y2);
				Double dwidth = Double.parseDouble(width);
				Double dheight = Double.parseDouble(height);
				int ix1 = dx1.intValue();
				int iy1 = dy1.intValue();
				int ix2 = dx2.intValue();
				int iy2 = dy2.intValue();
				int iwidth = dwidth.intValue();
				int iheight = dheight.intValue();
				int maxWidth = Integer.parseInt(_maxWidth);
				int maxHeight = Integer.parseInt(_maxHeight);
				uploadImg = Tool.uploadImgCut(request, img, PATH_SYS_IMG, ix1, iy1, iwidth, iheight,maxWidth,maxHeight);
			}else{
				uploadImg = Tool.uploadImg(request, img,PATH_SYS_IMG);
			}
		}
		if(Tool.isBlank(inputName)){
			inputName = "";
		}
		if(Tool.isBlank(multiSelect)){
			multiSelect = "";
		}
		return "redirect:/sys/sysCommonWebController/sysImgList?inputName="+inputName+"&multiSelect="+multiSelect+"&uploadImg="+((Tool.isNotBlank(inuse)&&"1".equals(inuse))?uploadImg:"");
	}
	
	
	/*
	 * 跳转到图片选择器
	 */
	@RequestMapping(value = "/sysImgList")
	public String sysImgList(HttpServletRequest request,Model model){
		List<Map<String,Object>> imgList = null;
		String dir = request.getSession().getServletContext().getRealPath(PATH_SYS_IMG);
		File file = new File(dir);
		File[] fs = file.listFiles();
		if(fs != null && fs.length>0){
			imgList = new ArrayList<Map<String,Object>>();
			for(File f: fs){
				double size = f.length()/1024;
				String fileSize = size>1024?size/1024+"MB":size+"KB";
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("filePath", request.getContextPath()+PATH_SYS_IMG+f.getName());
				map.put("fileSize", fileSize);
				map.put("uploadTime", new Date(f.lastModified()));
				imgList.add(map);
			}
		}
		model.addAttribute("imgList", imgList);
		return "/WEB-INF/views/sys/sysImgList.jsp";
	}
	
	/*
	 * 删除图片
	 */
	@RequestMapping(value = "/deleteImg")
	public String deleteImg(HttpServletRequest request,Model model,
			@RequestParam(value="filePath",required=false) String filePath,
			@RequestParam(value="inputName",required=false) String inputName,
			@RequestParam(value="multiSelect",required=false) String multiSelect){
		String dir = request.getSession().getServletContext().getRealPath(PATH_SYS_IMG);
		String realPath = dir+File.separator+filePath.replace(request.getContextPath()+PATH_SYS_IMG, "");
		File file = new File(realPath);
		file.delete();
		if(Tool.isBlank(inputName)){
			inputName = "";
		}
		if(Tool.isBlank(multiSelect)){
			multiSelect = "";
		}
		return "redirect:/sys/sysCommonWebController/sysImgList?inputName="+inputName+"&multiSelect="+multiSelect;
	}
	
}
