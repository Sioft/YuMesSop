package com.sop.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.sop.entity.ProcedureEntity;
import com.sop.entity.ProductTree;
import com.sop.entity.SopShow;
import com.sop.service.SopService;
import com.sop.util.NoteResult;
import com.sop.util.SopUtil;


@Controller
@RequestMapping("/sop")
public class SopController {
	
	private static Logger logger =Logger.getLogger(SopController.class);
	
	@Resource
	private SopService sopService;
	
	
	/**
	 *  根据产品大类和工位找到所有版本的sop
	 *  待完成
	 *  木有使用
	 * @param stationCode
	 * @param productCode
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findAllSop.do")  
	@ResponseBody
    public NoteResult findAllVersionSop(String productCode,String procedureNo,HttpServletRequest request, ModelMap model,HttpSession session) throws Exception {  
		NoteResult result = new NoteResult();
		List<SopShow> sopShow = sopService.findAllVersionSop(productCode,procedureNo,session);
		return result;	  
    } 
	/**
	 * 
	 * @param stationCode
	 * @param productCode
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findSop.do")  
	@ResponseBody
    public NoteResult findSop(String barCode,String ipAddress) throws Exception { 
		logger.error("findSop.do");
		logger.error("参数：barCode："+barCode+",ipAddress"+ipAddress);
		System.out.println("findSop参数");
		System.out.println("barCode："+barCode+",ipAddress:"+ipAddress);
		NoteResult result = new NoteResult();
		SopShow sopShow = sopService.findSop(barCode,ipAddress);
		if(sopShow != null){
			//session.setAttribute("pdglist", sopShow.getPdglist());
			//session.setAttribute("versionNo", sopShow.getVersionNo());
			//session.setAttribute("validTime", sopShow.getValidTime());
			
			//判断是否有新版本、
			
			result.setStatus(0);
			//result.setData(sopShow.getPdglist());
			result.setData(sopShow);
		}else{
			result.setStatus(1);
		}
		return result;	  
    } 
	
	/**
	 * 
	 * 工序组改进
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findSopPlus.do")  
	@ResponseBody
    public NoteResult findSopPlus(String procedureNos,String productCode) throws Exception { 
		logger.error("findSopPlus.do");
		logger.error("参数：procedureNos："+procedureNos+",productCode"+productCode);
		NoteResult result = new NoteResult();
		result = sopService.findSopPlus(procedureNos, productCode);
		
		return result;	  
    } 
	/**
	 *  根据产品大类和工位找到所需的sop
	 *  
	 *  此接口用于工位触摸屏
	 * @param stationCode
	 * @param productCode
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/findSop.do")  
	@ResponseBody
    public NoteResult findSop(String stationCode,String productCode,HttpServletRequest request, ModelMap model,HttpSession session) throws Exception {  
		NoteResult result = new NoteResult();
		SopShow sopShow = sopService.findSop(stationCode,productCode,session);
		if(sopShow != null){
			//session.setAttribute("pdglist", sopShow.getPdglist());
			//session.setAttribute("versionNo", sopShow.getVersionNo());
			//session.setAttribute("validTime", sopShow.getValidTime());
			
			//判断是否有新版本、
			
			result.setStatus(0);
			//result.setData(sopShow.getPdglist());
			result.setData(sopShow);
		}else{
			result.setStatus(1);
		}
		return result;	  
    } */
	/**
	 *  根据产品大类和工位找到所需的sop产品过站信息
	 *  
	 *  此接口用于工位触摸屏
	 * @param stationCode
	 * @param productCode
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/findMessage.do")  
	@ResponseBody
    public NoteResult findProductMessage(String barCode,int procedureNo) throws Exception {  
		System.out.println("findMessage参数");
		System.out.println("barCode:"+barCode+",procedureNo:"+procedureNo);
		NoteResult result = new NoteResult();
		result = sopService.findMessage(barCode,procedureNo);
		return result;	  
    } */
	
