<%@page import="com.sop.entity.SopShow"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>jquery自动隐藏切换按钮焦点图代码 - 站长素材</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/welcome_plus.js"></script>
<script src="${pageContext.request.contextPath}/scripts/slider.js"></script>
	
<style type="text/css">

.lab{
		color:#6fbce8;
}
.btn{
	background:none;
	border:none;
	cursor:pointer;
	color:#333;
	border:1px solid #6fbce8;
	width:100px;
	height:30px;
	margin-left:30px;
}
.btn:hover{
	color:#6fbce8;
}
.box{color: #000}
 
.items{
	list-style-type:none;
}
table tr td{
	text-align: center;
}
</style>
</head>
<body>
<%	
	//相对路径
	String nodePath = (String)request.getParameter("nodePath");
	//产品大类编码
	String productCode = (String)request.getParameter("productCode");
	//工序序号
	//int procedureNo = Integer.parseInt(request.getParameter("procedureNo"));
%> 
<!-- 存值label标签 -->
	<label id="nodePath" hidden="hidden"><%=nodePath %></label> 
	<label id="productCode" hidden="hidden"><%=productCode %></label> 
	<%-- <label id="procedureNo" hidden="hidden"><%=procedureNo %></label> --%>
 

<h3 style="margin-left:25%">欢迎进入鱼跃sop界面</h3>
<table width="55%" id="sopTable" style="font-size:14px;">

		<%-- <tr>
			<td>
			工序
			</td>
			
			<td>
				<div >
					<div class="slideBox">
					  <ul id="image_sop" class="items">
						<li><img width="450px" height="200px" src="${pageContext.request.contextPath}/upload/ZF/20170428164416dog.jpg"></li>
					  </ul>
					</div>
					<span>生效时间：<span class="lab" id="valid_time">${validTime }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="delete" class="btn" value="删除" /></span>
				</div>
			</td>
			<td>
				<div>
					<div class="slideBox">
					  <ul id="image_sop" class="items">
						<li><img width="450px" height="200px" src="${pageContext.request.contextPath}/upload/ZF/20170428164416dog.jpg"></li>
					  </ul>
					</div>
					<span>生效时间：<span class="lab" id="valid_time">${validTime }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="delete" class="btn" value="删除" /></span>
				</div>
			</td> 
			<td>
				<input  type="button" class="btn" id="uploadnew" value="上传新版本">
			</td>
				
		</tr>
		<tr>
			<td colspan="4"><hr></td>
		</tr> --%>


</table>
<%-- 版本号：<select id="version_no" >
<%
	List<Integer> versionlist = (List<Integer>)session.getAttribute("versionlist");
	for(int i : versionlist){
		%>
		<option value="<%=i %> "><%=i %></option>
		<% 
	}
%>
</select> --%>


</body>
</html>
