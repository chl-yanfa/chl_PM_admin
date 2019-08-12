var _menus = null;	//当前用户所有菜单数据
var _currentSecondMenu;//当前选中的二级菜单
//初始化方法
$(function(){
	initMenuData(); //初始化菜单
	initPrimNavShowTrigger(); //初始化一级菜单弹出方法
	initUserName(); //初始化首页登录用户名
	initUserEditFun(); //初始化用户信息操作
	openPwd(); //设置登录窗口
	initWindowScroll(); //初始化页面滚动条事件
	
});

/** 全局通用打开新页面方法 **/
function openPage(name,url){
	$("#mainPanel").load("module.html",function(){
		initTargetPage(name,url);
	});
}
/***********************页面顶部用户信息和店信息初始化国界线begin*********************************/
//初始化首页登录用户名
function initUserName(){
	var userNameTemp = $.cookie('userName');
	if(userNameTemp){
		var userName = Base64.decode(userNameTemp);
		$("#userNames").html(userName);
	}
}


//设置登录窗口
function openPwd() {
    $('#w').window({
        title: '修改密码',
        width: 400,
        modal: true,
        shadow: true,
        closed: true,
        height: 200,
        resizable:false,
        onOpen:function(){
        	$('#updatePasswordInner').show();
        }
    });
}
//关闭登录窗口
function closePwd() {
    $('#w').window('close');
}
//修改密码
function serverLogin() {
    var $newpass = $('#txtNewPass');
    var $rePass = $('#txtRePass');

    if ($newpass.val() == '') {
        msgShow('系统提示', '请输入密码！', 'warning');
        return false;
    }
    if ($rePass.val() == '') {
        msgShow('系统提示', '请输入确认密码！', 'warning');
        return false;
    }

    if ($newpass.val() != $rePass.val()) {
        msgShow('系统提示', '两次密码不一致！请重新输入', 'warning');
        return false;
    }

    $.post('userManagement/updatePwd?userPassword=' + $newpass.val(), function(msg) {
    	msgShow('系统提示', '恭喜，密码修改成功！', 'info');
    	$newpass.val('');
    	$rePass.val('');
    	$('#w').window('close');
    });
    
}
//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
//初始化用户信息操作
function initUserEditFun(){
	$('#editpass').click(function() {
        $('#w').window('open');
    });

    $('#btnEp').click(function() {
        serverLogin();
    });

    $('#btnCancel').click(function(){
    	$('#txtNewPass').val("");
    	$('#txtRePass').val("");
    	closePwd();
    });

    $('#loginOut').click(function() {
        $.messager.confirm('系统提示', "您确定要退出本次登录吗?", function(r) {
            if (r) {
            	systemLogout(true);
            }
        });
    });
}
/**
 * 退出系统
 */
