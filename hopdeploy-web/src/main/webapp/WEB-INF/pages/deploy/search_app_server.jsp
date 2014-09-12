<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应用服务器信息查询</title>
</head>
<body>
	<div class="easyui-layout" fit="true" style="height: 500px;">
		<div region="center" border="false">
			<table id="dg">
				<thead>
					<tr>
						<th	data-options="field:'ck',checkbox:true,formatter : function(value, row, index) {return row.rowId;}"></th> 
						<th data-options="field:'parentId',width:150,hidden:true">管理服务器ID</th>
						<th data-options="field:'id',width:150,hidden:true">应用服务器ID</th>
						<th data-options="field:'ip',width:150,align:'center'">服务器IP</th>
						<th data-options="field:'middlewarePath',width:250,align:'center'">应用节点路径</th>
						<th data-options="field:'port',width:100,align:'center'">端口</th>
						<th data-options="field:'nodeName',width:150,formatter:updateAS,align:'center'">节点名称</th>
						<th data-options="field:'memo',width:300">备注</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a href="${dynamicURL}/deploy/createAppServerInit.action?parentId=${weblogicId}"
					class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
            	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" 
            	    onclick="delAppServer()">删除</a>
            	<a href="${dynamicURL}/deploy/searchAppRelateServerInit.action" 
            		class="easyui-linkbutton" iconCls="icon-back" plain="true" style="float:right">返回</a>
			</div>
		</div>
	</div>
	<form method="post" id="fm" style="display: none;"></form>
	<script type="text/javascript">
		function updateAS(val, row) {
			return "<a href='${dynamicURL}/deploy/updateAppServerInit.action?id="+row.id+"&parentId="+row.parentId+"'>" + val + "</a>";
		}
	
		function loaddata() {
			$('#dg').datagrid('load',
					sy.serializeObject($("#searchForm").form()));
		}
		$(function() {
			$('#dg').datagrid({
				title : '应用服务器信息查询',
				toolbar : '#tb',
				singleSelect : true,
				fit : true,
				fitColumns : false,
				collapsible : true,
				rownumbers : true, //显示行数 1，2，3，4...
				pagination : true, //显示最下端的分页工具栏
				pagePosition : 'bottom',
				pageList : [ 5, 10, 15, 20 ], //可以调整每页显示的数据，即调整pageSize每次向后台请求数据时的数据
				pageSize : 15, //读取分页条数，即向后台读取数据时传过去的值
				url : '${dynamicURL}/deploy/searchAppServer.action?id=${weblogicId}',
				onLoadSuccess: function(data){
					// 拿到标题列
					$(".datagrid-header-inner table tr:first-child").css("text-align", "center");
				}
			});
		});
		function delAppServer(){
        	var row = $('#dg').datagrid('getSelected');
            if (!row){
            	$.messager.alert('操作提示','请选择要删除的数据！','info');
            	return;
            }
            if(confirm('确定删除'+row.nodeName+'?')){
	        	$('#fm').form('submit',{
	                url: '${dynamicURL}/deploy/deleteAppServer.action?id='+row.id,
	                onSubmit: function(){
	                    return $(this).form('validate');
	                },
	                success: function(result){
	                	handleActionResult(result,{
	                		onSuccess:function(){
	                			$('#dg').datagrid('reload');//重新加载数据
	                		}
	                	});
	                }
	            });
            }
        }
	</script>
</body>
</html>