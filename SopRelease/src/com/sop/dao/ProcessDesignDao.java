package com.sop.dao;

import java.util.List;

import com.sop.entity.ProcessDesignInfo;

public interface ProcessDesignDao {
	public ProcessDesignInfo findByName(String diaryno,String userno);
	public Long save(ProcessDesignInfo pdg);
	public void update(ProcessDesignInfo pdn);
	public List<ProcessDesignInfo> findAll(String userno);
}
