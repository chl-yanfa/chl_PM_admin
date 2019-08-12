var ua = navigator.userAgent;
function downLoadBrower(str){
	return;
	var address = new Array();
	address.push('<div style="width:500px;height:400px;position:absolute;left:50%;top:50%;margin-left:-250px;margin-top:-200px">');
	address.push('<h3>'+str+'</h3></br>');
	address.push('<a href="http://dlsw.baidu.com/sw-search-sp/soft/9d/14744/ChromeStandaloneSetup41.0.2272.89.1426235198.exe" target="_self" style="color:#1E90FF;text-decoration:underline">下载地址1（百度）</a>&nbsp;&nbsp;&nbsp;');
	address.push('<a href="http://4dxwt.pc6.com/cz1/chrome.zip" target="_self" style="color:#1E90FF;text-decoration:underline">下载地址2（联通）</a>&nbsp;&nbsp;&nbsp;');
	address.push('<a href="http://dlc2.pconline.com.cn/filedown_63040_6974820/iObJ8x1E/41.0.2272.101_chrome_installer.exe" target="_self" style="color:#1E90FF;text-decoration:underline">下载地址3（电信）</a></br></br>');
	address.push('<a href="http://app.tuanche.net:8084/browser/chrome.exe" target="_self" style="color:#1E90FF;text-decoration:underline">下载地址4（内网地址）</a>');
	address.push('</div>');
	document.body.innerHTML = address.join("");
}

//排除Opera浏览器,为iphone和ipad开绿灯
if((/Chrome\/(\S+)/.test(ua) && ua.indexOf("OPR/")==-1)||ua.indexOf("iPhone")!=-1||ua.indexOf("iPad")!=-1){
	var chromeVer = RegExp["$1"];
	var version = parseFloat(chromeVer);
	if(version<37){
		downLoadBrower("您的浏览器版本过低("+chromeVer+")，请下载最新版本");
	}else{
		if(!$("div.google-ad").is(":visible")){
			var html = new Array();
			html.push('<div style="width:610px;height:600px;position:absolute;left:50%;top:20px;text-align:center;margin-left:-300px;">');
			html.push('<font style="color:red">检测到您打开了广告拦截插件，为了不影响本系统的正常使用，请关闭广告拦截功能！</font>');
			html.push('<p style="text-align:left;margin-top:10px">您可按照以下步骤关闭广告拦截<br/>');
			html.push('步骤一：<br/><img src="resources/images/help/closeAdbStep1.jpg" width="600"/><br/><br/>');
			html.push('步骤二：<br/><img src="resources/images/help/closeAdbStep2.jpg" width="600"/>');
			html.push('</p></div>');
			document.body.innerHTML=html.join("");
		}
	}
}else{
	downLoadBrower("请使用版本号为37.0以上的Chrome浏览器（谷歌浏览器）");
}
