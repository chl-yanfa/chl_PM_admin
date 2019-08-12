$(document).ready(function(){
	initRecordCombobox();
	checkWord(document.getElementById('remark'),'remark-check',200);
});
function initRecordCombobox(){
	initAddressPCAS("#procedureRecord","#province","#city","#area",false,150,40);
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
function addProcedureRecord(){
	if (!$('#procedureRecord').form("validate")) {
		return;
	}
	if($("#procedureRecord #province").combobox('getText').length==0
			||$("#procedureRecord #city").combobox('getText').length==0
			||$("#procedureRecord #area").combobox('getText').length==0
			||$("#procedureRecord #remark").val().length==0){
		$.messager.alert('系统提示', '请完善归档信息!');
		return;
	}
	var transferInvoiceId = $(".addpic").eq(0).children("input[type='hidden']").val();
	if (transferInvoiceId==null||transferInvoiceId=='') {
		$.messager.alert('系统提示', '请上传过户发票!');
		return;
	}
	var registrationId = $(".addpic").eq(1).children("input[type='hidden']").val();
	if (registrationId==null||registrationId=='') {
		$.messager.alert('系统提示', '请上传登记证复印件!');
		return;
	}
	var procedureId = getParamValue("procedureId");
	var ff = "";
	ff = $('#procedureRecord input,#procedureRecord textarea').serialize();
	ff += "&province=" + $("#procedureRecord #province").combobox('getText');
	ff += "&city=" + $('#procedureRecord #city').combobox('getText');
	ff += "&area=" + $('#procedureRecord #area').combobox('getText');
	ff += "&procedureId=" + procedureId;
	ff += "&transferInvoiceId=" + transferInvoiceId;
	ff += "&registrationId=" + registrationId;
	$.ajax({
		type : 'post',
		url : '../../procedure/addProcedureRecord',
		dataType : "json",
		data:ff,
		success : function(data) {
			if (data.returnCode == "000000") {
				$.messager.alert('提示','手续归档成功','info',function(){
					window.open("about:blank","_self").close();
					window.location.href="../../html/procedure/procedureList.html";	
				});
			} else {
				$.messager.alert('提示', "手续归档失败");
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}
		
	});
}

