<%@ page contentType="text/html; charset=utf-8"%>
<html>
    <head>
        <title>我的笔记</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/menu.js"></script>
		<style>
		.styled-select select {
			   background: transparent;
			   width: 268px;
			   padding: 5px;
			   font-size: 16px;
			   border: 1px solid #ccc;
			   height: 34px;
			   -webkit-appearance: none; /*for chrome*/
			}
			
			body {
				background-color: #F1F1F1;
				border-top: 1px #B7B7B8 solid;
				background-repeat: no-repeat;
				background-position: left bottom;
				/* background-image : url(../images/background.jpg);
				background-repeat:repeat-y; */
			}
			.switchPoint {
				COLOR: #ff9e0d;
				CURSOR: hand;
				FONT-FAMILY: "Webdings";
				FONT-SIZE: 9pt;
			}
		</style>
		<script type="text/javascript">
    		
    	</script>
    </head>
    <body  leftmargin=0 topmargin=0 marginwidth=0 marginheight=0">
    	
    	<br>
    	<!-- 产品大类 -->
    
    	<div class="styled-select">
    	 <p > 产品大类：</p>
    	  <select id="productTypeSel" >
		  	<option value="0">物料</option>
		  	<option value="1">压缩机</option>
		  	<option value="2">热能板</option>
		  	<option value="3">铜板</option>
		  </select>
    	</div>
    	<br/>
    	<!-- 工位 -->
    	<!-- <div class="styled-select">
    	  工位：<select id="stationSel">
		  	<option value="0">001</option>
		  	<option value="1">002</option>
		  	<option value="2">003</option>
		  	<option value="3">004</option>
		  </select>
    	</div> -->
    	<br/>
    	<!-- 人员 -->
    	<!-- <div class="styled-select">
    	  人员：<select id="staffSel">
		  	<option value="0">1001</option>
		  	<option value="1">1002</option>
		  	<option value="2">1003</option>
		  	<option value="3">1004</option>
		  </select>
    	</div>
    	<br/>
    	<br/>
    	
    	<div></div>
		<input type="text" name="menuData" id="menuData" value="menuContent" />
		工位选择：
		<table style="width:90%;height:50%" border="0" cellpadding="5">
        <tbody>
            <tr>
                     
                </td>
            </tr>
            <tr>
                <td>
                    <select id="stationSel" style="width: 135px; height: 280px;" multiple="multiple">
                    </select>
                </td>
                <td align="center" width="5%">
                    <button type="button" id="add" style="width: 30px;">&gt;</button> <br><br>
                    <button type="button" id="add_all" style="width: 30px;">&gt;&gt;</button> <br><br> 
                    <button type="button" id="remove" style="width: 30px;">&lt;</button> <br><br>
                    <button type="button" id="remove_all" style="width: 30px;">&lt;&lt;</button> 
                </td>
                <td>
                     
                    <select  style="width: 135px; height: 280px;" multiple="multiple" id="select2">
                        <option value="111123vbb1">vpx1111</option>
                        <option value="2s2222">2orvq222</option>
                    </select>
                </td>
            </tr>
        </tbody>
    </table> -->
    <<input id="submit" type="button" value="查询"/> 

	</body>	
<script>
     $("#submit").on('click', function(){ 
    	 $.ajax({
    	  		url:"http://localhost:8080/sop/sop/findSopAllProce.do",
    			type:"post",
    			data:{"productCode":"ZF-1"},
    			dataType:"json",
    			success:function(result){
    				console.log("station:"+result);
    				
    			},
    			error:function(){
    				alert("程序出错");
    			}
    		});
     });
                     
     </script>	
</html>