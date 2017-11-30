package com.sop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sop.entity.Procedure;
import com.sop.entity.StationConfig;
import com.sop.service.BasicService;
import com.sop.util.NoteResult;
import com.sop.util.SopUtil;

@Controller
@RequestMapping("/basic")
public class BasicController {
	
	private static Logger logger =Logger.getLogger(BasicController.class);
	@Resource
	private BasicService basicService;
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findAllStationConfig.do")  
	@ResponseBody
    public NoteResult findAllStaionConfig() throws Exception {  
		NoteResult result = new NoteResult();
		result = basicService.findAllStationConfig();
		return result;	  
    }
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findIPStation.do")  
	@ResponseBody
    public NoteResult findIPStation(String ipAddress) throws Exception {  
		NoteResult result = new NoteResult();
		result = basicService.findIPStation(ipAddress);
		return result;	  
    }
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findIpSopStation.do")  
	@ResponseBody
    public NoteResult findIpSopStation(String ipAddress) throws Exception {  
		NoteResult result = new NoteResult();
		result = basicService.findIpSopStation(ipAddress);
		return result;	  
    }
	
	/**
	 * 产品大类的下拉框数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findProductCodeSelect.do")  
	@ResponseBody
    public NoteResult findProductCodeSelect() throws Exception {  
		NoteResult result = new NoteResult();
		result = basicService.findProductCodeSelect();
		return result;	  
    }
	
	
	/**
	 * 操作员工的下拉框数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findEmployeeorSel.do")  
	@ResponseBody
    public NoteResult findEmployeeorSel() throws Exception {
		NoteResult result = new NoteResult();
		result = basicService.findEmployeeorSel();
		return result;	  
    }
	
	/**
	 * 工序的下拉框数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findProcedureSel.do")  
	@ResponseBody
    public NoteResult findProcedureSel(String productId) throws Exception {
		System.out.println("findProcedureSel参数：productId："+productId);
		NoteResult result = new NoteResult();
		result = basicService.findProcedureSel(productId);
		return result;	  
    }
	
	
	/**
	 * 根据产品大类+工位 查找工序表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findProcedureByIpAndProductCode.do")  
	@ResponseBody
    public NoteResult findProcedureByIpAndProductCode(String productId,String ipAddress) throws Exception {  
		System.out.println("findProcedureByIpAndProductCode参数：");
		System.out.println("productId:"+productId+",ipAddress:"+ipAddress);
		NoteResult result = new NoteResult();
		result = basicService.findProcedureByIpAndProductCode(productId,ipAddress);
		return result;	  
    }
	
	//"lineType":lineType,"functionCode":functionCode,"functionName":functionName,"procedureNo":procedureNo,
	//"userId":userId,"ipAddress":ipAddress,"macAddress":macAddress
	/**
	 * 
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping("/saveStationConfig.do")
	@ResponseBody
	public NoteResult saveStationConfig(String lineType,String functionCode,String functionName,Integer procedureNo,
			String userId,String ipAddress,String macAddress,HttpSession session) throws Exception{
		StationConfig config = new StationConfig();
		config.setFunction_code(functionCode);
		config.setFunction_name(functionName);
		config.setUser_id(userId);
		config.setIp_address(ipAddress);
		config.setLine_type(lineType);
		config.setMac_address(macAddress);
		config.setProcedure_no(procedureNo);
		Date dateNow = new Date();
		String dateForm = SopUtil.dateFormat2(dateNow);
		config.setPub_crtdate(dateForm);
		NoteResult result = basicService.saveStationConfig(config);
		return result;	
	}*/
	
