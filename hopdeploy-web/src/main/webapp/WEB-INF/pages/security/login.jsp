<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/common/meta.jsp" %>
    <title>HOPDEPLOY系统登录</title>
    <link rel="shortcut icon" href="${staticURL}/images/haier3.ico" />
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-${easyUIVersion}/themes/${easyUITheme}/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-${easyUIVersion}/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-${easyUIVersion}/themes/hop.css?t=2"/>
    <script type="text/javascript" src="${staticURL}/portal/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${staticURL}/jquery-easyui-${easyUIVersion}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${staticURL}/jquery-easyui-${easyUIVersion}/hop.js"></script>
    <script type="text/javascript" src="${staticURL}/jquery-easyui-${easyUIVersion}/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <style type="text/css">
    	#login {
    		margin:100px;
    	}
    </style>
</head>
<body>
<center>
<div id="login">
	<div class="easyui-panel" title="发版管理系统登录" style="width:400px;">
       <div style="padding:10px 60px 20px 60px">
       <form id="ff" method="post" action="${dynamicURL}/security/login.action">
           <table cellpadding="5">
               <tr>
                   <td>用户名:</td>
                   <td><input class="easyui-validatebox" type="text" name="username" data-options="required:true"></input></td>
               </tr>
               <tr>
                   <td>密 码:</td>
                   <td><input class="easyui-validatebox" type="password" name="password" data-options="required:true"></input></td>
               </tr>
           </table>
	       <div style="text-align:center;padding:5px">
	           <input type="button" id="but_sm" value="提交" onclick="submit();"/>
	           <input type="button" value="重置" onclick="clearForm()">
	       </div>
       </form>
       </div>
	</div>
</div>
</center>
<script>
	$(function(){  
	    document.onkeydown = function(e){    
		    var ev = document.all ? window.event : e;  
		    if(ev.keyCode==13) {
		    	// 如（ev.ctrlKey && ev.keyCode==13）为ctrl+Center 触发要处理的事件
		    	$("#but_sm").trigger("click");
	    }  
	  }  
	});
   function clearForm(){
       $('#ff').form('clear');
   }
</script>
</body>
</html>