package com.sop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sop.entity.Procedure;
import com.sop.entity.StationConfig;

public interface BasicDao {
	public Map<String, Object> findIPStation(@Param("ipAddress")String ipAddress) throws Exception;
	public List<Map<String, Object>> findProductCodeSelect() throws Exception;
	public List<Map<String, Object>> findProcedureSel(@Param("productId")String productId) throws Exception;
	//查找操作员工下拉框
	public List<Map<String, Object>> findEmployeeorSel() throws Exception;
	
	public Map<String, Object> findIpSopStation(@Param("ipAddress")String ipAddress) throws Exception;
	public List<Map<String, Object>> findAllStationConfig() throws Exception;
	public Integer saveStationConfig(StationConfig config) throws Exception;
	public Integer updateStationConfig(StationConfig config) throws Exception;
	public Map<String, Object> findExistStationConfigId(@Param("ipAddress")String ipAddress,@Param("productId")int productId) throws Exception;
	
	public Integer deleteStationConfig(@Param("scfId")int scfId) throws Exception;
	
	
	
	public List<Map<String, Object>> findAllProcedure() throws Exception;
	public Integer saveProcedure(Procedure procedure) throws Exception;
	public Integer updateProcedure(Procedure procedure) throws Exception;
	public Integer deleteProcedure(@Param("procedureId")int procedureId) throws Exception;
	
	public List<Map<String, Object>> findAllDeviceInfo() throws Exception;
	public Integer saveDeviceInfo(@Param("dvepjtid")int dvepjtid, @Param("dvetype") String dvetype,
			@Param("dvedtype")String dvedtype,@Param("dvevle") String dvevle,@Param("time") String time) throws Exception;
	public Integer updateDeviceInfo(@Param("dveid")long dveid,@Param("dvepjtid")int dvepjtid, @Param("dvetype") String dvetype,
			@Param("dvedtype")String dvedtype,@Param("dvevle") String dvevle) throws Exception;
	public Integer deleteDeviceInfo(@Param("dveId")int dveId) throws Exception;
	
	public List<Map<String, Object>> findAllCheckInfo() throws Exception;
	public Integer saveCheckInfo(@Param("chkpjtid")String chkpjtid, @Param("chktype") String chktype,
			@Param("chkuvl")double chkuvl,@Param("chkdvl") double chkdvl,@Param("time") String time) throws Exception;
	public Integer updateCheckInfo(@Param("chkid")long chkid,@Param("chkpjtid")String chkpjtid, @Param("chktype") String chktype,
			@Param("chkuvl")double chkuvl,@Param("chkdvl") double chkdvl) throws Exception;
	public Integer deleteCheckInfo(@Param("chkId")int chkId) throws Exception;
	
	
	//查询工序组（产品大类+工位）
	public List<String> findProcedureByIpAndProductCode(@Param("ipAddress")String ipAddress,@Param("productId")String productId) throws Exception;
	//更新工序组删除标记
	public Integer deleteProcedureGroup(@Param("procedureGroup")int procedureGroup) throws Exception;
	//插入工序组标记
	public Integer saveProcedureGroup(@Param("procedureGroup")int procedureGroup,@Param("procedureNo")String procedureNo,@Param("time")String time) throws Exception;
}
