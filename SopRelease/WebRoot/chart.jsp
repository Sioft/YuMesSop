<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>碎片录入主页面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/echarts.common.min.js"></script>
	<script type="text/javascript">
	$(function(){
		var mychart = echarts.init(document.getElementById("echart"));
		/* var pieData = [
           {value:335, name:'直接访问'},
           {value:310, name:'邮件营销'},
           {value:234, name:'联盟广告'},
           {value:135, name:'视频广告'},
           {value:1548, name:'搜索引擎'}
       ] */
       var pieData = [
                      {value:335, name:'直接访问'},
                      {value:310, name:'邮件营销'},
                      {value:234, name:'联盟广告'}
                  ];
		/* var pieData = [
 			<c:forEach items="${seriesList}" var="series">
 				{value:${series.count}, name:'${series.piecetype}'},
 			</c:forEach>
 		]; */
 		//var textData = ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎'] ;
 		var textData = ['直接访问','邮件营销','联盟广告'] ;
 		/* var textData = [
 			<c:forEach items="${seriesList}" var="series">
 				'${series.piecetype}',
 			</c:forEach>
 		]; */
 	
 		option = {
 			    title : {
 			        //text: '不同类型碎片数量分布',
 			       	//subtext: '——全部碎片统计',
 			        x:'center'
 			    },
 			    tooltip : {
 			        trigger: 'item',
 			        formatter: "{a} <br/>{b} : {c} ({d}%)"
 			    },
 			  /*   legend: {
 			        orient: 'vertical',
 			        left: 'left',
 			        data: textData
 			    }, */
 			   color:['red', 'green','yellow'], 
 			    series : [
 			        {
 			            name: '个数',
 			            type: 'pie',
 			            radius : '55%',
 			            center: ['50%', '60%'],
 			            data: pieData,
 			            itemStyle: {
 			                emphasis: {
 			                    shadowBlur: 10,
 			                    shadowOffsetX: 0,
 			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
 			                }
 			            }
 			        }
 			    ]
 			};	
		mychart.setOption(option);
	});
	</script>
	<script type="text/javascript">
	
	</script>
		
</head>
<body>
	<div >
		<div id="echart"  style="width: 200px;height:120px; margin: auto; margin-top: 55px;"></div>
	</div>
	
</body>
</html>
