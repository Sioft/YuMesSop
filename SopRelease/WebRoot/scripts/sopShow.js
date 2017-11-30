//WEB socket
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

$(function(){
	
	var bannerSlider = new Slider($('#banner_tabs'), {
		time: 5000,
		delay: 400,
		event: 'hover',
		auto: true,
		mode: 'fade',
		controller: $('#bannerCtrl'),
		activeControllerCls: 'active'
	});
	$('#banner_tabs .flex-prev').click(function() {
		bannerSlider.prev()
	});
	$('#banner_tabs .flex-next').click(function() {
		bannerSlider.next()
	});
	/*$('#demo1').slideBox({
		direction : 'left',//left,top#方向
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'swing',//swing,linear//滚动特效
		delay : 2,//滚动延迟时间，单位：秒
		startIndex : 0,//初始焦点顺序
		hideBottomBar : false,//隐藏底栏
		hideClickBar : false,//不自动隐藏点选按键
	});*/
	 $("#find").on('click', function(){ 
    	var productCode = $("#productCode").val();
    	//工序序号：根据产品和工序序号确定自己的sop
    	var procedureNo = $("#procedureNo").val();
    	console.log(productCode);
    	loadNeedSop('001','ZF-1');
   
    });
});

function loadNeedSop(stationCode,productCode){
	var obj = new Object();
	obj.productCode = productCode;
	obj.stationCode = stationCode;
	var formData = obj;
	console.log(formData);
	$.ajax({
  		url:"../sop/sop/findSop.do",
		type:"post",
		data:formData,
		dataType:"json",
		success:function(result){
			
			if(result.status == 0){
				var resultdata = result.data;
				console.log(resultdata);
				//版本号
				var versionNo = resultdata.versionNo;
				$("#version").text(versionNo);
				//下一版本号
				var nextVersion = resultdata.nextVersion;
				if(nextVersion != 0){
					$("#nextVersion").show();//显示div    
				}else{
					$("#nextVersion").hide();
				}
				addSopImage(resultdata.pdglist);
				
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
	
	
}
function AlterImage(img){
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
}
function getRootPath() {
	//获得根目录
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath); 
	  var prePath = strFullPath.substring(0, pos); 
	   var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1); 
	   return (prePath + postPath);
	}
function addSopImage(data){
	$("#showlist").html("");
	$("#bannerCtrl").html("");
	var li = "";
	var libtr = "";
	//var sUrl = [];
	if(data.length == 1){
		var img_url = "upload"+data[0].PDGPATH+"/"+data[0].SYSNAME;
		li = "<li><img src='"+img_url+"'></li>";
		//bannerSlider.stop();
	}else{
		for(var i=0;i<data.length;i++){
			var img_url = "upload"+data[i].PDGPATH+"/"+data[i].SYSNAME;
			//获得项目根路径
			var path = getRootPath();
			//console.log(path);
			//获取图片的宽高
			//var img = new Image();
			// 改变图片的src3608813441.jpg)
			//img.src = path+"/"+img_url+"?t="+Math.random();
			libtr += "<li><a>"+(i+1)+"</a></li>";
			li += "<li><img src='"+img_url+"'></li>";  
			//测试
			//sUrl[i] = path+"/"+img_url+"?t="+Math.random();
			//console.log(sUrl[i]);
			/*$(img).attr('src', sUrl[i]).load(function() {
				console.log(this.src);
		        var width = this.width; //你要的宽
		        var height = this.height; //你要的高
		        console.log('<img/>width:'+width+',height:'+height);
		        AlterImage(this);
		        console.log('<img/new>width:'+this.width+',height:'+this.height);
		        var li = "<li><img width='"+this.width+"px' height='"+this.height+"px' src='"+this.src+"'></li>";  
				
				console.log("showlist"+li);
				
				$("#showlist").append($(li));
				*/
		       // $(document.body).append(this); //直接插入网页
		        //如果你还要对这个元素进行一些位置方面的调整，在这里进行
		    //});
			//console.log("图片路径"+img.src);//?'+Date.parse(new Date());//防止缓存可以加上时间
			//console.log('width:'+img.naturalWidth+',height:'+img.naturalHeight);
			//var imgNew = new Image();
			//img.onload = function() {
			//imgNew = AlterImage(img);
			
			
			//}
			//console.log('imgNew---width:'+imgNew.width+',height:'+imgNew.height);
			
			//img.offsetWidth;
		    //img.offsetHeight;
			//console.log('width:'+img.width+',height:'+img.width);
			//var imgNew = AlterImage(img);
			
			
			//调整图片的大小
			/*li += "<li><img width='700px' height='300px' src='upload"+data[i].PDGPATH+"/"+data[i].SYSNAME+"'></li>";*/
			
		} 
	}
	
	console.log(li);
	$("#showlist").append(li);
	//$("#showlist").append(li);
	console.log(libtr);
	$("#bannerCtrl").append(libtr);
	//$("#bannerCtrl").append(libtr);
	var bannerSlider = new Slider($('#banner_tabs'), {
		time: 3000,
		delay: 400,
		event: 'hover',
		auto: true,
		mode: 'slide',
		controller: $('#bannerCtrl'),
		activeControllerCls: 'active'
	});
}

/*jQuery(function($){
	
	$('#demo6').slideBox();
	
	$('#demo3').slideBox({
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'linear',//swing,linear//滚动特效
		delay : 5,//滚动延迟时间，单位：秒
		hideClickBar : false,//不自动隐藏点选按键
		clickBarRadius : 10
	});
	$('#demo4').slideBox({
		hideBottomBar : true//隐藏底栏
	});
});*/