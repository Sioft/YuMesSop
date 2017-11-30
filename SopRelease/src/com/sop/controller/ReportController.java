package com.sop.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sop.entity.DefectAnalysis;
import com.sop.service.ReportService;
import com.sop.util.NoteResult;
import com.sop.util.SopUtil;



@Controller
@RequestMapping("/report")
public class ReportController {

	private static Logger logger =Logger.getLogger(BasicController.class);
	@Resource
	private ReportService reportService;
	
	/**
	 * 不良品追溯报表 defective-product
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findDefectiveProductReport.do")  
	@ResponseBody
    public String findDefectiveProductReport(HttpServletRequest request) throws Exception { 
		int rows = Integer.valueOf(request.getParameter("rows")); //每页中显示的记录行数
		int page = Integer.valueOf(request.getParameter("page")); //当前的页码
		System.out.println(rows+","+page);
		String sord = request.getParameter("sord");//排序方式
		String sidx = request.getParameter("sidx");//排序列名
		System.out.println(sord+","+sidx);
		Boolean search =(request.getParameter("_search").equals("true"))?true:false;//是否用于查询请求
		
		//主要参数：
		String pordId = request.getParameter("pordId"); 
		System.out.println(pordId);
		String message = null;
		if(pordId != null && pordId != ""){
			message = reportService.findDefectiveProductReport(page,rows,sord,sidx,search,pordId);
		}else{
			message = reportService.findAllDefectiveProductReport(page, rows, sord, sidx, search);
		}
		//获取需要展示的不良品信息
		 
		System.out.println(message);
		return message;
    }
	
	/**
	 * 不良品追溯报表 defective-product
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findDefectiveProductAnalysis.do")  
	@ResponseBody
    public NoteResult findDefectiveProductAnalysis(HttpServletRequest request) throws Exception { 
		NoteResult result = new NoteResult();
		//参数：
		String startTime = request.getParameter("startTime");//起始时间
		String endTime = request.getParameter("endTime");//结束时间
		//参数验证：
		Date current = new Date();   //当前时间
		Date before = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(current);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -3);  //设置为前3月
		before = calendar.getTime();   //得到前3月的时间
		
		if(startTime == null || startTime == ""){
			//默认起始时间为2个月前
			startTime = SopUtil.dateFormat2(before);
		}else {
			startTime = SopUtil.dateFormat(startTime);
		}
		
		if(endTime == null || endTime == ""){
			//默认时间为当前时间
			endTime = SopUtil.dateFormat2(current);
		}else {
			endTime = SopUtil.dateFormat(endTime);
		}
		System.out.println("startTime："+startTime+",endTime："+endTime);
		result = reportService.findDefectiveProductAnalysis(startTime,endTime);
		
		//获取需要展示的不良品信息
		 
		System.out.println(result);
		return result;
    }
	
	@RequestMapping(value = "/findAllDefectiveProductReport.do")  
	@ResponseBody
    public String findAllDefectiveProductReport(HttpServletRequest request) throws Exception { 
		int rows = Integer.valueOf(request.getParameter("rows")); //每页中显示的记录行数
		int page = Integer.valueOf(request.getParameter("page")); //当前的页码
		System.out.println(rows+","+page);
		String sord = request.getParameter("sord");//排序方式
		String sidx = request.getParameter("sidx");//排序列名
		System.out.println(sord+","+sidx);
		Boolean search =(request.getParameter("_search").equals("true"))?true:false;//是否用于查询请求
		
		//主要参数：
		//String pordId = request.getParameter("pordId"); //当前的页码
		//System.out.println(pordId);
		//获取需要展示的不良品信息
		String message = reportService.findAllDefectiveProductReport(page,rows,sord,sidx,search);
		System.out.println(message);
		return message;
    }
	
	
	@RequestMapping(value = "/loadBoardMessage.do")  
	@ResponseBody
    public NoteResult loadBoardMessage() throws Exception { 
		NoteResult result = new NoteResult();
		//
		result = reportService.loadBoardMessage();
		
		//获取需要展示的不良品信息
		 
		//System.out.println(result);
		return result;
    }
	
	/**
	 * 任务单完成率
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadPordCompletRate.do")  
	@ResponseBody
    public NoteResult loadPordCompletRate() throws Exception { 
		NoteResult result = new NoteResult();
		//
		result = reportService.loadPordCompletRate();
		
		//获取需要展示的不良品信息
		 
		//System.out.println(result);
		return result;
    }
	
	/**
	 * 任务单完成率
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadWeekCompletCount.do")  
	@ResponseBody
    public NoteResult loadWeekCompletCount() throws Exception { 
		NoteResult result = new NoteResult();
		//
		result = reportService.loadWeekCompletCount();
		
		//获取需要展示的不良品信息
		 
		//System.out.println(result);
		return result;
    }
}
