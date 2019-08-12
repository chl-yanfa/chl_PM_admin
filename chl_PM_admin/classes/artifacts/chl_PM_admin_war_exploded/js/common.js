/*!
 * Common Javascript Library
 * Version: v1.0
 * Date: 2014.07.08
 */
/*$(function(){
	// 设置jQuery Ajax全局的参数
	$.ajaxSetup({
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		complete:function(XMLHttpRequest,textStatus){
			//通过XMLHttpRequest取得响应头，sessionstatus，
			var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
			if(sessionstatus=="timeout"){
				//如果超时就处理 ，指定要跳转的页面(比如登陆页)
				window.location.replace("/login/index.php");
			}
		}
	});
});*/
function validateBoxDefault() {
	var span = $("<span>").addClass('red').text("* ");
    $("input.validatebox-invalid").prev("label").children('span').remove();
    $("input.validatebox-invalid").prev("label").prepend(span);

    $("input.validatebox-invalid").css("background", "none").css("border-color", "none");

    $("input.validatebox-invalid").validatebox('disableValidation');
    $("input.easyui-validatebox-temp[required='true']").focus(function() {
  	  $(this).validatebox('enableValidation');
    }).blur(function() {
  	  $(this).validatebox('validate');
    });
};
// 此方法是为了解决页面内，当某个字段为不可编辑时，点击”backSpace"键就退出系统的问题
function checkEvent(event) {
	var code;
	var e = event || window.event || arguments.callee.caller.arguments[0];
	var element = e.srcElement || e.target;
	if (((e.keyCode == 8) // BackSpace
			&& ((element.type != "text" && element.type != "textarea" && element.type != "password") || element.readOnly == true))
			|| ((e.ctrlKey) && ((e.keyCode == 78) || (e.keyCode == 82))) || (e.keyCode == 116)) { // CtrlN,CtrlR ,F5
		e.keyCode = 0;
		e.returnValue = false;
	}
	return true;
}
document.onkeydown = checkEvent;
$.extend($.fn.validatebox.defaults.rules, {
	bytesLength:{
		validator:function(value, param){
			var vaLen = value.length;
			for(var i=0;i<value.length;i++){
				if(value.charCodeAt(i)>128){
					vaLen++;
				}
				if(vaLen>param[0]){
					break;
				}
			}
			return vaLen<=param[0];
		},
		message: "输入字符长度超过限制！"
	},

});
$.extend($.fn.validatebox.defaults.rules, {
	bytesLen:{
		validator:function(value, param){
			var vaLen = value.length;
			for(var i=0;i<value.length;i++){
				if(value.charCodeAt(i)>128){
					vaLen++;
				}
				if(vaLen>param[0]){
					break;
				}
			}
			return vaLen<=param[0];
		},
		message: "输入长度不能超过32个字符（每个汉字为2个字符）！"
	}
});
$.extend($.fn.validatebox.defaults.rules, {// 时间
    date:{
         validator:function(value){
             return /^[0-9]{4}[-][0-9]{2}[-][0-9]{2} [0-9]{2}:[0-9]{2}$/i.test($.trim(value));
         },
         message: '时间格式不正确,如yyyy-MM-dd HH:mm'
     }
});
$.extend($.fn.validatebox.defaults.rules, {// 时间2
  datetime:{
       validator:function(value){
           return /^[0-9]{4}[-][0-9]{2}[-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$/i.test($.trim(value));
       },
       message: '时间格式不正确,如yyyy-MM-dd HH:mm:ss'
   }
});
$.extend($.fn.validatebox.defaults.rules, {//数字  
     pricesV:{
         validator:function(value){
             return value==''||value=='0'||(value>99&&value.substr(value.length-2,value.length)=='00');
         },
         message:"请输入整百数字"
     }
});
$.extend($.fn.validatebox.defaults.rules, {//数字  
	hundredValue:{
        validator:function(value){
        	var reg=/^[1-9]\d*00$/;
        	return reg.test(value);
        },
        message:"请输入整百数字"
    }
});
$.extend($.fn.validatebox.defaults.rules, {//数字  
	hundredOrZero:{
        validator:function(value){
        	var reg=/^[1-9]\d*00|0$/;
        	return reg.test(value);
        },
        message:"请输入整百数字"
    }
});

$.extend($.fn.validatebox.defaults.rules, {//数字  
    pricesMax:{
        validator:function(value){
            return value<10000000;
        },
        message:"不能大于9999999"
    }
});

$.extend($.fn.validatebox.defaults.rules, {//数字  
	priceInteger:{
		validator:function(value){
			return new RegExp(/[0-9]*$/).test(value);
		},
		message:"请输入整数"
	}
});

$.extend($.fn.validatebox.defaults.rules, {//排序数字  
	sortMax:{
      validator:function(value){
          return value<1000;
      },
      message:"不能大于999"
  }
});
$.extend($.fn.validatebox.defaults.rules, {//手机号   /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{10})$/;
	mobiles:{
		validator:function(value){
			var reg =  /^1\d{10}$/;
			return value.length == 11 && reg.test(value);
		},
		message:"手机号格式不正确"
	},
	mobiles_ep: {
		validator: function(value) {
			var reg_mobile = /^1\d{10}$/;
			var reg_tel = /^0\d+/;

			return (value.length == 11 && reg_mobile.test(value)) || reg_tel.test(value);
		},
		message:"手机号格式不正确"
	}
});
$.extend($.fn.validatebox.defaults.rules, {//手机号   
	telPhone:{
		validator:function(value){
			var reg = /^\d{6,15}$/;
			return reg.test(value);
		},
		message:"电话号格式不正确"
	}
});
$.extend($.fn.validatebox.defaults.rules, {//数字  
	numExp:{
        validator:function(value){
            return /^[0-9]*$/i.test($.trim(value));
        },
        message:"请输入数字"
    }
});
$.extend($.fn.datebox.defaults.rules, {//小于当前日期
	maxCurrentDate:{
    validator:function(value){
    	if(value==''){
    		return true;
    	}
    	var d1=new Date();
    	var d2=new Date(value.replace(/\-/g,'/')+' 23:59:59');
      return d1.getTime()<d2.getTime();
    },
    message:"小于当前日期"
  }
});
$.extend($.fn.datetimebox.defaults.rules, {//小于当前时间 showSeconds:false,
	maxCurrentDateTime:{
    validator:function(value){
    	if(value==''){
    		return true;
    	}
    	var d1=new Date();
    	var d2=new Date(value.replace(/\-/g,'/')+':00');
      return d1.getTime()<d2.getTime();
    },
    message:"小于当前时间"
  }
});
$.extend($.fn.validatebox.defaults.rules, {//汉字或字母
	charExp:{
        validator:function(value){
            return /^[\·a-zA-Z\u4e00-\u9fa5]*$/i.test(value);
        },
        message:"请输入汉字、字母或间隔号（·）"
    }
});
//格式化时间
function formatDate(nS) {
	if (!nS) {
		return "";
	}
	var now = new Date(parseInt(nS));
	var h = now.getHours();
	if (h < 10) {
		h = '0' + h;
	}
	var m = now.getMinutes();
	if (m < 10) {
		m = '0' + m;
	}
	return now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate() + " " + h + ":" + m;
};
//格式化时间ss
function formatDateSecond(nS){
  if(nS==null){
      return "";
  }
  var now = new Date(parseInt(nS));
  var h=now.getHours();
  if(h<10){
      h ='0'+h;
  }
  var m=now.getMinutes();
  if(m<10){
      m ='0'+m;
  }
  var s=now.getSeconds();
  if(s<10){
      s ='0'+s;
  }
  return now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate()+" "+h+":"+m+":"+s;
};
//格式化时间
function formatDateD(nS){
  if(nS==null){
      return "";
  }
  var now = new Date(parseInt(nS));
  return now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
};

//对数字进行四舍五入
function converToRound(money){
	money = Math.round(money/100)*100;
	return money;
}

//将字符串转成人民币形式
function formatToMoney(money){
	if(!money){
		return "";
	}
	money = converToRound(money);
	var result="";
	money = Math.round(money).toString();
	if(money.length >= 4){
		var i = money.length-3;
		while(true){
			if(i<=0){
				result = money.substring(0, i+3) + result;
				break;
			}else{
				result =  "," + money.substring(i, i+3) + result;
			}
			i = i-3;
		}
	}else{
		result = money;
	}
	result = "¥"+result;
	return  result;
}
function formatToMoney1(money){
//	if(!money){
//		return "";
//	}
	money = converToRound(money);
	var result="";
	money = Math.round(money).toString();
	if(money.length >= 4){
		var i = money.length-3;
		while(true){
			if(i<=0){
				result = money.substring(0, i+3) + result;
				break;
			}else{
				result =  "," + money.substring(i, i+3) + result;
			}
			i = i-3;
		}
	}else{
		result = money;
	}
	result = "¥"+result;
	return  result;
}
//数字转换成人民币大写形式
function AmountLtoU(num) {
    if(isNaN(num)){return "无效数值！";}
    if(num==0){return "零元";}
    var strPrefix="";
    if(num<0)strPrefix ="(负)";
    num=Math.abs(num);
    if(num>=1000000000000)return "金额过大，请核对后再输入！";
    var strOutput = "";
    var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';
    var strCapDgt='零壹贰叁肆伍陆柒捌玖';
    num += "00";
    var intPos = num.indexOf('.');
    if (intPos >= 0){
        num = num.substring(0, intPos) + num.substr(intPos + 1, 2);
    }
    strUnit = strUnit.substr(strUnit.length - num.length);
    for (var i=0; i < num.length; i++){
        strOutput += strCapDgt.substr(num.substr(i,1),1) + strUnit.substr(i,1);
    }
    return strPrefix+strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");
}
/**
 * 强制转换输入金额为规定范围内并做四舍五入处理（精确到百位）
 * 可将此方法赋予parser属性，如下：
 *  $("#myEvalMoneyInput").numberbox({
 *		parser:parseMoneyNumber
 *	});
 */
function parseMoneyNumber(data){
	if(!data){
		return '';
	}else{
		if(isNaN(data)){
			return 0;
		}
		if(data<0){
			return 0;
		}
		if(data>0 && data<100){
			return 100;
		}
		if(data>=10000000){
			return 10000000;
		}
		return Math.round(data/100)*100;
	}
}
/**
 * 强制转换输入金额为规定范围内并做四舍五入处理（精确到十位）
 * 可将此方法赋予parser属性，如下：
 *  $("#myEvalMoneyInput").numberbox({
 *		parser:parseMoneyNumberTen
 *	});
 */
function parseMoneyNumberTen(data){
	if(!data){
		return '';
	}else{
		if(isNaN(data)){
			return 0;
		}
		if(data<0){
			return 0;
		}
		if(data>0 && data<10){
			return 10;
		}
		if(data>=10000000){
			return 10000000;
		}
		return Math.round(data/10)*10;
	}
}
//格式化日期函数
function updateHourMin(){
	var endTime=$("#edate").datetimebox('getValue');
	var now = new Date();
	var h = now.getHours();
	if (h < 10) {
		h = '0' + h;
	}
	var m = now.getMinutes();
	if (m < 10) {
		m = '0' + m;
	}
	if(m>=60){
		h=Number(h)+1;
	}
	if(endTime!=''&&endTime!=null&&endTime.length>=10){
	endTime=endTime.substring(0,10)+" " + h + ":" + m;
	}else{
		endTime='';
	}
	$("#edate").datetimebox('setValue',endTime);

}
function initDate(beginTime,endTime,which){
	var date=new Date();
	var h = date.getHours();
	if (h < 10) {
		h = '0' + h;
	}
	var m = date.getMinutes();
	if (m < 10) {
		m = '0' + m;
	}
	var dateBegin='';
	var dateEnd='';
	//日期到日
	if(which=='1'){
		 dateBegin=date.getFullYear()+'-'+(date.getMonth()+1)+'-'+'01';
		 dateEnd=date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
		 $('#'+beginTime).datetimebox('setValue',dateBegin);
		$('#'+endTime).datetimebox('setValue',dateEnd);
	}
	else if(which=='2'){//日期到时分
		 dateBegin=date.getFullYear()+'-'+(date.getMonth()+1)+'-'+'01'+' 00'+':00';
		 dateEnd=date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()+' '+h+':'+m;
		 $('#'+beginTime).datetimebox('setValue',dateBegin);
		 $('#'+endTime).datetimebox('setValue',dateEnd);
	}

	sdate=dateBegin;
	edate=dateEnd;
}


//给string对象添加getValue方法获取参数
String.prototype.GetValue=function(para) {
	  var reg = new RegExp("(^|&)"+ para +"=([^&]*)(&|$)");
	  var r = this.substr(this.indexOf("\?")+1).match(reg);
	  if(r!=null && r[2].indexOf("#")>0){
		  r[2] = r[2].substring(0,r[2].indexOf("#"));
	  }
	  if (r!=null) return decodeURI(r[2]); return null;
};
//String格式化方法
String.prototype.format = function(args) {
	if (arguments.length > 0) {
		var result = this;
		if (arguments.length == 1 && typeof (args) == "object") {
			for (var key in args) {
				result = result.replace(new RegExp("({" + key + "})", "g"), args[key]);
			}
		} else {
			for (var i = 0; i < arguments.length; i++) {
				if (arguments[i] == undefined) {
					return "";
				} else {
					result = result.replace(new RegExp("({[" + i + "]})", "g"), arguments[i]);
				}
			}
		}
		return result;
	} else {
		return this;
	}
};

/**
 //---------------------------------------------------
 // 日期格式化
 // 格式 YYYY/yyyy/YY/yy 表示年份
 // MM/M 月份
 // W/w 星期
 // dd/DD/d/D 日期
 // hh/HH/h/H 时间
 // mm/m 分钟
 // ss/SS/s/S 秒
 //---------------------------------------------------
 */
Date.prototype.Format = function(formatStr)
{
    var str = formatStr;
    var Week = ['日','一','二','三','四','五','六'];

    str=str.replace(/yyyy|YYYY/,this.getFullYear());
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

    str=str.replace(/MM/,(this.getMonth()+1)>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));
    str=str.replace(/M/g,(this.getMonth()+1));

    str=str.replace(/w|W/g,Week[this.getDay()]);

    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
    str=str.replace(/d|D/g,this.getDate());

    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
    str=str.replace(/h|H/g,this.getHours());
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
    str=str.replace(/m/g,this.getMinutes());

    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
    str=str.replace(/s|S/g,this.getSeconds());

    return str;
};

