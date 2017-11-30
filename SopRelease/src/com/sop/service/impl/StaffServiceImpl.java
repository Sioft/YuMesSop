package com.sop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sop.dao.StaffDao;
import com.sop.entity.StaffInfo;
import com.sop.service.StaffService;
import com.sop.util.NoteResult;

@Service
public class StaffServiceImpl implements StaffService{

	@Resource
	private StaffDao staffDao;
	
	@Override
	public NoteResult findAllStaffs() throws Exception {
		NoteResult result = new NoteResult();
		List<StaffInfo> staffList = staffDao.findAll();
		System.out.println("员工列表"+staffList.toString());
		if(staffList!=null && staffList.size()>0){
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(staffList);
		}else {
			result.setStatus(1);
			result.setMsg("未发现工位");
		}
		return result;
	}

	@Override
	public NoteResult findByName(String stationCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
