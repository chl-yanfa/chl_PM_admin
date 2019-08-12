function addScrap(){
	var auctionId = getParamValue("id");
	var ff = $('#scrapForm input,#scrapForm textarea').serialize();
	ff += "&auctionId=" +auctionId;
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
    ff += "&fileIds=" +fileIds;
	$.post("../../aftersale/addScrap",ff,function(res) {
		if(res.returnCode='000000'){
			$.messager.alert('提示', "提交报废成功",'info',function(){
        		var auctionId = getParamValue("id");
        		window.location.href="../../html/aftersale/abortiveAuctionList.html";
        	});
		}
	});
	
}
function cancel(){
	window.history.back(-1);
	window.parent.scrollTo(0, 0);
}
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
                    fileNameList.push(pic_name);
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
