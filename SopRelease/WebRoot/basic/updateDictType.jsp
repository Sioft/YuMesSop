<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>工位配置表维护</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/basic/stationConfig.js"></script>
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dictType.css"/> --%>
	<script type="text/javascript">
	</script>
<%
//正常逻辑，获取工位的IP地址，根据用户的IP地址去数据库获取工位的名称

%>
</head>
	
<body>
	<h3>系统管理</h3>
	<div id="sysInform">
      <table id="showInform">
    	<thead>
    		<tr>
    			<th width="7%">序号</th>
    			<th width="17%">产品大类编码</th>
    			<th width="45%">产品大类名称</th>
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
		  </div>
</body>
</html>
