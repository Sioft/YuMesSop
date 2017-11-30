package com.sop.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.sop.entity.ProductTree;

public class MenuUtil {
	                                                                                                                                                                                                                  
	public Map<String,Map<String,Object>> saveProductTree(                                                                                                                                                                                                             
			String name,short secondLevelMenu,int parentNodeId,
			short nodeType,short catalogLevel,String path){
		Map<String,Map<String,Object>> paramMap = new HashMap<String,Map<String,Object>>();  
		//paramMap = productDao.saveProductTree();
		
		
		
		
		
		return paramMap;                                                                                                                                                                                                                                    
	}                                                                                                                                                                                                                                          
	                                                                                                                                                                                                                                           
    /**
     * 修改产品大类菜单                                                                                                                                                                                                           
     */
/*	public Response updateCatalog(                                                                                                                                                                                                             
			@FormParam('id' ) Long id,                                                                                                                                                                                                             
			@FormParam('catalogName' ) String catalogName,                                                                                                                                                                                         
			@FormParam('nodeEntityId' ) Long nodeEntityId) {                                                                                                                                                                                       
		                                                                                                                                                                                                                                      
		                                                                                                                                                                                                        
            catalog = delegator.findOne('UserCatalog', UtilMisc.toMap('id', id), false);                                                                                                                                                     
            if(UtilValidate.isNotEmpty(catalog)){                                                                                                                                                                                            
            	BasicService.updateNode(delegator,id,catalogName,null,null);                                                                                                                                                                   
            	String nodeType = catalog.get('nodeType').toString();                                                                                                                                                                          
				                                                                                                                                                                                                                  
            } 
	}*/
    
	/**
	 * 删除产品大类菜单
	 * @return
	 */
	/*public Response deleteCatalog(                                                                                                                                                                                                             
			@FormParam('id' ) String id) {                                                                                                                                                                                                         
			catalog = delegator.findOne('UserCatalog',UtilMisc.toMap('id', Long.parseLong(id)),false);                                                                                                                                             
			if(UtilValidate.isNotEmpty(catalog)){                                                                                                                                                                                                  
					delegator.removeValue(catalog); 
			}
	}*/
				
	/**
	 * 遍历产品大类表，形成产品菜单树                                                                                                                                                                                                                                    
	 * @return
	 */
	/*@Path('/getChild')   */                                                                                                                                                                                                                      
	public static String getChildCategoryTree() { 
                                                                                                                                                                                                                                                                                                                                                                                                                                                    
		List<ProductTree> childOfCats = new ArrayList<ProductTree>();
		//为返回json字符串声明一个String
		String jsonStr = null;                                                                                                                                                                                                                   
		//查询结果集
		//childOfCats = productDao.findAll();
		
    	/*List<String> orderByList = new ArrayList<String>();                                                                                                                                                                            
    	orderByList.add('catalogLevel');                                                                                                                                                                                               
    	orderByList.add('id');                                                                                                                                                                                                         
    	childOfCats = delegator.findByAnd('UserCatalog', conditions , orderByList, false);  */                                                                                                                                           
         
		                                                                                                                                                                                                           
        Map<String,ProductTree> treeNodeMap = new HashMap<String,ProductTree>();                                                                                                                                                   
         List<ProductTree> listToJson = new ArrayList<ProductTree>();                                                                                                                                                               
         List<Map.Entry<String, ProductTree>> jsonList = null;                                                                                                                                                                      
            	                                                                                                                                                                                                                               
       //如果获取的子目录列表不为空                                                                                                                                                                        
         if(childOfCats != null){
        	 JSONArray json = null;
        	//遍历子目录                                                                                                                                                                                                                    
             for (ProductTree child : childOfCats ) {                                                                                                                                                                                                                                                                                                                                                                                         
             	treeNodeMap.put(String.valueOf(child.getNode_id()), child);                                                                                                                                                      
             } 
                                                                                                                                                                                                                                   
                                                                                                                                                                                                                                             
	        //让Map排序                                                                                                                                                                                                                 
	        jsonList = new ArrayList<Map.Entry<String, ProductTree>>(treeNodeMap.entrySet());                                                                                                                                      
	        Collections.sort(jsonList,new Comparator<Map.Entry<String, ProductTree>>(){                                                                                                                                            
	        	@Override
				public int compare(Map.Entry<String, ProductTree> mapping1,Map.Entry<String, ProductTree> mapping2){                                                                                                               
	        		return mapping1.getKey().compareTo(mapping2.getKey());                                                                                                                                                               
	        	}                                                                                                                                                                                                                      
	        }); 
	        //
	        for (Map.Entry<String, ProductTree> entry : jsonList) {                                                                                                                                                                
	             listToJson.add(entry.getValue());                                                                                                                                                                                                                                                                                                                                                                                                          
			}                                                                                                                                                                                                                                  
	         //转换为需要的josn字符串                                                                                                                                                                                                          
	        json = JSONArray.fromObject(listToJson);                                                                                                                                                                                 
            jsonStr = json.toString();
            System.out.println("ss:"+jsonStr);
                                                                                                                                                                                                                                       
         }   
         return jsonStr;
	}
}
