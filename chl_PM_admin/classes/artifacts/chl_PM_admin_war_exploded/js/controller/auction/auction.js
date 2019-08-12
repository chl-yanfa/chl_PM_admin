/*initCombobox初始化下拉框*/
function initCombobox(_readonly) {
	//productionDate 出厂日期
	$('#vehicleInfo #productionDate').datebox({
		editable : false,
		readonly:_readonly
	});
	//lotAreas 所在地
	$('#vehicleInfo #lotAreasId').combobox({
		required : true,
		editable : false,
		readonly:_readonly,
		method : 'get',
		url : '../../areas',
		valueField : 'id',
		textField : 'areasName',
		onSelect:function(rec){
			$("#lotAreas").val(rec.areasName);
		}
	});
	$('#address-searchText').searchbox({
	   width :280,
	   prompt : '请输入需要检索的地址'
	});
	$('#suggestId').searchbox({
	   width :280,
	   prompt : '请输入车辆所在位置'
	});
}

/**
 * loadAuctionDetail 拍品信息查看详情
 * @param _id		formid
 * @param _pic		显示图片id
 * @param auction	数据源
 * @returns
 */
function loadAuctionDetail(_id,_pic,auction){
	//auctionType拍品类型
	if (auction.auctionType == 0) {
		auction.auctionType = "全车配件";
		$(".carTemp").show();
    	$(".vPartsTemp,.allPartsTemp,.partsTemp").hide();
	} else if (auction.auctionType == 1) {
		auction.auctionType = "高价值配件";
		$(".vPartsTemp,.partsTemp").show();
    	$(".carTemp,.allPartsTemp").hide();
	} else {
		auction.auctionType = "大宗配件";
		$(".allPartsTemp,.partsTemp").show();
    	$(".carTemp,.vPartsTemp").hide();
	}
	$(_id).find("td[col]").each(function(){
		$(this).html(auction[$(this).attr("col")]);
	});
	$(_id).find("div[col]").each(function(){
		$(this).html(auction[$(this).attr("col")]);
	});
	$(_id).form('load', auction);
	$("#vehicleInfo input,#vehicleInfo textarea").attr("readonly", true);
	//file图片展示
	loadPictureList(auction['auctionFileList'],_pic);
	//配件展示
	loadPartsList(auction['autopartsList'],'partsTable');
}

/*查看详情--图片展示*/
function loadPictureList(imglist,_id){
	var imgCount=0;
	var trid=0;
	if(obj.isNotEmpty(imglist)){
		for(var i=0;i<imglist.length;i++){
			var picEntity = imglist[i];
			var img=picEntity.imgPath;
			if(picEntity.picType == 0){
				if(imgCount%8==0){
					trid++;
					$(_id).append("<tr col='cur-"+trid+"'></tr>");
				}
				//图片放大modal='zoomImg'
				$(_id+" tr[col='cur-"+trid+"']").append("<img src='"+img+"' border='0' width='100' height='100'>");
				imgCount++;
			}
		}
	}else{
		$(_id).addClass('text-div');
		$(_id).html("暂无图片");
	}
	
}
/*查看详情--配件展示*/
function loadPartsList(partslist,_id){
	if(partslist!=null && partslist.length>0){
		var innerHtml='';
		for(var i=0;i<partslist.length;i++){
			var parts = partslist[i];
			innerHtml += '<tr><td>'+(i+1)+'</td>'+
				'<td>'+parts.partsName+'</td>'+
				'<td>'+parts.partsNum+'</td>'+
				'<td>'+parts.carFrameNumber+'</td>'+
				'<td>'+parts.carModelNumber+'</td></tr>';
		}
		$("#"+_id).append(innerHtml);
	}
}
/**
 * 添加车辆、配件
 * @param _type 0新增 1编辑
 * @returns
 */
