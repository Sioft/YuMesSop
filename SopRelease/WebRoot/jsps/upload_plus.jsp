<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <link href="${pageContext.request.contextPath}/css/webuploader.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/upload/upload.css" />
<link href="${pageContext.request.contextPath}/css/webuploader.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/external/datetimepicker/jquery.datetimepicker.css"/>
<style type="text/css">

.custom-date-style {
	background-color: red !important;
}

.input{	
}
.input-wide{
	width: 500px;
}

</style>
<%	
	//相对路径
	String nodePath = (String)request.getParameter("nodePath");
	//产品大类编码
	String productCode = (String)request.getParameter("productCode");
	//工序序号
	int procedureNo = Integer.parseInt(request.getParameter("procedureNo"));
	int versionNo = Integer.parseInt(request.getParameter("versionNo"))+1;
	System.out.print("乱码路径："+nodePath);
%>

    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/webuploader.min.js"></script>
	<!-- 存值label标签 -->
	<label id="nodePath" hidden="hidden"><%=nodePath %></label> 
	<label id="productCode" hidden="hidden"><%=productCode %></label> 
	<label id="procedureNo" hidden="hidden"><%=procedureNo %></label> 
	<div id="info">
		生效时间:<input type="text" value="" id="datetimepicker"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		版本号:<input type="text" value="<%=versionNo %>" id = "version" readonly/> 
	</div>
	<div id="wrapper">
		<div id="container">
			<div id="uploader">
				<div class="queueList">
					<div id="dndArea" class="placeholder">
						<div id="filePicker"></div>
						<p>您可以尝试文件拖拽，使用QQ截屏工具，然后激活窗口后粘贴，或者点击添加图片按钮</p>
					</div>
				</div>
				<div class="statusBar" style="display: none;">
					<div class="progress">
						<span class="text">0%</span> <span class="percentage"></span>
					</div>
					<div class="info"></div>
					<div class="btns">
						<div id="filePicker2"></div>
						<div class="uploadBtn">开始上传</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/upload.js"></script>
<script src="${pageContext.request.contextPath}/external/datetimepicker/build/jquery.datetimepicker.full.js"></script>
<script>/*
window.onerror = function(errorMsg) {
	$('#console').html($('#console').html()+'<br>'+errorMsg)
}*/
//var date = new Date();
$.datetimepicker.setLocale('ch');
$('#datetimepicker').datetimepicker({
dayOfWeekStart : 1,
lang:'en',
//disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
startDate:new Date()
});

 $('#datetimepicker').datetimepicker({value:new Date(),step:10}); 

</script>