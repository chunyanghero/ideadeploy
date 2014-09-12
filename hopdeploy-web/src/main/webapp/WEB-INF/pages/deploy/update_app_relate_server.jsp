<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新应用及其对应服务器信息</title>
<style type="text/css">
	#button {text-align:center}
	a {margin: 10px 10px 0px 10px}
</style>
</head>
<body> 
	<div class="easyui-panel" title="更新应用及其对应服务器信息">
		<div style="padding:10px 0 10px 10px">
		<form id="updateAppRelateServer" method="post" action="${dynamicURL}/deploy/updateAppRelateServer.action">
			<s:hidden name="appRelateServer.applicationId" />
			<s:hidden name="appRelateServer.weblogicId" />
			<table class="form_table">
	            <tr>
	                <th>应用编码<span class="star">*</span>:</th>
	                <td><s:textfield cssClass="easyui-validatebox" type="text" name="appRelateServer.applicationCode" data-options="required:true" size="54" /></td>
	           
	                <th>应用简称<span class="star">*</span>:</th>
	                <td><s:textfield cssClass="easyui-validatebox" type="text" name="appRelateServer.applicationAbbr" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	            	<th>应用名称<span class="star">*</span>:</th>
	                <td><s:textfield cssClass="easyui-validatebox" type="text" name="appRelateServer.applicationName" data-options="required:true" size="54" /></td>
	                
	                <th>部署方式<span class="star">*</span>:</th>
	                <td><s:radio list="#{'0':'不适用','1':'热部', '2':'卸后部', '3':'部分节点', '4':'解压', '5':'解压热部'}" cssClass="easyui-validatebox" name="appRelateServer.deployType"  data-options="required:true" /></td>
	            </tr>
	            <tr>
	                <th>中间件类型<span class="star">*</span>:</th>
	                <td><s:radio list="#{'1':'weblogic','2':'dubbo', '3':'tomcat', '4':'jboss', '6':'4.0-static', '7':'4.0-war'}" cssClass="easyui-validatebox" name="appRelateServer.middlewareType" data-options="required:true" /></td>
	                
	                <th>打包类型<span class="star">*</span>:</th>
	                <td><s:radio list="#{'1':'war','2':'tar', '3':'zip'}" name="appRelateServer.packageType" cssClass="easyui-validatebox" data-options="required:true" /></td>
	            </tr>
	            <tr>
	            	
	                <th>服务器IP<span class="star">*</span>:</th>
	                <td><s:textfield cssClass="easyui-validatebox"  type="text" name="appRelateServer.ip" data-options="required:true" size="54" /></td>
	            
	                <th>端口<span class="star" id="portspan">*</span>:</th>
	                <td><s:textfield id="port" cssClass="easyui-validatebox" type="text" name="appRelateServer.port" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	            	<th>操作系统<span class="star">*</span>:</th>
	                <td><s:radio list="#{'0':'linux','1':'windows'}" name="appRelateServer.osType"  data-options="required:true" /></td>
	            
	                <th>节点名称<span class="star" id="nodespan">*</span>:</th>
	                <td><s:textfield id="node" cssClass="easyui-validatebox" type="text" name="appRelateServer.nodeName" classClass="easyui-validatebox" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>中间件路径<span class="star">*</span>:</th>
	                <td><s:textfield cssClass="easyui-validatebox" type="text" name="appRelateServer.middlewarePath" data-options="required:true" size="54" /></td>
	            
	                <th>检查URL（tomcat）<span class="star" id="tomcaturlspan"></span>:</th>
	                <td><s:textfield id="tomcaturl" cssClass="easyui-validatebox" type="text" name="appRelateServer.checkUrl" size="54" /></td>
	            </tr>
	            <tr>
	                <th>用户名<span class="star" id="wusernamespan">*</span>:</th>
	                <td><s:textfield id="wusername" cssClass="easyui-validatebox" type="text" name="appRelateServer.login" data-options="required:true" size="54" /></td>
	            
	                <th>密码<span class="star" id="wpasswordspan">*</span>:</th>
	                <td><s:textfield id="wpassword" cssClass="easyui-validatebox" type="text" name="appRelateServer.passwd" data-options="required:true" size="54" /></td>
	            </tr>
	            <tr>
	                <th>备注<span class="star"></span>:</th>
	                <td><s:textfield name="appRelateServer.memo" size="54" data-options="required:false" /></td>
	                
	                <th>开发负责人:</th>
	                <td><s:textfield name="appRelateServer.engineer" size="54" data-options="required:false" /></td>
	            </tr>
	            <tr>
	                <th>开发负责人邮箱:</th>
	                <td><s:textfield name="appRelateServer.engineerMail" size="54" data-options="required:false" /></td>
	                
	                <th>开发负责人电话:</th>
	                <td><s:textfield name="appRelateServer.engineerNumber" size="54" data-options="required:false" /></td>
	            </tr>
	            <tr>
	                <th>PSI接口人:</th>
	                <td><s:textfield name="appRelateServer.psiConner" size="54"  data-options="required:false" /></td>
	                
	                <th>PSI接口人电话:</th>
	                <td><s:textfield name="appRelateServer.psiConnerNumber" size="54" data-options="required:false" /></td>
	            </tr>
	            <tr>
	            	<th>特定路径（如tomcat应用路径、hop4.0 jetty路径）<span class="star" id="specificpathspan"></span>:</th>
	                <td><s:textfield id="specificpath" name="appRelateServer.specificPath" size="54" cssClass="easyui-validatebox" data-options="required:false" /></td>
	                
	                <th>指定部署节点（weblogic）<span id="specificnodespan" class="star"></span>:</th>
	                <td><s:textfield id="specificnode" name="appRelateServer.deployNodes" size="54" classClass="easyui-validatebox" data-options="required:false" /></td>
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
$(document).ready(function(){
	function notweblogic() {
		$('#node').validatebox('options').required = false;
		$('#nodespan').html('');
		$('#node').attr("readonly","readonly");
		
		$('#wusername').validatebox('options').required = false;
		$('#wusernamespan').html('');
		$('#wusername').attr("readonly","readonly");
		
		$('#wpassword').validatebox('options').required = false;
		$('#wpasswordspan').html('');
		$('#wpassword').attr("readonly","readonly");
		
		//$('#specificnode').validatebox('options').required = false;
		$('#specificnode').attr("readonly","readonly");
	}
	function weblogic() {
		$('#port').validatebox('options').required = true;
		$('#portspan').html('*');
		$('#port').removeAttr("readonly");
		
		$('#node').validatebox('options').required = true;
		$('#nodespan').html('*');
		$('#node').removeAttr("readonly");
		
		$('#wusername').validatebox('options').required = true;
		$('#wusernamespan').html('*');
		$('#wusername').removeAttr("readonly");
		
		$('#wpassword').validatebox('options').required = true;
		$('#wpasswordspan').html('*');
		$('#wpassword').removeAttr("readonly");
		
		$('#wpassword').validatebox('options').required = false;
		$('#wpassword').removeAttr("readonly");
	}
	function tomcat() {
		$('#tomcaturl').validatebox('options').required = true;
		$('#tomcaturlspan').html('*');
		$('#tomcaturl').removeAttr("readonly");
	}
	function nottomcat() {
		$('#tomcaturl').validatebox('options').required = false;
		$('#tomcaturlspan').html('');
		$('#tomcaturl').attr("readonly","readonly");
	}
	function jettytomcat() {
		
		$('#specificpath').validatebox('options').required = true;
		$('#specificpathspan').html('*');
		$('#specificpath').removeAttr("readonly");
	}
	function notjettytomcat() {
		$('#specificpath').validatebox('options').required = false;
		$('#specificpathspan').html('');
		$('#specificpath').attr("readonly","readonly");
	}
	function tomcatspecificpath() {
		$('#specificpath').validatebox('options').required = false;
		$('#specificpathspan').html('');
		$('#specificpath').removeAttr("readonly");
	}

   
	/* 禁止更新过程中更改中间件类型，为了判断输入框不能为空方便  */
	$("input[name='appRelateServer.middlewareType']").each(function(){
		if(this.checked==false)
			$(this).attr("disabled","disabled");	
	});
	$val = $("input[name='appRelateServer.middlewareType'][checked]").val()
	if ($val == 1) {
		// weblogic
		weblogic();
		nottomcat();
		notjettytomcat();
	} else	if ($val == 2) {
		// dubbo
		notweblogic();
		nottomcat();
		notjettytomcat();
	} else	if ($val == 3) {
		// tomcat
		tomcat();
		tomcatspecificpath();
		notweblogic();
	} else 	if ($val == 4) {
		// jboss
		notweblogic();	
		tomcat();
		notjettytomcat();
	} else	if ($val == 6) {
		// hop4.0 static
		jettytomcat();
		notweblogic();
		tomcat();
	} else	if ($val == 7) {
		// hop4.0 war
		jettytomcat();
		notweblogic();
		tomcat();
	}
});

function submitForm(){
	 $('#updateAppRelateServer').form('submit',{
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
	$('#updateAppRelateServer').form('clear');
}
</script>
</body>
</html>