$(function() {
	initAuctionProcedure();
});
function initAuctionProcedure(){
	var procedureId = getParamValue("procedureId");
	$("#procedureBody").load("procedureTemp.html",function(){
		initProcedureHistory(procedureId);
		$("#vehicleBody").load("../auction/auctionTemp.html",function(){
			var id = getParamValue("id");
			$.post("../../procedure/getAuctionProcedureById",{
				auctionId : id,
			},function(res) {
				var auction=res.entity.auction;
				var procedure=res.entity.procedure;
				loadAuctionDetail("#vehicleInfo","#carPhotoUrl",auction);
				/*手续*/
				initPProcedureData("#addProcedure",procedure);
			});
		});
	});
	$.post("../../procedure/getOutofProcedureAudit", {
		procedureId : procedureId
	}, function(data) {
		if(data.returnCode=="000000"){
			$("#outProcedureAudit").form('load', data.entity);
			$("#outProcedureAudit input,#outProcedureAudit textarea").attr("readonly", true);
		}else{
			$.messager.alert('提示','没有要审核的出库手续申请','info',function() {
				window.location.href = "../../html/procedure/procedureList.html";
			});
		}
	});
}
function initProcedure(){
	$("#addProcedure input[name='fileIds']").change(function(){
		var value = $(this).val();
		for (var i = 1; i <= 12; i++) {
			var istrue = $('#file-img-'+i).is(':hidden');
			if (!istrue) {
				$("#cb-" + i).prop("checked", true);
			}else{
				$("#cb-" + i).prop("checked", false);
			}
		}
		if(value==0){
			var istrue = $('#file-img-00').is(':hidden');
			if (!istrue) {
				$("#cb-0").prop("checked", true);
			}else{
				$("#cb-0").prop("checked", false);
			}
			var istrue = $('#file-img-01').is(':hidden');
			if (!istrue) {
				$("#cb-0").prop("checked", true);
			}else{
				$("#cb-0").prop("checked", false);
			}
		}
	});
}
//同意
/**
 * _isPass:同意或拒绝 0-同意 1-拒绝
 * 
 */
function auditAgree(_isPass,_dialog) {
	var applicationId = $('#outProcedureAudit #id').val();
	var noPassReason = '';
	if (_isPass == 1) {
		noPassReason = $("#noPassReason").val();
	}
	$.ajax({
		type : 'post',
		url : '../../procedure/outofProcedureAudit',
		dataType : "json",
		data : {
			'applicationId' : applicationId,
			'isPass' : _isPass,
			'noPassReason' : noPassReason
		},
		success : function(data) {
			if (_isPass == 0) {
				if (data.returnCode == "000000") {
					$.messager.alert('提示','审核通过','info',function() {
						window.location.href = "../../html/procedure/procedureList.html";
					});
				} else {
					$.messager.alert('提示', "审核失败");
				}
			}else{
				if (data.returnCode == "000000") {
					$.messager.alert('提示', "拒绝成功");
					$("#AUDIT_DIALOG").dialog('close');
				} else {
					$.messager.alert('提示', "拒绝失败");
				}
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}

	});
}
//拒绝
function openAuditReject(){
	$('#AUDIT_DIALOG').dialog({
        title: '信息审核',
        width: 500,
        height: 300,
        modal: true,
        resizable: true,
        href:'../../html/auction/auditReject.html',
        buttons:[
			{id:'submitFormBtn', text:'确定', handler:function(){
				auditAgree(1,"AUDIT_DIALOG");
			}},
			{text:'取消', handler:function(){
				$('#AUDIT_DIALOG').dialog("close");
				
			}
		}],
    });
}
