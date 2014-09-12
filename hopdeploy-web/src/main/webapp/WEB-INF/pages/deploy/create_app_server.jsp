<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${staticURL}/scripts/upload/ajaxupload.js"></script>
<title>创建应用服务器信息</title>
</head>
<body> 
	<div class="easyui-panel" title="创建应用服务器信息">
		<div style="padding:10px 0 10px 10px">
		<form id="createAppServer" method="post" action="${dynamicURL}/deploy/createAppServer.action?id=${parentId}">
			<table class="form_table">
	            <tr>
	                <th>服务器IP<span class="star">*</span>:</th>
	                <td><input class="easyui-validatebox" type="text" name="appServer.ip" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>端口<span class="star">*</span>:</th>
	                <td><input class="easyui-validatebox" type="text" name="appServer.port" data-options="required:true" size="54" /></td>
	            </tr>
	           
	            <tr>
	                <th>节点名称<span class="star">*</span>:</th>
	                <td><input class="easyui-validatebox" type="text" name="appServer.nodeName" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>应用节点路径<span class="star">*</span>:</th>
	                <td><input class="easyui-validatebox" type="text" name="appServer.middlewarePath" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>备注<span class="star"></span>:</th>
	                <td><input class="easyui-validatebox" type="text" name="appServer.memo" data-options="required:false" size="54" /></td>
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
		 $('#createAppServer').form('submit',{
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
		$('#createAppServer').form('clear');
	}
</script>
</body>
</html>