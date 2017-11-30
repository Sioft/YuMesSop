<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文件上传</title>
    <link href="${pageContext.request.contextPath}/css/webuploader.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/external/bootstrap-3.3.5/css/bootstrap.min.css" type="text/css" rel="stylesheet"/> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script><%-- 
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/webuploader.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/webuploader.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/webuploader.html5only.js"></script> --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/external/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/webuploader.html5only.min.js"></script>
</head>	
<body>
    <span style="font-size:14px;"><div id="uploader-demo">  
    <!--用来存放item-->  
    <div id="thelist" class="uploader-list"></div>  
    <div>  
     <div id="filePicker">选择图片</div>  
     <button id="ctlBtn" class="btn btn-default">开始上传</button>  
    </div>  
</div>  
</span>  
</div>
</body>
<span style="font-size:14px;"><script type="text/javascript">  
  $(function(){  
   /*init webuploader*/  
   var $list=$("#thelist");   //这几个初始化全局的百度文档上没说明，好蛋疼。  
   var $btn =$("#ctlBtn");   //开始上传  
   var thumbnailWidth = 100;   //缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档  
   var thumbnailHeight = 100;  
  
   var uploader = WebUploader.create({  
       // 选完文件后，是否自动上传。  
       auto: false,  
  
       // swf文件路径  
       
  
       // 文件接收服务端。  
       server: 'http://localhost:8080/sop/sop/uploader.do',  
  
       // 选择文件的按钮。可选。  
       // 内部根据当前运行是创建，可能是input元素，也可能是flash.  
       pick: {id:'#filePicker',multiple:true}, 
  
       // 只允许选择图片文件。  
       accept: {  
           title: 'Images',  
           extensions: 'gif,jpg,jpeg,bmp,png',  
           mimeTypes: 'image/jpg,image/jpeg,image/png'   //修改位置  
       },  
       method:'POST',  
   });  
   // 当有文件添加进来的时候  
   uploader.on( 'fileQueued', function( file ) {  // webuploader事件.当选择文件后，文件被加载到文件队列中，触发该事件。等效于 uploader.onFileueued = function(file){...} ，类似js的事件定义。  
       console.log("syx文件添加");
      // uploader.addFiles( file );		
   	var $li = $(  
               '<div id="' + file.id + '" class="file-item thumbnail">' +  
                   '<img>' +  
                   '<div class="info">' + file.name + '</div>' +  
               '</div>'  
               ),  
           $img = $li.find('img');  
  
  
       // $list为容器jQuery实例  
       $list.append( $li );  
  		//syx将图片加到数组里
  	  
  		
       // 创建缩略图  
       // 如果为非图片文件，可以不用调用此方法。  
       // thumbnailWidth x thumbnailHeight 为 100 x 100  
       uploader.makeThumb( file, function( error, src ) {   //webuploader方法  
           if ( error ) {  
               $img.replaceWith('<span>不能预览</span>');  
               return;  
           }  
  
           $img.attr( 'src', src );  
       }, thumbnailWidth, thumbnailHeight );  
   });  
   // 文件上传过程中创建进度条实时显示。  	
   /* uploader.on( 'uploadProgress', function( file, percentage ) {  
       var $li = $( '#'+file.id ),  
           $percent = $li.find('.progress span');  
  
       // 避免重复创建  
       if ( !$percent.length ) {  
           $percent = $('<p class="progress"><span></span></p>')  
                   .appendTo( $li )  
                   .find('span');  
       }  
  
       $percent.css( 'width', percentage * 100 + '%' );  
   });   */
  
   // 文件上传成功，给item添加成功class, 用样式标记上传成功。  
   uploader.on( 'uploadSuccess', function( file ) { 
	   console.log("syx上传成功");
       $( '#'+file.id ).addClass('upload-state-done');  
   });  
  
   // 文件上传失败，显示上传出错。  
   uploader.on( 'uploadError', function( file ) {  
	   console.log("syx上传失败");
       var $li = $( '#'+file.id ),  
           $error = $li.find('div.error');  
  
       // 避免重复创建  
       if ( !$error.length ) {  
           $error = $('<div class="error"></div>').appendTo( $li );  
       }  
  
       $error.text('上传失败');  
   });  
  
   // 完成上传完了，成功或者失败，先删除进度条。  
   uploader.on( 'uploadComplete', function( file ) {  
	   console.log("syx上传完成");
       $( '#'+file.id ).find('.progress').remove();  
   });  
   
   
   $btn.on( 'click', function() {  
    console.log("上传...");  
    uploader.upload();
    /* 	var files =  uploader.getFiles();
    		 var form = new FormData(files);
    		console.log(form);
    		var week =  "monday";
    		//alert(weather+","+week+","+text);
    		$.ajax({
    			url:"http://localhost:8080/sop/sop/upload.do",
    			type:"post",
    			//
    			data:{"files":files,"week":week},
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
    		 */
       
     console.log("上传成功"); 
   });   
  });  
 </script></span>  
</html>
