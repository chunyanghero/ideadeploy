<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新非weblogic服务器发版方法</title>
<style type="text/css">
	#txt {
		text-align: center;
		margin-left: auto;
		margin-right: auto;
	}
</style>
</head>
<body>
<div class="easyui-panel" title="更新非WEBLOGIC容器发版方法">
	<h1 align="center">特殊项目部署方法</h1>
	<div style="padding:10px 0 10px 10px">
		<form id="updateDeployTutorial" method="post" action="${dynamicURL}/deploy/updateNotWeblogicDeployTutorial.action?applicationId=${applicationId}">
			<div id="txt">
				<s:textarea cols="100" rows="20" name='deployTutorial'></s:textarea>
			<br />
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="submitForm()">提交</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" onclick="clearForm()">重置</a>
		        <a href="${dynamicURL}/deploy/searchAppRelateServerInit.action" 
            		class="easyui-linkbutton" iconCls="icon-back" >返回</a>
	        </div>
		</form>
	</div>
</div>
<script type="text/javascript">
	function submitForm(){
		 $('#updateDeployTutorial').form('submit',{
			 onSubmit: function(){
                 return $(this).form('validate');
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
	function clearForm(){
		$('#updateDeployTutorial').form('clear');
	}
</script>
</body>
</html>