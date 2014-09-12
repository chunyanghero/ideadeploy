<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Weblogic服务器信息查询</title>
<style type="text/css">
	#cwb { position: relative; }
	#changebox {
		position: absolute;
	    left: 70px;
	    top: 20px;
	}
	#button {
		position: absolute;
		top: 110px;
		left: 105px;
	}
</style>
</head>
<body>
	<div class="easyui-layout" fit="true" style="height: 540px;">
		<div region="north" border="true" collapsible="false"
			collapsed="false" class="easyui-panel" title="查询条件"
			style="height: 62px; overflow: auto;">
			<form onsubmit="return false;" id="searchForm">
				<table>
					<tr>
						<td>应用编码:</td>
						<td><input name="appRelateServer.applicationCode" /></td>
						<td>应用简称:</td>
						<td>
							<!-- <input name="appRelateServer.applicationAbbr" /> -->
							<input class="easyui-combobox" id="applicationAbbr" name="appRelateServer.applicationAbbr" 
								data-options="
				                    url:'${dynamicURL}/deploy/searchAppRelateServerForCombobox.action',
				                    method:'get',
				                    valueField:'applicationAbbr',
				                    textField:'applicationAbbr',
				                    panelHeight:'250' "/>
						</td>
						<td>应用名称:</td>
						<td><input name="appRelateServer.applicationName" /></td>
						<td>服务器IP:</td>
						<td><input name="appRelateServer.ip" /></td>
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
						<th data-options="field:'ck',checkbox:true,formatter : function(value, row, index) {return row.applicationId;}"></th> 
						<th data-options="field:'applicationCode',width:60,hidden:true">应用编码</th>
						<th data-options="field:'applicationAbbr',width:100,align:'center',formatter:updateARS, sortable:true,sortOrder:'asc'">应用简称</th>
						<th data-options="field:'applicationName',width:180">应用名称</th>
						<th data-options="field:'applicationId',width:80,hidden:true">应用表applicationId</th>
						<th data-options="field:'weblogicId',width:80,hidden:true">服务器表id</th>
						<th data-options="field:'ip',width:100,align:'center',formatter:searchUaP">服务器</th>
						<th data-options="field:'port',width:50,align:'center'">端口</th>
						<th data-options="field:'login',width:110,align:'center'">用户名</th>
						<th data-options="field:'passwd',width:110,align:'center', formatter:changePasswd">密码</th>
						<th data-options="field:'nodeName',width:130,align:'center',formatter:showAppServer">管理节点名称</th>
						<th data-options="field:'middlewareType',width:70,formatter:mType">中间件类型</th>
						<th data-options="field:'deployType',width:90,formatter:dType">部署方式</th>
						<th data-options="field:'memo',width:250, formatter:showDeployingTutorial">备注（非weblogic容器点击查看部署方法）</th>
						<th data-options="field:'packageType',width:40,align:'center',formatter:pType">打包类型</th>
						<th data-options="field:'osType',width:60,align:'center',formatter:oType">操作系统</th>
						<th data-options="field:'middlewarePath',width:126,align:'center'">中间件路径</th>
						<th data-options="field:'specificPath',width:126,align:'center'">特定路径(tomcat hop4.0)</th>
						<th data-options="field:'deployNodes',width:90">部署节点</th>
						<th data-options="field:'checkUrl',width:126,align:'center'">检查URL</th>
						<th data-options="field:'engineer',width:126,align:'center'">开发工程师</th>
						<th data-options="field:'engineerMail',width:126,align:'center'">开发工程师邮箱</th>
						<th data-options="field:'engineerNumber',width:126,align:'center'">开发工程师电话</th>
						<th data-options="field:'psiConner',width:126,align:'center'">PSI接口人</th>
						<th data-options="field:'psiConnerNumber',width:126,align:'center'">PSI接口人电话</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="tb" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a href="${dynamicURL}/deploy/createAppRelateServerInit.action"
					class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
	            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" 
	                onclick="delAppRelateServer()">删除</a>
			</div>
		</div>
		<div id="cwb" class="easyui-dialog" title="更改密码" style="width:400px;height:200px;"
	        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
	        <form id="ff" method="post">
		        <table id="changebox">
		          <tr>
		        	<td>用户名</td>
		        	<td><input id="username_" class="easyui-validatebox" readonly="readonly" type="text" name="appRelateServer.login" data-options="required:true" /></td>
		          </tr>
		          <tr>
		        	<td>密码</td>
		        	<td><input id="password_" class="easyui-validatebox" type="text" name="appRelateServer.passwd" data-options="required:true" /></td>
		          </tr>
		          <input id="id_" type="text" name="appRelateServer.id" style="display:none" />
		          <div id="button">
		    		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitCWB()">提交</a>
		        	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeCWB()">取消</a>
		     	  </div>
		        </table>
	        </form>
		</div>
	</div>
	<form method="post" id="fm" style="display: none;"></form>
	<script type="text/javascript">
		
		function mType(val, row){
			if(row.middlewareType == 1) {return "<span>weblogic</span>";}
			if(row.middlewareType == 2) {return "<span>dubbo</span>";}
			if(row.middlewareType == 3) {return "<span>tomcat</span>";}
			if(row.middlewareType == 4) {return "<span>jboss</span>";}
			if(row.middlewareType == 6) {return "<span>hop4.0 static</span>";}
			if(row.middlewareType == 7) {return "<span>hop4.0 war</span>";}
		}
		function pType(val, row){
			if(row.packageType == 1) {return "<span>war</span>";}
			if(row.packageType == 2) {return "<span>tar</span>";}
			if(row.packageType == 3) {return "<span>zip</span>";}
		}
		function oType(val, row){
			if(row.osType == 0) {return "<span>linux</span>";}
			if(row.osType == 1) {return "<span>windows</span>";}
		}
		function dType(val, row){
			if (row.deployType == 0) {return "<span>不适用</span>";}
			if (row.deployType == 1) {return "<span>热部署</span>";}
			if (row.deployType == 2) {return "<span>卸载后部署</span>";}
			if (row.deployType == 3) {return "<span>部署部分节点</span>";}
			if (row.deployType == 4) {return "<span>解压部署</span>";}
			if (row.deployType == 5) {return "<span>解压热部署</span>";}
		}
		
		function searchUaP(val, row) {
			var $cpage = $('#dg').datagrid('getPager').data("pagination").options.pageNumber;
			if (row.middlewareType == '1') 
				return "<a href='${dynamicURL}/deploy/searchUnixInfoInit.action?weblogicId="+row.weblogicId+"'>" + val + "</a>";
			else
				return "<a href='${dynamicURL}/deploy/searchNWUnixInfoInit.action?weblogicId="+row.weblogicId+"'>" + val + "</a>";
		}
	
		function showAppServer(val, row) {
			var $cpage = $('#dg').datagrid('getPager').data("pagination").options.pageNumber;
			if (row.middlewareType == '1') {
				return "<a href='${dynamicURL}/deploy/searchAppServerInit.action?weblogicId="+row.weblogicId+"'>" + val + "</a>";
			}
		}
		
		function updateARS(val, row) {
			var $cpage = $('#dg').datagrid('getPager').data("pagination").options.pageNumber;
			return "<a href='${dynamicURL}/deploy/updateAppRelateServerInit.action?applicationId="+row.applicationId+"'>" + val + "</a>";
		}
		
		function showDeployingTutorial(val, row) {
			var $cpage = $('#dg').datagrid('getPager').data("pagination").options.pageNumber;
			return "<a href='${dynamicURL}/deploy/updateNotWeblogicDeployTutorialInit.action?applicationId="+row.applicationId+"'>" + val + "</a>";
		}
		
		function changePasswd(val, row) {
			if (row.middlewareType == '1') {
				return "<span><a href='#' onclick='showCWB_("+row.weblogicId+")'>"+val+"</a><span>";
			} else {
				return val;
			}
		}
		
		function showCWB_(weblogicId) {
			$('#cwb').dialog('open');
			$('#fm').form('submit',{
		        url: '${dynamicURL}/deploy/changeWeblogicPasswordInit.action?id='+weblogicId,
		        onSubmit: function(){},
		        success: function(data){
		        	data = jQuery.parseJSON(data);
		        	$('#username_').val(data.username);
		        	$('#password_').val(data.password);
		        	$('#id_').val(weblogicId);
		        }
		    });
		}
		
		function cleanForm(){
			$("#searchForm")[0].reset();
			$("#applicationAbbr").combobox("clear");
		}
		
		function loaddata() {
			$('#dg').datagrid('load',
					sy.serializeObject($("#searchForm").form()));
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
				url : '${dynamicURL}/deploy/searchAppRelateServer.action',
				onLoadSuccess: function(data){
					// 拿到标题列
					$(".datagrid-header-inner table tr:first-child").css("text-align", "center");
					//$('#dg').datagrid('getPager').find('input').val(${pageNumber});
				}
			});
			//alert(${pageNumber});

		});
		function delAppRelateServer(){
        	var row = $('#dg').datagrid('getSelected');
            if (!row){
            	$.messager.alert('操作提示','请选择要删除的数据！','info');
            	return;
            }
            if(confirm('确定要删除'+row.applicationAbbr+'?')){
	        	$('#fm').form('submit',{
	                url: '${dynamicURL}/deploy/deleteAppRelateServer.action?applicationId='+row.applicationId,
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
		function submitCWB() {
			$('#ff').form('submit', {
			    url:'${dynamicURL}/deploy/changeWeblogicPassword.action',
			    onSubmit: function(){
			    	return $("#ff").form('validate');
			    },
			    success:function(result){
					 handleActionResult(result,{
		        		onSuccess:function(){
		        			window.location.href = '${dynamicURL}/deploy/searchAppRelateServerInit.action';
		        		}
		        	});
				 }
			});
		}
		function closeCWB() {
			$('#cwb').dialog('close');
		}
	</script>
</body>
</html>