function loadIPStation(){
	var ipAddress =  $("#ipAddress").val();
	console.log(ipAddress);
	//根据IP查找指定记录
	$.ajax({
		url:"../basic/findIPStation.do",
		type:"post",
		//
		data:{"ipAddress":ipAddress},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var resultdate = result.data;
				//$("#id").val(resultdate.SCFID);
				//$("#lineType").val(resultdate.LINETYPE);
				//$("#functionCode").val(resultdate.FUNCTIONCODE);
				//$("#functionName").val(resultdate.FUNCTIONNAME);
				//$("#productCodeSel").val(resultdate.PRODUCTID);
				//$("#procedureSel").val(resultdate.PROCEDURENO);
				 //$("#productCodeSel option[value='"+resultdate.PRODUCTID+"']").attr("selected", true);
				 //$("#procedureSel option[value='"+resultdate.PROCEDURENO+"']").attr("selected", true);
				$("#userId").val(resultdate.USERID);
				//$("#ipAddress").val(resultdate.IPADDRESS);
				//$("#macAddress").val(resultdate.MACADDRESS);
				
				//window.opener.location.replace(window.opener.document.referrer);window.close();
				//alert(result.msg);
				//window.location.href="stationConfig.jsp";
				//loadDayNotes();
				
			}
		},
		error:function(){
			alert("操作失败");
		}
		
	});
	
}

function loadEmployeeorSelect(userIds){
	//根据IP查找指定记录
	$.ajax({
		url:"../basic/findEmployeeorSel.do",
		type:"post",
		//
		//data:{"ipAddress":ipAddress},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var resultdate = result.data;
				var li = "";
				for(var i=0;i<resultdate.length;i++){
					if(i==0){
						li +="<option value='"+ resultdate[i].EMPNUMBER+"' selected='selected'>"+resultdate[i].EMPNAME+"</option>";
					}else{
						li +="<option value='"+ resultdate[i].EMPNUMBER+"'>"+resultdate[i].EMPNAME+"</option>";
					}
					
				}
               var $li=$(li);
               //藏值
               //$li.data("bookId",bookId);
               //将td添加到div中
               $("#userId").empty();
               $("#userId").append($li); 
               
               var count=$("#userId").get(0).options.length;
        	   for(var i=0;i<count;i++){
        		   if($("#userId").get(0).options[i].text == userIds)
        		   {
        			   $("#userId").get(0).options[i].selected = true;
        			   break;
        		   }  
        	   }
             //操作工下拉框选中默认值===userId；--在用户下拉框初始化时选择
              //$("#userId").find("option[text='"+userIds+"']").attr("selected",true); 
            // $("#userId option[value='"+userIds+"']").attr("selected", true);
               //$("#userId").val(userIds);
			}
		},
		error:function(){
			console.log("操作失败");
		}
		
	});
}

function loadProductCodeSelect(procedureNos,productCodes,userIds){
	//根据IP查找指定记录
	$.ajax({
		url:"../basic/findProductCodeSelect.do",
		type:"post",
		//
		//data:{"ipAddress":ipAddress},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var resultdate = result.data;
				var li = "";
				for(var i=0;i<resultdate.length;i++){
					if(i==0){
						li +="<option value='"+ resultdate[i].PRODUCTID+"' selected='selected'>"+resultdate[i].PRODUCTNAME+"</option>";
					}else{
						li +="<option value='"+ resultdate[i].PRODUCTID+"'>"+resultdate[i].PRODUCTNAME+"</option>";
					}
					
				}
               var $li=$(li);
               //藏值
               //$li.data("bookId",bookId);
               //将td添加到div中
               $("#productCodeSel").empty();
               $("#productCodeSel").append($li);
               //操作工下拉框选中默认值===userId；--在用户下拉框初始化时选择
               //$("#userId option[value='"+userIds+"']").attr("selected", true);
               //$("#userId").val(userIds);
               console.log(procedureNos+","+productCodes);
              /* var procedureNo = $("#procedureNos").text();
               console.log(procedureNo);
               var productCode = $("#productCodes").text();
               console.log(productCode);*/
               //procedureNos,procedureCodes
              if(productCodes != null && productCodes !=""){
            	  $("#productCodeSel option[value='"+productCodes+"']").attr("selected", true);
            	  //给用户赋值
            	  //---加载用户下拉菜单时已经赋值了
            	  if(userIds != null && userIds !=""){
	            	  var count=$("#userId").get(0).options.length;
		           	   for(var i=0;i<count;i++){
		           		   if($("#userId").get(0).options[i].text == userIds)
		           		   {
		           			   $("#userId").get(0).options[i].selected = true;
		           			   break;
		           		   }  
		           	   }
            	  }
            	  loadProcedureSelect(productCodes,procedureNos);
              }
              //如果为空传第一个默认
              else{
            	  loadProcedureSelect(resultdate[0].PRODUCTID,procedureNos);
              }
            
		     //$("#procedureSel option[value='"+procedureNos+"']").attr("selected", true);
				
			}
		},
		error:function(){
			console.log("操作失败");
		}
		
	});
}

