<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>日记录入</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/multiple-select.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/multiple-select.css"/>
	<script type="text/javascript">
     $(function(){
    	 alert("sss");
   
        new MultipeSelect();
       $("#save").click(saveDiary);
     });
     
     function saveDiary(){
    	 var pdg = $("#pdg").val();
    	 console.log(pdg);
    		$.ajax({
    			url:"http://localhost:8080/sop/sop/save.do",
    			type:"post",
    			/* //
    			data:{"weather":weather,"week":week,"content":content}, */
    			data:{"pdg":pdg},
    			dataType:"json",
    			success:function(result){
    				if(result.status==0){
    					alert(result.msg);
    				}
    			},
    			error:function(){
    				alert("操作失败");
    			}
    			
    		});
    	}
    </script>
</head>
<body>
<div>  
    <input type="hidden" id="s_zoneStatId"    />  
    <input type="text" id="s_zoneStatName"   />  
    <div class="multipeSelect"     
style="position: absolute;"   
targetObjectId = "s_zoneStatId"   
targetObjectName = "s_zoneStatName" >  
               <ul>  
                                       <li>  
                                            <input value="1" type="checkbox">  
                                            <span>待评测</span>  
                                       </li>  
                                      
                                       <li>  
                                            <input value="2" type="checkbox">  
                                            <span>评测中</span>  
                                       </li>  
                                      
                                       <li>  
                                            <input value="3" type="checkbox">  
                                            <span>已评测</span>  
                                       </li>  
                                      
                                       <li>  
                                            <input value="4" type="checkbox">  
                                            <span>已定级</span>  
                                       </li>  
                                      
                                  
               </ul>  
               <a href="javascript:void(0)"  class="click">清空</a>  
               <a href="javascript:void(0)"  class="click">反选</a>  
      </div>  
</div>  
	<div id="diary_div">
     
	 <input type="button" id="save" value="保存"/>
	</div>
</body>
</html>