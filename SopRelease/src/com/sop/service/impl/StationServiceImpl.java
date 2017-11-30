package com.sop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sop.dao.StationDao;
import com.sop.entity.StationInfo;
import com.sop.service.StationService;
import com.sop.util.NoteResult;

@Service
public class StationServiceImpl implements StationService{

	@Resource
	private StationDao stationDao;
	
	@Override
	public NoteResult findAllStations() throws Exception {
		NoteResult result = new NoteResult();
		List<StationInfo> stationList = stationDao.findAll();
		System.out.println("工位列表"+stationList.toString());
		if(stationList!=null && stationList.size()>0){
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(stationList);
		}else {
			result.setStatus(1);
			result.setMsg("未发现工位");
		}
		return result;
	}

	@Override
	public NoteResult findStation(String stationCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
