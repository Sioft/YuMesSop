/**
 * 创建信息：
 * 文件描述：显示品质检测数据报告处理类；
 * 创建人：daniel.liu
 * 创建时间：2017年7月15日15:40:05
 * 版本修订描述：1.创建文件，支持页面功能查询；
 * 			
 * */

package com.sop.controller;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sop.entity.CheckValue;
import com.sop.service.QualityInspectionService;


@Controller
@RequestMapping("/quality")
public class QualityInspection {
	
	private static Logger logger =Logger.getLogger(QualityInspection.class);
	
	@Resource
	private QualityInspectionService qualityService;
	
	
	/**
	 *  根据barcode 查询检测信息
	 *  dnaiel.liu
	 */
	@RequestMapping(value = "/getQualityByBarCode.do")  
	@ResponseBody
    public List<CheckValue> Get_QualityByBarCode(String barCode,HttpServletRequest request,HttpSession session) throws Exception {
		List<CheckValue> cvlist=new ArrayList<CheckValue>();
		try {
			cvlist= qualityService.Get_QualityByBarCode(barCode);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return cvlist;	  
    } 
	
	
}