	@RequestMapping(value = "/findMessage.do")  
	@ResponseBody
    public NoteResult findProductMessage(String barCode,String ipAddress) throws Exception {  
		System.out.println("findMessage参数");
		System.out.println("barCode:"+barCode+",ipAddress:"+ipAddress);
		NoteResult result = new NoteResult();
		result = sopService.findMessage(barCode,ipAddress);
		return result;	  
    } 
	
	/**
	 * 根据产片大类和工序序号,版本号找到所需的sop
	 * @param productCode
	 * @param procedureNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findVersionSop.do")
	@ResponseBody
	public NoteResult findVersionSop(String productCode,String procedureNo,int versionNo,HttpSession session) throws Exception{
		NoteResult result = new NoteResult();
		SopShow sopShow = sopService.findVersionSop(productCode, procedureNo,versionNo);
		System.out.println("sopShow是否为空："+sopShow);
		if(sopShow != null){
			session.setAttribute("pdglist", sopShow.getPdglist());
		
			result.setStatus(0);
			result.setData(sopShow.getPdglist());
		}else{
			result.setStatus(1);
		}
		return result;	
	}
	/**
	 * 根据产片大类和工序序号找到所需的sop
	 * @param productCode
	 * @param procedureNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findExsit.do")
	@ResponseBody
	public NoteResult findExsitSop(String productCode,String procedureNo,HttpSession session) throws Exception{
		NoteResult result = new NoteResult();
		//List<Map<String, String>> pdglist = new ArrayList<Map<String,String>>();
		//SopShow sopShow = new SopShow();
		SopShow sopShow = sopService.findExsitSop(productCode, procedureNo);
		System.out.println("sopShow是否为空："+sopShow);
		//session.setAttribute("sopShow", sopShow);
		if(sopShow != null){
			session.setAttribute("pdglist", sopShow.getPdglist());
			session.setAttribute("versionNo", sopShow.getVersionNo());
			session.setAttribute("validTime", SopUtil.dateFormat2((Date)sopShow.getPdglist().get(0).get("VALIDTIME")));
			session.setAttribute("versionlist", sopShow.getVersionlist());
			//判断是否有新版本、
			//session.setAttribute("nextVersion", sopShow.getNextVersion());
			result.setStatus(0);
			result.setData(sopShow.getPdglist());
		}else{
			result.setStatus(1);
		}
		return result;	
	}
	/**
	 * 上传文件到服务器，并将信息保存进入数据库
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/uploader.do")
	@ResponseBody
    public NoteResult upload(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		NoteResult result = new NoteResult();
		//List<ProcessDesignInfo> pdglist = new ArrayList<ProcessDesignInfo>();
		//获取参数
		String nodePath = request.getParameter("nodePath");//相对路径
		String productCode = request.getParameter("productCode");//产品大类编码
		String procedureNo = request.getParameter("procedureNo");//工序序号
		int versionNo = Integer.parseInt(request.getParameter("versionNo"));//版本号
		String validTime = request.getParameter("validTime");//工序序号
		//将字符串类型保存到日期类型
		logger.error("uploader.do");
		logger.error("上传参数：nodePath:"+nodePath+",productCode:"+productCode+",procedureNo:"+procedureNo+",versionNo:"+versionNo+",validTime:"+validTime);
		System.out.println("文件上传所需参数----------------------------------");
		System.out.println("nodePath:"+nodePath);
		System.out.println("productCode:"+productCode);
		System.out.println("procedureNo:"+procedureNo);
		System.out.println("versionNo:"+versionNo);
		System.out.println("validTime:"+validTime);
		String validTimeNew = "";
		//生效时间格式转换
		try {
			validTimeNew = SopUtil.dateFormat(validTime);
			System.out.println("validTimeNew:"+validTimeNew);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
		//获取文件列表
		MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest)request;
	    Map<String, MultipartFile> files = Murequest.getFileMap();//得到文件map对象
	    System.out.println(files.toString());
	    //获取存储根目录
	    String path = request.getSession().getServletContext().getRealPath("upload");
	    System.out.println("文件保存的根目录文件夹路径："+path);
	    try {
	    	result = sopService.saveSopInfo(files,path,nodePath,
	    			productCode,procedureNo,versionNo,validTimeNew);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1);
		}
		
		
     return result;  
	}
	
	@RequestMapping("/findChild.do")
	@ResponseBody
	public String getChildCategoryTree() { 
		logger.error("findChild.do");
		logger.error("参数：无");
		List<ProductTree> childOfCats = new ArrayList<ProductTree>();
		//为返回json字符串声明一个String
		String jsonStr = null;                                                                                                                                                                                                                   
		//查询结果集                                                                                                                                         
		try {
			childOfCats = sopService.findChild();
			System.out.println("childOfCats:"+childOfCats);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		                                                                                                                                                                                                           
        Map<String,ProductTree> treeNodeMap = new HashMap<String,ProductTree>();                                                                                                                                                   
         List<ProductTree> listToJson = new ArrayList<ProductTree>();                                                                                                                                                               
         List<Map.Entry<String, ProductTree>> jsonList = null;                                                                                                                                                                      
            	                                                                                                                                                                                                                               
       //如果获取的子目录列表不为空                                                                                                                                                                        
         if(childOfCats != null){
        	 JSONArray json = null;
        	//遍历子目录                                                                                                                                                                                                                    
             for (ProductTree child : childOfCats ) {
             	treeNodeMap.put(String.valueOf(child.getNode_id()), child);                                                                                                                                                      
             } 
             System.out.println("treeNodeMap:"+treeNodeMap);                                                                                                                                                                                                                      
                                                                                                                                                                                                                                             
	        //让Map排序                                                                                                                                                                                                                 
	        jsonList = new ArrayList<Map.Entry<String, ProductTree>>(treeNodeMap.entrySet());                                                                                                                                      
	        Collections.sort(jsonList,new Comparator<Map.Entry<String, ProductTree>>(){                                                                                                                                            
	        	@Override
				public int compare(Map.Entry<String, ProductTree> mapping1,Map.Entry<String, ProductTree> mapping2){                                                                                                               
	        		return mapping1.getKey().compareTo(mapping2.getKey());                                                                                                                                                               
	        	}                                                                                                                                                                                                                      
	        }); 
	        //
	        for (Map.Entry<String, ProductTree> entry : jsonList) {
	        	if(!"0".equals(String.valueOf(entry.getValue().getParent_node_id()))){                                                                                                                                                        
            		treeNodeMap.get(String.valueOf(entry.getValue().getParent_node_id())).addTreeNode(entry.getValue());                                                                                                                        
            	}else{                                                                                                                                                                                                                 
            		listToJson.add(entry.getValue());                                                                                                                                                                                    
            	}     
			}                                                                                                                                                                                                                                  
	         //转换为需要的josn字符串                                                                                                                                                                                                          
	        json = JSONArray.fromObject(listToJson);                                                                                                                                                                                 
            jsonStr = json.toString();
            System.out.println("ss:"+jsonStr);
            logger.error("findChild.do返回结果："+jsonStr);
         }   
         return jsonStr;
	}
	
	//deleteVersionSop
	/**
	 * 根据产片大类和工序序号,版本号找到所需的sop
	 * 执行删除操作
	 * @param productCode
	 * @param procedureNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteVersionSop.do")
	@ResponseBody
	public NoteResult deleteVersionSop(String productCode,String procedureNo,int versionNo,HttpSession session) throws Exception{
		NoteResult result = new NoteResult();
		
		result = sopService.deleteVersionSop(productCode, procedureNo,versionNo);
		
		return result;	
	}
	
	/**
	 * 所有工序的所有sop同时展示
	 * 
	 * @param productCode
	 * @param procedureNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findSopAllProce.do")
	@ResponseBody
	public NoteResult findSopAllProce(String productCode,HttpSession session) throws Exception{
		logger.error("findSopAllProce.do");
		logger.error("参数:productCode:"+productCode);
		NoteResult result = new NoteResult();
		List<ProcedureEntity> procelist = new ArrayList<ProcedureEntity>();
		//1.获取所有的sop
		result = sopService.findSopAllProce(productCode);
		session.setAttribute("procelist", result.getData());
		logger.error("findSopAllProce.do返回结果:"+result.getData());
		return result;	
	}
	
}
