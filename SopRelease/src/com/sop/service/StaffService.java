package com.sop.service;

import org.springframework.stereotype.Service;

import com.sop.util.NoteResult;

@Service
public interface StaffService {
	public NoteResult findAllStaffs() throws Exception;
	//指定名称查找
	public NoteResult findByName(String stationCode) throws Exception;
}
