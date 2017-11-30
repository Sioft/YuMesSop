package com.sop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sop.entity.ProcessDesignInfo;
import com.sop.entity.ProductTree;
import com.sop.entity.SopInfo;

public interface SopDao {
	public void save(ProcessDesignInfo pdg);
	public List<ProcessDesignInfo> findSop(String stationCode,
			String productCode);
	public List<ProductTree> findChild();
	//查询工序表的id
	//public int findProcedureId(Map<String,Object> map);//mao传参
	public Long findProcedureId(@Param("productCode") String productCode, @Param("procedureNo") String procedureNo);//注解传参
	//工艺投放表
	public Long saveSop(SopInfo sopinfo);
	//根据版本号，工序号，产品大类编码找到sop
	public List<Map<String, Object>> findExsitSop(@Param("productCode") String productCode, @Param("procedureGroup") String[] procedureGroup,@Param("versionNo") int versionNo);
	//<!-- 找到已生效的最大版本号,和最大时间 -->
	public Integer findMaxVersion(@Param("productCode") String productCode, @Param("procedureNo") String procedureNo);//注解传参
	//<!-- 找到还未生效的版本号 -->
	public Integer findNextVersion(@Param("productCode") String productCode, @Param("procedureNo") String procedureNo);//注解传参
	//根据工位找到工序序号
	public Integer findProcedureNo(String stationCode);

	//找到所有版本的sop
	public List<SopInfo> findAllSop(@Param("productCode") String productCode, @Param("procedureNo") String procedureNo);
	//删除指定版本的sop
	public Integer deleteVersionSop(@Param("procedureId")Long procedureId,@Param("versionNo")int versionNo);
	//找到所有的没有被删除的版本号
	public List<Integer> findAllVersion(@Param("productCode") String productCode, @Param("procedureNo") String procedureNo);
	
	//产品大类下找到所有的工序号
	public List<Map<String, Object>> findSopAllProce (@Param("productCode") String productCode);
	//sop展示界面右侧产品信息展示栏
	public Map<String, Object> findMessage (@Param("barCode") Long barCode,@Param("procedureNo") String procedureNo);
	//findMessage
	
	//根据barCode找到产品大类
	public String findProductCode(@Param("barCode") Long barCode);
	public Map<String, Object> findProductCodeAndName(@Param("barCode") Long barCode);
	//
	
	public Integer findprocedureNoIp(@Param("ipAddress") String ipAddress,@Param("productCode") String productCode);
	public List<Map<String, Object>> findprocedureNoUser(@Param("ipAddress") String ipAddress,@Param("productCode") String productCode);
	
}
