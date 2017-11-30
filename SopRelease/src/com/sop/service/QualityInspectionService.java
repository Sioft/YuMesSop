package com.sop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sop.entity.CheckValue;

@Service
public interface QualityInspectionService {
	public List<CheckValue> Get_QualityByBarCode(String barCode) throws Exception;
	
}
