<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>检测表维护</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		var pageSize = 5;
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
			loadAllCheckInfo();
			//$("#addStation").click(addNewStationConfig);
		});
	
	</script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/liujin/js/checkInfo.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/liujin/css/basic.css"/> 
	
<%
//正常逻辑，获取工位的IP地址，根据用户的IP地址去数据库获取工位的名称

%>
</head>
	
<body>
	<h3>检测表管理</h3>
	<div id="checkform">
      <table id="checkTable" class="showTable">
    	<thead>
    		<tr>
    			<th>序号</th>
    			<th>产品大类代码</th>
    			<th>检测类型</th>
    			<th>检测上限</th>
    			<th>检测下限</th>
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
    <a href="updateCheckInfo.jsp"><input type="button" value="新增检测上下线" class="btn" style="font-size:24px;" id="addCheckInfo"/></a>
</body>
</html>
