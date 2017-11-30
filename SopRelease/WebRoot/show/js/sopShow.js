

function createWebSocket(url) {
    try {
        ws = new WebSocket(encodeURI(url));
        initEventHandle();
    } catch (e) {
        reconnect(url);
    }     
}
function reconnect(url) {
    if(lockReconnect) return;
    lockReconnect = true;
    //没连接上会一直重连，设置延迟避免请求过多
    setTimeout(function () {
        createWebSocket(url);
        lockReconnect = false;
    }, 2000);
}
//心跳检测
var heartCheck = {
    timeout: 60000,//60秒
    timeoutObj: null,
    serverTimeoutObj: null,
    reset: function(){
        clearTimeout(this.timeoutObj);
        clearTimeout(this.serverTimeoutObj);
        return this;
    },
    start: function(){
        var self = this;
        this.timeoutObj = setTimeout(function(){
            //这里发送一个心跳，后端收到后，返回一个心跳消息，
            //onmessage拿到返回的心跳就说明连接正常
            ws.send("HeartBeat");
            self.serverTimeoutObj = setTimeout(function(){//如果超过一定时间还没重置，说明后端主动断开了
                ws.close();//如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
            }, self.timeout)
        }, this.timeout)
    }
}

function initEventHandle() {
    ws.onclose = function () {
    	$("msg").text("连接发生错误");
    	$("#stata_t").html("异常");
    	errorConnection("通讯连接发生错误！");
		console.log("连接发生错误");
        reconnect(wsUrl);
    };
    ws.onerror = function () {
        reconnect(wsUrl);
    };
    ws.onopen = function () {
    	// 发送一个初始化消息
    	//ws.send("I am the client and I\'m listening!");
    	$("#errorMessage").css('visibility', 'hidden');
		//连接成功
		//win.setTitle(title + '&nbsp;&nbsp;(已连接)');
		$("msg").text("连接成功");
		$("#stata_t").html("正常");
		console.log("连接成功");
        //心跳检测重置
        //heartCheck.reset().start();
    };
    ws.onmessage = function (message) {
        //如果获取到消息，心跳检测重置
    	//heartCheck.reset().start();
        //拿到任何消息都说明当前连接是正常的
		var flag = message.data.substr(0, 1);
		if(flag == "1"){
			var barCode = message.data.substr(1);
			test(barCode);
			//向服务端返回信息
			//ws.send("2"+message.data.substr(1));
		}
		
		//test(message.data);  
    }
}

