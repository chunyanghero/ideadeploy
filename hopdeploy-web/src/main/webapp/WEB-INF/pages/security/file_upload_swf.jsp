<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="p" uri="/pagination-tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${staticURL}/scripts/upload/ajaxupload.js"></script>
<script>
               
$(document).ready(function(){	
	$('#fileTable').datagrid({
		title:'附件信息上传',
		fit:true,
    	fitColumns:true,
    	collapsible: true,
    	rownumbers: true, //显示行数 1，2，3，4...
    	pagination: true, //显示最下端的分页工具栏
    	pagePosition : 'bottom',
    	pageList: [5,10,15,20], //可以调整每页显示的数据，即调整pageSize每次向后台请求数据时的数据
    	pageSize: 20, //读取分页条数，即向后台读取数据时传过去的值
		url : '${dynamicURL}/security/searchFileUploadBySwf.action?file.status=1' + '&time=' + new Date(),
		loadMsg : '数据装载中......',
		remoteSort : false,
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		} ] ],
		 columns : [ [ {
			title : '文件名',
			field : 'fileName',
			width : '200',
			formatter :function(value,rowData,rowIndex){
				return "<a target='_blank' href='download.action?id="+rowData.id+"&fileName="+rowData.fileName+"'>" + rowData.fileName + "</a>";  
			}
		}, {
			title : '上传者',
			field : 'createBy',
			width : '80'
		} , {
			title : '上传时间',
			field : 'createDate',
			width : '150'
		} , {
			title : '存储路径',
			field : 'filePath1',
			width : '200'
		} ] ], 
		toolbar : [ {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				$('#fileTable').datagrid('reload');
				/* $('#fileTable').datagrid({
					url : '../security/searchFileUploadBySwf.action?status=1' + '&time=' + new Date(),
				}); */
			}
		}, '-',  {
			text : '删除',
			iconCls : 'icon-remove',
			handler : del
		}, '-', {
			id : 'uploadImg',
			iconCls : 'icon-search',
			text : '上传'
		}]
	});
	
	new AjaxUpload('uploadImg', {
        action: '${dynamicURL}/security/fileUploadBySwf.action',
        name:'fileInput',
        responseType:'json',
		onSubmit : function(file , ext){
            // Allow only images. You should add security check on the server-side.
			if (ext && /^(jpg|png|jpeg|gif)$/.test(ext)){
				/* Setting data */
				this.setData({
					'fieldUri' : "RESOURCE_ICON",//服务器存放路径 assets下新建文件夹
					'file' : file//文件名称
				}); 				
			} else {					
				// extension is not allowed
				alert('Error: only images are allowed');
				// cancel upload
				return false;				
			}		
		},
		onComplete : function(file,data){
	    	if(data.resultMessage.indexOf('Error') >= 0){
	    		$.messager.alert('warning',data.resultMessage,'warning');
	    	}else{
	    		$.messager.alert('info', 'success', 'info');
	    		$('#fileTable').datagrid('reload');
	    	}
		}		
	});
});		

function del() {
	var selected = $('#fileTable').datagrid('getSelections');
	if (selected.length>0) {
		$.messager.confirm('warning', '确认删除么?', function(id) {
			if (id) {
				var ids = [];
				for(var i=0; i<selected.length; i++){
				    ids.push(selected[i].id + ':' + selected[i].fileName);
				}
				$.ajax({
					type : "POST",
					url : "${dynamicURL}/security/deleteUploadFile.action",
					data : "ids=" + ids,
					dataType : "json",
					success : function callback(data, textStatus) {
						if(data.resultMessage == 'success'){
							$.messager.alert('info', '删除成功!', 'info');
						}else{
							$.messager.alert('error', data.resultMessage, 'error');
						}
						$('#fileTable').datagrid('reload');
					}
				});
				
			}
		});
	} else {
		$.messager.alert('warning', '请选择一行数据', 'warning');
	}
}
</script>
<title><s:text name="附件信息上传"/></title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="center">
			<table id="fileTable"></table>
		</div>
	</div>
</body>
</html>