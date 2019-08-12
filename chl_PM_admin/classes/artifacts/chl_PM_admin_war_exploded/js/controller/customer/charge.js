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
	var marginId = getParamValue("marginId");
	$.post('../../margin/getMarginById',{
		marginId:marginId
	},function(res) {
		var info=res.entity;
		$(".marginInfo-div").find("td[col]").each(function(){
			$(this).html(info[$(this).attr("col")]);
		});
		$("input[name='mid']").val(info.mid);
	});
}
function chargeMargin(){
	if (!$('#chargeForm').form("validate")) {
		return;
	}
	var payVoucherId = $(".addpic").eq(0).children("input[type='hidden']").val();
	if (payVoucherId==null||payVoucherId=='') {
		$.messager.alert('系统提示', '请上传付款凭证!');
		return;
	}
	$('#chargeMarginBtn').attr("disabled",true);
	var formData = new FormData();
	$.each($("#chargeForm").serializeArray(),function (i,field) {
		formData.append(field.name,field.value);
	});
	formData.append("payVoucherId",payVoucherId);
	$.ajax({
        url : '../../margin/chargeMargin',
        type : 'POST',
        async : false, 
        data : formData,
        processData : false,
        contentType : false,
        success : function(res) {
        	if(res.returnCode == "000000"){
        		$.messager.alert('提示' , '保存成功' , 'info' , function() {
					$('#chargeMarginBtn').attr("disabled",false);
					window.history.back(-1);
					window.parent.scrollTo(0, 0);
				});
        	}else if(res.returnCode == "100000"){
        		$.messager.alert('提示' , "参数不足" , 'info' , function() {
					$('#chargeMarginBtn').attr("disabled",false);
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
