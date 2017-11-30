<%@page import="java.util.*"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//String ipAddress = request.getLocalAddr();
	String ipAddress = request.getRemoteAddr();

	System.out.println("IP:"+ipAddress);

%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"> 
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>SOP轮播</title>
	
	 
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common.js"></script> --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-ui.js"></script>
</head>
<body>
<label id="ipAddress" hidden="hidden"><%=ipAddress %></label> 
<label id="code" hidden="hidden"></label> 
<label id="sopVersion" hidden="hidden"></label>
	<div id="left">
		<div class="kePublic">
			<!--效果html开始-->
			<div class="mkeFocus">
				<div class="mkeUl">
					<ul id="showlist">
						<!-- <li><a href="#" target="_blank"><img src="images/ban1.jpg" width="588" height="425" /><br />夏季风景摄影作品欣赏</a></li>
						<li><a href="#" target="_blank"><img src="images/ban2.jpg" width="588" height="425" /><br />优秀风景摄影作品</a></li>
						<li><a href="#" target="_blank"><img src="images/ban3.jpg" width="588" height="425" /><br />获奖风景摄影作品</a></li>
						<li><a href="#" target="_blank"><img src="images/ban4.jpg" width="588" height="425" /><br />旅游景点高清风景摄影</a></li> -->
						<!-- <li><a href="#" target="_blank"><img src="images/img_yuy.png" width="1200" height="700" /><br />旅游景点高清风景摄影</a></li> -->
						<li><img src="images/sop_message.jpg"/></li>
						<!-- <li><div id="spdf"></div></li> -->
						
					</ul>
					<div id="turn_foot">	
						<div class="mkeNum"><span class="mke_ns1">1</span><span class="mke_ns2">1</span></div>
						<!-- <div id="foot_dl">产品大类</div>
						<div id="foot_time"></div> -->
					</div>

					<div class="mkeLbtn"></div>
					<div class="mkeRbtn"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="right">
         	<div id="right_title">鱼跃SOP系统</div>
            <!-- <input type="button" style="position:absolute;padding-left:82px;" id="find3" value="查询3"/>
            <input type="button" style="position:absolute;padding-left:62px;" id="find2" value="查询2"/>
            <input type="button" style="position:absolute;" id="find" value="查询"/> -->  
            
            <div class="detail_table">
            <table >
                <tr>
                    <td class="lable_text"><a href="#" onclick="openwin()">工<span style="padding-left: 96px">序</span>：</a></td>
                    <td  class="lable_textc"><span id="procedureNo"></span><span id="procedureName"></span></td>
                </tr>
                <tr>
                    <td class="lable_text">员<span class="text_la">工</span><span class="text_la">编</span><span class="text_la">号</span>：</td>
                    <td class="lable_textc" id="userCode"></td>
                </tr>
                <tr>
                    <td class="lable_text">员<span class="text_la">工</span><span class="text_la">姓</span><span class="text_la">名</span>：</td>
                    <td class="lable_textc" id="userName"></td>
                </tr>
                <tr>
                    <td class="lable_text">产<span class="text_la">品</span><span class="text_la">料</span><span class="text_la">号</span>：</td>
                    <td class="lable_textc" id="productNo"></td>
                </tr>
                <tr>
                    <td class="lable_text">产<span class="text_la">品</span><span class="text_la">大</span><span class="text_la">类</span>：</td>
                    <td class="lable_textc" id="productCode"></td>
                </tr>
                <tr>
                    <td class="lable_text">原物料编号：</td>
                    <td class="lable_textc" id="bomCode"></td>
                </tr>
                <tr>
                    <td class="lable_text">原物料名称：</td>
                    <td class="lable_textc" id="bomName"></td>
                </tr>
              
            </table>
            <div id="errorMessage">
            	<div class="warm"><img src="images/warn.png" ><span class="warn_lable">异</span><span class="text_la">常</span><span class="text_la">信</span><span class="text_la">息</span>：</div>
               <div class="warm_detail" id="exception">异常信息</div>
            </div>
            </div>
            <div class=" msg_f"><div class="msg_fi">生产任务单</div><div class="msg_lh">成品料号</div><div class="msg_num">数量（台）</div></div>
            <div class="msg_down">
                <table class="msg_ta" id="pordTable">
                	<tbody>
                    <tr class="msg_first message_text">
                        <td class="msg_firsta">N1234567890</td>
                        <td class="msg_firstb">7F-1w制氧机(迷你型)(新款)</td>
                        <td class="msg_firstc">50</td>
                    </tr>
                    <tr class="msg_second message_text">
                        <td class="msg_firsta">N1234567890</td>
                        <td class="msg_second1">9F-3</td>
                        <td class="msg_second1">150</td>
                    </tr>
                    <tr class="msg_third message_text">
                        <td class="msg_firsta">N1234567890</td>
                        <td class="msg_third1">6F-3</td>
                        <td class="msg_third1">500</td>
                    </tr>
                    <tr class="msg_fourth message_text" style="color:#09d35e">
                        <td class="msg_firsta"><img src="images/right_arrow.png" alt="">N1234567890</td>
                        <td class="msg_fourth1">8F-1</td>
                        <td class="msg_fourth1">80000 <img src="images/left_arrow.png" alt=""></td>
                    </tr>
                    </tbody>
                </table>
                <div class="msg_fifth">
                    <div colspan="2" class="msg_fifth1">流程卡号：</div>
                    <div class="msg_fifth2" id="processnum">1234532</div>

                </div>
		</div>
	</div>

