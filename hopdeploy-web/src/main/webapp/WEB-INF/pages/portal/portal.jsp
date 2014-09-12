<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="${staticURL}/portal/js/HoorayLibs/hooraylibs.css" />
<link rel="stylesheet" href="${staticURL}/portal/img/ui/index.css" />
<link rel="stylesheet" href="${staticURL}/portal/img/skins/default.css" id="window-skin" />
<link rel="shortcut icon" href="${staticURL}/images/haier.ico"/>
<script src="${staticURL}/portal/js/jquery-1.8.3.min.js"></script>
<script src="${staticURL}/portal/js/HoorayLibs/hooraylibs.js" ></script>
<script src="${staticURL}/portal/js/util.js"></script>
<script src="${staticURL}/portal/js/core.js"></script>
<script src="${staticURL}/portal/js/templates.js"></script>
<script src="${staticURL}/portal/js/hros.app.js"></script>
<script src="${staticURL}/portal/js/hros.appmanage.js"></script>
<script src="${staticURL}/portal/js/hros.base.js"></script>
<script src="${staticURL}/portal/js/hros.desktop.js"></script>
<script src="${staticURL}/portal/js/hros.dock.js"></script>
<script src="${staticURL}/portal/js/hros.folderView.js"></script>
<script src="${staticURL}/portal/js/hros.grid.js"></script>
<script src="${staticURL}/portal/js/hros.maskBox.js"></script>
<script src="${staticURL}/portal/js/hros.navbar.js"></script>
<script src="${staticURL}/portal/js/hros.popupMenu.js"></script>
<script src="${staticURL}/portal/js/hros.taskbar.js"></script>
<script src="${staticURL}/portal/js/hros.uploadFile.js"></script>
<script src="${staticURL}/portal/js/hros.wallpaper.js"></script>
<script src="${staticURL}/portal/js/hros.widget.js"></script>
<script src="${staticURL}/portal/js/hros.window.js"></script>
<script src="${staticURL}/portal/js/hros.zoom.js"></script>
<script src="${staticURL}/portal/js/artDialog4.1.6/jquery.artDialog.js?skin=default"></script>
<script src="${staticURL}/portal/js/artDialog4.1.6/plugins/iframeTools.js"></script>
</head>

<body onload="initPortalPush()">

<s:if test="%{memberQuery.desknum && memberQuery.desknum > 0 }">
	<s:set var="desknum" value="%{memberQuery.desknum}" />
</s:if>
<s:else>
	<s:set var="desknum" value="5" />
</s:else>

