<%@page import="java.io.InputStreamReader"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>工位配置表维护</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/basic/css/sop_configure.css"/>
	<!-- 下拉框 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/basic/css/bootstrap.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/basic/css/multiple-select.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/basic/js/stationConfig.js"></script>
    <script src="${pageContext.request.contextPath}/basic/js/multiple-select.js"></script>
    <!-- 下拉框 -->
    
	<script type="text/javascript">
    	$(function(){
    		/* 多选下拉框 */
    		$('#procedureSel').change(function() {
                console.log($(this).val());
            }).multipleSelect({
                //width: '100%'
            	height: '45%'
            }); 
    		var procedureNos = '<%=(String)request.getParameter("procedureNo")!=null?(String)request.getParameter("procedureNo"):" "%>' ;
    		var procedureCodes = '<%=(String)request.getParameter("productCode")!=null?(String)request.getParameter("productCode"):" "%>';
    		var userIds = '<%=(String)request.getParameter("userId")!=null?(String)request.getParameter("userId"):" "%>';
    		//加载操作员工select框
    		loadEmployeeorSelect(userIds);
    		//加载产品大类select框
			loadProductCodeSelect(procedureNos,procedureCodes,userIds);
    		
    		//	//加载ip信息，加载原来工序
    		//loadIPStation();
    		
    	   
    	       $("#saveStation").click(saveStationConfig);
    	      // $("#productCodeSel").bind("onchange",changeProcedure());
    	       //$("#saveStation").click(saveStationConfig);
    	       $("#productCodeSel").change(function(){
    	    	   
    	    	   //alert($(this).children('option:selected').val()); 
    	    	   //获得选中的产品大类ID
    	    	   var productId=$(this).children('option:selected').val();//这就是selected的值 
    	    	 	if(productId==null||productId==""){
    	    	 		return false;
    	    	 	}
    	    	   
    	    	 	loadProcedureSelect(productId,"");
    	    	   //根据IP查找指定记录
    	    	});
    		});
    	</script>
    	<!-- <style type="text/css">
    		#station_new {
 				position:absolute;
 				background-color:#eee;
 				left:60px;
 				top:20px;
 				/* border: 1px #B7B7B8 solid; */
 				width:630px;
 				height:520px;
 				padding:30px;		
 			}
 			
 			.btn{
				background:none;
				border:1px solid #6fbce8;
				cursor:pointer;
				color:#333;
				font-size:18px;
				float:right;
			}
			.btn:hover{
				color:#6fbce8;
			}
			
    	</style> -->
<%
//正常逻辑，获取工位的IP地址，根据用户的IP地址去数据库获取工位的名称

%>
</head>
	
<body>
	<%
		/* public String getMACAddress(String ip){
			String str = "";  

        	String macAddress = "";
        	Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  

            InputStreamReader ir = new InputStreamReader(p.getInputStream());  

            LineNumberReader input = new LineNumberReader(ir);  

            

            while((str = input.readLine()) != null){

                if (str.indexOf("MAC") > 1) {

                    //使用substring函数截出mac地址

                    //macAddress = str.substring(str.indexOf("MAC") + 9, str.length());  

                    break;  

                }  

            }  
            return macAddress;  
		}*/
          

		String scfIdOld = (String)request.getParameter("scfId");
		System.out.println("scfId:"+scfIdOld);
		int scfId = 0;
		if(scfIdOld != null)
		{
			scfId=Integer.parseInt(scfIdOld);
		}
		String lineType = (String)request.getParameter("lineType");
		System.out.println("lineType:"+lineType);
		String functionCode = (String)request.getParameter("functionCode");
		System.out.println("functionCode:"+functionCode);
		String functionName = (String)request.getParameter("functionName");
		System.out.println("functionName:"+functionName);
		/* String procedureNoOld = (String)request.getParameter("procedureNo");
		System.out.println("procedureNo:"+procedureNoOld);
		int procedureNo = 0;
		if(procedureNoOld != null)
		{
			procedureNo=Integer.parseInt(procedureNoOld);
		} */
		/* String userIdOld = (String)request.getParameter("userId");
		System.out.println("userId:"+userIdOld);
		int userId = 0;
		if(userIdOld != null)
		{
			userId=Integer.parseInt(userIdOld);
		} */
		
		
		//String ipAddress = (String)request.getParameter("ipAddress");
		//System.out.println("ipAddress:"+ipAddress);
		String macAddress = (String)request.getParameter("macAddress");
		System.out.println("macAddress:"+macAddress);
		  
		String createTime = (String)request.getParameter("createTime");
		System.out.println("createTime:"+createTime);

		//String ipAddress = request.getLocalAddr();
		
		//暂时有用
		String ipAddress = request.getRemoteAddr();
		System.out.println("IP:"+ipAddress);
		
		//取得最后一个.之后的字符串
		//String functionCode = "";
		if(ipAddress != null){
			functionCode = ipAddress.substring(ipAddress.lastIndexOf('.')+1);
		}
		System.out.println("functionCode:"+functionCode);
		
		String procedureNos = (String)request.getParameter("procedureNo");
		System.out.println("procedureNos:"+procedureNos);
		
		String productCodes = (String)request.getParameter("productCode");
		System.out.println("productCodes:"+productCodes);
	%>
	
	<%-- <label id="ipAddress" hidden="hidden"><%=ipAddress %></label>  --%>

	<%-- <div id="station_new">
	<h3>工位配置表更新</h3>
		<label id="ipAddress" hidden="hidden"><%=ipAddress %></label> 
		<input hidden="hidden" type="text" id="id" value="<%=scfId!=0?scfId:"" %>"/>
		线别号：<input type="text" id="lineType" value="<%=lineType!=null?lineType:"壹号线" %>"/><br><br>
		
		机编码：<input type="text" id="functionCode" value="<%=functionCode!=null?functionCode:"" %>"/><br><br>
		
		机名称：<input type="text" id="functionName" value="<%=functionName!=null?functionName:"" %>"/><br><br>
		
		工序顺序号：<input type="text" id="procedureNo" value="<%=procedureNo!=0?procedureNo:"" %>"/><br><br>
				
		员工ID：<input type="text" id="userId" value='<%=userId!=0?userId:"" %>'/><br><br>
		
		IP地址：<input type="text" id="ipAddress" value='<%=ipAddress!=null?ipAddress:"" %>'/><br><br>
		
		MAC地址：<input type="text" id="macAddress" value="<%=macAddress!=null?macAddress:"" %>"/><br><br>
		<!-- <input type="button"  onclick="window.history.go(-1);" class="btn" value="返回"/> -->
		<input type="button" id="saveStation" class="btn" value="保存"/>
	</div>   --%>
