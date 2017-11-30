<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SOP<%-- (${requestScope.PRP_VERSION}) --%></title>
</head>
<frameset rows="75,*" frameborder="NO" border="10" framespacing="0" id="mainFrame">
  <frame src="${pageContext.request.contextPath}/jsps/head.jsp" name="head" scrolling="no" id="head"
			noresize="noresize" />			
  <frameset id="menuFrame" cols="300,*" framespacing="0" frameborder="no" border="0" >
		<%-- <frame name="main" scrolling="no" id="main" src="${ctx}/userPower/showMenu.do?systemCode=prpins&userCode=${userInfo.userCode }&riskCode=${session.userInfo.riskCode}"  /> --%>
		<frame name="menu" scrolling="auto" id="menu" src="${pageContext.request.contextPath}/jsps/menu_plus.jsp"  />
	  	<frame name="page" scrolling="auto" id="page" src="${pageContext.request.contextPath}/Message.jsp"/>
  </frameset>	
</frameset>
</html>	