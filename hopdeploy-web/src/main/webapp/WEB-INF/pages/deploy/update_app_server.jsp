<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新应用服务器信息</title>
</head>
<body> 
	<div class="easyui-panel" title="更新应用服务器信息">
		<div style="padding:10px 0 10px 10px">
		<form id="updateAppServer" method="post" action="${dynamicURL}/deploy/updateAppServer.action">
			<s:hidden name="appServer.id" />
			<s:hidden name="appServer.parentId" />
			<table class="form_table">
	            <tr>
	                <th>服务器IP<span class="star">*</span>:</th>
	                <td><s:textfield cssClass="easyui-validatebox" type="text" name="appServer.ip" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>端口<span class="star">*</span>:</th>
	                <td><s:textfield cssClass="easyui-validatebox" type="text" name="appServer.port" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>节点名称<span class="star">*</span>:</th>
	                <td><s:textfield cssClass="easyui-validatebox" type="text" name="appServer.nodeName" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>应用节点路径<span class="star">*</span>:</th>
	                <td><s:textfield cssClass="easyui-validatebox" type="text" name="appServer.middlewarePath" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>备注<span class="star">*</span>:</th>
	                <td>
	                	<s:textfield name="appServer.memo" size="54" Class="easyui-validatebox" data-options="required:false" />
	                </td>
	            </tr>
	            <tr>
	                <td colspan="2">
	                	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="submitForm()">提交</a>
				        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" onclick="clearForm()">重置</a>
				    </td>
	            </tr>
	        </table>
	     </form>
	     </div>
	</div>
<script type="text/javascript">
	function submitForm(){
		 $('#updateAppServer').form('submit',{
			 onSubmit: function(){
                 return $(this).form('validate');
             },
			 success:function(result){
				 handleActionResult(result,{
             		onSuccess:function(){
             			window.location.href = '${dynamicURL}/deploy/searchAppServerInit.action?weblogicId=${parentId}';
             		}
             	});
			 }
		 });
	}
	function clearForm(){
		$('#updateAppServer').form('clear');
	}
</script>
</body>
</html>