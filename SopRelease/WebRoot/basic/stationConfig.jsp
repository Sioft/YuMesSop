<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>工位配置表维护</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		var pageSize = 20;
		var resultdata = "";
		$(function(){
			$("#firstPage").click(function(){//首页
				firstPage(resultdata,pageSize);
			});
			$("#prePage").click(function(){//上一页
				prePage(resultdata,pageSize);
			});
			$("#nextPage").click(function(){//下一页
				nextPage(resultdata,pageSize);
			});
			loadAllStationConfig();
			//$("#addStation").click(addNewStationConfig);
		});
	
	</script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/basic/js/stationConfig.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/basic/css/basic.css"/> 
	
<%
//正常逻辑，获取工位的IP地址，根据用户的IP地址去数据库获取工位的名称

%>
</head>
	
<body>
	<h3>工位配置表管理</h3>
	<div id="stationInform">
      <table id="stationTable" class="showTable">
    	<thead>
    		<tr>
    			<th>序号</th>
    			<th>线别号</th>
    			<th width="10%">机编码</th>
    			<th>机名称</th>
    			<th>工序序号</th>
    			<th>员工id</th>
    			<th>ip地址</th>
    			<th>mac地址</th>
    			<th>创建时间</th>
    			<th>操作</th>
    		</tr>
    	</thead>
    	<tbody>
      </table>
      <br/>
	    <div class="pageInfo">
		   	<span>共计</span><span id="totalRecords"></span><span>条数据</span>
		   	<input type="button" id="firstPage" class="btn" value = "首页">
			 	<input type="button" id="prePage"  class="btn" value="上一页">
			 	<span>第</span><span id="currentPage"></span><span>页/共</span>
			 	<span id="totalPage"></span><span>页</span>
			 	<input type="button" id="nextPage" class="btn" value="下一页">
		  </div>
		  <br>
      <!-- <span style="float:right;"></span> -->
    </div>
    <a href="updateStation.jsp"><input type="button" value="新增配置" class="btn" style="font-size:24px;" id="addStation"/></a>
</body>
</html>
