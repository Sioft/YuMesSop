	
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
 	
 	loadSopAllProce();
 			
 	
 		/*$('#banner_tabs .flex-prev').click(function() {
 			bannerSlider.prev()
 		});
 		$('#banner_tabs .flex-next').click(function() {
 			bannerSlider.next()
 		});*/
 		/*$('#demo1').slideBox({
 			direction : 'left',//left,top#方向
 			duration : 0.3,//滚动持续时间，单位：秒
 			easing : 'swing',//swing,linear//滚动特效
 			delay : 2,//滚动延迟时间，单位：秒
 			startIndex : 0,//初始焦点顺序
 			hideBottomBar : false,//隐藏底栏
 			hideClickBar : false,//不自动隐藏点选按键
 		});*/
});

function loadSopAllProce(){
	var productCode = $("#productCode").text();
	$.ajax({
  		url:"../sop/findSopAllProce.do",
		type:"post",
		data:{"productCode":productCode},
		dataType:"json",
		success:function(result){
			console.log(result.status);
			if(result.status == 0){
				console.log(result.data);
				createTable(result.data);
				/*var bannerSlider = new Slider($('#banner_tabs'), {
			 		time: 5000,
			 		delay: 400,
			 		event: 'hover',
			 		auto: true,
			 		mode: 'fade',
			 		controller: $('#bannerCtrl'),
			 		activeControllerCls: 'active'
			 	});*/
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}
function createTable(data){
	$("#sopTable").html("");
	//var table=$("#sopTable");
	console.log($("#sopTable").html());
	var k = 0;
	//当前版本
	var version_no = 0;
	var version_num = 0;
	//待生效版本
	var version_next = 0;
	//最大版本
	var version_max = 0;
	//记录所有的tr数量
	var count = 0;
    for(var i=0;i<data.length;i++)
    {
    	//判断生效时间
    	
		if(k != data[i].PROCEDURENO){
			//第一次是从最大版本开始排,判断最大版本是否生效
	       //var date=new Date();
			//if(data[i].STATUS != 1)
	       console.log(fnDate());
	       console.log(data[i].VALIDTIME);
	      
	       if(typeof(data[i].VALIDTIME) != "undefined"){
		       if(data[i].VALIDTIME>fnDate()){
		    	   console.log("未生效");
		    	  //待生效版本 
		    	   version_next = data[i].VERSIONNO;
		    	   version_max = data[i].VERSIONNO;
		    	   version_no = 0;
		    	   console.log("待生效版本号："+version_next);
		    	 //当前版本号  注：不能用下一版本减一，因为有可能中间涉及到版本删除
		    	   //再次整体遍历，找出上一版本
		    	   for(var j=0;j<data.length;j++){
		    		   if(data[j].PROCEDURENO == data[i].PROCEDURENO){
		    			   if(version_next == data[j].VERSIONNO){
			    			   console.log("版本号:"+data[j].VERSIONNO)
			    			   //如果相等，说明是同一版本，跳过
			    			   continue;
			    		   }else{
			    			   console.log("已生效版本的上一版本:"+data[j].VERSIONNO);
			    			   version_no = data[j].VERSIONNO;
			    			   version_num = j;
			    			   break;
			    		   }
		    		   }
		    	   }
		    	   console.log("ssssssssssss:"+version_no+"ss:"+version_next);
		    	   //version_no = parseInt(data[i].VERSIONNO)-1;
		    	   console.log("当前版本号:"+version_no);
		       }else{
		    	   console.log("已生效");
		    	   version_no = data[i].VERSIONNO;
		    	   version_max = data[i].VERSIONNO;
		    	   version_next = 0;
		    	   console.log(version_no);
		    	   //tr +="<li><img width='450px' height='200px' src='../upload"+data[i].NODEPATH+"/"+data[i].SYSNAME+"'></li>";
		       }
	       }else{
	    	   version_no = 0;
	    	   version_next = 0;
	    	   version_max = 0;
	       }
		console.log("k!="+data[i].PROCEDURENO);
		var tr = "";
		   tr +="<tr>";
	       tr +="<td width='80px'>NO.<span>"+data[i].PROCEDURENO+"</span></td>";
	       //
	       tr +="<td><div>";
	       tr +="<div>";
	       tr +=" <ul class='items'>";
	       
	       if(data[i].VERSIONNO == version_no){
	    	   tr +="<li><img width='330px' height='200px' src='../upload"+data[i].NODEPATH+"/"+data[i].SYSNAME+"'></li>";
	       }else{
	    	   if(version_no == 0){
	    		   tr +="<li><img width='330px' height='200px' src=''></li>";
	    	   }
	       }
	       //tr +="	<li><img width='450px' height='200px' src='../images/banner1.jpg'></li>";
	       tr +=" </ul>";
	       //绑定按钮
	       /*tr +="<ol id='bannerCtrl' class='flex-control-nav flex-control-paging'>";
		   tr +="<li><a>1</a></li>";
		   tr +="</ol>";
			*/
	       
	       tr +="</div>";
	       if(data[i].VERSIONNO == version_no){
	    	   tr +="<span>生效时间：<span class='lab'>"+data[i].VALIDTIME+"</span><input type='button' version='"+version_no+"' onclick='deleteVersion(this);' class='btn' value='删除'/></span>";
	       }else{
	    	   if(version_no != 0){
	    		   console.log(version_no);
	    		   tr +="<span>生效时间：<span class='lab'>"+data[version_num].VALIDTIME+"</span><input type='button' version='"+version_no+"' onclick='deleteVersion(this);' class='btn' value='删除'/></span>";
	    	   }
	       }
	       tr +="</div></td>";
	       //
	       tr +="<td><div>";
	       tr +="<div>";
	       tr +=" <ul class='items'>";
	       if(data[i].VERSIONNO == version_next){
	    	   tr +="<li><img width='330px' height='200px' src='../upload"+data[i].NODEPATH+"/"+data[i].SYSNAME+"'></li>";
	       }else{
	    	   tr +="<li><img width='330px' height='200px' src=''></li>";
	       }
	      /* tr +="	<li><img width='330px' height='200px' src='../upload"+data[i].NODEPATH+"/"+data[i].SYSNAME+"'></li>";*/
	       //没有则为空
	       tr +=" </ul>";
	       tr +="</div>";
	       if(data[i].VERSIONNO == version_next){
	    	   tr +="<span>生效时间：<span class='lab'>"+data[i].VALIDTIME+"</span><input type='button' class='btn' version='"+version_next+"' onclick='deleteVersion(this);' value='删除'/></span>";
	       }else{
	    	   //tr +="<span>生效时间：<span class='lab'>"+data[i].VALIDTIME+"</span><input type='button' class='btn' version='"+version_next+"' onclick='deleteVersion(this);' value='删除'/></span>";
	       }
	       tr +="</div></td>";
	       //
	       tr +="<td><input type='button' class='btn' version='"+version_max+"' version_next='"+version_next+"' onclick='addVersion(this)' value='上传新版本'></td>";
	       tr +="</tr>";
	       
	       //下划线
	       tr +="<tr><td colspan='4'><hr></td></tr>";
	       //$("#sopTable").appendTo($(tr));
	       k = data[i].PROCEDURENO;
	       count += 2;
	       $("#sopTable").append(tr);
		}else if(k == data[i].PROCEDURENO){
			//
			console.log("k=="+data[i].PROCEDURENO);
			if(data[i].VERSIONNO == version_no){
				var li ="<li><img width='330px' height='200px' src='../upload"+data[i].NODEPATH+"/"+data[i].SYSNAME+"'></li>";
				$("#sopTable").find("tr").eq(count-2).find("td").eq(1).find("ul").append(li);
			}else if(data[i].VERSIONNO == version_next){
				var li ="<li><img width='330px' height='200px' src='../upload"+data[i].NODEPATH+"/"+data[i].SYSNAME+"'></li>";
				$("#sopTable").find("tr").eq(count-2).find("td").eq(2).find("ul").append(li);
			}else{
				
			}
			//给第二个td中的div 的div 的ul追加li
			//console.log($("#sopTable tr:eq(1) td:eq(1) div div ul").html());
			//console.log($("#sopTable tr:eq(1) td:eq(1)").html());
			/*console.log(count);
			console.log($("#sopTable").find("tr").eq(count-2).html());
			console.log($("#sopTable").find("tr").eq(count-2).find("td").eq(1).find("ul").html());
			var li ="<li><img width='450px' height='200px' src='../upload"+data[i].NODEPATH+"/"+data[i].SYSNAME+"'></li>";
			$("#sopTable").find("tr").eq(count-2).find("td").eq(1).find("ul").append(li);
			console.log($("#sopTable").find("tr").eq(count-2).find("td").eq(1).find("ul").html());*/
		}
      
    }
    //$("#sopTable").append(tr);
}
function addVersion(obj){
	//$(obj).parent().html());
	//var obj = new Object();
	//设置判断条件，如果有新版本则不允许上传操作，提示删除当前未生效版本
	var versionNext = $(obj).attr("version_next");
	if(versionNext != 0){
		alert("请删除当前未生效版本");
		return false;
	}
	var procedureNo = $(obj).parent().parent().find("td").eq(0).find("span").html();
	console.log("procedureNo=:"+procedureNo);
	var productCode = $("#productCode").text();
	var nodePath = $("#nodePath").text();
	var versionNo = $(obj).attr("version");
	//var formData = obj;
	//console.log(formData);
	var hrefUrl = "upload_plus.jsp?productCode="+productCode+"&nodePath="+nodePath+
	"&procedureNo="+procedureNo+"&versionNo="+versionNo;
	console.log(hrefUrl);
	top.page.location.href = hrefUrl;

	
}
function deleteVersionConfirm(obj){
	var objdata = new Object();
	objdata.procedureNo = $(obj).parent().parent().parent().parent().find("td").eq(0).find("span").html();
	objdata.productCode = $("#productCode").text();
	console.log(objdata.productCode);
	objdata.versionNo = $(obj).attr("version");
	var formData = objdata;
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
				loadSopAllProce();
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}

function deleteVersion(obj){
	if(confirm("确定删除该版本？"))   //弹出确认删除对话框  
	{	
		deleteVersionConfirm(obj);
	//  return true;    //点击确定时，返回值为true，执行如上操作。
	}
	else
	{
	   return false; //点击取消时，返回值为false，不执行操作。
	}
}
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
	var time=year+"-"+fnW((month+1))+"-"+fnW(data)+" "+fnW(hour)+":"+fnW(minute)+":"+fnW(second);
	//alert(date.toLocaleString());
	//alert("ss");
	return time
	//oDiv.innerHTML=time;
}

function fnW(str){
	var num;
	str>=10?num=str:num="0"+str;
	return num;
}