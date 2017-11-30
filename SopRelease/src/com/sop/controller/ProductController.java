package com.sop.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sop.util.NoteResult;
import com.sop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private static Logger logger =Logger.getLogger(ProductController.class);
	
	@Resource
	private ProductService productService;
	
	@RequestMapping("/findAll.do")
	@ResponseBody
	public NoteResult findAllProducts() throws Exception{
		NoteResult result = productService.findAllProducts();
		return result;	
	}
	/**
	 * obj.barCode = barCode;
	obj.pordId = pordId;
	obj.ipAddress = ipAddress;
	obj.sopVersion = sopVersion;
	obj.productCode = productCode;
	obj.userCode = userCode;
	 * @param barCode
	 * @param pordId
	 * @param ipAddress
	 * @return
	 */
	@RequestMapping("/savePassProduct.do")
	@ResponseBody
	public NoteResult savePassProduct(String barCode,String pordId,String ipAddress,String userCode,String productCode,String sopVersion) throws Exception{
		System.out.println("savePassProduct参数");
		System.out.println("barCode："+barCode+",pordId:"+pordId+",ipAddress:"+ipAddress+",userCode:"+userCode+",productCode:"+productCode+",sopVersion:"+sopVersion);
		NoteResult result = productService.savePassProduct(barCode,pordId,ipAddress,userCode,productCode,sopVersion);
		return result;	
	}
	
	/**
	 * 保存关键原物料的信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveMaterielMsg.do")
	@ResponseBody
	public NoteResult saveMaterielMsg(String barCode,String ipAddress,String productBarCode,String procedureName,HttpSession session) throws Exception{
		System.out.println("saveMaterielMsg参数");
		System.out.println("barCode："+barCode+",ipAddress:"+ipAddress+",productBarCode:"+productBarCode+",procedureName:"+procedureName);
		NoteResult result = productService.saveMaterielMsg(barCode,ipAddress,productBarCode,procedureName);
		return result;	
	}
	
	/**
	 * 保存主观不良的信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveDecfectiveMsg.do")
	@ResponseBody
	public NoteResult saveDecfectiveMsg(String defectiveBarCode,String ipAddress,String productBarCode,String defectiveType,HttpSession session) throws Exception{
		System.out.println("saveMaterielMsg参数");
		System.out.println("defectiveBarCode："+defectiveBarCode+",ipAddress:"+ipAddress+",productBarCode:"+productBarCode+",defectiveType:"+defectiveType);
		NoteResult result = productService.saveDecfectiveMsg(defectiveBarCode,ipAddress,productBarCode,defectiveType);
		return result;	
	}
	
	
	/**
	 * 从产品记录表中获取将要产生的下一工位产品
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findNextProducts.do")
	@ResponseBody
	public NoteResult findNextProducts(String barCode) throws Exception{
		//获取最新的四条记录
		
		System.out.println("findNextProducts参数");
		System.out.println("barCode："+barCode);
		NoteResult result = productService.findNextProducts(barCode);
		return result;	
	}
	
	
	/**
	 * 新增产品大类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveNewProductCode.do")
	@ResponseBody
	public NoteResult saveNewProductCode(String nodeName,String nodeParentId,String nodeParentPath,String menuLevel) throws Exception{
		//获取参数
		System.out.println("saveNewProductCode参数");
		System.out.println("nodeName："+nodeName+",nodeParentId:"+nodeParentId+",nodeParentPath:"+nodeParentPath+",menuLevel:"+menuLevel);
		NoteResult result = productService.saveNewProductCode(nodeName, nodeParentId, nodeParentPath, menuLevel);
		return result;	
	}
	
	/**
	 * 删除产品大类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteProductCode.do")
	@ResponseBody
	public NoteResult deleteProductCode(String nodeId) throws Exception{
		//获取参数
		System.out.println("deleteProductCode参数");
		System.out.println("nodeId："+nodeId);
		NoteResult result = productService.deleteProductCode(nodeId);
		return result;	
	}
	/**
	 * 修改产品大类
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editNewProductCode.do")
	@ResponseBody
	public NoteResult editNewProductCode(String nodeId,String nodeName) throws Exception{
		//获取参数
		System.out.println("editNewProductCode参数");
		System.out.println("nodeId："+nodeId+",nodeName:"+nodeName);
		NoteResult result = productService.editNewProductCode(nodeId,nodeName);
		return result;	
	}
	
	
	/**
	 * 初始化主观不良下拉框
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findSubjectDefectiveSel.do")
	@ResponseBody
	public NoteResult findSubjectDefectiveSel() throws Exception{
		//获取参数
		System.out.println("findSubjectDefectiveSel参数");
		//System.out.println("nodeId："+nodeId+",nodeName:"+nodeName);
		NoteResult result = productService.findSubjectDefectiveSel();
		return result;	
	}
	
	
}
