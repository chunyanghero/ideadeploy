<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手动发版结果查询</title>
</head>
<body>
	<div class="easyui-layout" fit="true" style="height: 540px;">
		<div region="north" border="true" collapsible="false"
			collapsed="false" class="easyui-panel" title="查询条件"
			style="height: 62px; overflow: auto;">
			<form onsubmit="return false;" id="searchForm">
				<table>
					<tr>
						<td>发包名称:</td>
						<td><input name="task.packageName" /></td>
						<td>中间件类型:</td>
						<td>
							<s:select list="#{'1':'weblogic','2':'dubbo', '3':'tomcat', '4':'jboss', '6':'4.0-static', '7':'4.0-war'}" listKey="key" listValue="value"  
								name="task.middlewareType" headerKey="" headerValue="所有" />
						</td>
						<td>开始日期:</td>
						<td>
							<input type="text" id="start" class="easyui-datebox" name="startTime">
						</td>
						<td>结束日期:</td>
						<td>
							<input type="text" id="end" class="easyui-datebox" name="endTime">
						</td>
						<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
						<td><a href="#" class="easyui-linkbutton"
							iconCls="icon-search" onclick="loaddata()">查询</a>
						</td>
						<td><a href="#" class="easyui-linkbutton"
							iconCls="icon-undo" onclick="cleanForm()">清空</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div region="center" border="false">
			<table id="dg">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true,formatter : function(value, row, index) {return row.taskId;}"></th> 
						<th data-options="field:'packageName',width:150">发包包名</th>
						<th data-options="field:'deployDate',width:150,align:'center'">发版时间</th>
						<th data-options="field:'taskId',width:150,align:'center',formatter:showStatus">服务器返回信息查询</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" 
	                onclick="delManualDeployResult()">删除</a>
			</div>
		</div>
		<div id="dm" class="easyui-dialog" title="部署信息" style="width:800px;height:600px;"
	        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
	     	<div id="deployMessage"></div>
		</div>
	</div>
	<form method="post" id="fm" style="display: none;"></form>
	<script type="text/javascript">
		function cleanForm(){
			$("#searchForm")[0].reset();
			$('#start').datebox('setValue', '');
			$('#end').datebox('setValue', '');
		}
		
		function loaddata() {
			$('#dg').datagrid('load',
					sy.serializeObject($("#searchForm").form()));
		}
		
		function showDM_ (rowid) {
			$('#deployMessage').empty();
			$('#fm').form('submit',{
		        url: '${dynamicURL}/deploy/searchManualDeployResultByTaskId.action?id='+rowid,
		        onSubmit: function(){},
		        success: function(data){
		        	data = jQuery.parseJSON(data);
		        	$('#deployMessage').append("<span>"+data.deployMessage.replace(/\r\n|\n|\r/g, '<br />')+"</span>")
		        }
		    });
			$('#dm').dialog('open');
		}
		
		function showStatus(val, row) {
			return "<a href='#' onclick='showDM_("+row.taskId+")'>点击查看</a>";
		}
		
		$(function() {
			$('#dg').datagrid({
				title : 'weblogic adminserver信息查询',
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
				url : '${dynamicURL}/deploy/searchManualDeployResult.action',
				onLoadSuccess: function(data){
					// 拿到标题列
					$(".datagrid-header-inner table tr:first-child").css("text-align", "center");
				}
			});

		});
		function delManualDeployResult(){
        	var row = $('#dg').datagrid('getSelected');
            if (!row){
            	$.messager.alert('操作提示','请选择要删除的数据！','info');
            	return;
            }
            if(confirm('确定要删除'+row.packageName+'在'+row.deployDate+'这条记录?')){
	        	$('#fm').form('submit',{
	                url: '${dynamicURL}/deploy/deleteManualDeployResult.action?id='+row.taskId,
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