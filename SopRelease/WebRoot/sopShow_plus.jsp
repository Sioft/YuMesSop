<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>代码 - 站长素材</title>
<link href="${pageContext.request.contextPath}/external/utils/css/jquery.slideBox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>	

</head>
<body>

<h3>欢迎进入鱼跃sop界面</h3>

<div id="demo1" class="slideBox">
  <ul id="sopWindow" class="items">
<%-- <li><img width="700px" height="300px" src="${pageContext.request.contextPath}/upload/20170424172809fatcat.jpg"></li> --%>
  <li><img width="700px" height="300px" src="${pageContext.request.contextPath}/images/002.jpg"></li>
  </ul>
</div>
<div >
	<input type="text" value="ZF-3-1" id = "productCode" readonly/> 
	<input type="text" value="1" id = "procedureNo" readonly/> 
	<input type="button" value="获取" id="find" />
</div>
<script src="${pageContext.request.contextPath}/external/utils/js/jquery.slideBox.min.js" type="text/javascript"></script>
<script type="text/javascript">

</script>

<script>

</script>

</body>
</html>