<div class="container">
    <div class="content">
    <div class="title">工 位 配 置 表 更 新</div>
    	<%-- <label id="ipAddress" hidden="hidden"><%=ipAddress %></label>  --%>
		<input hidden="hidden" type="text" id="id" value="<%=scfId!=0?scfId:"" %>"/>
		<input hidden="hidden" type="text" id="procedureNos" value="<%=procedureNos!=null?procedureNos:"" %>"/>
		<input hidden="hidden" type="text" id="productCodes" value="<%=productCodes!=null?productCodes:"" %>"/>
		<div class="firstrow">
            <div class="fst_lable">级<span class="lable_text">别</span><span class="lable_text">号 </span>：</div>
            <div class="fst_inp"><input type="text" class="inp" id="lineType" value="<%=lineType!=null?lineType:"壹号线" %>"/></div>
        </div>
        <div class="otherrow">
            <div class="oth_lable">机<span class="lable_text">编</span><span class="lable_text">号 </span>：</div>
            <div class="oth_inp"><input type="text" class="inp" id="functionCode" value="<%=functionCode!=null?functionCode:"" %>"/></div>
        </div>
        <div class="otherrow">
            <div class="oth_lable">产<span class="lable_text">品</span><span class="lable_text">类 </span>：</div>
            <%-- <div class="oth_inp"><input type="text" class="inp" id="functionName" value="<%=functionName!=null?functionName:"" %>"/></div> --%>
        	<div class="oth_inp">
        		<select class="inp" id="productCodeSel">
        		</select>
        	</div>
        </div>
        <%-- <div class="otherrow">
            <div class="oth_lable">机<span class="lable_text">名</span><span class="lable_text">称 </span>：</div>
            <div class="oth_inp"><input type="text" class="inp" id="functionName" value="<%=functionName!=null?functionName:"" %>"/></div>
        </div> --%>
        <div class="otherrow">
            <div class="oth_lable">工序顺序号 ：</div>
            <div class="oth_inp">
            	<!-- <select class="inp" multiple='multiple' id="procedureSel">
        		</select> -->
        		<select id="procedureSel" style="width: 400px;height: 48px;border:1px solid #b3b3b3;border-radius: 2px;font-size: 36px;color: #333333;padding-left: 15px;" multiple="multiple">
		        </select>
            <%-- <input type="text" class="inp" id="procedureNo" value="<%=procedureNo!=0?procedureNo:"" %>"/> --%>
            </div>
        </div>
         <div class="otherrow">
            <div class="oth_lable">员<span class="lable_text1">工</span><span class="lable_text1">ID</span> ：</div>
            <%-- <div class="oth_inp"><input type="text" class="inp" id="functionName" value="<%=functionName!=null?functionName:"" %>"/></div> --%>
        	<div class="oth_inp">
        		<select class="inp" id="userId">
        		</select>
        	</div>
        </div>
        <!-- <div class="otherrow">
            <div class="oth_lable">员<span class="lable_text1">工</span><span class="lable_text1">ID</span> ：</div>
            <div class="oth_inp"><input type="text" class="inp" id="userId" value=''/></div>
        </div> -->
        <div class="otherrow">
            <div class="oth_lable">IP<span class="lable_text">地</span><span class="lable_text">址 </span>：</div>
            <div class="oth_inp"><input type="text" class="inp" id="ipAddress" value='<%=ipAddress!=null?ipAddress:"" %>'/></div>
        </div>
        <%-- <div class="otherrow">
            <div class="oth_lable">MAC<span class="lable_text2">地</span><span class="lable_text2">址 </span>：</div>
            <div class="oth_inp"><input type="text" class="inp" id="macAddress" value="<%=macAddress!=null?macAddress:"" %>"/></div>
        </div> --%>
        <div class="btn"><input type="button" class="button" id="saveStation" value="保&nbsp;&nbsp;&nbsp;&nbsp;存"></div>
		 </div>
</div>
</body>
</html>
