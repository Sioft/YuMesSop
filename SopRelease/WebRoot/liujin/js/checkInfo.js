
function saveCheckInfo(){

	console.log("in");
	//id
	var chkid = $("#chkid").val();
	console.log("chkid:"+chkid);
	var chkpjtid =  $("#chkpjtid").val();
	var chktype = $("#chktype").val();
	var chkuvl =  $("#chkuvl").val();
	var chkdvl = $("#chkdvl").val();

	if(""==chkid){
		//alert(weather+","+week+","+text);
		$.ajax({
			url:"../basic/saveCheckInfo.do",
			type:"post",
			//
			data:{"chkpjtid":chkpjtid,"chktype":chktype,"chkuvl":chkuvl,"chkdvl":chkdvl},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg);
					window.location.href="checkInfo.jsp";
					//loadDayNotes();
					
				}
			},
			error:function(){
				alert("操作失败");
			}
			
		});
	}else{
		$.ajax({
			url:"../basic/updateCheckInfo.do",
			type:"post",
			//
			data:{"chkid":chkid,"chkpjtid":chkpjtid,"chktype":chktype,"chkuvl":chkuvl,"chkdvl":chkdvl},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg);
					//还原默认
					window.location.href="checkInfo.jsp";
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
function loadAllCheckInfo(){
	$.ajax({
  		url:"../basic/findAllCheckInfo.do",
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
	var chkid = childs[1].innerText;
	var chkpjtid = childs[2].innerText;
	var chktype = childs[3].innerText;
	var chkuvl = childs[4].innerText;
	var chkdvl = childs[5].innerText;
	
	//跳转页面
	window.location.href="updateCheckInfo.jsp?chkid="+chkid+"&chkpjtid="+chkpjtid+"&chktype="+chktype+
			"&chkuvl="+chkuvl+"&chkdvl="+chkdvl;
}

function del(obj){
	var par = obj.parentNode.parentNode;
	var childs = par.childNodes;
	//获得要删除的摘要号码
	var id = childs[1].innerText;
	var flag =confirm("确定要删除该检测配置?");
	if(flag){ 
		$.ajax({
			url:"../basic/deleteCheckInfo.do",
			type:"post",
			//
			data:{"chkid":id},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg);
					//还原默认
					
					loadAllCheckInfo();
				}
			},
			error:function(){
				alert("操作失败");
			}
		
		});
	}
}

function clearTableContent(){//清除表格中的数据
	$("#checkTable tbody tr").remove();
}
			
function addTableContent(tr){//将数据加入表格中
	$("#checkTable tbody").append($(tr));
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
		//‘；var unixTime = new Date(data[i].CREATEDATE);
		//alert(data[i].insertimedate);
		///var time = unixTime.toLocaleString(); 
		//CHKTYPE=check_type_001, CHKPJTID=1, CHKUVL=10, CHKDVL=20, CHKID=1
		tr += "<tr><td>"+(i+1)+"</td><td style='display:none'>"+data[i].CHKID+"</td><td>"+data[i].CHKPJTID+"</td><td>"+data[i].CHKTYPE+"</td><td>"+data[i].CHKUVL+"</td>" +
				"<td>"+data[i].CHKDVL+"</td>";
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
