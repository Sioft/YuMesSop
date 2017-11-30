package com.sop.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sop.dao.ProcessDesignDao;
import com.sop.dao.SopDao;
import com.sop.entity.ProcessDesignInfo;
import com.sop.entity.ProductTree;
import com.sop.entity.SopInfo;
import com.sop.entity.SopShow;
import com.sop.service.SopService;
import com.sop.util.NoteResult;
import com.sop.util.SopUtil;

@Service
public class SopServiceImpl implements SopService{
	private static Logger logger =Logger.getLogger(SopServiceImpl.class);

	@Resource
	private ProcessDesignDao pdgDao;
	
	@Resource
	private SopDao sopDao;

	@Override
	@Transactional
	public NoteResult saveSopInfo(Map<String, MultipartFile> files, String path,String nodePath,
			String productCode,String procedureNo,int versionNo,String validTime)throws Exception {
		NoteResult result = new NoteResult();
		List<String> list = new ArrayList<String>(); 
		
        //保证根目录存在
        File f = new File(path);  
        if (!f.exists()) {
			 f.mkdirs();
		}
        
    	//文件分组：根据产品类型划分文件夹
  		String[] nodePathArr = nodePath.split("_");
  		String pathStr = "";
  		//保证各级子文件夹都存在，没有在新建
  		for(int i=0;i<nodePathArr.length;i++){
  			pathStr = pathStr +"\\"+nodePathArr[i];
  			File fsub = new File(path+pathStr);  
  	        if (!fsub.exists()) {
  	        	fsub.mkdirs();
  			}
  			
		}
  		String finalPath = path+pathStr;
  		System.out.println("文件夹最终路径："+path+"**"+pathStr);
		     
        for(MultipartFile file :files.values()){
        	  // 获得原始文件名  
    	    String fileName = file.getOriginalFilename();  
    	    System.out.println("原始文件名:" + fileName);  
    	    // 系统命名  
    	    String sysNamePre = SopUtil.createSysImageName();
    	    String newFileName = sysNamePre + fileName;
    	    System.out.println("文件上传到文件夹位置："+finalPath);
    	    File targetFile = new File(finalPath, newFileName);  
             
            try {  
            	file.transferTo(targetFile);  
            } catch (Exception e) {  
                e.printStackTrace(); 
                logger.error(e);
            }  
           
            System.out.println("上传图片到:" + path +"**"+pathStr + "\\"+newFileName);
            logger.error("上传图片到:" + path +"**"+pathStr + "\\"+newFileName);
            //将上传的图片信息保存到数据库
            ProcessDesignInfo pdg = new ProcessDesignInfo();
            pdg.setMes_pdg_code("001");
            pdg.setMes_pdg_name("压缩机");
            pdg.setMes_pdg_imgname(fileName);
            pdg.setMes_pdg_sysname(newFileName);
            //保存路径时：使用反斜杠替换正斜杠
            String nodePathNew = pathStr.replace("\\", "/");
            pdg.setMes_pdg_path(nodePathNew);
            //执行sql插入操作：mes_processDesigninfo
            System.out.println("即将保存到节点路径："+pdg.getMes_pdg_path());
            pdg = saveProcessDesignInfo(pdg);
            
            //保存完图片之后保存投放信息
            //sopid,productCode,procedureNO,validTime
            //查询到productCode和procedureNo在工序表中对应的id
            //执行sql查询操作： sop_procedure
            Long procedureId = null;
            try {
            	procedureId = sopDao.findProcedureId(productCode,procedureNo);
			} catch (Exception e) {
				logger.error(e);
			}
            
            System.out.println("已知productCode+procedureNo确定定的procedureId:"+procedureId);
            
            if(procedureId != null){
            	//将信息保存到投放表
            	SopInfo sopInfo = new SopInfo();
            	sopInfo.setMes_pdg_id(pdg.getMes_pdg_id());
            	sopInfo.setProcedure_id(procedureId);
            	sopInfo.setMes_sop_validtime(validTime);
            	sopInfo.setMes_sop_version(versionNo);
            	
            	//执行sql插入操作： mes_sopInfo
            	Long sopId = null;
            	try {
            		sopId = sopDao.saveSop(sopInfo);
				} catch (Exception e) {
					logger.error(e);
				}
            	
            	System.out.println("工艺投放表保存成功，sopId:"+sopId);
            	logger.error("工艺投放表保存成功，sopId:"+sopId);
            	result.setStatus(0);
         		result.setMsg("插入成功");
            }else{
            	result.setStatus(1);
         		result.setMsg("找不到对应的工序表id");
         		logger.error("找不到对应的工序表");
            }
        }
       
		return result;
	}
	