function loadProcedureSelect(productCodes,procedureNos){
	$.ajax({
		url:"../basic/findProcedureSel.do",
		type:"post",
		//
		data:{"productId":productCodes},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var resultdate = result.data;
				var li = "";
				for(var i=0;i<resultdate.length;i++){
					if(i==0){
						li +="<option value='"+ resultdate[i].PROCEDURENO+"' selected='selected'>"+ resultdate[i].PROCEDURENO+" "+resultdate[i].PROCEDURENAME+"</option>";
					}else{
						li +="<option value='"+ resultdate[i].PROCEDURENO+"'>"+ resultdate[i].PROCEDURENO+" "+resultdate[i].PROCEDURENAME+"</option>";
					}
					
				}
               var $li=$(li);
               //藏值
               //$li.data("bookId",bookId);
               //将td添加到div中
               $("#procedureSel").empty(); 
               $("#procedureSel").append($li);
               $("#procedureSel").multipleSelect('refresh');
               if(procedureNos != null && procedureNos !=""){
            	   //转换为数组
            	   var procedureArr = procedureNos.split(",");
            	   $("#procedureSel").multipleSelect('setSelects',procedureArr);
            	   //$("#procedureSel option[value='"+procedureNos+"']").attr("selected", true);
               }else {
            	   //因为涉及到切换选择，所以会有procedureNo为空，但后台不为空的情况，所以需要查询
            	   //根据工位、产品大类、查找工序
            	   var ipAddress =  $("#ipAddress").val();
            	   findProcedureByIpAndProductCode(productCodes,ipAddress);
               }
             }
		},
		error:function(){
			console.log("操作失败");
		}
		
	});
}
function findProcedureByIpAndProductCode(productCodes,ipAddress){
	$.ajax({
		url:"../basic/findProcedureByIpAndProductCode.do",
		type:"post",
		//
		data:{"productId":productCodes,"ipAddress":ipAddress},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				console.log(result);
				var procedureNoss = result.data;
               if(procedureNoss != null && procedureNoss !=""){
            	   /*for(var i=0;i<procedureNoss.length;i++){
            		   console.log(procedureNoss[i].PROCEDURE_NO);
            		   //$("#procedureSel option[value='"+procedureNoss[i].PROCEDURE_NO+"']").attr("selected", true);
            		   
            	   }*/
            	   $("#procedureSel").multipleSelect('setSelects',procedureNoss.procedureGroup);
            	   //姓名
            	   //$("#userId").text(result.data.USERID);
            	   var count=$("#userId").get(0).options.length;
            	   for(var i=0;i<count;i++){
            		   if($("#userId").get(0).options[i].text == result.data.USERID)
            		   {
            			   $("#userId").get(0).options[i].selected = true;
            			   break;
            		   }  
            	   }
            	   //$("#userId option:contains('"+result.data.USERID+"')").attr("selected",true); 
               }
             }
		},
		error:function(){
			console.log("操作失败");
		}
		
	});
}

