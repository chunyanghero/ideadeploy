<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>日志查询</title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="north" border="false" collapsible="true" collapsed="false" 
		     class="zoc" title="查询条件" style="height: 60px; overflow: auto;">
			<form onsubmit="return false;" id="searchForm">
		        <table>
		        	<tr>
						<td>开始时间:</td>
						<td><input id="from" class="easyui-datebox" name="searchModel.from" data-options="formatter:defaultDateFormatter,parser:defaultDateParser"></input></td>
						<td>结束时间:</td>
						<td><input id="to" class="easyui-datebox" name="searchModel.to" data-options="formatter:defaultDateFormatter,parser:defaultDateParser"></input></td>
						<td><a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loaddata()">查询</a></td>
					</tr>
				</table>
			</form>
	    </div>
	    <div region="center" border="false">
		    <table id="dg">
		        <thead>
		            <tr>
		            	<th field="userName" width="120">操作人</th>
		                <th data-options="field:'gmtCreate',width:90,formatter:formatDateTime"  width="150">操作时间</th>
		                <th field="description" >事件</th>
		            </tr>
		        </thead>
		        
			</table>
		</div>
	</div>
	<script type="text/javascript">
        function loaddata(){
            $('#dg').datagrid('reload',sy.serializeObject($("#searchForm").form()));
        }
        function formatDateTime(val,row){
        	var date = new Date(val.time);
        	return defaultDateTimeFormatter(date);
        }
        $(function(){
       	   var from = new Date();
       	   var date = from.getDate();
       	   from.setDate(date-1);
       	   $("#from").datebox("setValue", defaultDateFormatter(from)); 
       	   $("#to").datebox("setValue", defaultDateFormatter(new Date())); 
       	});  
        $(function(){
	        $('#dg').datagrid({
	        	queryParams: {
	        		'searchModel.from': $('#from').datebox('getValue'),
	        		'searchModel.to': $('#to').datebox('getValue'),
	        	},
	        	title:'日志查询',
	        	fit:true,
	        	singleSelect:true,
	        	fitColumns:true,
	        	rownumbers: true, //显示行数 1，2，3，4...
	        	pagination: true, //显示最下端的分页工具栏
	        	pageList: [5,10,15,20], //可以调整每页显示的数据，即调整pageSize每次向后台请求数据时的数据
	        	pageSize: 20, //读取分页条数，即向后台读取数据时传过去的值
	        	url:'${dynamicURL}/security/searchOperationLog.action',
	        });
        });  
    </script>
</body>
</html>