	/**
	 * 执行sql插入操作：mes_processDesigninfo
	 * @param pdg
	 * @return
	 * @throws Exception
	 */
	public ProcessDesignInfo saveProcessDesignInfo(ProcessDesignInfo pdg) throws Exception {
		NoteResult result = new NoteResult();
		try {
			Long flag = pdgDao.save(pdg);
		} catch (Exception e) {
			logger.error(e);
		}
		
		Long pdgId = pdg.getMes_pdg_id();
		System.out.println("插入mes_processDesignInfo成功，返回mes_pdg_id:"+pdgId);
		return pdg;
	}

	/**
	 * 根据产品和工位找到sop
	 * 根据工位找到工序
	 * 然后调用写好的方法
	 */
	/*@Override
	public SopShow findSop(String barCode,
			String ipAddress) throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> pdglist = new ArrayList<Map<String, Object>>();
		SopShow sopShow = new SopShow();
		//根据barCode找到产品大类
		Long subBarcode = Long.valueOf(barCode.substring(1).trim());
		System.out.println(subBarcode);
		String productCode = sopDao.findProductCode(subBarcode);
		
		//找到工序：工位表
		if(productCode == null){
			logger.error("工位："+ipAddress+"未找到产品大类");
			return null;
		}
		//根据IP地址找到工序号
		Integer procedureNo = sopDao.findprocedureNoIp(ipAddress,productCode);
		if(procedureNo == null){
			logger.error("工位："+ipAddress+"未找到工序号码");
			return null;
		}
		System.out.println("查询到的产品大类:"+productCode);
		System.out.println("工序号:"+procedureNo);
		//找到还没生效的目前最大的版本号
		Integer versionNo = sopDao.findMaxVersion(productCode, procedureNo);
		if(versionNo == null){
			return null;
		}
		System.out.println("最大生效版本号:"+versionNo);
		sopShow.setVersionNo(versionNo);
		//查看是否有下一版本
		int nextVersion = findUnReleaseVersion(productCode,procedureNo);
		sopShow.setNextVersion(nextVersion);
		//查询这个版本
		if(versionNo != null){
			pdglist = sopDao.findExsitSop(productCode,procedureNo,versionNo);
		}else{
			return null;
		}
		
		if(null != pdglist && pdglist.size() >0 ){
			System.out.println("第一个pdglist中的名字："+pdglist.get(0).get("SYSNAME")+",路径:"+pdglist.get(0).get("PDGPATH"));
			sopShow.setPdglist(pdglist);
		}else{
			return null;
		}
		return sopShow;
	}*/

	@Override
	public List<ProductTree> findChild() throws Exception {
		List<ProductTree> productTreeList = sopDao.findChild();
		return productTreeList;
	}
	
	/**
	 * 根据productCode，procedureNo查询sop://获取sop的路径和文件名
	 * 1.取得所需版本
	 * 2.
	 */
	/*@Override
	public SopShow findExsitSop(String productCode, int procedureNo)
			throws Exception {
		SopShow sopShow = new SopShow();
		List<Map<String, Object>> pdglist = new ArrayList<Map<String, Object>>();
		System.out.print("参数productCode："+productCode+",procedureNo:"+procedureNo);
		//获取最大version
		//返回值：“SYSNAME”，“VALIDTIME”
		//找到最大版本号
		//Map<String, Object> versionMap = sopDao.findMaxVersion(productCode, procedureNo);
		//找到所有的没有被删除的版本号
		List<Integer> versionList = sopDao.findAllVersion(productCode, procedureNo);
		if(versionList != null && versionList.size()>0){
			System.out.println("找到所有版本号："+versionList);
			sopShow.setVersionlist(versionList);
			sopShow.setVersionNo(versionList.get(0));
		}else{
			return null;
		}
		//缺少判断条件
		//如果versionno为空
		//如果validtime为空
		if(versionMap == null){
			return null;
		}
		if(versionMap != null){
			System.out.println("最大版本号："+versionMap.get("VERSIONNO")+",最大版本生效时间:"+versionMap.get("VALIDTIME"));
			sopShow.setVersionNo(Integer.parseInt((versionMap.get("VERSIONNO")==null?"0":versionMap.get("VERSIONNO").toString())));
			sopShow.setValidTime(SopUtil.dateFormat2((Date)versionMap.get("VALIDTIME")));
		}
		
		//pdg列表：sysname+nodePath
		pdglist = sopDao.findExsitSop(productCode,procedureNo,sopShow.getVersionNo());
		System.out.println("pdglist:"+pdglist);
		if(null != pdglist && pdglist.size() >0 ){
			System.out.println("第一个pdglist中的名字："+pdglist.get(0).get("SYSNAME")+",路径:"+pdglist.get(0).get("PDGPATH"));
			sopShow.setPdglist(pdglist);
			//需不需要转换生效时间
		}else{
			return null;
		}
		
		//是否有未发布版本
		int nextVersion =  findUnReleaseVersion(productCode,procedureNo);
		
		sopShow.setNextVersion(nextVersion);
		
		return sopShow;
	}*/
	