function subjectDefectiveInit(){
	$.ajax({
		url:"../product/findSubjectDefectiveSel.do",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var resultdate = result.data;
				var li = "";
				for(var i=0;i<resultdate.length;i++){
					if(i==0){
						li +="<option value='"+ resultdate[i].DDT_CODE +"' selected='selected'>"+ resultdate[i].DDT_NAME +"</option>";
					}else{
						li +="<option value='"+ resultdate[i].DDT_CODE+"'>"+ resultdate[i].DDT_NAME +"</option>";
					}
					
				}
               var $li=$(li);
               //藏值
               //$li.data("bookId",bookId);
               //将td添加到div中
               $("#defective_type").empty(); 
               $("#defective_type").append($li);
			}
			
		},
		error:function(){
			console.log("操作失败");
		}
		
	});
}
function test(barCodeString){
	
	//var barCode = barCodeString.trim();
	 var barCode = barCodeString.replace(/[\r\n]/g, "").trim(); //去掉空格
     
	var ipAddress = $("#ipAddress").text();
	
	var flagP = barCode.substr(0, 1);
	var arr = barCode.split('#');
	var barCodeN = barCode.substr(1);
	if(flagP == 'P'){
		if (isNaN(barCodeN)){
			errorConnection("扫码过快！");
			return false;
		}
		//如果该产品流程卡已存在
		console.log(productBarCode);
		if(barCode == productBarCode){
			errorConnection("流程卡已获取");
			return false;
		}else{
			productBarCode = barCode;
		}
		//var ipAddress = $("#ipAddress").text();
		//清除表单历史数据
		$("#bomCode").html("");
		console.log(barCode);
		$("#processnum").html(barCode);
		//下一任务单展示
		console.log(ipAddress);
		//loadNeedSop(barCode,ipAddress);
		//savePassStation('12345');
		loadProductMessage(barCode,ipAddress);
		//loadNextProduct(barCode);
		//保存过站信息放在产品查询信息成功方法中
	//}else if(flagP == 'M'){
	}else if(flagP == 'E'){
		//主观不良
		if(productBarCode==""){
			errorConnection("流程卡未扫码！");
			return false;
		}
		defectiveBarCode = barCode;
		$( "#dialog-defective" ).dialog("open");
		//barCode
		
		//savePass
	}else if(arr.length == 5){
	
		
		//保存到产品过站信息上。
		//找到需要绑定的产品流程卡：ip地址+当前流程卡+最新时间。更新！
		//一个是物料编码，一个是产品编码
		if(productBarCode==""){
			errorConnection("流程卡未扫码！");
			return false;
		}
		//判断是否已绑定物料编码
		//如果已经绑定，则提示
		if(materialBarCode!=""){
			errorConnection("已绑定物料，覆盖绑定请重新扫码！");
			return false;
		}
		
		//$("#bomCode").html(barCode);
		$("#bomCode").html(arr[4]);
		//用来进行防止重复扫描物料的判断
		/*if(barCode == mBarCode){
			errorConnection("流程卡已获取");
			return false;
		}else{
			productBarCode = barCode;
		}*/
		saveMaterielMsg(barCode,productBarCode,ipAddress);
	}//四大关键原物料规则不统一，无法统一判断，不满足流程卡规则的默认为物料编码
	 else{
		
		//保存到产品过站信息上。
		//找到需要绑定的产品流程卡：ip地址+当前流程卡+最新时间。更新！
		//一个是物料编码，一个是产品编码
		if(productBarCode==""){
			errorConnection("流程卡未扫码！");
			return false;
		}
		//$("#bomCode").html(barCode);
		$("#bomCode").html("已获取");
		//用来进行防止重复扫描物料的判断
		/*if(barCode == mBarCode){
			errorConnection("流程卡已获取");
			return false;
		}else{
			productBarCode = barCode;
		}*/
		saveMaterielMsg(barCode,productBarCode,ipAddress);
	}

	//ws.send("2"+serMessage.substr(1));
}
//保存主观不良
function saveDecfectiveBarcode(){
	var type = $('#defective_type option:selected').text();
	//var defectiveBarCode = "";
	//var productBarCode
	var ipAddress = $("#ipAddress").text();

	var obj = new Object();
	//obj.passTime = time;
	obj.ipAddress = ipAddress;
	obj.productBarCode = productBarCode;
	obj.defectiveBarCode = defectiveBarCode;
	obj.defectiveType = type;
	var formData = obj;
	console.log(formData);
	
	$.ajax({
  		url:"../product/saveDecfectiveMsg.do",
		type:"post",
		data:formData,
		dataType:"json",
		success:function(result){
			
			if(result.status == 0){
				//var resultdata = result.data;
				
			}else{
				console.log("error:"+result.msg);				
			}
		},
		error:function(){
			errorConnection("服务器出错");
		}
	});
}
//保存物料信息
function saveMaterielMsg(barCode,productBarCode,ipAddress){
	//获取工位名称
	var procedureName = $("#procedureName").text();
	console.log("所在工位名称："+procedureName);
	var obj = new Object();
	//obj.passTime = time;
	obj.barCode = barCode;
	obj.productBarCode = productBarCode;
	obj.ipAddress = ipAddress;
	obj.procedureName = procedureName;
	var formData = obj;
	console.log(formData);
	
	$.ajax({
  		url:"../product/saveMaterielMsg.do",
		type:"post",
		data:formData,
		dataType:"json",
		success:function(result){
			
			if(result.status == 0){
				var resultdata = result.data;
				
			}else{
				console.log("error:"+result.msg);				
			}
		},
		error:function(){
			errorConnection("服务器出错");
		}
	});
}






