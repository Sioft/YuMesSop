<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>检测表维护</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/liujin/js/checkInfo.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/liujin/css/basic.css"/>
	<script type="text/javascript">
    	$(function(){
    	       $("#saveCheckInfo").click(saveCheckInfo);
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
		String chkidOld = (String)request.getParameter("procedureId");
		System.out.println("chkidOld:"+chkidOld);
		int chkid = 0;
		if(chkidOld != null)
		{
			chkid=Integer.parseInt(chkidOld);
		}
		String chkpjtid = (String)request.getParameter("chkpjtid");
		System.out.println("chkpjtid:"+chkpjtid);
		String chktype = (String)request.getParameter("chktype");
		System.out.println("chktype:"+chktype);
		String chkuvlOld = (String)request.getParameter("chkuvl");
		System.out.println("chkuvlOld:"+chkuvlOld);
		int chkuvl = 0;
		if(chkuvlOld != null)
		{
			chkuvl=Integer.parseInt(chkuvlOld);
		}
		
		String chkdvlOld = (String)request.getParameter("chkdvl");
		System.out.println("chkdvlOld:"+chkdvlOld);
		int chkdvl = 0;
		if(chkdvlOld != null)
		{
			chkdvl=Integer.parseInt(chkdvlOld);
		}

	%>
	<div id="check_new">
	<h3>检测表更新</h3>
	  <label hidden="hidden" id="chkid" ><%=chkid!=0?chkid:"" %></label>
		产品大类代码：<input type="text" id="chkpjtid" value="<%=chkpjtid!=null?chkpjtid:"" %>"/><br><br>
		
		检测类型：<input type="text" id="chktype" value="<%=chktype!=null?chktype:"" %>"/><br><br>
		
		检测上限：<input type="text" id="chkuvl" value="<%=chkuvl!=0?chkuvl:"" %>"/><br><br>
		
		检测下限：<input type="text" id="chkdvl" value="<%=chkdvl!=0?chkdvl:"" %>"/><br><br>
		
		<input type="button"  onclick="window.history.go(-1);" class="btn" value="返回"/>
		<input type="button" id="saveCheckInfo" class="btn" value="保存"/>
	</div>  
</body>
</html>
