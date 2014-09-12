<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设定发版时间</title>
<style type="text/css">
	#dd { position: relative; }
	#createbox {
		position: absolute;
	    left: 60px;
	    top: 20px;
	}
	.left {
		width: 60px;
	}
	#button {
		position: absolute;
		top: 110px;
		left: 95px;
	}
	#button a {
		margin: 5px;
	}
	#deployMessage {
		padding : 10px;
	}
</style>
</head>
<body>
<div class="easyui-layout" fit="true" style="height: 540px;">
	<div region="north" border="true" collapsible="false"
		collapsed="false" class="easyui-panel" title="条件查询定时任务"
		style="height: 62px; overflow: auto;">
     	<form onsubmit="return false;" id="searchForm">
		<table>
		<tr>
			<td>打包名称:</td>
			<td><input name="deploySchedule.packageName" /></td>
			<td>执行人:</td>
			<td>
				<input id="executor" class="easyui-combobox" name="deploySchedule.executor" 
					data-options="
	                    url:'${dynamicURL}/deploy/searchExecutorForCombobox.action',
	                    method:'get',
	                    valueField:'executor',
	                    textField:'executor',
	                    panelHeight:'auto',
	                    editable:false" />
			</td>
			<td>状态:</td>
			<td>
				<s:select list="#{0:'等待执行',1:'待检查',2:'失败',6:'已解决'}" listKey="key" listValue="value"  
					name="deploySchedule.status" headerKey="" headerValue="所有" />
			</td>
			<td>开始时间:</td>
			<td>
				<input type="text" id="start" class="easyui-datebox" name="startTime">
			</td>
			<td>结束时间:</td>
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
	        	<th data-options="field:'ck',checkbox:true,formatter : function(value, row, index) {return row.id;}"></th>
	            <th data-options="field:'packageName',width:170,align:'center'">打包名称</th>
	            <th data-options="field:'executeTime',width:126,align:'center'">预定发版时间</th>
	            <th data-options="field:'executor',width:126,align:'center'">执行人</th>
	            <th data-options="field:'status',width:126,align:'center',formatter:showStatus">状态</th>
	        </tr>
	    </thead>
	</table>
	</div>
	<div id="tb" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="javascript:$('#dd').dialog('open')">新增</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" 
	               onclick="delDeployScheduler()">删除</a>
		</div>
	</div>

	<div id="dd" class="easyui-dialog" title="新增定时发版任务" style="width:400px;height:200px;"
	        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
	    <form id="ff" method="post">
	    <table id="createbox">
		    <tr>
		        <td class="left"><label for="packageName">打包名称</label></td>
		        <td class="right"><input class="easyui-validatebox" type="text" name="deploySchedule.packageName" data-options="required:true" 
		        	style="width:146px"/></td>
		    </tr>
		    <tr>
		        <td class="left"><label for="executeTime">发版日期</label></td>
		        <td class="right">
		        <input class="easyui-datetimebox" name="deploySchedule.executeTime" 
        			data-options="required:true,showSeconds:false" style="width:150px"></td>
		    </tr>
		    <tr>
		        <td class="left"><label for="executor">执行人</label></td>
		        <td class="right">
		        <input class="easyui-combobox" name="deploySchedule.executor" 
								data-options="
				                    url:'${dynamicURL}/deploy/searchExecutorForCombobox.action',
				                    method:'get',
				                    valueField:'executor',
				                    textField:'executor',
				                    panelHeight:'auto',
				                    required:true " 
				                style="width:150px"/></td>
		    </tr>
		    <div id="button">
	    		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitFF()">提交</a>
	        	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeFF()">取消</a>
		     </div>
		</table>
		</form>
	</div>
	<div id="dm" class="easyui-dialog" title="部署信息" style="width:800px;height:600px;"
	        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
	        <div id="deployMessage"></div>
	</div>
</div>	
<form method="post" id="fm" style="display: none;"></form>
<script type="text/javascript">
$(function() {
	$('#dd').dialog({ autoOpen: false });
	$('#dg').datagrid({
		title : '定时发版设定表',
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
		url : '${dynamicURL}/deploy/searchDeploySchedule.action',
		onLoadSuccess: function(data){
			// 拿到标题列
			//$(".datagrid-header-inner table tr:first-child").css("text-align", "center");
			//$('#dg').datagrid('getPager').find('input').val(${pageNumber});
		},
		rowStyler : function ( index, row ) {   
	        if (row.status==1){   
	            return 'background-color:#94B84D;';   
	        } else if ( row.status == 2 ) {
	        	return 'background-color:#FF8282;';
	        } else if ( row.status == 6 ) {
	        	return 'background-color:#8DC6FF;';
	        }
	    }
	});

});
function showStatus(val, row) {
	if (row.status == 0) {
		return "<span>等待执行</span>&nbsp;&nbsp;<a href='#' onclick='confirm_("+row.id+")'>已解决</a>";
	} else if (row.status == 1) {
		return "<span><a href='#' onclick='showDM_("+row.id+")'>待检查</a></span>&nbsp;&nbsp;<a href='#' onclick='confirm_("+row.id+")'>已解决</a>";
	} else	if (row.status == 2) {
		return "<span><a href='#' onclick='showDM_("+row.id+")'>失败</a></span>&nbsp;&nbsp;<a href='#' onclick='confirm_("+row.id+")'>已解决</a>";
	} else	if (row.status == 6) {
		return "<span><a href='#' onclick='showDM_("+row.id+")'>已解决</a></span>";
	}
}
function cleanForm(){
	$("#searchForm")[0].reset();
	$("#executor").combobox("clear");
	$('#start').datebox('setValue', '');
	$('#end').datebox('setValue', '');
}

function loaddata() {
	$('#dg').datagrid('load',
			sy.serializeObject($("#searchForm").form()));
}
function delDeployScheduler(){
	var row = $('#dg').datagrid('getSelected');
    if (!row){
    	$.messager.alert('操作提示','请选择要删除的数据！','info');
    	return;
    }
    if(confirm('确定要删除'+row.packageName+' at '+ row.executeTime +'?')){
    	$('#fm').form('submit',{
            url: '${dynamicURL}/deploy/deleteDeploySchedule.action?id='+row.id,
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
function confirm_ (rowid) {
	$('#fm').form('submit',{
        url: '${dynamicURL}/deploy/confirmDeploySchedule.action?id='+rowid,
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
function showDM_ (rowid) {
	$('#deployMessage').empty();
	$('#fm').form('submit',{
        url: '${dynamicURL}/deploy/searchDeployMessage.action?id='+rowid,
        onSubmit: function(){},
        success: function(data){
        	data = jQuery.parseJSON(data);
        	$('#deployMessage').append("<span>"+data.deployMessage.replace(/\r\n|\n|\r/g, '<br />')+"</span>")
        }
    });
	$('#dm').dialog('open');
}

function submitFF() {
	$('#ff').form('submit', {
	    url:'${dynamicURL}/deploy/createDeploySchedule.action',
	    onSubmit: function(){
	    	return $("#ff").form('validate');
	    },
	    success:function(result){
			 handleActionResult(result,{
        		onSuccess:function(){
        			window.location.href = '${dynamicURL}/deploy/setDeployTimeInit.action';
        		}
        	});
		 }
	});
}

function closeFF() {
	$('#dd').dialog('close');
}
</script>
</body>
</html>