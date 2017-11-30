var pageSize = 4;

$(function(){
	
	loadDictType();
});

function loadDictType(){
	$.ajax({
  		url:"system/findDictType.do",
		type:"post",
		dataType:"json",
		success:function(result){
			console.log(result);
			if(result.status == 0){
				resultdata = result.data;
				console.log(resultdata.length);
				pageCalculate(resultdata,pageSize);
				firstPage(resultdata,pageSize);//显示首页
				
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
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
		tr += "<tr><td>"+(i+1)+"</td><td style='display:none'>"+data[i].DTPID+"</td><td>"+data[i].DTPCODE+"</td><td>"+data[i].DTPNAME+"</td>";
		tr += "<td><input id='cno' type='button' onclick='brower(this)' value ='浏览' class='btn'/><input type='button' value='删除' onclick='drop(this)' class='btn'/><input type='button' onclick='edit(this)' value='编辑' class='btn'/></td></tr>";
	}
	clearTableContent();
	addTableContent(tr);
}

//查看明细
function brower(obj){
	//获取当前产品大类
	var dtpId = $(obj).parent().parent().find("td").eq(1).html();
	console.log(dtpId);
	$.ajax({
  		url:"system/findDictData.do",
		type:"post",
		data:{"dtpId":dtpId},
		dataType:"json",
		success:function(result){
			console.log(result);
			if(result.status == 0){
				resultdata = result.data;
				addDictData(resultdata,obj);
				
				//pageCalculate(resultdata,pageSize);
				//firstPage(resultdata,pageSize);//显示首页
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}
//修改
function edit(obj){
	
}
//删除
function drop(obj){
	//获取当前产品大类
	var dtpId = $(obj).parent().parent().find("td").eq(1).html();
	console.log(dtpId);
	$.ajax({
  		url:"system/findDictData.do",
		type:"post",
		data:{"dtpId":dtpId},
		dataType:"json",
		success:function(result){
			console.log(result);
			if(result.status == 0){
				$(".sub").hide();
				resultdata = result.data;
				tr = "<tr class='sub'><td>2</td><td style='display:none'>1</td><td>ZF-2</td><td>SS</td>";
				tr += "<td><input id='cno' type='button' onclick='brower(this)' value ='浏览' class='btn'/><input type='button' value='删除' onclick='drop(this)' class='btn'/><input type='button' onclick='edit(this)' value='编辑' class='btn'/></td></tr>";
			
				
				console.log(resultdata);
				$(obj).parent().parent().after(tr);
				//pageCalculate(resultdata,pageSize);
				//firstPage(resultdata,pageSize);//显示首页
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}

function clearTableContent(){//清除表格中的数据
	$("#showInform tbody tr").remove();
}
			
function addTableContent(tr){//将数据加入表格中
	$("#showInform tbody").append($(tr));
} 
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

function addDictData(data,obj){
	var tr = "<tr class='sub'><td colspan='4'><table class='subTable'>" 
	tr += "<tr><th width='7%'>序号</th><th width='17%'>产品大类编码</th><th width='45%'>产品大类名称</th><th>操作</th></tr>";
	for(var i=0;i<data.length;i++){
		tr += "<tr><td>"+i+"</td><td style='display:none'>"+data[i].DDTID+"</td><td>"+data[i].DDTCODE+"</td><td>"+data[i].DDTNAME+"</td>";
		tr += "<td><input type='button' value='删除' onclick='drop(this)' class='btn'/><input type='button' onclick='edit(this)' value='编辑' class='btn'/></td></tr>";
	
	}
	tr += "</table></td><tr>";	
		
	console.log(tr);
	$(obj).parent().parent().after(tr);
}