//function savePassMeterial(barCode)
function loadNextProduct(barcode){
	$.ajax({
		url:"../product/findNextProducts.do",
		type:"post",
		//
		data:{"barCode":barcode},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var productdata = result.data;
				/*if(productdata.toString() == result.data.toString()){
					//如果此次查询和上一次结果相同：
					//那么界面保持不变，当前订单数量减一。
					console.log("和上次一样");
					count++;*/
				console.log("还剩0："+productdata[0].PORDNUM);
					var currentNum = parseInt(productdata[0].PORDNUM);
					var ipAddress = $("#ipAddress").text();
					var ipnum = parseInt(ipAddress.substring(ipAddress.lastIndexOf('.')+1));
					console.log("还剩1："+currentNum);
					console.log("还剩2："+ipnum);
					if(currentNum != 0 ){
						
						currentNum = currentNum + ipnum;
						console.log("还剩："+currentNum+"台");
					}
					/*//$("#currentNum").html(currentNum-1);
					 if(currentNum == count){
						 count = 0;
					 }*/
				/*}else {
					productdata = result.data;
					console.log(productdata.length);*/
					var len = result.data.length;
					if(len == 1){
						var li =  "<tr class='msg_first message_text'>";
						li += "<td class='msg_firsta'></td>";
						li += "<td class='msg_firstb'></td>";
						li += "<td class='msg_firstc'></td>";
						li += "</tr>";
						li += "<tr class='msg_second message_text'>";
						li += "<td class='msg_second1'></td>";
						li += "<td class='msg_second1'></td>";
						li += "<td class='msg_second1'></td>";
						li += "</tr>";
						li += "<tr class='msg_third message_text'>";
						li += "<td class='msg_third1'></td>";
						li += "<td class='msg_third1'></td>";
						li += "<td class='msg_third1'></td>";
						li += "</tr>";
						li += "<tr class='msg_fourth message_text'>";
						li += "<td class='msg_fourth1'><img src='images/right_arrow.png' alt=''>"+productdata[0].PRODID+"</td>";
						li += "<td class='msg_fourth1'>"+productdata[0].PRODUCTCODE+"</td>";
						li += "<td class='msg_fourth1' id='currentNum'>"+currentNum+"<img src='images/left_arrow.png' alt=''></td>";
						li += "</tr>";
						$("#pordTable tbody").html("");
						$("#pordTable tbody").append(li);
					}else if(len == 2){
						var li =  "<tr class='msg_first message_text'>";
						li += "<td class='msg_firsta'></td>";
						li += "<td class='msg_firstb'></td>";
						li += "<td class='msg_firstc'></td>";
						li += "</tr>";
						li += "<tr class='msg_second message_text'>";
						li += "<td class='msg_second1'></td>";
						li += "<td class='msg_second1'></td>";
						li += "<td class='msg_second1'></td>";
						li += "</tr>";
						li += "<tr class='msg_third message_text'>";
						li += "<td class='msg_third1'>"+productdata[1].PRODID+"</td>";
						li += "<td class='msg_third1'>"+productdata[1].PRODUCTCODE+"</td>";
						li += "<td class='msg_third1'>"+productdata[1].PORDNUM+"</td>";
						li += "</tr>";
						li += "<tr class='msg_fourth message_text'>";
						li += "<td class='msg_fourth1'><img src='images/right_arrow.png' alt=''>"+productdata[0].PRODID+"</td>";
						li += "<td class='msg_fourth1'>"+productdata[0].PRODUCTCODE+"</td>";
						li += "<td class='msg_fourth1' id='currentNum'>"+currentNum+"<img src='images/left_arrow.png' alt=''></td>";
						li += "</tr>";
						$("#pordTable tbody").html("");
						$("#pordTable tbody").append(li);
					}else if(len == 3){
						var li =  "<tr class='msg_first message_text'>";
						li += "<td class='msg_firsta'></td>";
						li += "<td class='msg_firstb'></td>";
						li += "<td class='msg_firstc'></td>";
						li += "</tr>";
						li += "<tr class='msg_second message_text'>";
						li += "<td class='msg_second1'>"+productdata[2].PRODID+"</td>";
						li += "<td class='msg_second1'>"+productdata[2].PRODUCTCODE+"</td>";
						li += "<td class='msg_second1'>"+productdata[2].PORDNUM+"</td>";
						li += "</tr>";
						li += "<tr class='msg_third message_text'>";
						li += "<td class='msg_third1'>"+productdata[1].PRODID+"</td>";
						li += "<td class='msg_third1'>"+productdata[1].PRODUCTCODE+"</td>";
						li += "<td class='msg_third1'>"+productdata[1].PORDNUM+"</td>";
						li += "</tr>";
						li += "<tr class='msg_fourth message_text'>";
						li += "<td class='msg_fourth1'><img src='images/right_arrow.png' alt=''>"+productdata[0].PRODID+"</td>";
						li += "<td class='msg_fourth1'>"+productdata[0].PRODUCTCODE+"</td>";
						li += "<td class='msg_fourth1' id='currentNum'>"+currentNum+" <img src='images/left_arrow.png' alt=''></td>";
						li += "</tr>";
						$("#pordTable tbody").html("");
						$("#pordTable tbody").append(li);
					}else if(len == 4){
						var li =  "<tr class='msg_first message_text'>";
						li += "<td class='msg_firsta'>"+productdata[3].PRODID+"</td>";
						li += "<td class='msg_firstb'>"+productdata[3].PRODUCTCODE+"</td>";
						li += "<td class='msg_firstc'>"+productdata[3].PORDNUM+"</td>";
						li += "</tr>";
						li += "<tr class='msg_second'>";
						li += "<td class='msg_second1'>"+productdata[2].PRODID+"</td>";
						li += "<td class='msg_second1'>"+productdata[2].PRODUCTCODE+"</td>";
						li += "<td class='msg_second1'>"+productdata[2].PORDNUM+"</td>";
						li += "</tr>";
						li += "<tr class='msg_third'>";
						li += "<td class='msg_third1'>"+productdata[1].PRODID+"</td>";
						li += "<td class='msg_third1'>"+productdata[1].PRODUCTCODE+"</td>";
						li += "<td class='msg_third1'>"+productdata[1].PORDNUM+"</td>";
						li += "</tr>";
						li += "<tr class='msg_fourth'>";
						li += "<td class='msg_fourth1'><img src='images/right_arrow.png' alt=''> "+productdata[0].PRODID+"</td>";
						li += "<td class='msg_fourth1'>"+productdata[0].PRODUCTCODE+"</td>";
						li += "<td class='msg_fourth1' id='currentNum'>"+currentNum+"<img src='images/left_arrow.png' alt=''></td>";
						li += "</tr>";
						$("#pordTable tbody").html("");
						$("#pordTable tbody").append(li);
					}else{
						var li =  "<tr class='msg_first message_text'>";
						li += "<td class='msg_firsta'></td>";
						li += "<td class='msg_firstb'></td>";
						li += "<td class='msg_firstc'></td>";
						li += "</tr>";
						li += "<tr class='msg_second message_text'>";
						li += "<td class='msg_second1'></td>";
						li += "<td class='msg_second1'></td>";
						li += "<td class='msg_second1'></td>";
						li += "</tr>";
						li += "<tr class='msg_third message_text'>";
						li += "<td class='msg_third1'></td>";
						li += "<td class='msg_third1'></td>";
						li += "<td class='msg_third1'></td>";
						li += "</tr>";
						li += "<tr class='msg_fourth message_text'>";
						li += "<td class='msg_fourth1'><img src='images/right_arrow.png' alt=''></td>";
						li += "<td class='msg_fourth1'></td>";
						li += "<td class='msg_fourth1' id='currentNum'><img src='images/left_arrow.png' alt=''></td>";
						li += "</tr>";
						$("#pordTable tbody").html("");
						$("#pordTable tbody").append(li);
					}
				//}
				
				
				//window.opener.location.replace(window.opener.document.referrer);window.close();
				//alert(result.msg);
				//window.location.href="stationConfig.jsp";
				//loadDayNotes();
				
			}else if(result.status==1){
				
			}
		},
		error:function(){
			errorConnection("服务器异常！");
		}
		
	});
}
function loadIpSopStation(){
	var ipAddress = $("#ipAddress").text();
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
			
				$("#procedureNo").text(resultdate.PROCEDURENO);
				$("#userName").text(resultdate.USERID);
				
				//window.opener.location.replace(window.opener.document.referrer);window.close();
				//alert(result.msg);
				//window.location.href="stationConfig.jsp";
				//loadDayNotes();
				
			}
		},
		error:function(){
			errorConnection("服务器异常！");
		}
		
	});
}
function savePassProduct(barCode,pordId){
	
	//ip+userCode+sopVersion+
	var ipAddress = $("#ipAddress").text();
	var sopVersion = $("#sopVersion").text();
	var userCode = $("#userName").text();
	var productCode = $("#productCode").text();
	
	var obj = new Object();
	obj.barCode = barCode;
	obj.pordId = pordId;
	obj.ipAddress = ipAddress;
	obj.sopVersion = sopVersion;
	obj.productCode = productCode;
	obj.userCode = userCode;
	var formData = obj;
	console.log("savePassProduct:"+formData);
	
	$.ajax({
  		url:"../product/savePassProduct.do",
		type:"post",
		data:formData,
		dataType:"json",
		success:function(result){
			
			if(result.status == 0){
				var resultdata = result.data;
				//下一产品展示
				loadNextProduct(barCode);
			}else if(result.status == 1){
				//出现错误
				errorConnection(result.msg);
				//console.log("error:"+result.msg);
				
			}
		},
		error:function(){
			errorConnection("服务器异常！");
		}
	});
}
function loadProductMessage(barCode,ipAddress){
	var procedureNo = $("#procedureNo").text();
	console.log("procedureNo:"+procedureNo);
	/*var obj = new Object();
	obj.barCode = barCode;
	obj.ipAddress = ipAddress;
	var formData = obj;*/
	var obj = new Object();
	obj.barCode = barCode;
	obj.ipAddress = ipAddress;
	var formData = obj;
	console.log(formData);
	$.ajax({
  		url:"../sop/findMessage.do",
		type:"post",
		data:formData,
		dataType:"json",
		success:function(result){
			
			if(result.status == 0){
				var resultdata = result.data;
				console.log(resultdata);
				if(resultdata.procedureGroup == "" || resultdata.procedureGroup==undefined ){
					errorConnection("请配置工序");
				}else{
					//工序号和工序名称多组展示
					/*$("#procedureName").text(resultdata.procedureName);
					$("#procedureNo").text(resultdata.procedureNo);*/
					$("#procedureName").text(resultdata.procedureGroup[0].procedureName);
					$("#procedureNo").text(resultdata.procedureGroup[0].procedureNo);
					
					$("#userName").html(resultdata.userId);
					var procedureGroup = resultdata.procedureGroup;
					console.log(procedureGroup);
					var procedureNos = new Array();
					for(var i=0;i<procedureGroup.length;i++){
						procedureNos[i] = procedureGroup[i].procedureNo;
					}
					//全局变量记住工序组
					pub_procedureGroup = procedureNos.toString();
					console.log(procedureNos);
					//loadNeedSop(barCode,ipAddress,resultdata.pordId);
					loadNeedSopPlus(procedureNos.toString(),resultdata.productCode,barCode,resultdata.pordId);
				}
				
				$("#productCode").html(resultdata.productName);
				$("#code").text(resultdata.productCode);
				//$("#productId").html(resultdata.PRODUCTCODE);
				//$("#bomCode").val("bomCode");
				//$("#bomName").val("bomName");
				//addSopImage(resultdata.pdglist);
				
				//savePassProduct(barCode,resultdata.PORDID);
				//显示在右侧
			}
		},
		error:function(){
			errorConnection("未找到任务单！");
		}
	});
}
function loadNeedSopPlus(procedureNos,productCode,barCode,pordId){
	var obj = new Object();
	obj.procedureNos = procedureNos;
	obj.productCode = productCode;
	var formData = obj;
	console.log(formData);
	$.ajax({
  		url:"../sop/findSopPlus.do",
		type:"post",
		data:formData,
		dataType:"json",
		success:function(result){
			
			if(result.status == 0){
				var resultdata = result.data;
				//版本号
				var versionNo = resultdata.versionNo;
				$("#sopVersion").text(versionNo);
				//下一版本号
				var nextVersion = resultdata.nextVersion;
				if(nextVersion != 0){
					$("#nextVersion").show();//显示div    
				}else{
					$("#nextVersion").hide();
				}
				//与上一次做比较
				if(imageList == resultdata.pdglist){
					return false;
				}else{
					imageList = resultdata.pdglist;
					addSopImage(resultdata.pdglist);
				}
				savePassProduct(barCode,pordId);
				
			}else{
				console.log(result.msg);
				//清空sop
				errorSopImage("流程卡出错！");
			}
		},
		error:function(){
			errorConnection("程序出错");
		}
	});
/*function loadNeedSop(barCode,ipAddress,pordId){
	var obj = new Object();
	obj.barCode = barCode;
	obj.ipAddress = ipAddress;
	var formData = obj;
	console.log(formData);
	$.ajax({
  		url:"../sop/findSop.do",
		type:"post",
		data:formData,
		dataType:"json",
		success:function(result){
			
			if(result.status == 0){
				var resultdata = result.data;
				//版本号
				var versionNo = resultdata.versionNo;
				$("#sopVersion").text(versionNo);
				//下一版本号
				var nextVersion = resultdata.nextVersion;
				if(nextVersion != 0){
					$("#nextVersion").show();//显示div    
				}else{
					$("#nextVersion").hide();
				}
				//与上一次做比较
				if(imageList == resultdata.pdglist){
					return false;
				}else{
					imageList = resultdata.pdglist;
					addSopImage(resultdata.pdglist);
				}
				savePassProduct(barCode,pordId);
				
			}else{
				console.log(result.msg);
				//清空sop
				errorSopImage("流程卡出错！");
			}
		},
		error:function(){
			errorConnection("程序出错");
		}
	});*/
	
	
}


