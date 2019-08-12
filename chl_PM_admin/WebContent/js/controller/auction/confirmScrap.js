$(function() {
	loadScrapData();
	loadData();
});

function loadScrapData(){
	var auctionId = getParamValue("id");
	$.post("../../aftersale/getScrapByAuctionId",{
		auctionId : auctionId,
	},function(res) {
		var scrap=res.entity;
		$(".scrapDiv").find("span[col]").each(function(){
			$(this).html(scrap[$(this).attr("col")]);
		});
		$('#scrap-id').val(scrap.id);
		if(scrap.fileList!=null && scrap.fileList.length>0){
			var list = scrap.fileList;
			pic_sort = list[list.length-1].sort;
		}
	});
}
function loadData(){
	var auctionId = getParamValue("id");
	$("#vehicleBody").load("auctionTemp.html",function(){
		$.post("../../procedure/getAuctionProcedureById",{
			auctionId : auctionId,
		},function(res) {
			var auction=res.entity.auction;
			loadAuctionDetail("#vehicleInfo","#carPhotoUrl",auction);
		});
	});
}
function confirmScrap(){
	var id=$('#scrap-id').val();
	var confirmRemark=$('textarea[name="confirmRemark"]').val();
	$.post("../../aftersale/confirmScrap",{
		id : id,
		confirmRemark : confirmRemark
	},function(res) {
		$.messager.alert('提示', "确认报废成功",'info',function(){
    		window.location.href="../../html/auction/scrapList.html";
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
    var scrapId = $("#scrap-id").val();
    if(obj.isNotBlank(scrapId)){
    	actionName = "../../aftersaleupload/upload/" + scrapId +"?fileType="+7+"&sort="+this_sort;
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
                	if(obj.isNotBlank(scrapId)){
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
    	actionName = "../../aftersaleupload/delete/" + picid;
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