function addLot(_type){
	var auctionType=$("#vehicleInfo input[name='auctionType']:checked").val();
	if (auctionType == '0') {
    	//请求车辆列表
		$.post("../../carscraporder/getCarScrapOrderList",{
			orderStatus:7
		},function(res,status){
			if(res.returnCode ='000000' && res.rows!=null&& res.rows.length > 0){
				$('#Dialog').dialog({
					title : "系统查询到已完成的整车订单",
					width : 1100,
					top : 10,
					modal : true,
					href : "../../html/auction/scraporderList.html",
					onLoad : function() {
						initCarDateGrid(_type);
					}
				});
			}else{
				$.messager.alert('提示', '暂没有待拍车辆','info');
			}
		}).error(function(xhr,errorText,errorType){
			$.messager.alert('提示', '系统出错：'+errorType,'info');
		});
    }else if(auctionType == '1'||auctionType == '2'){
    	//请求配件列表
		$.post("../../carscraporder/getCarScrapOrderAutopartsList",{
			orderStatus:8
		},function(res,status){
			if(res.returnCode ='000000' && res.rows!=null&& res.rows.length > 0){
				$('#Dialog').dialog({
					title : "系统查询到已完成的旧件订单",
					width : 1100,
					top : 10,
					modal : true,
					href : "../../html/auction/scraporderList.html",
					onLoad : function() {
						initAutopartsDateGrid(_type);
					}
				});
			}else{
				$.messager.alert('提示', '暂没有待拍配件','info');
			}
		}).error(function(xhr,errorText,errorType){
			$.messager.alert('提示', '系统出错：'+errorType,'info');
		});
    }
}
function deleteParts(v,_type){
	$(v).parent().parent().remove();
	if(_type == 1){
		var auctionId = $("input[name='id']").val();
		var autopartsid = $(v).parent().parent().children("td").eq(0).find("input[class='autopartsid']").val();
		$.ajax({
			url: "../../carscraporder/deleteParts/" + autopartsid +"?orderType=2&auctionId="+auctionId,
			type: "DELETE",
			dataType: "json",
			async: false,
			success: function (res) {}
		});
	}
	var count = $(".partsTable input[class='numberId']").length;
	for (var i = 0; i < count; i++) {
		$(".partsTable input[class='numberId']:eq(" + i + ")").val(i+1);
	}		
}

/**
 * initEditAuctionHtml 编辑拍品信息 初始化页面
 * @returns
 */
function initEditAuctionHtml() {
	/*拍品类型*/
	$("#vehicleInfo input[name='auctionType']").click(function(){
		return false;
	});
}


/*
 * 新增/编辑
 * _apiType: 	0:新增, 1编辑 
 * _saveType:	0:保存, 1提交
 * _isMsg:		0弹窗提示,1:不弹窗提示
 */
function saveAuction(_apiType,_saveType,_isMsg) {
	/*防止重复提交*/
	$('#c-save').attr("disabled",true);
	$('#c-pub').attr("disabled",true);
	
	if (!$('#vehicleInfo').form("validate")) {
		$('#c-save').attr("disabled",false);
		$('#c-pub').attr("disabled",false);
		return;
	}		
	var dataJson = "";
	dataJson = $('#vehicleInfo input,#vehicleInfo textarea').serialize();
	if(_apiType==0){
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
	    dataJson += "&fileIds=" + fileIds;
	}
	//配件
	var autopartsidcount = $(".partsTable input[class='autopartsid']").length;
	for (var i = 0; i < autopartsidcount; i++) {
		var autopartsid = $(".partsTable input[class='autopartsid']:eq("+i+")").val();
		if (autopartsid) {
			dataJson += "&autopartsIdList=" + autopartsid;
        }
	}
	dataJson += "&dataState=" + _saveType;
	var actionName = "";
	var ajaxType = "";
	switch (_apiType) {
	case 0:
		actionName = "addAuction";
		ajaxType = "post";
		break;
	case 1:
		actionName = "updateAuction";
		ajaxType = "post";
		break;
	}
	$.ajax({
		type : ajaxType,
		url : '../../auction/' + actionName,
		dataType : "json",
		data : dataJson,
		success : function(data) {
			$('#c-save').attr("disabled",false);
			$('#c-pub').attr("disabled",false);
				if (data.returnCode == "000000") {
					if(_isMsg==0){
						$.messager.alert('提示', (_saveType == 1 ? "提交成功":"保存成功"),'info',function(){
							goBackView(-1);
						});
					}
				}else {
					$.messager.alert('提示', (_saveType == 1 ? "提交失败":"保存失败"));
				}				
		},
		error : function(e) {
			$('#c-save').attr("disabled",false);
			$('#c-pub').attr("disabled",false);
			$.messager.alert('提示', "操作失败");
		}
	});
}
function validateCar(flag){
	var nFlag = !flag;
	$("#vin").validatebox({required : flag});
	$("#partsCount").validatebox({required : nFlag});
	$("#partsWeight").validatebox({required : nFlag});
}
function validateVParts(flag){
	var nFlag = !flag;
	$("#partsCount").validatebox({required : flag});
	$("#partsWeight").validatebox({required : nFlag});
	$("#vin").validatebox({required : nFlag});
}
function validateAllParts(flag){
	var nFlag = !flag;
	$("#partsCount").validatebox({required : flag});
	$("#partsWeight").validatebox({required : flag});
	$("#vin").validatebox({required : nFlag});
}
