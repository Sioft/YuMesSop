
function saveProcedure(){

	console.log("in");
	//id
	var id = $("#id").val();
	console.log("id:"+id);
	var productCode =  $("#productCode").val();
	var productName = $("#productName").val();
	var procedureNo =  $("#procedureNo").val();
	var procedureCode = $("#procedureCode").val();
	var procedureName = $("#procedureName").val();

	if(""==id){
		//alert(weather+","+week+","+text);
		$.ajax({
			url:"../../sop/basic/saveProcedure.do",
			type:"post",
			//
			data:{"productCode":productCode,"productName":productName,"procedureNo":procedureNo,"procedureCode":procedureCode,
				"procedureName":procedureName},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg);
					window.location.href="procedure.jsp";
					//loadDayNotes();
					
				}
			},
			error:function(){
				alert("操作失败");
			}
			
		});
	}else{
		$.ajax({
			url:"../../sop/basic/updateProcedure.do",
			type:"post",
			//
			data:{"procedureId":id,"productCode":productCode,"productName":productName,"procedureNo":procedureNo,"procedureCode":procedureCode,
				"procedureName":procedureName},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg);
					//还原默认
					window.location.href="procedure.jsp"
					//loadDayNotes();

				}
			},
			error:function(){
				alert("操作失败");
			}
		
		});
	}
	
}
//列出
function loadAllProcedure(){
	$.ajax({
  		url:"../basic/findAllProcedure.do",
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
	//"productCode":productCode,"productName":productName,"procedureNo":procedureNo,"procedureCode":procedureCode,
	//"procedureName":procedureName
	var par = obj.parentNode.parentNode;
	var childs = par.childNodes;
	var procedureId = childs[1].innerText;
	var productCode = childs[2].innerText;
	var productName = childs[3].innerText;
	var procedureNo = childs[4].innerText;
	var procedureCode = childs[5].innerText;
	var procedureName = childs[6].innerText;
	
	//跳转页面
	window.location.href="updateProcedure.jsp?procedureId="+procedureId+"&productCode="+productCode+"&productName="+productName+
			"&procedureNo="+procedureNo+"&procedureCode="+procedureCode+"&procedureName="+procedureName;
}

function del(obj){
	var par = obj.parentNode.parentNode;
	var childs = par.childNodes;
	//获得要删除的摘要号码
	var id = childs[1].innerText;
	var flag =confirm("确定要删除该工序?");
	if(flag){ 
		$.ajax({
			url:"../basic/deleteProcedure.do",
			type:"post",
			//
			data:{"procedureId":id},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg);
					//还原默认
					
					loadAllProcedure();
				}
			},
			error:function(){
				alert("操作失败");
			}
		
		});
	}
}

function clearTableContent(){//清除表格中的数据
	$("#procedureTable tbody tr").remove();
}
			
function addTableContent(tr){//将数据加入表格中
	$("#procedureTable tbody").append($(tr));
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
		//{PROCEDURECODE=1, PRODUCTNAME=1, CREATEDATE=2017-05-09 18:22:57.0, PROCEDURENAME=1, PRODUCTCODE=1, PROCEDURENO=1, PROCEDUREID=1}]		
		tr += "<tr><td>"+(i+1)+"</td><td style='display:none'>"+data[i].PROCEDUREID+"</td><td>"+data[i].PRODUCTCODE+"</td><td>"+data[i].PRODUCTNAME+"</td><td>"+data[i].PROCEDURENO+"</td>" +
				"<td>"+data[i].PROCEDURECODE+"</td><td>"+data[i].PROCEDURENAME+"</td><td>"+time+"</td>";
		tr += "<td><input type='button' value ='编辑' onclick='edit(this)' class='btn'/><input type='button' value='删除' onclick='del(this)' class='btn'/></td></tr>";
		}
	console.log(tr);
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
/*function addNewProcedure(){
	window.location.href="updateStation.jsp;
}*/