/**
 *
 * @param {} day 传递过来要增加或者减少的天数目
 * @param {} index 0表示天数增加,其它参数表示天数减少
 * 	示例:
 * 		var nowTime = new Date()
 * 		var startTime=nowTime.diffDay();     //返回nowTime
 * 					 =nowTime.diffDay(30,0); //返回 nowTime+30天
 * 					 =nowTime.diffDay(30,1); //返回 nowTime-30天
 * 					 =nowTime.diffDay(30);   //返回 nowTime-30天
 * @return {}
 */
Date.prototype.diffDay=function(day,mark){
	if(arguments.length==2){//两个参数都传递
		if(mark ==0){
			return new Date(this.getTime()+1000*60*60*24*parseInt(day));
		}else{
			return new Date(this.getTime()-1000*60*60*24*parseInt(day));
		}
	}else if(arguments.length==1){//传递一个参数
		return new Date(this.getTime()-1000*60*60*24*parseInt(day));
	}else{
		return this;
	}
}



function load(js) {
	var s = document.createElement('script');
	s.setAttribute('type','text/javascript');
	s.setAttribute('src',js);
	var head = document.getElementsByTagName('head');
	head[0].appendChild(s);
}
function string_isEmpty(str) {
	str = $.trim(str);
	if (str == '' || str == null || str == 'null' || typeof str == 'undefined' || str == 'undefined') {
		return true;
	}
	return false;
}
function string_isNotEmpty(str) {
	return !string_isEmpty(str);
}

