package com.sop.service;

import org.springframework.stereotype.Service;
import com.sop.util.NoteResult;

@Service
public interface SystemService {

	public NoteResult findDictType() throws Exception;
	public NoteResult saveDictType() throws Exception;
	public NoteResult updateDictType(String productCode) throws Exception;
	public NoteResult deleteDictType(String productCode) throws Exception;
	
	
	
	public NoteResult findDictData(int dtpId) throws Exception;
	public NoteResult saveDictData() throws Exception;
	public NoteResult updateDictData(String productCode) throws Exception;
	public NoteResult deleteDictData(String productCode) throws Exception;
}