	/**
	 * 找到是否有最新的还没有发布的版本
	 */
	public int findUnReleaseVersion(String productCode, String procedureNo){
		Integer nextversionNo = sopDao.findNextVersion(productCode, procedureNo);
		int nextVersion;
		if(nextversionNo!=null){
			//有新版本
			nextVersion = nextversionNo.intValue();
			
		}else{
			//没有新版本
			nextVersion = 0;
		}
		
		return nextVersion;
	}

	@Override
	public List<SopShow> findAllVersionSop(String productCode, String procedureNo,
			HttpSession session) throws Exception {
		List<SopInfo> soplist = sopDao.findAllSop(productCode,procedureNo);
		System.out.println(soplist);
		Map<String, Object> sopMap = new HashMap<String, Object>();
		if(soplist != null && soplist.size()>0){
			for(SopInfo sop : soplist){
				
			}
		}
		/*
		 * versionNo
		 * sysname
		 * nodepath
		 * validtime
		 * 
		 */
		/*
		SopShow sopShow = new SopShow();
		sopShow.getVersionNo("")
		sopMap.put("", value)*/
		return null;
	}

	/*@Override
	public SopShow findVersionSop(String productCode, int procedureNo,
			int versionNo) throws Exception {
		SopShow sopShow = new SopShow();
		List<Map<String, Object>> pdglist = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> pdglistNew = new ArrayList<Map<String, Object>>();
		pdglist = sopDao.findExsitSop(productCode,procedureNo,versionNo);
		if(null != pdglist && pdglist.size() >0 ){
			System.out.println("第一个pdglist中的名字："+(String)pdglist.get(0).get("SYSNAME")+",路径:"+(String)pdglist.get(0).get("PDGPATH")+",生效时间:"+SopUtil.dateFormat2((Date)pdglist.get(0).get("VALIDTIME")));
			//转换生效时间
			for(Map<String, Object> pdg : pdglist){
				pdg.put("VALIDTIME", SopUtil.dateFormat2((Date)pdg.get("VALIDTIME")));
				pdglistNew.add(pdg);
			}
			
			sopShow.setPdglist(pdglistNew);
		}
		return sopShow;
	}
	*/
	/**
	 * 删除一个版本的sop
	 */
	@Override
	public NoteResult deleteVersionSop(String productCode, String procedureNo,
			int versionNo) throws Exception {
		NoteResult result = new NoteResult();
		//找到procedureId
		Long procedureId = sopDao.findProcedureId(productCode,procedureNo);
		System.out.println("procedureId："+procedureId+",versionNo:"+versionNo);
		if(procedureId!=null){
			Integer count = sopDao.deleteVersionSop(procedureId,versionNo);
			System.out.println("删除版本："+versionNo+",共删除了"+count+"条数据");
			if(count != null){
				result.setStatus(0);
				result.setMsg("删除成功");
			}else{
				result.setStatus(1);
				result.setMsg("没有需要删除的sop");
			}
		}
		
		return result;
	}

	@Override
	public NoteResult findSopAllProce(String productCode) throws Exception {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> procelist = new ArrayList<Map<String, Object>>();
		// TODO Auto-generated method stub
		try {
			procelist = sopDao.findSopAllProce(productCode);
		} catch (Exception e) {
			logger.error(e); 
		}

		if(procelist!=null && procelist.size()>0){
			System.out.println("procelist:"+procelist.toString());
			//bug:如果上传一组sop然后删除，这时会查询所有status不为1的数据，空的工序不会显示
			//这里替换status==1的数据
			//如果status==1，则插入一组空数据
			/*for(Map<String,Object> proce : procelist){
				System.out.println("STATUSSSSSS:"+proce.get("STATUS"));
				if("1".equals(proce.get("STATUS"))){
					System.out.println("VALIDTIME:"+proce.get("VALIDTIME"));
					proce.put("VALIDTIME", "");
					System.out.println("VALIDTIME2:"+proce.get("VALIDTIME"));
				}
			}*/
			//{SYSNAME=20170505131825cat.jpg, VERSIONNO=1, PRODUCTCODE=ZF-1, 
			//NODEPATH=/ZF, VALIDTIME=2017-05-06 13:40:00, PROCEDURENO=2, STATUS=1}
			System.out.println("procelist{1}:"+procelist.get(0));
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(procelist);
			
		}else{
			result.setStatus(1);
			result.setMsg("没有找到记录");
		}
		return result;
	}