function addSopImage(data){
	$("#errorMessage").css('visibility', 'hidden');
	$("#showlist").html("");
	var li = "";
	for(var i=0;i<data.length;i++){
		var img_url = "../../../mes/sopImages"+data[i].PDGPATH+"/"+data[i].SYSNAME;
		li += "<li><img src='"+img_url+"'></li>";  
	} 
	$("#showlist").append(li);
	rnum=data.length;
	console.log("size:"+rnum);
	//var sop_width = 1300;//588
	//var cnum=0;
	$(".mke_ns2").html(rnum);
	$(".mke_ns1").html(cnum+1);
	$(".mkeUl ul").width(rnum*sop_width);
	//$("#errorMessage").hide();
}
function errorSopImage( msg){
	//$("#errorMessage").show();
	//展示错误信息
	$("#errorMessage").css('visibility', 'visible');
	$("#showlist").html("");
	var li = "<li><img src='../show/images/sop_error.png'></li>";
	
	$("#showlist").append(li);
	rnum = 0;
	$(".mke_ns2").html(rnum);
	$(".mke_ns1").html(cnum);

	$(".mkeUl ul").width(rnum*sop_width);
	$("#exception").html(msg);

}

function errorConnection( msg){
	//$("#errorMessage").show();
	//展示错误信息
	$("#errorMessage").css('visibility', 'visible');
	$("#exception").html(msg);

}

