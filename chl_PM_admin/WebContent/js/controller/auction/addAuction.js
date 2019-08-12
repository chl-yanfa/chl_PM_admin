$(function() {
	initCombobox(false);
	$(".addLot").click(function() {
		addLot(0);
	});
	$("#vehicleInfo input[name='auctionType']").change(function(){
		var value = $(this).val();
	    if (value == '0') {
	    	$(".carTemp").show();
	    	$(".vPartsTemp,.allPartsTemp,.partsTemp").hide();
	    	validateCar(true);
	    }else if(value == '1'){
	    	$(".vPartsTemp,.partsTemp").show();
	    	$(".carTemp,.allPartsTemp").hide();
	    	validateVParts(true);
	    }else{
	    	$(".allPartsTemp,.partsTemp").show();
	    	$(".carTemp,.vPartsTemp").hide();
	    	validateAllParts(true);
	    }
	});
	
});

/**处理图片 start*/
var fileNameList = [];

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
    	$.messager.alert('提示',"所传图片不能大于4M");
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
            		$("#pic-upload-row").append(picinfo);
                }
                fileNameList.push(pic_name);
            };
        },
        error: function (returndata) {
        	$.messager.alert('提示',"操作失败");
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