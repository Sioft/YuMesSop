package com.sop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.org.mozilla.javascript.internal.regexp.SubString;

import com.sop.controller.ProductController;
import com.sop.dao.ProductDao;
import com.sop.entity.Maintain;
import com.sop.entity.ProductInfo;
import com.sop.entity.ProductRecord;
import com.sop.entity.ProductTree;
import com.sop.service.ProductService;
import com.sop.util.NoteResult;
import com.sop.util.SopUtil;

@Service
public class ProductServiceImpl implements ProductService{

	private static Logger logger =Logger.getLogger(ProductController.class);
	@Resource
	private ProductDao productDao;
	
	@Override
	public NoteResult findAllProducts() throws Exception {
		NoteResult result = new NoteResult();
		List<ProductInfo> productList = productDao.findAll();
		System.out.println("产品大类列表"+productList.toString());
		if(productList!=null && productList.size()>0){
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(productList);
		}else {
			result.setStatus(1);
			result.setMsg("未发现产品大类");
		}
		return result;
	}


	@Override
	@Transactional
	public NoteResult savePassProduct(String barCode,String pordId,String ipAddress,String userCode,String productCode,String sopVersion) throws Exception {
		//System.out.println("barCode:"+barCode+",ipAddress:"+ipAddress+",message:"+message+",passTime:"+passTime);
		NoteResult result = new NoteResult();
		Date dateNow = new Date();
		String passTime = SopUtil.dateFormat2(dateNow);
		//判断流程卡是否存在
		//String lineNum = productDao.findMaterialLinenum(barCode);
		String barCodeNum = productDao.findProcessNum(barCode);
		System.out.println("barCodeNum:"+barCodeNum);
		Integer qcId = null;
		if(barCodeNum != null){
			//已存在过站记录
			 //qcId = productDao.savePassProductM(barCode,pordId,passTime,ipAddress,lineNum);
			qcId = productDao.savePassUser(userCode, sopVersion, barCode, ipAddress, passTime);
		}else{
			//第一次插入
			// Integer mGroup = productDao.findMaterialNextSeq();
			// System.out.println("mGroup:"+mGroup);
			 qcId = productDao.savePassProduct(barCode, barCode, barCode, pordId, passTime);
			 logger.error("first insert processnum");
			 logger.error("barCode:"+barCode+",pordId:"+pordId+",passTime:"+passTime);
			 if(qcId != null){
				 //插入userp信息
				 productDao.savePassUser(userCode, sopVersion, barCode, ipAddress, passTime);
				//上线
				//第一次插入流程卡的时候为上线，更新生产辅助表
				 String countDate = SopUtil.dateFormat3(dateNow);
				 Integer onlineCount = productDao.findProductOnlineCount(countDate);
				 System.out.println("sys生产数量:"+onlineCount);
				 if(onlineCount != null){
					 productDao.updateProductOnlineCount(countDate,onlineCount.intValue()+1,passTime);
					 System.out.println("更新车公共");
				 }else{
					 productDao.saveProductAssistCount(countDate, 1, 0, passTime, passTime);
					 System.out.println("sys生产数量插入成功");
				 }
				 
				 //第一次插入流程卡的时候为上线，更新任务单辅助表
				 Integer onlinePordCount = productDao.findPordOnlineCount(pordId);
				 if(onlinePordCount != null){
					 productDao.updatePordOnlineCount(pordId,onlinePordCount.intValue()+1,passTime);
				 }else{
					 productDao.savePordAssistCount(pordId, 1, 0, passTime, passTime);
					 //将任务单状态改为已上线任务单(m_code置为Y)
					 productDao.updatePordMcodeStatus(pordId);
					 
				 }
			 }
		}
		//9月26日更新：更新产品状态
		//查询当前IP地址对应的产品状态
		String ipStatus = productDao.findIpConfigByIpaddress(ipAddress);
		System.out.println("ipStatusssssss"+ipStatus);
		if(ipStatus != null){
			//更新状态之前状态判断
			//获取该流程卡当前状态
			String currentStatus = productDao.findCurrentProductStatusByBarCode(barCode);
			System.out.println("ipStatusssssss2"+currentStatus);
			if(!("09".equals(currentStatus) || "Y".equals(currentStatus))){
				//如果状态不是不良品，或者不是完成 就更新
				//更新产品的状态
				productDao.updateProductStatus(barCode,ipStatus);
			}else if("09".equals(currentStatus)){
				System.out.println("产品状态为不良品！");
				//查询维修工位关于此流程卡的记录
				String reonStation = productDao.findRepairMsgBybarCode(barCode);
				System.out.println("reonStation"+reonStation);
				if(reonStation != null){
					//如果状态不是不良品，或者不是完成 就更新
					//更新产品的状态
					productDao.updateProductStatus(barCode,ipStatus);
					//if()
					//如果上线工位不等于当前工位
				}else{
					//如果上线工位为空
					result.setStatus(1);
					result.setMsg("产品待维修！");
					return result;
				}
			}else if("Y".equals(currentStatus)){
				//如果已经入库，则不更新状态
				//提示已入库
				result.setStatus(1);
				result.setMsg("产品已经完工！");
				return result;
			}
		}
		
		//Integer qcId = productDao.savePassProduct(barCode,pordId,passTime,ipAddress);
		if(qcId!=null){
			System.out.println("qcId:"+qcId);
			//入库
			//if("192.168.109.57".equals(ipAddress)){//||"192.168.109.59".equals(ipAddress)||"192.168.109.58".equals(ipAddress)||"192.168.109.55".equals(ipAddress)||"192.168.109.56".equals(ipAddress)||"192.168.109.57".equals(ipAddress)){
			if("192.168.109.57".equals(ipAddress)){
				//将processnum表中的stock_in字段值修改yes，标记已入库
				productDao.updateProcessStock(barCode,passTime);
				//更新生产数量辅助表入库数量
				// 扫描铭牌标签时为入库，更新任务单辅助表
				String countDate = SopUtil.dateFormat3(dateNow);
				Integer stockinCount = productDao
						.findProductStockinCount(countDate);
				if (stockinCount != null) {	
					productDao.updateProductStockinCount(countDate,
							stockinCount.intValue() + 1,passTime);
				} else {
					productDao.saveProductAssistCount(countDate, 0, 1,
							passTime, passTime);
				}

				// 下线，更新任务单辅助表
				Integer stockinPordCount = productDao
						.findPordStockinCount(pordId);
				if (stockinPordCount != null) {
					productDao.updatePordStockinCount(pordId,
							stockinPordCount.intValue() + 1,passTime);
					
					//判断任务单总数量是否已经完成，如果已经完成，将任务单状态改为待入库状态
					//获取任务单总数量
					Integer pordtotalNum = productDao.findPordTotalNumByPordId(pordId);
					if(pordtotalNum != null){
						//判断总数量与完成数量是否相等
						if((stockinPordCount.intValue() + 1) == pordtotalNum.intValue()){
							//将任务单状态置为待入库状态
							productDao.updatePordStockStatus(pordId);
						}
					}
				} else {
					productDao.savePordAssistCount(pordId, 0, 1, passTime,
							passTime);
				}
				
			}
			result.setStatus(0);
			result.setMsg("过站信息保存失败");
			//
				
		}else {
			result.setStatus(1);
			result.setMsg("过站信息保存失败");
		}
		return result;
	}


