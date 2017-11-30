<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SOP<%-- (${requestScope.PRP_VERSION}) --%></title>
<link rel="Shortcut Icon" href="images/yu.ico"  type="image/x-icon" />  
</head>
<%
      String username = request.getParameter("username");
	  String department = request.getParameter("department");
		//System.out.println(user);
%>
<frameset rows="90,*" frameborder="NO" border="10" framespacing="0" id="mainFrame">
  <frame src="${pageContext.request.contextPath}/jsps/head.jsp?username=<%=username!=null?username:"" %>&department=<%=department!=null?department:"" %>" name="head" scrolling="no" id="head"
			noresize="noresize" />			
  <frameset id="menuFrame" cols="30%,*" framespacing="0" frameborder="no" border="0" >
		<%-- <frame name="main" scrolling="no" id="main" src="${ctx}/userPower/showMenu.do?systemCode=prpins&userCode=${userInfo.userCode }&riskCode=${session.userInfo.riskCode}"  /> --%>
		<frame name="menu" scrolling="auto" id="menu" src="${pageContext.request.contextPath}/jsps/menu_plus.jsp"  />
	  	<frame name="page" scrolling="auto" id="page" src="${pageContext.request.contextPath}/Message.jsp"/>
  </frameset>	
</frameset>
</html>	