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
function getRootPath() {
	//获得根目录
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath); 
	  var prePath = strFullPath.substring(0, pos); 
	   var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1); 
	   return (prePath + postPath);
	}	