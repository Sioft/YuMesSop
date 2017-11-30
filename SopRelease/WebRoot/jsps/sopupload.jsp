<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>sop批量上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<%
		//相对路径
		String nodePath = (String) request.getParameter("nodePath");

		String nodePath2 = java.net.URLDecoder.decode(nodePath, "UTF-8");
		nodePath = nodePath2.replace("_", "/");
		//产品大类编码
		String productCode = (String) request.getParameter("productCode");
		String productName = (String) request.getParameter("productname");
		productName = java.net.URLDecoder.decode(productName, "UTF-8");
		//工序序号
		//int procedureNo = Integer.parseInt(request.getParameter("procedureNo"));
	%>
	<!-- 存值label标签 -->
	<label id="nodePath" style="display: none;"><%=nodePath%></label>
	<label id="productCode" style="display: none;"><%=productCode%></label>
	<label id="productName" style="display: none;"><%=productName%></label>
	<%-- <label id="procedureNo" hidden="hidden"><%=procedureNo %></label> --%>
	<h3 style="margin-left:25%">欢迎进入鱼跃sop界面</h3>
	
	

</body>
</html>
