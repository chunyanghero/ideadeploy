<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>应用部署服务器用户名密码查询</title>
</head>
<body>
	<div class="easyui-layout" fit="true" style="height: 500px;">
		<div region="center" border="false">
			<table id="dg"></table>
		</div>
		<div id="tb" style="padding: 5px; height: 25px;">
			<div style="margin-bottom: 5px">
            	<a href="${dynamicURL}/deploy/searchAppRelateServerInit.action" 
            		class="easyui-linkbutton" iconCls="icon-back" plain="true" style="float:right">返回</a>
			</div>
		</div>
	</div>
	<form method="post" id="fm" style="display: none;"></form>
	<script type="text/javascript">
		function loaddata() {
			$('#dg').datagrid('load',
					sy.serializeObject($("#searchForm").form()));
		}
		
		function updateActions(index){
			$('#dg').datagrid('updateRow',{
				index: index,
				row:{}
			});
		}
		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		function editrow(target){
			$('#dg').datagrid('beginEdit', getRowIndex(target));
		}
		
		
		
		function cancelrow(target){
			$('#dg').datagrid('cancelEdit', getRowIndex(target));
		}
		
		$(function() {
			$('#dg').datagrid({
				title : '服务器用户名密码查询',
				toolbar : '#tb',
				singleSelect : true,
				fit : true,
				fitColumns : true,
				collapsible : true,
				rownumbers : true, //显示行数 1，2，3，4...
				pagination : true, //显示最下端的分页工具栏
				pagePosition : 'bottom',
				pageList : [ 5, 10, 15, 20 ], //可以调整每页显示的数据，即调整pageSize每次向后台请求数据时的数据
				pageSize : 15, //读取分页条数，即向后台读取数据时传过去的值
				url : '${dynamicURL}/deploy/searchNWUnixInfo.action?id=${weblogicId}',
				columns:[[
				          {field:'id',title:'主键', width:60,hidden:'true'},
				          {field:'ip',title:'服务器IP', width:60},
				          {field:'unixUsername',title:'用户名',width:90,editor:'text'},
				          {field:'unixPassword',title:'密码',width:90,editor:'text'},
				          {field:'ftpIp',title:'FTP IP',width:90,editor:'text'},
				          {field:'ftpUsername',title:'FTP用户名',width:90,editor:'text'},
				          {field:'ftpPassword',title:'FTP密码',width:90,editor:'text'},
				          {field:'value',title:'操作',width:80,align:'center',
				        	  formatter:function(value,row,index){
									if (row.editing){
										var s = '<a href="#" onclick="saverow('+index+')">Save</a> ';
										var c = '<a href="#" onclick="cancelrow(this)">Cancel</a>';
										return s+c;
									} else {
										var e = '<a href="#" onclick="editrow(this)">Edit</a> ';
										var d = '<a href="#" onclick="deleterow(this)"></a>';
										return e+d;
									}
								}
				          }
				]],
				onBeforeEdit:function(index,row){
					row.editing = true;
					updateActions(index);
				},
				onAfterEdit:function(index,row){
					row.editing = false;
					updateActions(index);
				},
				onCancelEdit:function(index,row){
					row.editing = false;
					updateActions(index);
				},				
				onLoadSuccess: function(data){
					// 拿到标题列
					$(".datagrid-header-inner table tr:first-child").css("text-align", "center");
				}
			});
		});
		
		function saverow(tt){
			//alert(tt);
			$('#dg').datagrid('endEdit', getRowIndex(tt));
			var ed = $('#dg').datagrid('getEditor', {index:tt,field:'unixUsername'});
			var name=$(ed.target).val();
			var ed = $('#dg').datagrid('getEditor', {index:tt,field:'unixPassword'});
			var password=$(ed.target).val();
			var ed = $('#dg').datagrid('getEditor', {index:tt,field:'ftpIp'});
			var ftpIp = $(ed.target).val();
			var ed = $('#dg').datagrid('getEditor', {index:tt,field:'ftpUsername'});
			var ftpUsername = $(ed.target).val();
			var ed = $('#dg').datagrid('getEditor', {index:tt,field:'ftpPassword'});
			var ftpPassword = $(ed.target).val();
			
			var idaddress=$("#dg").datagrid('getRows')[tt].id;
			//alert(name+'--'+password+'--'+ipaddress);
			 
			$('#fm').form('submit',{
                url: '${dynamicURL}/deploy/updateNWUnixInfo.action?id='+idaddress+'&uu='+name+'&up='+password+'&fi='+ftpIp+'&fu='+ftpUsername+'&fp='+ftpPassword,
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
	</script>
</body>
</html>