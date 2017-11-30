package com.sop.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sop.entity.DefectAnalysis;
import com.sop.util.NoteResult;

@Service
public interface ReportService {
	//public String findAllDeviceInfoJosn() throws Exception;
	public String findDefectiveProductReport(int page,int rows,String sord,String sidx,Boolean search,String pordId);
	
	public NoteResult findDefectiveProductAnalysis(String startTime,String endTime);
	
	public String findAllDefectiveProductReport(int page,int rows,String sord,String sidx,Boolean search);
	public NoteResult loadBoardMessage();
	public NoteResult loadPordCompletRate();
	public NoteResult loadWeekCompletCount() throws Exception;;

}
