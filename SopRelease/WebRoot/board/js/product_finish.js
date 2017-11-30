/**
 * Created by Administrator on 2017/7/12.
 */
$(document).ready(function(){

    $('.skillbar').each(function(){
        $(this).find('.skillbar-bar').animate({
            width:$(this).attr('data-percent')
        },6000);

    });
    var myChart = echarts.init(document.getElementById('weekComplet'));
	//app.title = "坐标轴刻度与标签对齐";
    option = {
    		color: ['#3398DB'],
    	    tooltip : {
    	        trigger: 'axis',
    	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
    	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
    	        }
    	    },
    	    grid: {
    	        left: '3%',
    	        right: '4%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    xAxis : [
    	        {
    	            type : 'category',
    	            data : ['周一','周二','周三','周四','周五','周六','周日']
    	        }
    	    ],
    	    yAxis : [
    	        {
    	            type : 'value'
    	        }
    	    ],
    	    series : [
    	        
    	        {
    	            name:'联盟广告',
    	            type:'bar',
    	            stack: '广告',
    	            data:[220, 182, 191, 234, 290, 330, 310]
    	        }
    	        
    	    ]
    	};

	myChart.setOption(option);
    
    setInterval(show,10000);


});

//看板轮询
function show(){
	//统计
	loadBoardMessage();
	//任务单完成率
	loadPordCompletRate();
	//最近一周每天的生产量
	loadWeekCompletCount();
	
}
function loadWeekCompletCount(){
	$.ajax({
  		url:"../report/loadWeekCompletCount.do",
		type:"post",
		//data:{"startTime":startTime,"endTime":endTime},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				console.log(result.data);
				var resultdata = result.data;
				//$("#weekComplet").empty();
				var myChart = echarts.init(document.getElementById('weekComplet'));
				//app.title = "坐标轴刻度与标签对齐";
			    option = {
			    		color: ['#3398DB'],
			    	    tooltip : {
			    	        trigger: 'axis',
			    	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			    	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			    	        }
			    	    },
			    	    grid: {
			    	        left: '3%',
			    	        right: '4%',
			    	        bottom: '3%',
			    	        containLabel: true
			    	    },
			    	    xAxis : [
			    	        {
			    	            type : 'category',
			    	            data : resultdata.date
			    	            //data : ['周一','周二','周三','周四','周五','周六','周日']
			    	        }
			    	    ],
			    	    yAxis : [
			    	        {
			    	            type : 'value'
			    	        }
			    	    ],
			    	    series : [
			    	        
			    	        {
			    	            name:'联盟广告',
			    	            type:'bar',
			    	            stack: '广告',
			    	            //data:[220, 182, 191, 234, 290, 330, 310]
			    	            data:resultdata.value
			    	        }
			    	        
			    	    ]
			    	};

				myChart.setOption(option);
			}
			//$("#dataAnalysis").empty();
			
		},
		error:function(){
			console.log("ss");
			//errorConnection("服务器出错");
		}
	});
}


function loadPordCompletRate(){
	$.ajax({
  		url:"../report/loadPordCompletRate.do",
		type:"post",
		//data:{"startTime":startTime,"endTime":endTime},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				console.log(result.data);
				var resultdata = result.data;
				$("#pordId1").html(resultdata[0].PORDID);
				$("#pordId1_rate").attr("data-percent",resultdata[0].RATE+"%");
				$("#pordId1_rate_val").html(resultdata[0].RATE+"%");
				
				$("#pordId2").html(resultdata[1].PORDID);
				$("#pordId2_rate").attr("data-percent",resultdata[1].RATE+"%");
				$("#pordId2_rate_val").html(resultdata[1].RATE+"%");
				
				$("#pordId3").html(resultdata[2].PORDID);
				$("#pordId3_rate").attr("data-percent",resultdata[2].RATE+"%");
				$("#pordId3_rate_val").html(resultdata[2].RATE+"%");
				
				$("#pordId4").html(resultdata[3].PORDID);
				$("#pordId4_rate").attr("data-percent",resultdata[3].RATE+"%");
				$("#pordId4_rate_val").html(resultdata[3].RATE+"%");
				 $('.skillbar').each(function(){
				        $(this).find('.skillbar-bar').animate({
				            width:$(this).attr('data-percent')
				        },100);

				    });
			}
			//$("#dataAnalysis").empty();
			
		},
		error:function(){
			console.log("ss");
			//errorConnection("服务器出错");
		}
	});
}
function loadBoardMessage(){
	//var startTime = $("#startTime").val();
	//var endTime = $("#endTime").val();
	//console.log("startTime:"+startTime+",endTime:"+endTime);
	$.ajax({
  		url:"../report/loadBoardMessage.do",
		type:"post",
		//data:{"startTime":startTime,"endTime":endTime},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				console.log(result.data);
				var resultdata = result.data;
				$("#pordCount").html(resultdata.pordCount+"台");
				$("#currentCount").html(resultdata.currentCount+"台");
				$("#onCount").html(resultdata.onCount+"台");
				$("#allCurrentCount").html(resultdata.currentCount+"台");
				$("#errorCount").html(resultdata.errorCount+"台");
				
			}
			//$("#dataAnalysis").empty();
			
		},
		error:function(){
			console.log("ss");
			//errorConnection("服务器出错");
		}
	});
}