	/**
	 * 保存工位配置表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveStationConfig.do")
	@ResponseBody
	public NoteResult saveStationConfig(String lineType,String functionCode,Integer productId,Integer procedureNo,
			String userId,String ipAddress,HttpSession session) throws Exception{
		StationConfig config = new StationConfig();
		config.setFunction_code(functionCode);
		//config.setFunction_name(functionName);
		config.setProduct_id(productId);
		config.setUser_id(userId);
		config.setIp_address(ipAddress);
		config.setLine_type(lineType);
		//config.setMac_address(macAddress);
		config.setProcedure_no(procedureNo);
		Date dateNow = new Date();
		String dateForm = SopUtil.dateFormat2(dateNow);
		config.setPub_crtdate(dateForm);
		logger.error("saveStationConfig.do参数");
		logger.error("新增工位配置："+config.toString());
		NoteResult result = basicService.saveStationConfig(config);
		return result;	
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateStationConfig.do")
	@ResponseBody
	public NoteResult updateStationConfig(Integer scfId,String lineType,String functionCode,String functionName,Integer procedureNo,
			String userId,String ipAddress,String macAddress,HttpSession session) throws Exception{
		StationConfig config = new StationConfig();
		config.setMes_scf_id(scfId);
		config.setFunction_code(functionCode);
		config.setFunction_name(functionName);
		config.setIp_address(ipAddress);
		config.setUser_id(userId);
		config.setLine_type(lineType);
		config.setMac_address(macAddress);
		config.setProcedure_no(procedureNo);
	/*	Date dateNow = new Date();
		String dateForm = SopUtil.dateFormat2(dateNow);
		config.setPub_crtdate(dateForm);*/
		NoteResult result = basicService.updateStationConfig(config);
		return result;	
	}
	
	/**
	 * 工位配置更新
	 * 根据（工位+产品大类查询）
	 * 如果没有，就新增，如果有，就更新
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveOrUpdateConfig.do")
	@ResponseBody
	public NoteResult saveOrUpdateConfig(String lineType,String functionCode,Integer productId,String procedureNo,
			String userId,String ipAddress,HttpSession session) throws Exception{
		System.out.println("procedureNo:"+procedureNo+",ipAddress:"+ipAddress);
		StationConfig config = new StationConfig();
		config.setFunction_code(functionCode);
		config.setProduct_id(productId);
		config.setUser_id(userId);
		config.setLine_type(lineType);
		//config.setProcedure_no(procedureNo);
		config.setIp_address(ipAddress);
		Date dateNow = new Date();
		String dateForm = SopUtil.dateFormat2(dateNow);
		config.setPub_crtdate(dateForm);
		System.out.println(procedureNo);
		NoteResult result = basicService.saveOrUpdateConfig(config,procedureNo);
		return result;	
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteStationConfig.do")
	@ResponseBody
	public NoteResult deleteStationConfig(Integer scfId,HttpSession session) throws Exception{
		NoteResult result = new NoteResult();
		if(scfId != null){
			result = basicService.deleteStationConfig(scfId);
		}
		return result;	
	}
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findAllProcedure.do")  
	@ResponseBody
    public NoteResult findAllProcedure() throws Exception {  
		NoteResult result = new NoteResult();
		
		result = basicService.findAllProcedure();
		return result;	  
    }
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveProcedure.do")
	@ResponseBody
	public NoteResult saveProcedure(String productCode,String productName,Integer procedureNo,
			String procedureCode,String procedureName,HttpSession session) throws Exception{
		Procedure procedure = new Procedure();
		procedure.setProduct_code(productCode);
		procedure.setProduct_name(productName);
		procedure.setProcedure_no(procedureNo);
		procedure.setProcedure_code(procedureCode);
		procedure.setProcedure_name(procedureName);
		Date dateNow = new Date();
		String dateForm = SopUtil.dateFormat2(dateNow);
		procedure.setPub_crtdate(dateForm);
		NoteResult result = basicService.saveProcedure(procedure);
		return result;	
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateProcedure.do")
	@ResponseBody
	public NoteResult updateProcedure(Integer procedureId,String productCode,String productName,Integer procedureNo,
			String procedureCode,String procedureName,HttpSession session) throws Exception{
		Procedure procedure = new Procedure();
		procedure.setProcedure_id(procedureId);
		procedure.setProduct_code(productCode);
		procedure.setProduct_name(productName);
		procedure.setProcedure_no(procedureNo);
		procedure.setProcedure_code(procedureCode);
		procedure.setProcedure_name(procedureName);
		/*Date dateNow = new Date();
		String dateForm = SopUtil.dateFormat2(dateNow);
		procedure.setPub_crtdate(dateForm);*/
		NoteResult result = basicService.updateProcedure(procedure);
		return result;	
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteProcedure.do")
	@ResponseBody
	public NoteResult deleteProcedure(Integer procedureId,HttpSession session) throws Exception{
		NoteResult result = new NoteResult();
		if(procedureId != null){
			result = basicService.deleteProcedure(procedureId);
		}
		return result;	
	}
	
	//DEVICEiNFO 工艺信息表（参数）
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findAllDeviceInfo.do")  
	@ResponseBody
    public NoteResult findAllDeviceInfo() throws Exception {  
		NoteResult result = new NoteResult();
		result = basicService.findAllDeviceInfo();
		return result;	  
    }
	
	@RequestMapping(value = "/findAllDeviceInfoJosn.do")  
	@ResponseBody
    public String findAllDeviceInfoJosn(HttpServletRequest request) throws Exception { 
		int rows = Integer.valueOf(request.getParameter("rows")); //每页中显示的记录行数
		int page = Integer.valueOf(request.getParameter("page")); //当前的页码
		System.out.println(rows+","+page);
		String sord = request.getParameter("sord");//排序方式
		String sidx = request.getParameter("sidx");//排序列名
		System.out.println(sord+","+sidx);
		Boolean search =(request.getParameter("_search").equals("true"))?true:false;//是否用于查询请求
		List<Map<String, Object>> allList = new ArrayList<Map<String, Object>>();//返回结果集
		allList = basicService.findAllDeviceInfoJosn();
		List<List<String>> arrList = new ArrayList<List<String>>();
		 //DVEID,DVEPJTID,DVETYPE,DVEDTYPE,DVEVLE,createDate 
		for(int i=0;i<allList.size();i++){
			List<String> arr = new ArrayList<String>();
			arr.add(String.valueOf(allList.get(i).get("DVEID")));
			arr.add(String.valueOf(allList.get(i).get("DVEPJTID")));
			arr.add((String)allList.get(i).get("DVETYPE"));
			arr.add((String)allList.get(i).get("DVEDTYPE"));
			arr.add(String.valueOf(allList.get(i).get("DVEVLE")));
			arr.add((String)allList.get(i).get("CREATEDATE").toString());
			arrList.add(arr);
		}
		//升降序SQL语句转换
		/*if (sidx!=null&&!"".equals(sidx)) {
		System.out.println(sidx);
		keys += " ORDER BY " + sidx;
		System.out.println("sord="+sord);
		if (!sord.equals("asc")) {
		keys += " DESC ";
		}*/
		//allList = Factory.getDemoDAOInstance().doSearch(keys);
		//分页部分
		int total=0; 
		total=(arrList.size()%rows==0)?(arrList.size()/rows):((arrList.size()/rows)+1);
		int j = 0;
		int m = (page-1)*rows;
		int n = (page-1)*rows+rows;
		JSONArray jArray = new JSONArray();
		for (j=m; j<arrList.size()&&j<n; j++) { 
		jArray.add(JSONArray.fromObject(arrList.get(j))); 
		}
		JSONObject jjson = new JSONObject(); 
		//检索结果及分页信息封装 返回
		jjson.accumulate("page", page);
		jjson.accumulate("total", total);
		jjson.accumulate("records", arrList.size());
		jjson.accumulate("rows", jArray);
		System.out.println(jjson.toString());
		//response.getWriter().write(jjson.toString());
		
		return jjson.toString();	  
    }
	
	//"lineType":lineType,"functionCode":functionCode,"functionName":functionName,"procedureNo":procedureNo,
	//"userId":userId,"ipAddress":ipAddress,"macAddress":macAddress
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveDeviceInfo.do")
	@ResponseBody
	public NoteResult saveDeviceInfo(Integer dvepjtid,String dvetype,String dvedtype,
			String dvevle) throws Exception{
		System.out.println("saveDeviceInfo参数：");
		System.out.println("dvepjtid："+dvepjtid+",dvetype:"+dvetype+",dvedtype："+dvedtype+",dvevle:"+dvevle);
		NoteResult result = basicService.saveDeviceInfo(dvepjtid, dvetype, dvedtype, dvevle);
		return result;	
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateDeviceInfo.do")
	@ResponseBody
	public NoteResult updateDeviceInfo(Integer dveid,Integer dvepjtid,String dvetype,String dvedtype,
			String dvevle,HttpSession session) throws Exception{
		NoteResult result = basicService.updateDeviceInfo(dveid, dvepjtid, dvetype, dvedtype, dvevle);
		return result;	
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteDeviceInfo.do")
	@ResponseBody
	public NoteResult deleteDeviceInfo(Integer dveid,HttpSession session) throws Exception{
		NoteResult result = new NoteResult();
		if(dveid != null){
			result = basicService.deleteDeviceInfo(dveid);
		}
		return result;	
	}
	
	
	//DEVICEiNFO 工艺信息表（参数）
	
		/**
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/findAllCheckInfo.do")  
		@ResponseBody
	    public NoteResult findAllCheckInfo() throws Exception {  
			NoteResult result = new NoteResult();
			result = basicService.findAllCheckInfo();
			return result;	  
	    }
		
		
		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/saveCheckInfo.do")
		@ResponseBody
		public NoteResult saveCheckInfo(String chkpjtid,String chktype,double chkuvl,
				double chkdvl) throws Exception{	
			NoteResult result = basicService.saveCheckInfo(chkpjtid, chktype, chkuvl, chkdvl);
			return result;	
		}
		
		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/updateCheckInfo.do")
		@ResponseBody
		public NoteResult updateCheckInfo(long chkid,String chkpjtid,String chktype,double chkuvl,
				double chkdvl,HttpSession session) throws Exception{
			NoteResult result = basicService.updateCheckInfo(chkid, chkpjtid, chktype, chkuvl, chkdvl);
			return result;	
		}
		
		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/deleteCheckInfo.do")
		@ResponseBody
		public NoteResult deleteCheckInfo(Integer chkid,HttpSession session) throws Exception{
			NoteResult result = new NoteResult();
			if(chkid != null){
				result = basicService.deleteCheckInfo(chkid);
			}
			return result;	
		}
}
