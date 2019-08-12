$(function() {
	//初始化进度条
	var auctionId = getParamValue("id");
	var auctionSetId = getParamValue("auctionSetId");
	$.post('../../aftersale/getDealInfoByAuctionSetId',{
		auctionSetId:auctionSetId
	},function(res) {
		var dealInfo=res.entity;
		mid = dealInfo.dealMid;
		if(dealInfo['licenseNumber']){
			dealInfo.licenseNumber = dealInfo.licenseNumber;
		}else{
			dealInfo.licenseNumber="车牌未提供"
		}
		$(".dealInfo-div").find("span[col]").each(function(){
			$(this).html(dealInfo[$(this).attr("col")]);
		});
	});
	$.post('../../aftersale/getAftersaleByAuctionSetId',{
		auctionSetId:auctionSetId
	},function(data) {
		var aftersale=data.entity;
		if(aftersale.payState==0||aftersale.payState==2){//待付款1
			processBar(1);
			$('.payTrace').show();
			deferType=0;
		}else if(aftersale.takeCarState==0||aftersale.takeCarState==2){//待提货2
			processBar(2);
			$('.takeCarTrace').show();
			deferType=1;
		}else{//完成3
			processBar(3);
		}
	});
	
	getTrace();
	$('#takeCarDialog').dialog({
	    onClose:function(){
	    	closeTakeCarTrace();
	    }
    });
});
var deferType=-1;
var mid=null;
function processBar(index) {
    var stepContents = ["s-step0","s-step1","s-step2","s-step3"];
    var key;//数组中元素的索引值
    for (key in stepContents) {
        var stepContent = stepContents[key];//获得元素的值
        if (key == index) {
            $('.'+stepContent+' b').addClass('active');
            $('.'+stepContent+' .s-tit').addClass('active');
        }else {
            if(key>index){
            	$('.'+stepContent+' b').removeClass('active');
                $('.'+stepContent+' p').removeClass('active');
                $('.'+stepContent+' .s-tit').removeClass('active');
            }else if(key<index){
            	$('.'+stepContent+' b').addClass('active');
                $('.'+stepContent+' p').addClass('active');
                $('.'+stepContent+' .s-tit').addClass('active');
            }
        }
        window.parent.scrollTo(0, 0);
    }

}
var auctionId = getParamValue("id");
/*查看车辆信息*/
function getDetails(){
	window.location.href="../../html/auction/auctionDetails.html?id="+auctionId;
}
/*付款跟踪*/
function toPayTrace(){
	$('#DIALOG').dialog({
		title : "付款跟踪",
		width : 500,
		height : 300,
		modal : true,
		href : "../../html/aftersale/payTrace.html",
		buttons : [ 
			{id : 'saveBtn',text : '保存',handler : function() {
				addPayTrack(auctionId);
			}}, 
			{id : 'cancelBtn',text : '取消',handler : function() {
				$('#DIALOG').dialog("close");
			}} 
		],
		onLoad: function () {
			checkWord(document.getElementById('trackMessage'),'trackMessage-check',200);
		}
	});
}
/*申请调价*/
function applyPrice(){
	var auctionSetId = getParamValue("auctionSetId");
	window.location.href="../../html/aftersale/apply.html?id="+auctionId+"&auctionSetId="+auctionSetId+"&applyType=1"+"&deferType="+deferType;
}
/*申请退货*/
function applyBackCar(){
	var auctionSetId = getParamValue("auctionSetId");
	window.location.href="../../html/aftersale/apply.html?id="+auctionId+"&auctionSetId="+auctionSetId+"&applyType=2"+"&deferType="+deferType;
}
/*申请延期*/
function applyDefer(){
	var auctionSetId = getParamValue("auctionSetId");
	window.location.href="../../html/aftersale/apply.html?id="+auctionId+"&auctionSetId="+auctionSetId+"&applyType=0"+"&deferType="+deferType;
}
/*提货跟踪*/
function toTakeCarTrace(){
	loadUploadHtml("takeCar-div","upload-takeCar","takeCar",1);
	$('#takeCarDialog').dialog('open');
	checkWord(document.getElementById('tc-remark'),'tc-remark-check',200);
}
function closeTakeCarTrace(){
	$('#takeCar-div').html('');
	fileNameList = [];
}
function closeTakeCarTraceBtn(){
	$('#takeCarDialog').dialog('close');
	$('#takeCar-div').html('');
	fileNameList = [];
}
function getTrace(){
	var auctionId = getParamValue("id");
	var auctionSetId = getParamValue("auctionSetId");
	$.post('../../aftersale/getTrackByAuctionSetId',{
		auctionSetId:auctionSetId
	},function(data) {
		if(data.returnCode=='000000'){
			var payList=data.entity.payTracks;
			var takeCarList=data.entity.takeCarTracks;
			var str='';
			if(payList.length>0){
				str+='<section>'+
				'<span class="point-time point-green"></span>'+
				'<time><span class="text-green">付款跟踪</span></time>'+
				'<aside><p>跟踪信息：</p>';
				for(var i in payList){
					var trackMessage=payList[i].trackMessage;
					var operator=payList[i].operator;
					var payHandle=payList[i].payHandle;
					var createTime=payList[i].createTime;
					str+='<p>'+trackMessage+'</p>';
					if(operator!=null){
						str+='<p>操作人：<span class="brief">'+operator+'（'+createTime+'）<span></p>';						
					}
					if(payHandle!=null){
						str+='<p>付款人：<span class="brief">'+payHandle+'（'+createTime+'）<span></p>';	
					}
				}
				str+='</aside>'+'</section>';				
			}
			if(takeCarList.length>0){
				str+='<section>'+
				'<span class="point-time point-green"></span>'+
				'<time><span class="text-green">提货跟踪</span></time>'+
				'<aside><p>跟踪信息：</p>';
				for(var i in takeCarList){
					var remark=takeCarList[i].remark;
					var operator=takeCarList[i].operator;
					var createTime=takeCarList[i].createTime;
					str+='<p>'+remark+'</p>';
					str+='<div id="takeCarImg'+i+'"></div>';
					str+='<p>操作人：<span class="brief">'+operator+'（'+createTime+'）<span></p>';
				}
				str+='</aside>'+'</section>';				
			}
			$('.time-div article').append(str);
			
			if(takeCarList.length>0){
				for(var i in takeCarList){
					loadImgInfo('#takeCarImg'+i,takeCarList[i].fileList);
				}
			}
			$('#viewer img').zoomify();
		}
	});
}
/**
 * 付款跟踪
 */
