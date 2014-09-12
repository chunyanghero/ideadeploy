<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
 
  
    <div class="easyui-panel" style="padding:5px;">
        <a href="${dynamicURL}/deploy/setDeployTimeInit.action" class="easyui-linkbutton" data-options="plain:true">待处理发版</a>
        <a href="${dynamicURL}/deploy/searchAppRelateServerInit.action" class="easyui-linkbutton" data-options="plain:true">服务器信息查询</a>
        <a href="${dynamicURL}/deploy/manualDeployInit.action" class="easyui-linkbutton" data-options="plain:true">手动发版</a>
        <a href="${dynamicURL}/deploy/searchManualDeployResultInit.action" class="easyui-linkbutton" data-options="plain:true">手动发版历史记录</a>
       	<a href="#" class="easyui-menubutton" data-options="menu:'#mm2',iconCls:'icon-search'">管理</a>
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm3'">发版教程</a>
    </div>

    <div id="mm2" style="width:100px;">
        <div><a href="http://alm.haier.net" target="_blank">ALM周期平台</a></div>
        <div><a href="http://console.haier.net" target="_blank">监控中心</a></div>
        <div><a href="http://10.135.6.66:9099/hudson/" target="_blank">HUDSON</a></div>
        <div><a href="http://10.135.31.6/" target="_blank">堡垒机</a></div>
        <div><a href="http://ump.haier.net:8080/" target="_blank">JIRA</a></div>
    </div>
    <div id="mm3" class="menu-content" style="background:#f0f0f0;padding:10px;text-align:left">
        <div><a href="#">weblogic</a></div>
        <div><a href="#">tomcat</a></div>
        <div><a href="#>hop4.0 静态页面</a></div>
        <div><a href="#">hop4.0 war包</a></div>
        <div><a href="#">dubbo</a></div>
    </div>
  
  
<div class="clearfix"></div>