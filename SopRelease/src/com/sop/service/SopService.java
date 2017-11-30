package com.sop.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sop.entity.ProductTree;
import com.sop.entity.SopShow;
import com.sop.util.NoteResult;


@Service
public interface SopService {
	//public NoteResult saveProcessDesign(String pdgCode,int pdgId) throws Exception;
	
	
	/**
	 * 根据产品大类和工序号找到所有版本sop
	 * 执行删除操作
	 * @param productCode
	 * @param procedureNo
	 * @return
	 * @throws Exception
	 */
	public NoteResult deleteVersionSop(String productCode,String procedureNo,int versionNo) throws Exception;
	
	/**
	 * 根据产品大类和工序号找到所有版本sop
	 * @param productCode
	 * @param procedureNo
	 * @return
	 * @throws Exception
	 */
	public List<SopShow> findAllVersionSop(String productCode,String procedureNo,HttpSession session) throws Exception;
	/**
	 * 上传文件，保存工艺信息，保存工艺投放信息
	 * @param files
	 * @param path
	 * @param nodePath
	 * @param productCode
	 * @param procedureNo
	 * @param versionNo
	 * @param validTime
	 * @return
	 * @throws Exception
	 */
	public NoteResult saveSopInfo(Map<String, MultipartFile> files, String path,String nodePath,
			String productCode,String procedureNo,int versionNo,String validTime) throws Exception;
	/**
	 * 根据产品大类和工序号找到sop
	 * @param productCode
	 * @param procedureNo
	 * @return
	 * @throws Exception
	 */
	public SopShow findExsitSop(String productCode,String procedureNo) throws Exception;
	/**
	 * 根据产品大类和工序号,版本号找到指定版本sop
	 * @param productCode
	 * @param procedureNo
	 * @return
	 * @throws Exception
	 */
	public SopShow findVersionSop(String productCode,String procedureNo,int versionNo) throws Exception;
	
	
	/**
	 *  根据产品大类和工位号找到sop
	 * @param stationCode
	 * @param productCode
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public SopShow findSop(String barCode,String ipAddress) throws Exception;
	/**
	 *  根据产品大类和工位号找到sop
	 * @param stationCode
	 * @param productCode
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public NoteResult findSopPlus(String procedureNos,String productCode) throws Exception;
	
	/**
	 *  根据产品大类和工位号找到sop
	 * @param stationCode
	 * @param productCode
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public NoteResult findMessage(String barCode,String ipAddress) throws Exception;
	/**
	 * ztree产品树形菜单：从productTree数据库中查找所有数据
	 * @return
	 * @throws Exception
	 */
	public List<ProductTree> findChild() throws Exception;
	
	/**
	 * 
	 * @param productCode
	 * @return
	 * @throws Exception
	 */
	public NoteResult findSopAllProce(String productCode) throws Exception;
	
}