	@Override
	public NoteResult findNextProducts(String barCode) throws Exception {
		NoteResult result = new NoteResult();
		//查出所有的任务单类型
		//select * from mes_product_record group by product_code
		List<Map<String, Object>> productlist = productDao.findNextProducts(barCode);
		//prodId,productCode,pordnum
		if(productlist!=null){
			
			for(int i=0;i<productlist.size();i++){
				//根据生产任务单查询当前上线数量
				System.out.println(productlist.get(i).toString());
				Integer count = productDao.findNumByExistPord((String)productlist.get(i).get("PRODID"));
				System.out.println(count);
				int remainCount = Integer.parseInt((String)productlist.get(i).get("PORDNUM"))-count.intValue();
				
				System.out.println(remainCount);
				productlist.get(i).put("PORDNUM", remainCount);
				System.out.println(productlist.get(i).toString());
			}
			result.setStatus(0);
			result.setMsg("下一产品查找成功");
			result.setData(productlist);
		}else {
			result.setStatus(1);
			result.setMsg("下一产品查找失败");
		}
		return result;
	}

	/**
	 * 通过productbarcode找到对应的线号，
	 * 保存物料信息
	 */
	@Override
	public NoteResult saveMaterielMsg(String barCode, String ipAddress,
			String productBarCode,String procedureName) throws Exception {
		logger.error("保存物料信息：");
		logger.error("物料："+barCode+",地址："+ipAddress+",流程卡："+productBarCode+",工序："+procedureName);
		// TODO Auto-generated method stub
		//System.out.println("barCode:"+barCode+",ipAddress:"+ipAddress+",message:"+message+",passTime:"+passTime);
		//根据工序比较所在关键物料类型
		String materialType = "";
		if(procedureName.indexOf("装压缩机")!=-1){
		    System.out.println("1");
		    materialType = "压缩机";
		}else if(procedureName.indexOf("装吸附塔")!=-1){
		    System.out.println("2");
		    materialType = "吸附塔";
		}else if(procedureName.indexOf("装换向阀")!=-1){
		    System.out.println("3");
		    materialType = "换向阀";
		}else if(procedureName.indexOf("装电路板")!=-1){
			System.out.println("4");
			materialType = "电路板";
		}else if(procedureName.indexOf("铭牌")!=-1){
			System.out.println("5");
			materialType = "铭牌";
		}else{
			materialType = "其他";
		}
		
		NoteResult result = new NoteResult();
		Date dateNow = new Date();
		String passTime = SopUtil.dateFormat2(dateNow);
		//通过productBArcode找到material
		//String lineNum = productDao.findMaterialLinenum(productBarCode);
		//System.out.println("lineNum:"+lineNum);
		Integer qcId = null;
		//if(lineNum != null){
			qcId = productDao.saveMaterielMsg(barCode,productBarCode,ipAddress,passTime,materialType);
		//}
		
		if(qcId!=null){
			//如果ip地址未本体标签地址
			/*if("192.168.109.50".equals(ipAddress)){
				//将processnum表中的stock_in字段值修改yes，标记已入库
				productDao.updateProcessStock(productBarCode,passTime);
			}*/
			System.out.println("qcId:"+qcId);
			result.setStatus(0);
			result.setMsg("物料过站保存成功");
		}else {
			result.setStatus(1);
			result.setMsg("物料过站保存失败");
		}
		return result;
	}


