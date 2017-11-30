package com.sop.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sop.entity.Procedure;
import com.sop.entity.StationConfig;
import com.sop.util.NoteResult;

@Service
public interface BasicService {
	
	public NoteResult findIPStation(String ipAddress) throws Exception;
	public NoteResult findIpSopStation(String ipAddress) throws Exception;
	public NoteResult findProductCodeSelect() throws Exception;
	public NoteResult findProcedureSel(String productId) throws Exception;
	//操作员工的下拉框
	public NoteResult findEmployeeorSel() throws Exception;
	public NoteResult findProcedureByIpAndProductCode(String productId,String ipAddress) throws Exception;
	
	
	
	public NoteResult findAllStationConfig() throws Exception;
	public NoteResult saveStationConfig(StationConfig config) throws Exception;
	public NoteResult updateStationConfig(StationConfig config) throws Exception;
	public NoteResult saveOrUpdateConfig(StationConfig config,String procedureNo) throws Exception;
	
	public NoteResult deleteStationConfig(int scfId) throws Exception;
	
	
	
	public NoteResult findAllProcedure() throws Exception;
	public NoteResult saveProcedure(Procedure procedure) throws Exception;
	public NoteResult updateProcedure(Procedure procedure) throws Exception;
	public NoteResult deleteProcedure(int procedureId) throws Exception;
	
	public NoteResult findAllDeviceInfo() throws Exception;
	//grid 纯json数组
	public List<Map<String, Object>> findAllDeviceInfoJosn() throws Exception;
	
	public NoteResult saveDeviceInfo(Integer dvepjtId,String dvetype,String dvedtype,
			String dvevle) throws Exception;
	public NoteResult updateDeviceInfo(Integer dveid,Integer dvepjtid,String dvetype,String dvedtype,
			String dvevle) throws Exception;
	public NoteResult deleteDeviceInfo(int dveId) throws Exception;
	
	public NoteResult findAllCheckInfo() throws Exception;
	public NoteResult saveCheckInfo(String chkpjtid,String chktype,double chkuvl,
			double chkdvl) throws Exception;
	public NoteResult updateCheckInfo(long chkId,String chkpjtid,String chktype,double chkuvl,
			double chkdvl) throws Exception;
	public NoteResult deleteCheckInfo(int chkId) throws Exception;
}