function showTime(){
	//setInterval(function(){trace("每隔1秒钟我就会显示一次")},1000);
	setInterval(function(){
		fnDate();},1000);
}
//js 获取当前时间
function fnDate(){
	//var oDiv=document.getElementById("time");
	var date=new Date();
	var year=date.getFullYear();//当前年份
	//alert("ss"+year);
	var month=date.getMonth();//当前月份
	var data=date.getDate();//天数
	var hour=date.getHours();//小时
	var minute=date.getMinutes();//分
	var second=date.getSeconds();//秒
	//var time = year+"/"+(month+1)+"/"+data+" "+hour+":"+minut+":"+second;
	var time=year+"/"+fnW((month+1))+"/"+fnW(data)+" "+fnW(hour)+":"+fnW(minute)+":"+fnW(second);
	//alert(date.toLocaleString());
	//alert("ss");
	$("#time").html(time);
	//oDiv.innerHTML=time;
}
//补位 当某个字段不是两位数时补0
function fnW(str){
	var num;
	str>=10?num=str:num="0"+str;
	return num;
}

//弹出工序注册窗口
function openwin() {
	var productCode = $("#code").text();
	console.log(productCode);
	var productName = $("#productCode").text();
	//var procedureNo = $("#procedureNo").text();
	//获取工序组
	var procedureNo = pub_procedureGroup;
	var userId = $("#userName").text();
	console.log(procedureNo);
	window.open ("../basic/updateStation.jsp?productCode="+productCode+"&procedureNo="+procedureNo+"&userId="+userId+
			"","工位配置", "height=854, width=800, top=100, left=100,toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	//var procedure = "";
}

//修改图片大小
/*function AlterImage(img){
console.log('imgOld---width:'+img.width+',height:'+img.height);
var windowW = 1440;
var windowH = 960;
if (img.width>windowW || img.height>windowH){
	if (img.width/img.height>windowW/windowH){
		img.width=windowW
	}else{
		img.height=windowH;
	}
}
return img;
}*/

//浏览器大小的自动调节
/*window.onresize = function(){
// alert(1);
	console.log("放大");
}*/


//查看网络连接状态
/*function testOnline(){
	console.log("in");
	if(navigator.onLine){
		console.log("已连接"); 
		$("#stata_t").html("正常");
	}else{
		console.log("已断开");
		$("#stata_t").html("异常");
	}
}*/

/*$("#submit").click(function(){
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
});*/

//实时监听网络连接状态
/*
var EventUtil = { 
	addHandler: function (element, type, handler) { 
		if (element.addEventListener) { 
			element.addEventListener(type, handler, false); 
		} else if (element.attachEvent) { 
			element.attachEvent("on" + type, handler); 
		} else { 
			element["on" + type] = handler; 
		} 
	} 
}; 

EventUtil.addHandler(window, "online", function () { 
	console.log("Online"); 
	$("#stata_t").html("正常");
}); 
EventUtil.addHandler(window, "offline", function () { 
	console.log("Offline"); 
	$("#stata_t").html("异常");
}); */

/*function getRootPath() {
	//获得根目录
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath); 
  var prePath = strFullPath.substring(0, pos); 
   var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1); 
   return (prePath + postPath);}*/

