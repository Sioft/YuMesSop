/*

option = {
    title : {
        text: '某站点用户访问来源',
        subtext: '纯属虚构',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    },
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'直接访问'},
                {value:310, name:'邮件营销'},
                {value:234, name:'联盟广告'},
                {value:135, name:'视频广告'},
                {value:1548, name:'搜索引擎'}
            ],
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
*/

function dataAnalysisInit(){
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	console.log("startTime:"+startTime+",endTime:"+endTime);
	$.ajax({
  		url:"servlet/findDefectiveProductAnalysis.do",
		type:"post",
		data:{"startTime":startTime,"endTime":endTime},
		dataType:"json",
		success:function(resultdata){
			console.log(resultdata);
			var pieData = new Array();
			var textData = new Array();
			//if(result.status == 0){
				//var resultdata = result.data;
				//for(var i=0;i<resultdata.length;i++){
					//if('resultdata[i].device_type_001)
					pieData.push({'value':resultdata.device_type_001,'name':'压缩机'});
					pieData.push({'value':resultdata.device_type_002,'name':'吸附塔'});
					pieData.push({'value':resultdata.device_type_003,'name':'换向阀'});
					pieData.push({'value':resultdata.device_type_004,'name':'氧浓度'});
					pieData.push({'value':resultdata.device_type_005,'name':'正常'});
					textData.push({'name':'压缩机'});
					textData.push({'name':'吸附塔'});
					textData.push({'name':'换向阀'});
					textData.push({'name':'氧浓度'});
					textData.push({'name':'正常'});
				//}
				console.log(pieData);
				console.log(textData.toString());
			//}
				//清空div
			$("#dataAnalysis").empty();
				var myChart = echarts.init(document.getElementById('dataAnalysis'));

			    // 指定图表的配置项和数据
				option = {
					    title : {
					        text: '鱼跃医电一分厂',
					        subtext: '不良品分析',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} :({d}%)"
					    },
					    legend: {
					        orient: 'vertical',
					        left: 'right',
					        data: textData
					    },
					    series : [
					        {
					            name: '所占比率',
					            type: 'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:pieData,
					            itemStyle: {
					            	 normal:{
					                     label:{
					                     show:true,
					                     formatter: '{b} : {c} \n ({d}%)'
					                     },
					                     labelLine:{
					                     show:true
					                     }
					                     },
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
					    ]
					};
			    // 使用刚指定的配置项和数据显示图表。
			    myChart.setOption(option);
			
			
			/*var pieData = [
	   			<c:forEach items="${seriesList}" var="series">
	   				{value:${series.count}, name:'${series.piecetype}'},
	   			</c:forEach>*/
		 
		},
		error:function(){
			console.log("ss");
			//errorConnection("服务器出错");
		}
	});
}
$(function(){
	$('#startTime').datetimepicker({
		dayOfWeekStart : 1,
		lang:'ch',
		//disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
		startDate:new Date()
		});
		 $('#endTime').datetimepicker({value:new Date(),step:10}); 
	$('#endTime').datetimepicker({
		dayOfWeekStart : 1,
		lang:'ch',
		//disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
		startDate:new Date()
		});
	$.datetimepicker.setLocale('ch');
	dataAnalysisInit();
	$("#searchDataAnalysis").click(function(){
		dataAnalysisInit();
	});
});