<div id="footer">
    <div id="footer_name">哈工英飒智能技术有限公司</div>
    <div id="footer_tel">服务电话：0512-12345678</div>
    <div id="footer_btn"><input class="change_btn" type="button"  value="左右切换"/></div>
    <div id="state">链接状态 :<span id="stata_t"></span></div>
    <div id="time"></div>
    <!--<div >链接状态:正常</div>-->
</div>

<div id="dialog-defective" title="请选择主观不良类型">
  <p>
  	<select class="inp" id="defective_type">
  		<option value="0">噪音过大</option>
  	</select>
  	</p>
</div>
<script language="javascript">

	var ws; //websocket实例
	var lockReconnect = false; //避免重复连接
	var wsUrl = 'ws://192.168.100.135:9000/';
	var pub_procedureGroup = "";

	//存储条形码
	var productBarCode = "";
	//存储物料条形码
	var materialBarCode = "";
	var defectiveBarCode = "";
	var count = 0;
//存储上一次查到的图片列表
var imageList = "";


var rnum=$(".mkeUl ul li").size();
console.log("size:"+rnum);
var sop_width = 1374;//588
var cnum=0;
//var flagbtn = true;
$(".mke_ns2").html(rnum);
$(".mkeUl ul").width(rnum*sop_width);
$(".mkeRbtn").click(function(){
	/* if(flagbtn){
		flagbtn = false; */
		cnum++;
		if(cnum>(rnum-1)){
		cnum=0	
		}
		$(".mkeUl ul").animate({"left":-cnum*sop_width},300);
		$(".mke_ns1").html(cnum+1);
		//flagbtn = true;
	//}**
});
$(".mkeLbtn").click(function(){
	/* if(flagbtn){
		flagbtn = false; */
		cnum--;
		if(cnum<0){
		cnum=rnum-1;	
		}
		$(".mkeUl ul").animate({"left":-cnum*sop_width},300);
		$(".mke_ns1").html(cnum+1);
		//flagbtn = true;
	//}
});

$(function(){
	//测试Pdf展示
	//var success = new PDFObject({ url: "images/images.pdf" }).embed("spdf");
	
	//initWebSocket();
	//testOnline();
	createWebSocket(wsUrl);
	showTime();
	//加载工序信息
	//loadIpSopStation();
	//主观不良下拉框初始化
	subjectDefectiveInit();
	$("#dialog-defective" ).dialog({
		  autoOpen: false,
	      resizable: false,
	      width:500,
	      height:300,
	      modal: true,
	      buttons: {
	        "确认": function() {
	          $( this ).dialog( "close" );
	          saveDecfectiveBarcode();
	        },
	        "取消": function() {
	          $( this ).dialog( "close" );
	         
	        }
	      }
	});
	$("#errorMessage").css('visibility', 'hidden');
	
	//测试
	$("#find").on('click', function(){ 
		test('P170815003851');
    });
	$("#find2").on('click', function(){ 
		test('P171017011061');
    });
	$("#find3").on('click', function(){ 
		test('7F-3B#YY01.02.84-V2.7##20170630#00001');
    });
	$("#footer_btn").click(function() {
	    var div = $("#left");
	    var div1 = $("#right");
	    if(div.hasClass("dest")) {
	        div.removeClass("dest").animate({left: 0}, 1000);
	    } else {
	        div.addClass("dest").animate({left:546}, 1000);
	    }

	    if(div1.hasClass("dest1")) {

	        div1.removeClass("dest1").animate({left: 828}, 1000);

	    } else {
	        div1.addClass("dest1").animate({left: -546}, 1000);
	    }
	});
	
	$(".change_btn").click(function(){
        var nowTime = new Date().getTime();
        var clickTime = $(this).attr("ctime");
        if( clickTime != 'undefined' && (nowTime - clickTime < 2000)){
            /*alert('操作过于频繁，稍后再试');*/
            return false;
        }else{
            $(this).attr("ctime",nowTime);
            /* alert('提交成功');*/
        }
    });
});

/* function autoPlay(){
  	cnum++;
	if(cnum>(rnum-1)){
	cnum=0	
	}
	$(".mkeUl ul").animate({"left":-cnum*sop_width},300);
	$(".mke_ns1").html(cnum+1);
} */
//var Timer=setInterval(autoPlay,4000);
//$(".mkeFocus").hover(function(){clearInterval(Timer)},function(){Timer=setInterval(autoPlay,4000);});

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/show/js/sopShow.js"></script>
</div>
</body>
</html>