<div class="loading"></div>
<!-- 桌面 -->
<div id="desktop">
	<div id="zoom-tip"><div><i>​</i>​<span></span></div><a href="javascript:;" class="close" onClick="HROS.zoom.close();">×</a></div>
    <div id="header" class="head" style="height:43px;position: absolute; width:100%">
    	<div class="head-body">
            <div class="head-body-b">
               <div class="l-logo">
                   <div class="l-head">海尔SHOWCASE系统</div>
               </div>
               <div class="head-body-b-float">
	               <div class="b-button-01">
	                   <div class="button-left">&nbsp;</div>
	                   <div class="button-right">&nbsp;</div>
	                   <a href="#"><div class="button-bg">
	                         <span class="bg"><s:property value="#session['_user_name']"/></span>
	                   </div></a>
	               </div>
	               <div class="b-button-01 b-button-02">
	                   <div class="button-left">&nbsp;</div>
	                   <div class="button-right">&nbsp;</div>
	                   <a href="javascript:;" onclick="openSetting()"><div class="button-bg">
	                         <span class="bg-01">设置</span>
	                   </div></a>
	               </div>
	               <div class="b-button-01 b-button-02">
	                   <div class="button-left">&nbsp;</div>
	                   <div class="button-right">&nbsp;</div>
	                   <a href="logout.action"><div class="button-bg">
	                         <span class="bg-02">退出</span>
	                   </div></a>
	               </div>
	               <div class="b-button-01 b-button-02">
	                   <div class="button-left">&nbsp;</div>
	                   <div class="button-right">&nbsp;</div>
	                   <a href="#" id="hideHeader"><div class="button-bg">
	                         <span >隐藏</span>
	                   </div></a>
	               </div>
               </div>
         </div>
    </div>
    <div class="head-hook"><a href="#"></a></div>
    
    </div>
	<div id="desk" style="top:43px;height: 372px;">
    	<!-- <div id="desk-header" style="background-color:#CCC; height:40px;position: absolute;">desh头部</div> -->
		<div id="desk-6" class="desktop-container nomove">
			<div class="scrollbar scrollbar-x"></div><div class="scrollbar scrollbar-y"></div>
			<div id="homeContainer">
				<div class="main-content">
					<div class="main-content-navi">
						<div class="nav-hc">
							<div class="handdle-left-hc" ><a href="javascript:void(0)">&nbsp;</a></div>
							<div class="handdle-right-hc" ><a href="javascript:void(0)">&nbsp;</a></div>
							
							<ul id="todotasks">
								<s:iterator value="%{moduleList}" id='res' status='status'> 
								    <s:if test="#status.Last">
								    <li class="last-li" title="${description}" src="${dynamicURL}<s:property value='resources.get(0).url'/>"  resid="<s:property value='id'/>" id="resource_<s:property value='id'/>"/>
								    </s:if> 
								    <s:else>
								    <li  title="${description}" src="${dynamicURL}<s:property value='resources.get(0).url'/>"  resid="<s:property value='id'/>"  id="resource_<s:property value='id'/>" />
								    </s:else>
								    <div class="main-content-navi-icon">
								    	<s:if test="fileId != null && fileId != ''">
								    		<img width="30px" height="30px" style="margin-top:-8px;" src="${dynamicURL}/security/download.action?id=<s:property value='fileId'/>" />
								    	</s:if>
								    	<s:else>
								    		<img width="30px" height="30px" style="margin-top:-8px;" src="${staticURL}/images/todo.png" />
								    	</s:else>
										<div class="live-tip">8</div>
									</div>
									<div class="main-content-navi-icontext"><s:property value='name'/></div>
									<div class="navi-selected-icon"></div>
									</li>
								</s:iterator> 
							</ul>
						</div>
					</div>
					<div class="main-subnav">
						<div class="main-content-subnav">
						      <div class="subnav-hc">
							      	<s:iterator value="%{moduleList}" id='res' status='status'> 
							      		<ul id="subResouceId_<s:property value='id'/>"  resid="<s:property value='id'/>">
							      			<s:iterator value="%{resources}" id='r' status='status'>
							      				<li  src="${dynamicURL}<s:property value='url'/>"  >
						            				<div class="text-subnav"><s:property value='name'/></div>
						            				<div class="count-subnav">8</div>
						          				</li>
											</s:iterator> 
							      		</ul>
									</s:iterator> 
						      </div>
						</div>
					</div>
					<!-- iframe -->
					<div id="homeIframeContent" style="height:400px;width:100%;background-color: white;">
						<iframe id="homeIframe" style="height:400px;width:100%; border:0 none;" ></iframe>
					</div>
				</div>

			</div>
		</div>
		<s:bean name="org.apache.struts2.util.Counter" id="counter">
   			<s:param name="first" value="1" />
   			<s:param name="last" value="%{desknum}" /> 
   			<s:iterator>
				<div id="desk-<s:property/>" class="desktop-container">
					<div class="scrollbar scrollbar-x"></div>
					<div class="scrollbar scrollbar-y"></div>
				</div>
   			</s:iterator>
		</s:bean>
		<!-- <div id="desk-1" class="desktop-container"><div class="scrollbar scrollbar-x"></div><div class="scrollbar scrollbar-y"></div></div> -->
		<!-- <div id="desk-bottom" style="background-color:#CCC; height:40px;position: absolute;">
			desk-底部
		</div> -->
        <div id="dock-bar">
			<div id="dock-container">
				<div class="widget-hub">
					<div class="one-widget">
						<div class="one-widget-navi">
							<span>工作任务</span>
							<div class="one-widget-option">
								<div class="option-icon">
									<a href="javascript:void(0)" class="option-icon-btn">
										<img src="${staticURL}/portal/img/images/one-widget-option-img.png" />
									</a>
									<div class="opiton-container">
										<ul>
											<li><a href="#"><span>移除控件</span></a></li>
											<li style="display:none"><a href="#"><span>放大控件</span></a></li>
											<li><a href="#"><span>缩小控件</span></a></li>
											<li><a href="#"><span>刷新</span></a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<iframe id="dock_iframe" frameborder="0" src="http://top.baidu.com/clip?b=2&hd_h_info=1&p_name=%E4%BB%8A%E6%97%A5%E7%83%AD%E9%97%A8%E6%90%9C%E7%B4%A2%E6%8E%92%E8%A1%8C%E6%A6%9C" style="width: 100%; border: 0px none; height:150px;max-height:200"></iframe>
					</div>
		
					<%-- <div class="one-widget">
						<div class="one-widget-navi">
							<span>通知公告</span>
						</div>
						<!--one-widget显示内容-->
					</div> --%>
		
					<%-- <div class="one-widget">
						<div class="one-widget-navi">
							<span>个人绩效</span>
						</div>
						<!--one-widget显示内容-->
					</div> --%>
				</div>
			
			
				<!-- <div class="dock-middle">
					<div class="dock-applist"></div>
					<div class="dock-toollist">
						<a href="javascript:;" class="dock-tool-setting" title="桌面设置"></a>
						<a href="javascript:;" class="dock-tool-style" title="主题设置"></a>
					</div>
				</div> -->
			</div>
		</div>
	</div>
	<div id="task-bar-bg1"></div>
	<div id="task-bar-bg2"></div>
	<div id="task-bar">
		<div id="task-next"><a href="javascript:;" id="task-next-btn" hidefocus="true"></a></div>
		<div id="task-content">
			<div id="task-content-inner"></div>
		</div>
		<div id="task-pre"><a href="javascript:;" id="task-pre-btn" hidefocus="true"></a></div>
	</div>
	<div id="desktop_nav" class="foot" style="height:12px;position: absolute; bottom:32px; width:100%;">
	<!--<div class="foot-body-b"> --> 
		<div class="b-content" id="navContainer">
			<div id="b-content-06">
				<a href="#" index="6"><img src="${staticURL}/portal/img/images/icon-home.png" /></a>
				<a href="#" index="6" class="title-icon"><span>Home</span></a>
			</div>
			<s:bean name="org.apache.struts2.util.Counter" id="counter">
				<s:param name="first" value="1" />
				<s:param name="last" value="%{desknum}" /> 
				<s:iterator>
					<div id="b-content-0<s:property/>" class="deskNav">
						<a href="#" index="<s:property/>"><img src="${staticURL}/portal/img/images/ico_folder.png" /></a>
						<a href="#" index="<s:property/>" class="title-icon"><span><s:property value="%{memberQuery.desknames[current-2]}"/></span></a>
					</div>
				</s:iterator>
			</s:bean>
			<div id="b-content-00">
				<a href="#" index="0"><img src="${staticURL}/portal/img/images/icon-desktop-all.png" /></a>
				<a href="#" index="0" class="title-icon"><span>全景图</span></a>
			</div>
		</div> 
       <!-- </div> -->
	</div>
