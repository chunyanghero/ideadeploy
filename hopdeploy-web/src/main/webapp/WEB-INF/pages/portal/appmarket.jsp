<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${staticURL}/portal/img/ui/sys1.css">
</head>
<body class="easyui-layout" fit="true">
   <div data-options="region:'west'" title="应用导航" style="width:245px;">
      <div id="appmarketLeftMenu" style="height:398px; width: 238px; overflow-x:hidden; overflow-y:auto;">
         <ul id="tree" class="easyui-tree" data-options="
            url: '${dynamicURL}/portal/appmarket.action',
            animate: true,
            lines:true,
            onClick: treeOnClick,
            onBeforeExpand:treeOnBeforeExpand"></ul>
      </div>
      <div class="search-box" style="position: absolute; bottom: 0px;">
		 <input id="search" style="width:238px;height: 32px;"></input>  
      </div>
   </div>
   <div data-options="region:'center'" style="width:500px">
	   <table id="dg"></table>  
   </div>
   
<script type="text/javascript">
function removeApp(){
	var row = $("#dg").datagrid("getSelected");
	window.parent.HROS.app.remove(row.wmaId, function(){
		window.parent.HROS.app.get();
		$('#dg').datagrid('reload');
	});
}

function openApp(){
	var row = $("#dg").datagrid("getSelected");
	window.parent.HROS.window.create(row.wmaId);
}

function createApp(){
	var row = $("#dg").datagrid("getSelected");
	window.parent.HROS.app.add(row.id, function(){
		window.parent.HROS.app.get();
    	$('#dg').datagrid('reload');
	});
}
$(document).ready(function(){
	$("#search").searchbox({
		searcher:function(value,name){ 
			var apptype = "";
			var nodes = $('#tree').tree('getSelected');
			if(nodes){
				apptype = nodes.id.split("_")[1];
			}
			$('#dg').datagrid('reload',{
		    	'apptype' : apptype,
		    	'keyword' : value
			});
		},
		prompt:'请输入应用名称'
	});
	$('#dg').datagrid({ 
      	  fit: true,
      	  border: false,
          pagination: true,
          url: window.parent.HROS.CONFIG.basicActionUrl + '/appmarketList.action',
          autoRowHeight: true,
          fitColumns: true,
          singleSelect: true,
          pageList: [5,10,15,20],
          queryParams : {
          	'apptype' : '0',
          	'keyword' : $('#keyword').val()
          },
          columns:[[
              {field:'name',title:"应用列表",width:330,height:32,formatter: function(value,row,index){
      				if (row.name){
      					var val = '<div style="height:36px;line-height:36px;width:99%;"><div style="float:left">';
      					
      					if( row.fileId == null || row.fileId == "" ){
      						val += "<img style='vertical-align:middle; margin-right:5px;width:24px;height:24px;' src='${staticURL}/portal/img/ui/system-gear.png'/>";
      					}else{
      						val += "<img style='vertical-align:middle; margin-right:5px;width:22px;height:22px;' src='${dynamicURL}/portal/portalDownload.action?resId="+row.id+"' />";
      					}
      					val += row.name;
   					    val += "</div>";
  						val +="<div style='float:right;height:36px;line-height:36px;'>";
      					if( row.wmaId !== null && row.wmaId !== 0){
       					    val += "<a class='btn-run-s' style='margin-top:6px;float: left;height: 25px;width: 25px;' href='javascript:openApp();' title='打开应用'></a>";
      						val += "<a class='btn-remove-s' style='margin-top:6px;float: left;height: 25px;width: 25px;' href='javascript:removeApp();' title='删除应用'></a>";
      					}else{ 
      						val += "<a class='btn-add-s' style='margin-top:6px;float: left;height: 25px;width: 25px;' href='javascript:createApp();' title='添加应用'></a>";
      					}
      					val += '</div></div>';
      					return val;
      				} else {
      					return value;
      				}
      			}}
          ]]
    }); 
});

function treeOnClick(treeNode) {
	var nodeTarget = treeNode.target;
	var isLeaf = $('#tree').tree('isLeaf',nodeTarget);
	//是叶子节点则不重新加载  
	if(isLeaf){
		return;
	}
	$('#dg').datagrid('load',{
    	'apptype' : treeNode.id.split("_")[1],
    	'keyword' : $("#search").searchbox("getValue")
	});
}
function treeOnBeforeExpand(treeNode){
	var nodeTarget = treeNode.target;
	var children = $('#tree').tree('getChildren',nodeTarget);
	if(children == ''){
		treeNode.id = treeNode.id.split("_")[1];
	}
	return true;
}
</script>
</body>
</html>

