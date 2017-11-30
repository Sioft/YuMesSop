package com.sop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sop.dao.SystemDao;
import com.sop.service.SystemService;
import com.sop.util.NoteResult;

@Service
public class SystemServiceImpl implements SystemService{

	@Resource
	private SystemDao sysDao;
	
	@Override
	public NoteResult findDictType() throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> typelist = new ArrayList<Map<String, Object>>();
		typelist = sysDao.findDictType();
		
		if(typelist!=null && typelist.size()>0){
			System.out.println("DictType列表"+typelist.toString());
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(typelist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}

	@Override
	public NoteResult saveDictType() throws Exception {
		NoteResult result = new NoteResult();
    	Integer count = sysDao.saveDictType();
    	System.out.println("DictType保存成功:"+count);
    	if(count != null){
	    	result.setStatus(0);
	 		result.setMsg("插入成功");
	    }else{
	    	result.setStatus(1);
	    }
    	return result;
	}

	@Override
	public NoteResult updateDictType(String productCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoteResult deleteDictType(String productCode) throws Exception {
		NoteResult result = new NoteResult();
		Integer count = sysDao.deleteDictType(productCode);
		System.out.println("删除DictType："+productCode+",共删除了"+count+"条数据");
		if(count != null){
			result.setStatus(0);
			result.setMsg("删除成功");
		}else{
			result.setStatus(1);
			result.setMsg("没有需要删除的sop");
		}		
		return result;
	}

	@Override
	public NoteResult findDictData(int dtpId) throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
		datalist = sysDao.findDictData(dtpId);
		
		if(datalist!=null && datalist.size()>0){
			System.out.println("DictData列表"+datalist.toString());
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(datalist);
		}else {
			result.setStatus(1);
			result.setMsg("没有查找到数据");
		}
		return result;
	}

	@Override
	public NoteResult saveDictData() throws Exception {
		NoteResult result = new NoteResult();
    	Integer count = sysDao.saveDictData();
    	System.out.println("DictData保存成功:"+count);
    	if(count != null){
	    	result.setStatus(0);
	 		result.setMsg("插入成功");
	    }else{
	    	result.setStatus(1);
	    }
    	return result;
	}

	@Override
	public NoteResult updateDictData(String productCode) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoteResult deleteDictData(String productCode) throws Exception {
		NoteResult result = new NoteResult();
		Integer count = sysDao.deleteDictData(productCode);
		System.out.println("删除DictData："+productCode+",共删除了"+count+"条数据");
		if(count != null){
			result.setStatus(0);
			result.setMsg("删除成功");
		}else{
			result.setStatus(1);
			result.setMsg("没有需要删除的sop");
		}		
		return result;
	}

}
