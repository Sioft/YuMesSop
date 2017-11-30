<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>工序表维护</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/basic/js/procedure.js"></script>
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dictType.css"/> --%>
	<script type="text/javascript">
    	$(function(){
    		//	//页面载入后自动发送请求，加载当天日记
    			//loadPassages();
    	       //保存日记笔记本+按钮绑定处理
    	       //var passageno="";
    	       //loadDayPassages();
    	       $("#saveProcedure").click(saveProcedure);
    		});
    	</script>
    	<style type="text/css">
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
			
    	</style>
<%
//正常逻辑，获取工位的IP地址，根据用户的IP地址去数据库获取工位的名称

%>
</head>
	
<body>
	<%
		String procedureIdOld = (String)request.getParameter("procedureId");
		System.out.println("procedureIdOld:"+procedureIdOld);
		int procedureId = 0;
		if(procedureIdOld != null)
		{
			procedureId=Integer.parseInt(procedureIdOld);
		}
		String productCode = (String)request.getParameter("productCode");
		System.out.println("productCode:"+productCode);
		String productName = (String)request.getParameter("productName");
		System.out.println("productName:"+productName);
		String procedureNoOld = (String)request.getParameter("procedureNo");
		System.out.println("procedureNo:"+procedureNoOld);
		int procedureNo = 0;
		if(procedureNoOld != null)
		{
			procedureNo=Integer.parseInt(procedureNoOld);
		}
		String procedureCode = (String)request.getParameter("procedureCode");
		System.out.println("procedureCode:"+procedureCode);
	
		String procedureName = (String)request.getParameter("procedureName");
		System.out.println("procedureName:"+procedureName);
		
	
	%>
	<div id="station_new">
	<h3>工位配置表更新</h3>
		<input hidden="hidden" type="text" id="id" value="<%=procedureId!=0?procedureId:"" %>"/>
		产品大类代码：<input type="text" id="productCode" value="<%=productCode!=null?productCode:"" %>"/><br><br>
		
		产品名称：<input type="text" id="productName" value="<%=productName!=null?productName:"" %>"/><br><br>
		
		工序顺序号：<input type="text" id="procedureNo" value="<%=procedureNo!=0?procedureNo:"" %>"/><br><br>
		
		工序代码：<input type="text" id="procedureCode" value="<%=procedureCode!=null?procedureCode:"" %>"/><br><br>
				
		工序名称：<input type="text" id="procedureName" value="<%=procedureName!=null?procedureName:"" %>"/><br><br>
		
		<input type="button"  onclick="window.history.go(-1);" class="btn" value="返回"/>
		<input type="button" id="saveProcedure" class="btn" value="保存"/>
	</div>  
</body>
</html>
