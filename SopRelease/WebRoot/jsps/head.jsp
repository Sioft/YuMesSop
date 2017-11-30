<%@ page contentType="text/html; charset=utf-8"%>

<html>
<head>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.min.js"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/scripts/time.js"></script>
	<script type="text/javascript">
		function initWindow(){
			showTime();
		}
	</script> --%>
 	<style>
 	*{
	    padding: 0;
	    margin: 0;
	}
	
	 	.header{
		    width: 100%;
		    height: 90px;
		    min-width:1000px;
		    background: url("../images/header_bg.png");
		}
		.header_content{
		    width: 1000px;
		    height: 90px;
		    margin: auto;
		    font-size: 22px;
		}
		.logo,.vertical,.mes_logo,.header_msg{
		    float: left;
		}
		.logo{
		    margin-top: 30px;
		    margin-right: 20px;
		}
		.mes_logo{
		    margin-left: 20px;
		    margin-top: 30px;
		}
		.header_msg{
		    margin-left: 300px;
		}
		.btn_exit{
		    width: 70px;
		    height: 25px;
		    margin-left: 80px;
		    margin-top: 18px;
		    background:url("../images/btn_bg.png") ;
		    border: none;
		    color: #ffffff;
		    cursor: pointer;
		}
		.btn_exit:hover{
		    background:url("../images/btn_bg_f.png") ;
		}
		.btn_exit{
		    margin-left: 70px;
		}
		.header_user_message,.department{
		    width: 100%;
		    text-align: right;
		    font-size:12px ;
		    margin-top: 6px;
		}
		.user_name,.user_department{
   			 color: #333333;
		}
		.user_name_a,.user_department_a{
		    color: #3a8fe4;
		}
	</style> 
	<title>HAED</title>
</head>

<%
	String username = request.getParameter("username");
	String department = request.getParameter("department");
%>
<body >
	<div class="header">
		<div class="header_content">
            <div class="logo"><img src="../images/LOGO.png" alt=""></div>
            <div class="vertical"><img src="../images/vertical.png" alt=""></div>
            <div class="mes_logo"><img src="../images/mes_logo.png" alt=""></div>
             <div class="header_msg">
                <div class="header_btn">
                    <input type="button" onclick="qualityquit()" value="关闭" class="btn_exit">
              </div>
              <div class="header_user_message">
                  <span class="user_name">用户名：</span>
                  <span class="user_name_a"><%=username!=null?username:"" %></span>
              </div>
              <div class="department">
                  <span class="user_department">部门：</span>
                  <span class="user_department_a"><%=department!=null?department:"" %></span>
              </div> 
	</div>
	
	<!-- <img width="50px" height="50px" src="../images/logo_r.jpg"/>
		<div style="position:absolute;left:6%;top:8%;font-family:'Arial';margin:center;">
			<span>TimeFlow:</span><span id="time"></span>
		</div> -->
		<script type="text/javascript">
		function qualityquit(){
			parent.window.close();
		 }
		</script>
</body>
</html>