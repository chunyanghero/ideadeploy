<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${staticURL}/scripts/upload/ajaxupload.js"></script>
<title>创建应用及其管理服务器信息</title>
<style type="text/css">
	#button {text-align:center}
	a {margin: 10px 10px 0px 10px}
</style>
</head>
<body> 
	<div class="easyui-panel" title="创建应用及其管理服务器信息">
		<div style="padding:10px 0 10px 10px">
		<form id="createAppRelateServer" method="post" action="${dynamicURL}/deploy/createAppRelateServer.action">
			<table class="form_table">
				<tr>
	                <th>应用编码<span class="star">*</span>:</th>
	                <td><input class="easyui-validatebox" type="text" name="appRelateServer.applicationCode" data-options="required:true" size="54" /></td>
	           
	                <th>应用简称<span class="star">*</span>:</th>
	                <td><input class="easyui-validatebox" type="text" name="appRelateServer.applicationAbbr" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	            	<th>应用名称<span class="star">*</span>:</th>
	                <td><input class="easyui-validatebox" type="text" name="appRelateServer.applicationName" data-options="required:true" size="54" /></td>
	                
	                <th>部署方式<span class="star">*</span>:</th>
	                <td><s:radio list="#{'0':'不适用','1':'热部', '2':'卸后部', '3':'部分节点', '4':'解压', '5':'解压热部'}" name="appRelateServer.deployType" value="1" data-options="required:true" /></td>
	            </tr>
	            <tr>
	                <th>中间件类型<span class="star">*</span>:</th>
	                <td><s:radio list="#{'1':'weblogic','2':'dubbo', '3':'tomcat', '4':'jboss', '6':'4.0-static', '7':'4.0-war'}" name="appRelateServer.middlewareType" id="mwtype" value="1" data-options="required:true" /></td>
	                
	                <th>打包类型<span class="star">*</span>:</th>
	                <td><s:radio list="#{'1':'war','2':'tar', '3':'zip'}" name="appRelateServer.packageType" value="1" data-options="required:true" /></td>
	            </tr>
	            <tr>
	            	
	                <th>服务器IP<span class="star">*</span>:</th>
	                <td><input class="easyui-validatebox" type="text" name="appRelateServer.ip" data-options="required:true" size="54" /></td>
	            
	                <th>端口<span class="star" id="portspan">*</span>:</th>
	                <td><input id="port" class="easyui-validatebox" type="text" name="appRelateServer.port" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	            	<th>操作系统<span class="star">*</span>:</th>
	                <td><s:radio list="#{'0':'linux','1':'windows'}" name="appRelateServer.osType" value="0" data-options="required:true" /></td>
	            
	                <th>节点名称<span class="star" id="nodespan">*</span>:</th>
	                <td><input id="node" class="easyui-validatebox" type="text" name="appRelateServer.nodeName" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>中间件路径<span class="star">*</span>:</th>
	                <td><input class="easyui-validatebox" id="mpath" type="text" name="appRelateServer.middlewarePath" data-options="required:true" size="54" /></td>
	            
	                <th>检查URL（tomcat）<span class="star" id="tomcaturlspan"></span>:</th>
	                <td><input id="tomcaturl" class="easyui-validatebox" type="text" name="appRelateServer.checkUrl" size="54" readonly="readonly" /></td>
	            </tr>
	            <tr>
	                <th>WEBLOGIC用户名<span class="star" id="wusernamespan">*</span>:</th>
	                <td><input id="wusername" class="easyui-validatebox" type="text" name="appRelateServer.login" data-options="required:true" size="54" /></td>
	            
	                <th>WEBLOGIC密码<span class="star" id="wpasswordspan">*</span>:</th>
	                <td><input id="wpassword" class="easyui-validatebox" type="text" name="appRelateServer.passwd" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>备注<span class="star"></span>:</th>
	                <td><input name="appRelateServer.memo" size="54" classClass="easyui-validatebox" data-options="required:false" /></td>
	                
	                <th>开发负责人<span class="star"></span>:</th>
	                <td><input name="appRelateServer.engineer" size="54" classClass="easyui-validatebox" data-options="required:false" /></td>
	                
	            </tr>
	            <tr>
	                <th>开发负责人邮箱<span class="star"></span>:</th>
	                <td><input name="appRelateServer.engineerMail" size="54" classClass="easyui-validatebox" data-options="required:false" /></td>
	                
	                <th>开发负责人电话<span class="star"></span>:</th>
	                <td><input name="appRelateServer.engineerNumber" size="54" classClass="easyui-validatebox" data-options="required:false" /></td>
	                
	            </tr>
	            <tr>
	                <th>PSI接口人<span class="star"></span>:</th>
	                <td><input name="appRelateServer.psiConner" size="54" classClass="easyui-validatebox" data-options="required:false" /></td>
	                
	                <th>PSI接口人电话<span class="star"></span>:</th>
	                <td><input name="appRelateServer.psiConnerNumber" size="54" classClass="easyui-validatebox" data-options="required:false" /></td>
	                
	            </tr>
	            <tr>
	            	<th>特定路径（如tomcat应用路径、hop4.0 jetty路径）<span id="specificpathspan" class="star"></span>:</th>
	                <td><input id="specificpath" class="easyui-validatebox" name="appRelateServer.specificPath" size="54" classClass="easyui-validatebox" data-options="required:false" readonly="readonly" /></td>
	                
	            	<th>指定部署节点<span id="specificnodespan" class="star"></span>:</th>
	                <td><input id="specificnode" class="easyui-validatebox" name="appRelateServer.deployNodes" size="54" classClass="easyui-validatebox" data-options="required:false" /></td>
	            </tr>
	            
	        </table>
	        <div id="button">
	                 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="submitForm()">提交</a>
				     <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" onclick="clearForm()">重置</a>
			</div>	     
		</form>
		</div>
    </div>
    
