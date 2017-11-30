package com.sop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sop.entity.CheckValue;
import com.sop.entity.Maintain;
import com.sop.entity.ProductInfo;
import com.sop.entity.ProductTree;

public interface ProductDao {
	public List<CheckValue> getCheckValueListByBarCode(@Param("barCode") String barCode);
	
	public List<ProductInfo> findAll();
	public Integer findMaterialNextSeq();
	public Integer savePassProduct(@Param("barCode") String barCode,@Param("userp") String userp,@Param("material") String material,@Param("pordId") String pordId,@Param("passTime") String passTime);
	public Integer savePassProductM(@Param("barCode") String barCode,@Param("pordId") String pordId,@Param("passTime") String passTime,@Param("ipAddress") String ipAddress,@Param("lineNum") String lineNum);
	public List<Map<String, Object>> findNextProducts (@Param("barCode") String barCode);
	//根据产品号码找到物料表线别组
	public String findMaterialLinenum(@Param("productBarCode") String productBarCode);
	//保存过站信息
	public Integer saveMaterielMsg(@Param("barCode") String barCode,@Param("mGroup") String mGroup,@Param("mPosition") String mPosition,@Param("passTime") String passTime,@Param("materialType") String materialType);
	
	public String findProcessNum(@Param("barCode") String barCode);
	
	public Integer savePassUser(@Param("userCode") String userCode,@Param("sopVersion") String sopVersion,@Param("uGroup") String uGroup,@Param("position") String position,@Param("passTime") String passTime);
	
	//
	public Integer savePassUser(@Param("uGroup") String uGroup,@Param("position") String position,@Param("passTime") String passTime);
	
	public Integer updateProcessStock(@Param("barCode") String barCode,@Param("passTime") String passTime);
	//根据生产任务单查询已上线的
	public Integer findNumByExistPord(@Param("pordId") String pordId);
	
	//插入生产数量辅助表
	public Integer saveProductAssistCount(@Param("countDate") String countDate,@Param("onlineCount") int onlineCount,@Param("stockinCount") int stockinCount,@Param("createDate") String createDate,@Param("updateDate") String updateDate );
	//根据当前日期查询当前上线数量
	public Integer findProductOnlineCount(@Param("countDate") String countDate);
	//根据当前日期查询当前入库数量
	public Integer findProductStockinCount(@Param("countDate") String countDate);
	//更新生产数量辅助表上线数量
	public Integer updateProductOnlineCount(@Param("countDate") String countDate,@Param("onlineCount") int onlineCount,@Param("updateDate") String updateDate);
	//更新生产数量辅助表入库数量
	public Integer updateProductStockinCount(@Param("countDate") String countDate,@Param("stockinCount") int stockinCount,@Param("updateDate") String updateDate);
	
	//插入任务单数量辅助表
	public Integer savePordAssistCount(@Param("pordId") String pordId,@Param("onlineCount") int onlineCount,@Param("stockinCount") int stockinCount,@Param("createDate") String createDate,@Param("updateDate") String updateDate );
	//根据任务单查询当前上线数量
	public Integer findPordOnlineCount(@Param("pordId") String pordId);
	//根据任务单查询当前入库数量
	public Integer findPordStockinCount(@Param("pordId") String pordId);
	//更新任务单数量辅助表上线数量
	public Integer updatePordOnlineCount(@Param("pordId") String pordId,@Param("onlineCount") int onlineCount,@Param("updateDate") String updateDate);
	//更新任务单数量辅助表入库数量
	public Integer updatePordStockinCount(@Param("pordId") String pordId,@Param("stockinCount") int stockinCount,@Param("updateDate") String updateDate);

	//创建产品大类
	//nodeName,nodeParentId,menuLevel,nodePath,createTime
	public Integer saveNewProductCode(ProductTree product);
	//维护产品大类基础表
	public Integer saveNewMaterialNum(@Param("productId") int productId,@Param("nodeName") String nodeName);
	//删除产品大类
	public Integer deleteProductCode(@Param("nodeId") int nodeId);
	//删除产品大类基础表
	public Integer deleteMaterialNum(@Param("nodeId") int nodeId);
	//修改产品大类名称
	public Integer editNewProductCode(@Param("nodeId") int nodeId,@Param("nodeName") String nodeName);
	//修改产品大类名称
	public Integer editNewMaterialNum(@Param("nodeId") int nodeId,@Param("nodeName") String nodeName);
	
	//产品状态更新
	//根据IP查找所属状态类型
	public String findIpConfigByIpaddress(@Param("ipaddress") String ipaddress);
	//查找当前流程卡号所处的状态
	public String findCurrentProductStatusByBarCode(@Param("barCode") String barCode);
	//更新流程卡的产品状态
	public Integer updateProductStatus(@Param("barCode") String barCode,@Param("ipStatus") String ipStatus);
	//根据流程卡查询维修记录
	public String findRepairMsgBybarCode(@Param("barCode") String barCode);
	
	//保存主观不良信息
	public Integer saveDecfectiveMsg(Maintain maintain);
	//保存主观不良信息(material)
	public Integer saveDefectiveMaterial(@Param("barCode") String barCode,@Param("repairType") int repairType,@Param("createDate") String createDate);
	//初始化主观不良下拉框
	public List<Map<String, Object>> findSubjectDefectiveSel();
	
	//根据任务单号找到任务单总数量
	public Integer findPordTotalNumByPordId(@Param("pordId") String pordId);
	//更新任务单状态
	public Integer updatePordStockStatus(@Param("pordId") String pordId);
	//更新任务单上线状态
	public Integer updatePordMcodeStatus(@Param("pordId") String pordId);
}