</div>
<!-- 全局视图 -->
<div id="appmanage">
	<a class="amg_close" href="javascript:;"></a>
	<div id="amg_dock_container"></div>
	<div class="amg_line_x"></div>
	<div id="amg_folder_container">
		<s:bean name="org.apache.struts2.util.Counter" id="counter">
   			<s:param name="first" value="1" />
   			<s:param name="last" value="%{desknum}" /> 
   			<s:iterator id="id" >
   				<div class="folderItem">
					<div class="folder_bg folder_bg<s:property/>"></div>
					<div class="folderOuter">
						<div class="folderInner"></div>
						<div class="scrollBar"></div>
					</div>
					<s:if test="%{#id != 1}">
						<div class="amg_line_y"></div>
					</s:if>
				</div>
   			</s:iterator>
		</s:bean>
		
		<!-- <div class="folderItem">
			<div class="folder_bg folder_bg1"></div>
			<div class="folderOuter">
				<div class="folderInner"></div>
				<div class="scrollBar"></div>
			</div>
			<div class="amg_line_y"></div>
		</div> -->
	</div>
</div>

<script>
/*设置basicUrl  */
HROS.CONFIG.deskLength = ${desknum};
HROS.CONFIG.basicDynamicUrl = '${dynamicURL}';
HROS.CONFIG.basicStaticUrl = '${staticURL}';
HROS.CONFIG.basicActionUrl = '${dynamicURL}/portal';
$(function(){
	/*jquery mobile page不自动初始化 */
	$(document).bind("mobileinit", function(){
		　$.extend( $.mobile , {autoInitializePage: false});
	});
	
	//IE下禁止选中
	document.body.onselectstart = document.body.ondrag = function(){return false;}
	
	$('.loading').hide();
	$('#desktop').show();
	//初始化
	HROS.base.init();
	//待办任务列表
	$("#desk-6 .main-content .main-subnav ul").on("click","li",function(){
		$("#todotasks li").removeClass("current-zoc");
		var id= $(this).parent().attr("resid");
		$("#todotasks #resource_"+id).addClass("current-zoc");
		$("#desk-6 .main-content .main-subnav ul li").removeClass("current");
		$(this).addClass("current");
		var src = $(this).attr("src");
		$("#homeIframe").attr("src",src);
	});
	$("#todotasks").off("click").on("click","li",function(){
		$("#todotasks li").removeClass("current-zoc");
		$(this).addClass("current-zoc");
		var src = $(this).attr("src");
		$("#homeIframe").attr("src",src);
	}).on("mouseenter","li",function(){
		var id = $(this).attr("resid");
		//console.log(id);
		var left = $(this).offset().left;
		left = left - 5;
		if(HROS.CONFIG.dockPos == 'left'){
			left = left - HROS.CONFIG.dockBarWidth;
		}
		$("#desk-6 .main-content .main-subnav").hide().css({marginLeft:left}).find("ul").hide();
		$("#desk-6 .main-content .main-subnav").show().find("#subResouceId_"+id).show();
	}).on("mouseover",function(){
		return false;
	})
	$("#desk-6 .main-content .main-subnav").on("mouseover",function(){
		return false;
	})
	$("body").on("mouseover",function(){
		$("#desk-6 .main-content .main-subnav").hide().find("ul").hide();
	});
	
	//隐藏按钮 
	$("#hideHeader").on("click",function(){
		$("#header .head-hook").show();
		$("#header").animate({marginTop:-HROS.CONFIG.headerHeight},300,function(){
			$("#header div.head-body").hide();
			HROS.CONFIG.headerHeight = 8;
			$("#header").css({height:HROS.CONFIG.headerHeight,marginTop:0});
			$("#desk").css({height:$(window).height()-HROS.CONFIG.headerHeight,top:HROS.CONFIG.headerHeight});
			$(window).resize();
		})
	});
	$("#header .head-hook").on("click","a",function(){
		HROS.CONFIG.headerHeight = 43;
		$("#header .head-hook").hide();
		$("#header").css({height:HROS.CONFIG.headerHeight,marginTop:-HROS.CONFIG.headerHeight+8});
		$("#header div.head-body").show();
		$("#header").animate({marginTop:0},200);
		$("#desk").css({height:$(window).height()-HROS.CONFIG.headerHeight,top:HROS.CONFIG.headerHeight});
		$(window).resize();
	}); 
});
function openSetting(){
	HROS.window.createTemp({
		appid : 'hoorayos-zmsz',
		title : '桌面设置',
		url : HROS.CONFIG.basicActionUrl+'/desksetting.action',
		width : 750,
		height : 480,
		isflash : false
	});
}
  function showMessage(content){
	  $.dialog({
			title: '您有一条新信息',
			width: 320,
			content: content
		});
  }
  function initPortalPush(){
	    /*  portalPush.onPageLoad();
		 dwr.engine.setActiveReverseAjax(true);
	     dwr.engine.setNotifyServerOnPageUnload(true); */
	}	

</script>
</body>
</html>