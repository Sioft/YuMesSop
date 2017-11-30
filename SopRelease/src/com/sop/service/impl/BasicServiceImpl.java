package com.sop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sop.dao.BasicDao;
import com.sop.dao.SopDao;
import com.sop.entity.Procedure;
import com.sop.entity.StationConfig;
import com.sop.service.BasicService;
import com.sop.util.NoteResult;
import com.sop.util.SopUtil;

@Service
public class BasicServiceImpl implements BasicService{


	@Resource
	private BasicDao basicDao;
	@Resource
	private SopDao sopDao;
	
	@Override
	public NoteResult findAllStationConfig() throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> configlist = new ArrayList<Map<String, Object>>();
		configlist = basicDao.findAllStationConfig();
		
		if(configlist!=null && configlist.size()>0){
			System.out.println("configlist列表"+configlist.toString());
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(configlist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}
	
	//findIPStation
	@Override
	public NoteResult findIPStation(String ipAddress) throws Exception {
		NoteResult result = new NoteResult();
		Map<String, Object> configlist = new HashMap<String, Object>();
		configlist = basicDao.findIPStation(ipAddress);
		
		if(configlist!=null){
			System.out.println("configlist列表"+configlist.toString());
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(configlist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}
	
	@Override
	public NoteResult findIpSopStation(String ipAddress) throws Exception {
		NoteResult result = new NoteResult();
		Map<String, Object> configlist = new HashMap<String, Object>();
		configlist = basicDao.findIpSopStation(ipAddress);
		
		if(configlist!=null){
			System.out.println("configlist列表"+configlist.toString());
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(configlist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}
	
	@Override
	public NoteResult saveStationConfig(StationConfig config) throws Exception {
		System.out.println(config.toString());
		NoteResult result = new NoteResult();
		Integer scfId = basicDao.saveStationConfig(config);
		if(scfId!=null){
			result.setStatus(0);
			result.setMsg("配置成功");
		}else {
			result.setStatus(1);
			result.setMsg("配置失败");
		}
		return result;
	}

	@Override
	public NoteResult updateStationConfig(StationConfig config) throws Exception {
		System.out.println(config.toString());
		NoteResult result = new NoteResult();
		Integer count = basicDao.updateStationConfig(config);
		if(count!=null){
			result.setStatus(0);
			result.setMsg("配置修改成功");
		}else {
			result.setStatus(1);
			result.setMsg("配置修改失败");
		}
		return result;
	}

	@Override
	public NoteResult saveOrUpdateConfig(StationConfig config,String procedureNo) throws Exception {
		System.out.println(config.toString());
		NoteResult result = new NoteResult();
		//查询记录是否存在id
		Map<String, Object> station = basicDao.findExistStationConfigId(config.getIp_address(),config.getProduct_id());
		
		Integer count = null;
		//如果不存在，新增
		if(station != null){
			System.out.println("station:"+station.toString());
			config.setMes_scf_id(Integer.parseInt(String.valueOf(station.get("SCFID"))));
			//判断工位是否被锁定
			String flagLock = String.valueOf(station.get("FLAGLOCK"));
			if("是".equals(flagLock)){
				//锁定，前台不能配置
				result.setStatus(1);
				result.setMsg("该工位不允许修改工序配置！");
				return result;
			}else {
				//没有锁定，可以配置
				count = basicDao.updateStationConfig(config);
			
				//修改得是工序组
				//先删除工序表，更新删除标记
				count = basicDao.deleteProcedureGroup(config.getMes_scf_id());
				//增加工序
				String[] procedureNos = procedureNo.split(",");
				System.out.println(procedureNos);
				for(int i=0;i<procedureNos.length;i++){
					count = basicDao.saveProcedureGroup(config.getMes_scf_id(),procedureNos[i],config.getPub_crtdate());
				}
				
			}
			
		}else{
			count = basicDao.saveStationConfig(config);
			if(config.getMes_scf_id() != 0){
				//插入工序号
				//增加工序
				String[] procedureNos = procedureNo.split(",");
				System.out.println(procedureNos);
				for(int i=0;i<procedureNos.length;i++){
					count = basicDao.saveProcedureGroup(config.getMes_scf_id(),procedureNos[i],config.getPub_crtdate());
				}
			}
		}
		if(count!=null){
			result.setStatus(0);
			result.setMsg("配置修改成功");
		}else {
			result.setStatus(1);
			result.setMsg("配置修改失败");
		}
		return result;
	}
	@Override
	public NoteResult deleteStationConfig(int scfId) throws Exception {
		NoteResult result = new NoteResult();
		Integer count = basicDao.deleteStationConfig(scfId);
		if(count!=null){
			result.setStatus(0);
			result.setMsg("配置删除成功");
		}else {
			result.setStatus(1);
			result.setMsg("配置删除失败");
		}
		return result;
	}

	@Override
	public NoteResult findAllProcedure() throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> procelist = new ArrayList<Map<String, Object>>();
		procelist = basicDao.findAllProcedure();
		
		if(procelist!=null && procelist.size()>0){
			System.out.println("procelist列表"+procelist.toString());
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(procelist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}

	@Override
	public NoteResult saveProcedure(Procedure procedure) throws Exception {
		System.out.println(procedure.toString());
		NoteResult result = new NoteResult();
		Integer procedureId = basicDao.saveProcedure(procedure);
		if(procedureId!=null){
			result.setStatus(0);
			result.setMsg("工序增加成功");
		}else {
			result.setStatus(1);
			result.setMsg("增加失败");
		}
		return result;
	}

	@Override
	public NoteResult updateProcedure(Procedure procedure) throws Exception {
		System.out.println(procedure.toString());
		NoteResult result = new NoteResult();
		Integer count = basicDao.updateProcedure(procedure);
		if(count!=null){
			result.setStatus(0);
			result.setMsg("工序修改成功");
		}else {
			result.setStatus(1);
			result.setMsg("工序修改失败");
		}
		return result;
	}

	@Override
	public NoteResult deleteProcedure(int procedureId) throws Exception {
		NoteResult result = new NoteResult();
		Integer count = basicDao.deleteProcedure(procedureId);
		if(count!=null){
			result.setStatus(0);
			result.setMsg("工序删除成功");
		}else {
			result.setStatus(1);
			result.setMsg("工序删除失败");
		}
		return result;
	}
	@Override
	public List<Map<String, Object>> findAllDeviceInfoJosn() throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> devicelist = new ArrayList<Map<String, Object>>();
		devicelist = basicDao.findAllDeviceInfo();
		
		if(devicelist!=null && devicelist.size()>0){
			System.out.println("devicelist列表"+devicelist.toString());
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(devicelist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return devicelist;
	}
	@Override
	public NoteResult findAllDeviceInfo() throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> devicelist = new ArrayList<Map<String, Object>>();
		devicelist = basicDao.findAllDeviceInfo();
		
		if(devicelist!=null && devicelist.size()>0){
			System.out.println("devicelist列表"+devicelist.toString());
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(devicelist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}

	@Override
	public NoteResult saveDeviceInfo(Integer dvepjtid, String dvetype,
			String dvedtype, String dvevle) throws Exception {
		NoteResult result = new NoteResult();
		Date dateNow = new Date();
		String dateForm = SopUtil.dateFormat2(dateNow);
		Integer dveId = basicDao.saveDeviceInfo(dvepjtid, dvetype, dvedtype, dvevle, dateForm);
		if(dveId!=null){
			result.setStatus(0);
			result.setMsg("设备增加成功");
		}else {
			result.setStatus(1);
			result.setMsg("增加失败");
		}
		return result;
	}

	@Override
	public NoteResult updateDeviceInfo(Integer dveid, Integer dvepjtid,
			String dvetype, String dvedtype, String dvevle) throws Exception {
		// TODO Auto-generated method stub
		NoteResult result = new NoteResult();
		Integer count = basicDao.updateDeviceInfo(dveid, dvepjtid, dvetype, dvedtype, dvevle);
		if(count!=null){
			result.setStatus(0);
			result.setMsg("设备修改成功");
		}else {
			result.setStatus(1);
			result.setMsg("工序修改失败");
		}
		return result;
	}

	@Override
	public NoteResult deleteDeviceInfo(int dveId) throws Exception {
		NoteResult result = new NoteResult();
		Integer count = basicDao.deleteDeviceInfo(dveId);
		if(count!=null){
			result.setStatus(0);
			result.setMsg("工艺删除成功");
		}else {
			result.setStatus(1);
			result.setMsg("工艺删除失败");
		}
		return result;
	}

	@Override
	public NoteResult findAllCheckInfo() throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> checklist = new ArrayList<Map<String, Object>>();
		checklist = basicDao.findAllCheckInfo();
		
		if(checklist!=null && checklist.size()>0){
			System.out.println("devicelist列表"+checklist.toString());
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(checklist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}

	@Override
	public NoteResult deleteCheckInfo(int chkId) throws Exception {
		NoteResult result = new NoteResult();
		Integer count = basicDao.deleteCheckInfo(chkId);
		if(count!=null){
			result.setStatus(0);
			result.setMsg("删除成功");
		}else {
			result.setStatus(1);
			result.setMsg("删除失败");
		}
		return result;
	}

	@Override
	public NoteResult saveCheckInfo(String chkpjtid, String chktype,
			double chkuvl, double chkdvl) throws Exception {
		//System.out.println(check.toString());
		NoteResult result = new NoteResult();
		Date dateNow = new Date();
		String dateForm = SopUtil.dateFormat2(dateNow);
		Integer chkId = basicDao.saveCheckInfo(chkpjtid, chktype, chkuvl, chkdvl, dateForm);
		if(chkId!=null){
			result.setStatus(0);
			result.setMsg("工序增加成功");
		}else {
			result.setStatus(1);
			result.setMsg("增加失败");
		}
		return result;
	}

	@Override
	public NoteResult updateCheckInfo(long chkid, String chkpjtid,
			String chktype, double chkuvl, double chkdvl) throws Exception {
		//System.out.println(check.toString());
		NoteResult result = new NoteResult();
		Integer count = basicDao.updateCheckInfo(chkid, chkpjtid, chktype, chkuvl, chkdvl);
		if(count!=null){
			result.setStatus(0);
			result.setMsg("修改成功");
		}else {
			result.setStatus(1);
			result.setMsg("修改失败");
		}
		return result;
	}

	@Override
	public NoteResult findProductCodeSelect() throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> configlist = new ArrayList<Map<String, Object>>();
		configlist = basicDao.findProductCodeSelect();
		
		if(configlist!=null){
			System.out.println("configlist列表"+configlist.toString());
			result.setStatus(0);
			result.setMsg("产品大类下拉框查找成功");
			result.setData(configlist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}

	@Override
	public NoteResult findProcedureSel(String productId) throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> configlist = new ArrayList<Map<String, Object>>();
		configlist = basicDao.findProcedureSel(productId);
		
		if(configlist!=null){
			System.out.println("configlist列表"+configlist.toString());
			result.setStatus(0);
			result.setMsg("产品大类下拉框查找成功");
			result.setData(configlist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}

	@Override
	public NoteResult findProcedureByIpAndProductCode(String productId,
			String ipAddress) throws Exception {
		NoteResult result = new NoteResult();
		//查询已经注册好得多个工序
		//List<String> configlist  = basicDao.findProcedureByIpAndProductCode(ipAddress, productId);
		Map<String, Object> message = new HashMap<String, Object>();
		List<Map<String, Object>> procedureMap = sopDao.findprocedureNoUser(ipAddress,productId);
		System.out.println("工序组："+procedureMap.toString());
		if(procedureMap!=null && procedureMap.size()>0){
			//查询操作员工名称
			//Map<String, Object> configMap = sopDao.findprocedureNoUser(ipAddress, productId);
			//List<Map<String, Object>> configlist  = basicDao.findProcedureByIpAndProductCode(ipAddress, productId);
			message.put("userId", procedureMap.get(0).get("USERID"));
			//工序列表
			//List<Map<String,Object>> procedureGroup = new ArrayList<Map<String, Object>>();
			String[] productGroups = new String[procedureMap.size()];
			for(int i=0;i<procedureMap.size();i++){
				/*Map<String, Object> map = new HashMap<String, Object>();
				map.put("procedureNo", procedureMap.get(i).get("PROCEDURENO"));
				map.put("procedureName", procedureMap.get(i).get("PROCEDURENAME"));
				procedureGroup.add(map);*/
				productGroups[i] = String.valueOf(procedureMap.get(i).get("PROCEDURENO"));
			}
			message.put("procedureGroup", productGroups);
			System.out.println("message列表"+message.toString());
			result.setStatus(0);
			result.setMsg("工序表查找成功");
			result.setData(message);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}

	@Override
	public NoteResult findEmployeeorSel() throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> userlist = new ArrayList<Map<String, Object>>();
		userlist = basicDao.findEmployeeorSel();
		
		if(userlist!=null){
			System.out.println("userlist列表"+userlist.toString());
			result.setStatus(0);
			result.setMsg("产品大类下拉框查找成功");
			result.setData(userlist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}

	

	

	
	

}