<script type="text/javascript">
	function notweblogic() {
		/* $('#port').validatebox('options').required = false;
		$('#portspan').html('');
		$('#port').val('none');
		$('#port').attr("readonly","readonly"); */
		
		$('#node').validatebox('options').required = false;
		$('#nodespan').html('');
		$('#node').val('');
		$('#node').attr("readonly","readonly");
		
		$('#wusername').validatebox('options').required = false;
		$('#wusernamespan').html('');
		$('#wusername').val('');
		$('#wusername').attr("readonly","readonly");
		
		$('#wpassword').validatebox('options').required = false;
		$('#wpasswordspan').html('');
		$('#wpassword').val('');
		$('#wpassword').attr("readonly","readonly");
		
		$('#specificnode').validatebox('options').required = false;
		$('#specificnode').val('');
		$('#specificnode').attr("readonly","readonly");
		
		
	}
	function weblogic() {
		$('#port').validatebox('options').required = true;
		$('#portspan').html('*');
		$('#port').val('');
		$('#port').removeAttr("readonly");
		
		$('#node').validatebox('options').required = true;
		$('#nodespan').html('*');
		$('#node').val('');
		$('#node').removeAttr("readonly");
		
		$('#wusername').validatebox('options').required = true;
		$('#wusernamespan').html('*');
		$('#wusername').val('');
		$('#wusername').removeAttr("readonly");
		
		$('#wpassword').validatebox('options').required = true;
		$('#wpasswordspan').html('*');
		$('#wpassword').val('');
		$('#wpassword').removeAttr("readonly");
		
		$('#wpassword').validatebox('options').required = false;
		$('#wpassword').val('');
		$('#wpassword').removeAttr("readonly");
		
	}
	function tomcat() {
		$('#tomcaturl').validatebox('options').required = true;
		$('#tomcaturlspan').html('*');
		$('#tomcaturl').val('');
		$('#tomcaturl').removeAttr("readonly");
	}
	function nottomcat() {
		$('#tomcaturl').validatebox('options').required = false;
		$('#wusernamespan').html('');
		$('#tomcaturl').val('');
		$('#tomcaturl').attr("readonly","readonly");
	}
	function jettytomcat() {
		
		$('#specificpath').validatebox('options').required = true;
		$('#specificpathspan').html('*');
		$('#specificpath').val('');
		$('#specificpath').removeAttr("readonly");
	}
	function notjettytomcat() {
		$('#specificpath').validatebox('options').required = false;
		$('#specificpathspan').html('');
		$('#specificpath').val('');
		$('#specificpath').attr("readonly","readonly");
	}
	function tomcatspecificpath() {
		$('#specificpath').validatebox('options').required = false;
		$('#specificpathspan').html('');
		$('#specificpath').val('若webapp非默认路径，须在此指定，否则需清空');
		$('#specificpath').removeAttr("readonly");
	}
	
	$('[name="appRelateServer.middlewareType"]').change(function(){
		if($(this).val() == 1){
			weblogic();
			nottomcat();
			notjettytomcat();
		}
		if($(this).val() == 2){
			notweblogic();
			nottomcat();
			notjettytomcat();
		}
		if($(this).val() == 3){
			notweblogic();		
			tomcat();
			tomcatspecificpath();
			
		}
		if($(this).val() == 4){
			notweblogic();	
			tomcat();
			notjettytomcat();
		}
		if($(this).val() == 6){
			jettytomcat();
			notweblogic();
			tomcat();
			$('#specificpath').val('HOP4.0 jetty路径');
		}
		if($(this).val() == 7){
			jettytomcat();
			notweblogic();
			tomcat();
			$('#specificpath').val('HOP4.0 jetty路径');
		}
	});
	function submitForm(){
		 $('#createAppRelateServer').form('submit',{
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
		$('#createAppRelateServer').form('clear');
	}
</script>
</body>
</html>