$(function() {
	initAuctionProcedure();
});
function initAuctionProcedure(){
	var procedureId = getParamValue("procedureId");
	$("#procedureBody").load("addProcedureTemp.html",function(){
		$("#vehicleBody").load("../auction/auctionTemp.html",function(){
			var id = getParamValue("id");
			$.post("../../procedure/getAuctionProcedureById",{
				auctionId : id,
			},function(res) {
				var auction=res.entity.auction;
				var procedure=res.entity.procedure;
				loadAuctionDetail("#vehicleInfo","#carPhotoUrl",auction);
				/*手续入库 */
				if(procedure !=null && procedure.id!=null&&procedure.id.length>0){
					procedure.version = procedure.version + 1;
				}else{
					procedure.version = 1;
				}
				initAddProcedure("#addProcedure",procedure);
				initProcedure();
				initLoadFile(procedure.procedureFileList);
			});
		});
	});
}
function initProcedure(){
	$("#addProcedure input[name='fileTypes'][value='0']").prop("checked", true);
	$("#addProcedure input[name='fileTypes'][value='1']").prop("checked", true);
	$("#addProcedure input[name='fileTypes'][value='2']").prop("checked", true);
	$('#li-file-0,#li-file-1,#li-file-2').show();
	$("#addProcedure input[name='fileTypes']").click(function(){
		$("#addProcedure input[name='fileTypes'][value='0']").prop("checked", true);
		$("#addProcedure input[name='fileTypes'][value='1']").prop("checked", true);
		$("#addProcedure input[name='fileTypes'][value='2']").prop("checked", true);
		var i = $(this).val();
		var fileId = $(".addpic").eq(i).children("input[type='hidden']").val();
		if (fileId!=null&&fileId!=''&&fileId.length>0 && i>2 && ($("#cb-" + i).prop("checked")==false)) {
			$.messager.show({
				title:'提示',
				msg:'请先取消已上传的证件',
				showType:'fade',
				timeout:3000,
				style:{
					right:'',
					bottom:''
				}
			});
			$("#cb-" + i).prop("checked", true);
		}
		if ($("#cb-" + i).prop("checked")==true) {
			$('#li-file-' + i).show();
		}else{
			$('#li-file-' + i).hide();
		}
	});
}
function initLoadFile(procedureFileList){
	if(procedureFileList!=null&&procedureFileList.length>0){
		for(var i=0;i<procedureFileList.length;i++){
			var fileType=procedureFileList[i].fileType;
			var pstate=procedureFileList[i].procedureState;
			var filePath=procedureFileList[i].imgPath;
			var fileId=procedureFileList[i].id;
			var attachmentId=procedureFileList[i].attachmentId;
			
			$(".addpic").eq(fileType).children("img").attr("src", filePath);
			$(".addpic").eq(fileType).children("input[type='hidden']").val(attachmentId);
			$(".addpic").eq(fileType).children("input[type='file']").remove();
		
			if(pstate==0){
				$("#cb-" + fileType).prop("checked", true);
				$('#li-file-' + fileType).show();
			}
		}
	}
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
//确认入库
function addProcedure() {
	if (!$('#addProcedure').form("validate")) {
		return;
	}
	var idcardFaceFileId = $(".addpic").eq(0).children("input[type='hidden']").val();
	if (idcardFaceFileId==null||idcardFaceFileId=='') {
		$.messager.alert('系统提示', '请上传车主身份证正面照!');
		return;
	}
	var idcardBackFileId = $(".addpic").eq(1).children("input[type='hidden']").val();
	if (idcardBackFileId==null||idcardBackFileId=='') {
		$.messager.alert('系统提示', '请上传车主身份证反面照!');
		return;
	}
	for (var i = 2; i <= 13; i++) {
		if ($("#cb-" + i).prop("checked")==true) {
			var filed = $(".addpic").eq(i).children("input[type='hidden']").val();
			if (filed==null||filed=='') {
				$.messager.alert('系统提示', '请上传相关手续扫描件!');
				return;
			}
		}
	}
	var data='';
	//获取上传的证件
	var fileTypes = '';
	var fileIds = '';
	var cfileId0 = $(".addpic").eq(0).children("input[type='hidden']").val();
	if (cfileId0!=null&&cfileId0!=''&&cfileId0.length>0) {
		var cfileType0 = $(".addpic").eq(0).children(".fileType").text();
		fileIds = fileIds + cfileId0 + "," ;
		fileTypes = fileTypes + cfileType0 + "," ;
	}
	var cfileId1 = $(".addpic").eq(1).children("input[type='hidden']").val();
	if (cfileId1!=null&&cfileId1!=''&&cfileId1.length>0) {
		var cfileType1 = $(".addpic").eq(1).children(".fileType").text();
		fileIds = fileIds + cfileId1 + "," ;
		fileTypes = fileTypes + cfileType1 + "," ;
	}
	for (var i = 2; i <= 13; i++) {
		if ($("#cb-" + i).prop("checked")==true) {
			var fileId = $(".addpic").eq(i).children("input[type='hidden']").val();
			if (fileId!=null&&fileId!=''&&fileId.length>0) {
				var fileType = $(".addpic").eq(i).children(".fileType").text();
				fileIds = fileIds + fileId + "," ;
				fileTypes = fileTypes + fileType + "," ;
			}			
		}
	}
	if(fileIds!=null&&fileIds.length>0){
		fileIds=fileIds.substring(0,fileIds.length-1);
	}
	if(fileTypes!=null&&fileTypes.length>0){
		fileTypes=fileTypes.substring(0,fileTypes.length-1);
	}
	data += "&fileIds="+fileIds;
//	data += "&fileTypes="+fileTypes;
	ajaxSubmit(data);
}
function ajaxSubmit(fileData) {
	var auctionId = getParamValue("id");
	var version = $("input[name='version']").val();
	var param = "";
	param = $('#addProcedure input').serialize();
	param += "&province=" + $("#addProcedure #province").combobox('getText');
	param += "&city=" + $('#addProcedure #city').combobox('getText');
	param += "&area=" + $('#addProcedure #area').combobox('getText');
	param += "&auctionId=" + auctionId;
	param += "&version=" + version;
	param += fileData;
	$.ajax({
		type : 'post',
		url : '../../procedure/addProcedure',
		dataType : "json",
		data : param,
		success : function(data) {
			if (data.returnCode == "000000") {
				$.messager.alert('提示', "手续入库成功",'info',function(){
	        		var procedureId = data.entity;
	        		var auctionId = getParamValue("id");
	        		window.location.href="../../html/procedure/addProcedure.html?id="+auctionId+"&procedureId="+procedureId;
	        	});
			} else {
				$.messager.alert('提示', "手续入库失败");
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}

	});
}
/**=========================================================================*/
//出库申请-弹窗
function openOutofProcedure() {
	var procedureId = getParamValue("procedureId");
	if (procedureId == "null") {
		procedureId = $("#addProcedure #id").val();
	}
	$.ajax({
		type : 'post',
		url : '../../procedure/getOutofProcedure',
		dataType : "json",
		data : {
			procedureId : procedureId
		},
		success : function(data) {
			if(data.returnCode=="000000"){
				$('#DIALOG').dialog({
					title : "手续出库申请",
					width : 600,
					height : 700,
					modal : true,
					href : "../../html/procedure/outProcedure.html",
					buttons : [ 
						{id : 'outofProcedureBtn',text : '申请出库',handler : function() {
							outofProcedure("DIALOG");
						}}, 
						{id : 'cancelBtn',text : '取消',handler : function() {
							$('#DIALOG').dialog("close");
						}} 
					],
					onLoad: function () {
						var list=data.entity.fileTypeList;
						if(list!=null&&list.length>0){
							var str='';
							for(var key =0;key<list.length;key++){
								var i=list[key];
								str+='<div class="out-check">'
										+'<input type="checkbox" id="out-cb-'+i+'" name="fileTypes" value='+i+'>'
										+'<label for="out-cb-'+i+'">'+outCheckBox[i]+'</label>'
									+'</div>';
							}
							$("#checkBoxBody").html(str);
						}
						$("#outofProcedure").form('load', data.entity);
					},
				});
			}else if(data.returnCode=="400000"){
				$.messager.alert('提示', "没有要出库的手续");
			}
		},
	});
}
var outCheckBox={0:'车主身份证',1:'委托合同',2:'车辆登记证',3:'进口车商检证',4:'车辆行驶证',5:'交强险单凭证',6:'购车发票',7:'交裁证明复印件',8:'购置附加税证',9:'进口车报关单',10:'工商认证章',11:'其他证件',12:'委托方组织机构代码证',}

//出库申请
function outofProcedure(_dialogId){
	if (!$('#outofProcedure').form("validate")) {
		return;
	}
	var ff = "";
	ff = $('#outofProcedure input,#outofProcedure textarea').serialize();
	$.ajax({
		type : 'post',
		url : '../../procedure/outofProcedure',
		dataType : 'json',
		data : ff,
		success : function(data) {
			if (data.returnCode == "000000") {
				$.messager.alert('提示','申请出库成功','info',function(){
					window.location.href="../../html/procedure/procedureList.html";	
					$('#' + _dialogId).dialog("close");
				});
			}else {
				$.messager.alert('提示', "申请出库失败");					
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}
	});
}
// 完结归档
function addRecord() {
	var procedureId = getParamValue("procedureId");
	if (procedureId == "null") {
		procedureId = $("#addProcedure #id").val();
	}
	if (procedureId != "null"&&procedureId != null&&procedureId.length>0) {
		window.location.href = "../../html/procedure/procedureRecord.html?procedureId="+ procedureId;		
	}else{
		$.messager.alert('提示', "请先进行手续入库");
	}
}

