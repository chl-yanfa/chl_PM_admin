$(function() {
	var _type = getParamValue("type");
	initAuctionWarehouse(_type);
});
function initAuctionWarehouse(_type){
	var actionName='';
	if(_type==1){
		actionName='getAuctionWarehouseById';
	}else if(_type==-1){
		actionName='getAuctionSaveWarehouse';
	}
	$("#warehouseBody").load("addWarehouseTemp.html",function(){
		$("#vehicleBody").load("../auction/auctionTemp.html",function(){
			var id = getParamValue("id");
			$.post("../../warehouse/"+actionName,{
				auctionId : id,
			},function(res) {
				initWareCombobox(false);
				var auction=res.entity.auction;
				var warehouse=res.entity.warehouse;
				loadAuctionDetail("#vehicleInfo","#carPhotoUrl",auction);
				
				$("#addWarehouse").form('load', warehouse);
				$("#baseInfo").form('load', auction);
				$("#baseInfo input,#baseInfo textarea").attr("readonly", true);
				//仓库-回显图片
				var imglist=warehouse['warehouseFileList'];
				if(imglist!=null && imglist.length>0){
					picInfo = "";
					for(var i=0;i<imglist.length;i++){
						if(imglist[i].fileType == 0){
							picInfo += '<li class="pic-single">'+ 
								'<div class="pic-box">'+ 
								'<img src="' + imglist[i].imgPath + '" alt="" class="pic-img"/>'+
								'<input type="hidden" class="hiddenpicid" value="' + imglist[i].id + '" />'+
								'<button type="button" class="del-btn" onclick="delImg(this,' + imglist[i].id + ')">'+
								'<img src="../../resources/images/remove.png" class="remove">'+
								'</button>'+
								'</div>'+
								'</li>';
							pic_sort = imglist[i].sort+1;
						}
					}
					$("#pic-upload-row").append(picInfo);
				}
				//回显证件
				$('#voucherName').html(warehouse['voucherName']);
    			$('.hiddenvoucherid').val(warehouse['voucherId']);
				
				checkWord(document.getElementById('keyRemark'),'keyRemark-check',200);
				checkWord(document.getElementById('storeSpace'),'storeSpace-check',200);
				checkWord(document.getElementById('troubleDescription'),'troubleDescription-check',200);
				checkWord(document.getElementById('dismantlingPart'),'dismantlingPart-check',200);
				checkWord(document.getElementById('twoAccidentsRemark'),'twoAccidentsRemark-check',200);
				checkWord(document.getElementById('refitPart'),'refitPart-check',200);
				checkWord(document.getElementById('checkPart'),'checkPart-check',200);
				checkWord(document.getElementById('noCheckPark'),'noCheckPark-check',200);
				checkWord(document.getElementById('receivingPart'),'receivingPart-check',200);
				checkWord(document.getElementById('remark'),'remark-check',200);
			});
		});
	});
}

/**处理图片 start*/
var fileNameList = [];
var pic_sort = 0;

function addPic(v) {
	$('.' + v).get(0).click();
};
function doUpload(v) {
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
    var this_sort = pic_sort;
    var actionName = "../../attachment/uploadSingle";
    var stockId = $("input[name='id']").val();
    if(obj.isNotBlank(stockId)){
    	actionName = "../../warehouseupload/upload/" + stockId +"?fileType="+0+"&sort="+this_sort;
    }
    $.ajax({
        url: actionName,
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
                	if(obj.isNotBlank(stockId)){
                		picinfo = "";
                		picinfo += '<li class="pic-single">';
                		picinfo += '<div class="pic-box">';
                		picinfo += '<img src="' + res.entity.imgPath + '" alt="" class="pic-img"/>';
                		picinfo += '<input type="hidden" class="hiddenpicid" value="' + res.entity.id + '" />';
                		picinfo += '<button type="button" class="del-btn" onclick="delImg(this,' + res.entity.id + ')">'+
                		'<img src="../../resources/images/remove.png" class="remove">'+
                		'</button>';
                		picinfo += '</div>';
                		picinfo += '</li>';
                		$("#pic-upload-row").append(picinfo);                		
                	}else{
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
                		$("#pic-upload-row").append(picinfo);
                	}
                    fileNameList.push(pic_name);
                    pic_sort++;
                }
            };
        },
        error: function (returndata) {
        	$.messager.alert("操作失败");
        }
    });
};