	@Override
	public NoteResult findMessage(String barCode, String ipAddress)
			throws Exception {
		NoteResult result = new NoteResult();
		Map<String, Object> message = new HashMap<String, Object>();
		// TODO Auto-generated method stub
		
		//根据barCode找到产品大类
		Long subBarcode = Long.valueOf(barCode.substring(1).trim());
		System.out.println(subBarcode);
		Map<String, Object> product = sopDao.findProductCodeAndName(subBarcode);
		
		
		if(product == null){
			logger.error("工位："+ipAddress+"未找到产品大类");
			return null;
		}
		if(product != null){
			System.out.println("产品大类:"+product.get("PRODUCTID")+",产品名称："+product.get("PRODUCTNAME"));
			String productCode = (String)product.get("PRODUCTID");
			String productName = (String)product.get("PRODUCTNAME");
			String pordId = (String)product.get("PORDID");
			//根据
			//根据IP地址找到工序号
			List<Map<String, Object>> procedureMap = sopDao.findprocedureNoUser(ipAddress,productCode);
			//找到工序：工位表
			
			if(procedureMap != null&&procedureMap.size()>0){
				System.out.println(procedureMap.toString());
				//message = sopDao.findMessage(subBarcode,Integer.parseInt(String.valueOf(procedureMap.get("PROCEDURENO"))));
				//1.产品大类代码、产品名称、任务单号
				//2.工序号、工序名称、用户名
				message.put("userId", procedureMap.get(0).get("USERID"));
				//工序列表
				List<Map<String,Object>> procedureGroup = new ArrayList<Map<String, Object>>();
				for(int i=0;i<procedureMap.size();i++){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("procedureNo", procedureMap.get(i).get("PROCEDURENO"));
					map.put("procedureName", procedureMap.get(i).get("PROCEDURENAME"));
					procedureGroup.add(map);
				}
				message.put("procedureGroup", procedureGroup);
				//return null;
			}else {
				logger.error("工位："+ipAddress+"未找到工序号码");
			}
			
			message.put("productCode", productCode);
			message.put("productName", productName);
			message.put("pordId", pordId);
			
		}

		if(message!=null){
			System.out.println("message:"+message.toString());
			
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(message);
			
		}else{
			result.setStatus(1);
			result.setMsg("没有找到记录");
			result.setData(message);
		}
		return result;
	}

	@Override
	public NoteResult findSopPlus(String procedureNos, String productCode)
			throws Exception {
		NoteResult result = new NoteResult();
		//增加工序
		String[] procedureGroup = procedureNos.split(",");
		System.out.println(procedureGroup);
		
		List<Map<String, Object>> pdglist = new ArrayList<Map<String, Object>>();
		SopShow sopShow = new SopShow();

		Integer versionNo = sopDao.findMaxVersion(productCode, procedureGroup[0]);
		if(versionNo == null){
			return null;
		}
		System.out.println("最大生效版本号:"+versionNo);
		sopShow.setVersionNo(versionNo);
		/*//查看是否有下一版本
		int nextVersion = findUnReleaseVersion(productCode,procedureNo);
		sopShow.setNextVersion(nextVersion);*/
		//查询这个版本
		if(versionNo != null){
			//遍历工序组里的sop
			/*for(int i=0;i<procedureGroup.length;i++){
				
			}*/
			pdglist = sopDao.findExsitSop(productCode,procedureGroup,versionNo);
		}else{
			return null;
		}
		
		if(null != pdglist && pdglist.size() >0 ){
			System.out.println("第一个pdglist中的名字："+pdglist.get(0).get("SYSNAME")+",路径:"+pdglist.get(0).get("PDGPATH"));
			sopShow.setPdglist(pdglist);
		}else{
			return null;
		}
		
		result.setStatus(0);
		result.setMsg("sop查询成功");
		result.setData(sopShow);
		return result;
	}

	@Override
	public SopShow findExsitSop(String productCode, String procedureNo)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SopShow findVersionSop(String productCode, String procedureNo,
			int versionNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SopShow findSop(String barCode, String ipAddress) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
