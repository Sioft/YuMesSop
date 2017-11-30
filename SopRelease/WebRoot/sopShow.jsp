<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> <HTML> <HEAD> <TITLE> New Document </TITLE> <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script><script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script><script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script><script type="text/javascript"> window.onload = function() { var arr = $('#divs').find('div').toArray();// 把三个div放进数组里面 
var temp; // 1 3对调 
temp = arr[0]; arr[0] = arr[2]; arr[2] = temp; $('#divs').html(arr) } </script> </HEAD> <BODY> <div id="divs"> <div>111111</div> <div>222222</div> <div>333333</div> </div> </BODY> </HTML>