function editStringLen(value, param){
	if(!value){
		return "";
	}
	var len=0;
	var val="";
	for(var i=0;i<value.length;i++){
		if(value.charCodeAt(i)>128){
			val+=value[i];
			len+=2;
		}else{
			val+=value[i];
			len++;
		}
		if(len>=param){
			if(val==value){
				return val;
			}
			return val+"...";
		}
	}
	return val;
}
/**
 * 年月日格式
 * week 是否需要星期0否 1是
 * */
function getNowYMD(week){
	var result;
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	result = y + "年" + m + "月" + d +"日";
	if(week){
		var weekArray = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
		result = result + " " + weekArray[date.getDay()];
	}
	return result;
}
/** 数字金额大写转换(可以处理整数,小数,负数) + 整*/
function digitUppercase(n) {
	var fraction = ['角', '分'];
	var digit = [
		'零', '壹', '贰', '叁', '肆',
		'伍', '陆', '柒', '捌', '玖'
	];
	var unit = [
		['元', '万', '亿'],
		['', '拾', '佰', '仟']
	];
	var head = n < 0 ? '欠' : '';
	n = Math.abs(n);
	var s = '';
	for (var i = 0; i < fraction.length; i++) {
		s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
	}
	s = s || '整';
	n = Math.floor(n);
	for (var i = 0; i < unit[0].length && n > 0; i++) {
		var p = '';
		for (var j = 0; j < unit[1].length && n > 0; j++) {
			p = digit[n % 10] + unit[1][j] + p;
			n = Math.floor(n / 10);
		}
		s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
	}
	return head + s.replace(/(零.)*零元/, '元')
			.replace(/(零.)+/g, '零')
			.replace(/^整$/, '零元整');
}