function saveStationConfig(){

	//id
	var id = $("#id").val();
	console.log("id:"+id);
	//线别号
	var lineType =  $("#lineType").val();
	if(lineType==null||lineType==""){
		alert("请输入线别号");
	}
	//机编码
	/*var functionCode = $("#functionCode").val();
	if(lineType==null||lineType==""){
		alert("请输入线别号");
	}*/
	//机名称
	/*var functionName =  $("#functionName").val();
	if(lineType==null||lineType==""){
		alert("请输入线别号");
		return false;
	}*/
	
	//产品大类
	var productId = $('#productCodeSel option:selected').val();
	if(productId==null||productId==""){
		alert("请选择产品大类！");
		return false;
	}
	//工序顺序号
	/*var procedureNo = $("#procedureNo").val();
	if(procedureNo==null||procedureNo==""){
		alert("请输入工序号");
		return false;
	}*/
	//var procedureNo = $('#procedureSel option:selected').val();
	var procedureNo =  $('#procedureSel').multipleSelect('getSelects').toString();
	console.log("多选："+procedureNo);
	console.log(procedureNo);
	if(procedureNo==null||procedureNo==""){
		alert("请选中工序号！");
		return false;
	}
	
	//用户id
	//产品大类
	var userId = $('#userId option:selected').val();
	if(userId==null||userId==""){
		alert("请选择当前操作员工！");
		return false;
	}
	//var userId = $("#userId").val();
	/*if(userId==null||userId==""){
		alert("请输入用户id");
		return false;
	}*/
	//暂时允许不输入用户名
	var ipAddress =  $("#ipAddress").val();
	if(ipAddress==null||ipAddress==""){
		alert("请输入IP地址！");
		return false;
	}
	console.log("ipAddress:"+ipAddress);
	/*var macAddress = $("#macAddress").val();
	if(macAddress==null||macAddress==""){
		alert("请输入mac地址");
		return false;
	}*/
	$.ajax({
		url:"../basic/saveOrUpdateConfig.do",
		type:"post",
		//
		data:{"lineType":lineType,"productId":productId,"procedureNo":procedureNo,
			"userId":userId,"ipAddress":ipAddress},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				window.opener.location.reload();
				window.close();
				//window.history.go(0);
				
			}else if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("操作失败");
		}
	
	});
	/*if(""==id){
		//alert(weather+","+week+","+text);
		$.ajax({
			url:"../basic/saveStationConfig.do",
			type:"post",
			//
			data:{"lineType":lineType,"functionCode":functionCode,"productId":productId,"procedureNo":procedureNo,
				"userId":userId,"ipAddress":ipAddress},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//alert(result.msg);
					//window.location.href="stationConfig.jsp";
					//loadDayNotes();
					//window.location.href=document.referrer;
					//window.opener.location.href=window.opener.location.href;
					 

					window.opener.location.reload();
					window.close();
					//window.history.go(0);
					
					//window.opener.location.reload();
				}
			},
			error:function(){
				alert("操作失败");
			}
			
		});
	}else{
		$.ajax({
			url:"../basic/updateStationConfig.do",
			type:"post",
			//
			data:{"scfId":id,"lineType":lineType,"functionCode":functionCode,"productId":productId,"procedureNo":procedureNo,
				"userId":userId,"ipAddress":ipAddress},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//alert(result.msg);
					//还原默认
					//window.location.href="stationConfig.jsp";
					//loadDayNotes();
					//window.opener.location.href=window.opener.location.href;window.close();
					//alert("sss");

					window.opener.location.reload();
					window.close();
					//window.history.go(0);
					
				}
			},
			error:function(){
				alert("操作失败");
			}
		
		});
	}*/
	
}
//列出
function loadAllStationConfig(){
	$.ajax({
  		url:"../basic/findAllStationConfig.do",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				resultdata = result.data;
				//gotoPage(resultdata);
				pageCalculate(resultdata,pageSize);
				firstPage(resultdata,pageSize);//显示首页
				//noteno="";
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}

function edit(obj){
	//"lineType":lineType,"functionCode":functionCode,"functionName":functionName,"procedureNo":procedureNo,
	//"userId":userId,"ipAddress":ipAddress,"macAddress":macAddress
	var par = obj.parentNode.parentNode;
	var childs = par.childNodes;
	var scfId = childs[1].innerText;
	var lineType = childs[2].innerText;
	var functionCode = childs[3].innerText;
	var functionName = childs[4].innerText;
	var procedureNo = childs[5].innerText;
	var userId = childs[6].innerText;
	var ipAddress = childs[7].innerText;
	var macAddress = childs[8].innerText;
	var createTime = childs[9].innerText;
	//跳转页面
	window.location.href="updateStation.jsp?scfId="+scfId+"&lineType="+lineType+"&functionCode="+functionCode+
			"&functionName="+functionName+"&procedureNo="+procedureNo+"&userId="+userId+"&ipAddress="+ipAddress+
			"&createTime="+createTime+"&macAddress="+macAddress;
}

function del(obj){
	var par = obj.parentNode.parentNode;
	var childs = par.childNodes;
	//获得要删除的摘要号码
	var id = childs[1].innerText;
	var flag =confirm("确定要删除该配置?");
	if(flag){ 
		$.ajax({
			url:"../basic/deleteStationConfig.do",
			type:"post",
			//
			data:{"scfId":id},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg);
					//还原默认
					
					loadAllStationConfig();
				}
			},
			error:function(){
				alert("操作失败");
			}
		
		});
	}
}