function delImg(v, picid) {
	var actionName = "../../attachment/delete/"+picid;
    var stockId = $("input[name='id']").val();
    if(obj.isNotBlank(stockId)){
    	actionName = "../../warehouseupload/delete/" + picid;
    }
    $.ajax({
        url: actionName,
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
function doUploadVoucher(e) {
	var formData = new FormData();
    if (e.files.length == 0) {
        $.messager.alert('提示', "请选择凭证");
        return false;
    };
    var pic_name = e.files[0].name;
    /*if (!/\.(jpg|png|gif|jpeg|JPG|PNG|GIF|JPEG)$/.test(pic_name)) {
        $.messager.alert('提示', "请上传正确图片格式");
        $('.' + v).val("");
        return;
    };*/
    var sizes = e.files[0].size;
    var kb = Math.round(sizes / 1024 * 100) / 100;
    if (kb > 4*1024) {
    	$.messager.alert("所传文件不能大于4M");
        return;
    }
    formData.append("file", e.files[0]);
    var actionName = "../../attachment/uploadSingle";
    $.ajax({
        url: actionName,
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
        			$('#voucherName').html(pic_name);
        			$('.hiddenvoucherid').val(res.entity.id);
                }
            };
        },
        error: function (returndata) {
        	$.messager.alert("操作失败");
        }
    });
};


/**
 * 车辆入库
 * _type:0-新增；1-转为入库
 * _save:0-保存；1-提交
 */
function addOrEditWarehouse(_type,_save){
	var auctionId = getParamValue("id");
	if (!$('#addWarehouse').form("validate")) {
		return;
	}
	var paramJson = {};
	$.each($("#addWarehouse").serializeArray(),function (i,field) {
		paramJson[field.name] = field.value;
	});
	paramJson["auctionId"] = auctionId;
	paramJson["ccProvince"] = $("#ccProvince").combobox('getText');
	paramJson["ccCity"] = $('#ccCity').combobox('getText');
	paramJson["ccArea"] = $('#ccArea').combobox('getText');
	paramJson["kProvince"] = $('#kProvince').combobox('getText');
	paramJson["kCity"] = $('#kCity').combobox('getText');
	paramJson["kArea"] = $('#kArea').combobox('getText');
	paramJson["sProvince"] = $('#sProvince').combobox('getText');
	paramJson["sCity"] = $('#sCity').combobox('getText');
	paramJson["sArea"] = $('#sArea').combobox('getText');
	paramJson["originProvince"] = $('#originProvince').combobox('getText');
	paramJson["originCity"] = $('#originCity').combobox('getText');
	paramJson["destinationProvince"] = $('#destinationProvince').combobox('getText');
	paramJson["destinationCity"] = $('#destinationCity').combobox('getText');
	paramJson["dataState"] = _save;
	
	/**图片*/
	var fileIds = '';
	var picLength = $("#pic-upload-row").find(".pic-img").length;
    if (picLength > 1) {
        for (var t = 0; t <= picLength-1; t++) {
            var id = $("#pic-upload-row").find(".hiddenpicid").eq(t).val();
            if (id) {
            	fileIds = fileIds + id + "," ;
            }
        }
    }
    if(fileIds!=null&&fileIds.length>0){
		fileIds=fileIds.substring(0,fileIds.length-1);
	}
    paramJson["fileIds"] = fileIds;
    var voucherId = $('.hiddenvoucherid').val();
    if(voucherId){
    	paramJson["voucherId"] = voucherId;    	
    }
	var ajaxUrl = "";
	switch (_type) {
	case 0:
		ajaxUrl = "addWarehouse"
		break;
	case 1:
		ajaxUrl = "transToWarehouse"
		break;
	}
	$.ajax({
		type : 'post',
		url : '../../warehouse/'+ajaxUrl,
		dataType : "json",
		data:paramJson,
		success : function(data) {
			if (data.returnCode == "000000") {
				if(_save==1){
					$.messager.alert('提示', '提交成功','info',function(){
						window.history.back(-1);		
					});					
				}else{
					$.messager.alert('提示', '保存成功','info',function(){
						window.history.back(-1);		
					});
				}
			}else {
				$.messager.alert('提示', "提交信息失败");
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}
		
	});
}