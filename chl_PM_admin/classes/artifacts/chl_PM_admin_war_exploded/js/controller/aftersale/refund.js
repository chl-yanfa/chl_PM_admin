$(function() {
	loadData();
	//收款类型
	$('#chargeSource').combobox({
    	editable : false,
    	width : 150,
		valueField:'id',
		textField:'value',
		data: [{id:"银行转账",value: "银行转账"}],
		panelHeight:"auto",
		required:true
	});
});

function loadData(){
	var auctionSetId = getParamValue("auctionSetId");
	$.post('../../finance/getRefund',{
		auctionSetId:auctionSetId
	},function(res) {
		if(res.entity!=null){
			var pay=res.entity;
			$('.content-top .title').html("已退款");
			$('.loadPay-DIV').show();
			$('#payForm-load').form('load', pay);
			$("#payForm-load input,#payForm-load textarea").attr("readonly", true);
			$('#payForm-load .img-payVoucher').html("<img src='"+pay.payVoucherPath+"' width='100%' height='100%'>");
		}else{
			$('.addPay-DIV').show();		
		}
	});
	$.post('../../aftersale/getDealInfoByAuctionSetId',{
		auctionSetId:auctionSetId
	},function(res) {
		var dealInfo=res.entity;
		dealEntity=res.entity;
		if(dealInfo['licenseNumber']){
			dealInfo.licenseNumber = dealInfo.licenseNumber;
		}else{
			dealInfo.licenseNumber="车牌未提供"
		}
		$(".dealInfo-div").find("span[col]").each(function(){
			$(this).html(dealInfo[$(this).attr("col")]);
		});
	});
}
function refund(){
	if (!$('#refundForm').form("validate")) {
		return;
	}
	var payVoucherId = $(".addpic").eq(0).children("input[type='hidden']").val();
	if (payVoucherId==null||payVoucherId=='') {
		$.messager.alert('系统提示', '请上传退款凭证!');
		return;
	}
	var auctionId = getParamValue("auctionId");
	var auctionSetId = getParamValue("auctionSetId");
	$('#refundBtn').attr("disabled",true);
	var formData = new FormData();
	$.each($("#refundForm").serializeArray(),function (i,field) {
		formData.append(field.name,field.value);
	});
	formData.append("payVoucherId",payVoucherId);
	formData.append("auctionId",auctionId);
	formData.append("auctionSetId",auctionSetId);
	$.ajax({
        url : '../../aftersale/addRefund',
        type : 'POST',
        data : formData,
        processData : false,
        contentType : false,
        success : function(res) {
        	if(res.returnCode == "000000"){
        		$.messager.alert('提示' , '操作成功' , 'info' , function() {
					$('#refundBtn').attr("disabled",false);
					window.history.back(-1);
					window.parent.scrollTo(0, 0);
				});
        	}else if(res.returnCode == "100000"){
        		$.messager.alert('提示' , "参数不足" , 'info' , function() {
					$('#refundBtn').attr("disabled",false);
				});
        	}
        }
    });
}
//上传图片
function addFile(a) {
    $(".upload-file-"+a).click();
};
function doUpload(a) {
    var formData = new FormData();
    if ($(".upload-file-"+a)[0].files.length == 0) {
    	$.messager.alert("系统提示","请选择文件！");
        return false;
    };
    var pics = $(".upload-file-"+a)[0].files[0].name;
    if (!/\.(jpg|png|gif|jpeg|JPG|PNG|GIF|JPEG)$/.test(pics)) {
    	$.messager.alert("系统提示","请上传正确图片格式");
        $(".upload-file-"+a)[0].val("");
        return;
    };
    var sizes = $(".upload-file-"+a)[0].files[0].size;
    var pan = Math.round(sizes / 1024 * 100) / 100;
    if (pan > 4096) {
        $.messager.alert("系统提示","所传图片不能大于4M");
        return;
    };
    formData.append("file", $(".upload-file-"+a)[0].files[0]);
    var actionName ="../../attachment/uploadSingle";
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
            if (res.returnCode == "000000") {
                if (res.entity!=null) {
            		$(".addpic").eq(a).children("img").attr("src", res.entity.storagePath);
            		var id = res.entity.id;
            		$(".addpic").eq(a).children("input[type='hidden']").val(id);
            		var delinfo = '<button type="button" class="del-btn" onclick="delImg(this,' + id + ')">'+
            		'<img src="../../resources/images/remove.png" class="remove">'+
            		'</button>';
            		$(".addpic").eq(a).children(".delpic").html(delinfo);  
                }
            };
        },
        error: function (returndata) {
            $.messager.alert("系统提示","操作失败");
        }
    });
};
function delImg(v, picid) {
	var actionName ="../../attachment/delete/"+picid;
    $.ajax({
        url: actionName,
        type: "DELETE",
        dataType: "json",
        async: false,
        success: function (res) {
            if (res.returnCode == "000000") {
            	console.log("删除图片id=" + picid);
				$(v).parent().parent().children("img").attr("src", "../../resources/images/addimg-bg.png");
				$(v).parent().parent().children("input[type='hidden']").val("");
				$(v).parent().html("");
            }
        }
    });
}
