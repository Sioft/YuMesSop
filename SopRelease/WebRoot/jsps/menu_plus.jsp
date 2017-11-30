<%@ page contentType="text/html; charset=utf-8"%>
<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/external/zTree_v3-master/css/demo.css" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/external/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" type="text/css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/external/zTree_v3-master/js/jquery.ztree.core.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery-ui.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/menu.js"></script>
  
  <style type="text/css">
div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
div#rMenu ul li{
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
.treeDiv {
	/* margin-right: 0; */
	float:right;
	margin-right:20px;
	margin-top:20px;
}
	</style>
  <SCRIPT LANGUAGE="JavaScript">
  
   /* var zTreeObj;
   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
   var setting = {};
   // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
   var zNodes = [
   {name:"test1", open:true,children:[
      {name:"test1_1"}, {name:"test1_2"}]},
   {name:"test2", open:true, children:[
      {name:"test2_1"}, {name:"test2_2"}]}
   ];
   */
  
  </SCRIPT>
  <style>
  	.treeDiv{
  		/* margin-left:100px; */
  	}
  </style>
 </HEAD>
<BODY>
<br>
<%-- <div class="select">
    	  <p>请选择工序序号:</p><select id="procedureNumberSel" >
    	  <option value="0" disabled selected>..请选择..</option>
    	  <%
    	  	for(int i=0;i<100;i++){
    			%>
    			<option value="<%=i+1%>"><%=i+1%></option>
    		<%
    		}
    		%>
		  </select>
    	</div>
    	<br>
<div> --%>
<div class="treeDiv">
   <ul id="treeDemo" class="ztree"></ul>   
</div>
<div id="rMenu" class="menu">
	<ul>
		<li id="m_add" onclick="addTreeNode();">增加节点</li>
		<li id="m_edit" onclick="editTreeNode();">编辑节点</li>
		<li id="m_del" onclick="removeTreeNode();">删除节点</li>
	</ul>
</div>
<br>
<div id="com-dialog-new" title="请输入产品大类名称">
  <p>
  	<input type="text" id = "newProductCodeName"/>
  	</p>
</div>
<div id="dialog-edit" title="请输入新的产品大类名称">
  <p>
  	<input type="text" id = "editProductCodeName"/>
  	</p>
</div>
<div id="dialog-delete" title="要删除该产品吗？">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>这些项目将被永久删除，并且无法恢复。您确定吗？</p>
</div>
<!-- <div id="com-dialog-confirm1" title="提示框">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>生产数量：</p><div id="prodNum"></div>
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>入库数量：</p><div id="inProdNum"></div>
</div> -->
<!-- <input id="submit" type="button" value="测试"/> -->
<script>
   
                     
     </script>	

</BODY>
</HTML>