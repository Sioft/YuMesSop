/*$(function(){
    			//页面载入后自动发送请求，加载所有工位
    	       loadAllStations();
    	       loadAllStaffs();
    	       loadAllProducts();
    	     //移到右边
    	       $('#add').bind('click', function() {
    	           //获取选中的选项，删除并追加给对方
    	           $('#stationSel option:selected').appendTo('#select2');
    	           r_optionsOverAll=$("#select2").children();
    	       });
    	       //移到左边
    	       $('#remove').bind('click', function() {
    	           $('#select2 option:selected').appendTo('#stationSel');
    	           optionsOverAll=$("#stationSel").children();    
    	       });
    	       //全部移到右边
    	       $('#add_all').bind('click', function() {
    	           $('#stationSel option').appendTo('#select2');
    	           r_optionsOverAll=$("#select2").children();

    	       });
    	       //全部移到左边
    	       $('#remove_all').bind('click', function() {
    	           $('#select2 option').appendTo('#stationSel');
    	           optionsOverAll=$("#stationSel").children();    
    	       });
    	       //双击选项
    	       $('#stationSel').bind('dblclick', function() { //绑定双击事件
    	           //获取全部的选项,删除并追加给对方
    	           $("option:selected", this).appendTo('#select2'); //追加给对方
    	       });
    	       //双击选项
    	       $('#select2').bind('dblclick', function() {
    	       }); 
    		});

//列出工位
function loadAllStations(){
	$.ajax({
  		url:httpUrl+"station/findAll.do",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				resultdata = result.data;
				showStationSelect(resultdata);
				console.log("station:"+resultdata);
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}

function showStationSelect(data){
	var option = "";
	for(var i=0;i<data.length;i++){
	
		option += "<option value='"+data[i].mes_stn_code+"'>"+data[i].mes_stn_name+"</option>";
	}
	$("#stationSel option").remove();
	$("#stationSel").append($(option));
}

//列出员工
function loadAllStaffs(){
	$.ajax({
  		url:httpUrl+"staff/findAll.do",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				resultdata = result.data;
				showStaffSelect(resultdata);
				console.log("staff:"+resultdata);
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}

function showStaffSelect(data){
	var option = "";
	for(var i=0;i<data.length;i++){
	
		option += "<option value='"+data[i].mes_sta_code+"'>"+data[i].mes_sta_name+"</option>";
	}
	$("#staffSel option").remove();
	$("#staffSel").append($(option));
}

//列出产品
function loadAllProducts(){
	$.ajax({
  		url:httpUrl+"product/findAll.do",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				resultdata = result.data;
				showProductSelect(resultdata);
				console.log("product:"+resultdata);
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}

function showProductSelect(data){
	var option = "";
	for(var i=0;i<data.length;i++){
	
		option += "<option value='"+data[i].mes_pdt_code+"'>"+data[i].mes_pdt_name+"</option>";
	}
	$("#productTypeSel option").remove();
	$("#productTypeSel").append($(option));
}
*/
function removeTreeNode() {
	hideRMenu();
	 $( "#dialog-delete" ).dialog("open");
}
function editTreeNode() {
	hideRMenu();
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodeoldname = zTree.getSelectedNodes()[0].node_name;
	$("#editProductCodeName").val(nodeoldname);
	$("#dialog-edit" ).dialog("open");
}
function addTreeNode() {
	hideRMenu();
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	 $("#com-dialog-new" ).dialog("open");
	/*console.log(zTree.getSelectedNodes());
	console.log(zTree.getSelectedNodes()[0]);
	hideRMenu();
	var newNode = { name:"增加"};
	if (zTree.getSelectedNodes()[0]) {
		
		zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
	} else {
		zTree.addNodes(null, newNode);
	}*/
}

