package com.sop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ReportDao {

	public List<Map<String, Object>> findDefectiveProductReport(@Param("pordId") String pordId) throws Exception;
	
	public List<Map<String, Object>> findAllDefectiveProductReport() throws Exception;
	
	public List<Map<String, Object>> findDefectiveProductAnalysis(@Param("startTime") String startTime,@Param("endTime") String endTime) throws Exception;
	
	//
	public Integer findAllProductCount(@Param("startTime") String startTime,@Param("endTime") String endTime) throws Exception;

	
	public Integer findProductCount(@Param("startTime") String startTime) throws Exception;
	public Integer findAllPordCount(@Param("startTime") String startTime) throws Exception;
	public Integer findOnPordCount(@Param("startTime") String startTime) throws Exception;
	public Integer findErrorCount(@Param("startTime") String startTime) throws Exception;
	
	//生产任务单完成率
	public List<Map<String, Object>> loadPordCompletRate() throws Exception;
	
	//最近一周的生产数量
	public Integer loadWeekCompletCount(@Param("day") int day) throws Exception;
	
}