	@Override
	@Transactional
	public NoteResult saveNewProductCode(String nodeName, String nodeParentId,
			String nodeParentPath, String menuLevel) throws Exception {
		NoteResult result = new NoteResult();
		Date dateNow = new Date();
		String createTime = SopUtil.dateFormat2(dateNow);
		//通过productBArcode找到material
		//String lineNum = productDao.findMaterialLinenum(productBarCode);
		//System.out.println("lineNum:"+lineNum);
		String nodePath = null;
		if(!"1".equals(menuLevel)){
			nodePath = nodeParentPath +"_"+nodeName;
		}else{
			nodePath = nodeParentPath;
		}
		System.out.println("nodePath:"+nodePath);
		ProductTree product = new ProductTree();
		product.setNode_name(nodeName);
		product.setParent_node_id(Integer.parseInt(nodeParentId));
		product.setNode_path(nodePath);
		product.setMenu_level(Short.parseShort(menuLevel));
		product.setPub_crtdate(createTime);
		
		Integer nodeId = null;
		//if(lineNum != null){
		nodeId = productDao.saveNewProductCode(product);
		//}
		if(nodeId!=null){
			//产品大类创建成功后维护yu_material表
			System.out.println("menuLevel:"+menuLevel);
			Integer productId = null;
			//如果是产品大类则不新增产品大类基础表
			if(!"1".equals(menuLevel)){
				/*System.out.println("报错");
				int i = 1/0;*/
				productId = productDao.saveNewMaterialNum(product.getNode_id(),nodeName); 
			}
			result.setStatus(0);
			result.setMsg("产品大类创建成功");
		}else {
			result.setStatus(1);
			result.setMsg("产品大类创建失败");
		}
		return result;
	}


