<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>设备表维护</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/liujin/js/deviceInfo.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/liujin/css/basic.css"/>
	<script type="text/javascript">
    	$(function(){
    	       $("#saveDeviceInfo").click(saveDeviceInfo);
    		});
    	</script>
    	
<%
//正常逻辑，获取工位的IP地址，根据用户的IP地址去数据库获取工位的名称

%>
</head>
	
<body>
	<%
	
	/* chkid="+chkid+"&chkpjtid="+chkpjtid+"&chktype="+chktype+
			"&chkuvl="+chkuvl+"&chkdvl="+chkdvl; */
		String dveidOld = (String)request.getParameter("dveid");
		System.out.println("dveidOld:"+dveidOld);
		int dveid = 0;
		if(dveidOld != null)
		{
			dveid=Integer.parseInt(dveidOld);
		}
		String dvepjtid = (String)request.getParameter("dvepjtid");
		System.out.println("dvepjtid:"+dvepjtid);
		String dvetype = (String)request.getParameter("dvetype");
		System.out.println("dvetype:"+dvetype);
		String dvedtype = (String)request.getParameter("dvedtype");
		System.out.println("dvedtype:"+dvedtype);
		
		
		String dvevle = (String)request.getParameter("dvevle");
		System.out.println("dvevle:"+dvevle);

	%>
	<div id="device_new">
	<h3>检测表更新</h3>
	  <label hidden="hidden" id="dveid" ><%=dveid!=0?dveid:"" %></label>
		产品大类代码：<input type="text" id="dvepjtid" value="<%=dvepjtid!=null?dvepjtid:"" %>"/><br><br>
		
		检测类型：<input type="text" id="dvetype" value="<%=dvetype!=null?dvetype:"" %>"/><br><br>
		
		输出类型：<input type="text" id="dvedtype" value="<%=dvedtype!=null?dvedtype:"" %>"/><br><br>
		
		输出值：<input type="text" id="dvevle" value="<%=dvevle!=null?dvevle:"" %>"/><br><br>
		
		<input type="button"  onclick="window.history.go(-1);" class="btn" value="返回"/>
		<input type="button" id="saveDeviceInfo" class="btn" value="保存"/>
	</div>  
</body>
</html>