/** 数字金额大写转换(可以处理整数,小数,负数) */
function digitUppercaseOri(n) {
	if(parseInt(n)==0)return "零";
	var fraction = ['角', '分'];
	var digit = [
		'零', '壹', '贰', '叁', '肆',
		'伍', '陆', '柒', '捌', '玖'
	];
	var unit = [
		['元', '万', '亿'],
		['', '拾', '佰', '仟']
	];
	var head = n < 0 ? '欠' : '';
	n = Math.abs(n);
	var s = '';

	for (var i = 0; i < fraction.length; i++) {
		s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
	}
	n = Math.floor(n);
	for (var i = 0; i < unit[0].length && n > 0; i++) {
		var p = '';
		for (var j = 0; j < unit[1].length && n > 0; j++) {
			p = digit[n % 10] + unit[1][j] + p;
			n = Math.floor(n / 10);
		}
		s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
	}
	return head + s.replace(/(零.)+/g, '零');
}
//js精确加减运算方法
var calc = {
	Add: function (arg1, arg2) {
		arg1 = arg1.toString(), arg2 = arg2.toString();
		var arg1Arr = arg1.split("."), arg2Arr = arg2.split("."), d1 = arg1Arr.length == 2 ? arg1Arr[1] : "", d2 = arg2Arr.length == 2 ? arg2Arr[1] : "";
		var maxLen = Math.max(d1.length, d2.length);
		var m = Math.pow(10, maxLen);
		var result = Number(((arg1 * m + arg2 * m) / m).toFixed(maxLen));
		var d = arguments[2];
		return typeof d === "number" ? Number((result).toFixed(d)) : result;
	},
	Sub: function (arg1, arg2) {
		return calc.Add(arg1, -Number(arg2), arguments[2]);
	},
	Mul: function (arg1, arg2) {
		var r1 = arg1.toString(), r2 = arg2.toString(), m, resultVal, d = arguments[2];
		m = (r1.split(".")[1] ? r1.split(".")[1].length : 0) + (r2.split(".")[1] ? r2.split(".")[1].length : 0);
		resultVal = Number(r1.replace(".", "")) * Number(r2.replace(".", "")) / Math.pow(10, m);
		return typeof d !== "number" ? Number(resultVal) : Number(resultVal.toFixed(parseInt(d)));
	},
	Div: function (arg1, arg2) {
		var r1 = arg1.toString(), r2 = arg2.toString(), m, resultVal, d = arguments[2];
		m = (r2.split(".")[1] ? r2.split(".")[1].length : 0) - (r1.split(".")[1] ? r1.split(".")[1].length : 0);
		resultVal = Number(r1.replace(".", "")) / Number(r2.replace(".", "")) * Math.pow(10, m);
		return typeof d !== "number" ? Number(resultVal) : Number(resultVal.toFixed(parseInt(d)));
	}
};
//给Number类型增加一个add方法，，使用时直接用 .add 即可完成计算。
Number.prototype.Add = function (arg) {
	return calc.Add(arg, this);
};
//点击鼠标位置
function getMousePos(event) {
//    var e = event || window.event;
    var e = getEvent();
    var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
    var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
    var x = e.pageX || e.clientX + scrollX;
    var y = e.pageY || e.clientY + scrollY;
    return { 'x': x, 'y': y };
}
//同时兼容ie和ff的写法
function getEvent() {
	if (document.all) {
		return window.event;
	}
	func = getEvent.caller;
	while (func != null) {
		var arg0 = func.arguments[0];
		if (arg0) {
			if ((arg0.constructor == Event || arg0.constructor == MouseEvent)
					|| (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
				return arg0;
			}
		}
		func = func.caller;
	}
	return null;
}
function getComboData(data, textField, valueField, hasAll) {
	var comboData = new Array();
	if (hasAll) {
		var all = new Object();
		eval("all." + valueField + "='';");
		eval("all." + textField + "='全部';");
		comboData.push(all);
	}
    $.each(data, function (k, v) {
        var item = {};
        eval('item.' + textField + '="' + v + '"');
        eval('item.' + valueField + '="' + k + '"');
        comboData.push(item);
    });
    
    var first = comboData.shift();
    first.selected = true;
    comboData.unshift(first);
    
    return comboData;
}
/**gd*/
function subString(value, length) {
	if (value == null)
		return "";
	var resultVal = editStringLen(value, length);
	var title = value.replace(new RegExp(/\s/g),"&nbsp;");
	return "<front title="+title+">"+resultVal+"</front>";
}

window.obj = {};
/**isBlank 判断空字符串*/
obj.isBlank = function (r) {
	return (r == null || r == undefined || r == "");
}
/**isNotBlank 判断!空字符串*/
obj.isNotBlank = function (r) {
	return !obj.isBlank(r);
}
/**isEmpty 判断空*/
obj.isEmpty = function (r) {
	return (r == null || r == undefined || r.length == 0);
}
/**isNotEmpty 判断!空*/
obj.isNotEmpty = function (r) {
	return !obj.isEmpty(r);
}