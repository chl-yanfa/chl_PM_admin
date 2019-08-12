$(function() {
	initAuction();
	$(".addLot").click(function() {
		addLot(1);
	});
});
function initAuction(){
	var id = getParamValue("id");
	$.post("../../auction/getAuctionById", {
		id:id
	}, function (data) {
		initCombobox(false);
		initEditAuctionHtml();
		loadEditAuctionData("#vehicleInfo",data);
	});
}

/**
 * loadEditAuctionData 初始化数据显示--车辆编辑
 * @param _id
 * @param auction
 * @returns
 */
function loadEditAuctionData(_id,auction) {
	$(_id).form('load', auction);
	$("#auctionNo").attr("readonly", true);
	if (auction.auctionType == 0) {
		$(".carTemp").show();
		$(".vPartsTemp,.allPartsTemp,.partsTemp").hide();
		validateCar(true);
	} else if (auction.auctionType == 1) {
		$(".vPartsTemp,.partsTemp").show();
		$(".carTemp,.allPartsTemp").hide();
		validateVParts(true);
	} else {
		$(".allPartsTemp,.partsTemp").show();
		$(".carTemp,.vPartsTemp").hide();
		validateAllParts(true);
	}
	checkWord(document.getElementById('description'),'description-check',200);
	checkWord(document.getElementById('buyRequirement'),'buyRequirement-check',200);
	/**加载图片*/
	var picList = auction.auctionFileList;
	picInfo = "";
	if(picList != null && picList.length>0){
		for(i = 0; i < picList.length; i++){
			if(picList[i].fileType == 0){
				picInfo += '<li class="pic-single">'+ 
					'<div class="pic-box">'+ 
					'<img src="' + picList[i].imgPath + '" alt="" class="pic-img"/>'+
					'<input type="hidden" class="hiddenhxpicid" value="' + picList[i].id + '" />'+
					'<button type="button" class="del-btn" onclick="delImg(this,' + picList[i].id + ')">'+
					'<img src="../../resources/images/remove.png" class="remove">'+
					'</button>'+
					'</div>'+
					'</li>';
				pic_sort = picList[i].sort+1;
			}
		}
		$("#pic-upload-row").append(picInfo);
	}
	/**加载配件*/
	var partslist = auction.autopartsList;
	if(partslist!=null && partslist.length>0){
		var innerHtml='';
		for(var i=0;i<partslist.length;i++){
			var parts = partslist[i];
			innerHtml += '<tr>'+
				'<td>'+
					'<input class="numberId" value="'+(i+1)+'" readonly="readonly" style="width:20px;border:none;"/>'+
					'<input type="hidden" class="autopartsid" value="'+parts.id+'" />'+
				'</td>'+
				'<td>'+parts.partsName+'</td>'+
				'<td>'+parts.partsNum+'</td>'+
				'<td>'+parts.carFrameNumber+'</td>'+
				'<td>'+parts.carModelNumber+'</td>'+
				'<td><a onclick="deleteParts(this,1)">删除</a></td>'+
			'</tr>';
		}
		$("#partsTable").append(innerHtml);
	}
	bidNoticeEditor.ready(function() {
		bidNoticeEditor.setContent(auction.bidNotice);
	});
	specialNoticeEditor.ready(function() {
		specialNoticeEditor.setContent(auction.specialNotice);
	});
}

/**处理图片 start*/
var fileNameList = [];
var pic_sort = 0;

function addAuctiontPic(v) {
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
    var auctionId = $("input[name='id']").val();
    var this_sort = pic_sort;
    $.ajax({
        url: "../../auctionupload/upload/" + auctionId +"?fileType="+0+"&sort="+this_sort,
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
                    pic1info = "";
                    pic1info += '<li class="pic-single">';
                    pic1info += '<div class="pic-box">';
                    pic1info += '<img src="' + res.entity.imgPath + '" alt="" class="pic-img"/>';
                    pic1info += '<input type="hidden" class="hiddenhxpicid" value="' + res.entity.id + '" />';
                    pic1info += '<button type="button" class="del-btn" onclick="delImg(this,' + res.entity.id + ')">'+
                    			'<img src="../../resources/images/remove.png" class="remove">'+
                    			'</button>';
                    pic1info += '</div>';
                    pic1info += '</li>';
                    $("#pic-upload-row").append(pic1info);
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
    $.ajax({
        url: "../../auctionupload/delete/" + picid,
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
