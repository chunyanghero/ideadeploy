<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="/security-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手动部署</title>
<style type="text/css">
	#panel { position: relative; }
	#deploy {
		position: absolute;
		left: 20px;
	    top: 15px;
	}
	.pad {
		 padding: 5px;
	}
	#tasklist {
		position: absolute;
		top: 100px;
		left: 25px;
		width: 1300px;
		height: 400px;
		border: solid 1px gray;
		overflow: auto;
		/*background-color:red;*/
		
	}
	/* #tip {
		
		position: absolute;
		top: 40px;
		left: 510px;
	} */
	#weblogicShell {
		position: absolute;
		top: 0px;
		left: 550px;
	}
	#clearWeblogicShell {
		/* margin: 48px 5px 3px 10px; */
		position: absolute;
		z-index: 10; 
		top: 110px;
		right: 30px;
	}
</style>
</head>
<body>
<div id="panel" class="easyui-panel" fit="true" border="true" title="部署应用" style="height: 500px;overflow: auto;">
	<div id="deploy">
		<form id="ff" method="post">
		<table>
		<tr>
			<td class="pad">应用名称</td>
			<td>版本号</td>
			<td></td>
			<td class="pad">打包类型</td>
			<td class="pad"></td>
		</tr>
		<tr>
			<td class="pad">
				<input class="easyui-combobox" id="applicationAbbr" name="packageName" 
					data-options="
		                  url:'${dynamicURL}/deploy/searchAppRelateServerForCombobox.action',
		                  method:'get',
		                  valueField:'applicationAbbr',
		                  textField:'applicationAbbr',
		                  panelHeight:'250',
		                  required:'true' "/>
			</td>
			<td>
				<input class="easyui-datebox" data-options="formatter:myformatter"
				 name="packageNamePart1"  style="width:90px" />
			</td> <!-- 2014 06 23 -->
			<td>
				<input id="ss" name="packageNamePart2" class="easyui-numberspinner" style="width:40px;" data-options="
		                onChange: function(value){
							if (value < 10) {
								var cc = $('#ss').numberspinner('options');  
								//cc.prefix='0';
								$('#ss').numberspinner({prefix:'0'});
							} else {
							var cc = $('#ss').numberspinner('options');  
								//cc.prefix='0';
								$('#ss').numberspinner({prefix:''});
							}
		                },
		                prefix:'', 
		                min:1 " />
			</td>
			<td class="pad">
				<select name="packageNamePart3">
		  		<option value="war">war</option>
				<option value="tar">tar</option>
				<option value="zip">zip</option>
				</select>
			</td> <!-- middlewareType -->
			<td class="pad">
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loaddata()">部署</a>
			</td>
		</tr>
		</table>
		</form>
	</div>
	
	<div id="weblogicShell">
		<br />
		<form method="post" id="weblogicShellForm">
		<table>
		<tr>
			<td class="pad">weblogic脚本所在服务器IP</td>
			<td class="pad">用户名</td>
			<td class="pad">密码</td><td></td>
		</tr>
		<tr>
			<td class="pad"><s:textfield type="text" name="weblogicShell.weblogicShellIp" /></td>
			<td class="pad"><s:textfield type="text" name="weblogicShell.weblogicShellUsername" /></td>
			<td class="pad"><s:textfield type="password" name="weblogicShell.weblogicShellPassword" /></td>
			<td><a href="#" class="easyui-linkbutton" onclick="updateWeblogicShell()">更新</a></td>
		</tr>
		</table>
		</form>
	</div>
	<a href="#" id="clearWeblogicShell" class="easyui-linkbutton" iconCls="icon-reload"  onclick="clearWeblogicShell()">清除</a>
	<div id="tasklist" >
		
	</div>
<form method="post" id="fm" style="display: none;"></form>
</div>
<script type="text/javascript">
function clearWeblogicShell(){
	$("#tasklist").empty();
}
function updateWeblogicShell(){
	$('#weblogicShellForm').form('submit',{
		url:'${dynamicURL}/deploy/updateWeblogicShell.action',
		onSubmit: function(){ 
		}, 
		success:function(data){
			window.location.href='${dynamicURL}/deploy/manualDeployInit.action';
		} 
	});
}
function loaddata(){
	$('#ff').form('submit', { 
		url:'${dynamicURL}/deploy/manualDeploy.action', 
		onSubmit: function(){ 
			return $("#ff").form('validate')
		}, 
		success:function(data){
			data = jQuery.parseJSON(data);
			if(data.state=='success'){
				var task=data.task.result;

				$.each(task, function(key, val) {
					var tli = "<span>"+val+"</span><br />";
					$("#tasklist").append(tli);
				});
			}else{
				var task=data.task.result;

				$.each(task, function(key, val) {
					var tli = "<span>"+val+"</span><br />";
					$("#tasklist").append(tli);
				});
			}
		} 
	});
}

function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+(m<10?('0'+m):m)+(d<10?('0'+d):d);
}

function cleanForm(){
	$("#searchForm")[0].reset();
	$("#applicationAbbr").combobox("clear");
}
</script>
</body>
</html>