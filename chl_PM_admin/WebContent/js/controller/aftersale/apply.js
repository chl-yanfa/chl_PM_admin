$(function() {
	initCombobox(); 
	var applyType = getParamValue("applyType");
	var deferType = getParamValue("deferType");
	if(applyType==0){
		$("input[name='applyType'][value='延期']").prop("checked", true);
		$('.deferDIV').removeClass('v-hide');
		loadUploadHtml("deferUpload","upload-defer","defer",3);
	}else if(applyType==1){
		$("input[name='applyType'][value='调价']").prop("checked", true);
		$('.priceDIV').removeClass('v-hide');
		loadUploadHtml("priceUpload","upload-price","price",4);
	}else if(applyType==2){
		$("input[name='applyType'][value='退货']").prop("checked", true);
		$('.backCarDIV').removeClass('v-hide');
		loadUploadHtml("backCarUpload","upload-backCar","backCar",5);
	}
	$("input[name='applyType']").change(function(){
		var value = $(this).val();
		if(value=='延期'){
	    	$('#priceForm').form('clear');
	    	$('#backCarForm').form('clear');
	    	$('.deferDIV').removeClass('v-hide');
	    	$('.priceDIV').addClass('v-hide');
	    	$('.backCarDIV').addClass('v-hide');
	    	clearHtml("deferUpload");
	    	loadUploadHtml("deferUpload","upload-defer","defer",3);
		}else if(value=='调价'){
	    	$('#deferForm').form('clear');
	    	$('#backCarForm').form('clear');
	    	$('.deferDIV').addClass('v-hide');
	    	$('.priceDIV').removeClass('v-hide');
	    	$('.backCarDIV').addClass('v-hide');
	    	clearHtml("priceUpload");
	    	loadUploadHtml("priceUpload","upload-price","price",4);
		}else if(value=='退货'){
	    	$('#deferForm').form('clear');
	    	$('#priceForm').form('clear');
	    	$('.deferDIV').addClass('v-hide');
	    	$('.priceDIV').addClass('v-hide');
	    	$('.backCarDIV').removeClass('v-hide');
	    	clearHtml("backCarUpload");
	    	loadUploadHtml("backCarUpload","upload-backCar","backCar",5);
		}
		fileNameList = [];
	});
	if(deferType!=null&&deferType.length>0&&deferType!=-1){
		if(deferType==0){
			$('#deferType').val('打款延期');
			$('.deferName').text('打款');
		}else if(deferType==1){
			$('#deferType').val('提货延期');
			$('.deferName').text('提货');
		}
	}
});
function initCombobox(){
	$('#deadline').datebox({
		editable : false
	});
	$('#deferTime').datebox({
		editable : false
	});
	
	$("input[name='carPriceType']").change(function(){
	    var value = $(this).val();
	    if (value == '车款百分比') {
			$("#cpt-type").html("%");
		} else if(value == '固定值'){
			$("#cpt-type").html("元");
		}
	});
	$("input[name='commissionType']").change(function(){
	    var value = $(this).val();
	    if (value == '佣金百分比') {
			$("#ct-type").html("%");
		} else if(value == '固定值'){
			$("#ct-type").html("元");
		}
	});
}
function addApply(){
	var auctionId = getParamValue("id");
	var auctionSetId = getParamValue("auctionSetId");
	var applyType=$('input[name="applyType"]:checked').val();
	var dataJson='';
	var ajaxName='';
	var uploadUlId='';
	if(applyType=='延期'){
		dataJson=$('#deferForm input,#deferForm textarea').serialize();
		ajaxName='addDefer';
		uploadUlId='defer-upload-row';
	}else if(applyType=='调价'){
		dataJson=$('#priceForm input,#priceForm textarea').serialize();
		ajaxName='addAdjustPrice';
		uploadUlId='price-upload-row';
	}else if(applyType=='退货'){
		dataJson=$('#backCarForm input,#backCarForm textarea').serialize();
		ajaxName='addBackCar';
		uploadUlId='backCar-upload-row';
	}
	dataJson += "&auctionId=" + auctionId;
	dataJson += "&auctionSetId=" + auctionSetId;
	/**图片*/
	var fileIds = '';
	var picLength = $("#"+uploadUlId).find(".pic-img").length;
    if (picLength > 1) {
        for (var t = 0; t <= picLength-1; t++) {
            var id = $("#"+uploadUlId).find(".hiddenpicid").eq(t).val();
            if (id) {
            	fileIds = fileIds + id + "," ;
            }
        }
    }
    if(fileIds!=null&&fileIds.length>0){
		fileIds=fileIds.substring(0,fileIds.length-1);
	}
    dataJson += "&fileIds=" + fileIds;
	$.post('../../aftersale/'+ajaxName,dataJson,function(data) {
		if(data.returnCode=='000000'){
			$.messager.alert('提示', "申请提交成功",'info',function(){
				goBackView(-1);
			});
		}
	});
}
function cancel(){
	goBackView(-1);
}

function clearHtml(_uploadDivId){
	$("#cpt-type").html('');
	$("#ct-type").html('');
	$('.deferName').text('提货');
	$('#'+_uploadDivId).html('');
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
            		if(type == 3){
            			$("#defer-upload-row").append(picinfo);
            		}else if(type == 4){
            			$("#price-upload-row").append(picinfo);
            		}else{
            			$("#backCar-upload-row").append(picinfo);
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
function loadUploadHtml(_uploadDivId,_uploadId,_type,_num){
	var addText = "addPic('"+_uploadId+"')";
	var doUploadText = "doUpload('"+_uploadId+"',"+_num+")";
	var innerHtml = '<p class="title">上传图片:</p>'+
		'<ul id="'+_type+'-upload-row">'+
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