function clearTableContent(){//清除表格中的数据
	$("#stationTable tbody tr").remove();
}
			
function addTableContent(tr){//将数据加入表格中
	$("#stationTable tbody").append($(tr));
} 


function prePage(data,pageSize){//上一页
	var pageIndex =$("#currentPage").text();//获取当前页
	gotoPage(pageSize,parseInt(pageIndex)-1,data);//跳转至上一页
	$("#currentPage").text(parseInt(pageIndex)-1);//改变当前页页数
	pageJudge();
}

function nextPage(data,pageSize){//下一页
	var pageIndex =$("#currentPage").text();//获取当前页
	gotoPage(pageSize,parseInt(pageIndex)+1,data);//跳转至下一页
	$("#currentPage").text(parseInt(pageIndex)+1);//改变当前页页数
	pageJudge();
}
function gotoPage(pageSize,pageIndex,data){//页数跳转
	var tr = "";
	for(var i=(pageIndex-1)*pageSize;i<=parseInt(pageIndex)*parseInt(pageSize)-1 && i<data.length;i++){
		//var createTime = data[i].CREATEDATE;
		var unixTime = new Date(data[i].CREATEDATE);
		//alert(data[i].insertimedate);
		var time = unixTime.toLocaleString();
		//{MACADDRESS=1, CREATEDATE=2017-05-09 11:31:27.0, FUNCTIONCODE=1, SCFID=3, PROCEDURENO=1, LINETYPE=1, IPADDRESS=1, FUNCTIONNAME=1, USERID=0}]
		tr += "<tr><td>"+(i+1)+"</td><td style='display:none'>"+data[i].SCFID+"</td><td>"+data[i].LINETYPE+"</td><td>"+data[i].FUNCTIONCODE+"</td><td>"+data[i].FUNCTIONNAME+"</td>" +
				"<td>"+data[i].PROCEDURENO+"</td><td>"+data[i].USERID+"</td><td>"+data[i].IPADDRESS+"</td><td>"+data[i].MACADDRESS+"</td><td>"+time+"</td>";
		tr += "<td><input type='button' value ='编辑' onclick='edit(this)' class='btn'/><input type='button' value='删除' onclick='del(this)' class='btn'/></td></tr>";
		}
	clearTableContent();
	addTableContent(tr);
}
/*function gotoPage(data){//页数跳转
	var tr = "";
	for(var i=0;i<data.length;i++){
		
	}
	console.log(tr);
	clearTableContent();
	addTableContent(tr);
}*/
function pageCalculate(data,pageSize){//计算总页数
	var temp = data.length%pageSize;
	if(temp !=0){
		$("#totalPage").text(parseInt(data.length/pageSize+1));
	}else{
		$("#totalPage").text(data.length/pageSize);
	}
}

function pageJudge(){//判断上一页下一页是否可用
	var currentPage =$("#currentPage").text();
	var totalPage =$("#totalPage").text();
	
	if(currentPage == totalPage && currentPage ==1){//当前页为尾页且当前页为起始页
		$("#nextPage").attr("disabled",true);
		$("#prePage").attr("disabled",true);
	}else if(currentPage ==1){//当前页为起始页
		$("#prePage").attr("disabled",true);
		$("#nextPage").attr("disabled",false);
	}else if(currentPage == totalPage){//当前页为尾页
		$("#prePage").attr("disabled",false);
		$("#nextPage").attr("disabled",true);
	}else{//当前页既不是首页也不是尾页
		$("#nextPage").attr("disabled",false);
		$("#prePage").attr("disabled",false);
	}
}

function firstPage(data,pageSize){//首页
	$("#totalRecords").text(data.length);
	gotoPage(pageSize,parseInt(1),data);//跳转至首页
	$("#currentPage").text(1);//改变当前页页数
	pageJudge();
}
/*function addNewStationConfig(){
	window.location.href="updateStation.jsp;
}*/
