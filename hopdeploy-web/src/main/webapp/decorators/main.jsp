<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<html>
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>HOPDEPLOY系统</title>
    <link rel="shortcut icon" href="${staticURL}/images/haier3.ico" />
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-${easyUIVersion}/themes/${easyUITheme}/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-${easyUIVersion}/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${staticURL}/jquery-easyui-${easyUIVersion}/themes/hop.css?t=2"/>
    <script type="text/javascript" src="${staticURL}/portal/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${staticURL}/jquery-easyui-${easyUIVersion}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${staticURL}/jquery-easyui-${easyUIVersion}/hop.js"></script>
    <script type="text/javascript" src="${staticURL}/jquery-easyui-${easyUIVersion}/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <style>
    	a { TEXT-DECORATION:none }
		/* unclear all underlines of anchor tags */
		td a { TEXT-DECORATION:none }
		td a:hover { TEXT-DECORATION:underline }
    </style>
    <decorator:head/>
</head>
<body <decorator:getProperty property="body.id" writeEntireProperty="true"/> <decorator:getProperty property="body.class" writeEntireProperty="true"/> >
	<div><!--  class="navbar navbar-inverse navbar-fixed-top" role="navigation"> -->
		<jsp:include page="/common/header.jsp" flush="true"/>
		<decorator:body/>
	</div>
</body>
</html>