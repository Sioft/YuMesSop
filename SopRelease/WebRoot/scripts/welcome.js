	
function shake(element,className,times){
    var i = 0,
        t = false ,
        o = element.attr("class"),
        c = "",
        times = times||2;

    if(t) return;

    t = setInterval(function(){
      i++;
      c = i%2 ? o + ' ' + className : o;
      element.attr("class",c);

      if(i==2*times){
        clearInterval(t);
        element.removeClass(className);
        }
      },200);

    };
    
 function changeVersionImage(data){
	 //修改生效时间
	 $("#valid_time").text(data[0].VALIDTIME);
	 //修改demo1div中的图片列表
	 var li="";
	 for(var i=0;i<data.length;i++){
		 //
		 li += "<li><img width='700px' height='300px' src='../upload"+data[i].PDGPATH+"/"+data[i].SYSNAME+"'></li>";		 
	 }
	 
	 //清空ul()image_sop
	 $("#image_sop").html("");
	 //追加li
	 console.log(li);
	 $("#image_sop").append($(li));
	 
	 $("#demo1").slideBox({
			direction : 'left',//left,top#方向
			duration : 0.3,//滚动持续时间，单位：秒
			easing : 'swing',//swing,linear//滚动特效
			delay : 2,//滚动延迟时间，单位：秒
			startIndex : 0,//初始焦点顺序
			hideBottomBar : false,//隐藏底栏
			hideClickBar : false,//不自动隐藏点选按键
	});
 }
 
 function deleteVersionSop(productCode,procedureNo,versionNo){
	var obj = new Object();
	obj.productCode = productCode;
	obj.procedureNo = procedureNo;
	obj.versionNo = versionNo;
	var formData = obj;
	console.log(formData);
	$.ajax({
  		url:"../sop/deleteVersionSop.do",
		type:"post",
		data:formData,
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				//resultdata = result.data;
				//console.log("pdglist:"+resultdata);
				//changeVersionImage(resultdata);
				alert(result.msg);
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
 }
    
$(function(){
	//文字闪烁
 	shake($(".box"),"red",10);
	
 	$("#uploadnew").on('click', function(){
		//产品大类
    	var productCode = $("#productCode").text();
    	//工序序号：根据产品和工序序号确定自己的sop
    	var procedureNo = $("#procedureNo").text();
    	//生效时间
    	//var validTime = $("#datetimepicker").val();
    	//版本号
    	var versionNo = $("#versionNo").text();
    	//相对路径
    	//产品大类路径：在上传图片时使用，确定文件夹结构
    	var nodePath = $("#nodePath").text();
		var hrefUrl = "upload_plus.jsp?productCode="+productCode+"&nodePath="+nodePath+
		"&procedureNo="+procedureNo+"&versionNo="+versionNo;
		console.log(hrefUrl);
		top.page.location.href = hrefUrl;
	});
 	$("#delete").on('click', function(){
    	//版本号:当前selete框选中
    	//$('#testSelect option:selected').text();//选中的文本
		//$('#testSelect option:selected') .val();//选中的值
    	var versionNo = $("#version_no option:selected").val();
    	//产品大类
    	var productCode = $("#productCode").text();
    	//工序序号：根据产品和工序序号确定自己的sop
    	var procedureNo = $("#procedureNo").text();
    	if(confirm("确定删除第"+versionNo+"版本"))   //弹出确认删除对话框  
    	{	
    		deleteVersionSop(productCode,procedureNo,versionNo);
    	//  return true;    //点击确定时，返回值为true，执行如上操作。
    	}
    	else
    	{
    	   return false; //点击取消时，返回值为false，不执行操作。
    	}
   
	});
 	
 	$("#version_no").change(function(){
 		var productCode = $("#productCode").text();
    	//工序序号：根据产品和工序序号确定自己的sop
    	var procedureNo = $("#procedureNo").text();
    	//生效时间
 		var versionNo = $("#version_no option:selected").val();
	  	//根据版本号查询所需sop
 		var obj = new Object();
 		obj.productCode = productCode;
 		obj.procedureNo = procedureNo;
 		obj.versionNo = versionNo;
 		var formData = obj;
 		console.log(formData);
 		$.ajax({
 	  		url:"../sop/findVersionSop.do",
 			type:"post",
 			data:formData,
 			dataType:"json",
 			success:function(result){
 				if(result.status == 0){
 					resultdata = result.data;
 					console.log("pdglist:"+resultdata);
 					changeVersionImage(resultdata);
 				}else{
 					alert(result.msg);
 				}
 			},
 			error:function(){
 				alert("程序出错");
 			}
 		});
	});
 	
});

/*
jQuery(function($){
	$('.demo').slideBox({
		direction : 'left',//left,top#方向
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'swing',//swing,linear//滚动特效
		delay : 6,//滚动延迟时间，单位：秒
		startIndex : 0,//初始焦点顺序
		hideBottomBar : false,//隐藏底栏
		hideClickBar : false,//不自动隐藏点选按键
	});
	$('#demo6').slideBox();
	$('#demo1').slideBox({
		direction : 'left',//left,top#方向
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'swing',//swing,linear//滚动特效
		delay : 6,//滚动延迟时间，单位：秒
		startIndex : 0,//初始焦点顺序
		hideBottomBar : false,//隐藏底栏
		hideClickBar : false,//不自动隐藏点选按键
	});
	$('#demo2').slideBox({
		direction : 'left',//left,top#方向
		duration : 0.3,//滚动持续时间，单位：秒
		easing : 'swing',//swing,linear//滚动特效
		delay : 6,//滚动延迟时间，单位：秒
		startIndex : 0,//初始焦点顺序
		hideBottomBar : false,//隐藏底栏
		hideClickBar : false,//不自动隐藏点选按键
	});
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