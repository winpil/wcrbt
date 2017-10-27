package com.java.sys.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.multipart.MultipartFile;

import com.thoughtworks.xstream.XStream;

import net.sf.json.JSONObject;


public class Tool {
	protected static final String PATH_IMG = "/uploads/images/";		//上传图片默认保存路径
	protected static final String PATH_FILE = "/uploads/files/";		//上传图片默认保存路径
	
	
	/**
	 * 方法名：ceil
	 * 详述：向上取整
	 * @param num
	 * @param size
	 * @return int
	 */
	public static int ceil(int num, int size) {
		if (size == 0) {
			return 0;
		}
		int n1 = num/size;
		double n2 = num%size;
		if (n2 > 0) {
			n1 += 1;
		}
		return n1;
	}
	
	
	/**
	 * 方法名：getImgPrefix
	 * 详述：返回项目路径（图片前缀），到端口，没有/
	 * 创建时间：2016年5月24日
	 * @param request
	 * @return String
	 */
	public static String getImgPrefix(HttpServletRequest request){
		return "http://" + request.getServerName() + ":" + request.getServerPort();
	}
	
	
	/**
	 * 方法名：strToList
	 * 详述：对字符串以split为分割符，转换成List
	 * @param request
	 * @param str
	 * @param split
	 * @return List<String>
	 */
	public static List<String> strToList(String str,String split){
		if(Tool.isBlank(str,split)){
			return null;
		}
		List<String> list = null;
		String[] strArr = str.split(split);
		if(strArr != null && strArr.length>0){
			list = new ArrayList<String>();
			for(String s : strArr){
				if(!isBlank(s)){
					list.add(s);
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 方法名：toUrls
	 * 详述：转换绝对地址，用逗号连接每个地址
	 * 创建时间：2016年3月24日
	 * @param request
	 * @param imgsStr
	 * @return String
	 */
	public static String toUrls(HttpServletRequest request, String str){
		if(!Tool.isBlank(str)){
			String[] strs = str.split(",");
			if(strs!=null && strs.length>0){
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<strs.length;i++){
					sb.append(toUrl(request, strs[i]));
					if(i<(strs.length-1)){
						sb.append(",");
					}
				}
				return sb.toString();
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 方法名：toUrl
	 * 详述：转换绝对地址
	 * 创建时间：2016年3月24日
	 * @param request
	 * @param imgUrl 数据库路径
	 * @return String
	 */
	public static String toUrl(HttpServletRequest request, String path){
		if(isBlank(path)){
			return null;
		}
		if(path.contains("http")){
			return path;
		}
		return getImgPrefix(request)+path;
	}
	
	
	/**
	 * 方法名：double2String
	 * 详述：double的金额转String(超过万，转成，如：3.00万)
	 * 创建时间：2016年5月24日
	 * @param num
	 * @return String
	 */
	public static String double2String(Double num) {
		String str = "";
		if (num >= 10000 && num < 1000000) {
			num = num * 0.0001;
			DecimalFormat df = new DecimalFormat("######0.0");  
			str = df.format(num);
			str += "万";
		} else if (num >= 1000000 && num < 10000000) {
			num = num * 0.000001;
			DecimalFormat df = new DecimalFormat("######0.0");  
			str = df.format(num);
			str += "百万";
		} else if (num >= 10000000) {
			num = num * 0.0000001;
			DecimalFormat df = new DecimalFormat("######0.0");  
			str = df.format(num);
			str += "千万";
		} else {
			str = Double.toString(num);
		}
		return str;
	}
	
	
	
	
	
	
	/**
	 * 方法名：isDouble
	 * 详述：判断一个字符串是否为小数
	 * 创建时间：2016年3月21日
	 * @param str
	 * @return boolean
	 */
	public static boolean isDouble(String str){
		if(isBlank(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$");
		Matcher m = pattern.matcher(str);
		return m.matches();
	}
	
	/**
	 * 方法名：isDouble
	 * 详述：判断多个个字符串是否为小数
	 * 创建时间：2016年4月22日
	 * @param str
	 * @param strs
	 * @return boolean
	 */
	public static boolean isDouble(String ... strs){
		for(String s : strs){
			if(!isDouble(s)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 方法名：isPhone
	 * 详述：判断一个字符串是否是一个正确的手机号码
	 * @param phoneStr
	 * @return boolean
	 */
	public static boolean isPhone(String phone) {
		if(isBlank(phone) || phone.length()!=11){
			return false;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(phone);
		return m.matches();
	}
	
	/**
	 * 方法名：isNumber
	 * 详述：判断一个字符是否为数字字符
	 * 创建时间：2016年4月22日
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumber(String str){
		if(isBlank(str)){
			return false;
		}
		if(StringUtils.isNumeric(str)){
			return true;
		}
		return true;
	}
	
	/**
	 * 方法名：isNumber
	 * 详述：判断多个字符是否为数字字符
	 * 创建时间：2016年4月22日
	 * @param str
	 * @param strs
	 * @return boolean
	 */
	public static boolean isNumber(String ... strs){
		for(String s : strs){
			if(!isNumber(s)){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 方法名：uploadFile
	 * 详述：上传文件
	 * @param request
	 * @param uploadFile
	 * @param path 路径，如果为null，则上传到项目默认路径
	 * @return
	 */
	public static String uploadFile(HttpServletRequest request,MultipartFile uploadFile,String path,boolean originName){
		String pathImg = null;
		String dir = (path==null?PATH_FILE:path);
		try {
			if(uploadFile != null && uploadFile.getSize()>0){
				String saveDirStr = request.getSession().getServletContext().getRealPath(dir);
				File saveDir = new File(saveDirStr);
				saveDir.mkdirs();
				String fileName = uploadFile.getOriginalFilename();
				if(!fileName.contains(".")){
					return null;
				}
				String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
				String newFileName =  UUID.randomUUID().toString().replace("-", "")+System.currentTimeMillis()+suffix;
				if(originName){
					newFileName = makeUniqueFileName(saveDir.list(), uploadFile.getOriginalFilename().replace(suffix, ""))+suffix;
				}
		        File newFile = new File(saveDirStr,newFileName);
				newFile.createNewFile();
				uploadFile.transferTo(newFile);
				pathImg = request.getContextPath()+dir+newFileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathImg;
	}
	
	public static String uploadImgCut(HttpServletRequest request,MultipartFile uploadFile,String path,int x,int y,int width,int height,int maxWidth,int maxHeight){
		//System.out.println(x+","+y+","+width+","+height+","+maxWidth+","+maxHeight);
		String pathImg = null;
		String dir = (path==null?PATH_IMG:path);
		try {
			if(uploadFile != null && uploadFile.getSize()>0){
				String saveDirStr = request.getSession().getServletContext().getRealPath(dir);
				File saveDir = new File(saveDirStr);
				saveDir.mkdirs();
				String fileName = uploadFile.getOriginalFilename();
				if(!fileName.contains(".")){
					return null;
				}
				String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
				String newFileName =  UUID.randomUUID().toString().replace("-", "")+System.currentTimeMillis()+suffix;
				String subFileName = UUID.randomUUID().toString().replace("-", "")+System.currentTimeMillis()+suffix;
		        File newFile = new File(saveDirStr,newFileName);
				newFile.createNewFile();
				uploadFile.transferTo(newFile);
				String srcPath = saveDirStr + File.separator + newFileName;
				String subPath = saveDirStr + File.separator + subFileName;
				BufferedImage bufferedImage = ImageIO.read(new File(srcPath));   
				int w = bufferedImage.getWidth();
				int h = bufferedImage.getHeight();
				if(width > w){
					width = w;
				}
				if(height > h){
					height = h;
				}
				if(w > maxWidth || h > maxHeight){
					double dw = divide((double)w,(double)maxWidth);
					double dh = divide((double)h,(double)maxHeight);
					x = (int)(x * dw);
					y = (int)(y * dh);
					width = (int)(width * dw);
					height = (int)(height * dh);
				}
				ImageUtil o = new ImageUtil(x, y, width, height,srcPath,subPath,suffix);
		        o.cut();
		        File file = new File(srcPath);
				file.delete();
				pathImg = request.getContextPath()+dir+subFileName;
				//System.out.println("Tool uploadImg() : "+pathImg);
				//System.out.println("real path : "+saveDirStr+File.separator+subFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathImg;
	}
	
	
	/**
	 * 方法名：uploadImg
	 * 详述：图片上传（单张）
	 * 创建时间：2016年3月21日
	 * @param request
	 * @param uploadFile
	 * @return String
	 */
	public static String uploadImg(HttpServletRequest request,MultipartFile uploadFile,String path){
		String pathImg = null;
		String dir = (path==null?PATH_IMG:path);
		try {
			if(uploadFile != null && uploadFile.getSize()>0){
				String saveDirStr = request.getSession().getServletContext().getRealPath(dir);
				File saveDir = new File(saveDirStr);
				saveDir.mkdirs();
				String fileName = uploadFile.getOriginalFilename();
				if(!fileName.contains(".")){
					return null;
				}
				String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
				String newFileName =  UUID.randomUUID().toString().replace("-", "")+System.currentTimeMillis()+suffix;
		        File newFile = new File(saveDirStr,newFileName);
				newFile.createNewFile();
				uploadFile.transferTo(newFile);
				pathImg = request.getContextPath()+dir+newFileName;
				//System.out.println("Tool uploadImg() : "+pathImg);
				//System.out.println("real path : "+saveDirStr+File.separator+newFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathImg;
	}
	
	/**
	 * 方法名：uploadImg
	 * 详述：图片上传（多张），返回List
	 * 创建时间：2016年3月21日
	 * @param request
	 * @param uploadFiles
	 * @return List<String>
	 */
	public static List<String> uploadImg(HttpServletRequest request,MultipartFile[] uploadFiles,String path){
		List<String> list = new ArrayList<String>();
		String dir = (path==null?PATH_IMG:path);
		try {
			if(uploadFiles != null && uploadFiles.length>0){
				for(MultipartFile uploadFile : uploadFiles){
					if(uploadFile != null && uploadFile.getSize()>0){
						String saveDirStr = request.getSession().getServletContext().getRealPath(dir);
						File saveDir = new File(saveDirStr);
						saveDir.mkdirs();
						String fileName = uploadFile.getOriginalFilename();
						if(!fileName.contains(".")){
							return null;
						}
						String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
						String newFileName =  UUID.randomUUID().toString().replace("-", "")+System.currentTimeMillis()+suffix;
				        File newFile = new File(saveDirStr,newFileName);
						newFile.createNewFile();
						uploadFile.transferTo(newFile);
						list.add(request.getContextPath()+dir+newFileName);
						//System.out.println("Tool uploadImg() : "+request.getContextPath()+dir+newFileName);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 方法名：uploadImgs
	 * 详述：图片上传（多张），返回String
	 * 创建时间：2016年4月28日
	 * @param request
	 * @param uploadFiles
	 * @return String
	 */
	public static String uploadImgs(HttpServletRequest request,MultipartFile[] uploadFiles,String path){
		List<String> imgsList = uploadImg(request, uploadFiles,(path==null?PATH_IMG:path));
		if(imgsList == null || imgsList.size()<1){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(String img : imgsList){
			sb.append(img).append(",");
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
	
	
	/**
	 * 方法名：uploadExcel
	 * 详述：上传excel文件，返回绝对路径
	 * @param request
	 * @param uploadFile
	 * @return
	 */
	public static String uploadExcel(HttpServletRequest request,MultipartFile uploadFile){
		String pathFile = null;
		try {
			if(uploadFile != null && uploadFile.getSize()>0){
				String saveDirStr = request.getSession().getServletContext().getRealPath(PATH_FILE);
				File saveDir = new File(saveDirStr);
				saveDir.mkdirs();
				String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
				if(!".xls".equals(suffix)){
					return "-1";
				}
				String newFileName =  UUID.randomUUID().toString().replace("-", "")+System.currentTimeMillis()+suffix;
		        File newFile = new File(saveDirStr,newFileName);
				newFile.createNewFile();
				uploadFile.transferTo(newFile);
				pathFile = saveDirStr+File.separatorChar+newFileName;
				System.out.println("uploadFile : "+pathFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathFile;
	}
	

	
	/**
	 * 方法名：isBlank
	 * 详述：判断String参数是否为空的方法，参数数量可变，如果其中有一个参数是空，就返回true
	 * 创建时间：2016年3月21日
	 * @param strs
	 * @return boolean
	 */
	public static boolean isBlank(String ... strs){
		for(String s : strs){
			if(StringUtils.isBlank(s)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotBlank(String ... strs){
		return !isBlank(strs);
	}

	/**
	 * 方法名：notIn
	 * 详述：判断一个String的值在不在可变参数里面,如果不在里面，就返回true
	 * 创建时间：2016年3月21日
	 * @param str
	 * @param strs
	 * @return boolean
	 */
	public static boolean notIn(String str,String ... strs){
		for(String s : strs){
			if(s.equals(str)){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 判断str在不在strs里面，如果在，返回true
	 * @param str
	 * @param strs
	 * @return
	 */
	public static boolean in(String str,String ... strs){
		for(String s : strs){
			if(s.equals(str)){
				return true;
			}
		}
		return false;
	}
	

	/**
	 * 方法名：random
	 * 详述：随机生成6位数的字符串
	 * 创建时间：2016年3月21日
	 * @return String
	 */
	public static String random(){
		int num = (int)((Math.random()*9+1)*100000);
		return String.valueOf(num);
	}
	
	/**
	 * 方法名：toLength
	 * 详述：返回将number补0，长度为length位后的字符
	 * 创建时间：2016年4月14日
	 * @param number 要补0的数字
	 * @param length 补0后的长度
	 * @return String
	 */
	public static String toLength(int number,int length){
		return String.format("%0"+length+"d", number);
	}
	
	
	/**
	 * 把Date对象格式化成String对象
	 * @param date
	 * @param format 传null默认为 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDate(Date date,String format){
		SimpleDateFormat sf = null;
		if(Tool.isBlank(format)){
			sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else{
			sf = new SimpleDateFormat(format);
		}
		return sf.format(date);
	}
	
	
	/**
	 * 方法名：toDate
	 * 详述：把String转成Date
	 * 创建时间：2016年4月22日
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException Date
	 */
	public static Date toDate(String str,String format) throws ParseException {
		if(isBlank(str)){
			return null;
		}
		if(Tool.isBlank(format)){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		Date date = null;
		SimpleDateFormat sf = new SimpleDateFormat(format);
		date = sf.parse(str);
		return date;
	}
	
	/**
	 * 获取Date对象当前月份的第一天
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getMonthFirst(Date date) {
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}
	
	/**
	 * 获取Date对象当前月份的最后一天
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getMonthLast(Date date) {
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	/**
	 * 返回Date年份加value后的Date对象
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date yearPlu(Date date,int value){
		if(date == null || value < 1){
			return null;
		}
		Calendar calendar=Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+value);
		return calendar.getTime();
	}
	
	/**
	 * 返回Date年份减value后的Date对象
	 * @param date
	 * @param value
	 * @return
	 */
	public static Date yearSub(Date date,int value){
		if(date == null || value < 1){
			return null;
		}
		Calendar calendar=Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-value);
		return calendar.getTime();
	}
	
	/**
	 * 方法名：yearSub
	 * 详述：两个日期相减，返回 date1 - date2 的年份差
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int yearSub(Date date1,Date date2){
		if(date1 != null && date2 != null){
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance(); 
			cal1.setTime(date1);
			cal2.setTime(date2);
			return (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR));
		}
		return 0;
	}
	
	/**
	 * 方法名：datePlu
	 * 详述：返回日期参数 加 value天后的Date
	 * 创建时间：2016年4月22日
	 * @param date
	 * @param value
	 * @return Date
	 */
	public static Date datePlu(Date date,int value){
		if(date == null || value < 1){
			return null;
		}
		Calendar calendar=Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+value);
		return calendar.getTime();
	}
	
	/**
	 * 方法名：dateSub
	 * 详述：返回日期参数 减 value天后的Date
	 * 创建时间：2016年4月22日
	 * @param date
	 * @param value
	 * @return Date
	 */
	public static Date dateSub(Date date,int value){
		if(date == null || value < 1){
			return null;
		}
		Calendar calendar=Calendar.getInstance(); 
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)-value);
		return calendar.getTime();
	}
	
	/**
	 * 方法名：day2Day
	 * 详述：计算两个Date对象之间相差多少天
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int day2Day(Date min,Date max){
		if(min == null || max == null || min.getTime() > max.getTime()){
			return 0;
		}
		int result = (int)((max.getTime() - min.getTime())/1000/3600/24);
		return result;
	}
	
	/**
	 * 方法名：isSameDay
	 * 详述：判断两个Date是否是同一天
	 * 创建时间：2016年4月23日
	 * @param d1
	 * @param d2
	 * @return boolean
	 */
	public static boolean isSameDay(Date d1,Date d2){
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(d1);
		cal2.setTime(d2);
		if(cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)){
			return false;
		}
		if(cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)){
			return false;
		}
		if(cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)){
			return false;
		}
		return true;
	}
	
	
	
	
	
	/**
	 * 
	 * 方法名：getWeekOne
	 * 详述：获取星期一
	 * @param date
	 * @return
	 */
	public static Date getWeekOne(Date date){
		if(date == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if(day == 1){//星期天
			return dateSub(date, 6);
		}
		if(day == 2){//星期一
			return date;
		}
		if(day == 3){
			return dateSub(date, 1);
		}
		if(day == 4){
			return dateSub(date, 2);
		}
		if(day == 5){
			return dateSub(date, 3);
		}
		if(day == 6){
			return dateSub(date, 4);
		}
		if(day == 7){
			return dateSub(date, 5);
		}
		return null;
	}
	
	
	
	
	/**
	 * 方法名：MD5
	 * 详述：MD5加密字符串，返回加密后大写
	 * 创建时间：2016年2月29日
	 * @param str
	 * @return
	 * @throws Exception String
	 */
	public static String MD5(String str){
		try{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] md5 = digest.digest(str.getBytes("UTF-8"));
			StringBuffer md5StringBuffer = new StringBuffer();
			String part = null;
			for (int i=0;i<md5.length;i++) {
				part = Integer.toHexString(md5[i] & 0xFF);
				if (part.length()==1) {
					part = "0"+part;
				}
				md5StringBuffer.append(part);
			}
			return md5StringBuffer.toString().toUpperCase();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 方法名：subImg
	 * 详述：处理CKFinder上传的图片
	 * 创建时间：2016年4月21日
	 * @param img
	 * @return String
	 */
	public static String subImg(String img){
		if(isBlank(img)){
			return null;
		}
		String imgStr = img.replace("|", ",");
		if(imgStr.subSequence(0, 1).equals(",")){
			imgStr = imgStr.substring(1);
		}
		String[] iconArr = imgStr.split(",");
		if(iconArr != null && iconArr.length>1){
			imgStr = iconArr[0];
		}
		return imgStr;
	}
	
	/**
	 * 方法名：subImgs
	 * 详述：处理CKFinder上传的图片
	 * 创建时间：2016年4月21日
	 * @param imgs
	 * @return String
	 */
	public static String subImgs(String imgs){
		if(isBlank(imgs)){
			return null;
		}
		String imgsStr = imgs.replace("|", ",");
		if(imgsStr.subSequence(0, 1).equals(",")){
			imgsStr = imgsStr.substring(1);
		}
		return imgsStr;
	}
	
	/**
	 * 方法名：dateToString
	 * 详述：Date转String
	 * 创建时间：2016年5月4日
	 * @param date
	 * @param type
	 * @return String
	 */
	public static String dateToString(Date date, String type) {
		SimpleDateFormat sdf=new SimpleDateFormat(type); 
		String str=sdf.format(date); 
		return str;
	}

	
	
	/**
	 * 发送get请求
	 * @param getUrl
	 * @return
	 */
	public static String get2(String getUrl,String authorization) {
        try { 
            //发送GET请求
            URL url = new URL(getUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Basic "+authorization);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("---------------- get() : connect failed!");
                System.out.println("获取项目列表返回 : "+conn.getResponseCode());
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
	/**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @return
     */
    public static Map<String,Object> httpPost(String url,JSONObject jsonParam,String authorization){
        //post请求返回结果
    	CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    	Map<String,Object> mapResult = new HashMap<>();
        HttpPost method = new HttpPost(url);
        String str = "";
        HttpResponse result = null;
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json;charset=UTF-8");
                method.addHeader("Authorization", "Basic "+authorization);
                method.setEntity(entity);
            }
            RequestConfig defaultRequestConfig = RequestConfig.custom()
            	    .setConnectTimeout(10000)
            	    .setConnectionRequestTimeout(10000)
            	    .build();
            method.setConfig(defaultRequestConfig);           
            result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            try {
                str = EntityUtils.toString(result.getEntity());
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
        mapResult.put("code", result.getStatusLine().getStatusCode());
        mapResult.put("data", str);
        return mapResult;
    }
	/**
	 * 方法名：printRequest
	 * 详述：打印HttpServletRequest
	 * @param request
	 */
	public static void printRequest(HttpServletRequest request){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			System.out.println("------- printRequest() : \n "+sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 方法名：trimList
	 * 详述：去掉List<String>的重复值
	 * @param oldList
	 * @return
	 */
	public static List<String> trimList(List<String> oldList){
		List<String> list = new ArrayList<String>();
		if(oldList != null && oldList.size()>0){
			for(String str : oldList){
				if(!list.contains(str)){
					list.add(str);
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 方法名：XMLToMap
	 * 详述：把xml字符串封装到Map中
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> XMLToMap(HttpServletRequest request){
		try{
			Map<String,String> map = new HashMap<String, String>();
			SAXReader reader = new SAXReader();
			InputStream ins = request.getInputStream();
			Document doc = reader.read(ins);
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			for(Element e : list){
				map.put(e.getName(), e.getText());
			}
			ins.close();
			return map;
		}catch(DocumentException e){
			System.out.println("------------ XMLToMap() DocumentException : "+e.getMessage());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 方法名：printMap
	 * 详述：打印map
	 * @param map
	 */
	public static void printMap(Map<String,String> map){
		if(map != null){
			System.out.println("******************* printMap start *******************");
			Set<String> set = map.keySet();
			for(String key : set){
				System.out.println(key+":"+map.get(key));
			}
			System.out.println("******************* printMap end *******************");
		}
	}
	
	/**
	 * 方法名：mapToXML
	 * 详述：把map转成xml字符串
	 * @param map
	 * @return
	 */
	public static String mapToXML(Map map){
		if(map != null){
			Element root = DocumentHelper.createElement("xml");  
		    Document document = DocumentHelper.createDocument(root);
			Set<String> set = map.keySet();
			for(String key : set){
				if(map.get(key) != null){
					root.addElement(key).addText(String.valueOf(map.get(key)));
				}
			}
			return document.asXML();
		}
		return "";
	}
	
	/**
	 * 方法名：objToXML
	 * 详述：把对象变成xml格式字符串
	 * @param obj
	 * @return
	 */
	public static String objToXML(Object obj){
		XStream xstream = new XStream();
		xstream.alias("xml", obj.getClass());
		return xstream.toXML(obj).replace("__", "_");
	}
	
	/** 
	 * @Description 将字符串中的emoji表情转换成可以在utf-8字符集数据库中保存的格式（表情占4个字节，需要utf8mb4字符集） 
	 * @param str 待转换字符串 
	 * @return 转换后字符串 
	 */ 
	public static String emojiConvert(String str){
		if(Tool.isNotBlank(str)){
			try{
			    String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";  
			    Pattern pattern = Pattern.compile(patternString);  
			    Matcher matcher = pattern.matcher(str);  
			    StringBuffer sb = new StringBuffer();  
			    while(matcher.find()) {  
			    	matcher.appendReplacement(sb,"[[" + URLEncoder.encode(matcher.group(1),"UTF-8") + "]]");  
			    }  
			    matcher.appendTail(sb);
			    return sb.toString();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/** 
	 * @Description 还原utf8数据库中保存的含转换后emoji表情的字符串 
	 * @param str 转换后的字符串 
	 * @return 转换前的字符串 
	 */
	public static String emojiRecovery(String str) {
		if(Tool.isNotBlank(str)){
			try{
				if(str==null){
					str ="";
				}
			    String patternString = "\\[\\[(.*?)\\]\\]";  
			    Pattern pattern = Pattern.compile(patternString);  
			    Matcher matcher = pattern.matcher(str);  
			  
			    StringBuffer sb = new StringBuffer();  
			    while(matcher.find()) {  
			    	matcher.appendReplacement(sb,URLDecoder.decode(matcher.group(1), "UTF-8"));  
			    }  
			    matcher.appendTail(sb);  
			    return sb.toString();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/**
	 * 方法名：addImgPrefixInHTML
	 * 详述：把富文本中的img标签的src的值添加前缀，如 http://xxx
	 * @param str 要处理的富文本内容
	 * @param prefix img的src需要添加的前缀
	 * @return
	 */
	public static String addImgPrefixInHTML(String str,String prefix){
		if(isNotBlank(str)){
			str = str.replace("alt=\"\"", "");
			Pattern patternForTag = Pattern.compile("<\\s*img\\s+([^>]*)\\s*>");   
	        Pattern patternForAttrib = Pattern.compile("src=\"([^\"]+)\"");
	        Matcher matcherForTag = patternForTag.matcher(str); 
	        StringBuffer sb = new StringBuffer();
	        boolean result = matcherForTag.find();
	        while (result) {
	            StringBuffer sbreplace = new StringBuffer();   
	            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));   
	            if (matcherForAttrib.find()) {
	            	String newTag = "";
	            	if(matcherForAttrib.group(1).contains("http")){
	            		newTag = "<img src='" + matcherForAttrib.group(1) + "'/>";
	            	}else{
	            		newTag = "<img src='" + prefix + matcherForAttrib.group(1) + "'/>";
	            	}
	            	matcherForAttrib.appendReplacement(sbreplace,newTag);
	            }   
	            matcherForTag.appendReplacement(sb, sbreplace.toString());   
	            result = matcherForTag.find();   
	        }   
	        matcherForTag.appendTail(sb);   
	        return sb.toString();
		}
		return null;
	}
	
	
	/**
	 * 方法名：makeUniqueFileName
	 * 详述：返回不重复的文件名
	 * @param arr 文件名称数组，通常调用File的list()方法获得
	 * @param name 原文件名
	 * @return
	 */
	public static String makeUniqueFileName(String[] arr,String name){
		Pattern p = Pattern.compile("\\([0-9]*\\)");
		if(arr != null && isNotBlank(name)){
			if(name.lastIndexOf(".") != -1){
				String suffix = name.substring(name.lastIndexOf("."));
				if(isNotBlank(suffix)){
					name = name.replace(suffix, "");
				}
			}
			Integer num = 0;
			if(arr.length>0){
				for(String str : arr){
					if(str.lastIndexOf(".") != -1){
						String suffix = str.substring(str.lastIndexOf("."));
						if(isNotBlank(suffix)){
							str = str.replace(suffix, "");
						}
					}
					Matcher mStr = p.matcher(str);
					if(mStr.find()){
						String s = mStr.group(0);
						str = str.replace(s, "");
						num = Integer.parseInt(s.replace("(", "").replace(")", ""))+1;
					}
					if(str.equals(name)){
						name += "("+num+")";
						return makeUniqueFileName(arr,name);
					}
				}
				Matcher mName = p.matcher(name);
				if(!mName.find()){
					System.out.println(11);
					for(String str : arr){
						if(str.lastIndexOf(".") != -1){
							String suffix = str.substring(str.lastIndexOf("."));
							if(isNotBlank(suffix)){
								str = str.replace(suffix, "");
							}
						}
						if(str.equals(name)){
							name = name+"(1)";
						}
					}
				}else{
					Integer newNum = 1;
					name = name.replace(mName.group(0), "");
					for(String str : arr){
						if(str.lastIndexOf(".") != -1){
							String suffix = str.substring(str.lastIndexOf("."));
							if(isNotBlank(suffix)){
								str = str.replace(suffix, "");
							}
						}
						Matcher mStr = p.matcher(str);
						if(mStr.find()){
							str = str.replace(mStr.group(0), "");
							if(str.equals(name)){
								Integer eNum = Integer.parseInt(mStr.group(0).replace("(", "").replace(")", ""));
								if(newNum <= eNum){
									newNum = eNum+1;
								}
							}
						}
					}
					name += "("+newNum+")";
				}
			}
			return name;
		}
		return null;
	}
	
	/**
	 * 方法名：formatDecimal
	 * 详述：格式化小数
	 * @param d double小数对象
	 * @param pattern 格式，null默认为#.00
	 * @return
	 */
	public Double formatDecimal(Double d,String pattern){
		if(d != null){
			if(isBlank(pattern)){
				pattern = "#.00";
			}
			DecimalFormat df = new DecimalFormat(pattern);
			return Double.parseDouble(df.format(d));
		}
		return 0.0;
	}
	
	/**
	 * 方法名：divide
	 * 详述：精确计算两个数相除，v1除以v2
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Double divide(Integer v1, Integer v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        BigDecimal bd = b1.divide(b2, 10, BigDecimal.ROUND_HALF_UP);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
	
	/**
	 * 方法名：divide
	 * 详述：精确计算两个数相除，v1除以v2
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Double divide(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        BigDecimal bd = b1.divide(b2, 10, BigDecimal.ROUND_HALF_UP);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
	
	/**
	 * 方法名：divide
	 * 详述：精确计算两个数相除，v1除以v2
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static Double divide(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        BigDecimal bd = b1.divide(b2, 10, BigDecimal.ROUND_HALF_UP);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
	
	/**
	 * 方法名：getIp
	 * 详述：获取外网IP
	 * @return
	 */
	public static String getIp(){
		String netip = null;
		try{
			boolean finded = false;// 是否找到外网IP
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			while(netInterfaces.hasMoreElements() && !finded){
				NetworkInterface ni = netInterfaces.nextElement();
	            Enumeration<InetAddress> address = ni.getInetAddresses();
	            while (address.hasMoreElements()) {
	            	 InetAddress ip = address.nextElement();
	            	 if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
	                     netip = "http://"+ip.getHostAddress();
	                     finded = true;
	                     break;
	                 } 
	            }
			}
			if(Tool.isBlank(netip)){
				netip = "http://127.0.0.1:8080";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return netip;
	}
	
	/**
	 * 方法名：escapeHtml
	 * 详述：去掉字符中的html
	 * @param str
	 * @return
	 */
	public static String escapeHtml(String str){
		if(isNotBlank(str)){
			return StringEscapeUtils.escapeHtml4(str.trim());
		}
		return null;
	}
	
	
	
	/**
	 * 方法名：isChinese
	 * 详述：判断字符串是不是中文，是返回true
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		if(isNotBlank(str)){
			char[] arr = str.toCharArray();
			if(arr != null && arr.length > 0){
				for(char c : arr){
					Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
			        if (ub != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
			                && ub != Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
			                && ub != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
			                && ub != Character.UnicodeBlock.GENERAL_PUNCTUATION
			                && ub != Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
			                && ub != Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			            return false;
			        }
				}
				return true;
			}
		}
        return false;
    }
	
	/**
	 * 方法名：suffixIs
	 * 详述：判断文件后缀
	 * @param file 文件
	 * @param suffix 后缀名
	 * @return
	 */
	public static boolean suffixIs(MultipartFile file,String suffix){
		if(file != null && isNotBlank(suffix)){
			String fileName = file.getOriginalFilename();
			if(fileName.contains(".")){
				String suffixFile = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
				if(suffix.equals(suffixFile)){
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 方法名：getWeekOne
	 * 详述：获取星期一
	 * @param date
	 * @return
	 */
	public static Date getWeekFirst(Date date){
		if(date == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if(day == 1){//星期天
			return dateSub(date, 6);
		}
		if(day == 2){//星期一
			return date;
		}
		if(day == 3){
			return dateSub(date, 1);
		}
		if(day == 4){
			return dateSub(date, 2);
		}
		if(day == 5){
			return dateSub(date, 3);
		}
		if(day == 6){
			return dateSub(date, 4);
		}
		if(day == 7){
			return dateSub(date, 5);
		}
		return null;
	}
	
	/**
	 * 方法名：getWeekLast
	 * 详述：获取星期天
	 * @param date
	 * @return
	 */
	public static Date getWeekLast(Date date){
		if(date == null){
			return null;
		}
		return datePlu(getWeekFirst(date), 6);
	}
	
	
	
	
	/**
	 * 方法名：getWeekList
	 * 详述：获取date当周星期一~星期天的日期
	 * @param date
	 * @return
	 */
	public static List<Date> getWeekList(Date date){
		if(date == null){
			return null;
		}
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if(day == 1){//星期天
			Date start = dateSub(date, 6);
			list.add(start);
			for(int i=0; i<6; i++){
				start = datePlu(start, 1);
				list.add(start);
			}
		}
		if(day == 2){//星期一
			Date start = date;
			list.add(start);
			for(int i=0; i<6; i++){
				start = datePlu(start, 1);
				list.add(start);
			}
		}
		if(day == 3){
			Date start = dateSub(date, 1);
			list.add(start);
			for(int i=0; i<6; i++){
				start = datePlu(start, 1);
				list.add(start);
			}	
		}
		if(day == 4){
			Date start = dateSub(date, 2);
			list.add(start);
			for(int i=0; i<6; i++){
				start = datePlu(start, 1);
				list.add(start);
			}
		}
		if(day == 5){
			Date start = dateSub(date, 3);
			list.add(start);
			for(int i=0; i<6; i++){
				start = datePlu(start, 1);
				list.add(start);
			}
		}
		if(day == 6){
			Date start = dateSub(date, 4);
			list.add(start);
			for(int i=0; i<6; i++){
				start = datePlu(start, 1);
				list.add(start);
			}
		}
		if(day == 7){
			Date start = dateSub(date, 5);
			list.add(start);
			for(int i=0; i<6; i++){
				start = datePlu(start, 1);
				list.add(start);
			}
		}
		return list;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(new Sha1Hash("guest12017").toString());
	}
	
}
