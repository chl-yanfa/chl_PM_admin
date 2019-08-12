//全局变量 缓存导航条高度
var _cacheIframeHeight=0;
var _minHeight = $(window).height();
//页面初始化方法
function init(){
	//initMenu();
	resizeMainPanelSize();
	crossDomainSetScrollHeight();
}
//页面初始化方法(无三级菜单)
function initUrl(url){
	openRightMainWindow(url);
	resizeMainPanelSize();
}
//初始化新的页面-直接跳转使用
function initTargetPage(name,url){
	$("#leftMenuWrap").html('<div class="navbox"><a href="javascript:void(0);" menuName="'+name+'" class="Cur" url="'+url+'">'+name+'</a></div>');
	initMenuFun();
	$("#leftMenuWrap div a").click(function(){
		openRightMainWindow($(this).attr("url"));
	});
	openRightMainWindow(url);
	resizeMainPanelSize();
}

//初始化菜单
function initMenu(){
	var menuHtml = "";
	for(var i=0,j=_currentSecondMenu.child.length;i<j;i++){
		var thirdMenu = _currentSecondMenu.child[i];
		var menuClass = "navbox";
		if(thirdMenu.child.length>0){
			menuHtml += '<div class="navbox" hasChild="true"><a href="javascript:void(0);" menuName="'+thirdMenu.menuname+'" url="'+thirdMenu.url+'">'+thirdMenu.menuname+'</a></div>';
			menuHtml +='<div class="fobox" isChild="true" style="display:none">';
			for(var p=0;p<thirdMenu.child.length;p++){
				menuHtml += '<a href="javascript:void(0);" menuName="'+thirdMenu.child[p].menuname+'" url="'+thirdMenu.child[p].url+'">'+thirdMenu.child[p].menuname+'</a>';
			}
			menuHtml += '</div>';
		}else{
			menuHtml += '<div class="navbox"><a href="javascript:void(0);" menuName="'+thirdMenu.menuname+'" url="'+thirdMenu.url+'">'+thirdMenu.menuname+'</a></div>';
		}
	}
	$("#leftMenuWrap").html(menuHtml);
	initMenuFun();
	if(_currentSecondMenu.child[0].child.length>0){		
		$("#leftMenuWrap div[isChild='true'] a").eq(0).click();
	}else{		
		$("#leftMenuWrap div a").eq(0).click();
	}
}
//初始化菜单点击事件
function initMenuFun(){
	$("#leftMenuWrap div a").click(function(){
		if($(this).parent().attr("hasChild")){
			//点击三级菜单且有子节点，不跳转页面
			$(this).addClass("Cur").parent().removeClass("navbox").addClass("todrop").next("div").show()
					.siblings("div.fobox").hide();
			$(this).parent().siblings("div").removeClass("todrop").addClass("navbox").find("a").removeClass("Cur");
		}else if($(this).parent().attr("isChild")){
			//点击四级节点，跳转页面
			$(this).addClass("foCur").siblings().removeClass("foCur");
			$(this).parent().siblings("div.fobox").find("a").removeClass("foCur");
			openRightMainWindow($(this).attr("url"));
		}else{
			//点击三级菜单且无子节点，跳转页面
			$(this).addClass("Cur").parent().siblings().removeClass("todrop").addClass("navbox");
			$(this).parent().siblings("div.fobox").hide();
			$(this).parent().siblings().find("a").removeClass("Cur").removeClass("foCur");
			openRightMainWindow($(this).attr("url"));
		}
	});
}
//打开页面
function openRightMainWindow(url){
	$("#rightMainIframe").attr("src",url);
}
/**
 * 跨域设置滚动条位置
 */
function crossDomainSetScrollHeight(){
	$(window).scroll(function(){
		var url = $("#rightMainIframe").attr("src");
		if(url.indexOf("http:")==0||url.indexOf("https:")==0){
			if(url.indexOf("#")>0){
				url = url.substring(0,url.indexOf("#"));
			}
			url = url + "#" + $(window).scrollTop();
			$("#rightMainIframe").attr("src",url);
		}
	});
}
//根据屏幕大小重新设置主显示区域大小
function resizeMainPanelSize(){
	try {
		var iframe = window.frames['rightMainIframe'];
		if(!iframe || !iframe.document){return;}
		_resizeMainPanelSize($(iframe.document.body).height());
		setTimeout(resizeMainPanelSize,500);
	} catch (e) {}
}
//根据屏幕大小重新设置主显示区域大小,由跨域子页面调用
function _resizeMainPanelSize(height){
	var inBodyH = height +20;
	var mainHeight = _minHeight>=inBodyH?_minHeight:inBodyH;
	if(_cacheIframeHeight!=mainHeight-20){
		_cacheIframeHeight = mainHeight;
		$("#leftMenuWrap").height(mainHeight-70);//需要减去按钮的padding高度
		$("#rightMain,#rightMainIframe").height(mainHeight);
	}
}