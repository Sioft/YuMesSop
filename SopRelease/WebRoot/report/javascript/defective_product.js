$(function(){
	//页面加载完成之后执行
	findAllDefectiveProductReport();
	//pageInit();
	$("#searchDefective").on('click', function(){ 
		pageInit();
    });
});
function findAllDefectiveProductReport(){
	//创建jqGrid组件
	jQuery("#list2").jqGrid(
			{
				url : 'servlet/uploadExcelServlet.servlet',//组件创建完成之后请求数据的url
				//postData : {"pordId":pordId},
				datatype : "json",//请求数据返回的类型。可选json,xml,txt
				colNames : [ '订单号', '产品名称', '流程卡号', '检测类型', '检测指标（上限）','检测指标（下限）', '检测数值','检测结果' ],//jqGrid的列显示名字
				colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
				             {name : 'id',index : 'id',width : 150}, 
				             {name : 'invdate',index : 'invdate',width : 100}, 
				             {name : 'name',index : 'name asc, invdate',width : 100}, 
				             {name : 'amount',index : 'amount',width : 100,align : "right"}, 
				             {name : 'tax',index : 'tax',width : 80,align : "right"}, 
				             {name : 'total',index : 'total',width : 80,align : "right"}, 
				             {name : 'note',index : 'note',width : 80,sortable : false}, 
				             {name : 'ss',index : 'note',width : 80,sortable : false}
				           ],
				rowNum : 20,//一页显示多少条
				rowList : [ 20, 40, 60 ],//可供用户选择一页显示多少条
				pager : '#pager2',//表格页脚的占位符(一般是div)的id
				sortname : 'id',//初始化的时候排序的字段
				sortorder : "desc",//排序方式,可选desc,asc
				mtype : "post",//向后台请求数据的ajax的类型。可选post,get
				viewrecords : true,
				scroll : false,
				height : 470
			});
	/*创建jqGrid的操作按钮容器*/
	/*可以控制界面上增删改查的按钮是否显示*/
	jQuery("#list2").jqGrid('navGrid', '#pager2', {edit : false,add : false,del : false});
}
function pageInit(){
	//获取参数：
	var pordId = $("#defective_pordId").val();
	console.log("pordId:"+pordId);
	/*if(pordId=="" && pordId==null){
		alert("请先输入任务单号！");
		return false;
	}*/
	//$("#list2").empty();
	//创建jqGrid组件
	
	/*创建jqGrid的操作按钮容器*/
	/*可以控制界面上增删改查的按钮是否显示*/
	$('#list2').jqGrid('setGridParam', {url: '../../../report/findDefectiveProductReport.do?pordId='+pordId}).trigger('reloadGrid');
}