	@Override
	@Transactional
	public NoteResult deleteProductCode(String nodeId) {
		NoteResult result = new NoteResult();
		Integer num = null;
		num = productDao.deleteProductCode(Integer.parseInt(nodeId));
		//}
		if(num!=null){
			//产品大类创建成功后维护yu_material表
			Integer productId = productDao.deleteMaterialNum(Integer.parseInt(nodeId)); 
			if(productId != null){
				result.setStatus(0);
				result.setMsg("产品大类删除成功");
			}
		}else {
			result.setStatus(1);
			result.setMsg("产品大类删除失败");
		}
		return result;
	}

	@Override
	@Transactional
	public NoteResult editNewProductCode(String nodeId, String nodeName) {
		NoteResult result = new NoteResult();
		Integer num = null;
		num = productDao.editNewProductCode(Integer.parseInt(nodeId),nodeName);
		//}
		if(num!=null){
			//产品大类创建成功后维护yu_material表
			Integer productId = productDao.editNewMaterialNum(Integer.parseInt(nodeId),nodeName); 
			if(productId != null){
				result.setStatus(0);
				result.setMsg("产品大类修改成功");
			}
		}else {
			result.setStatus(1);
			result.setMsg("产品大类修改失败");
		}
		return result;
	}


	@Override
	@Transactional
	public NoteResult saveDecfectiveMsg(String defectiveBarCode,
			String ipAddress, String productBarCode, String defectiveType)
			throws Exception {
		NoteResult result = new NoteResult();
		//查询当前IP地址对应的产品状态
		String ipStatus = productDao.findIpConfigByIpaddress(ipAddress);
		System.out.println("ipStatus:"+ipStatus);
		//工位号码
		//String stationNo = defectiveBarCode.substring(1);
		if(ipStatus == null){
			result.setStatus(1);
			result.setMsg("产品不良记录失败");
			return result;
		}
		Maintain maintain = new Maintain();
		maintain.setPub_crtdate(SopUtil.dateFormat2(new Date()));
		maintain.setYu_mtn_note("主观不良");
		maintain.setYu_mtn_phen(defectiveType);
		maintain.setOff_station(ipStatus);
		System.out.println(maintain.toString());
		//更新产品的状态
		productDao.updateProductStatus(productBarCode,"09");
		Integer num = null;
		num = productDao.saveDecfectiveMsg(maintain);
		
		//}
		if(num!=null){
			//maintain创建成功后维护yu_material表
			int mtn_id = maintain.getYu_mtn_id();
			Integer Id = productDao.saveDefectiveMaterial(productBarCode,mtn_id,maintain.getPub_crtdate()); 
			if(Id != null){
				result.setStatus(0);
				result.setMsg("主观不良记录成功");
			}
		}else {
			result.setStatus(1);
			result.setMsg("产品不良记录失败");
		}
		return result;
	}


	@Override
	public NoteResult findSubjectDefectiveSel() throws Exception {
		NoteResult result = new NoteResult();
		
		List<Map<String, Object>> subjectList = productDao.findSubjectDefectiveSel();
		
		if(subjectList!=null && subjectList.size()>0){
			result.setStatus(0);
			result.setMsg("主观不良查找成功");
			result.setData(subjectList);
		}else {
			result.setStatus(1);
			result.setMsg("主观不良查找失败");
		}
		return result;
	}


}