function systemLogout(flag){
	 $.get("userLogin/logout",function(data){
		 if(flag){
			 location.href = 'login.html';
		 }
     });
}
//解密
var Base64 = {
	// private property
	_keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
	// public method for encoding
	encode : function(input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = Base64._utf8_encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}

			output = output + this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) + this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
		}
		return output;
	},
	// public method for decoding
	decode : function(input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;

		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

		while (i < input.length) {

			enc1 = this._keyStr.indexOf(input.charAt(i++));
			enc2 = this._keyStr.indexOf(input.charAt(i++));
			enc3 = this._keyStr.indexOf(input.charAt(i++));
			enc4 = this._keyStr.indexOf(input.charAt(i++));

			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;

			output = output + String.fromCharCode(chr1);

			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}

		}
		output = Base64._utf8_decode(output);
		return output;
	},

	// private method for UTF-8 encoding
	_utf8_encode : function(string) {

		string = string.replace(/\r\n/g, "\n");
		var utftext = "";

		for ( var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if ((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
		}
		return utftext;
	},
	// private method for UTF-8 decoding
	_utf8_decode : function(utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;

		while (i < utftext.length) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if ((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i + 1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i + 1);
				c3 = utftext.charCodeAt(i + 2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}
};
/***********************页面顶部用户信息和店信息初始化国界线end*********************************/
/****************************首页菜单操作国界线begin*****************************************/
//初始化菜单数据
function initMenuData(){
	$.get("roleManagement/getUserMenu?_"+Math.random(),function(data){
		_menus = data;
		initTopMenuNav();
	});
}
function initTopMenuNav() {
	var towNavHtml = '<a menuId="" href="index.html" class="Cur">首页</a>';
	
	for (var menuId in _menus) {
		var topMenu = _menus[menuId];
		var hasChildMenu = false;
		if (topMenu.children.length > 0) {
			for (var index = 0; index < topMenu.children.length; index++) {
				if (topMenu.children[index].menuChecked == "Y") {
					hasChildMenu = true;
					break;
				}
			}
		}
		
		if (topMenu.menuChecked == "Y" || hasChildMenu) {
			if (topMenu.children.length > 0) {
				var style = "position: relative; top: -7px; left: -1px; color: rgb(255, 3, 20); font-weight: bold;";
				towNavHtml += '<a menuId="' + menuId + '" href="javascript:void(0);" url="' + topMenu.menuUrl + '" childs="' + topMenu.children.length + '">' + topMenu.menuName + '<span style="' + style + '"></span></a>';
			} else {
				towNavHtml += '<a style="cursor:default;color:##9CA09F" menuId="' + menuId + '" href="javascript:void(0);" url="' + topMenu.menuUrl + '" childs="' + topMenu.children.length + '">' + topMenu.menuName + '</a>';
			}
		}
	}
	$("#twoNav").html(towNavHtml);
	$("#twoNav a[menuId != ''][childs != 0]").click(function() {
		$(this).addClass("Cur").siblings("a").removeClass("Cur");
		var menuId = $(this).attr("menuId");
		openTopMenuNav(menuId);
	});
}
function openTopMenuNav(menuId) {
	$("#mainPanel").load("module.html",function() {
		_menus[menuId];
		var menuHtml = "";
		var childFirstMenu = "";
		for (var childMenuId in _menus[menuId].children) {
			var childMenu = _menus[menuId].children[childMenuId];
			
			if (childMenu.menuChecked == "Y") {
				var menuClass = "navbox";
				var menuName = childMenu.menuName;
				var menuUrl = childMenu.menuUrl;
				if (childMenu.children.length == 0) {
					menuHtml += '<div class="navbox">';
					menuHtml += '<a href="javascript:void(0);" hasChild="N" menuName="' + menuName +'" url="' + menuUrl + '">' + menuName + '</a>';
					menuHtml += '</div>';
				} else {
					menuHtml += '<div top class="todrop">';
					menuUrl = childMenu.children[0].menuUrl;
					menuHtml += '<a href="javascript:void(0);" menuId="' + childMenu.id + '" hasChild="Y" menuName="' + menuName +'" url="' + menuUrl + '">' + menuName + '</a>';
					menuHtml += '<div parentMenuId="' + childMenu.id + '" style="display:none;">';
					for (var childMenuIdTwo in  childMenu.children) {
						var childMenuTwo = childMenu.children[childMenuIdTwo];
						menuName = childMenuTwo.menuName;
						menuUrl = childMenuTwo.menuUrl;
						menuHtml += '<a href="javascript:void(0);" parentMenuId="' + childMenu.id + '" level="3" style="padding-left:40px;width:103px;border-top:none;" menuName="' + menuName + '" url="' + menuUrl + '">' + menuName + '</a>'
					}
					menuHtml += '</div>';
					menuHtml += '</div>';					
				}
			}
			
		}
		$("#leftMenuWrap").html(menuHtml);
		$("#leftMenuWrap div a").click(function(){
			//点击三级菜单且无子节点，跳转页面
			if ($(this).attr("level") == "3") {
				$(this).addClass("Cur").siblings().removeClass("Cur");
				$("#leftMenuWrap div[parentMenuId ^= 'M'][parentMenuId != '" + $(this).attr("parentMenuId") + "']").slideUp();
			} else {
				$("#leftMenuWrap div[parentMenuId ^= 'M']").slideUp();
				$(this).addClass("Cur").parent().siblings().removeClass("todrop").addClass("navbox");
				$(this).parent().siblings("div.fobox").hide();
				$(this).parent().siblings().find("a").removeClass("Cur").removeClass("foCur");
			}
			
			if ($(this).attr("hasChild") == "Y") {
				$("#leftMenuWrap div[parentMenuId = '" + $(this).attr("menuId") + "']").slideToggle();
				$("#leftMenuWrap div[parentMenuId = '" + $(this).attr("menuId") + "'] a").removeClass("Cur");
				$("#leftMenuWrap div[parentMenuId = '" + $(this).attr("menuId") + "'] a:eq(0)").addClass("Cur");
			}
			
			openRightMainWindow($(this).attr("url"));
		});
		$("#leftMenuWrap div a").eq(0).click();
		
		init();
	});
}
function isEmpty(str) {
	if (str == '' || str == null || str == 'null' || str == undefined || typeof str == 'undefined') {
		return true;
	}
	return false;
}
function isNotEmpty(str) {
	return !isEmpty(str);
}

//初始化鼠标移上触发以及菜单显示
function initPrimNavShowTrigger(){
	$("#primNavShowTrigger").click(function() {
		if($(this).attr("class")=="primSlidUp"){
			$(this).removeClass("primSlidUp").addClass("primSlidDown");
			$("#primNavWrap,#topWrap").slideUp(400);
		}else{
			$(this).removeClass("primSlidDown").addClass("primSlidUp");
			$("#primNavWrap,#topWrap").slideDown(400);
		}
	});
}
/****************************首页菜单操作国界线end*****************************************/
/**************页面其他方法*****************/
//初始化页面滚动条事件
function initWindowScroll(){
	window.onscroll=function(){
		if($(window).scrollTop()>250){
			$("#backtotopBar").show(0);
		}else{
			$("#backtotopBar").hide(0);
		}
	};
}
//跳转到页面顶部
function gotoPageTop(){
	window.scrollTo(0,0);
}
//按字节切分字符串，val:字符串，len:保留字节数
function splitBytesLen(val, len){
	var vaLen = 0;
	for(var i=0;i<val.length;i++){
		//半角+1 全角+2
		vaLen++;
		if(val.charCodeAt(i)>128){
			vaLen++;
		}
		if(vaLen>len){
			val = val.substr(0,i);
			break;
		}
	}
	return val;
}
//IE 兼容Console的问题
window.console = window.console || (function(){ 
	var c = {}; c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function(){}; 
	return c; 
})(); 