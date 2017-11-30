package com.sop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SystemDao {
	public List<Map<String, Object>> findDictType() throws Exception;
	public Integer saveDictType() throws Exception;
	public Integer updateDictType(String productCode) throws Exception;
	public Integer deleteDictType(String productCode) throws Exception;
	
	
	
	public List<Map<String, Object>> findDictData(@Param("dtpId")int dtpId) throws Exception;
	public Integer saveDictData() throws Exception;
	public Integer updateDictData(String productCode) throws Exception;
	public Integer deleteDictData(String productCode) throws Exception;
}