/*function editTreeNode() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	remove = $("#remove").attr("checked"),
	rename = $("#rename").attr("checked"),
	removeTitle = $.trim($("#removeTitle").get(0).value),
	renameTitle = $.trim($("#renameTitle").get(0).value);
	zTree.setting.edit.showRemoveBtn = remove;
	zTree.setting.edit.showRenameBtn = rename;
	zTree.setting.edit.removeTitle = removeTitle;
	zTree.setting.edit.renameTitle = renameTitle;
	showCode(['setting.edit.showRemoveBtn = ' + remove, 'setting.edit.showRenameBtn = ' + rename,
		'setting.edit.removeTitle = "' + removeTitle +'"', 'setting.edit.renameTitle = "' + renameTitle + '"']);
}*/
function showCode(str) {
	var code = $("#code");
	code.empty();
	for (var i=0, l=str.length; i<l; i++) {
		code.append("<li>"+str[i]+"</li>");
	}
}
function saveNewProductCode(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	
	var nodeName = $("#newProductCodeName").val();
	//验证：新建节点的名称不能为空
	if(nodeName=="" ||nodeName==undefined){
		alert("请输入节点名称！");
		return false;
	}
	var nodeParentId = "";
	var nodeParentPath = "";
	var menuLevel = 0;
	
	var node = zTree.getSelectedNodes()[0];
	//验证：node为空则是根目录创建新的文件夹
	if(node=="" ||node==undefined){
		//创建根节点文件夹
		nodeParentId = 0;
		nodeParentPath = nodeName;
		menuLevel = 1;
	}else{
		//创建普通文件
		nodeParentId = node.node_id;
		nodeParentPath = node.node_path;
		menuLevel = node.menu_level +1;
	}
	
	var obj = new Object();
	//nodeName, nodeParentId, nodePath, menuLevel
	obj.nodeName = nodeName;
	obj.nodeParentId = nodeParentId;
	obj.nodeParentPath = nodeParentPath;
	obj.menuLevel = menuLevel;
	var formData = obj;
	$("#com-dialog-new").dialog( "close");
	$.ajax({
  		url:"../product/saveNewProductCode.do",
		type:"post",
		data:formData,
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				//清空输入框
				$("#newProductCodeName").val("");
				loadProductTree();
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}
function editProductCode(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	
	var nodeName = $("#editProductCodeName").val();
	//验证：新建节点的名称不能为空
	if(nodeName=="" ||nodeName==undefined){
		alert("请输入新的节点名称！");
		return false;
	}
	$("#dialog-edit").dialog("close");
	var node = zTree.getSelectedNodes()[0];
	//验证：node为空则报错
	if(node=="" ||node==undefined){
		//创建根节点文件夹
		alert("请选择一个节点！");
		return false;
	}
	$.ajax({
  		url:"../product/editNewProductCode.do",
		type:"post",
		data:{"nodeId":node.node_id,"nodeName":nodeName},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				//清空输入框
				$("#editProductCodeName").val("");
				loadProductTree();
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}

function deleteProductCode(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var node = zTree.getSelectedNodes()[0];
	//删除的时候判断一下，如果还有子节点，则提示不允许删除
    if(node.isParent){
       alert("请先删除该节点下产品类型");
       return false;
    }
	var nodeId = node.node_id;

	$.ajax({
  		url:"../product/deleteProductCode.do",
		type:"post",
		data:{"nodeId":nodeId},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				loadProductTree();
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
}


/*function clearSelectContent(){//清除表格中的数据
	$("#stationSel option").remove();
}
			
function addSelectContent(option){//将数据加入select框中
	$("#stationSel").append($(option));
} 
*/
//var httpUrl = "http://192.168.1.217:8080/sop/";
$(document).ready(function(){
	 $( "#dialog-delete" ).dialog({
		 autoOpen: false,
	      resizable: false,
	      height:200,
	      modal: true,
	      buttons: {
	        "确认": function() {
	          $( this ).dialog( "close" );
	          deleteProductCode();
	        },
	        "取消": function() {
	          $( this ).dialog( "close" );
	         
	        }
	      }
	    });
	 $("#com-dialog-new").dialog({
		  autoOpen: false,
	      //resizable: false,
	      height:155	,
	      modal: true,
	      buttons: {
	    	"确定": function() {
	    	 //$( this ).dialog( "close");
              saveNewProductCode();
            },  
	        "取消": function() {
	          $( this ).dialog( "close");
	        }
	      }
	    });
	    $( "#dialog-edit").dialog({
			  autoOpen: false,
		      //resizable: false,
		      height:155,
		      modal: true,
		      buttons: {
		    	"确定": function() {
		    	  //$( this ).dialog( "close");
	              editProductCode();
	            },  
		        "取消": function() {
		          $( this ).dialog( "close");
		        }
		      }
		    });
	   loadProductTree();
	   $("#submit").on('click', function(){ 
	    	 $.ajax({
	    	  		url:"../sop/findAllSop.do",
	    			type:"post",
	    			data:{"productCode":"ZF-1","procedureNo":"1"},
	    			dataType:"json",
	    			success:function(result){
	    				
	    			},
	    			error:function(){
	    				alert("程序出错");
	    			}
	    		});
	     });
  /*  zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes); */
}); 
function loadProductTree(){                                                                                                                                                       
	//var id = $(this).attr('data-id');                                                                                                                                                             
	//var category = $(this).attr('data-categoryId');                                                                                                                                               
	var url = "../sop/findChild.do";                                                                                                                                        
	$.ajax({                                                                                                                                                                                      
		url: url,                                                                                                                                                                                   
		//data:{'id':id,'secondLevelMenu':category,'sessionId':sessionId},                                                                                                                            
		type : "POST",                                                                                                                                                                              
		dataType : "json",                                                                                                                                                                          
		success : function(json)                                                                                                                                                                    
		{	                                                                                                                                                                                          
			var setting = {                                                                                                                                                                           
					data: {                                                                                                                                                                               
						key: {                                                                                                                                                                              
							children: "childlist",                                                                                                                                                             
							name: "node_name",                                                                                                                                                              
						},                                                                                                                                                                                  
						keep: {                                                                                                                                                                             
							parent: true,                                                                                                                                                                     
							leaf: false                                                                                                                                                                       
						}                                                                                                                                                                                   
					},                                                                                                                                                                                    
					view: {                                                                                                                                                                               
						dblClickExpand: false                                                                                                                                                               
					},                                                                                                                                                                                    
					callback: {                                                                                                                                                                           
						onRightClick: OnRightClick,                                                                                                                                                         
						//beforeRename: beforeRename,                                                                                                                                                         
						//onRename:zTreeOnRename,                                                                                                                                                             
						onClick:onClick,                                                                                                                                                                    
					},                                                                                                                                                                                    
				};                                                                                                                                                                                      
			$.fn.zTree.init($("#treeDemo"), setting, json); 
			//初始化界面
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
			//给第一个节点默认选中查询
			var treeNode = zTree.getNodeByParam('node_name', '7F-3EW制氧机');//获取id为1的点 
			changePageSopPlus(treeNode.node_code,treeNode.node_path);
		},                                                                                                                                                                                          
		error : function(text){                                                                                                                                                                     
			console.log(text);                                                                                                                                                                        
			if(text.responseText == ERR_MSG){                                                                                                                                                         
				alert("出错");                                                                                                                                                                        
			}                                                                                                                                                                                         
		}                                                                                                                                                                                           
	});                                                                                                                                                                                           
}

//树节点维护


function OnRightClick(event, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj('treeDemo');
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree.cancelSelectedNode();
		//showRMenu("root", event.clientX, event.clientY);
		showRMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		zTree.selectNode(treeNode);
		//showRMenu("node", event.clientX, event.clientY);
		showRMenu("node", event.clientX, event.clientY);
	}
}

function showRMenu(type, x, y) {
	console.log(y);
	$("#rMenu ul").show();
	if (type=="root") {
		$("#m_del").hide();
		$("#m_edit").hide();
	} else {
		$("#m_del").show();
		$("#m_edit").show();
	}
	var rMenu = $("#rMenu");
	rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

	$("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
	var rMenu = $("#rMenu");
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	var rMenu = $("#rMenu");
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
	}
}







function onClick(event, treeId, treeNode) { 
	
	var procedureNo = $("#procedureNumberSel option:selected").val();
	
	
	//alert("ss +"+theHREF.name);
	/*if(procedureNo=="0"){
		alert("请选择一个工序号");
		return false;
	}else{
		//加载已经存在的sop
		loadExsitSop(treeNode.node_name,procedureNo,treeNode.node_path);
		
	}*/
	//版本2 直接跳转到welcome_plus页面
	changePageSopPlus(treeNode.node_code,treeNode.node_path);
}
	
function changePageSopPlus(productCode,nodePath){
	var hrefUrl = "welcome_plus.jsp?productCode="+productCode+"&nodePath="+nodePath;
	console.log(hrefUrl);
	//top.page.location.href = hrefUrl;
	//兼容浏览器：
	top.page.location = hrefUrl;
}  

function loadExsitSop(productCode,procedureNo,nodePath){
	var obj = new Object();
	obj.productCode = productCode;
	obj.procedureNo = procedureNo;
	obj.nodePath = nodePath;
	var formData = obj;
	console.log(formData);
	$.ajax({
  		url:"../sop/findExsit.do",
		type:"post",
		data:formData,
		dataType:"json",
		success:function(result){
			if(result.status == 0){
			
				var hrefUrl = "welcome.jsp?productCode="+productCode+"&nodePath="+nodePath+
				"&procedureNo="+procedureNo;
				console.log(hrefUrl);
				top.page.location.href = hrefUrl;
			}else{
				var hrefUrl = "upload_plus.jsp?productCode="+productCode+"&nodePath="+nodePath+
				"&procedureNo="+procedureNo+"&versionNo=0";
				console.log(hrefUrl);
				top.page.location.href = hrefUrl;
			}
		},
		error:function(){
			alert("程序出错");
		}
	});
	
	
}

