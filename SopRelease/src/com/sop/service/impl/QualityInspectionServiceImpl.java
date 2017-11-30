package com.sop.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sop.dao.ProductDao;
import com.sop.entity.CheckValue;
import com.sop.service.*; 

@Service
public class QualityInspectionServiceImpl implements QualityInspectionService {
	@Resource
	private ProductDao productDao;
	
	/**
	 * 根据barocode返回检测信息
	 * daniel
	 * **/
	@Override
	public List<CheckValue> Get_QualityByBarCode(String barCode) throws Exception {
		List<CheckValue> cv_list=new ArrayList<CheckValue>();
		cv_list=productDao.getCheckValueListByBarCode(barCode);
		return cv_list;
	}
}
