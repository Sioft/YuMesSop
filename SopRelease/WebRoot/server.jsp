<%@ page language="java" pageEncoding="UTF-8" import="com.sop.websocket.WebSocketMessageServlet"%>
<%@ page import="java.net.*"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>碎片读取</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>	
		
<%-- <%!
public String getClientIp(HttpServletRequest request) { 
         String ip = request.getHeader("x-forwarded-for"); 
         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
         } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
         } 
       return ip;
 
}
public static String getMyIp()throws Exception{
     String cmd="ipconfig";
     Process p=Runtime.getRuntime().exec(cmd);
     java.io.InputStream in=p.getInputStream();
     java.io.BufferedReader br=new java.io.BufferedReader(new java.io.InputStreamReader(in));
     String line=null;
     String ip=null;
     while((line=br.readLine())!=null){
        System.out.print(line+"<br>");
        if(line.indexOf("IP Address")!=-1){
           ip=line.substring(line.indexOf(":")+1);
           break;
        }  
     }
     in.close();
     br.close();
     return ip;
}
%>
<%
out.print("ip:"+getClientIp(request)+","+getMyIp());
%> --%>



<%
//正常逻辑，获取工位的IP地址，根据用户的IP地址去数据库获取工位的名称
//如果数据库没有这个IP地址，那说明该工位是第一次登陆，显示注册页面
	
/* 	//缓存中用户为空
	if(user == null){
		//获取工位的IP地址
		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress().toString();//获得本机IP
		String address=addr.getHostName().toString();//获得本机名称 
	
	    out.println("ip:"+ip+";"+"address"+address);
	    out.println("当前连接人数:"+WebSocketMessageServlet.ONLINE_USER_COUNT);
		//为用户生成昵称
		user = "工位" + ip;
		WebSocketMessageServlet.ONLINE_USER_COUNT ++;
		session.setAttribute("user", user);
	}else{
		out.println("用户已存在");
	//获取工位的IP地址
		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress().toString();//获得本机IP
		String address=addr.getHostName().toString();//获得本机名称 
		user = "工位" + ip;
		WebSocketMessageServlet.ONLINE_USER_COUNT ++;
		session.setAttribute("user", user);
	 }
	pageContext.setAttribute("user", user); */
%>
<html>
<head>
	<title>WebSocket_test 聊天室</title>
	<!-- 引入CSS文件 -->
<!-- 	<link rel="stylesheet" type="text/css" href="ext4/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="ext4/shared/example.css" />
	<link rel="stylesheet" type="text/css" href="css/websocket.css" /> -->
	
	<!-- 映入Ext的JS开发包，及自己实现的webscoket. -->	<!-- <script type="text/javascript" src="websocket_test.js"></script> -->
	<script type="text/javascript">
		//alert("user:"+user);
		function initWebSocket() {
			
				if (window.WebSocket) {
					var	websocket = new WebSocket(encodeURI("ws://192.168.0.49:9000/"));
					
					var heartCheck = {
						    timeout: 60000,//60ms
						    timeoutObj: null,
						    reset: function(){
						       clearTimeout(this.timeoutObj);					    
						       this.start();
						    },
						    start: function(){
						        this.timeoutObj = setTimeout(function(){
						        	websocket.send("HeartBeat");
						        }, this.timeout);
						    }
						}
					
				
					websocket.onopen = function() {
						// 发送一个初始化消息
				        websocket.send("I am the client and I\'m listening!");
						//连接成功
						//win.setTitle(title + '&nbsp;&nbsp;(已连接)');
						$("msg").text("连接成功");
						console.log("连接成功");
						heartCheck.start();
					
					
					}
					websocket.onerror = function() {
						//连接失败
						//win.setTitle(title + '&nbsp;&nbsp;(连接发生错误)');
						$("msg").text("连接发生错误");
						console.log("连接发生错误");
					}
					websocket.onclose = function() {
						//连接断开
						//win.setTitle(title + '&nbsp;&nbsp;(已经断开连接)');
						$("msg").text("已经断开连接");
						console.log("已经断开连接");
						reconnect();
					}
					//消息接收
					websocket.onmessage = function(message) {
						//从ipqc接收字符串
						//解析字符串
						//如果前两位为01
						//if(message.type==0){
							//解析字符串，解析出stationCode+productCode+staffCode
							
							//向后台发送请求
							
							//查询出结果展示在sop展示界面
							
							//接收成功之后想服务端发送表示ok的字符串
						//}
						console.log("消息接收");
						console.log(message);
						//var message = JSON.parse(message.data);
						//接收用户发送的消息
						heartCheck.reset();
					}
				}
			};
			 function reconnect(url) {
			        if(lockReconnect) return;
			        lockReconnect = true;
			        //没连接上会一直重连，设置延迟避免请求过多
			        setTimeout(function () {
			            createWebSocket(url);
			            lockReconnect = false;
			        }, 2000);
			    }
			//发送消息
			function send(message) {
				
			}
			$(function(){
				$("#submit").click(function(){
					var message = {};
					//var userlist = {};
					var user1 = $("#user1").val();
					//var userlist = {};
					if (websocket != null) {
						//if ($(content).val()!="") {
							message = {
								from : user,
								content : $(content).val(),
								timestamp : new Date().getTime(),
								type : 'message',
								//要推送到的工位
								list:["user1","user2"]
							}
							console.log("messageJSON:"+JSON.stringify(message));
						//};
						websocket.send(JSON.stringify(user1));
						//websocket.onclose()
					} else {
						alert('提示', '您已经掉线，无法发送消息!');
					}
				});
			});
			
	</script>
	<script type="text/javascript">  
	
/* 	window.onbeforeunload = function(){ 
		if(confirm("确定删除第"+versionNo+"版本"))   //弹出确认删除对话框  
    	{	
    		//deleteVersionSop(productCode,procedureNo,versionNo);
    	//  return true;    //点击确定时，返回值为true，执行如上操作。
    	alert("ssss");
    	}
    	else
    	{
    	   return false; //点击取消时，返回值为false，不执行操作。
    	}
    //return "quit?";     
	}   */
	 /* $(window).unload(function(){
	        //响应事件
	       console.log("获取到了页面要关闭的事件了！"); 
	  }); 
	 */
	$(window).bind("beforeunload",function(){
		//发送即将关闭信息
		websocket.send(JSON.stringify(user1));
		console.log("获取到了页面要关闭的事件了！"); 
		return "sssss";
	});
       
</script>
</head>

<body onload="initWebSocket()">
	<h1>WebSocket发布界面</h1>
	<div><!-- 提示信息 -->
		<p id="msg">
		   
		</p>
		
		<input id = "user1" type="text"/>
	
		<input type="button" id="submit"/>
	</div>
	<div id="websocket_button"></div>
</body>
</html>
