/** 添加键盘监听事件 **/
document.onkeydown = function(e) {
	if (!e) 
		e = window.event;//火狐中是 window.event
	if ((e.keyCode || e.which) == 13) {
		if ($("#messagealert").is(":visible")) {
			return;
		} else {
			$("#btnlogin").click();
		}
	}
};
var showMsg;



$(function() {
	
	$('.userImg').hide();
	/*自定义alert方法*/
	showMsg=function(text){
		$("#message").html(text);
		setTimeout(function(){
			$("#message").empty();
		},3000);
	};
	initInput();
	initClearBtn();
	refreshKaptcha();
	//登录按钮方法
	$('#btnlogin').click(doLogin);
});
//初始化输入框
function initInput(){
	$("#loginId").focus(function() {
		var loginId = $(this).val();
		if (loginId == '请输入用户名') {
			$(this).val('');
			$("#cleanLoginId").hide();
			$("#userImg_h").hide();

			$('.userImg').show();
		} else {
			$("#cleanLoginId").show();
			$("#userImg_h").show();
			$('.userImg').hide();
		}
	}).blur(function() {
		var loginId = $.trim($(this).val());
		if (loginId == '') {
			$(this).val('请输入用户名');
			$("#userImg_h").show();
			$("#cleanLoginId").hide();
		} else {
			$("#cleanLoginId").show();
			$("#userImg_h").hide();
			$('.userImg').show();
		}
	});
	$("#kaptcha").focus(function() {
		var kaptcha = $(this).val();
		if (kaptcha == '验证码') {
			$(this).val('');
			
		}
	}).blur(function() {
		var kaptcha = $.trim($(this).val());
		if (kaptcha == '') {
			$(this).val('验证码');
			$('.vadImg').show();
			$('.vadImg_h').hide();
		}else{
			
			$('.vadImg_h').show();
			$('.vadImg').hide()
		}
	});
	
	$("#password").focus(function() {
		var password = $(this).val();
		var isVisible = $("#passwordTxt").is(':visible');
		if (password != '') {
			$("#passwordTxt").hide();
			
		} else if (isVisible) {
			$("#passwordTxt").hide();
		} else {
			$("#passwordTxt").show();
			
		}
	}).blur(function() {
		var password = $(this).val();
		if (password == '') {
			$("#passwordTxt").show();
			$("#cleanPassword").hide();
			$(".pwdImg_h").hide();
		} else {
			$("#passwordTxt").hide();
			$("#cleanPassword").show();
			$(".pwdImg_h").show();
		}
	});
	
	//获取cookie用户并锁定焦点
	if ($.cookie("rememberUserMark") == "true") {
		$("#keepId").attr("checked", true);
		$("#loginId").val($.cookie("rememberUserName"));
		$("#password").focus();
	}else{
		$("#loginId").focus();
	}
}
//初始化清空和重置按钮
function initClearBtn(){
	//清空用户名
	$('#cleanLoginId').click(function(){
		$('#loginId').val('');
		$('#loginId').focus();
		$(this).hide();
	});
	//清空密码
	$('#cleanPassword').click(function(){
		$('#password').val('');
		$('#password').focus();
		$(this).hide();
	});
	$("#passwordTxt").click(function(){
		$('#password').focus();
	});
	//重置验证码
	$('#kaptchaImage').click(refreshKaptcha);
}
// 登录方法
function doLogin() {
	if (!checkLoginInfo()) {
		return;
	}
	$.get('userLogin/validateUser', {
		loginId : $("#loginId").val(),
		password : $("#password").val(),
		kaptcha : $("#kaptcha").val()
	}, function(data) {
		if (data == "000000") {
			$("#message").empty();
			saveUserInfo();
			window.location.href="index.html";
		} else if (data == "L00000") {
			showMsg('验证码输入有误');
			$("#kaptcha").val("");
			
			$('.vadImg').show();
			$('.vadImg_h').hide();
		} else if (data == "L00001") {
			showMsg('用户名或密码错误');
			$("#password").val("");
			$("#kaptcha").val("");
			$("#cleanPassword").hide();
			$(".pwdImg_h").hide();
			$('.vadImg').show();
			$('.vadImg_h').hide();
		} else if (data == "L00002") {
			showMsg('该用户已被锁定');
			$("#password").val("");
			$("#kaptcha").val("");
		} else if (data == "L00003") {
			showMsg('该用户没有此平台权限');
			$("#password").val("");
			$("#kaptcha").val("");			
		} else {
			showMsg('未知错误，请联系管理员' + data);
			$("#password").val("");
			$("#kaptcha").val("");
		}
		refreshKaptcha();
	});
}
//重置验证码
function refreshKaptcha() {
	$('#kaptchaImage').hide().attr('src', 'kaptcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();
}

function checkLoginInfo() {
	var loginId = $.trim($("#loginId").val());
	var password = $.trim($("#password").val());
	var kaptcha = $.trim($("#kaptcha").val());
	
	if (loginId == "" || loginId == "请输入用户名") {
		$("#loginId").focus();
		return false;
	}
	if (password == "") {
		$("#password").focus();
		return false;
	}
	if (kaptcha == "" || kaptcha == "验证码") {
		$("#kaptcha").focus();
		return false;
	}
	return true;
}

function saveUserInfo() {
    if ($("#keepId").is(":checked") == true) {
        var loginId = $("#loginId").val();
        $.cookie("rememberUserMark", "true", { expires: 30}); // 存储一个带30天期限的 cookie
        $.cookie("rememberUserName", loginId, { expires: 30}); // 存储一个带30天期限的 cookie
    }
    else {
        $.cookie("rememberUserMark", "false", { expires: -1});
        $.cookie("rememberUserName", '', { expires: -1});
    }
}