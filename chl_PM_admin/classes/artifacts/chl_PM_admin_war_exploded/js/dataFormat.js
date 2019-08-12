//数据时间格式
Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1, // month  
        "d+": this.getDate(), // day  
        "h+": this.getHours(), // hour  
        "m+": this.getMinutes(), // minute  
        "s+": this.getSeconds(), // second  
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S": this.getMilliseconds()  
        // millisecond  
    }  
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
            .substr(4 - RegExp.$1.length));  
    for (var k in o)  
        if (new RegExp("(" + k + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
    return format;  
}  
//数据时间格式
function formatDatebox(value) {  
    if (value == null || value == '') {  
        return '';  
    }  
    var dt;  
    if (value instanceof Date) {  
        dt = value;  
    } else {  
        dt = new Date(value);  
    }  
  
    return dt.format("yyyy-MM-dd hh:mm"); //扩展的Date的format方法(上述插件实现)  
}
/*获取请求参数*/
function getParamValue(prama) {
	var reg = new RegExp("(^|&)" + prama + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
	return unescape(r[2]);
	return undefined;
}
function initAddressPCA(_divId,_province,_city,_area,_readonly){
	$(_divId+' '+_city).combobox({
		editable : false,
		width:150,
		readonly:_readonly
	});
	$(_divId+' '+_area).combobox({
		editable : false,
		width:150,
		readonly:_readonly
	});
	$(_divId+' '+_province).combobox({
		editable : false,
		width:150,
		readonly:_readonly,
	    url:'../../json/cityList.json',
	    valueField:'id',
	    textField:'name',
	    value:'',
	    onChange : function(cmbProvince){
	    	 var value = $(this).combobox('getValue');
			 if(value!=''){
				 $(_divId+' '+_city).combobox({ 
	                url:'../../json/cityList.json',  
	                valueField:'id', //值字段  
	                textField:'name', //显示的字段  
	                loadFilter : function(data) {
						var list;
						for(var i = 0; i < data.length; i++){
							if (data[i].id == cmbProvince){
								list=data[i].children;
								break;
							}
						}
						return list;
					},
					value:'',
	                onChange : function(cmbCity){
	                	var value = $(this).combobox('getValue');
	       			 	if(value!=''){
	       			 		$(_divId+' '+_area).combobox({ 
	       		                url:'../../json/cityList.json',  
	       		                valueField:'id', //值字段  
	       		                textField:'name', //显示的字段  
								loadFilter : function(data) {
									var subList;
									for(var i = 0; i < data.length; i++){
										if (data[i].id == cmbProvince){
											var list=data[i].children;
											for(var j = 0; j < list.length; j++){
												if (list[j].id == cmbCity){
													subList=list[j].children;
													break;
												}
											}
										}
									}
									return subList;
								},
	       		                value:'',
	       		            });  
	       	            }
	       			 }
	            });
			}
			$(_divId+' '+_area).combobox({  
			    value:'',
			});
		},
		onLoadSuccess:function(){
            var value = $(this).combobox('getValue');
            var value2 = $(_divId+' '+_city).combobox('getText');
            var value3 = $(_divId+' '+_area).combobox('getText');
            if(value!=""){
	        	$(_divId+' '+_city).combobox({ 
	        		url:'../../json/cityList.json',  
	        		valueField:'id', //值字段  
	        		textField:'name', //显示的字段 
	        		value:value2,
	        		loadFilter : function(data) {
						var list;
						for(var i = 0; i < data.length; i++){
							if (data[i].id == value){
								list=data[i].children;
								break;
							}
						}
						return list;
					}
	        	});   
            	
            }
            if(value2!=""){
	        	$(_divId+' '+_area).combobox({ 
	        		url:'../../json/cityList.json',  
	        		valueField:'id', //值字段  
	        		textField:'name', //显示的字段 
	        		value:value3,
	        		loadFilter : function(data) {
						var subList;
						for(var i = 0; i < data.length; i++){
							if (data[i].id == value){
								var list=data[i].children;
								for(var j = 0; j < list.length; j++){
									if (list[j].id == value2){
										subList=list[j].children;
										break;
									}
								}
							}
						}
						return subList;
					},
	        	});   
            }
        }  
	});
}
function initAddressPC(_divId,_province,_city,_readonly){
	$(_divId+' '+_city).combobox({
		editable : false,
		width:150,
		readonly:_readonly
	});
	$(_divId+' '+_province).combobox({
		editable : false,
		readonly:_readonly,
		width:150,
	    url:'../../json/cityList.json',
	    valueField:'id',
	    textField:'name',
	    value:'',
	    onChange : function(cmbProvince){
	    	 var value = $(this).combobox('getValue');
			 if(value!=''){
				 $(_divId+' '+_city).combobox({ 
	                url:'../../json/cityList.json',  
	                valueField:'id', //值字段  
	                textField:'name', //显示的字段  
	                value:'',
	                loadFilter : function(data) {
						var list;
						for(var i = 0; i < data.length; i++){
							if (data[i].id == cmbProvince){
								list=data[i].children;
								break;
							}
						}
						return list;
					}
	            });
			}
		},
		onLoadSuccess:function(){
            var value = $(this).combobox('getValue');
            var value2 = $(_divId+' '+_city).combobox('getText');
            if(value!=""){
	        	$(_divId+' '+_city).combobox({ 
	        		url:'../../json/cityList.json',  
	        		valueField:'id', //值字段  
	        		textField:'name', //显示的字段 
	        		value:value2,
	        		loadFilter : function(data) {
						var list;
						for(var i = 0; i < data.length; i++){
							if (data[i].id == value){
								list=data[i].children;
								break;
							}
						}
						return list;
					}
	        	});   
            	
            }
        }  
	});
}
/*------------*/
function initAddressPCAS(_divId,_province,_city,_area,_readonly,_width,_height){
	$(_divId+' '+_city).combobox({
		editable : false,
		width:_width,
		height:_height,
		readonly:_readonly
	});
	$(_divId+' '+_area).combobox({
		editable : false,
		width:_width,
		height:_height,
		readonly:_readonly
	});
	$(_divId+' '+_province).combobox({
		editable : false,
		width:_width,
		height:_height,
		readonly:_readonly,
	    url:'../../json/cityList.json',
	    valueField:'id',
	    textField:'name',
	    value:'',
	    onChange : function(cmbProvince){
	    	 var value = $(this).combobox('getValue');
			 if(value!=''){
				 $(_divId+' '+_city).combobox({ 
	                url:'../../json/cityList.json',  
	                valueField:'id', //值字段  
	                textField:'name', //显示的字段  
	                loadFilter : function(data) {
						var list;
						for(var i = 0; i < data.length; i++){
							if (data[i].id == cmbProvince){
								list=data[i].children;
								break;
							}
						}
						return list;
					},
					value:'',
	                onChange : function(cmbCity){
	                	var value = $(this).combobox('getValue');
	       			 	if(value!=''){
	       			 		$(_divId+' '+_area).combobox({ 
	       		                url:'../../json/cityList.json',  
	       		                valueField:'id', //值字段  
	       		                textField:'name', //显示的字段  
								loadFilter : function(data) {
									var subList;
									for(var i = 0; i < data.length; i++){
										if (data[i].id == cmbProvince){
											var list=data[i].children;
											for(var j = 0; j < list.length; j++){
												if (list[j].id == cmbCity){
													subList=list[j].children;
													break;
												}
											}
										}
									}
									return subList;
								},
	       		                value:'',
	       		            });  
	       	            }
	       			 }
	            });
			}
			$(_divId+' '+_area).combobox({  
			    value:'',
			});
		},
		onLoadSuccess:function(){
            var value = $(this).combobox('getValue');
            var value2 = $(_divId+' '+_city).combobox('getText');
            var value3 = $(_divId+' '+_area).combobox('getText');
            if(value!=""){
	        	$(_divId+' '+_city).combobox({ 
	        		url:'../../json/cityList.json',  
	        		valueField:'id', //值字段  
	        		textField:'name', //显示的字段 
	        		value:value2,
	        		loadFilter : function(data) {
						var list;
						for(var i = 0; i < data.length; i++){
							if (data[i].id == value){
								list=data[i].children;
								break;
							}
						}
						return list;
					}
	        	});   
            	
            }
            if(value2!=""){
	        	$(_divId+' '+_area).combobox({ 
	        		url:'../../json/cityList.json',  
	        		valueField:'id', //值字段  
	        		textField:'name', //显示的字段 
	        		value:value3,
	        		loadFilter : function(data) {
						var subList;
						for(var i = 0; i < data.length; i++){
							if (data[i].id == value){
								var list=data[i].children;
								for(var j = 0; j < list.length; j++){
									if (list[j].id == value2){
										subList=list[j].children;
										break;
									}
								}
							}
						}
						return subList;
					},
	        	});   
            }
        }  
	});
}
function initAddressPCS(_divId,_province,_city,_readonly,_width,_height){
	$(_divId+' '+_city).combobox({
		editable : false,
		width:_width,
		height:_height,
		readonly:_readonly
	});
	$(_divId+' '+_province).combobox({
		editable : false,
		readonly:_readonly,
		width:_width,
		height:_height,
	    url:'../../json/cityList.json',
	    valueField:'id',
	    textField:'name',
	    value:'',
	    onChange : function(cmbProvince){
	    	 var value = $(this).combobox('getValue');
			 if(value!=''){
				 $(_divId+' '+_city).combobox({ 
	                url:'../../json/cityList.json',  
	                valueField:'id', //值字段  
	                textField:'name', //显示的字段  
	                value:'',
	                loadFilter : function(data) {
						var list;
						for(var i = 0; i < data.length; i++){
							if (data[i].id == cmbProvince){
								list=data[i].children;
								break;
							}
						}
						return list;
					}
	            });
			}
		},
		onLoadSuccess:function(){
            var value = $(this).combobox('getValue');
            var value2 = $(_divId+' '+_city).combobox('getText');
            if(value!=""){
	        	$(_divId+' '+_city).combobox({ 
	        		url:'../../json/cityList.json',  
	        		valueField:'id', //值字段  
	        		textField:'name', //显示的字段 
	        		value:value2,
	        		loadFilter : function(data) {
						var list;
						for(var i = 0; i < data.length; i++){
							if (data[i].id == value){
								list=data[i].children;
								break;
							}
						}
						return list;
					}
	        	});   
            	
            }
        }  
	});
}
/*文本框限制输入*/
function checkWord(val,_id,len){
    var str = val.value;
    myLen=getStrleng(str,len);
    var wck=document.getElementById(_id);
    if(myLen>len){
    	val.value=str.substring(0,len);
    }else{
        wck.innerHTML = Math.floor(len-myLen);
    }
}
function getStrleng(str,len){
    myLen =0;
    i=0;
    for(;(i<str.length)&&(myLen<=len);i++){
    	myLen++;
    }
    return myLen;
}
function LoadJS(fileUrl) { 
    var head = document.getElementsByTagName('head')[0]; 
    var script= document.createElement("script"); 
    script.type = "text/javascript"; 
    script.src=fileUrl; 
    head.appendChild(script); 
}
function removeJS(fileUrl){
	var script=document.getElementsByTagName("script");
	for (var i=script.length; i>=0;i--){
		if (script[i] &&script[i].getAttribute("src")!=null && script[i].getAttribute("src").indexOf(fileUrl)!=-1){
			script[i].parentNode.removeChild(script[i])			
		}
	}
}
/*车辆列表信息头*/
function carHeadInfo(rec){
	//车牌号
	var lotAreas='';
	if(obj.isNotBlank(rec.lotAreas)){
		lotAreas = rec.lotAreas;
	}else{
		lotAreas="无"
	}
	var drivingBrandStr=rec.fullName;
	var img='';
	if(rec.imgPath!=null&&rec.imgPath.length>0){
		img=rec.imgPath;
	}
	var resultDiv=
		"<div class='inforMain'>"+
			"<div class='carpic'>"+
				"<img src='"+img+"' border='0' width='80' height='80'>"+
			"</div>"+
			"<div class='carinfo'>"+
				"<div class='info-left' title='"+drivingBrandStr+"' style='float: left;width: 100%;'>&nbsp;"+ 
					drivingBrandStr+
				"</div>"+
				"<p class='carmess'>"+
					"<span>区域："+lotAreas+"</span>"+
				"</p>"+
			"</div>"+
		"</div>";
	return resultDiv;
}
function goBackView(index){
	window.history.back(index);
	window.parent.scrollTo(0, 0);
}
function goback(){
	window.history.back(-1);
	window.parent.scrollTo(0, 0);
}