function addPayTrack(auctionId){
	var auctionSetId = getParamValue("auctionSetId");
	var trackMessage=$('#trackMessage').val();
	$.post('../../aftersale/addPayTrack',{
		auctionId:auctionId,
		auctionSetId:auctionSetId,
		trackMessage:trackMessage
	},function(data) {
		if(data.returnCode=='000000'){
			$.messager.alert('提示', "添加成功",'info',function(){
				$('#DIALOG').dialog("close");
				location.reload();
        	});
		}
	});
}
/**
 * 提货跟踪
 */
function addTakeCarTrack(){
	var auctionSetId = getParamValue("auctionSetId");
	var remark=$('#tc-remark').val();
	/**图片*/
	var fileIds = '';
	var picLength = $("#takeCar-upload-row").find(".pic-img").length;
    if (picLength > 1) {
        for (var t = 0; t <= picLength-1; t++) {
            var id = $("#takeCar-upload-row").find(".hiddenpicid").eq(t).val();
            if (id) {
            	fileIds = fileIds + id + "," ;
            }
        }
    }
    if(fileIds!=null&&fileIds.length>0){
		fileIds=fileIds.substring(0,fileIds.length-1);
	}
	$.post('../../aftersale/addTakeCarTrack',{
		auctionId:auctionId,
		remark:remark,
		auctionSetId:auctionSetId,
		fileIds:fileIds
	},function(data) {
		if(data.returnCode=='000000'){
			closeTakeCarTraceBtn();
			$.messager.alert('提示', "添加成功",'info',function(){
        		location.reload();
        	});
		}
	});
}
function loadUploadHtml(_uploadDivId,_uploadId,_type,_num){
	var addText = "addPic('"+_uploadId+"')";
	var doUploadText = "doUpload('"+_uploadId+"',"+_num+")";
	var innerHtml = '<ul id="'+_type+'-upload-row">'+
			'<li class="pic-single">'+
				'<div class="input-pic-box pic-box">'+
					'<img class="pic-img" src="../../resources/images/addimg-bg.png" '+
						'onclick="'+addText+'" />'+
					'<input style="display: none;" type="file" accept=".png,.jpg,.jpeg,.gif,.PNG,.JPG,.JPEG,.GIF" name="upload" '+
						'id="'+_uploadId+'" class="'+_uploadId+'" onchange="'+doUploadText+'" />'+
				'</div>'+
			'</li>'+
		'</ul>';
	$('#'+_uploadDivId).html(innerHtml);
}
/*图片展示*/
function loadImgInfo(_div,imglist){
	var imgCount=0;
	var trid=0;
	for(var i=0;i<imglist.length;i++){
		var img=imglist[i].imgPath;
		if(imgCount%8==0){
			trid++;
			$(_div).append("<tr col='cur-"+trid+"'></tr>");
		}
		$(_div+" tr[col='cur-"+trid+"']").append("<img src='"+img+"' border='0' width='60' height='60'>");
		imgCount++;
	}
}
/**处理图片 start*/
var fileNameList = [];

