<%@page import="com.sop.entity.SopShow"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>jquery自动隐藏切换按钮焦点图代码 - 站长素材</title>
<link href="${pageContext.request.contextPath}/external/utils/css/jquery.slideBox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/welcome.js"></script>
<script src="${pageContext.request.contextPath}/external/utils/js/jquery.slideBox.min.js" type="text/javascript"></script>
	
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
	}
	.btn:hover{
		color:#6fbce8;
	}
	.box{color: #000}
 
  .red{color:#d00;}
	
</style>
</head>
<body>
<%	
	//相对路径
	String nodePath = (String)request.getParameter("nodePath");
	//产品大类编码
	String productCode = (String)request.getParameter("productCode");
	//工序序号
	int procedureNo = Integer.parseInt(request.getParameter("procedureNo"));
%>
<!-- 存值label标签 -->
	<label id="nodePath" hidden="hidden"><%=nodePath %></label> 
	<label id="productCode" hidden="hidden"><%=productCode %></label> 
	<label id="procedureNo" hidden="hidden"><%=procedureNo %></label>
<center>
<h3>欢迎进入鱼跃sop界面</h3>
 <%--  <%
	List<Map<String, String>> pdglist = (List<Map<String, String>>)session.getAttribute("pdglist");
	System.out.println(pdglist);
	if(pdglist!=null){
		for(int i=0;i<pdglist.size();i++){
			%>
			<li><a href="" title="书中的世界"><img width="700px" height="300px" src="${pageContext.request.contextPath}/upload/<%= pdglist.get(i).get("SYSNAME")%>"></a></li>
			<%
		}
	}
	//System.out.print(url+"sss");		
%> --%>

<div id="demo1" class="slideBox">
  <ul id="image_sop" class="items">
	<c:forEach items="${pdglist }" var="model" varStatus="s">
	<li><img width="700px" height="300px" src="${pageContext.request.contextPath}/upload${model.PDGPATH}/${model.SYSNAME}"></li>
	</c:forEach>
  </ul>
</div>
<!-- 测试 -->
<%-- <div id="demo2" class="slideBox">
  <ul class="items">
	<li><img width="100px" height="260px" src="${pageContext.request.contextPath}/upload/ZF/20170426175122dog.jpg"></li>
	<li><img width="100px" height="300px" src="${pageContext.request.contextPath}/upload/ZF/20170426175122dog.jpg"></li>
	<li><img width="900px" height="600px" src="${pageContext.request.contextPath}/upload/ZF/20170426175122dog.jpg"></li>
	<li><img width="700px" height="700px" src="${pageContext.request.contextPath}/upload/ZF/20170426175122dog.jpg"></li>
  </ul>
</div> --%>
<br>
版本号：<select id="version_no" >
<%
	List<Integer> versionlist = (List<Integer>)session.getAttribute("versionlist");
	for(int i : versionlist){
		%>
		<option value="<%=i %> "><%=i %></option>
		<% 
	}
%>
</select>

<label id="versionNo" hidden="hidden"><%=versionlist.get(0) %></label>
<%-- <span class="lab" id="version">${versionNo }</span> --%>&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn" id="uploadnew" value="上传新版本"><input type="button" id="delete" value="删除" />
<p>生效时间：<span class="lab" id="valid_time">${validTime }</span></p>
</center>
<%-- <%
	int nextVersion = (Integer)session.getAttribute("nextVersion");
	System.out.println("下一版本号");
	if(nextVersion != 0){
		%>
		<div class="box">有新版本</div>
		<%  
	}
%> --%>
<%-- <%
	//展示所有版本：一次性加载(未失效，失效相当有删除)
	List<SopShow> soplist = (List<SopShow>)session.getAttribute("sopShowList");
	for(SopShow sopShow: soplist){
		sopShow.getVersionNo();
		sopShow.getValidTime();
		sopShow.getPdglist();
	}
%> --%>
</body>
</html>
