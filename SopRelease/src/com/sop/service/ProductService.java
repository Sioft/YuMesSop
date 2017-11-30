package com.sop.service;

import org.springframework.stereotype.Service;

import com.sop.util.NoteResult;

@Service
public interface ProductService {
	public NoteResult findAllProducts() throws Exception;
	
	//指定名称查找
	public NoteResult savePassProduct(String barCode,String pordId,String ipAddress,String userCode,String productCode,String sopVersion) throws Exception;
	
	public NoteResult findNextProducts(String barCode) throws Exception;
	
	//
	public NoteResult saveMaterielMsg(String barCode,String ipAddress,String productBarCode,String procedureName) throws Exception;
	public NoteResult saveNewProductCode(String nodeName,String nodeParentId,String nodeParentPath,String menuLevel) throws Exception;
	//删除产品大类
	public NoteResult deleteProductCode(String nodeId);
	//修改产品大类
	public NoteResult editNewProductCode(String nodeId,String nodeName);
	
	//保存主观不良
	public NoteResult saveDecfectiveMsg(String defectiveBarCode,String ipAddress,String productBarCode,String defectiveType) throws Exception;
	//初始化主观不良下拉框
	public NoteResult findSubjectDefectiveSel() throws Exception;
}