function addPic(v) {
	$('.' + v).get(0).click();
};
function doUpload(v,type) {
	var formData = new FormData();
    if ($('.' + v)[0].files.length == 0) {
        $.messager.alert('提示', "请选择图片");
        return false;
    };
    var pic_name = $('.' + v)[0].files[0].name;
    if (!/\.(jpg|png|gif|jpeg|JPG|PNG|GIF|JPEG)$/.test(pic_name)) {
        $.messager.alert('提示', "请上传正确图片格式");
        $('.' + v).val("");
        return;
    };
    var sizes = $('.' + v)[0].files[0].size;
    var kb = Math.round(sizes / 1024 * 100) / 100;
    if (kb > 4096) {
    	$.messager.alert("所传图片不能大于4M");
        return;
    }
    for(var i = 0;i<fileNameList.length;i++){
    	var exit_name = fileNameList[i];
    	if(pic_name == exit_name){
    		return;
    	}
    }
    formData.append("file", $('.' + v)[0].files[0]);
    $.ajax({
        url: "../../attachment/uploadSingle",
        type: "POST",
        data: formData,
        dataType: "json",
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (res) {
            console.log(res);
            var otr = ""
            if (res.returnCode == "000000") {
                if (res.entity!=null) {
                	picinfo = "";
            		picinfo += '<li class="pic-single">';
            		picinfo += '<div class="pic-box">';
            		picinfo += '<img src="' + res.entity.storagePath + '" alt="" class="pic-img"/>';
            		picinfo += '<input type="hidden" class="hiddenpicid" value="' + res.entity.id + '" />';
            		picinfo += '<button type="button" class="del-btn" onclick="delImg(this,' + res.entity.id + ')">'+
            		'<img src="../../resources/images/remove.png" class="remove">'+
            		'</button>';
            		picinfo += '</div>';
            		picinfo += '</li>';
            		if(type == 1){
            			$("#takeCar-upload-row").append(picinfo);
            		}else{
            			$("#transfer-upload-row").append(picinfo);
            		}
            		
                }
                fileNameList.push(pic_name);
            };
        },
        error: function (returndata) {
        	$.messager.alert("操作失败");
        }
    });
};

function delImg(v, picid) {
    $.ajax({
        url: "../../attachment/delete/" + picid,
        type: "DELETE",
        dataType: "json",
        async: false,
        success: function (res) {
            if (res.returnCode == "000000") {
            	console.log("删除图片id=" + picid);
            	$(v).parent().parent().remove();
            }
        }
    });
}
/**处理图片 end*/