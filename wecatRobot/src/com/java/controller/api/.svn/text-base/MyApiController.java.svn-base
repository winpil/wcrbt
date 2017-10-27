package com.java.controller.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.common.constance.ApiConstance;
import com.java.entity.IssueType;
import com.java.entity.SyncLog;
import com.java.service.SyncFieldMappedService;
import com.java.service.SyncLogService;
import com.java.service.SyncProjectMappedService;
import com.java.service.SyncTypeMappedService;
import com.java.sys.basic.controller.BaseController;
import com.java.sys.basic.entity.BaseResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;



@Api(value="api-my-controller",description="数据集成")
@Controller
@RequestMapping("/api/jira")
public class MyApiController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(MyApiController.class);

	
	@Resource
	private SyncProjectMappedService syncProjectMappedService;
	@Resource
	private SyncTypeMappedService syncTypeMappedService;
	@Resource
	private SyncFieldMappedService syncFieldMappedService;
	@Resource
	private SyncLogService synclLogService;
	
	@ApiOperation(value = "getType")
	@RequestMapping(value = "/getType", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})	
	public ResponseEntity<BaseResult> getType(HttpServletRequest request,HttpServletResponse response,
			@ApiParam("projectId") @RequestParam(value="projectId",required=false) Integer projectId){
		List<IssueType> list = syncProjectMappedService.getIssueType(projectId);
		return buildSuccessInfo(list);
	}
	
	@ApiOperation(value = "addIssue")
	@RequestMapping(value = "/addIssue", method = RequestMethod.POST)
	@ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})	
	public ResponseEntity<BaseResult> addIssue(HttpServletRequest request,HttpServletResponse response,
			@ApiParam("data") @RequestParam(value="data",required=false) String data){
		log.info("------------------------in addIssue api--------------------------------");
		Map<String,String> projectMapped = syncProjectMappedService.getMapped();
		Map<String,String> typeMapped = syncTypeMappedService.getMapped();
		Map<String,String> fieldMapped = syncFieldMappedService.getMapped();
		Map<String,String> fieldTypeMapped = syncFieldMappedService.getTypeMapped();
		Map<String,Object> result = syncProjectMappedService.createIssue(data,projectMapped,typeMapped,fieldMapped,fieldTypeMapped);
		SyncLog l = new SyncLog();
		l.setResult(result.get("data").toString());
		l.setRequestData(result.get("requestData").toString());
		l.setReceive(data);
		l.setCode((Integer) result.get("code"));
		try{
			synclLogService.save(l);
		}catch(Exception e){
			e.printStackTrace();
		}
		BaseResult br = null;
		if("201".equals(result.get("code").toString())){
			br = new BaseResult(0, "success", result.get("data"));
		}else{
			br = new BaseResult(-1, "error", result.get("data"));
		}
		return new ResponseEntity<BaseResult>(br, HttpStatus.OK);
	}
	
	
}
