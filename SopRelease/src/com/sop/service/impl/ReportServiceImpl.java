package com.sop.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.sop.dao.ReportDao;
import com.sop.entity.DefectAnalysis;
import com.sop.entity.Series;
import com.sop.service.ReportService;
import com.sop.util.Constant;
import com.sop.util.NoteResult;
import com.sop.util.SopUtil;

@Service
public class ReportServiceImpl implements ReportService{
	@Resource
	private ReportDao reportDao;

	@Override
	public String findDefectiveProductReport(int page, int rows, String sord,
			String sidx, Boolean search,String pordId) {
		//最终返回的json对象
		JSONObject jjson = new JSONObject(); 
		List<Map<String, Object>> defectivelist = new ArrayList<Map<String, Object>>();
		try {
			defectivelist = reportDao.findDefectiveProductReport(pordId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//构建不良品数值数组
		List<List<String>> arrList = new ArrayList<List<String>>();
		if(defectivelist != null && defectivelist.size()>0){
			System.out.println(defectivelist.toString());
			for(int i=0;i<defectivelist.size();i++){
				List<String> arr = new ArrayList<String>();
				arr.add(String.valueOf(defectivelist.get(i).get("PORD_ID")));
				arr.add(String.valueOf(defectivelist.get(i).get("NAME")));
				arr.add(String.valueOf(defectivelist.get(i).get("CHVCID")));
				//SN..预留
				arr.add(String.valueOf(defectivelist.get(i).get("SYS_DDT_NAME")));
				arr.add(String.valueOf(defectivelist.get(i).get("CHVUVL")));
				arr.add(String.valueOf(defectivelist.get(i).get("CHVDVL")));
				arr.add(String.valueOf(defectivelist.get(i).get("CHVL")));
				arr.add(String.valueOf(defectivelist.get(i).get("CHVRES")));
				arrList.add(arr);
			}
			//分页部分
			int total=0; 
			total=(arrList.size()%rows==0)?(arrList.size()/rows):((arrList.size()/rows)+1);
			int j = 0;
			int m = (page-1)*rows;
			int n = (page-1)*rows+rows;
			JSONArray jArray = new JSONArray();
			for (j=m; j<arrList.size()&&j<n; j++) { 
			jArray.add(JSONArray.fromObject(arrList.get(j))); 
			}
			//检索结果及分页信息封装 返回
			jjson.accumulate("page", page);
			jjson.accumulate("total", total);
			jjson.accumulate("records", arrList.size());
			jjson.accumulate("rows", jArray);
			System.out.println(jjson.toString());
		}
		
	
		//升降序SQL语句转换
		/*if (sidx!=null&&!"".equals(sidx)) {
		System.out.println(sidx);
		keys += " ORDER BY " + sidx;
		System.out.println("sord="+sord);
		if (!sord.equals("asc")) {
		keys += " DESC ";
		}*/
		//allList = Factory.getDemoDAOInstance().doSearch(keys);
		
		//response.getWriter().write(jjson.toString());
		
		return jjson.toString();
	}

	@Override
	public String findAllDefectiveProductReport(int page, int rows,
			String sord, String sidx, Boolean search) {
		//最终返回的json对象
				JSONObject jjson = new JSONObject(); 
				List<Map<String, Object>> defectivelist = new ArrayList<Map<String, Object>>();
				try {
					defectivelist = reportDao.findAllDefectiveProductReport();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//构建不良品数值数组
				List<List<String>> arrList = new ArrayList<List<String>>();
				if(defectivelist != null && defectivelist.size()>0){
					System.out.println(defectivelist.toString());
					for(int i=0;i<defectivelist.size();i++){
						List<String> arr = new ArrayList<String>();
						arr.add(String.valueOf(defectivelist.get(i).get("PORD_ID")));
						arr.add(String.valueOf(defectivelist.get(i).get("NAME")));
						arr.add(String.valueOf(defectivelist.get(i).get("CHVCID")));
						//SN..预留
						arr.add(String.valueOf(defectivelist.get(i).get("SYS_DDT_NAME")));
						arr.add(String.valueOf(defectivelist.get(i).get("CHVUVL")));
						arr.add(String.valueOf(defectivelist.get(i).get("CHVDVL")));
						arr.add(String.valueOf(defectivelist.get(i).get("CHVL")));
						arr.add(String.valueOf(defectivelist.get(i).get("CHVRES")));
						arrList.add(arr);
					}
					//分页部分
					int total=0; 
					total=(arrList.size()%rows==0)?(arrList.size()/rows):((arrList.size()/rows)+1);
					int j = 0;
					int m = (page-1)*rows;
					int n = (page-1)*rows+rows;
					JSONArray jArray = new JSONArray();
					for (j=m; j<arrList.size()&&j<n; j++) { 
						jArray.add(JSONArray.fromObject(arrList.get(j))); 
					}
					//检索结果及分页信息封装 返回
					jjson.accumulate("page", page);
					jjson.accumulate("total", total);
					jjson.accumulate("records", arrList.size());
					jjson.accumulate("rows", jArray);
					System.out.println(jjson.toString());
				}
				
			
				//升降序SQL语句转换
				/*if (sidx!=null&&!"".equals(sidx)) {
				System.out.println(sidx);
				keys += " ORDER BY " + sidx;
				System.out.println("sord="+sord);
				if (!sord.equals("asc")) {
				keys += " DESC ";
				}*/
				//allList = Factory.getDemoDAOInstance().doSearch(keys);
				
				//response.getWriter().write(jjson.toString());
				
				return jjson.toString();
	}

	@Override
	public NoteResult findDefectiveProductAnalysis(String startTime,String endTime) {
		NoteResult result = new NoteResult();
		List<Map<String, Object>> defectivelist = new ArrayList<Map<String, Object>>();
		List<Series> seriesList = new ArrayList<Series>();
		Integer productCount = null;
		try {
			//defectivelist = reportDao.findDefectiveProductAnalysis(startTime,endTime);
			//找到时间段内所有的产品的数量
			defectivelist = reportDao.findDefectiveProductAnalysis(startTime,endTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			productCount = reportDao.findAllProductCount(startTime,endTime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//构建不良品数值数组
		Series checkType1 = new Series();//压缩机
		int count1 = 0;
		Series checkType2 = new Series();//吸附塔
		int count2 = 0;
		Series checkType3 = new Series();//换气阀
		int count3 = 0;
		Series checkType4 = new Series();//氧浓度
		int count4 = 0;
		Series checkType5 = new Series();//正常
		//List<List<String>> arrList = new ArrayList<List<String>>();
		if(defectivelist != null && defectivelist.size()>0){
			System.out.println(defectivelist.toString());
			List<String> arr = new ArrayList<String>();
			for(int i=0;i<defectivelist.size();i++){
				if("device_type_001".equals(String.valueOf(defectivelist.get(i).get("CHVSID")))){
					//压缩机
					count1++;
				}else if("device_type_002".equals(String.valueOf(defectivelist.get(i).get("CHVSID")))){
					//吸附塔
					count2++;
				}else if("device_type_003".equals(String.valueOf(defectivelist.get(i).get("CHVSID")))){
					//换向阀
					count3++;
				}else if("device_type_004".equals(String.valueOf(defectivelist.get(i).get("CHVSID")))){
					//氧浓度
					count4++;
				}
				
				/*arr.add(String.valueOf(defectivelist.get(i).get("CHVDVL")));
				arr.add(String.valueOf(defectivelist.get(i).get("CHVRES")));
				arrList.add(arr);*/
			}
		}
		int countAll = 0;
		if(productCount!=null && productCount.intValue() != 0){
			countAll = productCount.intValue();
			checkType1.setPercent((double)count1/countAll);
			checkType1.setName("压缩机");
			checkType2.setPercent((double)count2/countAll);
			checkType2.setName("吸附塔");
			checkType3.setPercent((double)count3/countAll);
			checkType3.setName("换向阀");
			checkType4.setPercent((double)count4/countAll);
			checkType4.setName("氧浓度");
			checkType5.setPercent((double)(countAll-count1-count2-count3-count4)/countAll);
			checkType5.setName("正常");
			seriesList.add(checkType1);
			seriesList.add(checkType2);
			seriesList.add(checkType3);
			seriesList.add(checkType4);
			seriesList.add(checkType5);
			result.setStatus(0);
			result.setMsg("查找成功");
			result.setData(seriesList);
		}else{
			result.setStatus(1);
			result.setMsg("没有数据");
		}
		return result;
	}

	@Override
	public NoteResult loadBoardMessage() {
		// TODO Auto-generated method stub
		//定义今天凌晨时间
		String todayStart = null;
		Calendar currentDate = new GregorianCalendar();   
		  
		currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		currentDate.set(Calendar.MINUTE, 0);  
		currentDate.set(Calendar.SECOND, 0);  
		System.out.println((Date)currentDate.getTime().clone()+"sss");
		
		try {
			todayStart = SopUtil.dateFormat2((Date)currentDate.getTime().clone());
			System.out.println(todayStart);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//当天实时产量
		//yu_processnum表中入库且更新时间为当天早上8点钟的数量
		Integer currentCount = null;
		try {
			currentCount = reportDao.findProductCount(todayStart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//待排产数量：查询所有未完成任务单的总数量
		Integer pordCount = null;
		try {
			pordCount = reportDao.findAllPordCount(todayStart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//订单累计完成数量:所有未完成任务单的上线数量
		Integer onCount = null;
		try {
			onCount = reportDao.findOnPordCount(todayStart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//实时不良品数量
		Integer errorCount = null;
		try {
			errorCount = reportDao.findErrorCount(todayStart);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//全部累计上线数量
		//值等同于实时产量
		int allOnCount = currentCount.intValue();
		Map<String,Object> countMap = new HashMap<String,Object>();
		countMap.put("errorCount", errorCount);
		countMap.put("onCount", onCount);
		countMap.put("pordCount", pordCount);
		countMap.put("currentCount", currentCount);
		System.out.println(countMap.toString());
		NoteResult result = new NoteResult();
		if(countMap != null){
			result.setData(countMap);
			result.setStatus(0);
			result.setMsg("查找成功");
		}
		return result;
	}

	@Override
	public NoteResult loadPordCompletRate() {
		//定义今天凌晨时间
		String todayStart = null;
		Calendar currentDate = new GregorianCalendar();   
		  
		currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		currentDate.set(Calendar.MINUTE, 0);  
		currentDate.set(Calendar.SECOND, 0);  
		System.out.println((Date)currentDate.getTime().clone()+"sss");
		
		try {
			todayStart = SopUtil.dateFormat2((Date)currentDate.getTime().clone());
			System.out.println(todayStart);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//获取生产任务单完成率
		List<Map<String, Object>> ratelist = new ArrayList<Map<String, Object>>();
		//ratelist onCount = null;
		try {
			ratelist = reportDao.loadPordCompletRate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NoteResult result = new NoteResult();
		DecimalFormat df = new DecimalFormat("######0.00");  
		if(ratelist != null && ratelist.size()>0){
			for(int i=0;i<ratelist.size();i++){
				int count = 0;
				int num = 0;
				if(ratelist.get(i).get("COUNT")!=null&&ratelist.get(i).get("NUM")!=null){
					count = Integer.parseInt(String.valueOf(ratelist.get(i).get("COUNT")));
					num = Integer.parseInt(String.valueOf(ratelist.get(i).get("NUM")));
					ratelist.get(i).put("RATE", df.format((double)count/num));
				}else{
					ratelist.get(i).put("RATE", 0);
				}
				
			}
			result.setData(ratelist);
			result.setStatus(0);
			result.setMsg("查找成功");
		}
		return result;
	}

	@Override
	public NoteResult loadWeekCompletCount() throws ParseException {
		NoteResult result = new NoteResult();
		System.out.println("每周生产数量：");
		Calendar calendar = Calendar.getInstance();
		//当前时间
		Date dateFlag = calendar.getTime();
		String monStart = SopUtil.dateFormat2(dateFlag);
		//System.out.println(monStart);
		Map<String, Object> map = new HashMap<String, Object>();
		//声明两个数组
		//存放数值
		int value[] = new int [7]; 
		//存放日期
		String date[] = new String [7]; 
		int countSum = 0;
		try {
			//轮询最近7天的生产数量
			for(int i=0;i<7;i++){
			   Date today = calendar.getTime();  
		       SimpleDateFormat format = new SimpleDateFormat("MM-dd");  
		       String day = format.format(today);  
			   calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);  
			   Integer count = reportDao.loadWeekCompletCount(i);
			   value[6-i] = count-countSum;
			   countSum = count;
			   date[6-i] = day;
				//System.out.println(day);
			}
			//Integer ratelist = reportDao.loadWeekCompletCount(4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("value", value);
		map.put("date", date);
		
		result.setStatus(0);
		result.setData(map);
		

		
		

		return result;
	}
}
